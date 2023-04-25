import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.Scanner;
    import java.io.File;

    public class SubsekvensRegister {
        ArrayList<HashMap<String, Subsekvens>> subsekvenser = new ArrayList<>();

    public void settInnHash(HashMap<String, Subsekvens> hashMap) {
        subsekvenser.add(hashMap);
        }

    public HashMap<String, Subsekvens> hentHash() {
        int index = 0; 
        try {
            return subsekvenser.get(index);
        } catch (Exception e) {
            return null;
        }
        }

    public int hentAntallHash() {
        return subsekvenser.size();
        }

    public static HashMap<String, Subsekvens> lesFraFil(String fileName) {
        HashMap<String, Subsekvens> resultMap = new HashMap<>();
        File inputFile = new File(fileName);
        String linje;
        String ord;

        try {
            Scanner fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNext()) {
                linje = fileScanner.nextLine();
                Scanner wordScanner = new Scanner(linje);
                ord = wordScanner.next();
                String[] splitLetters = ord.split("");

                for (int i = 0; i < splitLetters.length - 2; i++) {
                    String subsequence;
                    subsequence = splitLetters[i] + splitLetters[i + 1] + splitLetters[i + 2];
                    resultMap.put(subsequence, new Subsekvens(subsequence));
                }
                wordScanner.close();
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return resultMap;
    }

    public static HashMap<String, Subsekvens> flett(HashMap<String, Subsekvens> map1, HashMap<String, Subsekvens> map2) {
        HashMap<String, Subsekvens> tempHash = new HashMap<>(map1); // create a new HashMap and copy the key-value pairs from map1
        for (Subsekvens sub1 : map2.values()) {
            String subsekvens = sub1.subsekvens;
            if (tempHash.containsKey(subsekvens)) {
                tempHash.get(subsekvens).antall_forekomster += map2.get(subsekvens).antall_forekomster; // update the existing Subsekvens object
            } else {
                tempHash.put(subsekvens, sub1); // add a new key-value pair to tempHash
            }
        }
        return tempHash;
    }
    public HashMap<String, Subsekvens> flettAlt() {
        HashMap<String, Subsekvens> finalMap = null;
        try {
            finalMap = flett(subsekvenser.get(0), subsekvenser.get(1));
        } catch (Exception e) {
            System.out.println("Enten første eller andre map eksisterer ikke!");
        }
        int arrstorrelse = subsekvenser.size();
        for(int i = 2; arrstorrelse>i; i++){
            finalMap = flett(finalMap, subsekvenser.get(i));
        }
        return finalMap;
    }

    public String finnHøyestefremkomster(HashMap<String, Subsekvens> map) {
        String subsekvenser = "";
        int forekomster = 0;
        for (Map.Entry<String, Subsekvens> entry : map.entrySet()) {
            Subsekvens sub = entry.getValue();
            if (sub.antall_forekomster > forekomster) {
                forekomster = sub.antall_forekomster;
                subsekvenser = sub.subsekvens + " ";
            } else if (sub.antall_forekomster == forekomster) {
                subsekvenser += sub.subsekvens + " ";
            }
        }
        return "Forekomster: " + forekomster + "\n" + "Subsekvenser: " + subsekvenser;
    }

    public int hentStørrelse() {
        return subsekvenser.size();

        }

    @Override
    public String toString() {
        String print = ""; 
        for(HashMap<String, Subsekvens> map : subsekvenser){
            for(Subsekvens sub : map.values()){
                print = print + " " + sub;
            }
        }
        return print;
    }
}