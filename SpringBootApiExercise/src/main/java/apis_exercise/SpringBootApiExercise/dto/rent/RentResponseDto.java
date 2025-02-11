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
	private Integer id;
	private Integer accountId;
	private Integer vehicleId;
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;
	private LocalDateTime dateReturn;
	private Integer startKilometers;
	private Integer endKilometers;
}
