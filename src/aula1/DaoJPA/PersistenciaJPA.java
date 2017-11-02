package aula1.DaoJPA;

import aula1.ObjetoBase;
import java.util.List;

public class PersistenciaJPA <T extends ObjetoBase>{
    private final Class<T> classePersistencia;
            
    public PersistenciaJPA(Class<T> c){
        classePersistencia = c;
    }
    
    public void salvar(T obj){
        if(obj.getCodigo() > 0){
            FuncoesJPA.atualizar(obj);
        }else{
            FuncoesJPA.persistir(obj);
        }
    }
    public void remover (int chave){
       FuncoesJPA.remover(chave, classePersistencia);
    }
    
    public T recuperar(int chave){
        return (T) FuncoesJPA.recuperar(chave, classePersistencia);
    }
    
    public List<T>recuperarTodos(){
        return (List<T>) FuncoesJPA.selecionar(classePersistencia, " ");
    }
    
    
}
