package com.example.explorun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class RouteDBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "explorunRoutes.db";
    public static final int DB_VERSION = 1;

    public static final String ROUTES_LIST = "routes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DISTANCE = "distance";
//    public static final String COLUMN_LIGHT = "with_light";
//    public static final String COLUMN_WATER = "near_water";
//    public static final String COLUMN_GRADIENT = "gradient";

    public static final String SQL_CREATE = "CREATE TABLE " + ROUTES_LIST + "(" + COLUMN_ID + "INTEGER PRIMARY KEY, " + COLUMN_NAME + "TEXT, " + COLUMN_DISTANCE + "DECIMAL" + ")";


    public RouteDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ROUTES_LIST);

        onCreate(db);

    }

    public void insertRoute(Routes route) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, route.getId());
        values.put(COLUMN_NAME, route.getName());
        values.put(COLUMN_DISTANCE, route.getDistance());

        long result = db.insert(ROUTES_LIST,null, values);

        db.close();
    }

    public void createSampleRoutes(){
        Routes route1 = new Routes(1,"alster", 10.7);
        insertRoute(route1);
    }
    public boolean updateRoute(int ID, String name, double distance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, ID);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DISTANCE, distance);

        return db.update(ROUTES_LIST, values, COLUMN_ID + "=" + ID, null) > 0;
    }

//    public List<Routes> getAllRoutes(){
//        List<Routes> routes = new ArrayList<>();
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.query(ROUTES_LIST, null, null, null, null, null, null);
//        Routes route;
//        if(cursor.getCount()>0) {
//            for(int i = 0; i < cursor.getCount(); i++){
//                cursor.moveToNext();
//                route = new Routes();
//                route.setId(Integer.parseInt(cursor.getString(0)));
//
//            }
//        }
//        return routes;
//    }

    public boolean deleteRoute(int ID) {
        boolean result = false;
        String query = "SELECT * FROM " + ROUTES_LIST + " WHERE " + COLUMN_ID + " = \"" + String.valueOf(ID) + "\"" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Routes route = new Routes();
        if(cursor.moveToFirst()){
            route.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(ROUTES_LIST, COLUMN_ID + "=?", new String[]{String.valueOf(route.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public String loadRoutes(){
        String result = "";
        String query = "SELECT * FROM " + ROUTES_LIST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            double result_2 = cursor.getDouble(2);
            result += String.valueOf(result_0) + " " + result_1 + " " + String.valueOf(result_2)
                    + System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }


    public Routes findRoute(String routename){
        String query = "SELECT * FROM " + ROUTES_LIST + " WHERE " + COLUMN_NAME + " = \"" + routename + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Routes route = new Routes();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            route.setId(Integer.parseInt(cursor.getString(0)));
            route.setName(cursor.getString(1));
            route.setDistance(Double.parseDouble(cursor.getString(2)));
        }else {
            route = null;
        }
        db.close();
        return route;
    }

}
