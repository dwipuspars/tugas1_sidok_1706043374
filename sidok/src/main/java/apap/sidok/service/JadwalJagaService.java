package apap.sidok.service;

import apap.sidok.model.JadwalJagaModel;

import java.util.List;

public interface JadwalJagaService {
    void tambahJadwal(JadwalJagaModel jadwal);
    List<JadwalJagaModel> getJadwalJagaList();
}
