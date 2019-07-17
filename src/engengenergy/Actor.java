package engengenergy;

public class Actor extends Object {
    String ID;
    String Username;
    String Password;
    String Credcard;
    String Usertype;
    String Picture;
    String Contact;
    String Address;
    String Registry;
    String Timedout;
    double Revenues;
    double Costings;
    double Networth;
    
    // Initializer
    public Actor(String i, String u) {
        ID = i;
        Username = u;
        Registry = "no";
    }
    // Constructor From File
    public Actor(String[] data) {
        Username = data[0];
        Password = data[1];
        Credcard = data[2];
        Usertype = data[3];
        Picture = data[4];
        Contact = data[5];
        Address = data[6];
        Registry = data[7];
        Timedout = data[8];
        Revenues = Double.parseDouble(data[9]);
        Costings = Double.parseDouble(data[10]);
        Networth = Double.parseDouble(data[11]);
        ID = data[12];
    }
    // Behavior
    public String logout(String now) {
        Timedout = now;
        return Username+" logged out at "+Timedout+"\n";
    }
    public String logged(String now) {
        return Username+" logged in at "+now+"\n";
    }
    public String curate() {
        Networth = Revenues-Costings;
        return Networth+" added to "+Username+" credit\n";
    }
}
