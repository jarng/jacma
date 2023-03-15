package com.mycompany.cafe_management_app.ui.DashboardStaffUI;

import com.mycompany.cafe_management_app.ui.DashboardAdminUI.*;
import com.mycompany.cafe_management_app.controller.DashboardStaffController;
import com.mycompany.cafe_management_app.controller.DashboardStaffController.DetailsDishFunction;

import com.mycompany.cafe_management_app.model.Dish;

import javax.swing.JLabel;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author Trung
 */
public class DetailsItemStaff extends javax.swing.JPanel {

    private Dish dish;
    private DetailsDishFunction detailsDishFunction;

    public DetailsItemStaff(Dish dish, String size, String price, DetailsDishFunction detailsDishFunction) {
        initComponents();
        this.dish = dish;
        this.sizeLabel.setText(size);
        this.priceLabel.setText(price);
        this.detailsDishFunction = detailsDishFunction;
    }

    public JButton getPriceLabel() {
        return priceLabel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sizeLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(150, 30));
        setMinimumSize(new java.awt.Dimension(150, 30));
        setPreferredSize(new java.awt.Dimension(150, 30));

        sizeLabel.setForeground(new java.awt.Color(51, 51, 51));
        sizeLabel.setText(".");

        priceLabel.setBackground(new java.awt.Color(255, 255, 255));
        priceLabel.setText(".");
        priceLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        priceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        priceLabel.setMaximumSize(new java.awt.Dimension(23, 23));
        priceLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceLabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(sizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18,
                                        Short.MAX_VALUE)
                                .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sizeLabel)
                                        .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    private void priceLabelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_priceLabelActionPerformed
        String name = dish.getName();
        Double price = Double.parseDouble(priceLabel.getText());
        String size = sizeLabel.getText();
        detailsDishFunction.addDishes(dish, size, price);

    }// GEN-LAST:event_priceLabelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton priceLabel;
    private javax.swing.JLabel sizeLabel;
    // End of variables declaration//GEN-END:variables
}
