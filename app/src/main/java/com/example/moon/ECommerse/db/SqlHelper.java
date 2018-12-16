package com.example.moon.ECommerse.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.moon.ECommerse.models.ShopProduct;
import java.util.ArrayList;

public class SqlHelper extends SQLiteOpenHelper {

    private static String dbName = "CartAdded";
    public static String dbTable = "cart_list";
    public static String col0 = "product_id";
    public static String col1 = "product_title";
    public static String col2 = "product_image";
    public static String col3 = "product_price";
    public static String col4 = "product_detail";
    public static String col5 = "product_count";
    public static String TAG = "MyTag";

    public SqlHelper(Context context) {
        super(context, dbName, null, 1);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists "+ dbTable + " ( " + col0 + " integer primary key, " + col1 + " text, "
                + col2 + " integer , " + col3 + " text , " + col4 + " text , " + col5 + " integer );";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addProductToCart(ShopProduct shopProduct){
        int id = shopProduct.getId();
        String name = shopProduct.getTitle();
        int image = shopProduct.getImage();
        String price = shopProduct.getPrice();
        String details = shopProduct.getDetails();
        int count = shopProduct.getCount();

        ContentValues contentValues = new ContentValues();
        contentValues.put(col0,id);
        contentValues.put(col1,name);
        contentValues.put(col2,image);
        contentValues.put(col3,price);
        contentValues.put(col4,details);
        contentValues.put(col5,count);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long insert = sqLiteDatabase.insert(dbTable, null, contentValues);
        if(insert>0){
            return true;
        }else
            return false;

    }


    public ArrayList<ShopProduct> getCartAddedData(){
        ShopProduct shopProduct;
        ArrayList<ShopProduct> arrayList = new ArrayList<>();
        String sql = "select * from " + dbTable;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                int image = cursor.getInt(2);
                String price = cursor.getString(3);
                String detail = cursor.getString(4);
                int count = cursor.getInt(5);

                Log.i(TAG, "getCartAddedData: "+id);
                shopProduct = new ShopProduct(id,image,title,price,detail,count);
                arrayList.add(shopProduct);
            }
        }
        return arrayList;
    }

    public boolean resetAddCart(){
        //String sql = "delete * from " + dbTable ;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int delete = sqLiteDatabase.delete(dbTable, null, null);
        if(delete>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteById(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int delete = sqLiteDatabase.delete(dbTable, col0+" = ? ", new String[]{String.valueOf(id)});
        if(delete>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean update(int id,int count){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col5,count);
        int update = sqLiteDatabase.update(dbTable, contentValues, col0 + " =  ? ", new String[]{String.valueOf(id)});
        if(update>0){
            return true;
        }else{
            return false;
        }
    }
}
