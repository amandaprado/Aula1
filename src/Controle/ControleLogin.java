package Controle;

import aula1.DaoJPA.FuncoesJPA;
import aula1.Usuario;
import java.util.List;

public class ControleLogin {
    public static String autenticar (String[] dados){
        String Where  = "where o.login = '" + dados[0] + "' and o.senha = '" + Util.Hash.hash(dados[1]) + "'";
        List<Usuario> selecionar = (List<Usuario>) FuncoesJPA.selecionar(Usuario.class, Where);
     
        if (selecionar.isEmpty()){
            return "não existe ou senha errada";
        }else{
            return null;
        }
    }
}
