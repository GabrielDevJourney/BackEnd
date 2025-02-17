package apis_exercise.SpringBootApiExercise.dto.rent;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentRequestDto {
	private Long accountId;
	private Long vehicleId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateStart;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateEnd;

	private int startKilometers;
}
