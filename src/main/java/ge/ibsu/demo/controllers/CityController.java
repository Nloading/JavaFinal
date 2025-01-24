package ge.ibsu.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ge.ibsu.demo.dto.AddCity;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.services.CityService;

/**
 * CityController
 */
@RestController
@RequestMapping(value = "/api/city")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = { "application/json" })
    public List<City> getAll() {
        return cityService.getAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json" })
    public void addCity(@RequestBody AddCity city) {
        this.cityService.addCity(city);
    }
}
