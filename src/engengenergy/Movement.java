package engengenergy;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Movement {
    Movement(){}
    public TranslateTransition Move(Node n, int time, int cycle, double x, double y, boolean g) {
        TranslateTransition m = new TranslateTransition(Duration.millis(time), n);
        m.setCycleCount(cycle);
        m.setByX(x);
        m.setByY(y);
        m.setAutoReverse(g);
        return m;
    }
    public FadeTransition Fade(Node n, int time, int cycle, double from, double to, boolean x) {
        FadeTransition f = new FadeTransition(Duration.millis(time), n);
        f.setCycleCount(cycle);
        f.setFromValue(from);
        f.setToValue(to);
        f.setAutoReverse(x);
        return f;
    }
    public ScaleTransition Size(Node n, int time, int cycle, double x, double y, boolean g) {
        ScaleTransition s = new ScaleTransition(Duration.millis(time), n);
        s.setCycleCount(cycle);
        s.setByX(x);
        s.setByY(y);
        s.setAutoReverse(g);
        return s;
    }
    public RotateTransition Turn(Node n, int time, int cycle, double angle, boolean x) {
        RotateTransition r = new RotateTransition(Duration.millis(time), n);
        r.setCycleCount(cycle);
        r.setByAngle(angle);
        r.setAutoReverse(x);
        return r;
    }
    
}