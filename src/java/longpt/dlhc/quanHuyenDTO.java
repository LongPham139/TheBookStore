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
public class quanHuyenDTO {
    private int id, cityId;
    private String districtName;

    public quanHuyenDTO(int id, int cityId, String districtName) {
        this.id = id;
        this.cityId = cityId;
        this.districtName = districtName;
    }

    public quanHuyenDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    
    
    
}
