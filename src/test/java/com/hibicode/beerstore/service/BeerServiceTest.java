package com.hibicode.beerstore.service;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.BeerType;
import com.hibicode.beerstore.repository.BeersRepository;
import com.hibicode.beerstore.service.exception.BeerAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

public class BeerServiceTest {

    private BeerService beerService;

    @Mock
    private BeersRepository beersMocked;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        beerService = new BeerService(beersMocked);
    }

    @Test
    public void should_deny_creation_of_beer_that_exists() {
        RuntimeException e = Assertions.assertThrows(BeerAlreadyExistsException.class, () ->{

        Beer beerInDatabase = new Beer();
        beerInDatabase.setId(10L);
        beerInDatabase.setName("Heineken");
        beerInDatabase.setType(BeerType.LAGER);
        beerInDatabase.setVolume(new BigDecimal("355"));

        when(beersMocked.findByNameAndType("Heineken", BeerType.LAGER)).thenReturn(Optional.of(beerInDatabase));

        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        beerService.save(newBeer);
        });
    }

    @Test
    public void should_create_new_beer() {
        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        Beer newBeerInDatabse = new Beer();
        newBeerInDatabse.setId(10L);
        newBeerInDatabse.setName("Heineken");
        newBeerInDatabse.setType(BeerType.LAGER);

        when(beersMocked.save(newBeer)).thenReturn(newBeerInDatabse);
        Beer beerSaved = beerService.save(newBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }
}
