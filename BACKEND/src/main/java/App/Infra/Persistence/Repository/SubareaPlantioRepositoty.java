package App.Infra.Persistence.Repository;

import App.Infra.Persistence.Entity.SubAreaPlantioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubareaPlantioRepositoty extends JpaRepository<SubAreaPlantioEntity,Long> {

    Optional<SubAreaPlantioEntity> findBycodigo(String codigo);

    boolean existsBycodigo(String codigo);
}
