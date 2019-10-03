package com.roacult.kero.team7.backdropapp.controler;

import com.roacult.kero.team7.backdropapp.model.Product;

import java.util.ArrayList;

public abstract class MyCallback {
   public abstract void onSave(Product product) ;
   public abstract void onSave(String edbuilding, String  edstreet, String edstoreNumber , String editem, String edchinaPrice, String edpacking, String edcartonsNo ,  String edNotes) ;

    public void delete(){}

    public void edit(){}

  public   void mark(){}
  public   void onchange(){}


    public  void send(ArrayList<Product> productList1){}
}
