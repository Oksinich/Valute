package com.example.valute;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Valute {
    public List<OneValute> oneValute;

    public List<OneValute> getOneValute() {
        return oneValute;
    }

    public void setOneValute(List<OneValute> oneValute) {
        this.oneValute = oneValute;
    }
}
