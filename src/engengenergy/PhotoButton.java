package engengenergy;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PhotoButton extends StackPane {
    Button control;
    Rectangle picture;
    PhotoButton(double L, double H, String title, String photo) {
        // Image setup
        picture = new Rectangle(L,H);
        picture.setFill(new ImagePattern(new Image(getClass().getResourceAsStream(photo))));
        // Button setup
        control = new Button(title);
        control.setId("PhotoButton");
        control.setPrefSize(L,H);
        control.setMaxSize(L,H);
        control.setAlignment(Pos.CENTER);
        control.setPickOnBounds(true);
        // Stack setup
        setPrefSize(L,H);
        setMaxSize(L,H);
        getChildren().addAll(picture,control);
    }
}
