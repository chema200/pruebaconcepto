package org.intricom.dao;

import org.intricom.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ClientDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Client> findAll() {
        return em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }

    public Client findById(Long id) {
        return em.find(Client.class, id);
    }

    public void save(Client client) {
        em.persist(client);
    }

    public void update(Client client) {
        em.merge(client);
    }

    public void delete(Long id) {
        Client client = findById(id);
        if (client != null) {
            em.remove(client);
        }
    }
}

