package com.id.vonsan.jpastarter.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.vonsan.jpastarter.Entity.Country;
import com.id.vonsan.jpastarter.Exception.AlreadyExistException;
import com.id.vonsan.jpastarter.Exception.NotFoundException;
import com.id.vonsan.jpastarter.Repository.CountryRepository;
import com.id.vonsan.jpastarter.Service.ServiceGeneric;

@Service
public class CountryService implements ServiceGeneric<Country,Integer>{

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country create(Country country) {
        // cek data country apakah ada dari code dan name
        if(countryRepository.existsByCode(country.getCode()))
           {
               throw new AlreadyExistException
                    (
                        "Code or name already exist "
                    );
           }

        return countryRepository.save(country);
    }

    @Override
    public void delete(Integer id) {
        Country country = getById(id);
        countryRepository.delete(country);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country update(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country getById(Integer id) {
        Country country = countryRepository
                                .findById(id)
                                .orElseThrow
                                (
                                    ()->new NotFoundException("data tidak ditemukan")
                                );
                                            
        return country;
    }
    
}
