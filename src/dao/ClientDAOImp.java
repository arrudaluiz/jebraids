/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DatabaseInitializer.CLIENT_FILE;
import static dao.DatabaseInitializer.DIRECTORY;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Client;

/**
 *
 * @author luiz
 */
public class ClientDAOImp implements ClientDAO {

    @Override
    public void createClient(Client client) {
        try {
            FileWriter writer = new FileWriter(CLIENT_FILE, true);
            BufferedWriter buffWriter = new BufferedWriter(writer);
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append(lastClientKey()).append(",")
                    .append(client.getName()).append(",")
                    .append(client.getFone()).append(",")
                    .append(client.getSocialMedia()).append(",")
                    .append(client.getAddress().getCity()).append(",")
                    .append(client.getAddress().getNeighborhood()).append(",")
                    .append(client.getAddress().getStreet()).append(",")
                    .append(client.getAddress().getNumber()).append(",")
                    .append(client.getAddress().getComplement()).append("\n");
            buffWriter.write(sBuilder.toString());

            buffWriter.close();
            writer.close();
        } catch (IOException ioError) {
            System.err.println("Erro na escrita do arquivo. " + ioError);
        }
    }

    @Override
    public Client readClient(String str, int opt) {
        Client client = null;

        try {
            FileReader reader = new FileReader(CLIENT_FILE);
            BufferedReader buffReader = new BufferedReader(reader);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                if (field[opt].equalsIgnoreCase(str)) {
                    reader.close();
                    buffReader.close();

                    client = new Client();
                    client.setName(field[1]);
                    client.setFone(field[2]);
                    client.setSocialMedia(field[3]);
                    client.getAddress().setCity(field[4]);
                    client.getAddress().setNeighborhood(field[5]);
                    client.getAddress().setStreet(field[6]);
                    client.getAddress().setNumber(field[7]);
                    client.getAddress().setComplement(field[8]);

                    return client;
                }
            }
            reader.close();
            buffReader.close();
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }
        return client;
    }

    @Override
    public List<Client> readAllClients() {
        List<Client> clients = new ArrayList<>();

        try {
            FileReader reader = new FileReader(CLIENT_FILE);
            BufferedReader buffReader = new BufferedReader(reader);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                Client client = new Client();
                client.setName(field[1]);
                client.setFone(field[2]);
                client.setSocialMedia(field[3]);
                client.getAddress().setCity(field[4]);
                client.getAddress().setNeighborhood(field[5]);
                client.getAddress().setStreet(field[6]);
                client.getAddress().setNumber(field[7]);
                client.getAddress().setComplement(field[8]);
                clients.add(client);
            }
            reader.close();
            buffReader.close();
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }
        return clients;
    }

    @Override
    public void updateClient(Client oldClient, Client newClient) {
        try {
            FileReader reader = new FileReader(CLIENT_FILE);
            BufferedReader buffReader = new BufferedReader(reader);
            File tempFile = new File(DIRECTORY, "ClientTemp.txt");
            FileWriter writer = new FileWriter(tempFile);
            BufferedWriter buffWriter = new BufferedWriter(writer);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                if ((field[2].equals(oldClient.getFone())) && (field[3].equals(oldClient.getSocialMedia()))) {
                    StringBuilder sBuilder = new StringBuilder();
                    sBuilder.append(field[0]).append(",")
                            .append(newClient.getName()).append(",")
                            .append(newClient.getFone()).append(",")
                            .append(newClient.getSocialMedia()).append(",")
                            .append(newClient.getAddress().getCity()).append(",")
                            .append(newClient.getAddress().getNeighborhood()).append(",")
                            .append(newClient.getAddress().getStreet()).append(",")
                            .append(newClient.getAddress().getNumber()).append(",")
                            .append(newClient.getAddress().getComplement());
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

            boolean successful = tempFile.renameTo(CLIENT_FILE);
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }
    }

    @Override
    public void deleteClient(Client client) {
        try {
            FileReader reader = new FileReader(CLIENT_FILE);
            BufferedReader buffReader = new BufferedReader(reader);
            File tempFile = new File(DIRECTORY, "ClientTemp.txt");
            FileWriter writer = new FileWriter(tempFile);
            BufferedWriter buffWriter = new BufferedWriter(writer);

            while (buffReader.ready()) {
                String line = buffReader.readLine();
                String field[] = line.split(",");

                if (!((field[2].equals(client.getFone())) && (field[3].equals(client.getSocialMedia())))) {
                    buffWriter.write(line);
                    buffWriter.newLine();
                }
            }
            reader.close();
            buffReader.close();
            buffWriter.close();
            writer.close();

            boolean successful = tempFile.renameTo(CLIENT_FILE);
        } catch (IOException ioError) {
            System.err.println("Erro ao ler arquivo. " + ioError);
        }
    }

    public String lastClientKey() {
        int key = 0;

        try {
            FileReader reader = new FileReader(CLIENT_FILE);
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