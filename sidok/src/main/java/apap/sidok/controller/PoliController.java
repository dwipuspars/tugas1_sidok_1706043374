package apap.sidok.controller;

import apap.sidok.model.JadwalJagaModel;
import apap.sidok.model.PoliModel;
import apap.sidok.model.SpesialisasiModel;
import apap.sidok.service.PoliService;
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
public class PoliController {
    @Qualifier("poliServiceImpl")
    @Autowired
    private PoliService poliService;


    @RequestMapping("/poli")
    public String poliHome(Model model) {
        List<PoliModel> listPoli = poliService.getPoliList();

        model.addAttribute("poliList", listPoli);
        return "poli-home";
    }

    @RequestMapping(value = "/poli/tambah", method = RequestMethod.GET)
    private String tambahPoliFormPage(Model model){
        PoliModel newPoli = new PoliModel();
        model.addAttribute("poli", newPoli);
        return "form-tambah-poli";
    }

    @RequestMapping(value = "/poli/tambah", method = RequestMethod.POST)
    public String tambahPoliSubmit(@ModelAttribute PoliModel poli, Model model){
        poliService.tambahPoli(poli);
        model.addAttribute("namaPoli", poli.getNama());
        model.addAttribute("poli", poli);
        return "tambah-poli";
    }

    @RequestMapping("/poli/delete/{idPoli}")
    public String deletePoli(@PathVariable Long idPoli, Model model){
        PoliModel deletedPoli = poliService.getPoliByIdPoli(idPoli).get();
        model.addAttribute("poli", deletedPoli.getNama());
        poliService.deletePoli(deletedPoli);
        return "delete-poli";
    }

    @RequestMapping("/poli/dokter/{idPoli}")
    public String daftarDokter(@PathVariable Long idPoli, Model model){
        PoliModel poli = poliService.getPoliByIdPoli(idPoli).get();
        List<JadwalJagaModel> listJadwalJaga = poli.getListJadwalJagaPoli();
        model.addAttribute("poli", poli);
        model.addAttribute("listJadwalJaga", listJadwalJaga);
        return "detail-poli";
    }

    @RequestMapping("/poli/detail/{idPoli}")
    public String detailPoli(@PathVariable Long idPoli, Model model){
        PoliModel poli = poliService.getPoliByIdPoli(idPoli).get();
        List<JadwalJagaModel> listJadwalJaga = poli.getListJadwalJagaPoli();
        model.addAttribute("poli", poli);
        model.addAttribute("listJadwalJaga", listJadwalJaga);
        return "detail-poli";
    }

    @RequestMapping(value = "poli/update/{idPoli}", method = RequestMethod.GET)
    public String updatePoliFormPage(@PathVariable Long idPoli, Model model){
        PoliModel existingPoli = poliService.getPoliByIdPoli(idPoli).get();
        model.addAttribute("poli", existingPoli);
        return "form-update-poli";
    }

    @RequestMapping(value = "poli/update/{idPoli}", method = RequestMethod.POST)
    public String updatePoliFormSubmit(@PathVariable Long idPoli, @ModelAttribute PoliModel poli, Model model){
        PoliModel newPoliData = poliService.updatePoli(poli);
        model.addAttribute("poli", newPoliData);
        return "update-poli";
    }
}
