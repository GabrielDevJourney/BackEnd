package apis_exercise.SpringBootApiExercise.repository;

import apis_exercise.SpringBootApiExercise.entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<RentEntity, Integer> {
	//- Vehicle availability check

	//- Account rental history
	//- Active rentals
	//- Date range conflicts

}
