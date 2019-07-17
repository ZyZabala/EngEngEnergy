package engengenergy;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SmallHeader extends StackPane {
    Label heading;
    SmallHeader(double length, double height, String title, String type) {
        // Label setup
        heading = new Label(title);
        heading.setAlignment(Pos.CENTER);
        heading.setPrefSize(length-height, height);
        heading.setId("SmallHeader");
        // Stack setup
        setPrefSize(length,height);
        setMaxSize(length, height);
        setAlignment(Pos.CENTER);
        getChildren().addAll(new Cell(length,height,type),heading);
    }
    SmallHeader(Rectangle box, String title, String type) {
        // Label setup
        heading = new Label(title);
        heading.setAlignment(Pos.CENTER);
        heading.setPrefSize(box.getWidth(), box.getHeight());
        heading.setId("SmallHeader");
        // box setup
        box.setId(type);
        // Stack setup
        setPrefSize(box.getWidth(), box.getHeight());
        setMaxSize(box.getWidth(), box.getHeight());
        setAlignment(Pos.CENTER);
        getChildren().addAll(box,heading);
    }
}
