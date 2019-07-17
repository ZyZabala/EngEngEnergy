package engengenergy;

import java.util.ArrayList;

public class Plant extends Object {
    String Owner;
    String Name;
    String Model;
    String Type;
    String Address;
    String Established;
    String Status;
    double Rate;
    double Capacity;
    double CurrentCost;
    double UtilityCost;
    String ID;
    
    // Initializer
    public Plant(String o, String i, String t) {
        ID = i;
        Owner = o;
        Type = t;
    }
    // Constructor from File
    public Plant(String[] data) {
        Owner = data[0];
        Name = data[1];
        Model = data[2];
        Type = data[3];
        Address = data[4];
        Established = data[5];
        Status = data[6];
        Rate = Double.parseDouble(data[7]);
        Capacity = Double.parseDouble(data[8]);
        CurrentCost = Double.parseDouble(data[9]);
        UtilityCost = Double.parseDouble(data[10]);
        ID = data[11];
    }
    // Curator
    public String curate(ArrayList<Paper> subs, Actor a, Actor b) {
        String s = "No credit for "+Name+"\n";
        for (Paper p : subs) { 
            a.Revenues += p.Payments;
            s = Name+" earned "+p.Payments+" from "+b.Username+"\n";
        }
        a.Costings += UtilityCost;
        return s;
    }
}
