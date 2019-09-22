package com.roacult.kero.team7.backdropapp.model;

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
    private DecimalFormat twoDForm = new DecimalFormat("#.#");

    public Product(String building, String floor, String street, String storeNumber, String itemNo, String item, String chinaPrice, String jordanianPrice, String packing, String cartonsNo, String total, ArrayList<String> imagesList) {
        this.building = building;
        this.floor = floor;
        this.street = street;
        this.storeNumber = storeNumber;
        this.itemNo = itemNo;
        this.item = item;
        this.chinaPrice = chinaPrice + " CNY";

        this.jordanianPrice = Double.valueOf(twoDForm.format(Double.parseDouble(chinaPrice) * 0.16)) +" JD";
        this.packing = packing;
        this.cartonsNo = cartonsNo;
        this.total = String.valueOf(Double.valueOf(twoDForm.format(Double.parseDouble(packing) * Double.parseDouble(cartonsNo))));
        this.imagesList = imagesList;
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
