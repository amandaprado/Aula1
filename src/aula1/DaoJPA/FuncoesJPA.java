package aula1.DaoJPA;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class FuncoesJPA {
    
    public static EntityManager abrirTransacao(){
        EntityManager em = Conexao.conexao();
        em.getTransaction().begin();
        return em;
    }
    
    public static void fecharTransacao(EntityManager em, boolean commit){
        if(commit)
            em.getTransaction().commit();
        else
            em.getTransaction().rollback();
    }
    
    
    public static void persistir(Object obj){
        EntityManager em = abrirTransacao();
        em.persist(obj);
        fecharTransacao(em, true);
    }
    
    public static void atualizar(Object obj){
        EntityManager em = abrirTransacao();
        em.merge(obj);
        fecharTransacao(em, true);
    }
    public static void remover (int chave, Class classe){
        EntityManager em= abrirTransacao();
        Object obj = em.find(classe, chave);
        em.remove(obj);
        fecharTransacao(em, true);
    }
    
    public static Object recuperar(int chave, Class classe){
        EntityManager em = abrirTransacao();
        return em.find(classe, chave);
    }
    
    public static List<?> selecionar(Class classe, String sWhere){
        EntityManager em = Conexao.conexao();
        
        String jpql = "select o from " + classe.getName() + " o " + sWhere;
        
        Query q = em.createQuery(jpql);
        return q.getResultList();
    }
        
    
    
    
}
