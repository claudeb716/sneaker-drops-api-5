package com.pluralsight.sneakerdrops.service;

import com.pluralsight.sneakerdrops.data.BrandRepository;
import com.pluralsight.sneakerdrops.data.SneakerRepository;
import com.pluralsight.sneakerdrops.models.Brand;
import com.pluralsight.sneakerdrops.models.Sneaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SneakerServiceTest {
    @Mock
    private SneakerRepository sneakerRepository;
    @Mock
    private BrandRepository brandRepository;
    @InjectMocks //wired mocks to the real sneakerservice
    private SneakerService sneakerService;
    @Test
    void search_PriceRange_ReturnsPriceBetween(){
        //Arrange
        Sneaker highPrice =  new Sneaker("air force ones",90.00,1980,new Brand("Nike"));
        Sneaker midPrice =  new Sneaker("go walk",70.00,1995,new Brand("Skechers"));
        Sneaker lowPrice =  new Sneaker("air max",75.00,1995,new Brand("Nike"));
        when(sneakerRepository.findAll()).thenReturn(List.of(highPrice,midPrice,lowPrice));
        //Act
        List<Sneaker> found = sneakerService.search(null, null, null, 60.0, 100.0, null);
        //Assert
        assertEquals(1,found.size());
        assertEquals("midPrice",found.get(0).getModel());
    }

}