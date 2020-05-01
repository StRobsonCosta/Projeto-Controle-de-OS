
package br.com.nilu.telas;

import java.sql.*;
import br.com.nilu.dal.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();

    }

    private void consultar() {
        String sql = "select * from tbusuarios where iduser = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuFone.setText(rs.getString(3));
                txtUsuLogin.setText(rs.getString(4));
                txtUsuSenha.setText(rs.getString(5));
                cboUsuPerfil.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não Cadastrado");
                txtUsuNome.setText(null);
                txtUsuId.setText(null);
                txtUsuFone.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void adicionar() {
        String sql = "insert into tbusuarios (iduser, usuario, fone, login, senha, perfil) values (?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuFone.getText());
            pst.setString(4, txtUsuLogin.getText());
            pst.setString(5, txtUsuSenha.getText());
            pst.setString(6, cboUsuPerfil.getSelectedItem().toString());
            if ((txtUsuNome.getText().isEmpty()) || (txtUsuId.getText().isEmpty()) || (txtUsuFone.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty() || (txtUsuSenha.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha TODOS os Campos OBRIGATÓRIOS!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Funcionario Adicionado Com Sucesso!");
                    txtUsuNome.setText(null);
                    txtUsuId.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
        String sql = "update tbusuarios set usuario= ?, fone = ?, login= ?, senha= ?, perfil= ? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuFone.getText());
            pst.setString(3, txtUsuLogin.getText());
            pst.setString(4, txtUsuSenha.getText());
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(6, txtUsuId.getText());
            if ((txtUsuNome.getText().isEmpty()) || (txtUsuId.getText().isEmpty()) || (txtUsuFone.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty() || (txtUsuSenha.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha TODOS os Campos OBRIGATÓRIOS!");
            } else {
                int alterado = pst.executeUpdate();
                if (alterado > 0) {

                    JOptionPane.showMessageDialog(null, "Dados Alterados Com Sucesso!");
                    txtUsuNome.setText(null);
                    txtUsuId.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deletar (){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza Que Quer Remover Este Funcionario do Banco de Dados?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION);
        if (confirma==JOptionPane.YES_OPTION){
           String sql = "delete from tbusuarios where iduser = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());
                int apagado = pst.executeUpdate();
                if (apagado>0){
                    JOptionPane.showMessageDialog(null, "Funcionario Deletado com Sucesso!");
                    txtUsuNome.setText(null);
                    txtUsuId.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
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

        jLabel1 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUsuNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsuFone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUsuLogin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUsuSenha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboUsuPerfil = new javax.swing.JComboBox();
        btnUsuAdd = new javax.swing.JButton();
        btnUsuDel = new javax.swing.JButton();
        btnUsuCheck = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Funcionarios");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("*ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));
        getContentPane().add(txtUsuId, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 41, -1));

        jLabel2.setText("*Nome:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        getContentPane().add(txtUsuNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 88, 467, -1));

        jLabel3.setText("*Fone:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        txtUsuFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuFoneActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 213, -1));

        jLabel4.setText("*LOGIN:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));
        getContentPane().add(txtUsuLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 208, -1));

        jLabel5.setText("*SENHA:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, -1));
        getContentPane().add(txtUsuSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 176, 172, -1));

        jLabel6.setText("Perfil:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "admin", "user" }));
        getContentPane().add(cboUsuPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 231, 191, -1));

        btnUsuAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/Adicionar.png"))); // NOI18N
        btnUsuAdd.setToolTipText("ADICIONAR");
        btnUsuAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 311, -1, -1));

        btnUsuDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/Del.png"))); // NOI18N
        btnUsuDel.setToolTipText("DELETAR");
        btnUsuDel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDelActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 311, -1, -1));

        btnUsuCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/Pesquisar.png"))); // NOI18N
        btnUsuCheck.setToolTipText("CONSULTAR");
        btnUsuCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCheckActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 311, -1, -1));

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/Atualizar.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("ATUALIZAR");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 311, -1, -1));

        jLabel7.setText("* Campos Obrigatórios");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        setBounds(0, 0, 661, 468);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuFoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuFoneActionPerformed

    private void btnUsuCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCheckActionPerformed
        consultar();

    }//GEN-LAST:event_btnUsuCheckActionPerformed

    private void btnUsuAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuAddActionPerformed
        adicionar();
    }//GEN-LAST:event_btnUsuAddActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDelActionPerformed
        deletar();
    }//GEN-LAST:event_btnUsuDelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuAdd;
    private javax.swing.JButton btnUsuCheck;
    private javax.swing.JButton btnUsuDel;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
