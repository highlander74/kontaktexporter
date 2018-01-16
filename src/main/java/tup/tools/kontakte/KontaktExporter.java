package tup.tools.kontakte;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;

public class KontaktExporter {

    public static void export() throws IOException {
        File file = new File("E:/proj/eclipse/tools/data/Telefon.csv");
        FileInputStream fileStream = new FileInputStream(file);
        CsvReader csvReader = new CsvReader(fileStream, Charset.availableCharsets().get("Windows-1252"));

        while (csvReader.readRecord()) {
            String[] values = csvReader.getValues();
            System.out.println();
            // test
//            String etage = values[0];
            String vorName = values[0];
            String nachName = values[1];
            String telNr = values[2];
            telNr = ( telNr != null && telNr.length() > 0) ? telNr.substring(1) : null;
            String handyNr = 0 + values[3];

            String basisName = /*etage + "_" + */ vorName + "_" + nachName + "_";
            if (telNr != null && telNr.length() > 0) {
                File out = new File("E:/proj/eclipse/tools/data/out/" + basisName + telNr + ".kontakte.bat");
                String command = "yealink.bat " + telNr;
                writeOut(out, command);
            }
            if (!handyNr.equals("0")) {
                File out = new File("E:/proj/eclipse/tools/data/out/" + basisName + handyNr + ".kontakte.bat");
                String command = "yealink.bat " + handyNr;
                writeOut(out, command);
            }
        }

    }

    private static void writeOut(File out, String command) {
        FileWriter writer;
        try {
            writer = new FileWriter(out);
            writer.write(command);
            writer.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            export();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}