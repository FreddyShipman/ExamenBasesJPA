package DAO;

/**
 *
 * @author JOSE ALFREDO GUZMAN MORENO - 00000252524
 */
import Entidad.SuperHeroe;
import Interfaz.ISuperHeroeDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class SuperHeroeDAO implements ISuperHeroeDAO {

    private EntityManager em;
    
    public SuperHeroeDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(SuperHeroe superHeroe) {
        try {
            em.getTransaction().begin();
            em.persist(superHeroe);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(SuperHeroe superHeroe) {
        try {
            em.getTransaction().begin();
            em.merge(superHeroe);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            SuperHeroe superHeroe = buscar(id);
            if (superHeroe != null) {
                em.getTransaction().begin();
                em.remove(superHeroe);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public SuperHeroe buscar(Long id) {
        return em.find(SuperHeroe.class, id);
    }

    @Override
    public List<SuperHeroe> listar() {
        String jpql = "SELECT s FROM SuperHeroe s";
        TypedQuery<SuperHeroe> query = em.createQuery(jpql, SuperHeroe.class);
        return query.getResultList();
    }
}