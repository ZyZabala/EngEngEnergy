package engengenergy;

import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Trade extends BorderPane {
    ArrayList<String> log = new ArrayList<>();
    Head head;
    Foot foot;
    StackPane body = new StackPane();
    Trade(double L, double H, Actor A, StackPane root) {
        // Head setup
        head = new Head(L,H,A);
        setTop(head);
        // Foot setup
        foot = new Foot(L,H);
        setBottom(foot);
        // Body setup
        body.setPrefSize(L, H-H/4);
        body.setMaxSize(L, H-H/4);
        setCenter(body);
        // Compile
        root.getChildren().addAll(this);
    }
    void log(String info) {
        String all = "";
        log.add(info+"\n");
        for(int i=log.size()-1; i>=0; i--) all+=log.get(i);
        foot.Informant.setText(all);
    }
}
