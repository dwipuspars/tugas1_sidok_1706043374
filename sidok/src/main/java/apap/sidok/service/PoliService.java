package apap.sidok.service;

import apap.sidok.model.PoliModel;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;

import java.util.List;
import java.util.Optional;

public interface PoliService {
    List<PoliModel> getPoliList();
    void tambahPoli(PoliModel poli);
    void deletePoli(PoliModel poli);
    Optional<PoliModel> getPoliByIdPoli(Long idPoli);
    PoliModel updatePoli(PoliModel poli);
}
