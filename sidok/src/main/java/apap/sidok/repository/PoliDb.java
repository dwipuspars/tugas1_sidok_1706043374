package apap.sidok.repository;

import apap.sidok.model.PoliModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoliDb extends JpaRepository<PoliModel, Long> {
    Optional<PoliModel> findByIdPoli(Long idPoli);
}

