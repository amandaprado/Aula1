package aula1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Persistencia {

    protected String tabela;
    protected String chave;
    protected String[] campos;

    private String sqlInsert;
    private String sqlUpdate;
    private String sqlDelete;
    private String select;

    public Persistencia() {
        escrevercampos();
        formatSqlInsert();
        formatSqlDelete();
        formatSqlSelect();
    }

    protected void formatSqlInsert() {
        sqlInsert = "insert into " + tabela + " (";
        for (int i = 0; i < campos.length; i++) {
            sqlInsert += campos[i];
            if (i < campos.length - 1) {
                sqlInsert = sqlInsert + ",";
            }
        }
        sqlInsert = sqlInsert + ") values (";
        for (int i = 0; i < campos.length; i++) {
            sqlInsert = sqlInsert + "?";
            if (i < campos.length - 1) {
                sqlInsert = sqlInsert + ",";
            }
        }
        sqlInsert = sqlInsert + ")";
        System.out.println(sqlInsert);

    }

    protected void formatSqlDelete() {
        sqlDelete = "delete from " + tabela + " where " + chave + " = ?";

    }

    protected void formatSqlSelect() {
        select = "select * from " + tabela + " where " + chave + " = ?";
    }

    protected abstract void escrevercampos();

    //protected void formatsqlUpdate(){
    //  sqlUpdate = "update"
    //}
    protected abstract void PreparaParametrosEntrada(PreparedStatement st, ObjetoBase obj);
    protected abstract ObjetoBase PreparaParametrosSelect (ResultSet rs);

    public void executarsql(String sql) {

    }

    public String Salvar(ObjetoBase obj) {
        String msgRetorno = "Operação realizada com sucesso";
        Connection cnx;
        try {
            cnx = FabricaConexao.GeraConexao();
        } catch (SQLException ex) {
            return "ERRO";
        }
        PreparedStatement st;
        String sql;

        if (obj.getCodigo() > 0) {
            sql = sqlUpdate;
        } else {
            sql = sqlInsert;
        }
        try {
            st = cnx.prepareStatement(sql);
            PreparaParametrosEntrada(st, obj);
            if (obj.getCodigo() > 0) {
                st.setInt(campos.length + 1, obj.getCodigo());
            }
            st.executeUpdate();
        } catch (Exception e) {
            msgRetorno = "ERRO" + e.getMessage();
        }
        return msgRetorno;
    }

    public ObjetoBase select(int codigo) {
        Connection cnx;
        ObjetoBase obj;
        try {
            cnx = FabricaConexao.GeraConexao();
        } catch (SQLException ex) {
            return null;
        }
        PreparedStatement st;
        String sql;
        sql = select;

        try {
            st = cnx.prepareStatement(sql);
            st.setInt(1, codigo);
            
            ResultSet rs = st.executeQuery();
            rs.next();
            return PreparaParametrosSelect(rs);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        
    }

    public String excluir(int codigo) {
        String msgRetorno = "Operação realizada com sucesso";
        Connection cnx;

        try {
            cnx = FabricaConexao.GeraConexao();
        } catch (SQLException ex) {
            return "ERRO";
        }
        PreparedStatement st;
        String sql;
        sql = sqlDelete;

        try {
            st = cnx.prepareStatement(sql);
            st.setInt(1, codigo);
            st.executeUpdate();
        } catch (Exception e) {
            msgRetorno = "ERRO" + e.getMessage();
        }
        return msgRetorno;
    }

}
