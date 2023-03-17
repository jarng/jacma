/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cafe_management_app.ui.DashboardStaffUI;

import com.mycompany.cafe_management_app.model.Dish;
import com.mycompany.cafe_management_app.model.DishDetail;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Administrator
 */
public class NewDishForm extends javax.swing.JPanel {

    /**
     * Creates new form NewDishForm
     */
    private Dish dish;

    private DishDetail dishDetail;

    public NewDishForm(DishDetail dishDetail) {
        initComponents();
//        this.dish = dish;
        this.dishDetail = dishDetail;

        // System.out.println(dishDetail.getId() + " "  + dishDetail.getSize() + " " + dishDetail.getPrice());
        if (dishDetail.getDish() != null && dishDetail.getSize() != null && dishDetail.getPrice() != null) {
            inventoryNameLabel.setText(dishDetail.getDish().getName());
            dishSizeLabel.setText(dishDetail.getSize());
            dishPriceLabel.setText(dishDetail.getPrice().toString());
        }
    }

    public Long getDishDetailId() {
        return dishDetail.getId();
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
    
    public String getInventoryNameLabel() {
        return inventoryNameLabel.getText();
    }

    public String getDishSizeLabel() {
        return dishSizeLabel.getText();
    }

    public String getDishPriceLabel() {
        return dishPriceLabel.getText();
    }

    public String getQuantityDish() {
        return quantityDish.getText();
    }

    public void setQuantityDish(String quantityDish) {
        this.quantityDish.setText(quantityDish);
    }

    public JButton getDeleteDishButton() {
        return deleteDishButton;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inventoryNameLabel = new javax.swing.JLabel();
        dishSizeLabel = new javax.swing.JLabel();
        dishPriceLabel = new javax.swing.JLabel();
        deleteDishButton = new javax.swing.JButton();
        quantityDish = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(380, 32));
        setMinimumSize(new java.awt.Dimension(380, 32));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(380, 32));

        inventoryNameLabel.setText("Hong tra vai xoai");

        dishSizeLabel.setText("S");

        dishPriceLabel.setText("45.000");

        deleteDishButton.setBackground(new java.awt.Color(255, 0, 0));
        deleteDishButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\CafeManagementApp\\src\\main\\java\\com\\mycompany\\cafe_management_app\\asset\\image\\delete-button.png")); // NOI18N
        deleteDishButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        deleteDishButton.setMaximumSize(new java.awt.Dimension(19, 19));
        deleteDishButton.setMinimumSize(new java.awt.Dimension(19, 19));
        deleteDishButton.setPreferredSize(new java.awt.Dimension(19, 19));
        deleteDishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDishButtonActionPerformed(evt);
            }
        });

        quantityDish.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(quantityDish, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(dishSizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(dishPriceLabel)
                .addGap(45, 45, 45)
                .addComponent(deleteDishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inventoryNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dishSizeLabel)
                    .addComponent(dishPriceLabel)
                    .addComponent(deleteDishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityDish))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteDishButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteDishButtonActionPerformed
        // delete dish

    }// GEN-LAST:event_deleteDishButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteDishButton;
    private javax.swing.JLabel dishPriceLabel;
    private javax.swing.JLabel dishSizeLabel;
    private javax.swing.JLabel inventoryNameLabel;
    private javax.swing.JLabel quantityDish;
    // End of variables declaration//GEN-END:variables
}
