package com.id.vonsan.jpastarter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.id.vonsan.jpastarter.Entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {
    Boolean existsByCode(String code);
}
