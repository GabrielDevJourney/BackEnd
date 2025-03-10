package apis_exercise.SpringBootApiExercise.mapper;

import apis_exercise.SpringBootApiExercise.dto.rent.RentalRequestDto;
import apis_exercise.SpringBootApiExercise.dto.rent.RentalResponseDto;
import apis_exercise.SpringBootApiExercise.entity.RentalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {
	@Mapping(target = "accountEntity.id", source = "accountId")
	@Mapping(target = "vehicleEntity.id", source = "vehicleId")
	@Mapping(target = "dateReturn", ignore = true)
	@Mapping(target = "endKilometers", ignore = true)
	@Mapping(target = "totalPrice", ignore = true)
	@Mapping(target = "status", ignore = true)
	RentalEntity toEntityRequest(RentalRequestDto dto);

	@Mapping(target = "accountId", source = "accountEntity.id")
	@Mapping(target = "vehicleId", source = "vehicleEntity.id")
	@Mapping(target = "dateStart", source = "dateStart")
	@Mapping(target = "dateEnd", source = "dateEnd")
	@Mapping(target = "dateReturn", source = "dateReturn")
	@Mapping(target = "startKilometers", source = "startKilometers")
	@Mapping(target = "endKilometers", source = "endKilometers")
	@Mapping(target = "rentalStatus", source = "status")
	RentalResponseDto toDtoResponse(RentalEntity entity);

	List<RentalResponseDto> toDtoList(List<RentalEntity> entities);
}