package aula1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDao extends Persistencia {

    @Override
    protected void escrevercampos() {
        tabela = "usuario";
        chave = "codigousuario";
        campos = new String []{"nomeusuario", "loginusuario", "senhausuario"};
    }

    @Override
    protected void PreparaParametrosEntrada(PreparedStatement st, ObjetoBase obj) {
        Usuario usu = (Usuario) obj;
        try {
            st.setString(1, usu.getNome());
            st.setString(2, usu.getLogin());
            st.setString(3, usu.getSenha());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected ObjetoBase PreparaParametrosSelect(ResultSet rs) {
        Usuario usu = new Usuario();
        
        
        try{
           usu.setCodigo(rs.getInt("codigousuario")); 
           usu.setNome(rs.getString("nomeusuario"));
           usu.setLogin(rs.getString("loginusuario")); 
           usu.setSenha(rs.getString("senhausuario")); 
           
           return usu;
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
    
  
    

}
