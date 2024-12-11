class Main {
  public static void main(String[] args) {
    // Use the instructions in Blackboard or instructions.md to complete Lab 6
    MyHashMap<String, Integer> creditHours = new MyHashMap<>();

      //insert values
    creditHours.put("IT-1025", 3);
    creditHours.put("IT-1050", 3);
    creditHours.put("IT-1150", 3);
    creditHours.put("IT-2310", 3);
    creditHours.put("IT-2320", 4);
    creditHours.put("IT-2351", 4);
    creditHours.put("IT-2650", 4);
    creditHours.put("IT-2660", 4);
    creditHours.put("IT-2030", 4);

    //check for keys, display true or false
    System.out.println("Does the map contain IT-1025? " + creditHours.containsKey("IT-1025"));
    System.out.println("Does the map contain IT-2110? " + creditHours.containsKey("IT-2110"));

    //print all values in map
    System.out.println("Values in the map before removals: ");
    creditHours.displayAllValues();


    //remove IT-2030 and IT-1150
    creditHours.remove("IT-2030");
    creditHours.remove("IT-1150");

    //print all values in map after removal
    System.out.println("Values in the map after removals: "); 
    creditHours.displayAllValues();
  }
}