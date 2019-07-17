package engengenergy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AreaSelect extends VBox {
    SmallButton Title;
    SmallButton Create;
    SmallTexter Option;
    Place P;
    ArrayList<SmallButton> items = new ArrayList<>();
    ArrayList<String> Options = new ArrayList<>();
    String[] locator = {"Region","City","Town","District","Street"};
    String[] located;
    Movement M = new Movement();
    private final boolean[] flags;
    public AreaSelect(double L, double H, DataFile F, String[] S, int type) throws IOException {
        flags = new boolean[]{false, false, false, false, false, false, true};
        located = S;
        if(type<5) {
            Title = new SmallButton(L/3,H/12,"Select your "+locator[type],"Title");
            Title.control.setOnMouseEntered((MouseEvent me_in_area) -> {Title.control.setText("Click here to go back");});
            Title.control.setOnMouseExited((MouseEvent me_ex_area) -> {Title.control.setText("Select your "+locator[type]);});
            // Object Declarations
            HBox writer = new HBox();
            ScrollPane window = new ScrollPane();
            GridPane list = new GridPane();
            Create = new SmallButton(L/3,H/12,"Select this "+locator[type],"Title");
            Option = new SmallTexter(L-L/3,H/12,"If you can't find your "+locator[type]+" type it here");
            // Region Writer Setup
            writer.setPrefSize(L, H/12);
            writer.setMaxSize(L, H/12);
            writer.getChildren().addAll(Option,Create);
            M.Move(Option,1000,1,H/24,0,false).play();
            M.Move(Create,1000,1,-H/24,0,false).play();
            // Get All Unique Options
            switch (type) {
                case 0:
                    F.places.forEach((p) -> {Options.add(p.Region);});
                break;
                case 1:
                    F.places.forEach((p) -> {if(p.Region.equals(located[0])) Options.add(p.City);});
                break;
                case 2:
                    F.places.forEach((p) -> {if(p.Region.equals(located[0]) && p.City.equals(located[1])) Options.add(p.Town);});
                break;
                case 3:
                    F.places.forEach((p) -> {if(p.Region.equals(located[0]) && p.City.equals(located[1]) && p.Town.equals(located[2])) Options.add(p.District);});
                break;
                case 4:
                    F.places.forEach((p) -> {if(p.Region.equals(located[0]) && p.City.equals(located[1]) && p.Town.equals(located[2]) && p.District.equals(located[3])) Options.add(p.Street);});
                break;
            }
            ArrayList<String> Uniques = (ArrayList<String>) Options.stream().distinct().collect(Collectors.toList());
            // Assign unique regions to smallbuttons
            for(int i=0; i<Uniques.size(); i++) {
                items.add(new SmallButton(L+H/6,H/12,Uniques.get(i),"Header"));
                list.add(items.get(i), 0, i);
            }
            // Grid Setup
            list.setAlignment(Pos.CENTER);
            // Scroll Setup
            window.setMaxSize(L, H-H/12-H/6-H/12-H/12);
            window.setContent(list);
            window.setHvalue(0.5);
            window.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            window.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            window.setPannable(true);
            // Compile
            getChildren().addAll(Title,window,writer);
            setAlignment(Pos.CENTER);
        }
        else {
            P = new Place("None",F.makeID("Place"),located);
            F.places.add(P);
            F.Save("Place");
        }
    }
}
