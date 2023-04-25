import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;


public class Oblig5Del1 {
    public static void main(String[] args) {
        File metadataFil = new File("testdatalike" + "/metadata.csv");
        String filen = null;
        SubsekvensRegister register = new SubsekvensRegister();

        Scanner metadataLeser = null;

        try {
            metadataLeser = new Scanner(metadataFil);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen!");
        }
        while(metadataLeser.hasNextLine()){
            String nextLineHolder = metadataLeser.nextLine(); 
            filen = nextLineHolder; 
            String lokasjon = "testdatalike" + "/" + filen;
            HashMap<String, Subsekvens> tempHashMap;
            tempHashMap = SubsekvensRegister.lesFraFil(lokasjon);
            register.settInnHash(tempHashMap);
        }

        System.out.println(register.finnHøyestefremkomster(register.hentHash()));

    }
}