import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {
    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "//src//productData.txt");

        Boolean done = false;

        String ID = "";
        String name = "";
        String description = "";
        double cost = 0;

        do {
            ID = SafeInput.getNonZeroLenString(input,"Enter ID [6 digits]");
            name = SafeInput.getNonZeroLenString(input,"Enter Name");
            description = SafeInput.getNonZeroLenString(input,"Enter Description");
            cost = SafeInput.getRangedDouble(input, "Enter cost", 0, 10000);

            Product product = new Product(name, description, ID, cost);
            products.add(product);

            done = SafeInput.getYNConfirm(input, "Are You Done?: ");

        } while (!done);

        for (Product p: products) {
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

            for(Product rec : products)
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
