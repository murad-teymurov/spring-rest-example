package com.company.YearSpringRestApi.repository;

import com.company.YearSpringRestApi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

    List<City> findAllByName(String name);

    Optional<City> findByName(String name);
}
