package service;

import dto.VehicleDto;
import entity.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private AccountRepository accountRepository;

	//for response(get) mapping entity to dto
	public Optional<VehicleDto> findById(Integer id){
		return vehicleRepository.findById(id)
				.map(vehicleEntity -> {
					VehicleDto vehicleDto = new VehicleDto();
					vehicleDto.setPlate(vehicleEntity.getPlate());
					vehicleDto.setActive(vehicleEntity.isActive());
					if (vehicleEntity.getAccountEntity() != null) {
						vehicleDto.setAccountId(vehicleEntity.getAccountEntity().getId());
					}
					return vehicleDto;
				});
	}

	//for response(get) mapping entity to dto
	public List<VehicleDto> findAll(){
		return vehicleRepository.findAll()
				.stream()
				.map(vehicleEntity -> {
					VehicleDto vehicleDto = new VehicleDto();
					vehicleDto.setPlate(vehicleEntity.getPlate());
					vehicleDto.setActive(vehicleEntity.isActive());
					if (vehicleEntity.getAccountEntity() != null) {
						vehicleDto.setAccountId(vehicleEntity.getAccountEntity().getId());
					}
					return vehicleDto;
				})
				.toList();
	}

	//for request(post) mapping dto to entity
	public void save(VehicleDto vehicleDto){
		VehicleEntity vehicleEntity = new VehicleEntity();
		vehicleEntity.setPlate(vehicleDto.getPlate());
		vehicleEntity.setActive(vehicleDto.isActive());
		if (vehicleDto.getAccountId() != null){
			accountRepository.findById(vehicleDto.getAccountId())
					.ifPresent(vehicleEntity::setAccountEntity);
		}
		vehicleRepository.save(vehicleEntity);
	}

	public void delete(Integer id){
		vehicleRepository.deleteById(id);
	}
}
