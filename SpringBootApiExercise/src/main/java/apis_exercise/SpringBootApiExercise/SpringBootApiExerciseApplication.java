package apis_exercise.SpringBootApiExercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "apis_exercise")
@EnableJpaRepositories(basePackages = "apis_exercise")
@EntityScan(basePackages = "apis_exercise")
public class SpringBootApiExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiExerciseApplication.class, args);
	}

}
