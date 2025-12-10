package org.example.service;

import jakarta.transaction.Transactional;
import org.example.Enums.VendingStatus;
import org.example.authentication.DiagramInfo;
import org.example.db.VendingApparates;
import org.example.repositories.VendingApparatesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingApparatesService {
    private final VendingApparatesRepo repo;

    public VendingApparatesService(VendingApparatesRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public VendingApparates create(VendingApparates v){
        return repo.save(v);
    }

    public float getVendingApparatesNetworkEffectivnessPercent(){
        List<VendingApparates> list = repo.findAll();
        float working = 0;

        for(int i = 0; i < list.size(); ++i){
            if (list.get(i).getStatus() == VendingStatus.WORKING){
                ++working;
            }
        }

        return ((float) working / ((float) list.size() / 100)) ;
    }

    public DiagramInfo getDiagramInfo(){
        DiagramInfo inf = new DiagramInfo(0, 0, 0);
        List<VendingApparates> list = repo.findAll();

        for(int i = 0; i < list.size(); ++i){
            if(list.get(i).getStatus() == VendingStatus.WORKING)
            {
                inf.addWorking(1);
            }
            else if(list.get(i).getStatus() == VendingStatus.CLOSED)
            {
                inf.addClosed(1);
            }
            else if(list.get(i).getStatus() == VendingStatus.TECH_REMONT)
            {
                inf.addTech_remont(1);
            }
        }
        return inf;
    }

}
