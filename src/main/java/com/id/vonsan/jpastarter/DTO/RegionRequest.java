package com.id.vonsan.jpastarter.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegionRequest {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 25,message = "length for name maximum 25 characther")
    private String name;
    
    public RegionRequest() {
    }
    
    public RegionRequest(String name) {
        setName(name);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        // kapitalisasi karakter pertama ex : apa -> Apa
        this.name = name.substring(0,1)
                        .toUpperCase()+name.substring(1);
    }
    
}
