/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.profile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import longpt.utils.VNCharacterUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONObject;


/**
 *
 * @author Long Pham
 */
public class Profile_Modal {

    public Profile_Bean call_me(String access_token) throws Exception {
        String url = "https://graph.facebook.com/v2.12/me?fields=id,name,email&access_token=" + access_token;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response);
        Profile_Bean obj_Profile_Bean = new Profile_Bean();        
        JSONObject myResponse = new JSONObject(response.toString());
        String name = VNCharacterUtils.removeAccent(myResponse.getString("name"));
        String email = StringEscapeUtils.unescapeJava(myResponse.getString("email")); 
        System.out.println(name);
        System.out.println(email);
        obj_Profile_Bean.setId(myResponse.getString("id"));
        obj_Profile_Bean.setEmail(email);
        obj_Profile_Bean.setUser_name(name);
        return obj_Profile_Bean;
    }
}
