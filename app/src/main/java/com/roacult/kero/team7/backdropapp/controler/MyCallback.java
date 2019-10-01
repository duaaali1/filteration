package com.roacult.kero.team7.backdropapp.controler;

import com.roacult.kero.team7.backdropapp.model.Product;

import java.util.ArrayList;

public abstract class MyCallback {
   public abstract void onSave(Product product) ;

    public void delete(){}

    public void edit(){}

  public   void mark(){}

    public  void send(ArrayList<Product> productList1){}
}
