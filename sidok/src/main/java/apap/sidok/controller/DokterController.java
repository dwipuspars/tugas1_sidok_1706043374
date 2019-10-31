package apap.sidok.controller;

import apap.sidok.model.JadwalJagaModel;
import apap.sidok.model.PoliModel;
import apap.sidok.model.SpesialisasiModel;
import apap.sidok.service.JadwalJagaService;
import apap.sidok.service.PoliService;
import apap.sidok.service.SpesialisasiService;
import apap.sidok.model.DokterModel;
import apap.sidok.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class DokterController {
    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("spesialisasiServiceImpl")
    @Autowired
    private SpesialisasiService spesialisasiService;

    @Qualifier("jadwalJagaServiceImpl")
    @Autowired
    private JadwalJagaService jadwalJagaService;

    @Qualifier("poliServiceImpl")
    @Autowired
    private PoliService poliService;

    @RequestMapping("/")
    public String home(Model model) {
        //Mengambl semua objek RestoranModel yang ada
        List<DokterModel> listDokter = dokterService.getDokterList();
        //Add model restoran ke "resto" untuk dirender
        model.addAttribute("dokterList", listDokter);
        return "home";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.GET)
    private String tambahDokterFormPage(Model model){
        DokterModel newDokter = new DokterModel();
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getSpesialisasiList();
        model.addAttribute("dokter", newDokter);
        model.addAttribute("spesialisasiList", listSpesialisasi);
        return "form-tambah-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST)
    public String tambahDokterSubmit(@ModelAttribute DokterModel dokter, Model model){

        String nip = "";

        String[] tahunIni = LocalDate.now().toString().split("-");
        int tahunIniTambahLima = Integer.parseInt(tahunIni[0]) + 5;
        nip += tahunIniTambahLima;

        String[] tanggalLahir = dokter.getBirthdate().toString().split("-");
        nip += tanggalLahir[2] + tanggalLahir [1] + tanggalLahir[0];

        int kelamin = 0;
        if(dokter.getJenisKelamin() == 1){ kelamin += 1; }
        else{ kelamin += 2;}
        nip += kelamin;

        Random hurufRandom = new Random();
        char random1 = (char)(hurufRandom.nextInt(26) + 'a');
        char random2 = (char)(hurufRandom.nextInt(26) + 'a');
        nip += random1;
        nip += random2;
        dokter.setNip(nip);
        dokterService.tambahDokter(dokter);
        model.addAttribute("namaDokter", dokter.getNama());
        model.addAttribute("nipDokter", dokter.getNip());
        return "tambah-dokter";
    }

    @RequestMapping(value="/dokter/tambah", method = RequestMethod.POST, params = {"addRow"})
    public String addRow(@ModelAttribute DokterModel dokter, BindingResult bindingResult, final HttpServletRequest req, Model model){
        if(dokter.getListSpesialisasi() == null){
            dokter.setListSpesialisasi(new ArrayList<SpesialisasiModel>());
        }
        dokter.getListSpesialisasi().add(new SpesialisasiModel());
        model.addAttribute("dokter", dokter);
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getSpesialisasiList();
        model.addAttribute("spesialisasiList", listSpesialisasi);
        return "form-tambah-dokter";

    }

    @RequestMapping(path = "/dokter/detail/{nikDokter}", method = RequestMethod.GET)
    public String detail(@PathVariable String nikDokter, Model model){
            DokterModel dokter = dokterService.getDokterByNikDokter(nikDokter).get();

            //Add model restoran ke "resto" untuk dirender
            model.addAttribute("dokter", dokter);


            //Return view template
            return "detail-dokter";
    }

    @RequestMapping(value = "dokter/update/{idDokter}", method = RequestMethod.GET)
    public String updateDokterFormPage(@PathVariable Long idDokter, Model model){
        DokterModel existingDokter = dokterService.getDokterByIdDokter(idDokter).get();
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getSpesialisasiList();
        model.addAttribute("dokter", existingDokter);
        model.addAttribute("spesialisasiList", listSpesialisasi);
        return "form-update-dokter";
    }

    @RequestMapping(value = "dokter/update/{idDokter}", method = RequestMethod.POST)
    public String updateDokterFormSubmit(@PathVariable Long idDokter, @ModelAttribute DokterModel dokter, Model model){
        DokterModel newDokterData = dokterService.updateDokter(dokter);
        model.addAttribute("dokter", newDokterData);
        return "update-dokter";
    }

    @RequestMapping("/dokter/delete/{idDokter}")
    public String deleteDokter(@PathVariable Long idDokter, Model model){
        DokterModel deletedDokter = dokterService.getDokterByIdDokter(idDokter).get();
        model.addAttribute("dokter", deletedDokter.getNama());
        dokterService.deleteDokter(deletedDokter);
        return "delete-dokter";
    }

    @RequestMapping(value = "/cari", method = RequestMethod.GET)
    private String cariSpesialisasiPadaPoliGet(@ModelAttribute DokterModel dokter, Model model){
        model.addAttribute("listPoli", poliService.getPoliList());
        model.addAttribute("listSpesialisasi", spesialisasiService.getSpesialisasiList());
        model.addAttribute("dokter", dokter);
        return "cari-dokter-spesialisasi-poli";
    }

    @RequestMapping(value = "/cari", method = RequestMethod.POST)
    private String cariSpesialisasiPadaPoliResult(
            @RequestParam (value = "idSpesialisasi") Long idSpesialisasi,
            @RequestParam(value = "idPoli") Long idPoli,
            @ModelAttribute DokterModel dokter,
            Model model){
        PoliModel poli = poliService.getPoliByIdPoli(idPoli).get();
        SpesialisasiModel spesialisasi = spesialisasiService.getSpesialisasiByIdSpesialisasi(idSpesialisasi).get();
        List<DokterModel> listDokter = new ArrayList<>();

        List<JadwalJagaModel> listJadwalJaga = poli.getListJadwalJagaPoli();
        List<DokterModel> listDokterSpesialisasi = spesialisasi.getListDokter();

        for (JadwalJagaModel jadwalJaga: listJadwalJaga){
            for(DokterModel dokterSpesialisasi: listDokterSpesialisasi){
                if(jadwalJaga.getDokter().getIdDokter() == dokterSpesialisasi.getIdDokter()){
                    listDokter.add(dokterSpesialisasi);
                }
            }
        }
        model.addAttribute("listPoli", poliService.getPoliList());
        model.addAttribute("listSpesialisasi", spesialisasiService.getSpesialisasiList());
        model.addAttribute("listDokter", listDokter);
        model.addAttribute("dokter", dokter);

        return "cari-dokter-spesialisasi-poli-result";
    }


    @RequestMapping(value = "/dokter/cari/tugas-terbanyak", method = RequestMethod.GET)
    private String cariDokterTerbanyakPost(@ModelAttribute DokterModel dokter, Model model){
        model.addAttribute("listPoli", poliService.getPoliList());

        return "cari-dokter-tugas-terbanyak";
    }

    @RequestMapping(value = "/dokter/cari/tugas-terbanyak", method = RequestMethod.POST)
    private String cariDokterTerbanyakPost(
            @RequestParam(value = "idPoli") Long idPoli,
            @ModelAttribute DokterModel dokter,
            Model model
    ){
        PoliModel poli = poliService.getPoliByIdPoli(idPoli).get();
        List<DokterModel> dokterTerbanyak = dokterService.getDokterTerbanyakDiPoli(poli);
        List<PoliModel> listPoli = poliService.getPoliList();

        model.addAttribute("listPoli", listPoli);
        model.addAttribute("dokterTerbanyak", dokterTerbanyak);

        return "cari-dokter-tugas-terbanyak-result";
    }
}
