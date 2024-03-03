package com.kit.integrationmanager.model;

import lombok.Data;

import java.util.List;

@Data
public class Nominee {
    private String applicationId= null;
    private String nomineeFirstName = null;
    private String nomineeLastName= null;
    private String nomineeMiddleName = null;
    private String nomineeNickName = null;
    private Integer nomineeAge = 0;
    private GenderEnum nomineeGender = null;
    private OccupationEnum nomineeOccupation = null;
    private String otherOccupation = null;
    private RelationshipEnum relationshipWithHouseholdHead = null;
    private Boolean isReadWrite = false;
    List<Biometric> biometrics = null;
}
