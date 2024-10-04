package org.intricom.service;

import org.intricom.dao.HotelDAO;
import org.intricom.model.Hotel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class HotelService {

    @Inject
    private HotelDAO hotelDAO;

    public List<Hotel> findAll() {
        return hotelDAO.findAll();
    }

    public Hotel findById(Long id) {
        return hotelDAO.findById(id);
    }

    public void create(Hotel hotel) {
        hotelDAO.save(hotel);
    }

    public void update(Hotel hotel) {
        hotelDAO.update(hotel);
    }

    public void delete(Long id) {
        hotelDAO.delete(id);
    }
}
