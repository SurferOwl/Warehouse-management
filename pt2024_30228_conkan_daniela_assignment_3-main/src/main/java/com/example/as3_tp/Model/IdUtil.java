package com.example.as3_tp.Model;

import java.io.*;

/**
 * used to store the number of inserts into a file (to determine the order id) on orders and Log tables
 */
public class IdUtil {
    private static final String FILE_PATH = "src\\main\\resources\\idInsert.txt";

    /**
     * read from the file
     */

    public static int readId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 1;
        }
    }

    /**
     * write in the file
     * @param id id-ul updatat pe care il vom scrie in fisier
     */
    public static void writeId(int id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}