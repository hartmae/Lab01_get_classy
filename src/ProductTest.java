import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setUp() {
        product1 = new Product("Laptop", "High-performance laptop", "000001", 1299.99);
        product2 = new Product("Mouse", "Wireless mouse", "000002", 29.99);
        product3 = new Product("Keyboard", "000003", 79.99); // Constructor without description
    }

    @Test
    void testConstructorWithAllFields() {
        assertNotNull(product1);
        assertEquals("Laptop", product1.getName());
        assertEquals("High-performance laptop", product1.getDescription());
        assertEquals("000001", product1.getID());
        assertEquals(1299.99, product1.getCost(), 0.001);
    }

    @Test
    void testConstructorWithoutDescription() {
        assertNotNull(product3);
        assertEquals("Keyboard", product3.getName());
        assertEquals("", product3.getDescription());
        assertEquals("000003", product3.getID());
        assertEquals(79.99, product3.getCost(), 0.001);
    }

    @Test
    void testSetName() {
        product1.setName("Gaming Laptop");
        assertEquals("Gaming Laptop", product1.getName());
    }

    @Test
    void testSetDescription() {
        product1.setDescription("Ultimate gaming laptop");
        assertEquals("Ultimate gaming laptop", product1.getDescription());
    }

    @Test
    void testSetCostValid() {
        product1.setCost(1499.99);
        assertEquals(1499.99, product1.getCost(), 0.001);
    }

    @Test
    void testSetCostInvalid() {
        double originalCost = product1.getCost();
        product1.setCost(-100.00); // Negative cost should not be set
        assertEquals(originalCost, product1.getCost(), 0.001);
    }

    @Test
    void testToCSV() {
        String expected = "000001,Laptop,High-performance laptop,1299.99";
        assertEquals(expected, product1.toCSV());
        
        String expected2 = "000002,Mouse,Wireless mouse,29.99";
        assertEquals(expected2, product2.toCSV());
    }

    @Test
    void testToJSON() {
        String expected = "{\n" +
                "  \"ID\": \"000001\",\n" +
                "  \"name\": \"Laptop\",\n" +
                "  \"description\": \"High-performance laptop\",\n" +
                "  \"cost\": 1299.99\n" +
                "}";
        assertEquals(expected, product1.toJSON());
    }

    @Test
    void testToXML() {
        String expected = "<Product>\n" +
                "  <ID>000001</ID>\n" +
                "  <name>Laptop</name>\n" +
                "  <description>High-performance laptop</description>\n" +
                "  <cost>1299.99</cost>\n" +
                "</Product>";
        assertEquals(expected, product1.toXML());
    }

    @Test
    void testToString() {
        String result = product1.toString();
        assertTrue(result.contains("000001"));
        assertTrue(result.contains("Laptop"));
        assertTrue(result.contains("High-performance laptop"));
        assertTrue(result.contains("1299.99"));
    }

    @Test
    void testEquals() {
        Product sameProduct = new Product("Laptop", "High-performance laptop", "000001", 1299.99);
        Product differentProduct = new Product("Desktop", "High-performance laptop", "000001", 1299.99);
        
        assertEquals(product1, sameProduct);
        assertNotEquals(product1, product2);
        assertNotEquals(product1, differentProduct);
        assertNotEquals(product1, null);
        assertNotEquals(product1, "Not a Product");
    }

}