package engengenergy;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class PaperStuff extends StackPane {
    SmallButton distributor;
    public PaperStuff(double L, double H, Plant T, Place P, Paper A, DataFile F) {
        // Wall setup
        VBox V = new VBox();
        V.setAlignment(Pos.CENTER);
        V.setPrefSize(L/3-24, H-H/4-H/6);
        V.setMinSize(L/3-24, H-H/4-H/6);
        V.setMaxSize(L/3-24, H-H/4-H/6);
        // Button setup
        distributor = new SmallButton(new Rectangle(L/3-24,H/12),"Distribute","Title");
        distributor.control.setOnAction((ActionEvent ae_distribute) -> {
        });
        // Grid setup
        GridPane G = new GridPane();
        G.setPrefSize(L/3-24, 5*H/12);
        G.setMinSize(L/3-24, 5*H/12);
        G.setMaxSize(L/3-24, 5*H/12);
        // Array setup
        String[] text = {"Place ID","Plant ID","Rate","Duration","Requested"};
        ArrayList<StackPane> data = new ArrayList<>();
        ArrayList<SmallHeader> info = new ArrayList<>();
        data.add(new SmallHeader(new Rectangle(L/3-L/9,H/12),P.ID,"Header"));
        data.add(new SmallHeader(new Rectangle(L/3-L/9,H/12),T.ID,"Header"));
        data.add(new SmallHeader(new Rectangle(L/3-L/9,H/12),Double.toString(T.Rate),"Header"));
        data.add(new SmallTexter(new Rectangle(L/3-L/9,H/12),"Type duration here"));
        data.add(new SmallHeader(new Rectangle(L/3-L/9,H/12),A.PostDate,"Header"));
        // compile
        V.getChildren().addAll(G,distributor);
        getChildren().add(V);
        // Compile grid
        for(int i=0; i<text.length; i++) {
            info.add(new SmallHeader(new Rectangle(L/9-24,H/12), text[i], "Header"));
            G.add(info.get(i), 0, i);
            G.add(data.get(i), 1, i);
        }
    }
    
}
