package agh.ics.oop;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WriteToCSV {

    public static void saveRecord(String dayCount, String animalsAliveCount, String animalsDeadCount, String grassCount, String freeFields, String famousGenotype, String averageAnimalEnergy, String averageAnimalLifespan, String filepath){

        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(dayCount + ", " + animalsAliveCount + ", " + animalsDeadCount + ", " + grassCount + ", " + freeFields + ", " + famousGenotype + ", " + averageAnimalEnergy + ", " + averageAnimalLifespan);
            pw.flush();
            pw.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Record not saved");
        }


    }


}
