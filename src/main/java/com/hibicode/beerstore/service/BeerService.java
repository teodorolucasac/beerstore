package com.hibicode.beerstore.service;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.repository.BeersRepository;
import com.hibicode.beerstore.service.exception.BeerAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerService {

    private BeersRepository beersRepository;

    public BeerService(@Autowired BeersRepository beersRepository){
        this.beersRepository = beersRepository;
    }

    public Beer save(final Beer beer) {
        verifyIfBeerExists(beer);
        return beersRepository.save(beer);
    }

    public void verifyIfBeerExists(final Beer beer) {
        Optional<Beer> beerByNameAndType = beersRepository.findByNameAndType
                (beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent() && (beer.isNew() || isUpdatingToADifferentBeer(beer, beerByNameAndType))) {
            throw new BeerAlreadyExistsException();
        }
    }

    private boolean isUpdatingToADifferentBeer(Beer beer, Optional<Beer> beerByNameAndType) {
        return beer.alreadyExist() && !beerByNameAndType.get().equals(beer);
    }
}
