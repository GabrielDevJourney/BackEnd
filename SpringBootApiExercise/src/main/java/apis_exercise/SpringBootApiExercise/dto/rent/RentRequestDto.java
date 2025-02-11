package apis_exercise.SpringBootApiExercise.dto.rent;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentRequestDto {
	private Integer id;
	private Integer accountId;
	private Integer vehicleId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateStart;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateEnd;

	private Integer startKilometers;
}
