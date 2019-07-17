package engengenergy;

public class Place extends Object {
    String Owner;
    String Street;
    String District;
    String Town;
    String City;
    String Region;
    String ID;
    
    // Initializer
    public Place(String o, String i) {
        Owner = o;
        ID = i;
    }
    // Constructor from File
    public Place(String[] data) {
        Owner = data[0];
        Street = data[1];
        District = data[2];
        Town = data[3];
        City = data[4];
        Region = data[5];
        ID = data[6];
    }
    // Constructor from Application
    public Place(String o, String i, String[] a) {
        Owner = o;
        Street = a[4];
        District = a[3];
        Town = a[2];
        City = a[1];
        Region = a[0];
        ID = i;
    }
}
