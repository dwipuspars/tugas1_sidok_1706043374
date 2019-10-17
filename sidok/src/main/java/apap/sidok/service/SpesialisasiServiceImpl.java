package apap.sidok.service;

import apap.sidok.model.SpesialisasiModel;
import apap.sidok.repository.SpesialisasiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpesialisasiServiceImpl implements SpesialisasiService{
    @Autowired
    SpesialisasiDb spesialisasiDb;

    @Override
    public List<SpesialisasiModel> getSpesialisasiList() {
        return spesialisasiDb.findAll();
    }
}
