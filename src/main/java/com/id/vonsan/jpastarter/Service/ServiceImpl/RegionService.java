package com.id.vonsan.jpastarter.Service.ServiceImpl;

import java.util.List;
import java.util.Optional;

import com.id.vonsan.jpastarter.Exception.AlreadyExistException;
import com.id.vonsan.jpastarter.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.id.vonsan.jpastarter.Entity.Region;
import com.id.vonsan.jpastarter.Repository.RegionRepository;
import com.id.vonsan.jpastarter.Service.ServiceGeneric;

@Service
public class RegionService implements ServiceGeneric<Region,Integer> {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Region create(Region region) {
        // pengecekan apakah nama region telah ada
        Optional<Region> result = regionRepository
                                    .findByNameLike(region.getName());

        // jika ada maka terkena exception
        if(result.isPresent()){
            throw new AlreadyExistException("Name already exists");
        }

        // melakukan insert
        return regionRepository.save(region);
    }

    @Override
    public void delete(Integer id) {
        // pencarian data
        Region region = regionRepository
                                .findById(id)
                                .orElseThrow(
                                    ()->new NotFoundException("region tidak ditemukan"));
        // delete data
        regionRepository.delete(region);
    }

    @Override
    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region update(Region region) {
        return regionRepository.save(region);
    }
    
    @Override
    public Region getById(Integer id) {
        return regionRepository
                    .findById(id)
                    .orElseThrow(() ->
                        new NotFoundException("region tidak ditemukan"));
    }
}
