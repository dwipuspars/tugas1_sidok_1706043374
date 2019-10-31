package apap.sidok.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.namespace.QName;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name="dokter")
public class DokterModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokter;

    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "nik", nullable = false)
    private String nik;

    @NotNull
    @Size(max=255)
    @Column(name = "nip", nullable = false)
    private String nip;

    @NotNull
    @Column(name = "jenisKelamin", nullable = false)
    private int jenisKelamin;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggalLahir", nullable = false)
    private LocalDate birthdate;

    @NotNull
    @Column(name = "tempatLahir", nullable = false)
    private String tempatLahir;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JadwalJagaModel> listJadwalJagaDokter;

    @ManyToMany
    @JoinTable(name = "spesialisasiDokter", joinColumns = @JoinColumn(name = "idDokter"), inverseJoinColumns = @JoinColumn(name = "idSpesialisasi"))
    private List<SpesialisasiModel> listSpesialisasi;

    public Long getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(Long idDokter) {
        this.idDokter = idDokter;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip){
        this.nip = nip;
    }

    public int getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(int jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public List<JadwalJagaModel> getListJadwalJagaDokter() {
        return listJadwalJagaDokter;
    }

    public void setListJadwalJagaDokter(List<JadwalJagaModel> listJadwalJagaDokter) {
        this.listJadwalJagaDokter = listJadwalJagaDokter;
    }

    public List<SpesialisasiModel> getListSpesialisasi() {
        return listSpesialisasi;
    }

    public void setListSpesialisasi(List<SpesialisasiModel> listSpesialisasi) {
        this.listSpesialisasi = listSpesialisasi;
    }
}
