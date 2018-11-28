/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Service;

/**
 *
 * @author luiz
 */
public interface ServiceDAO {

    //CREATE
    public void createService(Service service);

    //READ
    public Service readService(String str, int opt);

    //READ ALL
    public List<Service> readAllServices();

    //UPDATE
    public void updateService(Service oldService, Service newService);

    //DELETE
    public void deleteService(Service service);
}
