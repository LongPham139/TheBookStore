/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.discount;

/**
 *
 * @author Long Pham
 */
public class DiscountDTO {
    private String discountID, description, starDiscount, endDiscount, userID;
    private float percenValue;
    private boolean isEnable;

    public DiscountDTO(String discountID, String description, String starDiscount, String endDiscount, String userID, float percenValue, boolean isEnable) {
        this.discountID = discountID;
        this.description = description;
        this.starDiscount = starDiscount;
        this.endDiscount = endDiscount;
        this.userID = userID;
        this.percenValue = percenValue;
        this.isEnable = isEnable;
    }

    public DiscountDTO(String discountID, String description, String starDiscount, String endDiscount, float percenValue, boolean isEnable) {
        this.discountID = discountID;
        this.description = description;
        this.starDiscount = starDiscount;
        this.endDiscount = endDiscount;
        this.percenValue = percenValue;
        this.isEnable = isEnable;
    }

    


    public DiscountDTO() {
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarDiscount() {
        return starDiscount;
    }

    public void setStarDiscount(String starDiscount) {
        this.starDiscount = starDiscount;
    }

    public String getEndDiscount() {
        return endDiscount;
    }

    public void setEndDiscount(String endDiscount) {
        this.endDiscount = endDiscount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getPercenValue() {
        return percenValue;
    }

    public void setPercenValue(float percenValue) {
        this.percenValue = percenValue;
    }

    public boolean isIsEnable() {
        return isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }
    
    
}
