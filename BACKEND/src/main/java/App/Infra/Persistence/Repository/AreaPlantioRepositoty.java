package App.Infra.Persistence.Repository;

import App.Infra.Persistence.Entity.AreaPlantioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaPlantioRepositoty extends JpaRepository<AreaPlantioEntity,Long> {

    Optional<AreaPlantioEntity> findBynomeIdentificador(String nomeIdentificador);


}
