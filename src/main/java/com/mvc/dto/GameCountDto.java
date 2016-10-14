package com.mvc.dto;

public class GameCountDto {

    private int id;
    private int inforId;
    private int allGamesCount;
    private int successCount;
    private String inforName=null;
    private float rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInforId() {
        return inforId;
    }

    public void setInforId(int inforId) {
        this.inforId = inforId;
    }

    public int getAllGamesCount() {
        return allGamesCount;
    }

    public void setAllGamesCount(int allGamesCount) {
        this.allGamesCount = allGamesCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public String getInforName() {
        return inforName;
    }

    public void setInforName(String inforName) {
        this.inforName = inforName;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

}
