package com.example.alarm.getalarms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getalarms {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("read_id")
    @Expose
    private Integer readId;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("mark")
    @Expose
    private Integer mark;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("minute")
    @Expose
    private Integer minute;
    @SerializedName("again")
    @Expose
    private Integer again;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("alert_setting_id")
    @Expose
    private Integer alertSettingId;
    @SerializedName("device_id")
    @Expose
    private Integer deviceId;
    @SerializedName("title_name")
    @Expose
    private String titleName;
    @SerializedName("device_title_name")
    @Expose
    private String deviceTitleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getReadId() {
        return readId;
    }

    public void setReadId(Integer readId) {
        this.readId = readId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getAgain() {
        return again;
    }

    public void setAgain(Integer again) {
        this.again = again;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAlertSettingId() {
        return alertSettingId;
    }

    public void setAlertSettingId(Integer alertSettingId) {
        this.alertSettingId = alertSettingId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getDeviceTitleName() {
        return deviceTitleName;
    }

    public void setDeviceTitleName(String deviceTitleName) {
        this.deviceTitleName = deviceTitleName;
    }

}