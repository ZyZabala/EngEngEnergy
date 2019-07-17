package engengenergy;

public class Paper extends Object {
    String Customer;
    String Provider;
    double Payments;
    double Kilowatt;
    double Duration;
    String PostDate;
    String LoadDate;
    String ID;
    
    // Initializer
    public Paper(String i) {
        ID = i;
    }
    // Constructor from File
    public Paper(String[] data) {
        Customer = data[0];
        Provider = data[1];
        Payments = Double.parseDouble(data[2]);
        Kilowatt = Double.parseDouble(data[3]);
        Duration = Double.parseDouble(data[4]);
        PostDate = data[5];
        LoadDate = data[6];
        ID = data[7];
    }
    // Curator
    public String curate(double r, Actor a) {
        Payments = Kilowatt*r;
        Duration--;
        return "Current bill of "+a.Username+" is "+Payments+"\n";
    }
}
