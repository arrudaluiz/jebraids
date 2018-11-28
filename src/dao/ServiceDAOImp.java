/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DatabaseInitializer.DIRECTORY;
import static dao.DatabaseInitializer.SERVICE_FILE;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Service;

/**
 *
 * @author luiz
 */
public class ServiceDAOImp implements ServiceDAO {

    @Override
    public void createService(Service service) {
        try {
            FileWriter writer = new FileWriter(SERVICE_FILE, true);
            BufferedWriter buffWriter = new BufferedWriter(writer);
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append(lastServiceKey()).append(",")
                    .append(service.getName()).append(",")
                    .append(service.getPrice()).append("\n");
            buffWriter.write(sBuilder.toString());

            buffWriter.close();
            writer.close();
        } catch (IOException ioError) {
            System.err.println("Erro na escrita do arquivo. " + ioError);
        }
    }

    @Override
    public Service readService(String str, int opt) {
        Service service = null;

        try {
            FileReader reader = new FileReader(SERVICE_FILE);
            BufferedReader buffReader = new BufferedReader(reader);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                if (field[opt].equalsIgnoreCase(str)) {
                    reader.close();
                    buffReader.close();

                    service = new Service();
                    service.setName(field[1]);
                    service.setPrice(Double.parseDouble(field[2]));

                    return service;
                }
            }
            reader.close();
            buffReader.close();
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }

        return service;
    }

    @Override
    public List<Service> readAllServices() {
        List<Service> services = new ArrayList<>();

        try {
            FileReader reader = new FileReader(SERVICE_FILE);
            BufferedReader buffReader = new BufferedReader(reader);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                Service service = new Service();
                service.setName(field[1]);
                service.setPrice(Double.parseDouble(field[2]));
                services.add(service);
            }
            reader.close();
            buffReader.close();
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }
        return services;
    }

    @Override
    public void updateService(Service oldService, Service newService) {
        try {
            FileReader reader = new FileReader(SERVICE_FILE);
            BufferedReader buffReader = new BufferedReader(reader);
            File tempFile = new File(DIRECTORY, "ServiceTemp.txt");
            FileWriter writer = new FileWriter(tempFile);
            BufferedWriter buffWriter = new BufferedWriter(writer);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                if ((field[1].equals(oldService.getName())) && (field[2].equals(oldService.getPrice()))) {
                    StringBuilder sBuilder = new StringBuilder();
                    sBuilder.append(field[0]).append(",")
                            .append(newService.getName()).append(",")
                            .append(newService.getPrice());
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

            boolean successful = tempFile.renameTo(SERVICE_FILE);
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }
    }

    @Override
    public void deleteService(Service service) {
        try {
            FileReader reader = new FileReader(SERVICE_FILE);
            BufferedReader buffReader = new BufferedReader(reader);
            File tempFile = new File(DIRECTORY, "ServiceTemp.txt");
            FileWriter writer = new FileWriter(tempFile);
            BufferedWriter buffWriter = new BufferedWriter(writer);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                if (!((field[1].equals(service.getName())) && (field[2].equals(service.getPrice())))) {
                    buffWriter.write(line);
                    buffWriter.newLine();
                }
            }

            reader.close();
            buffReader.close();
            buffWriter.close();
            writer.close();

            boolean successful = tempFile.renameTo(SERVICE_FILE);
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }
    }

    public String lastServiceKey() {
        int key = 0;

        try {
            FileReader reader = new FileReader(SERVICE_FILE);
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