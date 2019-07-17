package engengenergy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.setUserAgentStylesheet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
public class EngEngEnergy extends Application {
    /**
     * Global Variables
     */
    Double L;
    Double H;
    Login login;
    Trade trade;
    Root Design;
    Stage Energy;
    /**
     * Selector Variables
     */
    Actor ActorNow;
    Place PlaceNow;
    Plant PlantNow;
    Paper PaperNow;
    /**
     * General Program Transaction Variables
     */
    AreaSelect areaselect;
    UserSelect userselect;
    UserEditor usereditor;
    Distribute distribute;
    /**
     * Place selection variables
     */
    String[] address = {"","","","",""};
    /**
     * File Handling Variable
     */
    DataFile F = new DataFile(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    /**
     * Program Sequencer Methods
     */
    private void TypeMaster(String type) throws IOException, FileNotFoundException, ParseException {
        Design.getChildren().remove(Design.getChildren().size()-1);
        login.A.Usertype = type;
        login.A.Picture = "Default.png";
        TradeStart();
        trade.log(login.A.Username+" chose to be a "+login.A.Usertype+"\nOnce you're done, click the \"+\" Button on the upper left corner.");
        UserEditorStart();
    }
    private void ActorMaker(int i) {    
        if(i<3) {
            login.ActorMaker[i] = new SmallDialog(L,H,login.Captioning[i],"Yes","No");
            login.ActorMaker[i].next.control.setOnAction((ActionEvent ae_go_maker) -> {try {TypeMaster(login.Typer[i]);} catch (IOException | ParseException ex) {Logger.getLogger(EngEngEnergy.class.getName()).log(Level.SEVERE, null, ex);}});
            login.ActorMaker[i].back.control.setOnAction((ActionEvent ae_no_maker) -> {
                Design.getChildren().remove(Design.getChildren().size()-1);
                ActorMaker(i+1);
            });
        }
        else {
            login.ActorMaker[i] = new SmallDialog(L,H,"Sorry, "+login.User.input.getText()+".\nThose are your only options.","Ok",null);
            login.ActorMaker[i].next.control.setOnAction((ActionEvent ae_nomore) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
        }
        Design.getChildren().add(login.ActorMaker[i]);
    }
    private void LoginTrade() {
        // Login Outro setup
        login.next = new Timeline(
            new KeyFrame(Duration.millis(800),
                (EventHandler<ActionEvent>) (ActionEvent ae_proceed1) -> {
                    new Timeline(
                        new KeyFrame(Duration.millis(800),
                            (EventHandler<ActionEvent>) (ActionEvent ae_proceed2) -> {try {TradeStart();} catch (IOException | ParseException ex) {Logger.getLogger(EngEngEnergy.class.getName()).log(Level.SEVERE, null, ex);}},
                            new KeyValue(login.Bar.opacityProperty(), 1),
                            new KeyValue(login.Bar.opacityProperty(), 0),
                            new KeyValue(login.Bar.translateYProperty(), 0),
                            new KeyValue(login.Bar.translateYProperty(), H/3),
                            new KeyValue(login.Gear.translateYProperty(), 0),
                            new KeyValue(login.Gear.translateYProperty(), -H/3),
                            new KeyValue(login.Gear.rotateProperty(), 0),
                            new KeyValue(login.Gear.rotateProperty(), 720),
                            new KeyValue(login.Gear.opacityProperty(), 1),
                            new KeyValue(login.Gear.opacityProperty(), 0)
                        )
                    ).play();
                },
                new KeyValue(login.Low.opacityProperty(), 1),
                new KeyValue(login.Low.opacityProperty(), 0),
                new KeyValue(login.Top.opacityProperty(), 1),
                new KeyValue(login.Top.opacityProperty(), 0),
                new KeyValue(login.Gear.translateXProperty(), login.Gear.getTranslateX()),
                new KeyValue(login.Gear.translateXProperty(), 0),
                new KeyValue(login.Bar.translateXProperty(), login.Bar.getTranslateX()),
                new KeyValue(login.Bar.translateXProperty(), 0)
            )
        );
    }
    private void LoginEntry() {
        login.Low.control.setOnAction((ActionEvent ae_low) -> {
            switch(login.Low.control.getText()) {
                case "Enter":
                    if(login.User.input.getText().isEmpty()) {
                        login.Empty = new SmallDialog(L,H,"You can't login with an empty username!","Ok",null);
                        login.Empty.next.control.setOnAction((ActionEvent ae_empty) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                        Design.getChildren().add(login.Empty);
                    }
                    else {
                        if((F.FindActor(login.User.input.getText())==null)) {
                            login.False = new SmallDialog(L,H,"Sorry, "+login.User.input.getText()+".\nIt seems you are not in our database.\nRegister Now?","Yes","No");
                            login.False.next.control.setOnAction((ActionEvent ae_go_lost) -> {
                                Design.getChildren().remove(Design.getChildren().size()-1);
                                login.A = new Actor(F.makeID("Actor"),login.User.input.getText());
                                ActorMaker(0);
                            });
                            login.False.back.control.setOnAction((ActionEvent ae_no_lost) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                            Design.getChildren().add(login.False);
                        }
                        else {
                            login.A = F.FindActor(login.User.input.getText());
                            login.Top.control.setText("Login");
                            login.Low.control.setText("Change User");
                            login.User.setVisible(false);
                            login.Pass.setVisible(true);
                            login.Pass.input.requestFocus();
                            login.pass.play();
                        }
                    }
                break;
                case "Change User":
                    login.Low.control.setText("Enter");
                    login.Top.control.setText("Exit");
                    login.User.setVisible(true);
                    login.User.input.requestFocus();
                    login.Pass.setVisible(false);
                    login.back.play();
                break;
            }
        });
    }
    private void LoginStart() {
        // Login setup
        login = new Login(L,H,Design,F);
        // Begin login
        login.intro.play();
        // Entry setup
        LoginEntry();
        // Login Outro
        LoginTrade();
        // General Transaction setup
        userselect = new UserSelect(L,H);
    }
    private void TradeStart() throws IOException, FileNotFoundException, ParseException {
        // Trade setup
        ActorNow = login.A;
        trade = new Trade(L,H,ActorNow,Design);
        Design.getChildren().remove(2);
        trade.head.intro.play();
        // Time Setup
        TraderTime();
        // Settings button
        TradeGears();
        // User Settings button
        TradePhoto();
        switch(ActorNow.Usertype) {
            case "Distributor":
                DistributeStart();
                trade.body = distribute;
            break;
            case "Provider":
                DistributeStart();
            break;
            case "Customer":
                DistributeStart();
            break;
            default: break;
        }
    }
    private void TradeGears() {
        trade.head.gear.control.setOnAction((ActionEvent ae_settings) -> {
            // Create Dialog
            SmallDialog settings = new SmallDialog(L,H,"Hi "+ActorNow.Username+"!\nWhat else do you want to do?","Logout","About Us");
            settings.next.control.setOnAction((ActionEvent ae_logout) -> {
                Design.getChildren().remove(Design.getChildren().size()-1);
                if(ActorNow.Registry.equals("no")) {
                    SmallDialog returner = new SmallDialog(L,H,"Hey, wait!\nYou're not yet in our database.\nLogging out now will forfeit your registration.\nYou sure about this, "+ActorNow.Username+"?","Yes","No");
                    returner.next.control.setOnAction((ActionEvent ae_go_logout) -> {
                        Design.getChildren().remove(Design.getChildren().size()-1);
                        Design.getChildren().remove(Design.getChildren().size()-1);
                        LoginStart();
                    });
                    returner.back.control.setOnAction((ActionEvent ae_go_logout) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                    Design.getChildren().add(returner);
                }
                else {
                    ActorNow.logout(trade.foot.TimeClock.getText());
                    try {F.Save("Actor");} catch (IOException ex) {Logger.getLogger(EngEngEnergy.class.getName()).log(Level.SEVERE, null, ex);}
                    Design.getChildren().remove(Design.getChildren().size()-1);
                    LoginStart();
                }
            });
            settings.back.control.setOnAction((ActionEvent ae_about) -> {
                Design.getChildren().remove(3);
            });
            Design.getChildren().add(settings);
        });
    }
    private void TradePhoto() {
        trade.head.user.control.setOnAction((ActionEvent ae_user) -> {
            boolean flag = true;
            if(!ActorNow.Registry.equals("no")) {
                if(trade.log.get(trade.log.size()-1).equals("User editor window opened.\n")) {
                    if(ActorNow.Credcard.equals(usereditor.card.input.getText())) {
                        if(ActorNow.Contact.equals(usereditor.phone.input.getText())) {
                            if(ActorNow.Password.equals(usereditor.pass.input.getText())) {
                                if(! ActorNow.Password.equals(usereditor.very.input.getText())) flag=false; 
                            } else flag=false;
                        } else flag=false;
                    } else flag=false;
                    if(!flag) {
                        SmallDialog xedit = new SmallDialog(L,H,"You have some unsaved changes!\nUndo your changes or save them first.","OK",null);
                        xedit.next.control.setOnAction((ActionEvent ae_ok_xedit) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                        Design.getChildren().add(xedit);
                    } else UserSelectStart();
                } else UserEditorStart();
            } else {
                // Check card
                if(usereditor.card.input.getText().length()==16 && usereditor.card.input.getText().matches("[0-9]+")) {
                    // Check phone
                    if(usereditor.phone.input.getText().length()==11 && usereditor.phone.input.getText().matches("[0-9]+")) {
                        // Check place
                        if(usereditor.place.control.getText().equals("Click here to select address")) {
                            SmallDialog xplace = new SmallDialog(L,H,"You must have an address!","OK",null);
                            xplace.next.control.setOnAction((ActionEvent ae_ok_xplace) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                            Design.getChildren().add(xplace);
                        } else {
                            // Check pass
                            if(!usereditor.pass.input.getText().isEmpty()) {
                                // Check very
                                if(usereditor.very.input.getText().equals(usereditor.pass.input.getText())) {
                                    ActorNow.Credcard = usereditor.card.input.getText();
                                    ActorNow.Password =usereditor.pass.input.getText();
                                    ActorNow.Revenues = ActorNow.Costings = 0;
                                    ActorNow.Networth = 100000;
                                    ActorNow.Contact = usereditor.phone.input.getText();
                                    SmallDialog xnew = new SmallDialog(L,H,"Welcome to Eng Eng Energy, "+ActorNow.Username+"!\nJust select your user icon.\nAfter that, you're finally a registered "+ActorNow.Usertype+"!","OK",null);
                                    xnew.next.control.setOnAction((ActionEvent ae_ok_xnew) -> {
                                        Design.getChildren().remove(Design.getChildren().size()-1);
                                        UserSelectStart();
                                    });
                                    Design.getChildren().add(xnew);
                                } else {
                                    SmallDialog xvery = new SmallDialog(L,H,"Your passwords do not match!","OK",null);
                                    xvery.next.control.setOnAction((ActionEvent ae_ok_xvery) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                                    Design.getChildren().add(xvery);
                                }
                            } else {
                                SmallDialog xpass = new SmallDialog(L,H,"Your password cannot be empty!","OK",null);
                                xpass.next.control.setOnAction((ActionEvent ae_ok_xpass) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                                Design.getChildren().add(xpass);
                            }
                        }
                    } else {
                        SmallDialog xphone = new SmallDialog(L,H,"Your contact number is invalid! It must be an 11-digit number.","OK",null);
                        xphone.next.control.setOnAction((ActionEvent ae_ok_xphone) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                        Design.getChildren().add(xphone);
                    }
                } else {
                    SmallDialog xcard = new SmallDialog(L,H,"Your credit card is invalid! It must be a 16-digit number.","OK",null);
                    xcard.next.control.setOnAction((ActionEvent ae_ok_xcard) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                    Design.getChildren().add(xcard);
                }
            }
        });
    }
    private void TraderTime() throws IOException, FileNotFoundException, ParseException {
        // Informant data
        trade.log(ActorNow.Username+" logged in at "+F.Tick());
        // Timeline setup
        trade.foot.Tick = new Timeline(
            new KeyFrame(Duration.ZERO,
                (EventHandler<ActionEvent>) (ActionEvent ae_clock) -> {
                    try {trade.foot.TimeClock.setText(F.Tick());} catch (IOException | ParseException ex) {Logger.getLogger(Foot.class.getName()).log(Level.SEVERE, null, ex);}
                }
            ),
            new KeyFrame(Duration.seconds(1))
        );
        trade.foot.Tick.setCycleCount(INDEFINITE);
        trade.foot.Tick.play();
    }
    private void UserEditorStart() {
        trade.log("User editor window opened.");
        usereditor = new UserEditor(L,H,ActorNow,F);
        usereditor.head.control.setOnMouseExited((MouseEvent me_in_userdata) -> {usereditor.head.control.setText("Change User Data");});
        if(!ActorNow.Registry.equals("no")) {
            usereditor.head.control.setOnMouseEntered((MouseEvent me_in_userdata) -> {usereditor.head.control.setText("Save Changes");});
            usereditor.head.control.setOnAction((ActionEvent ae_head) -> {
                // Check card
                if(usereditor.card.input.getText().length()==16 && usereditor.card.input.getText().matches("[0-9]+")) {
                    // Check phone
                    if(usereditor.phone.input.getText().length()==11 && usereditor.phone.input.getText().matches("[0-9]+")) {
                        // Check pass
                        if(!usereditor.pass.input.getText().isEmpty()) {
                            // Check very
                            if(usereditor.very.input.getText().equals(usereditor.pass.input.getText())) {
                                ActorNow.Credcard=usereditor.card.input.getText();
                                ActorNow.Contact=usereditor.phone.input.getText();
                                ActorNow.Password=usereditor.pass.input.getText();
                                trade.log("User details updated");
                                try {F.Save("Actor");} catch (IOException ex) {Logger.getLogger(EngEngEnergy.class.getName()).log(Level.SEVERE, null, ex);}
                                trade.setCenter(trade.body);
                            }
                            else {
                                SmallDialog xvery = new SmallDialog(L,H,"Your passwords do not match!","OK",null);
                                xvery.next.control.setOnAction((ActionEvent ae_ok_xvery) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                                Design.getChildren().add(xvery);
                            }
                        }
                        else {
                            SmallDialog xpass = new SmallDialog(L,H,"Your password cannot be empty!","OK",null);
                            xpass.next.control.setOnAction((ActionEvent ae_ok_xpass) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                            Design.getChildren().add(xpass);
                        }
                    }
                    else {
                        SmallDialog xphone = new SmallDialog(L,H,"Your contact number is invalid! It must be an 11-digit number.","OK",null);
                        xphone.next.control.setOnAction((ActionEvent ae_ok_xphone) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                        Design.getChildren().add(xphone);
                    }
                }
                else {
                    SmallDialog xcard = new SmallDialog(L,H,"Your credit card is invalid! It must be a 16-digit number.","OK",null);
                    xcard.next.control.setOnAction((ActionEvent ae_ok_xcard) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                    Design.getChildren().add(xcard);
                }
            });
        }
        usereditor.place.control.setOnAction((ActionEvent ae_place) -> {try {UserAreaSelectStart(0);} catch (IOException ex) {Logger.getLogger(EngEngEnergy.class.getName()).log(Level.SEVERE, null, ex);}});
        trade.setCenter(usereditor);
    }
    private void UserSelectStart() {
        trade.log("User icon selector opened.");
        userselect.Title.control.setOnAction((ActionEvent ae) -> {trade.setCenter(usereditor);});
        for(PhotoButton p : userselect.Users) {
            p.control.setOnAction((ActionEvent ae_pic) -> {
                ActorNow.Picture = p.control.getText();
                if(ActorNow.Registry.equals("no")) {
                    trade.head.user.picture.setFill(new ImagePattern(new Image(getClass().getResourceAsStream(ActorNow.Picture))));
                    trade.setCenter(trade.body);
                    SmallDialog log = new SmallDialog(L,H,"As a first-timer, you'll be logged out of the program.\nHowever, you already have an account.\nWe just need you to verify your registration.\nCongratulations!","OK",null);
                    log.next.control.setOnAction((ActionEvent ae_ok_log) -> {
                        Design.getChildren().remove(Design.getChildren().size()-1);
                        Design.getChildren().remove(Design.getChildren().size()-1);
                        ActorNow.Registry = ActorNow.Timedout = trade.foot.TimeClock.getText();
                        F.actors.add(ActorNow);
                        try {F.Save("Actor");} catch (IOException ex) {Logger.getLogger(EngEngEnergy.class.getName()).log(Level.SEVERE, null, ex);}
                        LoginStart();
                    });
                    Design.getChildren().add(log);
                }
                else {
                    try {F.Save("Actor");} catch (IOException ex) {Logger.getLogger(EngEngEnergy.class.getName()).log(Level.SEVERE, null, ex);}
                    trade.head.user.picture.setFill(new ImagePattern(new Image(getClass().getResourceAsStream(ActorNow.Picture))));
                    trade.log(ActorNow.Username+" changed icon to "+p.control.getText());
                    trade.setCenter(usereditor);
                }
            });
        }
        trade.setCenter(userselect);
    }
    private void DistributeStart() {
        distribute = new Distribute(L,H,F,ActorNow);
        // demands setup
        for(int i=0; i<distribute.Demand.size(); i++) {
            PhotoButton demandnow = distribute.DemandGet.get(i);
            SmallButton demanding = distribute.Demand.get(i);
            demandnow.control.setOnAction((ActionEvent ae_demander) -> {
                distribute.place = F.FindPlace((demanding.control.getText()), "ID");
                trade.log(demanding.control.getText()+" selected for distribution as recipient.");
                distribute.paperview(L, H, distribute.plant, distribute.place, distribute.paper, F);
            });
            demanding.control.setOnAction((ActionEvent ae_demand) -> {
                trade.log(demanding.control.getText()+" details viewed.");
                distribute.place = distribute.placeview(L, H, F.FindPlace(demanding.control.getText(), "ID"), F);
            });
        }
        // supply setup
        for(int i=0; i<distribute.Supply.size(); i++) {
            SmallButton supplynow = distribute.SupplyGet.get(i);
            SmallButton supplying = distribute.Supply.get(i);
            supplynow.control.setOnAction((ActionEvent ae_supplier) -> {
                distribute.plant = F.FindPlant((supplying.control.getText()));
                trade.log(supplying.control.getText()+" selected for distribution as provider.");
                distribute.paperview(L, H, distribute.plant, distribute.place, distribute.paper, F);
            });
            supplying.control.setOnAction((ActionEvent ae_supply) -> {
                trade.log(supplying.control.getText()+" details viewed.");
                distribute.plant = distribute.plantview(L, H, F.FindPlant(supplying.control.getText()), F);
            });
        }
        // distribution data setup
        distribute.Interface.control.setOnAction((ActionEvent ae_distribution) -> {distribute.paperview(L, H, distribute.plant, distribute.place, distribute.paper, F);});
        trade.setCenter(distribute);
    }
    private void UserAreaSelectStart(int type) throws IOException {
        if(type<5) {
            areaselect = new AreaSelect(L,H,F,address,type);
            // Create Button Setup
            areaselect.Create.control.setOnAction((ActionEvent ae_create_option) -> {
                if(areaselect.Option.input.getText().isEmpty()) {
                    SmallDialog xplace = new SmallDialog(L,H,areaselect.locator[type]+" cannot be empty!","OK",null);
                    xplace.next.control.setOnAction((ActionEvent ae_ok_xplace) -> {Design.getChildren().remove(Design.getChildren().size()-1);});
                    Design.getChildren().add(xplace);
                }
                else {
                    address[type] = areaselect.Option.input.getText();
                    try {UserAreaSelectStart(type+1);} catch (IOException ex) {Logger.getLogger(EngEngEnergy.class.getName()).log(Level.SEVERE, null, ex);}
                }
            });
            // Title setup
            areaselect.Title.control.setOnAction((ActionEvent ae_create_option) -> {
                if(type>0) try {UserAreaSelectStart(type-1);} catch (IOException ex) {Logger.getLogger(EngEngEnergy.class.getName()).log(Level.SEVERE, null, ex);}
                else {
                    trade.log("User editor window opened.");
                    trade.setCenter(usereditor);
                }
            });
            // Selectables Setup
            for(int i=0; i<areaselect.items.size(); i++) {
                SmallButton item = areaselect.items.get(i);
                item.control.setOnAction((ActionEvent ae_items_option) -> {areaselect.Option.input.setText(item.control.getText());});
            }
            String ss;
            if(type>0) ss = "New "+areaselect.locator[type]+" in "+areaselect.located[type-1]+" "+areaselect.locator[type-1]+" being  created...";
            else ss = "New "+areaselect.locator[type]+" being  created...";
            trade.log(ss);
            trade.setCenter(areaselect);
        }
        else {
            areaselect = new AreaSelect(L,H,F,address,type);
            PlaceNow = areaselect.P;
            ActorNow.Address = PlaceNow.ID;
            usereditor.place.control.setText(F.toAddress(ActorNow.Address));
            trade.log("New address saved!");
            trade.setCenter(usereditor);
        }
    }
    /**
     * Application Methods
     */
    private void preset(Path file) throws MalformedURLException {
        file.toFile().deleteOnExit();
        setUserAgentStylesheet(file.toUri().toURL().toString());
        Energy.initStyle(StageStyle.UNDECORATED);
    }
    public static void main(String[] args) {launch(args);}
    @Override public void start(Stage stage) throws IOException {
        // Initialize files
        F.Read("", "Files");
        // Initialize Energy stage
        Energy = stage;
        // CSS reset
        preset(Files.createTempFile("prefix", "suffix"));
        // Launch Energy
        Energy.show();
        Energy.setMaximized(true);
        L = Energy.getWidth();
        H = Energy.getHeight();
        // Design setup
        Design = new Root(L,H);
        // Scene setup
        Energy.setScene(new Scene(Design));
        Energy.getScene().getStylesheets().add(getClass().getResource("EngEngEnergy.css").toExternalForm());
        // Formal Start
        LoginStart();
    }
}
