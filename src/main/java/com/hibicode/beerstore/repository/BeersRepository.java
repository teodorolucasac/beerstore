package com.hibicode.beerstore.repository;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BeersRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByNameAndType(String name, BeerType type);
}
