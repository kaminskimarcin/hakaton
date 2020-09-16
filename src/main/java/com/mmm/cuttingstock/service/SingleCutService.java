package com.mmm.cuttingstock.service;

import com.mmm.cuttingstock.repository.SingleCutRepository;
import org.springframework.stereotype.Service;

@Service
public class SingleCutService {

    private SingleCutRepository repository;

    public SingleCutService(SingleCutRepository repository){
        this.repository = repository;
    }

    public boolean checkedSingleCut(final boolean isChecked, final int id){
        var singleCutOpt = repository.findById(id);

        if(singleCutOpt.isPresent()) {
            var singleCut = singleCutOpt.get();
            singleCut.setChecked(isChecked);
            singleCut = repository.save(singleCut);

            return singleCut.isChecked() == isChecked;
        }

        return false;
    }

}
