package apis_exercise.SpringBootApiExercise.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
	@NotBlank(message = "Plate is required")
	@Pattern(regexp = "^[A-Z]{2}-[0-9]{2}-[A-Z]{2}$",
			message = "Invalid plate format. Must match Portuguese format (e.g., AA-12-BB)")
	private String plate;

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;

	@Min(value = 0, message = "Kilometers must be positive")
	private int currentKilometers;
}
