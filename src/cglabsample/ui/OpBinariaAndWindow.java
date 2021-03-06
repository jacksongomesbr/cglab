/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cglabsample.ui;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.And;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author Jackson
 */
public class OpBinariaAndWindow extends javax.swing.JFrame {
    private MainWindow mainWindow;
    private FastBitmap image;
    
    /**
     * Creates new form OpBinariaAndWindow
     */
    public OpBinariaAndWindow() {
        initComponents();
    }
    
    public OpBinariaAndWindow(MainWindow mainWindow) {
        this();
        this.mainWindow = mainWindow;
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonAbrirImagem = new javax.swing.JButton();
        buttonAplicar = new javax.swing.JButton();
        panelImagem = new javax.swing.JScrollPane();

        buttonAbrirImagem.setText("Abrir imagem");
        buttonAbrirImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAbrirImagemActionPerformed(evt);
            }
        });

        buttonAplicar.setText("Aplicar AND");
        buttonAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAplicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImagem)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonAbrirImagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonAplicar)
                        .addGap(0, 341, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAbrirImagem)
                    .addComponent(buttonAplicar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAbrirImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAbrirImagemActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            image = new FastBitmap(selectedFile.toPath().toString());
            panelImagem.setViewportView(new JLabel(image.toIcon()));
        }        
    }//GEN-LAST:event_buttonAbrirImagemActionPerformed

    private void buttonAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAplicarActionPerformed
        // TODO add your handling code here:
        FastBitmap originalImage = mainWindow.getImage();
        And filter = new And(image);
        filter.applyInPlace(originalImage);
        mainWindow.setTransformedImage(originalImage);
    }//GEN-LAST:event_buttonAplicarActionPerformed

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
            java.util.logging.Logger.getLogger(OpBinariaAndWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpBinariaAndWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpBinariaAndWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpBinariaAndWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OpBinariaAndWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAbrirImagem;
    private javax.swing.JButton buttonAplicar;
    private javax.swing.JScrollPane panelImagem;
    // End of variables declaration//GEN-END:variables
}
