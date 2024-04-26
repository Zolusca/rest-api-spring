package com.id.vonsan.jpastarter.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryRequest {
    @NotBlank @Size(max = 2, message = "length for code maximum 2 characther")
    private String code;
    @NotBlank @Size(max = 20, message = "length for name maximum 20 characther")
    private String name;
    @NotNull
    private Integer region;
    
    public CountryRequest() {
    }
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getRegion() {
        return region;
    }
    public void setRegion(Integer region) {
        this.region = region;
    }
}