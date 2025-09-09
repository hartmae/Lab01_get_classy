import java.util.Scanner;

/**
 * ObjInputTest - Test program to demonstrate SafeInputObj methods work
 * @author Student
 */
public class ObjInputTest {
    public static void main(String[] args) {
        // Create SafeInputObj instance using default constructor (System.in)
        SafeInputObj input = new SafeInputObj();
        
        System.out.println("=================================");
        System.out.println("SafeInputObj Test Program");
        System.out.println("Testing all SafeInputObj methods");
        System.out.println("=================================\n");
        
        // Test getNonZeroLenString
        System.out.println("Testing getNonZeroLenString:");
        String name = input.getNonZeroLenString("Enter your name");
        System.out.println("You entered: " + name);
        System.out.println();
        
        // Test getInt
        System.out.println("Testing getInt:");
        int age = input.getInt("Enter your age");
        System.out.println("You entered: " + age);
        System.out.println();
        
        // Test getRangedInt
        System.out.println("Testing getRangedInt:");
        int month = input.getRangedInt("Enter a month (1-12)", 1, 12);
        System.out.println("You entered month: " + month);
        System.out.println();
        
        // Test getDouble
        System.out.println("Testing getDouble:");
        double price = input.getDouble("Enter a price");
        System.out.println("You entered: $" + price);
        System.out.println();
        
        // Test getRangedDouble
        System.out.println("Testing getRangedDouble:");
        double percentage = input.getRangedDouble("Enter a percentage (0-100)", 0, 100);
        System.out.println("You entered: " + percentage + "%");
        System.out.println();
        
        // Test getRegExString
        System.out.println("Testing getRegExString:");
        String ssn = input.getRegExString("Enter SSN (###-##-####)", "\\d{3}-\\d{2}-\\d{4}");
        System.out.println("You entered SSN: " + ssn);
        System.out.println();
        
        // Test getYNConfirm
        System.out.println("Testing getYNConfirm:");
        boolean confirm = input.getYNConfirm("Do you want to continue?");
        if (confirm) {
            System.out.println("You chose to continue!");
        } else {
            System.out.println("You chose not to continue!");
        }
        System.out.println();
        
        // Test with Scanner parameter constructor
        System.out.println("=================================");
        System.out.println("Testing constructor with Scanner parameter:");
        Scanner customScanner = new Scanner(System.in);
        SafeInputObj customInput = new SafeInputObj(customScanner);
        
        String testString = customInput.getNonZeroLenString("Enter a test string using custom scanner");
        System.out.println("You entered: " + testString);
        
        System.out.println("\n=================================");
        System.out.println("All SafeInputObj methods tested successfully!");
        System.out.println("=================================");
    }
}