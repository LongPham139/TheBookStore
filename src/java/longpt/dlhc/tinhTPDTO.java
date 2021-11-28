/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.dlhc;

/**
 *
 * @author Long Pham
 */
public class tinhTPDTO {
    private int id, countryId;
    private String provinceName;

    public tinhTPDTO(int id, int countryId, String provinceName) {
        this.id = id;
        this.countryId = countryId;
        this.provinceName = provinceName;
    }

    public tinhTPDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    

    
}
