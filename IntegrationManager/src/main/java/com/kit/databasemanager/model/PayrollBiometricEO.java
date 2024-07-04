/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kit.databasemanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kit.integrationmanager.model.PayrollBiometric;

import android.util.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "payroll_bio")
public class PayrollBiometricEO {
    @PrimaryKey
    public int biometricId;

    public byte[] photo;
    public byte[] leftSmall;
    public byte[] leftRing;
    public byte[] leftMiddle;
    public byte[] leftIndex;
    public byte[] leftThumb;
    public byte[] rightSmall;
    public byte[] rightRing;
    public byte[] rightMiddle;
    public byte[] rightIndex;
    public byte[] rightThumb;

    public void mapFromBO(PayrollBiometric payrollBiometric){
        this.photo = payrollBiometric.getPhoto()!=null?decodeData(payrollBiometric.getPhoto()):null;
        this.leftSmall = payrollBiometric.getLeftSmall()!=null?decodeData(payrollBiometric.getLeftSmall()):null;
        this.leftRing = payrollBiometric.getLeftRing()!=null?decodeData(payrollBiometric.getLeftRing()):null;
        this.leftMiddle = payrollBiometric.getLeftMiddle()!=null?decodeData(payrollBiometric.getLeftMiddle()):null;
        this.leftIndex = payrollBiometric.getLeftIndex()!=null?decodeData(payrollBiometric.getLeftIndex()):null;
        this.leftThumb = payrollBiometric.getLeftThumb()!=null?decodeData(payrollBiometric.getLeftThumb()):null;
        this.rightSmall = payrollBiometric.getRightSmall()!=null?decodeData(payrollBiometric.getRightSmall()):null;
        this.rightRing = payrollBiometric.getRightRing()!=null?decodeData(payrollBiometric.getRightRing()):null;
        this.rightMiddle = payrollBiometric.getRightMiddle()!=null?decodeData(payrollBiometric.getRightMiddle()):null;
        this.rightIndex = payrollBiometric.getRightIndex()!=null?decodeData(payrollBiometric.getRightIndex()):null;
        this.rightThumb = payrollBiometric.getRightThumb()!=null?decodeData(payrollBiometric.getRightThumb()):null;
    }

    private byte[] decodeData(String data){
        try{
            if(data==null) return null;
            return Base64.decode(data, Base64.DEFAULT);
        }catch(Throwable t){

        }
        return null;
    }

}