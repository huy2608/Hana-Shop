/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.foods;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Shi
 */
public class FoodDTO implements Serializable{
    private String id;
    private String name;
    private String image;
    private String description;
    private float price;
    private Date createDate;
    private String category;
    private boolean status;
    private int quantity;
//    private int page_No;
    public FoodDTO() {
    }

    public FoodDTO(String id, String name, String image, String description, float price, Date createDate, String category, boolean status, int quantity) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
        this.id = id;
//        this.page_No = page_No;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the page_No
     */
//    public int getPage_No() {
//        return page_No;
//    }
//
//    /**
//     * @param page_No the page_No to set
//     */
//    public void setPage_No(int page_No) {
//        this.page_No = page_No;
//    }

}
