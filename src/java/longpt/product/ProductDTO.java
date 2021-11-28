/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.product;

/**
 *
 * @author Long Pham
 */
public class ProductDTO {
    private String productID, productName, description, dateCreated, categoryID, img;
    private int quantity, stt;
    private float price;

    public ProductDTO(String productID, String productName, String description, String dateCreated, String categoryID, String img, int quantity, float price) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.dateCreated = dateCreated;
        this.categoryID = categoryID;
        this.img = img;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductDTO(String productID, String productName, String description, String dateCreated, String categoryID, String img, int quantity, int stt, float price) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.dateCreated = dateCreated;
        this.categoryID = categoryID;
        this.img = img;
        this.quantity = quantity;
        this.stt = stt;
        this.price = price;
    }

    public ProductDTO() {
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
}
