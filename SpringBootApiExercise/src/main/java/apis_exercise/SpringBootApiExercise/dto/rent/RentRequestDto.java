package apis_exercise.SpringBootApiExercise.dto.rent;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentRequestDto {
	@NotNull(message = "Account ID is required")
	private Long accountId;

	@NotNull(message = "Vehicle ID is required")
	private Long vehicleId;

	@NotNull(message = "Start date is required")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateStart;

	@NotNull(message = "End date is required")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateEnd;

	@NotNull(message = "Start kilometers is required")
	@Min(value = 0, message = "Start kilometers must be positive")
	private int startKilometers;
}
