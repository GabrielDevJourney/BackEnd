package apis_exercise.SpringBootApiExercise.mapper;

import apis_exercise.SpringBootApiExercise.dto.account.AccountDto;
import apis_exercise.SpringBootApiExercise.dto.account.FirstLastNameDto;
import apis_exercise.SpringBootApiExercise.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	AccountDto toDto(AccountEntity entity);
	AccountEntity toEntity(AccountDto dto);
	List<AccountDto> toDtoList(List<AccountEntity> entities);

	@Named("toFirstLastNameDto")
	@Mapping(target = "firstName", source = "entity.firstName")
	@Mapping(target = "lastName", source = "entity.lastName")
	FirstLastNameDto toFirstLastNameDto(AccountEntity entity);


}
