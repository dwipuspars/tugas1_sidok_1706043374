package apap.sidok.repository;

import apap.sidok.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long> {
    Optional<DokterModel> findByNik(String nik);
    Optional<DokterModel> findByIdDokter(Long idDokter);
    Optional<DokterModel> findByNip(String nip);
}
