package apap.sidok.service;

import apap.sidok.model.DokterModel;
import apap.sidok.model.JadwalJagaModel;
import apap.sidok.model.PoliModel;
import apap.sidok.repository.DokterDb;
import apap.sidok.repository.SpesialisasiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

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
    public Optional<DokterModel> getDokterByNipDokter(String nip) { return dokterDb.findByNip(nip); }

    @Override
    public DokterModel updateDokter(DokterModel dokter){
        DokterModel targetDokter = dokterDb.findByIdDokter(dokter.getIdDokter()).get();
        try{
            targetDokter.setNama(dokter.getNama());
            targetDokter.setJenisKelamin(dokter.getJenisKelamin());
            targetDokter.setBirthdate(dokter.getBirthdate());
            targetDokter.setListSpesialisasi(dokter.getListSpesialisasi());
            String newNip = "";

            String[] tahunIni = LocalDate.now().toString().split("-");
            int tahunIniTambahLima = Integer.parseInt(tahunIni[0]) + 5;
            newNip += tahunIniTambahLima;

            String[] tanggalLahir = dokter.getBirthdate().toString().split("-");
            newNip += tanggalLahir[2] + tanggalLahir [1] + tanggalLahir[0];

            int kelamin = 0;
            if(targetDokter.getJenisKelamin() == 1){ kelamin += 1; }
            else{ kelamin += 2;}
            newNip += kelamin;

            Random hurufRandom = new Random();
            char random1 = (char)(hurufRandom.nextInt(26) + 'a');
            char random2 = (char)(hurufRandom.nextInt(26) + 'a');
            newNip += random1;
            newNip += random2;
            targetDokter.setNip(newNip);

            dokterDb.save(targetDokter);
            return targetDokter;
        }catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deleteDokter(DokterModel dokter) { dokterDb.delete(dokter); }

    @Override
    public List<DokterModel> getDokterTerbanyakDiPoli(PoliModel poli) {
        List<JadwalJagaModel> listJadwal = poli.getListJadwalJagaPoli();

        List<DokterModel> listDokter = new ArrayList<>();

        //masukin dokter2 dari jadwal jaga tsb ke listnya

        for(JadwalJagaModel jadwal : listJadwal){
            DokterModel dokter = jadwal.getDokter();
            listDokter.add(dokter);
        }
        try {
            //bikin hashmap
            Map<DokterModel, Integer> mapDokter = new HashMap<>();
            //looping sebanyak list dokter
            for (DokterModel dokter : listDokter) {
                Integer jumlah = mapDokter.get(dokter);
                mapDokter.put(dokter, jumlah == null ? 1 : jumlah + 1);
            }

            //cari yang paling maksimum
            //map.entry adalah isi dari mapp, entryset itu set of entries
            Map.Entry<DokterModel, Integer> termax = null;
            for (Map.Entry<DokterModel, Integer> currentMax : mapDokter.entrySet()) {
                if (termax == null || termax.getValue() < currentMax.getValue()) {
                    termax = currentMax;
                }
            }
            //bikin list dokter yg polinya maksimum, can be more than 1 kan
            List<DokterModel> listDokterDokter = new ArrayList<>();
            //nge looop dari semua mapdokter yg ada
            for (Map.Entry<DokterModel, Integer> isi : mapDokter.entrySet()) {
                if (isi.getValue() == termax.getValue()) {
                    listDokterDokter.add(isi.getKey());
                }
            }
            return listDokterDokter;
        }
        catch (NullPointerException e){
            return null;
        }
    }
}
