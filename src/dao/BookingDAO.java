/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Booking;

/**
 *
 * @author luiz
 */
public interface BookingDAO {

    //CREATE
    public void createBooking(Booking booking);

    //READ
    public Booking readBooking(String str, int opt);
    
    //READ ALL
    public List<Booking> readAllBookings();

    //UPDATE
    public void updateBooking(Booking oldBooking, Booking newBooking);

    //DELETE
    public void deleteBooking(Booking booking);
}
