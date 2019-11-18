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
@Table(name="spesialisasi")
public class SpesialisasiModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpesialisasi;

    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "gelar", nullable = false)
    private String gelar;

    @ManyToMany(mappedBy = "listSpesialisasi")
    private List<DokterModel> listDokter;

    public Long getIdSpesialisasi() {
        return idSpesialisasi;
    }

    public void setIdSpesialisasi(Long idSpesialisasi) {
        this.idSpesialisasi = idSpesialisasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public List<DokterModel> getListDokter() {
        return listDokter;
    }

    public void setListDokter(List<DokterModel> listDokter) {
        this.listDokter = listDokter;
    }
}
