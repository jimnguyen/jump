package com.cognixia.jump.fileio;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class FileIO {
    public static void main(String[] args) {

        File file = new File("resources/temp.txt");
        System.out.println("Does this file exist? " + (file.exists() ? "Yes" : "No"));

        if (!file.exists()) {

            try {
                boolean success = file.createNewFile();
                System.out.println("Status of file creation: " + success);
            } catch (IOException e) {
                System.out.println("Could not create file");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Unsuccessful action");
                e.printStackTrace();
            }

            System.out.println("Created file successfully");
        }

        System.out.println("Absolute path: " + file.getAbsolutePath());
        System.out.println("File name: " + file.getName());
        System.out.println("Relative path: " + file.getPath());
        System.out.println("Last modified: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()),
                TimeZone.getTimeZone("GMT-7").toZoneId()));

        File dir = new File("resources/dir1");
        boolean created;

        if (!dir.exists()) {
            try {
                created = dir.mkdir();
                if (created) {
                    System.out.println("Created directory successfully");
                } else {
                    System.out.println("Directory not created");
                }
            } catch (Exception e) {
                System.out.println("Unsuccessful action");
                e.printStackTrace();
            }
        }

        File dirs = new File("resources/dir2/dir3");
        created = dirs.mkdirs();

        if (created) {
            System.out.println("Two directories in hierarchy created");
        } else {
            System.out.println("Failed to create directories");
        }

        try {
            writeFile(3, "New line to add\n");
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O Exception occurred");
            e.printStackTrace();
        }
    }

    public static void readFile() throws IOException {
        File file = new File("resources/temp.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        // Read the whole file
        System.out.println("\nReading in " + file.getName());
        System.out.println("-------------------");
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
        fileReader.close();
    }

    public static void writeFile(int choice, String text) throws IOException {
        File file = new File("resources/temp.txt");
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        System.out.println("\nWriting into " + file.getName());

        switch (choice) {
            case 1 -> writeWithBuffer(bufferedWriter, text);
            case 2 -> appendToFile(bufferedWriter, text);
            case 3 -> writeUsingPrintWriter(printWriter, text);
            default -> System.out.println("Not a valid option");
        }

        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }

    public static void writeWithBuffer(BufferedWriter bufferedWriter, String str) throws IOException {
        for (int i = 0; i < 3; i++) {
            bufferedWriter.write(str);
        }
    }

    public static void appendToFile(BufferedWriter bufferedWriter, String str) throws IOException {
        for (int i = 0; i < 3; i++) {
            bufferedWriter.append(str);
        }
    }

    public static void writeUsingPrintWriter(PrintWriter printWriter, String str) {
        printWriter.println();
        printWriter.print(str);
        printWriter.print(str);
        printWriter.println(str);
        printWriter.println(str);
    }
}
