package apis_exercise.SpringBootApiExercise.dto.rent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentResponseDto {
	private Long id;
	private Long accountId;
	private Long vehicleId;
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;
	private LocalDateTime dateReturn;
	private int startKilometers;
	private int endKilometers;
}
