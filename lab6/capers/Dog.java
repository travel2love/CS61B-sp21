package capers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import static capers.Utils.*;

/** Represents a dog that can be serialized.
 * @author TODO
*/
public class Dog { // TODO
    static final File CWD = new File(System.getProperty("user.dir"));
    static final File CAPERS = join(CWD, ".capers");

    /** Folder that dogs live in. */
    static final File DOG_FOLDER = join(CAPERS, "dog"); // TODO (hint: look at the `join`
                                         //      function in Utils)

    /** Age of dog. */
    private int age;
    /** Breed of dog. */
    private String breed;
    /** Name of dog. */
    private String name;

    /**
     * Creates a dog object with the specified parameters.
     * @param name Name of dog
     * @param breed Breed of dog
     * @param age Age of dog
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_FOLDER.
     *
     * @param name Name of dog to load
     * @return Dog read from file
     */
    public static Dog fromFile(String name) {
        // TODO (hint: look at the Utils file)
        File f = new File(DOG_FOLDER, name);
        String dogs = readContentsAsString(f);
        String[] str = dogs.split("/");
        Dog dog = new Dog(str[1], str[2], Integer.parseInt(str[3]));
        return dog;
    }

    /**
     * Increases a dog's age and celebrates!
     */
    public void haveBirthday() {
        age += 1;
        saveDog();
        System.out.println(toString());
        System.out.println("Happy birthday! Woof! Woof!");
    }

    /**
     * Saves a dog to a file for future use.
     */
    public void saveDog() {
        // TODO (hint: don't forget dog names are unique)
        File dog = new File(DOG_FOLDER, name);
        try {
            dog.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writeContents(dog,"/" + name, "/",breed, "/", Integer.toString(age));
    }

    @Override
    public String toString() {
        return String.format(
            "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
            name, breed, age);
    }

}
