package com.example.valute;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataLink {

    @SerializedName("Date")
    @Expose
    public String Date;

    @SerializedName("PreviousDate")
    @Expose
    public String PreviousDate;

    @SerializedName("PreviousURL")
    @Expose
    public String PreviousURL;

    @SerializedName("Timestamp")
    @Expose
    public String Timestamp;

    @SerializedName("Valute")
    @Expose
    public Valute valute;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPreviousDate() {
        return PreviousDate;
    }

    public void setPreviousDate(String previousDate) {
        PreviousDate = previousDate;
    }

    public String getPreviousURL() {
        return PreviousURL;
    }

    public void setPreviousURL(String previousURL) {
        PreviousURL = previousURL;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public Valute getValute() {
        return valute;
    }

    public void setValute(Valute valute) {
        this.valute = valute;
    }
}
