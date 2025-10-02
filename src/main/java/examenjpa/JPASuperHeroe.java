package examenjpa;

/**
 *
 * @author JOSE ALFREDO GUZMAN MORENO - 00000252524
 */

import DAO.SuperHeroeDAO;
import Entidad.SuperHeroe;
import Enum.IdentidadSecreta;
import Enum.Universo;
import Interfaz.ISuperHeroeDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class JPASuperHeroe {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SuperHeroesPU");
        EntityManager em = emf.createEntityManager();

        ISuperHeroeDAO superHeroeDAO = new SuperHeroeDAO(em);

        System.out.println("---------- PRUEBA DE INSERTAR ----------");

        IdentidadSecreta identidadSpiderMan = new IdentidadSecreta("Peter Parker", "New York", "Fotografo");
        SuperHeroe spiderMan = new SuperHeroe("SpiderMan", Universo.MARVEL, identidadSpiderMan, "El hombre araña");
        
        IdentidadSecreta identidadIronMan = new IdentidadSecreta("Tony Stark", "New York", "Empresario");
        SuperHeroe IronMan = new SuperHeroe("IronMan", Universo.MARVEL, identidadIronMan, "El hombre de hierro");

        IdentidadSecreta identidadDeadPool = new IdentidadSecreta("DeadPool", "Chicago", "Vaga por la vida");
        SuperHeroe DeadPool = new SuperHeroe("DeadPool", Universo.INDEPENDIENTE, identidadDeadPool, "Que mmda es el mejor nombre de la vida");


        superHeroeDAO.insertar(spiderMan);
        superHeroeDAO.insertar(IronMan);
        superHeroeDAO.insertar(DeadPool);
        System.out.println("---------- Se insertaron 3 superheroes ----------");

        System.out.println("\n------- PRUEBA DE LISTAR (Despues de insertar) --------");
        listarYMostrar(superHeroeDAO);

        System.out.println("\n---------- PRUEBA DE ACTUALIZAR ----------");
        SuperHeroe IronManParaModificar = superHeroeDAO.buscar(IronMan.getId());
        if (IronManParaModificar != null) {
            System.out.println("Modificando a Iron Man... cambiando su ocupacion a 'Inversionista'");
            IronManParaModificar.getIdentidadSecreta().setOcupacion("Inversionista");
            superHeroeDAO.actualizar(IronManParaModificar);
            
            System.out.println("--------- Lista despues de la modificacion: ----------");
            listarYMostrar(superHeroeDAO);
        } else {
            System.out.println("No se encontró a Iron Man para modificar.");
        }

        System.out.println("\n---------- PRUEBA DE ELIMINAR ----------");
        System.out.println("Eliminando a DeadPool...");
        superHeroeDAO.eliminar(DeadPool.getId());

        System.out.println("---------- Lista despues de la eliminacion: ----------");
        listarYMostrar(superHeroeDAO);

        System.out.println("\nCerrando conexion...");
        em.close();
        emf.close();
    }

    private static void listarYMostrar(ISuperHeroeDAO dao) {
        List<SuperHeroe> heroes = dao.listar();
        if (heroes.isEmpty()) {
            System.out.println("No hay superheroes en la base de datos");
            return;
        }
        System.out.println("----------------------------------------------------------");
        for (SuperHeroe heroe : heroes) {
            System.out.printf("ID: %d, Nombre: %s, Universo: %s, Identidad: %s (%s, %s)\n",
                heroe.getId(),
                heroe.getNombre(),
                heroe.getUniverso(),
                heroe.getIdentidadSecreta().getNombreReal(),
                heroe.getIdentidadSecreta().getOcupacion(),
                heroe.getIdentidadSecreta().getCiudad()
            );
        }
        System.out.println("----------------------------------------------------------");
    }
}