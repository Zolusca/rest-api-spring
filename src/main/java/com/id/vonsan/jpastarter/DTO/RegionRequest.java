package com.id.vonsan.jpastarter.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegionRequest {
    @NotBlank(message = "name cannot be blank")
    @Size
    (
        max = 10,
        min = 3,
        message = "name size atleast min 3 and max 10 characther"    
    )
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

    @Override
    public String toString() {
        return "RegionRequest [name=" + name + "]";
    }
    
    
}
