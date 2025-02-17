package apis_exercise.SpringBootApiExercise.repository;

import apis_exercise.SpringBootApiExercise.entity.RentEntity;
import apis_exercise.SpringBootApiExercise.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RentRepository extends JpaRepository<RentEntity, Long> {
	RentEntity findByIdAndStatus(Long id, RentalStatus status);
	RentEntity findByAccountEntity_Id(Long accountId);
	RentEntity findByVehicleEntity_Id(Long vehicleId);
	RentEntity findByVehicleEntity_IdAndStatus(Long vehicleId, RentalStatus status);
	RentEntity findByAccountEntity_IdAndStatus(Long accountId, RentalStatus status);

	List<RentEntity> findAllByAccountEntity_Id(Long accountId);
	List<RentEntity> findAllByVehicleEntity_Id(Long vehicleId);
	List<RentEntity> findAllByStatus(RentalStatus status);

	@Query("SELECT r FROM RentEntity r WHERE r.vehicleEntity.id = :vehicleId AND "
			+ "((:dateStart BETWEEN r.dateStart AND r.dateEnd) OR "
			+ "(:dateEnd BETWEEN r.dateStart AND r.dateEnd) OR "
			+ "(r.dateStart BETWEEN :dateStart AND :dateEnd) OR "
			+ "(r.dateEnd BETWEEN :dateStart AND :dateEnd))")
	List<RentEntity> findOverlappingRentals(@Param("vehicleId") Long vehicleId,
	                                        @Param("dateStart") LocalDate dateStart,
	                                        @Param("dateEnd") LocalDate dateEnd);
}