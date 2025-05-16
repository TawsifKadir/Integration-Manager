/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kit.integrationmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author anwar
 */
@Data
@Getter
@Setter
public class PayrollBiometric {
    @JsonProperty("photo")
    @SerializedName("photo")
    private String photo;
    @JsonProperty("leftSmall")
    @SerializedName("leftSmall")
    private String leftSmall;
    @JsonProperty("leftRing")
    @SerializedName("leftRing")
    private String leftRing;
    @JsonProperty("leftMiddle")
    @SerializedName("leftMiddle")
    private String leftMiddle;
    @JsonProperty("leftIndex")
    @SerializedName("leftIndex")
    private String leftIndex;
    @JsonProperty("leftThumb")
    @SerializedName("leftThumb")
    private String leftThumb;
    @JsonProperty("rightSmall")
    @SerializedName("rightSmall")
    private String rightSmall;
    @JsonProperty("rightRing")
    @SerializedName("rightRing")
    private String rightRing;
    @JsonProperty("rightMiddle")
    @SerializedName("rightMiddle")
    private String rightMiddle;
    @JsonProperty("rightIndex")
    @SerializedName("rightIndex")
    private String rightIndex;
    @JsonProperty("rightThumb")
    @SerializedName("rightThumb")
    private String rightThumb;
}
