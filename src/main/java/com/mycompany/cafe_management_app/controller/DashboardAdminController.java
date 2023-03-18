/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cafe_management_app.controller;

import com.mycompany.cafe_management_app.ui.DashboardAdminUI.DetailsItem;
import com.mycompany.cafe_management_app.model.Account;
import com.mycompany.cafe_management_app.model.Bill;
import com.mycompany.cafe_management_app.model.Dish;
import com.mycompany.cafe_management_app.model.DishDetail;
import com.mycompany.cafe_management_app.model.Staff;
import com.mycompany.cafe_management_app.service.AdminService;
import com.mycompany.cafe_management_app.ui.DashboardAdminUI.BillItem;
import com.mycompany.cafe_management_app.ui.DashboardAdminUI.ConfirmBeforeDelete;
import com.mycompany.cafe_management_app.ui.DashboardAdminUI.DashboardAdminUI;
import com.mycompany.cafe_management_app.ui.DashboardAdminUI.DishForm;
import com.mycompany.cafe_management_app.util.callback.DeleteEvent;
import com.mycompany.cafe_management_app.util.callback.EditEvent;
import com.mycompany.cafe_management_app.ui.DashboardAdminUI.NewStaffForm;
import com.mycompany.cafe_management_app.ui.DashboardAdminUI.SizePriceInputItem;
import com.mycompany.cafe_management_app.ui.DashboardAdminUI.StaffItem;
import com.mycompany.cafe_management_app.ui.MenuItem;
import com.mycompany.cafe_management_app.ui.DetailsDish;
import com.mycompany.cafe_management_app.util.ErrorUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author namho
 */
public class DashboardAdminController {
    private DashboardAdminUI dashboardAdminUI;
    private AdminService adminService;
    private JButton addStaffBtn;
    private JButton addDishBtn;
    private JPanel wrapListStaff;    
    private JPanel wrapListDish;
    private JPanel wrapListBill;
    private DishForm dishForm;
    private JTextField dishNameInputField;
    private List<SizePriceInputItem> listSizePriceInput;
    private ErrorUtil errorUtil;

    
    public DashboardAdminController() {
        initController();
    }
    
    private void initController() {
        dashboardAdminUI = new DashboardAdminUI();
        adminService = new AdminService();
        errorUtil = ErrorUtil.getInstance();
       
        // Staff -----------------------------------------------------------

        //show list staff 
        wrapListStaff = dashboardAdminUI.getContainerListStaff();
        renderListStaff();
        // click new staff btn
        addStaffBtn = dashboardAdminUI.getAddStaffBtn();
        addStaffBtn.addActionListener(new addStaffEvent());
        
        // Menu -----------------------------------------------------------
        wrapListDish = dashboardAdminUI.getListDishContainer();
        renderListMenu();
        addDishBtn = dashboardAdminUI.getAddDishBtn();
        addDishBtn.addActionListener(new addDishListener());   
        
        //Bill -----------------------------------------------------------
        wrapListBill = dashboardAdminUI.getBillContainer();
        renderListBill();
        dashboardAdminUI.setVisible(true);
        re_renderListUI();
    }
       
//    Staff method ----------------------------------------------------------------------------------------------------------
    private void renderListStaff() {
        List<Staff> listStaff = adminService.getAllStaff();
        int statusCode = errorUtil.getErrorCode();
        if (statusCode == 0) {
            for (int i = 0; i < listStaff.size(); i++) {
                Staff tmpStaff = listStaff.get(i);
                String name = tmpStaff.getName();
                String dob = tmpStaff.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd.MM yyyy"));
                String phone = tmpStaff.getPhoneNumber();
                String pos = tmpStaff.getPosition();
                EditEvent editEvent = new EditEvent(new EditFunction());
                DeleteEvent deleteEvent = new DeleteEvent(new DeleteFunc());
                StaffItem tmp = new StaffItem(tmpStaff, name, dob, phone, pos, editEvent, deleteEvent);
                wrapListStaff.add(tmp);
            }
        }
    }
    
    private void re_renderListUI() {
            wrapListStaff.removeAll();
            wrapListDish.removeAll();
            renderListStaff();
            renderListMenu();
            dashboardAdminUI.revalidate();
    }
  
    private boolean validation(String username,String pass, String name, String phone,
         String pos, String day, String month, String year) {

     if (username.compareTo("") == 0 | pass.compareTo("") == 0 | name.compareTo("") == 0
         | phone.compareTo("") == 0 | pos.compareTo("") == 0 | day.compareTo("") == 0
             | month.compareTo("") == 0 | year.compareTo("") == 0) {
             new NotificationController("Fill in all fields, plssss !");
             return false;
         }
     try {
         int tmp_day = Integer.parseInt(day);
          int tmp_month = Integer.parseInt(month);
         int tmp_year = Integer.parseInt(year);

         if (tmp_day < 0 | tmp_day > 31) {
              new NotificationController("Day is invalid !");
              return false;
         }
         if (tmp_month < 0 | tmp_month > 12) {
             new NotificationController("Month is invalid !");
             return false;
         }
           if (tmp_year < 0) {
             new NotificationController("Year is invalid !");
             return false;
         }
     } catch (Exception e) {
         new NotificationController("Input is invalid !");
          e.printStackTrace();
          return false;
     }
     return true;
 }

    void addStaff(NewStaffForm form) {
        String username =  form.getUserNameField().getText();
        String pass =  form.getPasswordField().getText();
        String name =  form.getNameField().getText();
        String phone =  form.getPhoneField().getText();
        String pos =  form.getPosField().getText();
        String day =  form.getDayField().getText();
        String month =  form.getMonthField().getText();
        String year =  form.getYearField().getText();
            if (validation(username, pass, name, phone, pos, day, month, year)) {
                LocalDate dob = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month ), Integer.parseInt(day));
                Staff staff = new Staff( name, dob, phone, pos);
                staff.setAccount(new Account(username, pass, "staff"));
                adminService.saveStaff(staff);
                if (errorUtil.getErrorCode() == 0) {
                    new NotificationController("Add staff successfully !");
                } else {
                    new NotificationController("Add staff failed !");
                }
                form.dispose();
            } 
     }
        
    void updateStaff(NewStaffForm form, Staff editedStaff) {
        String username =  form.getUserNameField().getText();
        String pass =  form.getPasswordField().getText();
        String name =  form.getNameField().getText();
        String phone =  form.getPhoneField().getText();
        String pos =  form.getPosField().getText();
        String day =  form.getDayField().getText();
        String month =  form.getMonthField().getText();
        String year =  form.getYearField().getText();
            if (validation(username, pass, name, phone, pos, day, month, year)) {
                LocalDate dob = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month ), Integer.parseInt(day));
                editedStaff.setAll(name, dob, phone, pos);
                adminService.updateStaff(editedStaff);
                if (errorUtil.getErrorCode() == 0) {
                    new NotificationController("Update staff successfully !");
                } else {
                    new NotificationController("Update staff failed !");
                }
                
                form.dispose();
            } 
        }
        
    private class saveStaff implements  ActionListener {
        private NewStaffForm form;
        public saveStaff(NewStaffForm form) {
            this.form = form;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            addStaff(form);
           re_renderListUI();
        }
        
    }
    
    public class EditFunction {
        public void execute(NewStaffForm form, Staff editedStaff) {
            updateStaff(form, editedStaff);
            re_renderListUI();
        }
    }
    
    public class DeleteFunc {
        public void execute(Staff staff) {
            ConfirmBeforeDelete res = new ConfirmBeforeDelete(new Supplier<Void>() {
                @Override
                public Void get() {
                    adminService.deleteStaff(staff);
                    if (errorUtil.getErrorCode() == 0) {
                        new NotificationController("Delete staff successfully !");
                    } else {
                        new NotificationController("Delete staff failed !");
                    }
                    re_renderListUI();
                    return null;
                }
            });
            res.setVisible(true);
        }
    }
    
    private class addStaffEvent implements ActionListener {        
        @Override
        public void actionPerformed(ActionEvent e) {
            NewStaffForm  form = new NewStaffForm();
            form.setVisible(true);
            form.getSaveButton().addActionListener(new saveStaff(form));
        }
    }
   
    
    //    Menu method ----------------------------------------------------------------------------------------------------------
    private void renderListMenu() {
        List<Dish> listDish = adminService.getAllDish();
        if (errorUtil.getErrorCode() == 0) {
            for (Dish dish : listDish) {
                wrapListDish.add(new MenuItem(dish, new DetailsDishFunction(), new EditDishFunction(), new DeleteDishFunction(), Boolean.FALSE));
            }
        }
    }
    
   public class DetailsDishFunction {
       public void showDetails(Long id) {
           DetailsDish frame = new DetailsDish();
           JPanel container = frame.getContainer();
           List<DishDetail> list = adminService.getDetailsOf(id);
           if (errorUtil.getErrorCode() == 0) {
               for (int i = 0; i < list.size(); i++) {
                    String size = list.get(i).getSize();
                    String price = list.get(i).getPrice().toString();
                    container.add(new DetailsItem(size, price));
                }
                frame.setVisible(true);
           }
       }
   }
   
   public class DeleteDishFunction {
       public void delete(Dish dish) {
           ConfirmBeforeDelete res = new ConfirmBeforeDelete(new Supplier<Void>() {
               public Void get() {
                    adminService.deleteDish(dish);
                    if (errorUtil.getErrorCode() == 0) {
                        new NotificationController("Delete dish successfully !");
                    } else {
                        new NotificationController("Delete dish failed !");
                    }
                    re_renderListUI();
                    return null;
                }
           });
           res.setVisible(true);
       }
   }
   
    public class EditDishFunction {
       public void execute(Dish dish, Long id) {
           dishForm = new DishForm(dish.getName(), adminService.getDetailsOf(id));
           dishForm.getSaveBtn().addActionListener(new SaveDishForm("update", dish));
           dishForm.setVisible(true);
       }
   }
     
    private boolean validateDishForm(String name, List<SizePriceInputItem> list) {
         if (name.compareTo("") == 0) {
             new NotificationController("Dish name is invalid !");
             return false;
         }
         if (list.size() == 0) {
             new NotificationController("Size or price of dish is invalid !");
             return false;
         }
         for (SizePriceInputItem item : list) {
             String size = item.getSizeInput().getText();
             String price = item .getPriceInput().getText();
             if (size.compareTo("") == 0 | price.compareTo("") == 0) {
                 new NotificationController("Size or price of dish is invalid !");
                 return false;
             }
         }
         return true;
     }
   
   private class addDishListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
             dishForm = new DishForm();
             dishForm.getSaveBtn().addActionListener(new SaveDishForm("add", new Dish()));
             dishForm.setVisible(true);
        }
   }
   
   private class SaveDishForm implements ActionListener {
       private String type;
       private Dish dish;
       public SaveDishForm(String type, Dish dish){
           this.type = type;
           this.dish = dish;
       }

        @Override
        public void actionPerformed(ActionEvent e) {
             dishNameInputField = dishForm.getNameInput();
             listSizePriceInput = dishForm.getListSizePriceInput();
             String name = dishNameInputField.getText();
             if (validateDishForm(name , listSizePriceInput)) {
                 List<DishDetail> newListDetails = null;
                 List<DishDetail> currListDetails = null;
                dish.setName(name);
                 if (type.equals("update")) {
                    newListDetails = new ArrayList<>();
                    currListDetails = dish.getDetails();
                 }
                 int index = 0;
                for(SizePriceInputItem item: listSizePriceInput) {
                    String size = item.getSizeInput().getText();
                    Double price = Double.parseDouble(item.getPriceInput().getText());
                    DishDetail detail = new DishDetail(size, price);
                    
                    if (type.equals("update")) {
                        if (index < currListDetails.size()) {
                            detail.setId(currListDetails.get(index).getId());
                        }
                        detail.setDish(dish);
                        newListDetails.add(detail);
                    } else if (type.equals("add")) {
                        dish.addDetail(detail);
                    }
                    
                    index++;
                }
                
                if (type.equals("add")) {
                    adminService.saveDish(dish);
                    if (errorUtil.getErrorCode() == 0) {
                        new NotificationController("Add dish successfully !");
                    } else {
                        new NotificationController("Add dish failed !");
                    }
                } else if (type.equals("update")) {
                    adminService.updateDish(dish, newListDetails);
                    if (errorUtil.getErrorCode() == 0) {
                        new NotificationController("Update dish successfully !");
                    } else {
                        new NotificationController("Update dish failed !");
                    }
                }
                
                 re_renderListUI();
                dishForm.dispose();
             } else {
                 dishForm.dispose();
             }
        }
       
   }
   
   //Bill method ------------------------------------------------------------------------------------
   private void renderListBill() {
       List<Bill> list = adminService.getAllBills();
       if (errorUtil.getErrorCode() == 0) {
            int i = 0;
            for (Bill item: list ) {
                i++;
                String index = Integer.toString(i);
                DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                 String time = item.getTimeCreated().format(CUSTOM_FORMATTER);
                 String received = Double.toString(item.getReceivedAmount());
                 String returned = Double.toString(item.getReturnedAmount());
                 String total = Double.toString(item.getTotalPrice());
                wrapListBill.add(new BillItem(index, time, received, returned, total));
            }
       }
   }
}
