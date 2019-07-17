package engengenergy;

import java.text.SimpleDateFormat;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Foot extends StackPane {
    Label TimeClock;
    Button Simulator;
    TextArea Informant = new TextArea();
    Movement M = new Movement();
    Timeline Tick = new Timeline();
    SimpleDateFormat Simple = new SimpleDateFormat("HH:mm:ss EEEE ║ MMMM dd, yyyy");
    Foot(double L, double H) {
        // Stack setup
        setPrefSize(L, H/12);
        setMaxSize(L, H/12);
        // Rectangles setup
        Rectangle box = new Rectangle(L, H/12);
        box.setId("Texter");
        // Clock setup
        StackPane time = new StackPane();
        Rectangle block = new Rectangle(L/3, H/12);
        block.setId("Header");
        TimeClock = new Label();
        TimeClock.setPrefSize(L/3, H/12);
        TimeClock.setMaxSize(L/3, H/12);
        TimeClock.setMinSize(L/3, H/12);
        TimeClock.setAlignment(Pos.CENTER);
        TimeClock.setId("SmallHeader");
        // Informant Setup
        Informant.setText("");
        Informant.setEditable(false);
        Informant.setWrapText(true);
        Informant.setPrefSize(L-L/3-L/6, H/12);
        Informant.setMinSize(L-L/3-L/6, H/12);
        Informant.setMaxSize(L-L/3-L/6, H/12);
        Informant.setPadding(new Insets(0,0,0,10));
        Informant.setId("Informant");
        // Simlator Setup
        StackPane sim = new StackPane();
        Rectangle section = new Rectangle(L/6, H/12);
        section.setId("Title");
        Simulator = new Button("Simulator");
        Simulator.setPrefSize(L/6,H/12);
        Simulator.setMaxSize(L/6,H/12);
        Simulator.setMinSize(L/6,H/12);
        Simulator.setId("SmallButton");
        Simulator.setAlignment(Pos.CENTER);
        // Compile;
        time.getChildren().addAll(block,TimeClock);
        sim.getChildren().addAll(section,Simulator);
        HBox all = new HBox();
        all.getChildren().addAll(Informant,time,sim);
        getChildren().addAll(box,all);
    }
}
