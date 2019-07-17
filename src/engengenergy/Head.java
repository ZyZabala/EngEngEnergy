package engengenergy;

import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.ParallelTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Head extends StackPane {
    PhotoButton gear;
    PhotoButton user;
    SmallHeader welcome;
    SmallHeader typical;
    Movement M = new Movement();
    ParallelTransition intro = new ParallelTransition();
    Head(double L, double H, Actor A) {
        // Box setup
        Rectangle box = new Rectangle(L,H/6);
        box.setId("Texter");
        // Welcome setup
        welcome = new SmallHeader(L/3,H/12,"Welcome, "+A.Username,"Header");
        // Typical setup
        typical = new SmallHeader(L/5,H/18,A.Usertype,"Texter");
        // User setup
        user = new PhotoButton(H/7,H/7,"",A.Picture);
        // Gear setup
        gear = new PhotoButton(H/7,H/7,"⛭","Gear.png");
        gear.control.setId("SmallButton");
        // Stack setup
        setPrefSize(L, H/12);
        setMaxSize(L, H/12);
        getChildren().addAll(box,user,gear,welcome,typical);
        // Animation
        intro.getChildren().addAll(
            M.Move(user, 1000, 1, -L/2+H/12, 0, false),
            M.Move(gear, 1000, 1, L/2-H/12, 0, false),
            M.Turn(gear.picture, 3000, INDEFINITE, 360, true),
            M.Move(welcome, 1000, 1, 0, -H/28, false),
            M.Move(typical, 1000, 1, 0, H/28, false)
        );
    }
}
