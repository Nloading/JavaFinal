package ge.ibsu.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ge.ibsu.demo.entities.City;

/**
 * CityRepository
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	City findOneByCity(String city);

}
