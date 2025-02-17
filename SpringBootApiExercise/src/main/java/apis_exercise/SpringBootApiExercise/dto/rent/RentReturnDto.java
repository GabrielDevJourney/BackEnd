package apis_exercise.SpringBootApiExercise.dto.rent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentReturnDto {
	@NotNull
	private Long id;
	@NotNull
	@Min(value = 1)
	private int endKilometers;
}
