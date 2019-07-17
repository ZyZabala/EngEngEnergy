package engengenergy;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Distribute extends StackPane {
    Paper paper;
    Plant plant;
    Place place;
    ArrayList<SmallButton> Demand = new ArrayList<>();
    ArrayList<SmallButton> Supply = new ArrayList<>();
    ArrayList<PhotoButton> DemandGet = new ArrayList<>();
    ArrayList<SmallButton> SupplyGet = new ArrayList<>();
    SmallButton Papers;
    SmallButton Interface;
    SmallHeader DemandHead;
    SmallHeader SupplyHead;
    StackPane Displayer = new StackPane();
    Movement M = new Movement();
    PaperStuff paperview;
    PlantStuff plantview;
    PlaceStuff placeview;
    Distribute(double L, double H, DataFile F, Actor A) {
        paper = new Paper(F.makeID("Paper"));
        // Contract viewer setup
        Papers = new SmallButton(L+H/12,H/12,"View All Contracts","Header");
        // Title setups
        SupplyHead = new SmallHeader(L/3+H/12,H/12,"Power Plants","Texter");
        DemandHead = new SmallHeader(L/3+H/12,H/12,"Energy Demands","Texter");
        Interface = new SmallButton(L/3+H/12,H/12,"Distribution Data","Title");
        // Demand Grid
        GridPane demandlist = new GridPane();
        demandlist.setAlignment(Pos.CENTER);
            // Demand array setup
            for(int i=0; i<F.places.size(); i++) {
                Place P = F.places.get(i);
                if(F.places.get(i).Owner.equals(A.ID)) {
                    Demand.add(new SmallButton(new Rectangle(L/3-H/12,H/12),P.City+", "+P.Region,"Title"));
                    DemandGet.add(new PhotoButton(H/12,H/12,"","Locator.png"));
                    int ii = Demand.size()-1;
                    demandlist.add(Demand.get(ii), 0, ii);
                    demandlist.add(DemandGet.get(ii), 1, ii);
                    SmallButton D = Demand.get(ii);
                    D.control.setOnMouseEntered((MouseEvent me_in_dget)->{D.control.setText(P.ID);});
                    D.control.setOnMouseExited((MouseEvent me_ex_dget)->{D.control.setText(P.City+", "+P.Region);});
                    DemandGet.get(ii).control.setOnMouseEntered(D.control.getOnMouseEntered());
                    DemandGet.get(ii).control.setOnMouseExited(D.control.getOnMouseExited());
                    Demand.get(ii).control = D.control;
                }
            }
            // Scroll setup
            ScrollPane demandview = new ScrollPane();
            demandview.setPrefSize(L/3+12, H-H/4-H/6);
            demandview.setMinSize(L/3+12, H-H/4-H/6);
            demandview.setMaxSize(L/3+12, H-H/4-H/6);
            demandview.setContent(demandlist);
            demandview.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            demandview.setHvalue(0.5);
        // Supply Grid
        GridPane supplylist = new GridPane();
        supplylist.setAlignment(Pos.CENTER);
            // Supply array setup
            for(int i=0; i<F.plants.size(); i++) {
                Supply.add(new SmallButton(new Rectangle(L/3-H/7,H/12),F.plants.get(i).Name,"Title"));
                SupplyGet.add(new SmallButton(new Rectangle(H/7,H/12),"Request","Texter"));
                int ii= Supply.size()-1;
                Supply.get(i).square.setId(F.plants.get(i).Type);
                supplylist.add(SupplyGet.get(i), 0, i);
                supplylist.add(Supply.get(i), 1, i);
            }
            // Scroll setup
            ScrollPane supplyview = new ScrollPane();
            supplyview.setPrefSize(L/3+12, H-H/4-H/6);
            supplyview.setMinSize(L/3+12, H-H/4-H/6);
            supplyview.setMaxSize(L/3+12, H-H/4-H/6);
            supplyview.setContent(supplylist);
            supplyview.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            supplyview.setHvalue(0.5);
            supplyview.setVvalue(1);
        // Displayer
        Displayer.setPrefSize(L/3, H-H/4-H/6);
        Displayer.setMinSize(L/3, H-H/4-H/6);
        Displayer.setMaxSize(L/3, H-H/4-H/6);
        // Compile
        setPrefSize(L,H-H/12-H/6);
        setMaxSize(L,H-H/12-H/6);
        setMinSize(L,H-H/12-H/6);
        getChildren().addAll(Displayer,demandview,supplyview,Papers,SupplyHead,DemandHead,Interface);
        // Animate
        M.Move(Papers, 1000, 1, 0, H/2-H/6, false).play();
        M.Move(Interface, 1000, 1, 0, -H/2+H/6, false).play();
        M.Move(DemandHead, 1000, 1, -L/2+L/6, -H/2+H/6, false).play();
        M.Move(SupplyHead, 1000, 1, L/2-L/6, -H/2+H/6, false).play();
        M.Move(demandview, 1000, 1, -L/2+L/6+6, 0, false).play();
        M.Move(supplyview, 1000, 1, L/2-L/6-6, 0, false).play();
        M.Turn(supplyview, 1000, 1, 180, false).play();
        M.Turn(supplylist, 1000, 1, 180, false).play();
    }
    
    void update(double L, double H, DataFile F) {
    }
    Place placeview(double L, double H, Place P, DataFile F) {
        placeview = new PlaceStuff(L,H,P,F);
        if(Displayer.getChildren().size()>0) Displayer.getChildren().clear();
        Displayer.getChildren().add(placeview);
        return P;
    }
    Plant plantview(double L, double H, Plant P, DataFile F) {
        plantview = new PlantStuff(L,H,P,F);
        if(Displayer.getChildren().size()>0) Displayer.getChildren().clear();
        Displayer.getChildren().add(plantview);
        return P;
    }
    Paper paperview(double L, double H, Plant T, Place P, Paper A, DataFile F) {
        paperview = new PaperStuff(L,H,plant,place,paper,F);
        if(Displayer.getChildren().size()>0) Displayer.getChildren().clear();
        Displayer.getChildren().add(paperview);
        return A;
    }
}
