package com.kit.integrationmanager.payload.edit.response;

import com.kit.integrationmanager.payload.ResponseHeader;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryEditStatusResponse extends ResponseHeader {
private String applicationId;
}
