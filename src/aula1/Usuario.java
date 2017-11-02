
package aula1;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Usuario extends ObjetoBase{
    private String nome;
    private String login;
    private String senha;
    @ManyToOne
    private Categoria categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = Util.Hash.hash(senha);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", login=" + login + ", senha=" + senha + '}';
    }
    
}
