/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.*;

/**
 *
 * @author luiz
 */
public class DatabaseInitializer {

    public static final File DIRECTORY = new File(System.getProperty("user.home"), "JeBraids/");
    public static final File BOOKING_FILE = new File(DIRECTORY, "Booking.txt");
    public static final File CLIENT_FILE = new File(DIRECTORY, "Client.txt");
    public static final File SERVICE_FILE = new File(DIRECTORY, "Service.txt");

    public void initialize() {
        try {
            if (!DIRECTORY.exists()) {
                DIRECTORY.mkdir();
                BOOKING_FILE.createNewFile();
                CLIENT_FILE.createNewFile();
                SERVICE_FILE.createNewFile();
            } else {
                if (!BOOKING_FILE.exists()) {
                    BOOKING_FILE.createNewFile();
                }
                if (!CLIENT_FILE.exists()) {
                    CLIENT_FILE.createNewFile();
                }
                if (!SERVICE_FILE.exists()) {
                    SERVICE_FILE.createNewFile();
                }
            }
        } catch (IOException ioError) {
            System.err.println("Erro na escrita do arquivo. " + ioError);
        }
    }
}
