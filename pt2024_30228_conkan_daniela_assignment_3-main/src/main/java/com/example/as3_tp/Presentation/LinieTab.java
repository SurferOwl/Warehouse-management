package com.example.as3_tp.Presentation;

/**
 * this class constructs a line made of 6 cells, is used in the interface to make the tables
 */

public class LinieTab {

    private String cel1;
    private String cel2;
    private String cel3;
    private String cel4;
    private String cel5;
    private String cel6;

    public LinieTab(String cel1, String cel2, String cel3, String cel4, String cel5, String cel6) {
        this.cel1 = cel1;
        this.cel2 = cel2;
        this.cel3 = cel3;
        this.cel4 = cel4;
        this.cel5 = cel5;
        this.cel6 = cel6;
    }

    public LinieTab() {

    }

    public void setCel1(String cel1) {
        this.cel1 = cel1;
    }

    public void setCel2(String cel2) {
        this.cel2 = cel2;
    }

    public void setCel3(String cel3) {
        this.cel3 = cel3;
    }

    public void setCel4(String cel4) {
        this.cel4 = cel4;
    }

    public void setCel5(String cel5) {
        this.cel5 = cel5;
    }

    public void setCel6(String cel6) {
        this.cel6 = cel6;
    }

    public String getCel1() {
        return cel1;
    }

    public String getCel2() {
        return cel2;
    }

    public String getCel3() {
        return cel3;
    }

    public String getCel4() {
        return cel4;
    }

    public String getCel5() {
        return cel5;
    }

    public String getCel6() {
        return cel6;
    }
}
