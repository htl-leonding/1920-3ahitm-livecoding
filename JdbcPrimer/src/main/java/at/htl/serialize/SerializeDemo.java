package at.htl.serialize;

import java.io.*;

public class SerializeDemo {

    public static void main(String[] args) {
        Demo object = new Demo(1, "htl-leonding");
        String filename = "file.ser";

//        // Serialization
//        try {
//            //Saving of object in a file
//            FileOutputStream file = new FileOutputStream(filename);
//            ObjectOutputStream out = new ObjectOutputStream(file);
//
//            // Method for serialization of object
//            out.writeObject(object);
//
//            out.close();
//            file.close();
//
//            System.out.println("Object has been serialized");
//        }
//
//        catch(IOException ex) {
//            System.out.println("IOException is caught");
//        }


        Demo object1 = null;

        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object1 = (Demo)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            System.out.println("a = " + object1.a);
            System.out.println("b = " + object1.b);
        }

        catch(IOException ex) {
            System.err.println(ex.getMessage());
        }

        catch(ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
