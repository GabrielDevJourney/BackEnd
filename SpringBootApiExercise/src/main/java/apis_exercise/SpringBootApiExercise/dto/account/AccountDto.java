package apis_exercise.SpringBootApiExercise.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	@NotBlank(message = "Must have first name")
	private String firstName;

	@NotBlank(message = "Must have last name")
	private String lastName;

	@NotEmpty(message = "Must have email")
	@Email(message = "Email must be valid")
	private String email;

	@NotBlank(message = "Must have phone number")
	@Pattern(regexp = "^\\+351\\s9\\d{2}\\s\\d{3}\\s\\d{3}$",
			message = "Invalid phone number format. Expected format: +351 9xx xxx xxx.")
	private String phoneNumber;

	@NotNull(message = "Must have age")
	@Range(min = 18, max = 99, message = "Age must be between 18 and 99")
	private int age;
}