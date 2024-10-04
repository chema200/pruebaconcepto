package org.intricom.dao;

import org.intricom.model.Hotel;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class HotelDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Hotel> findAll() {
        return em.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
    }

    public Hotel findById(Long id) {
        return em.find(Hotel.class, id);
    }

    public void save(Hotel hotel) {
        em.persist(hotel);
    }

    public void update(Hotel hotel) {
        em.merge(hotel);
    }

    public void delete(Long id) {
        Hotel hotel = findById(id);
        if (hotel != null) {
            em.remove(hotel);
        }
    }
}
