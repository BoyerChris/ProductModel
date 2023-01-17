import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
/**
 * @author boyerchris
 */

public class ProductWriter {

    public static void main(String[] args)

    {
        Scanner in = new Scanner(System.in);

        ArrayList<String> listOne = new ArrayList<>();

        String iD = "";
        String name = "";
        String description = "";
        String csvPersonRecord = "";

        double cost = 0;

        boolean done = false;


        do {
            iD = SafeInput.getNonZeroLenString(in, "Please enter a 6 digit ID (000001, 000002, Etc)");
            name = SafeInput.getNonZeroLenString(in, "Please enter your product name");
            description = SafeInput.getNonZeroLenString(in, "Please enter your product description");
            cost = SafeInput.getDouble (in, "Please enter your product cost");

            csvPersonRecord = iD + ", " + name + ", " + description + ", " + cost;

            listOne.add(csvPersonRecord);


            done = SafeInput.getYNConfirm(in, "Are you finished entering person data?");

        }while (!done);

        File PersonData = new File(System.getProperty("user.dir"));
        Path file = Paths.get (PersonData.getPath() + "\\src\\ProductTestData.CSV");

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for (String p : listOne) {
                writer.write(p, 0, p.length());
                writer.newLine();
            }
            writer.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}