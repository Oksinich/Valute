package com.example.valute;


public class OneValute {

    public String Name;

    public float Value;

    public OneValute(String name, float value) {
        Name = name;
        Value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getValue() {
        return Value;
    }

    public void setValue(float value) {
        Value = value;
    }
}
