package engengenergy;

import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Root extends StackPane {
    Rectangle Plexus = new Rectangle();
    Rectangle Filter = new Rectangle();
    public Root(double L, double H) {
        // Plexus setup
        Plexus.setWidth(L);
        Plexus.setHeight(H);
        Plexus.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("Plexus.gif"))));
        // Filter setup
        Filter.setWidth(L);
        Filter.setHeight(H);
        Filter.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("Filter.png"))));
        Filter.setBlendMode(BlendMode.SCREEN);
        Filter.setOpacity(0.7);
        // Compile
        getChildren().addAll(Plexus,Filter);
    }
    public StackPane Design() {
        getChildren().clear();
        return this;
    }
}
