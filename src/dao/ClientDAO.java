/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Client;

/**
 *
 * @author luiz
 */
public interface ClientDAO {

    //CREATE
    public void createClient(Client client);

    //READ
    public Client readClient(String str, int opt);

    // READ ALL
    public List<Client> readAllClients();

    //UPDATE
    public void updateClient(Client oldClient, Client newClient);

    //DELETE
    public void deleteClient(Client client);
}
