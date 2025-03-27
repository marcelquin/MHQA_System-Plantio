package App.Infra.Persistence.Repository;

import App.Infra.Persistence.Entity.PlantaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantaRepository extends JpaRepository<PlantaEntity,Long> {


    Optional<PlantaEntity> findBycodigo(String codigo);
}
