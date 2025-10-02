package Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author JOSE ALFREDO GUZMAN MORENO - 00000252524
 */
public class JPAUtil {
    private static final String PERSISTENCE_UNIT = "SuperHeroesPU";
    private static EntityManagerFactory emf;
    
    private JPAUtil(){}
    
    public static EntityManager getEntityManager(){
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return emf.createEntityManager();
    }
}
