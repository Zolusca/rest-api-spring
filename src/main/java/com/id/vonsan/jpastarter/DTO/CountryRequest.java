package com.id.vonsan.jpastarter.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryRequest {
    @NotBlank(message = "code cannot be blank") 
    @Size
    (
        max = 2,
        min = 2,
        message = "code size must be 2 characther"
    )
    private String code;
    @NotBlank(message = "name cannot be blank")
    @Size
    (
        max = 20,
        min = 5, 
        message = "name size atleast min 5 and max 20 characther"
    )
    private String name;
    @NotNull(message = "region id cannot be null")
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