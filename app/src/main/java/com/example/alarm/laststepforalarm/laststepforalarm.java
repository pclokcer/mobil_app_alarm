package com.example.alarm.laststepforalarm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class laststepforalarm {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("modbus_address")
    @Expose
    private Integer modbusAddress;
    @SerializedName("read_id")
    @Expose
    private Integer readId;
    @SerializedName("setting_value")
    @Expose
    private Integer settingValue;
    @SerializedName("socket_value")
    @Expose
    private Integer socketValue;
    @SerializedName("mark")
    @Expose
    private Integer mark;
    @SerializedName("start_time")
    @Expose
    private Integer startTime;
    @SerializedName("end_time")
    @Expose
    private Integer endTime;
    @SerializedName("minute")
    @Expose
    private Integer minute;
    @SerializedName("again")
    @Expose
    private Integer again;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("device_id")
    @Expose
    private Integer deviceId;
    @SerializedName("title_name")
    @Expose
    private String titleName;
    @SerializedName("title_id")
    @Expose
    private Integer titleId;
    @SerializedName("device_title_name")
    @Expose
    private String deviceTitleName;
    @SerializedName("facility_name")
    @Expose
    private String facilityName;
    @SerializedName("facility_id")
    @Expose
    private Integer facilityId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getModbusAddress() {
        return modbusAddress;
    }

    public void setModbusAddress(Integer modbusAddress) {
        this.modbusAddress = modbusAddress;
    }

    public Integer getReadId() {
        return readId;
    }

    public void setReadId(Integer readId) {
        this.readId = readId;
    }

    public Integer getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(Integer settingValue) {
        this.settingValue = settingValue;
    }

    public Integer getSocketValue() {
        return socketValue;
    }

    public void setSocketValue(Integer socketValue) {
        this.socketValue = socketValue;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getDeviceTitleName() {
        return deviceTitleName;
    }

    public void setDeviceTitleName(String deviceTitleName) {
        this.deviceTitleName = deviceTitleName;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

}