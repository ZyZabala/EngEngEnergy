package engengenergy;

import javafx.scene.shape.Polygon;

public class Cell extends Polygon {
    Cell(double length, double height, String name) {
        getPoints().addAll(
            height/2, 0.0,
            length-height/2, 0.0,
            length, height/2,
            length-height/2, height,
            height/2, height,
            0.0, height/2
        );
        setId(name);
    }
}
