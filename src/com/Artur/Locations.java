package com.Artur;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
//        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")));){
//            for (Location location : locations.values()) {
//                locFile.writeInt(location.getLocationID()); //1
//                locFile.writeUTF(location.getDescription()); //2 //to write a String
//
//                System.out.println("Writing location " + location.getDescription() + " : " + location.getDescription());
//                System.out.println("Writing " + (location.getExits().size() - 1) + " exits.");
//
//                locFile.writeInt(location.getExits().size() - 1); //3
//                for (String direction : location.getExits().keySet()) {
//                    if (!direction.equalsIgnoreCase("Q")) {
//                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
//                        locFile.writeUTF(direction); //4
//                        locFile.writeInt(location.getExits().get(direction)); //5
//                    }
//                }
//            }
//        }
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")));) {
            for(Location location : locations.values()){
                locFile.writeObject(location);
            }
        }
    }

    static {

        try(ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
            boolean eof = false;    //eof = End Of File
            while(!eof){
                try {
                    Location location = (Location) locFile.readObject();
                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
                    System.out.println("Found " + location.getExits().size() + " exits");

                    locations.put(location.getLocationID(), location);
                }catch(EOFException e){
                    eof = true;
                }
            }
        }catch (IOException io){
            System.out.println("IO exception " + io.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException " +e.getMessage());
        }


//-------------------------------------------------------------------------------------------------
//        try(DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
//            boolean eof = false;    //eof = End Of File
//            while(!eof){
//                try{
//                    Map<String, Integer> exits = new LinkedHashMap<>();
//                    int locID = locFile.readInt();  //1
//                    String description = locFile.readUTF(); //2
//                    int numExits = locFile.readInt();   //3
//                    System.out.println("Read location:" + locID + " : " + description);
//                    System.out.println("Found " + numExits + " exits.");
//                    for(int i=0; i<numExits; i++){
//                        String direction = locFile.readUTF();   //4
//                        int destination = locFile.readInt();    //5
//                        exits.put(direction, destination);
//                        System.out.println("\t\t" + direction + "," + description);
//                    }
//                    locations.put(locID, new Location(locID, description, exits));
//                } catch (EOFException e){
//                    eof = true;
//                }
//            }
//        }catch (IOException io){
//            System.out.println("IO exception");
//
//        }
//-------------------------------------------------------------------------------------------------
//        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {
//            scanner.useDelimiter(",");
//            while(scanner.hasNextLine()) {
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                System.out.println("Imported loc: " + loc + ": " + description);
//                Map<String, Integer> tempExit = new HashMap<>();
//                locations.put(loc, new Location(loc, description, tempExit));
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//
//        // Now read the exits
//        try(BufferedReader dirFiles = new BufferedReader(new FileReader("directions_big.txt"))) {
//            String input;
//            while((input = dirFiles.readLine()) != null) {
//                String[] data = input.split(",");
//                int loc = Integer.parseInt(data[0]);
//                String direction = data[1];
//                int destination = Integer.parseInt(data[2]);
//
//                System.out.println(loc + ": " + direction + ": " + destination);
//                Location location = locations.get(loc);
//                location.addExit(direction, destination);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}


