package apis_exercise.SpringBootApiExercise.dto.rent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentReturnDto {
	private Long id;
	private int endKilometers;
}
