package Controle;

import aula1.Categoria;
import aula1.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.json.JSONObject;

public class ControleUsuario {

    public static String Salvar(String strJson) {
        Usuario usuario = new Usuario();
        aula1.DaoJPA.PersistenciaJPA<Usuario> p = new aula1.DaoJPA.PersistenciaJPA<>(Usuario.class);
        aula1.DaoJPA.PersistenciaJPA<Categoria> c = new aula1.DaoJPA.PersistenciaJPA<>(Categoria.class);

        JSONObject jsonobj = new JSONObject(strJson);
        int codigo = jsonobj.getInt("categoria");

        System.out.println(strJson);
        usuario.setCodigo(Integer.parseInt(jsonobj.getString("codigo")));
        usuario.setNome(jsonobj.getString("nome"));
        usuario.setLogin(jsonobj.getString("login"));
        usuario.setSenha(jsonobj.getString("senha"));

        Categoria categoria = c.recuperar(codigo);
        usuario.setCategoria(categoria);
        try {
            p.salvar(usuario);

            return "sucesso";
        } catch (EntityExistsException ex) {
            return "não salvou";
        }
    }

    public static ArrayList<String> SelecionarTudo() {
        aula1.DaoJPA.PersistenciaJPA<Usuario> u = new aula1.DaoJPA.PersistenciaJPA<>(Usuario.class);
        List<Usuario> recuperarTodos = u.recuperarTodos();

        ArrayList<String> usuario = new ArrayList<>();

        for (int i = 0; i < recuperarTodos.size(); i++) {
            JSONObject json = new JSONObject();
            json.put("codigo", recuperarTodos.get(i).getCodigo());
            json.put("nome", recuperarTodos.get(i).getNome());
            json.put("login", recuperarTodos.get(i).getLogin());
            json.put("senha", recuperarTodos.get(i).getSenha());

            System.out.println(json);
            usuario.add(json.toString());
        }

        return usuario;
    }

    public static String Deletar(String codigo) {

        try {
            aula1.DaoJPA.PersistenciaJPA<Usuario> u = new aula1.DaoJPA.PersistenciaJPA<>(Usuario.class);
            u.remover(Integer.parseInt(codigo));
            return "deletou";
        } catch (EntityExistsException ex) {
            return "erro";
        }
    }
}
