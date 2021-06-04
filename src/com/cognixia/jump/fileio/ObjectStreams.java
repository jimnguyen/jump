package com.cognixia.jump.fileio;

import com.cognixia.jump.streams.Person;

import java.io.*;

public class ObjectStreams {
    public static void main(String[] args) {
        File file = new File("resources/objectFile.data");
        boolean created;
        try {
            created = file.createNewFile();
            if (created) System.out.println("Successfully created file");
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeObjectsToFile(file);
        readObjectsToFile(file);
    }

    public static void writeObjectsToFile(File file) {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) {
            Person person = new Person("Jim", 28);
            writer.writeObject(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readObjectsToFile(File file) {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
            Person person = (Person) reader.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
