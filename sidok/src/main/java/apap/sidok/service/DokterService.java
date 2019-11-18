package apap.sidok.service;

import apap.sidok.model.DokterModel;

import java.util.List;
import java.util.Optional;

public interface DokterService {
    void tambahDokter(DokterModel dokter);
    List<DokterModel> getDokterList();
    Optional<DokterModel> getDokterByNikDokter(String nik);
    Optional<DokterModel> getDokterByIdDokter(Long idDokter);
    DokterModel updateDokter(DokterModel dokter);
}
