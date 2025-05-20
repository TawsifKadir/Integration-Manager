package com.kit.integrationmanager.payload.update.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kit.integrationmanager.model.Address;
import com.kit.integrationmanager.model.AlternatePayee;
import com.kit.integrationmanager.model.Biometric;
import com.kit.integrationmanager.model.CurrencyEnum;
import com.kit.integrationmanager.model.DocumentTypeEnum;
import com.kit.integrationmanager.model.GenderEnum;
import com.kit.integrationmanager.model.HouseholdMember;
import com.kit.integrationmanager.model.IncomeSourceEnum;
import com.kit.integrationmanager.model.LegalStatusEnum;
import com.kit.integrationmanager.model.Location;
import com.kit.integrationmanager.model.MaritalStatusEnum;
import com.kit.integrationmanager.model.Nominee;
import com.kit.integrationmanager.model.NonPerticipationReasonEnum;
import com.kit.integrationmanager.model.RelationshipEnum;
import com.kit.integrationmanager.model.SelectionCriteriaEnum;
import com.kit.integrationmanager.model.SelectionReasonEnum;

import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BeneficiaryUpdateRequestV2 {

    @JsonProperty("applicationId")
    private String applicationId;

    @JsonProperty("respondentFirstName")
    private String respondentFirstName;

    @JsonProperty("respondentMiddleName")
    private String respondentMiddleName;

    @JsonProperty("respondentLastName")
    private String respondentLastName;

    @JsonProperty("respondentNickName")
    private String respondentNickName;

    @JsonProperty("spouseFirstName")
    private String spouseFirstName;

    @JsonProperty("spouseMiddleName")
    private String spouseMiddleName;

    @JsonProperty("spouseLastName")
    private String spouseLastName;

    @JsonProperty("spouseNickName")
    private String spouseNickName;

    @JsonProperty("relationshipWithHouseholdHead")
    private RelationshipEnum relationshipWithHouseholdHead;

    @JsonProperty("relationshipOther")
    private String relationshipOther;

    @JsonProperty("respondentAge")
    private Integer respondentAge;

    @JsonProperty("respondentGender")
    private GenderEnum respondentGender;

    @JsonProperty("respondentMaritalStatus")
    private MaritalStatusEnum respondentMaritalStatus;

    @JsonProperty("respondentLegalStatus")
    private LegalStatusEnum respondentLegalStatus;

    @JsonProperty("documentType")
    private DocumentTypeEnum documentType;

    @JsonProperty("documentTypeOther")
    private String documentTypeOther;

    @JsonProperty("respondentId")
    private String respondentId;

    @JsonProperty("respondentPhoneNo")
    private String respondentPhoneNo;

    @JsonProperty("householdIncomeSource")
    private IncomeSourceEnum householdIncomeSource;

    @JsonProperty("incomeSourceOther")
    private String incomeSourceOther;

    @JsonProperty("householdMonthlyAvgIncome")
    private Integer householdMonthlyAvgIncome;

    @JsonProperty("currency")
    private CurrencyEnum currency;

    @JsonProperty("selectionCriteria")
    private SelectionCriteriaEnum selectionCriteria;

    @JsonProperty("selectionReason")
    private List<SelectionReasonEnum> selectionReason;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("householdSize")
    private Integer householdSize;

    @JsonProperty("householdMember2")
    private HouseholdMember householdMember2;

    @JsonProperty("householdMember5")
    private HouseholdMember householdMember5;

    @JsonProperty("householdMember17")
    private HouseholdMember householdMember17;

    @JsonProperty("householdMember35")
    private HouseholdMember householdMember35;

    @JsonProperty("householdMember64")
    private HouseholdMember householdMember64;

    @JsonProperty("householdMember65")
    private HouseholdMember householdMember65;

    @JsonProperty("isReadWrite")
    private Boolean isReadWrite;

    @JsonProperty("memberReadWrite")
    private Integer memberReadWrite;

    @JsonProperty("isOtherMemberPerticipating")
    private Boolean isOtherMemberPerticipating;

    @JsonProperty("notPerticipationReason")
    private NonPerticipationReasonEnum notPerticipationReason;

    @JsonProperty("notPerticipationOtherReason")
    private String notPerticipationOtherReason;

    @JsonProperty("nominees")
    private List<Nominee> nominees;

    @JsonProperty("biometrics")
    private List<Biometric> biometrics;

    @JsonProperty("alternatePayee1")
    private AlternatePayee alternatePayee1;

    @JsonProperty("alternatePayee2")
    private AlternatePayee alternatePayee2;
    @JsonProperty("updatedBy")
    private Long updatedBy;
    public String getSpouseFirstName() {
        return spouseFirstName;
    }

    public void setSpouseFirstName(String spouseFirstName) {
        this.spouseFirstName = spouseFirstName;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getRespondentFirstName() {
        return respondentFirstName;
    }

    public void setRespondentFirstName(String respondentFirstName) {
        this.respondentFirstName = respondentFirstName;
    }

    public String getRespondentMiddleName() {
        return respondentMiddleName;
    }

    public void setRespondentMiddleName(String respondentMiddleName) {
        this.respondentMiddleName = respondentMiddleName;
    }

    public String getRespondentLastName() {
        return respondentLastName;
    }

    public void setRespondentLastName(String respondentLastName) {
        this.respondentLastName = respondentLastName;
    }

    public String getRespondentNickName() {
        return respondentNickName;
    }

    public void setRespondentNickName(String respondentNickName) {
        this.respondentNickName = respondentNickName;
    }

    public String getSpouseMiddleName() {
        return spouseMiddleName;
    }

    public void setSpouseMiddleName(String spouseMiddleName) {
        this.spouseMiddleName = spouseMiddleName;
    }

    public String getSpouseLastName() {
        return spouseLastName;
    }

    public void setSpouseLastName(String spouseLastName) {
        this.spouseLastName = spouseLastName;
    }

    public String getSpouseNickName() {
        return spouseNickName;
    }

    public void setSpouseNickName(String spouseNickName) {
        this.spouseNickName = spouseNickName;
    }

    public RelationshipEnum getRelationshipWithHouseholdHead() {
        return relationshipWithHouseholdHead;
    }

    public void setRelationshipWithHouseholdHead(RelationshipEnum relationshipWithHouseholdHead) {
        this.relationshipWithHouseholdHead = relationshipWithHouseholdHead;
    }

    public String getRelationshipOther() {
        return relationshipOther;
    }

    public void setRelationshipOther(String relationshipOther) {
        this.relationshipOther = relationshipOther;
    }

    public Integer getRespondentAge() {
        return respondentAge;
    }

    public void setRespondentAge(Integer respondentAge) {
        this.respondentAge = respondentAge;
    }

    public GenderEnum getRespondentGender() {
        return respondentGender;
    }

    public void setRespondentGender(GenderEnum respondentGender) {
        this.respondentGender = respondentGender;
    }

    public MaritalStatusEnum getRespondentMaritalStatus() {
        return respondentMaritalStatus;
    }

    public void setRespondentMaritalStatus(MaritalStatusEnum respondentMaritalStatus) {
        this.respondentMaritalStatus = respondentMaritalStatus;
    }

    public LegalStatusEnum getRespondentLegalStatus() {
        return respondentLegalStatus;
    }

    public void setRespondentLegalStatus(LegalStatusEnum respondentLegalStatus) {
        this.respondentLegalStatus = respondentLegalStatus;
    }

    public DocumentTypeEnum getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypeEnum documentType) {
        this.documentType = documentType;
    }

    public String getDocumentTypeOther() {
        return documentTypeOther;
    }

    public void setDocumentTypeOther(String documentTypeOther) {
        this.documentTypeOther = documentTypeOther;
    }

    public String getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(String respondentId) {
        this.respondentId = respondentId;
    }

    public String getRespondentPhoneNo() {
        return respondentPhoneNo;
    }

    public void setRespondentPhoneNo(String respondentPhoneNo) {
        this.respondentPhoneNo = respondentPhoneNo;
    }

    public IncomeSourceEnum getHouseholdIncomeSource() {
        return householdIncomeSource;
    }

    public void setHouseholdIncomeSource(IncomeSourceEnum householdIncomeSource) {
        this.householdIncomeSource = householdIncomeSource;
    }

    public String getIncomeSourceOther() {
        return incomeSourceOther;
    }

    public void setIncomeSourceOther(String incomeSourceOther) {
        this.incomeSourceOther = incomeSourceOther;
    }

    public Integer getHouseholdMonthlyAvgIncome() {
        return householdMonthlyAvgIncome;
    }

    public void setHouseholdMonthlyAvgIncome(Integer householdMonthlyAvgIncome) {
        this.householdMonthlyAvgIncome = householdMonthlyAvgIncome;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public SelectionCriteriaEnum getSelectionCriteria() {
        return selectionCriteria;
    }

    public void setSelectionCriteria(SelectionCriteriaEnum selectionCriteria) {
        this.selectionCriteria = selectionCriteria;
    }

    public List<SelectionReasonEnum> getSelectionReason() {
        return selectionReason;
    }

    public void setSelectionReason(List<SelectionReasonEnum> selectionReason) {
        this.selectionReason = selectionReason;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getHouseholdSize() {
        return householdSize;
    }

    public void setHouseholdSize(Integer householdSize) {
        this.householdSize = householdSize;
    }

    public HouseholdMember getHouseholdMember2() {
        return householdMember2;
    }

    public void setHouseholdMember2(HouseholdMember householdMember2) {
        this.householdMember2 = householdMember2;
    }

    public HouseholdMember getHouseholdMember5() {
        return householdMember5;
    }

    public void setHouseholdMember5(HouseholdMember householdMember5) {
        this.householdMember5 = householdMember5;
    }

    public HouseholdMember getHouseholdMember17() {
        return householdMember17;
    }

    public void setHouseholdMember17(HouseholdMember householdMember17) {
        this.householdMember17 = householdMember17;
    }

    public HouseholdMember getHouseholdMember35() {
        return householdMember35;
    }

    public void setHouseholdMember35(HouseholdMember householdMember35) {
        this.householdMember35 = householdMember35;
    }

    public HouseholdMember getHouseholdMember64() {
        return householdMember64;
    }

    public void setHouseholdMember64(HouseholdMember householdMember64) {
        this.householdMember64 = householdMember64;
    }

    public HouseholdMember getHouseholdMember65() {
        return householdMember65;
    }

    public void setHouseholdMember65(HouseholdMember householdMember65) {
        this.householdMember65 = householdMember65;
    }

    public Boolean getReadWrite() {
        return isReadWrite;
    }

    public void setReadWrite(Boolean readWrite) {
        isReadWrite = readWrite;
    }

    public Integer getMemberReadWrite() {
        return memberReadWrite;
    }

    public void setMemberReadWrite(Integer memberReadWrite) {
        this.memberReadWrite = memberReadWrite;
    }

    public Boolean getOtherMemberPerticipating() {
        return isOtherMemberPerticipating;
    }

    public void setOtherMemberPerticipating(Boolean otherMemberPerticipating) {
        isOtherMemberPerticipating = otherMemberPerticipating;
    }

    public NonPerticipationReasonEnum getNotPerticipationReason() {
        return notPerticipationReason;
    }

    public void setNotPerticipationReason(NonPerticipationReasonEnum notPerticipationReason) {
        this.notPerticipationReason = notPerticipationReason;
    }

    public String getNotPerticipationOtherReason() {
        return notPerticipationOtherReason;
    }

    public void setNotPerticipationOtherReason(String notPerticipationOtherReason) {
        this.notPerticipationOtherReason = notPerticipationOtherReason;
    }

    public List<Nominee> getNominees() {
        return nominees;
    }

    public void setNominees(List<Nominee> nominees) {
        this.nominees = nominees;
    }

    public List<Biometric> getBiometrics() {
        return biometrics;
    }

    public void setBiometrics(List<Biometric> biometrics) {
        this.biometrics = biometrics;
    }

    public AlternatePayee getAlternatePayee1() {
        return alternatePayee1;
    }

    public void setAlternatePayee1(AlternatePayee alternatePayee1) {
        this.alternatePayee1 = alternatePayee1;
    }

    public AlternatePayee getAlternatePayee2() {
        return alternatePayee2;
    }

    public void setAlternatePayee2(AlternatePayee alternatePayee2) {
        this.alternatePayee2 = alternatePayee2;
    }
}

