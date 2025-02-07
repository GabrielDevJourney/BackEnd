package apis_exercise.SpringBootApiExercise.repository;

import apis_exercise.SpringBootApiExercise.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {
	boolean existsByEmail(String email);

	List<AccountEntity> findByActiveIsFalse();

	List<AccountEntity> findByActiveIsFalseOrderByFirstNameAscLastNameAsc();
}
