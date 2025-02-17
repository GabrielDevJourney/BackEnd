package apis_exercise.SpringBootApiExercise.mapper;

import apis_exercise.SpringBootApiExercise.dto.rent.RentRequestDto;
import apis_exercise.SpringBootApiExercise.dto.rent.RentResponseDto;
import apis_exercise.SpringBootApiExercise.entity.RentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentMapper {
	//to create renting no need for toEntity
	@Mapping(target = "accountEntity.id", source = "accountId")
	@Mapping(target = "vehicleEntity.id",source = "vehicleId")
	RentEntity toEntityRequest(RentRequestDto dto);

	@Mapping(target = "accountId", source = "accountEntity.id")
	@Mapping(target = "vehicleId", source = "vehicleEntity.id")
	@Mapping(source = "dateStart", target = "dateStart")
	@Mapping(source = "dateEnd", target = "dateEnd")
	@Mapping(source = "dateReturn", target = "dateReturn")
	@Mapping(source = "startKilometers", target = "startKilometers")
	@Mapping(source = "endKilometers", target = "endKilometers")
	@Mapping(source = "status", target = "rentalStatus")
	RentResponseDto toDtoResponse(RentEntity entity);

	List<RentResponseDto> toDtoList(List<RentEntity> entities);

}
