package apis_exercise.SpringBootApiExercise.repository;

import apis_exercise.SpringBootApiExercise.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
	boolean existsByPlate(String plate);

	Optional<VehicleEntity> findByPlate(String plate);

}
