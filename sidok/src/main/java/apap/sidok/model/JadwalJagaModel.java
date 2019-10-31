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
@Table(name = "jadwal_jaga")
public class JadwalJagaModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJadwalJaga;

    @NotNull
    @Column(name = "hari", nullable = false)
    private String hari;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idDokter", referencedColumnName = "idDokter")
    @OnDelete(action = OnDeleteAction.CASCADE)
    DokterModel dokter;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idPoli", referencedColumnName = "idPoli")
    @OnDelete(action = OnDeleteAction.CASCADE)
    PoliModel poli;

    public Long getIdJadwalJaga() {
        return idJadwalJaga;
    }

    public void setIdJadwalJaga(Long idJadwalJaga) {
        this.idJadwalJaga = idJadwalJaga;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public DokterModel getDokter() {
        return dokter;
    }

    public void setDokter(DokterModel dokter) {
        this.dokter = dokter;
    }

    public PoliModel getPoli() {
        return poli;
    }

    public void setPoli(PoliModel poli) {
        this.poli = poli;
    }
}
