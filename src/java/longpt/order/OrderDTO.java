/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.order;

/**
 *
 * @author Long Pham
 */
public class OrderDTO {
    private String orderID, userID, time, discountID;
    private Float total, moneyNotDiscount;
    private int stt;

    public OrderDTO(String orderID, String userID, String time, String discountID, Float total, Float moneyNotDiscount, int stt) {
        this.orderID = orderID;
        this.userID = userID;
        this.time = time;
        this.discountID = discountID;
        this.total = total;
        this.moneyNotDiscount = moneyNotDiscount;
        this.stt = stt;
    }

    public OrderDTO() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getMoneyNotDiscount() {
        return moneyNotDiscount;
    }

    public void setMoneyNotDiscount(Float moneyNotDiscount) {
        this.moneyNotDiscount = moneyNotDiscount;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    

    
    
}
