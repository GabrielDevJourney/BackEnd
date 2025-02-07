package apis_exercise.SpringBootApiExercise.repository;

import apis_exercise.SpringBootApiExercise.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {
		boolean existsByPlate(String plate);
		List<VehicleEntity> findByActiveIsTrueAndAccountEntityActiveIsFalse();
		List<VehicleEntity> findLicensePlateByActiveIsTrueAndAccountEntityActiveIsFalse();
}
