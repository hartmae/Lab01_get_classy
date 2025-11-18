import java.util.Objects;

/**
 * Product class representing a product with basic information
 * @author Student
 */
public class Product {
    private String name;
    private String description;
    private String ID;
    private double cost;

    public static final int NAME_LENGTH = 35;
    public static final int DESCRIPTION_LENGTH = 75;
    public static final int ID_LENGTH = 6;
    public static final int RECORD_SIZE = (NAME_LENGTH + DESCRIPTION_LENGTH + ID_LENGTH) * 2 + 8;

    /**
     * Constructor with all fields
     * @param name Product name
     * @param description Product description
     * @param ID Product ID (should never change)
     * @param cost Product cost
     */
    public Product(String name, String description, String ID, double cost) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.cost = cost;
    }

    /**
     * Constructor without description
     * @param name Product name
     * @param ID Product ID
     * @param cost Product cost
     */
    public Product(String name, String ID, double cost) {
        this(name, "", ID, cost);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getID() {
        return ID;
    }

    public double getCost() {
        return cost;
    }

    // Setters (ID should not have a setter as it should never change)
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        if (cost >= 0) {
            this.cost = cost;
        }
    }

    /**
     * Returns a CSV formatted string of the product data
     * @return CSV string
     */
    public String toCSV() {
        return ID + "," + name + "," + description + "," + cost;
    }

    /**
     * Returns a JSON formatted string of the product data
     * @return JSON string
     */
    public String toJSON() {
        return "{\n" +
                "  \"ID\": \"" + ID + "\",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"description\": \"" + description + "\",\n" +
                "  \"cost\": " + cost + "\n" +
                "}";
    }

    /**
     * Returns an XML formatted string of the product data
     * @return XML string
     */
    public String toXML() {
        return "<Product>\n" +
                "  <ID>" + ID + "</ID>\n" +
                "  <name>" + name + "</name>\n" +
                "  <description>" + description + "</description>\n" +
                "  <cost>" + cost + "</cost>\n" +
                "</Product>";
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.cost, cost) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(ID, product.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, ID, cost);
    }

    private static String padString(String str, int length) {
        if (str == null) {
            str = "";
        }
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public String getPaddedName() {
        return padString(name, NAME_LENGTH);
    }

    public String getPaddedDescription() {
        return padString(description, DESCRIPTION_LENGTH);
    }

    public String getPaddedID() {
        return padString(ID, ID_LENGTH);
    }

}