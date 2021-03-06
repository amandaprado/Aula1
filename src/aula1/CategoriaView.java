package aula1;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;

public class CategoriaView extends javax.swing.JFrame {

    private String estado = "navegacao";
    private String codigo;
    ArrayList<String> categorias = new ArrayList<>();

    public CategoriaView() {
        categorias = Controle.ControleCategoria.SelecionarTudo();
        initComponents();
        AtivarInsercao(false);
        CarregarTabela();
    }

    private void CarregarTabela() {
        for (String categoria : categorias) {
            InserirGrade(categoria);
        }
    }

    private void InserirGrade(String dados) {
        DefaultTableModel modelo = (DefaultTableModel) tabelacategoria.getModel();
        JSONObject jsonobj = new JSONObject(dados);
        
        String[] teste = new String[2];
        teste[0] = Integer.toString(jsonobj.getInt("codigo"));
        teste[1] = jsonobj.getString("descricao");

        modelo.addRow(teste);
    }
    
     private void InserirGradeInsercao() {
                 categorias = Controle.ControleCategoria.SelecionarTudo();
                CarregarTabela();
    }
    public void limpar() {
        codigocategoria.setText("");
        descricaocategoria.setText("");
    }

    private void AtivarInsercao(boolean estado) {

        //Controlador de botões
        botaonovo.setEnabled(!estado);
        botaogravar.setEnabled(estado);
        botaocancelar.setEnabled(estado);
        botaoeditar.setEnabled(!estado);
        botaoexcluir.setEnabled(!estado);

        AtivarInsercao2(estado);
    }

    private void AtivarInsercao2(boolean estado) {

        //Controlador de inserção
        codigocategoria.setEditable(/*!estado*/false);
        descricaocategoria.setEditable(estado);
    }

    private void EditarGrade(String[] dados) {
        int iLinha = tabelacategoria.getSelectedRow();

        DefaultTableModel modelo = (DefaultTableModel) tabelacategoria.getModel();
        modelo.setValueAt(dados[0], iLinha, 0);
        modelo.setValueAt(dados[1], iLinha, 1);
    }

    private void RemoverDaGrade() {
        int iLinha = tabelacategoria.getSelectedRow();

        DefaultTableModel modelo = (DefaultTableModel) tabelacategoria.getModel();
        modelo.removeRow(iLinha);
    }

    private void Gravar() {
        String dados[] = new String[2];

        JSONObject json = new JSONObject();
        if (estado.equals("INSERT")) {
            json.put("codigo", "0");
            json.put("descricao", descricaocategoria.getText());
            String strJson = json.toString();
            Controle.ControleCategoria.Salvar(strJson);

            //InserirGrade(strJson);
               InserirGradeInsercao();
        } else {
            if (estado.equals("UPDATE")) {
                json.put("codigo", codigocategoria.getText());
                json.put("descricao", descricaocategoria.getText());
                String strJson = json.toString();
                Controle.ControleCategoria.Salvar(strJson);
                InserirGradeInsercao();
            } else if (estado.equals("DELETE")) {
                Controle.ControleCategoria.Deletar(dados[0]);
                RemoverDaGrade();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        codigocategoria = new javax.swing.JTextField();
        descricaocategoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelacategoria = new javax.swing.JTable();
        botaonovo = new javax.swing.JButton();
        botaogravar = new javax.swing.JButton();
        botaoeditar = new javax.swing.JButton();
        botaoexcluir = new javax.swing.JButton();
        botaocancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Categoria");

        jLabel2.setText("Código");

        jLabel3.setText("Descrição");

        tabelacategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição"
            }
        ));
        tabelacategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelacategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelacategoria);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(descricaocategoria))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(codigocategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(501, 501, 501))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(codigocategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(descricaocategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaonovo.setText("Novo");
        botaonovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaonovoActionPerformed(evt);
            }
        });

        botaogravar.setText("Gravar");
        botaogravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaogravarActionPerformed(evt);
            }
        });

        botaoeditar.setText("Editar");
        botaoeditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoeditarActionPerformed(evt);
            }
        });

        botaoexcluir.setText("Excluir");
        botaoexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoexcluirActionPerformed(evt);
            }
        });

        botaocancelar.setText("Cancelar");
        botaocancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaocancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(botaonovo)
                        .addGap(37, 37, 37)
                        .addComponent(botaogravar)
                        .addGap(39, 39, 39)
                        .addComponent(botaoeditar)
                        .addGap(27, 27, 27)
                        .addComponent(botaoexcluir)
                        .addGap(18, 18, 18)
                        .addComponent(botaocancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaonovo)
                    .addComponent(botaogravar)
                    .addComponent(botaoeditar)
                    .addComponent(botaoexcluir)
                    .addComponent(botaocancelar))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaonovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaonovoActionPerformed
        // TODO add your handling code here:
        limpar();
        estado = "INSERT";
        AtivarInsercao(true);
        descricaocategoria.requestFocus();
    }//GEN-LAST:event_botaonovoActionPerformed

    private void botaogravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaogravarActionPerformed
        // TODO add your handling code here:
        Gravar();
        limpar();
        estado = "navegacao";
        AtivarInsercao(false);
    }//GEN-LAST:event_botaogravarActionPerformed

    private void botaoeditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoeditarActionPerformed
        // TODO add your handling code here:
        estado = "UPDATE";
        AtivarInsercao(true);
    }//GEN-LAST:event_botaoeditarActionPerformed

    private void botaoexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoexcluirActionPerformed
        // TODO add your handling code here:
        estado = "DELETE";
        int iLinha = tabelacategoria.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tabelacategoria.getModel();
        codigo = (String) modelo.getValueAt(iLinha, 0);
        codigocategoria.setText(codigo);
        Controle.ControleCategoria.Deletar(codigo);
        RemoverDaGrade();
        AtivarInsercao(true);
    }//GEN-LAST:event_botaoexcluirActionPerformed

    private void botaocancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaocancelarActionPerformed
        // TODO add your handling code here:
        limpar();
        estado = "navegacao";
        AtivarInsercao(false);
    }//GEN-LAST:event_botaocancelarActionPerformed

    private void tabelacategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelacategoriaMouseClicked
        // TODO add your handling code here:
        int iLinha = tabelacategoria.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tabelacategoria.getModel();

        String codigo = (String) modelo.getValueAt(iLinha, 0);
        String descricao = (String) modelo.getValueAt(iLinha, 1);

        codigocategoria.setText(codigo);
        descricaocategoria.setText(descricao);
    }//GEN-LAST:event_tabelacategoriaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CategoriaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaocancelar;
    private javax.swing.JButton botaoeditar;
    private javax.swing.JButton botaoexcluir;
    private javax.swing.JButton botaogravar;
    private javax.swing.JButton botaonovo;
    private javax.swing.JTextField codigocategoria;
    private javax.swing.JTextField descricaocategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelacategoria;
    // End of variables declaration//GEN-END:variables
}
