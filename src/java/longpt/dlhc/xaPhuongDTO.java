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
public class xaPhuongDTO {
    private int id, districtID;
    private String wardName;

    public xaPhuongDTO(int id, int districtID, String wardName) {
        this.id = id;
        this.districtID = districtID;
        this.wardName = wardName;
    }

    public xaPhuongDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
    
    
}
