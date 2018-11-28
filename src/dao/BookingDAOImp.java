/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DatabaseInitializer.BOOKING_FILE;
import static dao.DatabaseInitializer.DIRECTORY;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Booking;

/**
 *
 * @author luiz
 */
public class BookingDAOImp implements BookingDAO {

    @Override
    public void createBooking(Booking booking) {
        try {
            FileWriter writer = new FileWriter(BOOKING_FILE, true);
            BufferedWriter buffWriter = new BufferedWriter(writer);
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append(lastBookingKey()).append(",")
                    .append(booking.getDate()).append(",")
                    .append(booking.getClient()).append(",")
                    .append(booking.getService()).append("\n");
            buffWriter.write(sBuilder.toString());

            buffWriter.close();
            writer.close();
        } catch (IOException ioError) {
            System.err.println("Erro na escrita do arquivo. " + ioError);
        }
    }

    @Override
    public Booking readBooking(String str, int opt) {
        Booking booking = null;

        try {
            FileReader reader = new FileReader(BOOKING_FILE);
            BufferedReader buffReader = new BufferedReader(reader);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                if (field[opt].equalsIgnoreCase(str)) {
                    reader.close();
                    buffReader.close();

                    booking = new Booking();
//                    booking.setDate(field[1]);
//                    booking.setClient(Integer.parseInt(field[2]));
//                    booking.setService(Integer.parseInt(field[3]));

                    return booking;
                }
            }
            reader.close();
            buffReader.close();
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }

        return booking;
    }

    @Override
    public List<Booking> readAllBookings() {
        List<Booking> bookings = new ArrayList<>();

        try {
            FileReader reader = new FileReader(BOOKING_FILE);
            BufferedReader buffReader = new BufferedReader(reader);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                Booking booking = new Booking();
//                booking.setDate(field[1]);
//                booking.setClient(Integer.parseInt(field[2]));
//                booking.setService(Integer.parseInt(field[3]));
                bookings.add(booking);
            }
            reader.close();
            buffReader.close();
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }

        return bookings;
    }

    @Override
    public void updateBooking(Booking oldBooking, Booking newBooking) {
        try {
            FileReader reader = new FileReader(BOOKING_FILE);
            BufferedReader buffReader = new BufferedReader(reader);
            File tempFile = new File(DIRECTORY, "BookingTemp.txt");
            FileWriter writer = new FileWriter(tempFile);
            BufferedWriter buffWriter = new BufferedWriter(writer);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                if ((field[1].equals(oldBooking.getDate())) && (field[2].equals(oldBooking.getClient()))) {
                    StringBuilder sBuilder = new StringBuilder();
                    sBuilder.append(field[0]).append(",")
                            .append(newBooking.getDate()).append(",")
                            .append(newBooking.getClient()).append(",")
                            .append(newBooking.getService());
                    buffWriter.write(sBuilder.toString());
                } else {
                    buffWriter.write(line);
                }
                buffWriter.newLine();
            }
            reader.close();
            buffReader.close();
            buffWriter.close();
            writer.close();

            boolean successful = tempFile.renameTo(BOOKING_FILE);
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }
    }

    @Override
    public void deleteBooking(Booking booking) {
        try {
            FileReader reader = new FileReader(BOOKING_FILE);
            BufferedReader buffReader = new BufferedReader(reader);
            File tempFile = new File(DIRECTORY, "BookingTemp.txt");
            FileWriter writer = new FileWriter(tempFile);
            BufferedWriter buffWriter = new BufferedWriter(writer);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                if (!((field[1].equals(booking.getDate())) && (field[2].equals(booking.getClient())))) {
                    buffWriter.write(line);
                    buffWriter.newLine();
                }
            }
            reader.close();
            buffReader.close();
            buffWriter.close();
            writer.close();

            boolean successful = tempFile.renameTo(BOOKING_FILE);
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }
    }

    public String lastBookingKey() {
        int key = 0;

        try {
            FileReader reader = new FileReader(BOOKING_FILE);
            BufferedReader buffReader = new BufferedReader(reader);
            String line = "";

            while (buffReader.ready()) {
                line = buffReader.readLine();
            }
            String field[] = line.split(",");

            if (!field[0].isEmpty()) {
                key = Integer.parseInt(field[0]) + 1;
            }

            reader.close();
            buffReader.close();
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }

        return String.valueOf(key);
    }
}