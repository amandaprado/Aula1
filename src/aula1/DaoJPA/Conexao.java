package aula1.DaoJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

    private static EntityManagerFactory fabrica = null;

    public static EntityManager conexao() {
        if (fabrica == null) {
            fabrica = Persistence.createEntityManagerFactory("PersistenciaBanco");
        }
        return fabrica.createEntityManager();
    }

}
