package apap.sidok.service;

import apap.sidok.model.SpesialisasiModel;
import java.util.List;
import java.util.Optional;

public interface SpesialisasiService {
    List<SpesialisasiModel> getSpesialisasiList();
    Optional<SpesialisasiModel> getSpesialisasiByIdSpesialisasi(Long idSpesialisasi);
}
