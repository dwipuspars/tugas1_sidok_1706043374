package apap.sidok.repository;

import apap.sidok.model.JadwalJagaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long> {
}

