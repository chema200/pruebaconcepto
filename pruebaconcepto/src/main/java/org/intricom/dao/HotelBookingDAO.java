package org.intricom.dao;

import org.intricom.controller.ClientServlet;
import org.intricom.model.HotelBooking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class HotelBookingDAO {
    private static final Logger logger = LoggerFactory.getLogger(ClientServlet.class);

    @PersistenceContext
    private EntityManager em;

    public List<HotelBooking> findAll() {
        return em.createQuery("SELECT hb FROM HotelBooking hb", HotelBooking.class).getResultList();
    }

    public HotelBooking findById(Long id) {
        return em.find(HotelBooking.class, id);
    }

    public void save(HotelBooking hotelBooking) {
        em.persist(hotelBooking);
    }

    public void update(HotelBooking hotelBooking) {
        em.merge(hotelBooking);
    }


    public void delete(Long id) {
        try {
            // Crear una consulta nativa para eliminar el registro por ID
            em.createNativeQuery("DELETE FROM hotel_booking WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            em.flush();  // Forzar la sincronización con la base de datos
            logger.info("Eliminado correctamente el booking con id: " + id);
        } catch (Exception e) {
            logger.error("Error al eliminar la reserva: " + e.getMessage(), e);
            throw new EJBException("Error eliminando la reserva: " + e.getMessage(), e);
        }
    }


}
