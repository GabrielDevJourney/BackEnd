package apis_exercise.SpringBootApiExercise.mapper;

import apis_exercise.SpringBootApiExercise.dto.VehicleDto;
import apis_exercise.SpringBootApiExercise.entity.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface VehicleMapper {
	@Mapping(target = "accountId", source = "accountEntity.id")
	VehicleDto toDto(VehicleEntity entity);

	@Mapping(target = "accountEntity", ignore = true)
	VehicleEntity toEntity(VehicleDto dto);

	List<VehicleDto> toDtoList(List<VehicleEntity> entities);
}