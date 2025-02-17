package apis_exercise.SpringBootApiExercise.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstLastNameDto {
	private String firstName;
	private String lastName;
}
