package apis_exercise.SpringBootApiExercise.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "active")
	private boolean active;

	@Column(name="email",unique = true)
	@NotNull
	private String email;

	@Column(name = "phone", unique = true)
	@NotNull
	private String phoneNumber;

	@Column(name = "age")
	@NotNull
	@Range(min = 18, max = 99)
	private Integer age;
}
