package ge.ibsu.demo.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import ge.ibsu.demo.dto.AddCity;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.repositories.CityRepository;

/**
 * CityService
 */
@Service
public class CityService {
    CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void addCity(AddCity city) {
        City cityEnt = new City();
        cityEnt.setCity(city.getCity());
        this.cityRepository.save(cityEnt);
    }

    public List<City> getAll() {
        return this.cityRepository.findAll();
    }
}
