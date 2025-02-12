package apis_exercise.SpringBootApiExercise.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class VehicleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "plate", nullable = false, unique = true)
	private String plate;

	@Column(name = "active", columnDefinition = "boolean default true")
	private boolean active;

	@Column(name = "current_kilometers", nullable = false)
	private int currentKilometers;
}