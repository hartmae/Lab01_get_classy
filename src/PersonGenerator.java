import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {

        ArrayList<Person> folks = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");

        Boolean done = false;

        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int YOB = 0;

        do {
            ID = SafeInput.getNonZeroLenString(input,"Enter ID [6 digits]");
            firstName = SafeInput.getNonZeroLenString(input,"Enter first name");
            lastName = SafeInput.getNonZeroLenString(input,"Enter last name");
            title = SafeInput.getNonZeroLenString(input,"Enter title");
            YOB = SafeInput.getRangedInt(input, "Enter Year of Birth", 1940, 2010);

            Person person = new Person(firstName, lastName, ID, title, YOB);
            folks.add(person);

            done = SafeInput.getYNConfirm(input, "Are You Done?: ");

        } while (!done);

        for (Person p: folks) {
            System.out.println(p.toCSV());
        }

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(Person rec : folks)
            {
                String csvLine = rec.toCSV();
                writer.write(csvLine, 0, csvLine.length());
                writer.newLine();

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
