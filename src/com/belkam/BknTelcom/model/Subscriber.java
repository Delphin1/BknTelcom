package com.belkam.BknTelcom.model;

/**
 * Created with IntelliJ IDEA.
 * User: tsg
 * Date: 11.07.14
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */

// Simple POJO class
public class Subscriber {
    private String tabNum;
    private String fio;
    private String email;
    private String abbr;
    private String job;
    private String vn;
    private String gn;
    private String room;

    public Subscriber() {}

    public Subscriber(String tabNum, String fio, String email, String abbr, String job, String vn, String gn, String room) {
        this.tabNum = tabNum;
        this.fio = fio;
        this.email = email;
        this.abbr = abbr;
        this.job = job;
        this.vn = vn;
        this.gn = gn;
        this.room = room;
    }

    @Override
    public String toString()
    {
        return "TabN: " + getTabNum() + " FIO: " + getFio();
    }

    public String getTabNum() {
        return tabNum;
    }

    public void setTabNum(String tabNum) {
        this.tabNum = tabNum;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public String getGn() {
        return gn;
    }

    public void setGn(String gn) {
        this.gn = gn;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
