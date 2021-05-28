package com.example.caoviethoang_ktra2_bai2;

public class LichThi {
    int id;
    String name;
    String date;
    String time;
    boolean isThi;

    public LichThi() {
    }

    public LichThi(String name, String date, String time, boolean isThi) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.isThi = isThi;
    }

    public LichThi(int id, String name, String date, String time, boolean isThi) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.isThi = isThi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isThi() {
        return isThi;
    }

    public void setThi(boolean thi) {
        isThi = thi;
    }
}
