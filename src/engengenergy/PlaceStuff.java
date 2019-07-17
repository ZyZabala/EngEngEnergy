package engengenergy;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class PlaceStuff extends StackPane {
    public PlaceStuff(double L, double H, Place P, DataFile F) {
        // Grid setup
        GridPane G = new GridPane();
        G.setPrefSize(L/3-24, H-H/4-H/6);
        G.setMinSize(L/3-24, H-H/4-H/6);
        G.setMaxSize(L/3-24, H-H/4-H/6);
        // Array Setup
        ArrayList<SmallHeader> data = new ArrayList<>();
        String[] text = {"Resident","Place ID","Region","City","Town","District","Street",};
        ArrayList<SmallHeader> info = new ArrayList<>();
        if(F.FindActor(P.ID)!=null) data.add(new SmallHeader(new Rectangle(L/3-L/8,H/12),F.FindActor(P.ID).Username,"Header"));
        else data.add(new SmallHeader(new Rectangle(L/3-L/8,H/12),"Nobody","Header"));
        data.add(new SmallHeader(new Rectangle(L/3-L/8,H/12),P.ID,"Header"));
        data.add(new SmallHeader(new Rectangle(L/3-L/8,H/12),P.Region,"Header"));
        data.add(new SmallHeader(new Rectangle(L/3-L/8,H/12),P.City,"Header"));
        data.add(new SmallHeader(new Rectangle(L/3-L/8,H/12),P.Town,"Header"));
        data.add(new SmallHeader(new Rectangle(L/3-L/8,H/12),P.District,"Header"));
        data.add(new SmallHeader(new Rectangle(L/3-L/8,H/12),P.Street,"Header"));
        // Compile
        getChildren().add(G);
        // Compile grid
        for(int i=0; i<text.length; i++) {
            info.add(new SmallHeader(new Rectangle(L/8-24,H/12), text[i], "Title"));
            G.add(info.get(i), 0, i);
            G.add(data.get(i), 1, i);
        }
    }
}
