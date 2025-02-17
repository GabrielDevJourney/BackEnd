package apis_exercise.SpringBootApiExercise.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	@NotBlank(message = "Must have first name")
	private String firstName;

	@NotBlank(message = "Must have last name")
	private String lastName;

	@NotEmpty(message = "Name may not be empty")
	@Email(message = "Email must be valid")
	private String email;
}
