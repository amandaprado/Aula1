package aula1;

import aula1.DaoJPA.PersistenciaJPA;

public class Aula1 {

    public static void main(String[] args) {
        Categoria c = new Categoria();
        c.setDescricao("cat");
        PersistenciaJPA<Categoria> daocat = new PersistenciaJPA<>(Categoria.class);
        daocat.salvar(c);
        
        Usuario u = new Usuario();
        
        u.setNome("Italo");
        u.setLogin("login");
        u.setSenha("senha");
        u.setCategoria(c);
        PersistenciaJPA<Usuario> DaoUsuario = new PersistenciaJPA<>(Usuario.class);
        DaoUsuario.salvar(u);
    }
}
