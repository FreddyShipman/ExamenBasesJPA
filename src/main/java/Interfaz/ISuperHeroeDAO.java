package Interfaz;

import Entidad.SuperHeroe;
import java.util.List;

/**
 *
 * @author JOSE ALFREDO GUZMAN MORENO - 00000252524
 */
public interface ISuperHeroeDAO {
    void insertar(SuperHeroe e);
    void actualizar(SuperHeroe e);
    void eliminar(Long id);
    SuperHeroe buscar(Long id);
    List<SuperHeroe>listar();
}
