import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<Product> products = new ArrayList<>();

        try
        {
            // uses a fixed known path:
            //  Path file = Paths.get("c:\\My Documents\\data.txt");

            // use the toolkit to get the current working directory of the IDE
            // Not sure if the toolkit is thread safe...
            File workingDirectory = new File(System.getProperty("user.dir"));

            // Typiacally, we want the user to pick the file so we use a file chooser
            // kind of ugly code to make the chooser work with NIO.
            // Because the chooser is part of Swing it should be thread safe.
            chooser.setCurrentDirectory(workingDirectory);
            // Using the chooser adds some complexity to the code.
            // we have to code the complete program within the conditional return of
            // the filechooser because the user can close it without picking a file

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                final String HEADER_FMT = "%-6s  %-12s %-28s %12s";
                final String ROW_FMT    = "%06d  %-12s %-28s %12.2f";

                String header = String.format(HEADER_FMT, "ID#", "Name", "Description", "Cost");
                System.out.println(header);
                System.out.println("=".repeat(header.length()));

                // Finally we can read the file LOL!
                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    line++;
                    // echo to screen
                    String[] parts = rec.split("\\s*,\\s*");
                    if (parts.length != 4) {
                        System.err.println("Skipping malformed line " + line + ": " + rec);
                        continue;
                    }

                    try {
                        String id = parts[0];
                        String name = parts[1];
                        String description = parts[2];
                        double cost = Double.parseDouble(parts[3]);

                        Product product = new Product(name, description, id, cost);
                        products.add(product);

                        String row = String.format(ROW_FMT, Integer.parseInt(id), name, description, cost);
                        System.out.println(row);
                    } catch (NumberFormatException nfe) {
                        System.err.println("Skipping line " + line + " (bad number): " + rec);
                    }
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");
                
                // Display the ArrayList contents
                System.out.println("\n\nProducts from ArrayList:");
                System.out.println("=".repeat(header.length()));
                for (Product p : products) {
                    System.out.println(p.toCSV());
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
