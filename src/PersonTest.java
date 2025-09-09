import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;

class PersonTest {
    private Person person1;
    private Person person2;
    private Person person3;

    @BeforeEach
    void setUp() {
        person1 = new Person("John", "Doe", "000001", "Mr.", 1985);
        person2 = new Person("Jane", "Smith", "000002", "Dr.", 1990);
        person3 = new Person("Bob", "Johnson", "000003", 1975); // Constructor without title
    }

    @Test
    void testConstructorWithAllFields() {
        assertNotNull(person1);
        assertEquals("John", person1.getFirstName());
        assertEquals("Doe", person1.getLastName());
        assertEquals("000001", person1.getID());
        assertEquals("Mr.", person1.getTitle());
        assertEquals(1985, person1.getYOB());
    }

    @Test
    void testConstructorWithoutTitle() {
        assertNotNull(person3);
        assertEquals("Bob", person3.getFirstName());
        assertEquals("Johnson", person3.getLastName());
        assertEquals("000003", person3.getID());
        assertEquals("", person3.getTitle());
        assertEquals(1975, person3.getYOB());
    }

    @Test
    void testSetFirstName() {
        person1.setFirstName("James");
        assertEquals("James", person1.getFirstName());
    }

    @Test
    void testSetLastName() {
        person1.setLastName("Wilson");
        assertEquals("Wilson", person1.getLastName());
    }

    @Test
    void testSetTitle() {
        person1.setTitle("Dr.");
        assertEquals("Dr.", person1.getTitle());
    }

    @Test
    void testSetYOBValid() {
        person1.setYOB(2000);
        assertEquals(2000, person1.getYOB());
    }

    @Test
    void testSetYOBInvalid() {
        int originalYOB = person1.getYOB();
        person1.setYOB(1939); // Below valid range
        assertEquals(originalYOB, person1.getYOB()); // Should not change
        
        person1.setYOB(2011); // Above valid range
        assertEquals(originalYOB, person1.getYOB()); // Should not change
    }

    @Test
    void testFullName() {
        assertEquals("John Doe", person1.fullName());
        assertEquals("Jane Smith", person2.fullName());
    }

    @Test
    void testFormalName() {
        assertEquals("Mr. John Doe", person1.formalName());
        assertEquals("Dr. Jane Smith", person2.formalName());
        assertEquals(" Bob Johnson", person3.formalName()); // Empty title
    }

    @Test
    void testGetAgeCurrentYear() {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int expectedAge = currentYear - 1985;
        assertEquals(String.valueOf(expectedAge), person1.getAge());
    }

    @Test
    void testGetAgeSpecificYear() {
        assertEquals("35", person1.getAge(2020));
        assertEquals("40", person1.getAge(2025));
        assertEquals("15", person1.getAge(2000));
    }

    @Test
    void testToCSV() {
        String expected = "000001,John,Doe,Mr.,1985";
        assertEquals(expected, person1.toCSV());
        
        String expected2 = "000002,Jane,Smith,Dr.,1990";
        assertEquals(expected2, person2.toCSV());
    }

    @Test
    void testToJSON() {
        String expected = "{\n" +
                "  \"ID\": \"000001\",\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"Doe\",\n" +
                "  \"title\": \"Mr.\",\n" +
                "  \"YOB\": 1985\n" +
                "}";
        assertEquals(expected, person1.toJSON());
    }

    @Test
    void testToXML() {
        String expected = "<Person>\n" +
                "  <ID>000001</ID>\n" +
                "  <firstName>John</firstName>\n" +
                "  <lastName>Doe</lastName>\n" +
                "  <title>Mr.</title>\n" +
                "  <YOB>1985</YOB>\n" +
                "</Person>";
        assertEquals(expected, person1.toXML());
    }

    @Test
    void testToString() {
        String result = person1.toString();
        assertTrue(result.contains("000001"));
        assertTrue(result.contains("John"));
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("Mr."));
        assertTrue(result.contains("1985"));
    }

    @Test
    void testEquals() {
        Person samePerson = new Person("John", "Doe", "000001", "Mr.", 1985);
        Person differentPerson = new Person("Jane", "Doe", "000001", "Mr.", 1985);
        
        assertEquals(person1, samePerson);
        assertNotEquals(person1, person2);
        assertNotEquals(person1, differentPerson);
        assertNotEquals(person1, null);
        assertNotEquals(person1, "Not a Person");
    }

}