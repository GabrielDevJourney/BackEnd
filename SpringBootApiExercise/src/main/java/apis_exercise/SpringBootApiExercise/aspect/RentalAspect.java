package apis_exercise.SpringBootApiExercise.aspect;

import apis_exercise.SpringBootApiExercise.dto.rent.RentalRequestDto;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RentalAspect {
	private static final Logger logger = LoggerFactory.getLogger(RentalAspect.class);

	@Before("execution(* apis_exercise.SpringBootApiExercise.service.RentalService.createRenting(..)) && args(rentalRequestDto)")
	public void logBeforeCreateRental(RentalRequestDto rentalRequestDto) {
		logger.info("Creating rental for account ID: {} with vehicle ID: {}.",
				rentalRequestDto.getAccountId(), rentalRequestDto.getVehicleId());
	}

	@After("execution(* apis_exercise.SpringBootApiExercise.service.RentalService.createRenting(..))")
	public void logAfterCreateRental() {
		logger.info("Rental created with success!");
	}

	@Before("execution(* apis_exercise.SpringBootApiExercise.service.RentalService.endRenting(..)) && args(id, endKilometers)")
	public void logBeforeEndRental(Long id, int endKilometers) {
		logger.info("Ending rental with ID: {} at {} kilometers.", id, endKilometers);
	}

	@After("execution(* apis_exercise.SpringBootApiExercise.service.RentalService.endRenting(..))")
	public void logAfterEndRental() {
		logger.info("Rental ended with success!");
	}
}
