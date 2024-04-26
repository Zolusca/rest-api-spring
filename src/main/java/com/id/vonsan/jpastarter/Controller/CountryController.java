package com.id.vonsan.jpastarter.Controller;


import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
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

import com.id.vonsan.jpastarter.DTO.CountryRequest;
import com.id.vonsan.jpastarter.DTO.ResponseHandler;
import com.id.vonsan.jpastarter.Entity.Country;
import com.id.vonsan.jpastarter.Entity.Region;
import com.id.vonsan.jpastarter.Service.ServiceImpl.CountryService;
import com.id.vonsan.jpastarter.Service.ServiceImpl.RegionService;
import com.id.vonsan.jpastarter.Util.StringOperation;


@RestController
@RequestMapping("/countries")
public class CountryController {
    
    @Autowired
    private RegionService regionService;

    @Autowired
    private CountryService countryService;
    

    // mapper untuk country request ke object country
    Function<CountryRequest,Country> countryRequestMapper = new Function<CountryRequest, Country>() {
        @Override
        public Country apply(CountryRequest countryRequest) {
            Country country = new Country();
            Region region   = regionService
                                    .getById(countryRequest.getRegion());

            country.setCode(countryRequest.getCode().toUpperCase());
            country.setName(StringOperation.capitalizeFirstWord(countryRequest.getName()));
            country.setRegion(region);

            return country;
        }

    };


    @PostMapping
    (
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> insertingCountry
                (
                    @Valid
                    @RequestBody CountryRequest countryRequest
                )
    {
        // membuat object country mengunakan mapper functional
        Country country = countryService
                                    .create(
                                        countryRequestMapper
                                            .apply(countryRequest)
                                            );

        
        return ResponseHandler.customResponse
        (
            "success creating", 
            HttpStatus.OK, 
            country
        );
    }


    @GetMapping
    (
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getCountryList(){

        // mendapatkan country list dan mengurutkanya sesuai code
        List<Country> countryList = countryService
                                        .getAll()
                                        .stream()
                                        .sorted(new Comparator<Country>() {
                                                @Override
                                                public int compare(Country o1, Country o2) {
                                                    return o1.getCode().compareTo(o2.getCode());
                                                }
                                                })
                                        .collect(Collectors.toList());

        return ResponseHandler.customResponse
        (
            "success", 
            HttpStatus.OK, 
            countryList
        );
    }


    @DeleteMapping
    (
        path = "/{id}/delete",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> deleteCountryById
            (
                @PathVariable(value = "id") Integer id
            )
    {
        Country country = countryService.getById(id);

        countryService.delete(country.getId());

        return ResponseHandler.customResponse
        (
            "success deleted", 
            HttpStatus.OK, 
            country
        );
    }


    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateCountry
            (
                @PathVariable(value = "id")Integer id,
                @Valid @RequestBody CountryRequest countryRequest
            )
    {
        // mapping data
        // new means country contains data update
        Country countryNew = countryRequestMapper.apply(countryRequest);
        
        // find country
        // old means country old data
        Country countryOld = countryService.getById(id);
        countryNew.setId(countryOld.getId());

        // proses update
        countryNew = countryService.update(countryNew);
    
        return ResponseHandler.customResponse
        (
            "success updated", 
            HttpStatus.CREATED, 
            countryNew
        );
    }

    @GetMapping
    (
        path = "/{id}/detail"
    )
    public ResponseEntity<Object> getCountryById
            (
                @PathVariable(value = "id")Integer id
            )
    {
        Country country = countryService.getById(id);
        
        return ResponseHandler.customResponse
        (
            "success", 
            HttpStatus.OK,
            country
        );
    }
}
