package engengenergy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class DataFile {
    // Time objects
    String Moment="";
    Calendar C = Calendar.getInstance();
    SimpleDateFormat Simple = new SimpleDateFormat("HH:mm:ss EEEE || MMMM dd, yyyy");
    // Object List
    ArrayList<Plant> plants;
    ArrayList<Place> places;
    ArrayList<Actor> actors;
    ArrayList<Paper> papers;
    // File Headers
    final String[] filehead = {
        "Username♦♥♠♣Password♦♥♠♣Credcard♦♥♠♣Usertype♦♥♠♣Picture♦♥♠♣Contact♦♥♠♣Address♦♥♠♣Registry♦♥♠♣Timedout♦♥♠♣Revenues♦♥♠♣Costings♦♥♠♣Networth♦♥♠♣ID\n",
        "Owner♦♥♠♣Street♦♥♠♣District♦♥♠♣Town♦♥♠♣City♦♥♠♣Region♦♥♠♣ID\n",
        "Owner♦♥♠♣Name♦♥♠♣Model♦♥♠♣Type♦♥♠♣Address♦♥♠♣Established♦♥♠♣Status♦♥♠♣Rate♦♥♠♣Capacity♦♥♠♣CurrentCost♦♥♠♣UtilityCost♦♥♠♣ID\n",
        "Customer♦♥♠♣Provider♦♥♠♣Payments♦♥♠♣Kilowatt♦♥♠♣Duration♦♥♠♣PostDate♦♥♠♣LoadDate♦♥♠♣ID\n"
    };
    // File Names
    final String[] filename = {"Actor","Place","Plant","Paper"};
    // Constructor
    DataFile(ArrayList<Plant> plant, ArrayList<Place> place, ArrayList<Actor> actor, ArrayList<Paper> paper) {
        plants = plant;
        places = place;
        actors = actor;
        papers = paper;
    }
    // File Reader
    void Read(String line, String file) throws FileNotFoundException, IOException {
        if(file.equals("Files")) for(int i=0; i<4; i++) Read(line,filename[i]);
        else {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("src\\"+EngEngEnergy.class.getSimpleName()+"\\"+file+".dat").getAbsolutePath()))) {
                reader.readLine();
                if(file.equals("Plant")) while ((line = reader.readLine()) != null) plants.add(new Plant(line.split("♦♥♠♣")));
                if(file.equals("Place")) while ((line = reader.readLine()) != null) places.add(new Place(line.split("♦♥♠♣")));
                if(file.equals("Actor")) while ((line = reader.readLine()) != null) actors.add(new Actor(line.split("♦♥♠♣")));
                if(file.equals("Paper")) while ((line = reader.readLine()) != null) papers.add(new Paper(line.split("♦♥♠♣")));
            }
        }
    }
    void Save(String file) throws IOException {
        if(file.equals("Files")) for(int i=0; i<4; i++) Save(filename[i]);
        else {
            new File("src\\"+EngEngEnergy.class.getSimpleName()+"\\"+file+".dat").delete();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src\\"+EngEngEnergy.class.getSimpleName()+"\\"+file+".dat").getAbsolutePath()))) {
                for(int i=0; i<4; i++) if(file.equals(filename[i])) writer.append(filehead[i]);
                switch(file) {
                    case "Actor":
                        if(actors.size()>0) {
                            for (Actor p : actors) {
                                writer.append(p.Username+"♦♥♠♣");
                                writer.append(p.Password+"♦♥♠♣");
                                writer.append(p.Credcard+"♦♥♠♣");
                                writer.append(p.Usertype+"♦♥♠♣");
                                writer.append(p.Picture+"♦♥♠♣");
                                writer.append(p.Contact+"♦♥♠♣");
                                writer.append(p.Address+"♦♥♠♣");
                                writer.append(p.Registry+"♦♥♠♣");
                                writer.append(p.Timedout+"♦♥♠♣");
                                writer.append(p.Revenues+"♦♥♠♣");
                                writer.append(p.Costings+"♦♥♠♣");
                                writer.append(p.Networth+"♦♥♠♣");
                                writer.append(p.ID+"\n");
                            }
                        }
                        actors.clear();
                    break;
                    case "Paper":
                        if(papers.size()>0) {
                            for (Paper p : papers) {
                                writer.append(p.Customer+"♦♥♠♣");
                                writer.append(p.Provider+"♦♥♠♣");
                                writer.append(p.Payments+"♦♥♠♣");
                                writer.append(p.Kilowatt+"♦♥♠♣");
                                writer.append(p.Duration+"♦♥♠♣");
                                writer.append(p.PostDate+"♦♥♠♣");
                                writer.append(p.LoadDate+"♦♥♠♣");
                                writer.append(p.ID+"\n");
                            }
                        }
                        papers.clear();
                    break;
                    case "Plant":
                        if(plants.size()>0) {
                            for (Plant p : plants) {
                                writer.append(p.Owner+"♦♥♠♣");
                                writer.append(p.Name+"♦♥♠♣");
                                writer.append(p.Model+"♦♥♠♣");
                                writer.append(p.Type+"♦♥♠♣");
                                writer.append(p.Address+"♦♥♠♣");
                                writer.append(p.Established+"♦♥♠♣");
                                writer.append(p.Status+"♦♥♠♣");
                                writer.append(p.Rate+"♦♥♠♣");
                                writer.append(p.Capacity+"♦♥♠♣");
                                writer.append(p.CurrentCost+"♦♥♠♣");
                                writer.append(p.UtilityCost+"♦♥♠♣");
                                writer.append(p.ID+"\n");
                            }
                        }
                        plants.clear();
                    break;
                    case "Place":
                        if(places.size()>0) {
                            for (Place p : places) {
                                writer.append(p.Owner+"♦♥♠♣");
                                writer.append(p.Street+"♦♥♠♣");
                                writer.append(p.District+"♦♥♠♣");
                                writer.append(p.Town+"♦♥♠♣");
                                writer.append(p.City+"♦♥♠♣");
                                writer.append(p.Region+"♦♥♠♣");
                                writer.append(p.ID+"\n");
                            }
                        }
                        places.clear();
                    break;
                }
            }
            Read("",file);
        }
    }
    Actor FindActor(String u) {
        for (Actor a : actors) {
            if(a.Username.equals(u)) return a;
            else if(a.ID.equals(u)) return a;
            else if(a.Address.equals(u)) return a;
        }
        return null;
    }
    Paper FindPaper(String u) {
        for (Paper a : papers) if(a.ID.equals(u)) return a;
        return null;
    }
    Plant FindPlant(String u) {
        for (Plant a : plants) if(a.ID.equals(u)) return a;
        for (Plant a : plants) if(a.Name.equals(u)) return a;
        return null;
    }
    Place FindPlace(String u, String t) {
        if(t.equals("ID")) for (Place a : places) if(a.ID.equals(u)) return a;
        if(t.equals("Region")) for (Place a : places) if(a.Region.equals(u)) return a;
        if(t.equals("City")) for (Place a : places) if(a.City.equals(u)) return a;
        if(t.equals("Town")) for (Place a : places) if(a.Town.equals(u)) return a;
        if(t.equals("District")) for (Place a : places) if(a.District.equals(u)) return a;
        if(t.equals("Street")) for (Place a : places) if(a.Street.equals(u)) return a;
        return null;
    }
    // Helper Methods
    String makeID(String array) {
        String t="", a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for(int i=0; i<10; i++) t=t+a.charAt(Math.abs((new Random().nextInt(36))));
        switch (array) {
            case "Actor": 
                for(int i=0; i<actors.size(); i++) if(actors.get(i).ID.equals(a)) makeID(array);
            break;
            case "Paper": 
                for(int i=0; i<papers.size(); i++) if(papers.get(i).ID.equals(a)) makeID(array);
            break;
            case "Plant": 
                for(int i=0; i<plants.size(); i++) if(plants.get(i).ID.equals(a)) makeID(array);
            break;
            case "Place": 
                for(int i=0; i<places.size(); i++) if(places.get(i).ID.equals(a)) makeID(array);
            break;
            default:
            break;
        }
        return t;
    }
    String toAddress(String id) {
        Place f = FindPlace(id,"ID");
        return f.Street+", "+f.District+", "+f.Town+", "+f.City+", "+f.Region;
    }
    // Update Everything Every Second
    public String Tick() throws FileNotFoundException, IOException, ParseException {
        // Create file
        File info = new File("src\\"+EngEngEnergy.class.getSimpleName()+"\\Info.dat");
        // Read File
        try (BufferedReader R = new BufferedReader(new FileReader(info))) {Moment = R.readLine();}
        // Convert String into Calendar Object
        C.setTime(Simple.parse(Moment));
        // Add 1 Hour
        C.add(Calendar.SECOND, new Random().nextInt(3600));
        // Convert Calendar Object into String
        Moment = Simple.format(C.getTime());
        // Save File
        try (BufferedWriter W = new BufferedWriter(new FileWriter(info.getAbsolutePath()))) {W.append(Moment+"\n");}
        return Moment;
    }
}
