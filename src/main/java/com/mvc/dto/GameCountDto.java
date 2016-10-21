package com.mvc.dto;

import java.sql.Date;

public class GameCountDto {

    private int id;
    private int inforId;
    private int allGamesCount;
    private int successCount;
    private String inforName = null;
    private float rate;
    private Date date = null;
    private String gamestatus = null;
    private int roleId;
    private int allWerewolfCount;
    private int successWerewolfCount;
    private int allProphetCount;
    private int successProphetCount;
    private int allWitchCount;
    private int successWitchCount;
    private int allHunterCount;
    private int successHunterCount;
    private int allCivilianCount;
    private int successCivilianCount;
    private float werewolfRate=0;
    private float prophetRate=0;
    private float witchRate=0;
    private float hunterRate=0;
    private float civilianRate=0;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGamestatus() {
        return gamestatus;
    }

    public void setGamestatus(String gamestatus) {
        this.gamestatus = gamestatus;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getAllWerewolfCount() {
        return allWerewolfCount;
    }

    public void setAllWerewolfCount(int allWerewolfCount) {
        this.allWerewolfCount = allWerewolfCount;
    }

    public int getSuccessWerewolfCount() {
        return successWerewolfCount;
    }

    public void setSuccessWerewolfCount(int successWerewolfCount) {
        this.successWerewolfCount = successWerewolfCount;
    }

    public int getAllProphetCount() {
        return allProphetCount;
    }

    public void setAllProphetCount(int allProphetCount) {
        this.allProphetCount = allProphetCount;
    }

    public int getSuccessProphetCount() {
        return successProphetCount;
    }

    public void setSuccessProphetCount(int successProphetCount) {
        this.successProphetCount = successProphetCount;
    }

    public int getAllWitchCount() {
        return allWitchCount;
    }

    public void setAllWitchCount(int allWitchCount) {
        this.allWitchCount = allWitchCount;
    }

    public int getSuccessWitchCount() {
        return successWitchCount;
    }

    public void setSuccessWitchCount(int successWitchCount) {
        this.successWitchCount = successWitchCount;
    }

    public int getAllHunterCount() {
        return allHunterCount;
    }

    public void setAllHunterCount(int allHunterCount) {
        this.allHunterCount = allHunterCount;
    }

    public int getSuccessHunterCount() {
        return successHunterCount;
    }

    public void setSuccessHunterCount(int successHunterCount) {
        this.successHunterCount = successHunterCount;
    }

    public int getAllCivilianCount() {
        return allCivilianCount;
    }

    public void setAllCivilianCount(int allCivilianCount) {
        this.allCivilianCount = allCivilianCount;
    }

    public int getSuccessCivilianCount() {
        return successCivilianCount;
    }

    public void setSuccessCivilianCount(int successCivilianCount) {
        this.successCivilianCount = successCivilianCount;
    }

    public float getWerewolfRate() {
        return werewolfRate;
    }

    public void setWerewolfRate(float werewolfRate) {
        this.werewolfRate = werewolfRate;
    }

    public float getProphetRate() {
        return prophetRate;
    }

    public void setProphetRate(float prophetRate) {
        this.prophetRate = prophetRate;
    }

    public float getWitchRate() {
        return witchRate;
    }

    public void setWitchRate(float witchRate) {
        this.witchRate = witchRate;
    }

    public float getHunterRate() {
        return hunterRate;
    }

    public void setHunterRate(float hunterRate) {
        this.hunterRate = hunterRate;
    }

    public float getCivilianRate() {
        return civilianRate;
    }

    public void setCivilianRate(float civilianRate) {
        this.civilianRate = civilianRate;
    }

}
