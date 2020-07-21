package com.mwcd.activity;

public class DashboardItem {
    private String name;
    private int dataId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public DashboardItem(String name,int dataId){
        this.name = name;
        this.dataId= dataId;
    }
}
