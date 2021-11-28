/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.cart;

import java.util.HashMap;
import java.util.Map;
import longpt.product.ProductDTO;

/**
 *
 * @author Long Pham
 */
public class CartOBJ {
    private Map<String, ProductDTO> cart;

    public CartOBJ(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public CartOBJ() {
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }
    
    public boolean add(ProductDTO product){
        boolean check = false;
        try {
            if(cart == null){
                cart = new HashMap<>();
            }
            if(cart.containsKey(product.getProductID())){
                int quantity = cart.get(product.getProductID()).getQuantity();
                product.setQuantity(quantity+product.getQuantity());
            }
            cart.put(product.getProductID(), product);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }
    public boolean remove(String productID){
        boolean check = false;
        try {
            if(cart!= null){
                if(cart.containsKey(productID)){
                    cart.remove(productID);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
    public boolean update(String id, ProductDTO pro){
        boolean check = false;
        try {
            if(cart != null){
                if(cart.containsKey(id)){
                    cart.replace(id, pro);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
}
