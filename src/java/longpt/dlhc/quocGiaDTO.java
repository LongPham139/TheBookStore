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
public class quocGiaDTO {
    private int id;
    private String countryName;

    public quocGiaDTO(int id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public quocGiaDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    
    
}
