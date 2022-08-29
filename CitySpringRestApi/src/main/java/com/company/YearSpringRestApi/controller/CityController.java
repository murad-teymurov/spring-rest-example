package com.company.YearSpringRestApi.controller;

import com.company.YearSpringRestApi.exception.CityAlreadyExistsException;
import com.company.YearSpringRestApi.model.City;
import com.company.YearSpringRestApi.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

//    private  final List<City> cities = new ArrayList<>();

//    public CityController(){

//           City c1 = City.builder()
//                   .id(1)
//                   .name("BAku")
//                   .build();
//           City c2 = City.builder()
//                   .id(2)
//                   .name("Saatli")
//                   .build();
//           cities.add(c1);
//           cities.add(c2);
//
//    }

    @GetMapping("/cities")
    public ResponseEntity<List> getCities(){
      List<City> cities = cityService.getAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<City> getCityById(@PathVariable int id){
        City cityOne = cityService.getById(id);
        return new ResponseEntity<>(cityOne,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody City city){
        cityService.add(city);
        return new ResponseEntity<>(HttpStatus.CREATED);
//        cities.add(city);
    }
//    @PostMapping
//    public void create(@RequestBody City city){
//        cities.add(city);
//    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody City newCity){
        cityService.update(id,newCity);
        return new ResponseEntity<>(HttpStatus.OK);
//        City oldCity = getCity(id);
//        oldCity.setName(newCity.getName());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        cityService.deleteWithId(id);
        return new ResponseEntity<>(HttpStatus.OK);
//        City city = getCity(id);
//        cities.remove(city);
    }

    @GetMapping()
    public ResponseEntity<List<City>> getCityByName(String name){
        return new ResponseEntity<>( cityService.getCityByName(name),HttpStatus.OK);
    }

    private City getCity(int id){
        return cityService.getById(id);
//        return cities.stream()
//                .filter(city -> city.getId() == (id))
//                .findFirst().orElseThrow(() ->new RuntimeException());
    }

    @ExceptionHandler(CityAlreadyExistsException.class)
    public ResponseEntity<String> handleCityAlreadyExistsException(CityAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
