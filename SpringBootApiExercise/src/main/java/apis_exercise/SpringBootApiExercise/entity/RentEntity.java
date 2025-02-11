package apis_exercise.SpringBootApiExercise.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class RentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	private AccountEntity accountEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id", nullable = false)
	private VehicleEntity vehicleEntity;

	@Column(name = "date_start", nullable = false)
	private LocalDate dateStart;

	@Column(name = "date_end", nullable = false)
	private LocalDate dateEnd;

	@Column(name = "date_return")
	private LocalDate dateReturn;

	@Column(name = "start_kilometers", nullable = false)
	@Min(value = 0, message = "Kilometers must be above 0")
	private int startKilometers;

	@Column(name = "end_kilometers")
	@Min(value = 0, message = "Kilometers must be above start kilometers")
	private int endKilometers;

	@AssertTrue(message = "End date must be after start date")
	private boolean isDateValid() {
		if (dateEnd == null || dateStart == null) return true;
		return dateEnd.isAfter(dateStart) || dateEnd.isEqual(dateStart);
	}

	@AssertTrue(message = "End kilometers must be greater than start kilometers")
	private boolean isKilometersValid() {
		if (endKilometers == 0) return true;
		return endKilometers >= startKilometers;
	}


}
