/**
 * A class representing a person -- suitable for extending into interesting
 * kinds of people -- like students or truck-drivers.
 *
 * @author Mark Young (A00000000)
 */
public class Person {

    // ********** instance variables ********** //
    private String name;

    // ********** constructors ********** //
    /**
     * Create a Person with the given name.
     *
     * @param initialName this Person's name
     */
    public Person(String initialName) {
        name = initialName;
    }

    /**
     * Create a person with a place-holder name.
     */
    public Person() {
        this("Baby Human");
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
     * Provide a simple report on this Person.
     */
    public void writeOutput() {
        System.out.println("Name: " + name);
    }

    /**
     * Check whether this Person has the same name as another.
     *
     * @param other the other Person (who possibly has the same name).
     * @return true if these people's names are identical; false otherwise.
     */
    public boolean hasSameName(Person other) {
        if (other == null) return false;
        return name.equalsIgnoreCase(other.name);
    }

    /**
     * Create a String representing this Person.
     *
     * @return a String with this Person's name.
     */
    @Override
    public String toString() {
        return "Person: " + name;
    }

}

