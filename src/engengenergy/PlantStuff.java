package engengenergy;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PlantStuff extends StackPane {
    public PlantStuff(double L, double H, Plant P, DataFile F) {
        // Wall setup
        VBox V = new VBox();
        V.setAlignment(Pos.CENTER);
        V.setPrefSize(L/3-24, H-H/4-H/6);
        V.setMinSize(L/3-24, H-H/4-H/6);
        V.setMaxSize(L/3-24, H-H/4-H/6);
        // Grid setup
        GridPane G = new GridPane();
        G.setPrefSize(L/3-24, 4*H/12);
        G.setMinSize(L/3-24, 4*H/12);
        G.setMaxSize(L/3-24, 4*H/12);
        // Model setup
        Rectangle model;
        model = new Rectangle(L/3-24,H/4);
        model.setFill(new ImagePattern(new Image(getClass().getResourceAsStream(P.Model+".png"))));
        // Array setup
        String[] text = {"Name","Type/Mode","Rate (KW/Hr)","Capacity"};
        ArrayList<SmallHeader> data = new ArrayList<>();
        ArrayList<SmallHeader> info = new ArrayList<>();
        data.add(new SmallHeader(new Rectangle(L/3-L/9,H/12),P.Name,P.Type));
        data.add(new SmallHeader(new Rectangle(L/3-L/9,H/12),P.Type+": "+P.Model,P.Type));
        data.add(new SmallHeader(new Rectangle(L/3-L/9,H/12),Double.toString(P.Rate),P.Type));
        data.add(new SmallHeader(new Rectangle(L/3-L/9,H/12),Double.toString(P.Capacity),P.Type));
        // Compile
        V.getChildren().addAll(model,G);
        getChildren().add(V);
        // Compile grid
        for(int i=0; i<text.length; i++) {
            info.add(new SmallHeader(new Rectangle(L/9-24,H/12), text[i], "Header"));
            data.get(i).heading.setId("SmallTexter");
            G.add(info.get(i), 0, i);
            G.add(data.get(i), 1, i);
        }
    }
}
