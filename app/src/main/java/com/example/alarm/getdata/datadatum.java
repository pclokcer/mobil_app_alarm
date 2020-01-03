package com.example.alarm.getdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class datadatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("baud_rate")
    @Expose
    private Integer baudRate;
    @SerializedName("data_bit")
    @Expose
    private Integer dataBit;
    @SerializedName("data_parity")
    @Expose
    private String dataParity;
    @SerializedName("stop_bit")
    @Expose
    private Integer stopBit;
    @SerializedName("data_port")
    @Expose
    private String dataPort;
    @SerializedName("modbus_address")
    @Expose
    private Integer modbusAddress;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("expiration_date")
    @Expose
    private Object expirationDate;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("modem_id")
    @Expose
    private Integer modemId;
    @SerializedName("title_id")
    @Expose
    private Integer titleId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("modem_name")
    @Expose
    private String modemName;
    @SerializedName("modem_code")
    @Expose
    private String modemCode;
    @SerializedName("facility_id")
    @Expose
    private Integer facilityId;
    @SerializedName("facility_name")
    @Expose
    private String facilityName;
    @SerializedName("title_name")
    @Expose
    private String titleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Integer baudRate) {
        this.baudRate = baudRate;
    }

    public Integer getDataBit() {
        return dataBit;
    }

    public void setDataBit(Integer dataBit) {
        this.dataBit = dataBit;
    }

    public String getDataParity() {
        return dataParity;
    }

    public void setDataParity(String dataParity) {
        this.dataParity = dataParity;
    }

    public Integer getStopBit() {
        return stopBit;
    }

    public void setStopBit(Integer stopBit) {
        this.stopBit = stopBit;
    }

    public String getDataPort() {
        return dataPort;
    }

    public void setDataPort(String dataPort) {
        this.dataPort = dataPort;
    }

    public Integer getModbusAddress() {
        return modbusAddress;
    }

    public void setModbusAddress(Integer modbusAddress) {
        this.modbusAddress = modbusAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Object expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getModemId() {
        return modemId;
    }

    public void setModemId(Integer modemId) {
        this.modemId = modemId;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getModemName() {
        return modemName;
    }

    public void setModemName(String modemName) {
        this.modemName = modemName;
    }

    public String getModemCode() {
        return modemCode;
    }

    public void setModemCode(String modemCode) {
        this.modemCode = modemCode;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

}