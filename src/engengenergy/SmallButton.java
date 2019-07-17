package engengenergy;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SmallButton extends StackPane {
    Button control;
    Cell cell;
    Rectangle square;
    SmallButton(double length, double height, String title, String type) {
        // Button design setup
        control = new Button(title);
        control.setAlignment(Pos.CENTER);
        control.setPrefSize(length, height);
        control.setId("SmallButton");
        // Stack setup
        setPrefSize(length, height);
        setMaxSize(length, height);
        setAlignment(Pos.CENTER);
        // Cell setup
        cell = new Cell(length,height,type);
        getChildren().addAll(cell,control);
    }
    SmallButton(Rectangle box, String title, String type) {
        // Button design setup
        control = new Button(title);
        control.setAlignment(Pos.CENTER);
        control.setPrefSize(box.getWidth(), box.getHeight());
        control.setId("SmallButton");
        // Box setup
        box.setId(type);
        square = box;
        // Stack setup
        setPrefSize(box.getWidth(), box.getHeight());
        setMaxSize(box.getWidth(), box.getHeight());
        setAlignment(Pos.CENTER);
        getChildren().addAll(square,control);
    }
}
