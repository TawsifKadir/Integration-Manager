package com.kit.integrationmanager.payload.download.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class BeneficiaryDownloadRequest {
    @JsonProperty("bomaId")
    private long bomaId;
    @JsonProperty("size")
    private int size;
    @JsonProperty("page")
    private int page;

    public long getBomaId() {
        return bomaId;
    }

    public void setBomaId(long bomaId) {
        this.bomaId = bomaId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
