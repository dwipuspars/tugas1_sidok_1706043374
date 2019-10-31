package apap.sidok.controller;

import apap.sidok.model.JadwalJagaModel;
import apap.sidok.model.DokterModel;
import apap.sidok.model.PoliModel;
import apap.sidok.service.JadwalJagaService;
import apap.sidok.service.SpesialisasiService;
import apap.sidok.service.DokterService;
import apap.sidok.service.PoliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
public class JadwalController {
    @Qualifier("jadwalJagaServiceImpl")
    @Autowired
    private JadwalJagaService jadwalJagaService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("poliServiceImpl")
    @Autowired
    private PoliService poliService;

    @RequestMapping(path = "/jadwal/tambah/{nip}", method = RequestMethod.GET)
    private String tambahJadwalFormPage(@PathVariable String nip, Model model){
        JadwalJagaModel newJadwal = new JadwalJagaModel();
        DokterModel dokter = dokterService.getDokterByNipDokter(nip).get();
        List<PoliModel> poliList = poliService.getPoliList();
        model.addAttribute("dokter", dokter);
        model.addAttribute("jadwal", newJadwal);
        model.addAttribute("listPoli", poliList);
        model.addAttribute("listJadwalJaga", dokter.getListJadwalJagaDokter());

        return "form-tambah-jadwal";
    }

    @RequestMapping(path = "/jadwal/tambah/{nip}", method = RequestMethod.POST)
    public String tambahJadwalSubmit(@PathVariable String nip, @ModelAttribute JadwalJagaModel jadwal, Model model){
        DokterModel dokter = dokterService.getDokterByNipDokter(nip).get();
        jadwal.setDokter(dokter);
        jadwalJagaService.tambahJadwal(jadwal);
        model.addAttribute("namaDokter", jadwal.getDokter().getNama());
        model.addAttribute("namaPoli", jadwal.getPoli().getNama());
        model.addAttribute("hariJadwal", jadwal.getHari());
        return "tambah-jadwal";
    }
}
