package com.example.alarm.getdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class datadatum {

    @SerializedName("read_id")
    @Expose
    private Integer readId;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("gsm_date")
    @Expose
    private String gsmDate;
    @SerializedName("title_id")
    @Expose
    private Integer titleId;
    @SerializedName("title_name")
    @Expose
    private String titleName;

    public Integer getReadId() {
        return readId;
    }

    public void setReadId(Integer readId) {
        this.readId = readId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGsmDate() {
        return gsmDate;
    }

    public void setGsmDate(String gsmDate) {
        this.gsmDate = gsmDate;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

}