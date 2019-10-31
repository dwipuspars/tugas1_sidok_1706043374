package apap.sidok.model;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.namespace.QName;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import apap.sidok.model.DokterModel;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="poli")
public class PoliModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoli;

    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "lokasi", nullable = false)
    private String lokasi;

    @OneToMany(mappedBy = "poli", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JadwalJagaModel> listJadwalJagaPoli;

    public Long getIdPoli() {
        return idPoli;
    }

    public void setIdPoli(Long idPoli) {
        this.idPoli = idPoli;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public List<JadwalJagaModel> getListJadwalJagaPoli() {
        return listJadwalJagaPoli;
    }

    public void setListJadwalJagaPoli(List<JadwalJagaModel> listJadwalJagaPoli) {
        this.listJadwalJagaPoli = listJadwalJagaPoli;
    }
}
