package engengenergy;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SmallTexter extends StackPane {
    TextField input;
    Rectangle square;
    Cell cell;
    SmallTexter(double length, double height, String title) {
        // Input design setup
        input = new TextField();
        input.setAlignment(Pos.CENTER);
        input.setPrefSize(length, height);
        input.setPromptText(title);
        input.setId("SmallTexter");
        // Cell setup
        cell = new Cell(length,height,"Texter");
        // Stack setup
        setPrefSize(length, height);
        setMaxSize(length, height);
        setAlignment(Pos.CENTER);
        getChildren().addAll(cell,input);
    }
    SmallTexter(Rectangle box, String title) {
        // Text design setup
        input = new TextField();
        input.setAlignment(Pos.CENTER);
        input.setPrefSize(box.getWidth(), box.getHeight());
        input.setId("SmallTexter");
        // Box setup
        square = box;
        box.setId("Texter");
        // Stack setup
        setPrefSize(box.getWidth(), box.getHeight());
        setMaxSize(box.getWidth(), box.getHeight());
        setAlignment(Pos.CENTER);
        getChildren().addAll(square,input);
    }
}
