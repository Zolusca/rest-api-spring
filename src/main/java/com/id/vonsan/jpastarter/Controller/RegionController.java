package com.id.vonsan.jpastarter.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.id.vonsan.jpastarter.DTO.RegionRequest;
import com.id.vonsan.jpastarter.DTO.ResponseHandler;
import com.id.vonsan.jpastarter.Entity.Region;
import com.id.vonsan.jpastarter.Service.ServiceImpl.RegionService;



// TODO - membuat mapper region response dengan status
@RestController
@RequestMapping(path = "/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping
    (
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> insertingRegion
            (
                    @Valid
                    @RequestBody RegionRequest regionRequest
            )
    {
        // mapper object region
        Region region = new Region();
        region.setName(regionRequest.getName());

        // proses insert
        Region regionSave = regionService.create(region);

        // return new ResponseEntity<>(regionSave,HttpStatus.CREATED);
        return ResponseHandler.customResponse
        (
            "success inserting data",
            HttpStatus.CREATED,
            regionSave
        );
    }

    @GetMapping
    (
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getAllRegion(){
        List<Region> regions = regionService.getAll();  
                
        return ResponseHandler.customResponse
        (
            "success",
            HttpStatus.OK,
            regions
        );
    }
    
    @GetMapping
    (
        path = "/{id}/detail",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getRegionById
                    (
                        @PathVariable(value = "id")Integer id
                    )
    {
        // pengecekan data
        Region region = regionService.getById(id);

        return ResponseHandler.customResponse
        (
            "success",
            HttpStatus.OK, 
            region
        );
    }

    
    @PutMapping
    (
        path = "/{id}/update"
    )
    public ResponseEntity<Object> updateRegion
            (
                @PathVariable(value = "id")Integer id,
                @Valid @RequestBody RegionRequest regionRequest
            )
    {
        // mendapatkan data
        Region region = regionService.getById(id);

        // set new name
        region.setName(regionRequest.getName());
        
        // update proses
        regionService.update(region);
        
        return ResponseHandler.customResponse
        (
            "success", 
            HttpStatus.CREATED, 
            region
        );
    }

    @DeleteMapping
    (
        path = "/{id}/delete"
    )
    public ResponseEntity<Object> deleteRegion
            (
                    @PathVariable(value = "id")Integer id
            )
    { 
        // deleting data
        regionService.delete(id);

        // return new ResponseEntity<>(region,HttpStatus.OK);
        return ResponseHandler.customResponse
        (
            "sucess deleted", 
            HttpStatus.OK,
            null
        );
    }
}