package apis_exercise.SpringBootApiExercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class
VehicleDto {
	private String plate;
	private String name;
	private int currentKilometers;
}
