package apap.sidok.repository;

import apap.sidok.model.SpesialisasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface SpesialisasiDb extends JpaRepository<SpesialisasiModel, Long> {

}
