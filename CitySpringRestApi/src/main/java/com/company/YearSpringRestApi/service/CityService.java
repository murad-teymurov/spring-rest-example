package com.company.YearSpringRestApi.service;

import com.company.YearSpringRestApi.exception.CityAlreadyExistsException;
import com.company.YearSpringRestApi.model.City;
import com.company.YearSpringRestApi.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getAll(){
         return cityRepository.findAll();
    }

    public City getById(int id){
       return cityRepository.findById(id)
//               .stream()
//               .filter(city -> city.getId() == id)
//               .findFirst()
               .orElseThrow(() -> new RuntimeException());
    }

    public void add(City city){
       Optional<City> city1 =  cityRepository.findByName(city.getName());
       if(city1.isPresent()){
           throw  new CityAlreadyExistsException("City already exist with name"+"-> "+ city.getName());
       }
        cityRepository.save(city);
    }

    public void deleteWithId(int id){
        cityRepository.deleteById(id);
    }

    public void update(int id,City newCity){
        City oldCity = getById(id);
        oldCity.setName(newCity.getName());
        cityRepository.save(oldCity);
    }

    public List<City> getCityByName(String name){
        return cityRepository.findAllByName(name);
    }


}
