package com.example.caoviethoang_ktra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelperLichThi extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="LichThi.db";
    private static final int DATABASE_VERSION=1;
    public SQLiteHelperLichThi(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE Lichthi(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,"+
                "date DATE,"+
                "time TIME,"+
                "thi boolean)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }
    public void add(LichThi lichThi){
        String sql="INSERT INTO Lichthi(name,date,time,thi) VALUES (?,?,?,?)";
        String[] args={lichThi.getName(),lichThi.getDate(),lichThi.getTime(), String.valueOf(lichThi.isThi())};
        SQLiteDatabase statement=getWritableDatabase();
        statement.execSQL(sql,args);
    }
    public List<LichThi> getAll(){
        List<LichThi> list=new ArrayList<>();
        SQLiteDatabase statement=getReadableDatabase();
        Cursor resultset=statement.query("Lichthi",null,null,null,null,null,null);
        while(resultset!=null && resultset.moveToNext()){
            int id=resultset.getInt(0);
            String name=resultset.getString(1);
            String date=resultset.getString(2);
            String time=resultset.getString(3);
            boolean thi=resultset.getString(4).equals("true");
            list.add(new LichThi(id,name,date,time,thi));
        }
        return list;
    }
    public int update(LichThi lichThi){
        ContentValues v=new ContentValues();
        v.put("name",lichThi.getName());
        v.put("date",lichThi.getDate());
        v.put("time",lichThi.getTime());
        v.put("thi",String.valueOf(lichThi.isThi()));
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(lichThi.getId())};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("Lichthi",v,whereClause,whereArgs);
    }

    public int delete(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("Lichthi",whereClause,whereArgs);
    }
    public List<LichThi> getByname(String name) {
        List<LichThi> list=new ArrayList<>();
        String whereClause = "name LIKE ?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("Lichthi", null, whereClause, whereArgs, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nameorder = cursor.getString(cursor.getColumnIndex("name"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String time=cursor.getString(cursor.getColumnIndex("time"));
            boolean thi=cursor.getString(cursor.getColumnIndex("thi")).equals("true");
            list.add(new LichThi(id, nameorder, date,time,thi));
        }
        cursor.close();
        return list;
    }
    public List<LichThi> getByThi() {
        List<LichThi> list=new ArrayList<>();
        String whereClause = "thi = 'true'";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("Lichthi", null, whereClause, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nameorder = cursor.getString(cursor.getColumnIndex("name"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String time=cursor.getString(cursor.getColumnIndex("time"));
            boolean thi=cursor.getString(cursor.getColumnIndex("thi")).equals("true");
            list.add(new LichThi(id, nameorder, date,time,thi));
        }
        cursor.close();
        return list;
    }
}
