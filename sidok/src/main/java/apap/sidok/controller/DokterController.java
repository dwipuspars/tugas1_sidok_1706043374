package apap.sidok.controller;

import apap.sidok.model.SpesialisasiModel;
import apap.sidok.service.SpesialisasiService;
import apap.sidok.model.DokterModel;
import apap.sidok.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

//    @RequestMapping(path = "/dokter/detail", method = RequestMethod.GET)
//    public String detail(
//            @RequestParam(value = "nik") String nik, Model model
//    ){
//        DokterModel dokter = dokterService.getDokterByNikDokter(nik).get();
//
//        //Add model restoran ke "resto" untuk dirender
//        model.addAttribute("dokter", dokter);
//
//
//        //Return view template
//        return "detail-dokter";
//    }

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
        if(newDokterData.getBirthdate() == dokter.getBirthdate()){
            String newNip = "";

            String[] tahunIni = LocalDate.now().toString().split("-");
            int tahunIniTambahLima = Integer.parseInt(tahunIni[0]) + 5;
            newNip += tahunIniTambahLima;

            String[] tanggalLahir = dokter.getBirthdate().toString().split("-");
            newNip += tanggalLahir[2] + tanggalLahir [1] + tanggalLahir[0];

            int kelamin = 0;
            if(dokter.getJenisKelamin() == 1){ kelamin += 1; }
            else{ kelamin += 2;}
            newNip += kelamin;

            Random hurufRandom = new Random();
            char random1 = (char)(hurufRandom.nextInt(26) + 'a');
            char random2 = (char)(hurufRandom.nextInt(26) + 'a');
            newNip += random1;
            newNip += random2;
            dokter.setNip(newNip);
        }

        model.addAttribute("dokter", newDokterData);

        return "update-dokter";
    }
}
