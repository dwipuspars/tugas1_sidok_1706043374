package apap.sidok.service;

import apap.sidok.model.DokterModel;
import apap.sidok.model.PoliModel;
import apap.sidok.repository.DokterDb;
import apap.sidok.repository.PoliDb;
import apap.sidok.repository.SpesialisasiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PoliServiceImpl implements PoliService{
    @Autowired
    PoliDb poliDb;

    @Override
    public List<PoliModel> getPoliList(){
        return poliDb.findAll();
    }

    @Override
    public void tambahPoli(PoliModel poli) { poliDb.save(poli);}

    @Override
    public void deletePoli(PoliModel poli) { poliDb.delete(poli); }

    @Override
    public Optional<PoliModel> getPoliByIdPoli(Long idPoli) {
        return poliDb.findByIdPoli(idPoli);
    }

    @Override
    public PoliModel updatePoli(PoliModel poli) {
        PoliModel targetPoli = poliDb.findByIdPoli(poli.getIdPoli()).get();
        try {
            targetPoli.setNama(poli.getNama());
            targetPoli.setLokasi(poli.getLokasi());
            poliDb.save(targetPoli);
            return targetPoli;
        }catch (NullPointerException nullException){
            return null;
        }
    }


}
