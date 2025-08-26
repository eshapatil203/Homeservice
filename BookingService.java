package com.homeservice1.service;

import java.util.List;

import com.homeservice1.dao.*;
import com.homeservice1.entity.Booking;

public class BookingService {
    private BookingDAO dao = new BookingDAO();

    public void addBooking(Booking booking) { dao.save(booking); }
    public Booking getBooking(int id) { return dao.getById(id); }
    public List<Booking> getAllBookings() { return dao.getAll(); }
    public void updateBooking(Booking booking) { dao.update(booking); }
    public void deleteBooking(int id) { dao.delete(id); }
}
