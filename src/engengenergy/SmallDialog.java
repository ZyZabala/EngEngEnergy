package engengenergy;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SmallDialog extends StackPane {
    SmallButton next, back;
    Movement M = new Movement();
    SmallDialog(double length, double height, String message, String n, String b) {
        // Stack setup
        setAlignment(Pos.CENTER);
        setPrefSize(length,height);
        setId("Header");
        // Set Setup
        Rectangle set = new Rectangle();
        set.setWidth(length);
        set.setHeight(height);
        set.setId("Title");
        set.setBlendMode(BlendMode.SOFT_LIGHT);
        // Box setup
        Rectangle box = new Rectangle();
        box.setWidth(length/20);
        box.setHeight(height/20);
        box.setId("Texter");
        // Message setup
        Label notif = new Label(message);
        notif.setId("SmallTexter");
        notif.setWrapText(true);
        notif.setPrefSize(length/2.5,height/2.5);
        notif.setMaxSize(length/2.5,height/2.5);
        notif.setAlignment(Pos.CENTER);
        // Compile 
        getChildren().addAll(set,box,notif);
        // Button and Animation setup
        if (b==null) half(length/6,height/12,n);
        else dual(length/6,height/12,n,b);
        // Animate Boxes
        M.Size(box, 500, 1, 10, 10, false).play();
    }
    private void dual(double l, double h, String n, String b) {
        // Buttons setup
        next = new SmallButton(l,h,n,"Title");
        back = new SmallButton(l,h,b,"Title");
        // Compile
        getChildren().addAll(next,back);
        // Animate
        M.Move(next, 500, 1, -l-h, l-h/3, false).play();
        M.Move(back, 500, 1, l+h, l-h/3, false).play();
    }
    private void half(double l, double h, String n) {
        // Buttons setup
        next = new SmallButton(l,h,n,"Title");
        // Compile
        getChildren().add(next);
        // Animate;
        M.Move(next, 500, 1, 0, l-h/3, false).play();
    }
}
