/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.cart;

import huylng.foods.FoodDTO;
import java.util.HashMap;

/**
 *
 * @author Shi
 */
public class FoodsCart {

    private HashMap<String, FoodDTO> foodCart;

    public HashMap<String, FoodDTO> getFoodCart() {
        return foodCart;
    }

    public FoodsCart() {
    }

    public FoodsCart(HashMap<String, FoodDTO> foodCart) {
        this.foodCart = foodCart;
    }

    /**
     * @param foodCart the foodCart to set
     */
    public void setFoodCart(HashMap<String, FoodDTO> foodCart) {
        this.foodCart = foodCart;
    }

    public void addFoodToCart(FoodDTO dto) {
        if (foodCart == null) {
            this.foodCart = new HashMap<>();
        }
        
        if (this.foodCart.containsKey(dto.getId())) {
            int quantity = dto.getQuantity() + 1;
            dto.setQuantity(quantity);
        }
        this.foodCart.put(dto.getId(), dto);
    }

    public void removeFoodFromCart(FoodDTO dto) {
        if (foodCart == null) {
            return;
        }
        if (this.foodCart.containsKey(dto.getId())) {
            this.foodCart.remove(dto.getId());
        }
        if (this.foodCart.isEmpty()) {
            this.foodCart = null;
        }
    }
}
