package com.example.testpermissions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStateModel {
    @SerializedName("state")
    @Expose
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
