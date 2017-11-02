package Controle;

import aula1.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import org.json.JSONObject;

public class ControleCategoria {

    public static String Salvar(String strJson) {
        Categoria categoria = new Categoria();
        JSONObject jsonobj = new JSONObject(strJson);

        System.out.println(strJson);
        categoria.setCodigo(Integer.parseInt(jsonobj.getString("codigo")));
        categoria.setDescricao(jsonobj.getString("descricao"));

        try {
            aula1.DaoJPA.PersistenciaJPA<Categoria> p = new aula1.DaoJPA.PersistenciaJPA<>(Categoria.class);
            p.salvar(categoria);
            
            return "sucesso";
        } catch (EntityExistsException ex) {
            return "não salvou";
        }
    }

    public static ArrayList<String> SelecionarTudo() {
        aula1.DaoJPA.PersistenciaJPA<Categoria> u = new aula1.DaoJPA.PersistenciaJPA<>(Categoria.class);
        List<Categoria> recuperarTodos = u.recuperarTodos();

        ArrayList<String> categoria = new ArrayList<>();
        

        for (int i = 0; i < recuperarTodos.size(); i++) {
            JSONObject json = new JSONObject();
            json.put("codigo", recuperarTodos.get(i).getCodigo());
            json.put("descricao", recuperarTodos.get(i).getDescricao());
            
            System.out.println(json);
            categoria.add(json.toString());
        }

        return categoria;
    }

    public static String Deletar(String codigo) {

        try {
            aula1.DaoJPA.PersistenciaJPA<Categoria> u = new aula1.DaoJPA.PersistenciaJPA<>(Categoria.class);
            u.remover(Integer.parseInt(codigo));
            return "deletou";
        } catch (EntityExistsException ex) {
            return "erro";
        }
    }
}
