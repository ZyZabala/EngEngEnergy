package engengenergy;

import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;

public class SmallCrypto extends StackPane {
    PasswordField input;
    SmallCrypto(double length, double height, String title) {
        // Stack setup
        setPrefSize(length, height);
        setMaxSize(length, height);
        setAlignment(Pos.CENTER);
        // Input design setup
        input = new PasswordField();
        input.setAlignment(Pos.CENTER);
        input.setPrefSize(length, height);
        input.setPromptText(title);
        input.setId("SmallTexter");
        // Compile
        getChildren().addAll(new Cell(length,height,"Title"),input);
    }
}
