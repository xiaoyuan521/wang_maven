package com.mvc.dto;

public class ForumDto {

    private int BKId;
    private String BKName = null;
    public int getBKId() {
        return BKId;
    }
    public void setBKId(int bKId) {
        BKId = bKId;
    }
    public String getBKName() {
        return BKName;
    }
    public void setBKName(String bKName) {
        BKName = bKName;
    }

}
