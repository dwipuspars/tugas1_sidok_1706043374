package apap.sidok.service;

import apap.sidok.model.DokterModel;
import apap.sidok.model.PoliModel;

import java.util.List;
import java.util.Optional;

public interface DokterService {
    void tambahDokter(DokterModel dokter);
    List<DokterModel> getDokterList();
    Optional<DokterModel> getDokterByNikDokter(String nik);
    Optional<DokterModel> getDokterByIdDokter(Long idDokter);
    Optional<DokterModel> getDokterByNipDokter(String nip);
    DokterModel updateDokter(DokterModel dokter);
    void deleteDokter(DokterModel dokter);
    List<DokterModel> getDokterTerbanyakDiPoli(PoliModel poli);
}
