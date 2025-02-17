package apis_exercise.SpringBootApiExercise.repository;

import apis_exercise.SpringBootApiExercise.entity.AccountEntity;
import apis_exercise.SpringBootApiExercise.entity.RentEntity;
import apis_exercise.SpringBootApiExercise.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<RentEntity, Long> {
	RentEntity findByIdAndStatus(Long id, RentalStatus status);
	RentEntity findByAccountEntity_Id(Long accountId);
	RentEntity findByVehicleEntity_Id(Long vehicleId);
	RentEntity findByStatus(RentalStatus status);
	RentEntity findByVehicleEntity_IdAndStatus(Long vehicleId, RentalStatus status);

	List<RentEntity> findAllByAccountEntity_Id(Long accountId);
	List<RentEntity> findAllByVehicleEntity_Id(Long vehicleId);
	List<RentEntity> findAllByStatus(RentalStatus status);
}