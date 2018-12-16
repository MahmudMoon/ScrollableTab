package com.example.moon.ECommerse.cart;

import com.example.moon.ECommerse.models.ShopProduct;

import java.util.ArrayList;

public class Cart_AddedClass {
   static ArrayList<ShopProduct> arrayList = new ArrayList<>();
   static ArrayList<ShopProduct> finalList = new ArrayList<>();
   static ShopProduct shopProduct;
   static int cost = 0;

    public static int getCost() {
        return cost;
    }

    public static void setCost(int cost) {
        Cart_AddedClass.cost = cost;
    }

    public static ArrayList<ShopProduct> getShopProduct() {
        return arrayList;
    }

    public static void setShopProduct(ShopProduct shopProduct) {
        shopProduct = shopProduct;
        arrayList.add(shopProduct);
    }

    public static ArrayList<ShopProduct> getFinalList() {
        return finalList;
    }

    public static void setFinalList(ArrayList<ShopProduct> finalList) {
        Cart_AddedClass.finalList = finalList;
    }

    public static void addProductToFinalList(ShopProduct shopProduct){
        Cart_AddedClass.finalList.add(shopProduct);
    }
    public static void resetList(){
        Cart_AddedClass.finalList = null;
    }
}
