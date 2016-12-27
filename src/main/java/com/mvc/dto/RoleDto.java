package com.mvc.dto;

public class RoleDto {

    private int id;
    private String role = null;
    private double NPL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getNPL() {
        return NPL;
    }

    public void setNPL(double nPL) {
        NPL = nPL;
    }

}
