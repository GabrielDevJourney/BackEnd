package apis_exercise.SpringBootApiExercise.mapper;

import apis_exercise.SpringBootApiExercise.dto.rent.RentalRequestDto;
import apis_exercise.SpringBootApiExercise.dto.rent.RentalResponseDto;
import apis_exercise.SpringBootApiExercise.entity.RentalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {
	//to create renting no need for toEntity
	@Mapping(target = "accountEntity.id", source = "accountId")
	@Mapping(target = "vehicleEntity.id",source = "vehicleId")
	RentalEntity toEntityRequest(RentalRequestDto dto);

	@Mapping(target = "accountId", source = "accountEntity.id")
	@Mapping(target = "vehicleId", source = "vehicleEntity.id")
	@Mapping(source = "dateStart", target = "dateStart")
	@Mapping(source = "dateEnd", target = "dateEnd")
	@Mapping(source = "dateReturn", target = "dateReturn")
	@Mapping(source = "startKilometers", target = "startKilometers")
	@Mapping(source = "endKilometers", target = "endKilometers")
	@Mapping(source = "status", target = "rentalStatus")
	RentalResponseDto toDtoResponse(RentalEntity entity);

	List<RentalResponseDto> toDtoList(List<RentalEntity> entities);

}
