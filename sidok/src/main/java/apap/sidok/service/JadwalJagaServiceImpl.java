package apap.sidok.service;

import apap.sidok.model.JadwalJagaModel;
import apap.sidok.repository.JadwalJagaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService{
    @Autowired
    private JadwalJagaDb jadwalJagaDb;

    @Override
    public void tambahJadwal(JadwalJagaModel jadwal) { jadwalJagaDb.save(jadwal); }

    @Override
    public List<JadwalJagaModel> getJadwalJagaList() { return jadwalJagaDb.findAll(); }
}
