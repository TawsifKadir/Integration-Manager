package com.kit.integrationmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Address {

    @JsonProperty("stateId")
    @SerializedName("stateId")
    private Integer stateId;

    @JsonProperty("countyId")
    @SerializedName("countyId")
    private Integer countyId;

    @JsonProperty("payam")
    @SerializedName("payam")
    private Integer payam;

    @JsonProperty("boma")
    @SerializedName("boma")
    private Integer boma;


    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public Integer getPayam() {
        return payam;
    }

    public void setPayam(Integer payam) {
        this.payam = payam;
    }

    public Integer getBoma() {
        return boma;
    }

    public void setBoma(Integer boma) {
        this.boma = boma;
    }
}
