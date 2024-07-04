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
public class PayrollAlternate {
    @JsonProperty("id")
    @SerializedName("id")
    private Integer alternateId;
    @JsonProperty("firstName")
    @SerializedName("firstName")
    private String firstName;
    @JsonProperty("middleName")
    @SerializedName("middleName")
    private String middleName;
    @JsonProperty("lastName")
    @SerializedName("lastName")
    private String lastName;
    @JsonProperty("nickName")
    @SerializedName("nickName")
    private String nickName;
    @JsonProperty("age")
    @SerializedName("age")
    private Integer age;
    @JsonProperty("gender")
    @SerializedName("gender")
    private Integer gender;
    @JsonProperty("nationalId")
    @SerializedName("nationalId")
    private String nationalId;
    @JsonProperty("phoneNumber")
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @JsonProperty("biometric")
    @SerializedName("biometric")
    private PayrollBiometric biometric;
}
