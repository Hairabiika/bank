package phd.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import phd.demo.entities.Sex;

public interface SexRepository extends JpaRepository<Sex, Long>{
}
