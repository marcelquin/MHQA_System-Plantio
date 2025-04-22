package App.Infra.Persistence.Repository;

import App.Infra.Persistence.Entity.BlocoEntity;
import App.Infra.Persistence.Entity.LocalizacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlocoRepository extends JpaRepository<BlocoEntity,Long > {


    Optional<BlocoEntity> findByreferencia(String referencia);

}
