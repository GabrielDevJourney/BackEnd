package apis_exercise.SpringBootApiExercise.dto.rent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalReturnDto {
	@NotNull
	private Long id;
	@NotNull
	@Min(value = 1)
	private int endKilometers;
}
