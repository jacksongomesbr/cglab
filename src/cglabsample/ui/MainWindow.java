/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cglabsample.ui;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Tools.ImageStatistics;
import Catalano.Statistics.Histogram;
import cglabsample.ISubscriber;
import cglabsample.Tools;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Jackson
 */
public class MainWindow extends javax.swing.JFrame {

    FastBitmap image;
    FastBitmap transformed_image;
    List<ISubscriber> subscribers;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        paneImagemTransformada.getVerticalScrollBar().setModel(paneImagemOriginal.getVerticalScrollBar().getModel());
        paneImagemTransformada.getHorizontalScrollBar().setModel(paneImagemOriginal.getHorizontalScrollBar().getModel());
        subscribers = new ArrayList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneImagemOriginal = new javax.swing.JScrollPane();
        paneImagemTransformada = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CGLab - Laboratório de Processamento Digital de Imagens");

        jMenu1.setText("Arquivo");

        jMenuItem2.setText("Abrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem6.setText("Salvar transformação atual");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem8.setText("Trocar imagens (original <-> transf.)");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Transformações");

        jMenuItem1.setText("Converter para cinza");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem5.setText("Equalização de histograma");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem7.setText("Op. Binária - AND");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Informações");

        jMenuItem3.setText("Dados básicos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Histograma");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneImagemOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(paneImagemTransformada, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paneImagemTransformada, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addComponent(paneImagemOriginal))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void aplicarTransformacao(int t) {
        switch (t) {
            case 1:
            default:
                transformed_image = transformarEmTonsDeCinza();
                break;
            case 2:
                transformed_image = transformarEqualizacaoDoHistograma();
                break;

        }
        atualizarImagemTransformada(transformed_image);
    }

    public FastBitmap transformarEmTonsDeCinza() {
        FastBitmap newImage = Tools.cloneFastBitmap(image);
        newImage.toGrayscale();
        return newImage;
    }

    public FastBitmap transformarEqualizacaoDoHistograma() {
        FastBitmap img = Tools.cloneFastBitmap(this.image);
        ImageStatistics is = new ImageStatistics(img);
        double e = Math.pow(2, 8) - 1;
        if (img.isGrayscale()) {
            // calcular o histograma normalizado acumulado
            double[] hvalues = histogramaAcumuladoNormalizado(is.getHistogramGray());
            for (int i = 0; i < img.getHeight(); i++) {
                for (int j = 0; j < img.getWidth(); j++) {
                    int hna = (int) (hvalues[img.getGray(i, j)] * e);
                    img.setGray(i, j, hna);
                }
            }
        } else {
            double[] histograma_r = histogramaAcumuladoNormalizado(is.getHistogramRed());
            double[] histograma_g = histogramaAcumuladoNormalizado(is.getHistogramGreen());
            double[] histograma_b = histogramaAcumuladoNormalizado(is.getHistogramBlue());
            for (int i = 0; i < img.getHeight(); i++) {
                for (int j = 0; j < img.getWidth(); j++) {
                    int hr = (int) (histograma_r[img.getRed(i, j)] * e);
                    int hg = (int) (histograma_g[img.getGreen(i, j)] * e);
                    int hb = (int) (histograma_b[img.getBlue(i, j)] * e);
                    img.setRGB(i, j, hr, hg, hb);
                }
            }
        }

        return img;
    }

    private double[] histogramaAcumuladoNormalizado(Histogram h) {
        double[] hvalues = new double[256];
        int[] hivalues = h.getValues();
        long n_pixels = h.getTotal();

        for (int i = 0; i < hivalues.length; i++) {
            hvalues[i] = (double) hivalues[i];
        }
        // normalizado
        for (int i = 0; i < 256; i++) {
            hvalues[i] = hvalues[i] / n_pixels;
        }

        // acumulado
        for (int i = 1; i < 256; i++) {
            hvalues[i] = hvalues[i] + hvalues[i - 1];
        }

        return hvalues;
    }

//    private void double[][] equalizarBanda(double[][] pixels) {
//        double[][] equalizado = new double[1][1];
//        return equalizado;
//    }
    private void atualizarImagemTransformada(FastBitmap novaImagem) {
        this.transformed_image = novaImagem;
        paneImagemTransformada.setViewportView(new JLabel(novaImagem.toIcon()));
    }
    
    private void atualizarImagens() {
        paneImagemOriginal.setViewportView(new JLabel(this.image.toIcon()));
        paneImagemTransformada.setViewportView(new JLabel(this.transformed_image.toIcon()));
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        aplicarTransformacao(1);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            image = new FastBitmap(selectedFile.toPath().toString());
            paneImagemOriginal.setViewportView(new JLabel(image.toIcon()));
            updateSubscribers();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        ISubscriber s = new DadosBasicosWindow(this);
        ((JFrame) s).setVisible(true);
        subscribers.add(s);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        ISubscriber s = new HistogramWindow(this);
        ((JFrame) s).setVisible(true);
        subscribers.add(s);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        aplicarTransformacao(2);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                transformed_image.saveAsJPG(file.getAbsolutePath());
            }
        }

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        new OpBinariaAndWindow(this).setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        FastBitmap c = Tools.cloneFastBitmap(image);
        image = transformed_image;
        transformed_image = c;

        atualizarImagens();
        updateSubscribers();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    public FastBitmap getImage() {
        return Tools.cloneFastBitmap(this.image);
    }

    public void setTransformedImage(FastBitmap t) {
        atualizarImagemTransformada(t);
    }

    private void updateSubscribers() {
        for (ISubscriber s : this.subscribers) {
            s.update();
        }
    }

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JScrollPane paneImagemOriginal;
    private javax.swing.JScrollPane paneImagemTransformada;
    // End of variables declaration//GEN-END:variables
}
