package phd.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import phd.demo.entities.City;

public interface CityRepository extends JpaRepository<City, Long>{
}
