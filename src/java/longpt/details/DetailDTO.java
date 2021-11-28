/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.details;

/**
 *
 * @author Long Pham
 */
public class DetailDTO {
    private String detailID, productID, orderID, time;
    private int quantity, stt;
    private float price;

    public DetailDTO() {
    }

    public DetailDTO(String detailID, String productID, String orderID, String time, int quantity, int stt, float price) {
        this.detailID = detailID;
        this.productID = productID;
        this.orderID = orderID;
        this.time = time;
        this.quantity = quantity;
        this.stt = stt;
        this.price = price;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
    
}
