/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nilu.telas;

import java.sql.*;
import br.com.nilu.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author user
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();

    }

    private void adicionar() {
        String sql = "insert into tbclientes ( nomecli, fonecli, endcli, emailcli) values (?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliFone.getText());
            pst.setString(3, txtCliEnd.getText());
            pst.setString(4, txtCliEmail.getText());
            if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty()) || (txtCliEnd.getText().isEmpty() || (txtCliEmail.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha TODOS os Campos OBRIGATÓRIOS!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente Adicionado Com Sucesso!");
                    txtCliNome.setText(null);
                    txtCliId.setText(null);
                    txtCliFone.setText(null);
                    txtCliEnd.setText(null);
                    txtCliEmail.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar_cliente() {
        String sql = "select * from tbclientes where nomecli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setar_campos() {
        int setar = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtCliEnd.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
        btnCliAdd.setEnabled(false);

    }

    private void alterar_cliente() {
        String sql = "update tbclientes set nomecli= ?, fonecli = ?, endcli= ?, emailcli= ? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliFone.getText());
            pst.setString(3, txtCliEnd.getText());
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtCliId.getText());
            if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty()) || (txtCliEnd.getText().isEmpty() || (txtCliEmail.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha TODOS os Campos OBRIGATÓRIOS!");
            } else {
                int alterado = pst.executeUpdate();
                if (alterado > 0) {

                    JOptionPane.showMessageDialog(null, "Dados Alterados Com Sucesso!");
                    txtCliNome.setText(null);
                    txtCliId.setText(null);
                    txtCliFone.setText(null);
                    txtCliEnd.setText(null);
                    txtCliEmail.setText(null);
                    btnCliAdd.setEnabled(true);
                    
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deletar_cliente (){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza Que Quer Remover Este Cliente do Banco de Dados?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION);
        if (confirma==JOptionPane.YES_OPTION){
           String sql = "delete from tbclientes where iduser = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliId.getText());
                int apagado = pst.executeUpdate();
                if (apagado>0){
                    JOptionPane.showMessageDialog(null, "Cliente Deletado com Sucesso!");
                    txtCliNome.setText(null);
                    txtCliId.setText(null);
                    txtCliFone.setText(null);
                    txtCliEnd.setText(null);
                    txtCliEmail.setText(null);
                    btnCliAdd.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
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

        txtCliPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        txtCliNome = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCliEmail = new javax.swing.JTextField();
        txtCliEnd = new javax.swing.JTextField();
        btnCliAdd = new javax.swing.JButton();
        btnCliEdit = new javax.swing.JButton();
        btnCliDel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtCliPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 230, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/cliente.add.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel2.setText("*Campos Obrigatórios");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 16, -1, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 76, 655, 101));

        jLabel3.setText("Nome*:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 248, -1, -1));

        jLabel4.setText("Fone*:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 289, -1, -1));

        jLabel5.setText("Endereço*:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 336, -1, -1));

        jLabel6.setText("ID Cliente:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 212, -1, -1));

        txtCliId.setEnabled(false);
        getContentPane().add(txtCliId, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 206, 43, -1));
        getContentPane().add(txtCliNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 248, 468, -1));
        getContentPane().add(txtCliFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 286, 145, -1));

        jLabel7.setText("E-Mail:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 289, -1, -1));
        getContentPane().add(txtCliEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 286, 262, -1));
        getContentPane().add(txtCliEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 330, 464, -1));

        btnCliAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/cliente.find.png"))); // NOI18N
        btnCliAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnCliAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 405, -1, -1));

        btnCliEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/cliente.edit.png"))); // NOI18N
        btnCliEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnCliEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 405, -1, -1));

        btnCliDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/cliente.del.png"))); // NOI18N
        btnCliDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliDelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCliDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 405, -1, -1));

        setBounds(0, 0, 729, 536);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAddActionPerformed
        adicionar();
    }//GEN-LAST:event_btnCliAddActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setar_campos();

    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnCliEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliEditActionPerformed
        alterar_cliente();
    }//GEN-LAST:event_btnCliEditActionPerformed

    private void btnCliDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliDelActionPerformed
        deletar_cliente();
    }//GEN-LAST:event_btnCliDelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliAdd;
    private javax.swing.JButton btnCliDel;
    private javax.swing.JButton btnCliEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEnd;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    // End of variables declaration//GEN-END:variables
}
