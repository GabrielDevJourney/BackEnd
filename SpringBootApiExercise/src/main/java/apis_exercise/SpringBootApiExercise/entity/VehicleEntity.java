package entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RequestMapping(name = "vehicle")
public class VehicleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "account_id",referencedColumnName = "id")
	private AccountEntity accountEntity;

	@Column(name = "plate", unique = true)
	private String plate;

	@Column(name = "active")
	private boolean active;


}
