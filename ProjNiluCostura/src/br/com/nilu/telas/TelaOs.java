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
public class TelaOs extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private String tipo;

    /**
     * Creates new form TelaOs
     */
    public TelaOs() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void pesquisar_cliente() {
        String sql2 = "select O.os, C.nomecli as Nome, fonecli as Fone, idcli as ID from tbos as O inner join tbclientes as C on (O.idcli = C.iduser) where nomecli like ?";

        try {
            pst = conexao.prepareStatement(sql2);
            pst.setString(1, txtCliente.getText() + "%");
            rs = pst.executeQuery();
            tblCliente.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setar_campos() {
        
        int setar = tblCliente.getSelectedRow();
        txtID.setText(tblCliente.getModel().getValueAt(setar, 3).toString());
       
    }

    private void emitir_os() {
        String sql = "insert into tbos (tipo, situacao, servico,peca, cor, qnt, obs, resp, total, idcli ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboSit.getSelectedItem().toString());
            pst.setString(3, cboServ.getSelectedItem().toString());
            pst.setString(4, txtPeca.getText());
            pst.setString(5, txtCor.getText());
            pst.setString(6, spnQnt.getValue().toString());
            pst.setString(7, txtObs.getText());
            pst.setString(8, txtCost.getText());
            pst.setString(9, txtTotal.getText());
            pst.setString(10, txtID.getText());

            if ((txtID.getText().isEmpty()) || (txtPeca.getText().isEmpty()) || (txtCor.getText().isEmpty()) || (txtCost.getText().isEmpty()) || (txtTotal.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS emitida com Sucesso!");
                    txtPeca.setText(null);
                    txtCor.setText(null);
                    txtObs.setText(null);
                    txtCost.setText(null);
                    txtTotal.setText(null);
                    txtID.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void pesquisar_os() {
        //int setar = tblCliente.getSelectedRow();
        String num_os = JOptionPane.showInputDialog("Digite o Número da O.S!");// String num_id ....("Digite o n° do ID"); 2ª opc
        String sql = "select * from tbos where os =" + num_os;
        //String sql2= "select * from tbos where idcli = "+num_id;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtOS.setText(rs.getString(1));
                txtData.setText(rs.getString(2));                

                //txtCliente.setText(tblCliente.getModel().getValueAt(setar, 1).toString());
                String rbtTipo = rs.getString(3);
                if (rbtTipo.equals("servico")) {
                    rbtServ.setSelected(true);
                    tipo = "servico";
                   
                } else {
                    rbtVenda.setSelected(true);
                    tipo = "venda";

                }
                 cboSit.setSelectedItem(rs.getString(4));
                 cboServ.setSelectedItem(rs.getString(5));
                 txtPeca.setText(rs.getString(6));
                 txtCor.setText(rs.getString(7));
                 //spnQnt.setValue(rs.getString(8));
                 txtObs.setText(rs.getString(9));
                 txtCost.setText(rs.getString(10));
                 txtTotal.setText(rs.getString(12));
                 txtID.setText(rs.getString(11));

                 btnOsAdd.setEnabled(false);
                 txtCliente.setEnabled(false);
                 tblCliente.setEnabled(false); 
            } else {
                JOptionPane.showMessageDialog(null, "O.S Ainda Não Cadastrada");
            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "O.S Invalida!");

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);

        }
    }

    private void editar_os() {
        String sql = "update tbos set tipo=?,situacao=?, servico=?,peca=?, cor=?, qnt=?, obs=?, resp=?, total=? where os = ? ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboSit.getSelectedItem().toString());
            pst.setString(3, cboServ.getSelectedItem().toString());
            pst.setString(4, txtPeca.getText());
            pst.setString(5, txtCor.getText());
            pst.setString(6, spnQnt.getValue().toString());
            pst.setString(7, txtObs.getText());
            pst.setString(8, txtCost.getText());
            pst.setString(9, txtTotal.getText());
            pst.setString(10, txtOS.getText());

            if ((txtID.getText().isEmpty()) || (txtPeca.getText().isEmpty()) || (txtCor.getText().isEmpty()) || (txtCost.getText().isEmpty()) || (txtTotal.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "O.S Editada com Sucesso!");
                    txtPeca.setText(null);
                    txtCor.setText(null);
                    txtObs.setText(null);
                    txtCost.setText(null);
                    txtTotal.setText(null);
                    txtID.setText(null);
                    txtOS.setText(null);
                    txtData.setText(null);

                    btnOsAdd.setEnabled(true);
                    txtCliente.setEnabled(true);
                    tblCliente.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void delete_os() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbos where os = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtOS.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "OS Excluída Com Sucesso!");
                    txtPeca.setText(null);
                    txtCor.setText(null);
                    txtObs.setText(null);
                    txtCost.setText(null);
                    txtTotal.setText(null);
                    txtID.setText(null);
                    txtOS.setText(null);
                    txtData.setText(null);

                    btnOsAdd.setEnabled(true);
                    txtCliente.setEnabled(true);
                    tblCliente.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }
    private void costura (){
        String sql = "select usuario from tbusuarios";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()){
                cboCostura.setSelectedItem(rs.getString(1));                
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOS = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbtVenda = new javax.swing.JRadioButton();
        rbtServ = new javax.swing.JRadioButton();
        cboServ = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboSit = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtPeca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        spnQnt = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        txtObs = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCost = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtCliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtDesc = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnOsAdd = new javax.swing.JButton();
        btnOsEdit = new javax.swing.JButton();
        btnOsDel = new javax.swing.JButton();
        btnOsSearch = new javax.swing.JButton();
        cboCostura = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        setPreferredSize(new java.awt.Dimension(640, 480));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nº O.S");

        jLabel2.setText("Data");

        txtOS.setEditable(false);

        txtData.setEditable(false);

        buttonGroup1.add(rbtVenda);
        rbtVenda.setText("Venda");
        rbtVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtVendaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtServ);
        rbtServ.setText("Serviço");
        rbtServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtServActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtVenda))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtData))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbtServ)))
                        .addGap(0, 71, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtVenda)
                    .addComponent(rbtServ))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 16, -1, -1));

        cboServ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Barra a Mão", "Barra Comum", "Reparo Simples", "Reparo Complexo", "Zíper", "Sob Medida", "Outros" }));
        getContentPane().add(cboServ, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 155, -1, -1));

        jLabel3.setText("Ajustes:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 158, -1, -1));

        jLabel4.setText("Situação:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 193, -1, -1));

        cboSit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Na Fila", "Entregue OK", "Finalizado", "Em Execução", "Aguarda Guarnição", "Inapto (Reprovado)", "Abandonado" }));
        getContentPane().add(cboSit, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 190, 153, -1));

        jLabel5.setText("Peças:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 228, -1, -1));
        getContentPane().add(txtPeca, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 225, 514, -1));

        jLabel6.setText("Cor:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 263, -1, -1));
        getContentPane().add(txtCor, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 260, 147, -1));

        jLabel7.setText("Quantidade:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 263, -1, -1));

        spnQnt.setModel(new javax.swing.SpinnerNumberModel(0, 0, 31, 1));
        spnQnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spnQntMouseClicked(evt);
            }
        });
        getContentPane().add(spnQnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 260, 61, -1));

        jLabel8.setText("Obs:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 298, -1, -1));
        getContentPane().add(txtObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 295, 514, -1));

        jLabel9.setText("Costureira Resp:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 333, -1, -1));
        getContentPane().add(txtCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 330, 187, -1));

        jLabel10.setText("Sub-Total:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 333, -1, -1));
        getContentPane().add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 330, 142, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/miniLupa_search_48796.png"))); // NOI18N

        jLabel12.setText("ID*");

        txtID.setEditable(false);

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Fone", "O.S.", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 16, -1, 190));

        jLabel13.setText("Desconto à Vista (opcional):");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 368, -1, -1));
        getContentPane().add(txtDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 365, 105, -1));

        jLabel14.setText("Total:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 368, -1, -1));
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 365, 142, -1));

        btnOsAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/file_add_48761.png"))); // NOI18N
        btnOsAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 420, -1, -1));

        btnOsEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/file_edit_48763.png"))); // NOI18N
        btnOsEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 420, -1, -1));

        btnOsDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/file_delete_48762.png"))); // NOI18N
        btnOsDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsDelActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 420, -1, -1));

        btnOsSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/nilu/icons/file_search_48764.png"))); // NOI18N
        btnOsSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 420, -1, -1));

        cboCostura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboCosturaMouseClicked(evt);
            }
        });
        cboCostura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCosturaActionPerformed(evt);
            }
        });
        getContentPane().add(cboCostura, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 120, -1));

        setBounds(0, 0, 864, 535);
    }// </editor-fold>//GEN-END:initComponents

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        pesquisar_cliente();
    }//GEN-LAST:event_txtClienteKeyReleased

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblClienteMouseClicked

    private void rbtVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtVendaActionPerformed
        tipo = "venda";
    }//GEN-LAST:event_rbtVendaActionPerformed

    private void rbtServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtServActionPerformed
        tipo = "servico";
    }//GEN-LAST:event_rbtServActionPerformed

    private void btnOsAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAddActionPerformed
        emitir_os();
    }//GEN-LAST:event_btnOsAddActionPerformed

    private void spnQntMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnQntMouseClicked
        String servico = cboServ.getSelectedItem().toString();
        double valor = 0;
        int mult = Integer.parseInt(spnQnt.getValue().toString());
        if (servico.equals("Barra a Mão")) {
            valor = (25) * mult;
        } else if (servico.equals("Barra Comum")) {
            valor = (15) * mult;
        } else if (servico.equals("Reparo Simples")) {
            valor = (20) * mult;
        } else if (servico.equals("Reparo Complexo")) {
            valor = (35) * mult;
        } else if (servico.equals("Zíper")) {
            valor = (10) * mult;
        } else if (servico.equals("Sob Medida")) {
            valor = (50) * mult;
        }
        String total = Double.toString(valor);
        txtSubTotal.setText(total);
    }//GEN-LAST:event_spnQntMouseClicked

    private void btnOsSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsSearchActionPerformed
        pesquisar_os();
    }//GEN-LAST:event_btnOsSearchActionPerformed

    private void btnOsEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsEditActionPerformed
        editar_os();
    }//GEN-LAST:event_btnOsEditActionPerformed

    private void btnOsDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsDelActionPerformed
        delete_os();
    }//GEN-LAST:event_btnOsDelActionPerformed

    private void cboCosturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCosturaActionPerformed
        costura();
    }//GEN-LAST:event_cboCosturaActionPerformed

    private void cboCosturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboCosturaMouseClicked
        costura();
    }//GEN-LAST:event_cboCosturaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsAdd;
    private javax.swing.JButton btnOsDel;
    private javax.swing.JButton btnOsEdit;
    private javax.swing.JButton btnOsSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboCostura;
    private javax.swing.JComboBox cboServ;
    private javax.swing.JComboBox cboSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtServ;
    private javax.swing.JRadioButton rbtVenda;
    private javax.swing.JSpinner spnQnt;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDesc;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtOS;
    private javax.swing.JTextField txtObs;
    private javax.swing.JTextField txtPeca;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
