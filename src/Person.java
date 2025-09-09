import java.util.Calendar;
import java.util.Objects;

/**
 * Person class representing a person with basic information
 * @author Student
 */
public class Person {
    private String firstName;
    private String lastName;
    private String ID;
    private String title;
    private int YOB;

    /**
     * Constructor with all fields
     * @param firstName Person's first name
     * @param lastName Person's last name
     * @param ID Person's ID (sequence of digits)
     * @param title Person's title (Mr., Mrs., Ms., Prof., Dr., Hon., etc.)
     * @param YOB Year of birth (1940-2010)
     */
    public Person(String firstName, String lastName, String ID, String title, int YOB) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.title = title;
        this.YOB = YOB;
    }

    /**
     * Constructor without title (defaults to empty string)
     * @param firstName Person's first name
     * @param lastName Person's last name
     * @param ID Person's ID
     * @param YOB Year of birth
     */
    public Person(String firstName, String lastName, String ID, int YOB) {
        this(firstName, lastName, ID, "", YOB);
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public int getYOB() {
        return YOB;
    }

    // Setters (ID should not have a setter as it should never change)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYOB(int YOB) {
        if (YOB >= 1940 && YOB <= 2010) {
            this.YOB = YOB;
        }
    }

    /**
     * Returns full name as firstName space lastName
     * @return Full name string
     */
    public String fullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns formal name as title space fullName
     * @return Formal name string
     */
    public String formalName() {
        return title + " " + fullName();
    }

    /**
     * Returns age based on current year
     * @return Age as string
     */
    public String getAge() {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int age = currentYear - YOB;
        return String.valueOf(age);
    }

    /**
     * Returns age for a specified year
     * @param year The year to calculate age for
     * @return Age as string
     */
    public String getAge(int year) {
        int age = year - YOB;
        return String.valueOf(age);
    }

    /**
     * Returns a CSV formatted string of the person data
     * @return CSV string
     */
    public String toCSV() {
        return ID + "," + firstName + "," + lastName + "," + title + "," + YOB;
    }

    /**
     * Returns a JSON formatted string of the person data
     * @return JSON string
     */
    public String toJSON() {
        return "{\n" +
                "  \"ID\": \"" + ID + "\",\n" +
                "  \"firstName\": \"" + firstName + "\",\n" +
                "  \"lastName\": \"" + lastName + "\",\n" +
                "  \"title\": \"" + title + "\",\n" +
                "  \"YOB\": " + YOB + "\n" +
                "}";
    }

    /**
     * Returns an XML formatted string of the person data
     * @return XML string
     */
    public String toXML() {
        return "<Person>\n" +
                "  <ID>" + ID + "</ID>\n" +
                "  <firstName>" + firstName + "</firstName>\n" +
                "  <lastName>" + lastName + "</lastName>\n" +
                "  <title>" + title + "</title>\n" +
                "  <YOB>" + YOB + "</YOB>\n" +
                "</Person>";
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID='" + ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", YOB=" + YOB +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return YOB == person.YOB &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(ID, person.ID) &&
                Objects.equals(title, person.title);
    }

}