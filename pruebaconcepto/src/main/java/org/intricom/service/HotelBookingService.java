package org.intricom.service;


import org.intricom.dao.HotelBookingDAO;
import org.intricom.model.HotelBooking;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class HotelBookingService {

    @Inject
    private HotelBookingDAO hotelBookingDAO;

    public List<HotelBooking> findAll() {
        return hotelBookingDAO.findAll();
    }

    public HotelBooking findById(Long id) {
        return hotelBookingDAO.findById(id);
    }

    public void create(HotelBooking hotelBooking) {
        hotelBookingDAO.save(hotelBooking);
    }

    public void update(HotelBooking hotelBooking) {
        hotelBookingDAO.update(hotelBooking);
    }

    public void delete(Long id) {
        hotelBookingDAO.delete(id);
    }
}
