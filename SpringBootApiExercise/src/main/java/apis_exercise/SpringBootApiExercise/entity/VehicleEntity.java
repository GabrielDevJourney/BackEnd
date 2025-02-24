package apis_exercise.SpringBootApiExercise.entity;
import apis_exercise.SpringBootApiExercise.enums.VehicleStatus;
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

	@Column(name = "brand", nullable = false)
	private String brand;

	@Column(name = "model")
	private String model;

	@Column(name = "color")
	private String color;

	@Column(name = "year")
	private int yearManufacture;

	@Column(name = "plate", nullable = false, unique = true)
	private String plate;

	@Column(name = "daily_rate")
	private double dailyRate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private VehicleStatus status;

	@Column(name = "current_kilometers", nullable = false)
	private int currentKilometers;

}