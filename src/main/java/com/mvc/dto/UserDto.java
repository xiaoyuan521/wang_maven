package com.mvc.dto;

public class UserDto {

    private String username;
    private String password;
    private Integer redId = 0;
    private Integer redCount = 0;
    private Integer blueId = 0;
    private Integer blueCount = 0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRedId() {
        return redId;
    }

    public void setRedId(Integer redId) {
        this.redId = redId;
    }

    public Integer getRedCount() {
        return redCount;
    }

    public void setRedCount(Integer redCount) {
        this.redCount = redCount;
    }

    public Integer getBlueId() {
        return blueId;
    }

    public void setBlueId(Integer blueId) {
        this.blueId = blueId;
    }

    public Integer getBlueCount() {
        return blueCount;
    }

    public void setBlueCount(Integer blueCount) {
        this.blueCount = blueCount;
    }

}
