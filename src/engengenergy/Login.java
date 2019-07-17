package engengenergy;

import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Login extends StackPane{
    Actor A;
    Circle Gear;
    Timeline next;
    SmallButton Top;
    SmallButton Low;
    SmallButton Title;
    SmallTexter User;
    SmallCrypto Pass;
    SmallHeader Rite;
    SmallHeader Left;
    Movement M = new Movement();
    StackPane Bar = new StackPane();
    ParallelTransition user = new ParallelTransition();
    ParallelTransition pass = new ParallelTransition();
    ParallelTransition back = new ParallelTransition();
    ParallelTransition intro = new ParallelTransition();
    SmallDialog False;
    SmallDialog Empty;
    SmallDialog Valid;
    public String[] Captioning = {"Do you want to be a CUSTOMER?","So, a PROVIDER, then?","How about, a DISTRIBUTOR?"};
    public String[] Typer = {"Customer","Provider","Distributor"};
    public SmallDialog[] ActorMaker = new SmallDialog[4];
    public Login(double L, double H, StackPane root, DataFile F) {
        // Gear Setup
        Gear = new Circle(H/3);
        Gear.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("Gear.png"))));
        // Top Setup
        Top = new SmallButton(L/4,H/12, "Exit", "Title");
        Top.control.setOnAction((ActionEvent ae_top) -> {
            switch(Top.control.getText()) {
                case "Login":
                    if(Pass.input.getText().equals(A.Password)) {
                        User.setVisible(false);
                        Pass.setVisible(false);
                        Title.control.setText("Welcome");
                        Title.setVisible(true);
                        next.play();
                    }
                    else {
                        Valid = new SmallDialog(L,H,"What's up, "+A.Username+"?\nYou entered the wrong password.\nMaybe you forgot it?","Yes","No");
                        Valid.next.control.setOnAction((ActionEvent ae_go_valid) -> {root.getChildren().remove(root.getChildren().size()-1);});
                        Valid.back.control.setOnAction((ActionEvent ae_no_valid) -> {root.getChildren().remove(root.getChildren().size()-1);});
                        root.getChildren().add(Valid);
                    }
                break;
                case "Exit":
                    Platform.exit();
                break;
            }
        });
        Top.setVisible(false);
        // Low Setup
        Low = new SmallButton(L/4,H/12, "Enter", "Title");
        Low.setVisible(false);
        // Title Setup
        Title = new SmallButton(L/3, H/12, "Eng Eng Energy", "Texter");
        Title.control.setOnAction((ActionEvent ae_title) -> {
            Top.setVisible(true);
            Low.setVisible(true);
            User.setVisible(true);
            Title.setVisible(false);
            User.input.requestFocus();
            user.play();
        });
        Title.control.setOnMouseEntered((MouseEvent me_in_title) -> {
            Title.control.setText("Click to Login");
            M.Size(Gear, 300, 1, 0.5, 0.5, false).play();
            M.Turn(Gear, 4000, INDEFINITE, -360, false).play();
        });
        Title.control.setOnMouseExited((MouseEvent me_ex_title) -> {
            Title.control.setText("Eng Eng Energy");
            M.Size(Gear, 300, 1, -0.5, -0.5, false).play();
            M.Turn(Gear, 4000, INDEFINITE, 360, false).play();
        });
        // User Setup
        User = new SmallTexter(L/3, H/12, "Type your username");
        User.setVisible(false);
        // Pass Setup
        Pass = new SmallCrypto(L/3, H/12, "Type your password");
        Pass.setVisible(false);
        // Rite Setup
        Left = new SmallHeader(L/5, H/7, "","Texter");
        // Left Setup
        Rite = new SmallHeader(L/5, H/7, "","Texter");
        // Compile Bar
        Bar.setMaxSize(L/2.5+L/4, H/7);
        Bar.getChildren().addAll(Rite,Left,Top,Title,User,Pass,Low);
        // Compile
        setPrefSize(L,H);
        setMaxSize(L,H);
        getChildren().addAll(Gear,Bar);
        root.getChildren().add(this);
        // Intro animation setup
        intro.getChildren().addAll(
            M.Move(Rite, 1000, 1, L/10+L/6-H/12, 0, false),
            M.Move(Left, 1000, 1, -(L/10+L/6-H/12), 0, false),
            M.Turn(Gear, 4000, INDEFINITE, 360, true),
            M.Fade(Title, 1000, 1, 0.0, 1.0, false)
        );
        // User intro setup
        user.getChildren().addAll(
            M.Move(Gear, 900, 1, -L/4, 0, false),
            M.Move(Bar, 900, 1, L/8, 0, false),
            M.Move(Top, 900, 1, 0, -H/6, false),
            M.Move(Low, 900, 1, 0, H/6, false)
        );
        // Pass intro setup
        pass.getChildren().addAll(
            M.Move(Gear, 900, 1, L/2, 0, false),
            M.Move(Bar, 900, 1, -L/4, 0, false),
            M.Move(Top, 900, 1, 0, H/3, false),
            M.Move(Low, 900, 1, 0, -H/3, false)
        );
        // Back intro setup
        back.getChildren().addAll(
            M.Move(Gear, 900, 1, -L/2, 0, false),
            M.Move(Bar, 900, 1, L/4, 0, false),
            M.Move(Top, 900, 1, 0, -H/3, false),
            M.Move(Low, 900, 1, 0, H/3, false)
        );
    }
}
