package apis_exercise.SpringBootApiExercise.aspect;

import apis_exercise.SpringBootApiExercise.dto.vehicle.VehicleDto;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VehicleAspect {
	private static final Logger logger = LoggerFactory.getLogger(VehicleAspect.class);

	@Before("execution(* apis_exercise.SpringBootApiExercise.service.VehicleService.createVehicle(..)) && args(vehicleDto)")
	public void logBeforeCreateVehicle(VehicleDto vehicleDto) {
		logger.info("Creating vehicle with Plate: {}, Name: {}",
				vehicleDto.getPlate(), vehicleDto.getBrand());
	}

	@After("execution(* apis_exercise.SpringBootApiExercise.service.VehicleService.createVehicle(..))")
	public void logAfterCreateVehicle() {
		logger.info("Vehicle created with success!");
	}
}
