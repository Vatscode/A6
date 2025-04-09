
/**
 * A class representing a person -- suitable for extending into interesting
 * kinds of people -- like students or truck-drivers.
 *
 * @author Vats Upadhyay (A00454163)
 */
public class Person {

    // ********** instance variables ********** //
    private String name;
    private String sex;
    private int age;
    private double height;
    private double weight;

    // ********** constructors ********** //
    /**
     * Create a Person with the given name and other details.
     *
     * @param name this Person's name
     * @param sex this Person's sex (e.g., "M" or "F")
     * @param age this Person's age
     * @param height this Person's height
     * @param weight this Person's weight
     */
    public Person(String name, String sex, int age, double height, double weight) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    /**
     * Create a person with a place-holder name and values.
     */
    public Person() {
        this("Baby Human", "M", 0, 0.0, 0.0);
    }

    // ********** instance methods ********** //
    /**
     * Change this Person's name.
     *
     * @param newName this Person's new name.
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Return this Person's name.
     *
     * @return this Person's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Return this Person's sex.
     *
     * @return this Person's sex.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Return this Person's age.
     *
     * @return this Person's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Return this Person's height.
     *
     * @return this Person's height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Return this Person's weight.
     *
     * @return this Person's weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Provide a simple report on this Person.
     */
    public void writeOutput() {
        System.out.println("Name: " + name + ", Sex: " + sex + ", Age: " + age + ", Height: " + height + ", Weight: " + weight);

    }

    /**
     * Check whether this Person has the same name as another.
     *
     * @param other the other Person (who possibly has the same name).
     * @return true if these people's names are identical; false otherwise.
     */
    public boolean hasSameName(Person other) {
        if (other == null) {
            return false;
        }
        return name.equalsIgnoreCase(other.name);
    }

    /**
     * Create a String representing this Person.
     *
     * @return a String with this Person's name.
     */
    @Override
    public String toString() {
        return "Person: " + name + ", " + sex + ", " + age + ", " + height + ", " + weight;

    }

      // ********** Comparators for sorting ********** //
    public static Comparator<Person> nameComparator = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.name.compareToIgnoreCase(p2.name);
        }
    };

     public static Comparator<Person> ageComparator = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return Integer.compare(p1.age, p2.age);
        }
    };

    public static Comparator<Person> heightComparator = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return Double.compare(p1.height, p2.height);
        }
    };

     public static Comparator<Person> nameReverseComparator = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p2.name.compareToIgnoreCase(p1.name);
        }
    };

}
