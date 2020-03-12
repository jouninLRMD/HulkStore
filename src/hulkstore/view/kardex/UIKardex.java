package hulkstore_.view.inventory_;

import hulkstore_.controller.inventory_.CKardex;
import hulkstore_.model.dao.DaoException;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableCellRenderer;

/**
 * Main view of inventory_ Management
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public class UIKardex extends javax.swing.JFrame 
{    
    private CKardex controller;
    private ListSelectionModel cellSelectionModelKardex;
    private ListSelectionModel cellSelectionModelKardexDetails;
        
    /**
     * Constructor.
     * 
     * @param controller 
     * @throws hulkstore_.model.dao.DaoException 
     */
    public UIKardex(CKardex controller) throws DaoException
    {
        initComponents();
        this.setVisible(true);
        this.setTitle("INVENTARIO");
        setLocationRelativeTo(null);
        
        this.controller = controller;
        controller.uploadKardex(tblKardex);
        
        cellSelectionModelKardex = tblKardex.getSelectionModel();
        cellSelectionModelKardex.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        cellSelectionModelKardex.addListSelectionListener
        ((ListSelectionEvent event) -> {
            controller.uploadKardexDetails(tblKardex, tblKardexDetails, txtQuantity, txtUnityValue, txtTotalValue);
        });
        
        cellSelectionModelKardexDetails = tblKardexDetails.getSelectionModel();
        cellSelectionModelKardexDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        cellSelectionModelKardexDetails.addListSelectionListener
        ((ListSelectionEvent event) -> {
            controller.showDetails(tblKardex, tblKardexDetails, txtUser, txtDocumentDescription, txtDocumentNumber, txaObservations, txtState);
        });
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKardex = new javax.swing.JTable()
        {
            public Component prepareRenderer(TableCellRenderer r, int row, int column)
            {
                Component c = super.prepareRenderer(r, row, column);
                c.setBackground(Color.WHITE);
                if(column == 0 && !isCellSelected(row, column))
                {
                    c.setBackground(new Color(228, 251, 219));
                }
                else if(isCellSelected(row, column))
                {
                    c.setBackground(Color.BLUE);
                }
                return c;
            }
        };
        btnInsertKardex = new javax.swing.JButton();
        btnUpdateKardexDetails = new javax.swing.JButton();
        btnDeleteKardexDetails = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUnityValue = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTotalValue = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKardexDetails = new javax.swing.JTable()
        {
            public Component prepareRenderer(TableCellRenderer r, int row, int column)
            {
                Component c = super.prepareRenderer(r, row, column);
                c.setBackground(Color.WHITE);
                if(column == 0 && !isCellSelected(row, column))
                {
                    c.setBackground(new Color(228, 251, 219));
                }
                else if(isCellSelected(row, column))
                {
                    c.setBackground(Color.BLUE);
                }
                return c;
            }
        };
        jLabel6 = new javax.swing.JLabel();
        btnInsertKardexDetails = new javax.swing.JButton();
        btnDeleteKardex = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDocumentDescription = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDocumentNumber = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtState = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaObservations = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/hulkstore_/resources/images/inventory__icon.png")).getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setText("INVENTARIO");

        tblKardex.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblKardex.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Producto", "Producto", "Código Almacen", "Almacen", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblKardex);

        btnInsertKardex.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnInsertKardex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hulkstore_/resources/images/insert.png"))); // NOI18N
        btnInsertKardex.setText("INSERTAR");
        btnInsertKardex.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInsertKardex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertKardexActionPerformed(evt);
            }
        });

        btnUpdateKardexDetails.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnUpdateKardexDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hulkstore_/resources/images/update.png"))); // NOI18N
        btnUpdateKardexDetails.setText("MODIFICAR ÚLTIMO REGISTRO");
        btnUpdateKardexDetails.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUpdateKardexDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateKardexDetailsActionPerformed(evt);
            }
        });

        btnDeleteKardexDetails.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnDeleteKardexDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hulkstore_/resources/images/delete.png"))); // NOI18N
        btnDeleteKardexDetails.setText("ELIMINAR ÚLTIMO REGISTRO");
        btnDeleteKardexDetails.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDeleteKardexDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteKardexDetailsActionPerformed(evt);
            }
        });

        btnReport.setBackground(new java.awt.Color(51, 204, 255));
        btnReport.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btnReport.setText("GENERAR REPORTE");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hulkstore_/resources/images/back.png"))); // NOI18N
        btnBack.setText("VOLVER");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Cantidad:");

        txtQuantity.setEditable(false);
        txtQuantity.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("Valor Unitario:");

        txtUnityValue.setEditable(false);
        txtUnityValue.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setText("Valor Total:");

        txtTotalValue.setEditable(false);
        txtTotalValue.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setText("Kardex Cabecera");

        tblKardexDetails.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblKardexDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Fecha", "Operación", "Cantidad", "Val. Unitario", "Val. Total", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblKardexDetails);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setText("Kardex Detalles");

        btnInsertKardexDetails.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnInsertKardexDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hulkstore_/resources/images/insert.png"))); // NOI18N
        btnInsertKardexDetails.setText("INSERTAR");
        btnInsertKardexDetails.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInsertKardexDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertKardexDetailsActionPerformed(evt);
            }
        });

        btnDeleteKardex.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnDeleteKardex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hulkstore_/resources/images/delete.png"))); // NOI18N
        btnDeleteKardex.setText("ELIMINAR");
        btnDeleteKardex.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDeleteKardex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteKardexActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel7.setText("Más Detalles");

        txtUser.setEditable(false);
        txtUser.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setText("Usuario:");

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setText("Documento:");

        txtDocumentDescription.setEditable(false);
        txtDocumentDescription.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setText("Nro. Doc:");

        txtDocumentNumber.setEditable(false);
        txtDocumentNumber.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setText("Estado:");

        txtState.setEditable(false);
        txtState.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel12.setText("Observaciones:");

        txaObservations.setEditable(false);
        txaObservations.setColumns(20);
        txaObservations.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txaObservations.setRows(5);
        jScrollPane3.setViewportView(txaObservations);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnInsertKardexDetails)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdateKardexDetails)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteKardexDetails)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(79, 79, 79))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtState)
                                    .addComponent(txtDocumentNumber)
                                    .addComponent(txtUser)
                                    .addComponent(txtDocumentDescription)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnInsertKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDeleteKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(47, 47, 47)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtUnityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(360, 360, 360))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtUnityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertKardex)
                    .addComponent(btnDeleteKardex))
                .addGap(7, 7, 7)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDocumentDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDocumentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnInsertKardexDetails)
                    .addComponent(btnUpdateKardexDetails)
                    .addComponent(btnDeleteKardexDetails)
                    .addComponent(btnReport))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertKardexActionPerformed
        controller.insertKardex();
    }//GEN-LAST:event_btnInsertKardexActionPerformed

    private void btnUpdateKardexDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateKardexDetailsActionPerformed
        controller.updateKardexDetails(tblKardex);
    }//GEN-LAST:event_btnUpdateKardexDetailsActionPerformed

    private void btnDeleteKardexDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteKardexDetailsActionPerformed
        controller.deleteKardexDetails(tblKardex);
    }//GEN-LAST:event_btnDeleteKardexDetailsActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        controller.generateReport(tblKardex);
    }//GEN-LAST:event_btnReportActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        controller.menu();
    }//GEN-LAST:event_btnBackActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        controller.menu();
    }//GEN-LAST:event_formWindowClosing

    private void btnInsertKardexDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertKardexDetailsActionPerformed
        controller.insertKardexDetails(tblKardex);
    }//GEN-LAST:event_btnInsertKardexDetailsActionPerformed

    private void btnDeleteKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteKardexActionPerformed
        controller.deleteKardex(tblKardex, tblKardexDetails, txtState);
    }//GEN-LAST:event_btnDeleteKardexActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDeleteKardex;
    private javax.swing.JButton btnDeleteKardexDetails;
    private javax.swing.JButton btnInsertKardex;
    private javax.swing.JButton btnInsertKardexDetails;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnUpdateKardexDetails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblKardex;
    private javax.swing.JTable tblKardexDetails;
    private javax.swing.JTextArea txaObservations;
    private javax.swing.JTextField txtDocumentDescription;
    private javax.swing.JTextField txtDocumentNumber;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtTotalValue;
    private javax.swing.JTextField txtUnityValue;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
