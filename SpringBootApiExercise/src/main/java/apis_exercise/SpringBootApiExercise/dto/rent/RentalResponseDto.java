package apis_exercise.SpringBootApiExercise.dto.rent;

import apis_exercise.SpringBootApiExercise.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponseDto {
	private Long id;
	private Long accountId;
	private Long vehicleId;
	private LocalDate dateStart;
	private LocalDate dateEnd;
	private LocalDate dateReturn;
	private int startKilometers;
	private int endKilometers;
	private RentalStatus rentalStatus;
}
