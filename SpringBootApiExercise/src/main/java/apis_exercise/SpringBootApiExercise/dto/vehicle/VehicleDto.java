package apis_exercise.SpringBootApiExercise.dto.vehicle;

import apis_exercise.SpringBootApiExercise.enums.VehicleStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {

	@NotBlank(message = "Plate is required")
	@Pattern(regexp = "^[A-Z]{2}-[0-9]{2}-[A-Z]{2}$",
			message = "Invalid plate format. Must match Portuguese format (e.g., AA-12-BB)")
	private String plate;

	@NotBlank(message = "Brand is required")
	@Size(min = 2, max = 50, message = "Brand must be between 2 and 50 characters")
	private String brand;

	@Size(max = 50, message = "Model must be up to 50 characters")
	private String model;

	@Size(max = 30, message = "Color must be up to 30 characters")
	private String color;

	@Min(value = 1886, message = "Year must be a valid year (greater than or equal to 1886)")
	private int yearManufacture;

	@Min(value = 0, message = "Daily rate must be positive")
	private double dailyRate;

	private VehicleStatus status;

	@Min(value = 0, message = "Kilometers must be positive")
	private int currentKilometers;
}