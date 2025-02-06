package apis_exercise.SpringBootApiExercise.mapper;

import apis_exercise.SpringBootApiExercise.dto.AccountDto;
import apis_exercise.SpringBootApiExercise.entity.AccountEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	AccountDto toDto(AccountEntity entity);
	AccountEntity toEntity(AccountDto dto);
	List<AccountDto> toDtoList(List<AccountEntity> entities);

}
