package apap.sidok.service;

import apap.sidok.model.DokterModel;
import apap.sidok.repository.DokterDb;
import apap.sidok.repository.SpesialisasiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DokterServiceImpl implements DokterService {
    @Autowired
    private DokterDb dokterDb;

    @Override
    public void tambahDokter(DokterModel dokter) { dokterDb.save(dokter);}

    @Override
    public List<DokterModel> getDokterList() {
        return dokterDb.findAll();
    }

    @Override
    public Optional<DokterModel> getDokterByNikDokter(String nik){
        return dokterDb.findByNik(nik);
    }

    @Override
    public Optional<DokterModel> getDokterByIdDokter(Long idDokter) {
        return dokterDb.findByIdDokter(idDokter);
    }

    @Override
    public DokterModel updateDokter(DokterModel dokter){
        DokterModel targetDokter = dokterDb.findByIdDokter(dokter.getIdDokter()).get();

        try{
            targetDokter.setNama(dokter.getNama());
            targetDokter.setJenisKelamin(dokter.getJenisKelamin());
            targetDokter.setBirthdate(dokter.getBirthdate());
            targetDokter.setNip(dokter.getNip());
            targetDokter.setListSpesialisasi(dokter.getListSpesialisasi());
            dokterDb.save(targetDokter);
            return targetDokter;
        }catch (NullPointerException nullException){
            return null;
        }
    }
}
