package apis_exercise.SpringBootApiExercise.mapper;

import apis_exercise.SpringBootApiExercise.dto.VehicleDto;
import apis_exercise.SpringBootApiExercise.entity.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface VehicleMapper {
	VehicleDto toDto(VehicleEntity entity);

	VehicleEntity toEntity(VehicleDto dto);

	List<VehicleDto> toDtoList(List<VehicleEntity> entities);
}