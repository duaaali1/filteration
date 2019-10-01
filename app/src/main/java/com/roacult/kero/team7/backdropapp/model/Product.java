package com.roacult.kero.team7.backdropapp.model;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Product extends BaseModel {
    private String building;
    private String floor;
    private String street;
    private String storeNumber;
    private String itemNo;
    private String item;
    private String chinaPrice;
    private String jordanianPrice;
    private String packing;
    private String cartonsNo;
    private String total;
    private ArrayList<String> imagesList;
    private boolean mark = false;

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    private String storeImage;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private String notes;

    public boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }


    // private DecimalFormat twoDForm = new DecimalFormat("#.#");

    public Product(String building, String floor, String street, String storeNumber, String itemNo, String item, String chinaPrice, String jordanianPrice, String packing, String cartonsNo, String total, ArrayList<String> imagesList, String notes, String storeImage) {
        this.building = building;
        this.floor = floor;
        this.street = street;
        this.storeNumber = storeNumber;
        this.itemNo = itemNo;
        this.item = item;
        this.chinaPrice = chinaPrice + " CNY";
        this.jordanianPrice = getJordanianPrice(chinaPrice);
        this.packing = packing;
        this.cartonsNo = cartonsNo;
        this.total = getTotal(packing, cartonsNo);
        this.imagesList = imagesList;
        this.notes = notes;
        this.storeImage = storeImage ;
    }

    @NotNull
    private String getJordanianPrice(String chinaPrice) {
        try {
            if (!chinaPrice.equals(""))
                return Double.valueOf(new DecimalFormat("#.#").format(Double.parseDouble(chinaPrice) * 0.16)) + " JD";
        } catch (Exception e) {

        }

        return null;
    }

    @NotNull
    private String getTotal(String packing, String cartonsNo) {
        if (!packing.equals("") && !cartonsNo.equals(""))
            return String.valueOf(Double.valueOf(new DecimalFormat("#.#").format(Double.parseDouble(packing) * Double.parseDouble(cartonsNo))));
        return null;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getChinaPrice() {
        return chinaPrice;
    }

    public void setChinaPrice(String chinaPrice) {
        this.chinaPrice = chinaPrice;
    }

    public String getJordanianPrice() {
        return jordanianPrice;
    }

    public void setJordanianPrice(String jordanianPrice) {
        this.jordanianPrice = jordanianPrice;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getCartonsNo() {
        return cartonsNo;
    }

    public void setCartonsNo(String cartonsNo) {
        this.cartonsNo = cartonsNo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(ArrayList<String> imagesList) {
        this.imagesList = imagesList;
    }
}
