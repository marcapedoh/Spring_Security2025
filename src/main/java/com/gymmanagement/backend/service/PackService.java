package com.gymmanagement.backend.service;


import com.gymmanagement.backend.model.Pack;
import com.gymmanagement.backend.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PackService {

    @Autowired
    private PackRepository packRepository;

    public Pack addPack(Pack pack) {
        return packRepository.save(pack);
    }

    public List<Pack> getAllPacks() {
        return packRepository.findAll();
    }

    public Optional<Pack> getPackById(Long id) {
        return packRepository.findById(id);
    }

    public Pack updatePack(Long id, Pack packDetails) {
        Pack pack = packRepository.findById(id).orElseThrow(() -> new RuntimeException("Pack not found"));
        pack.setOfferName(packDetails.getOfferName());
        pack.setDurationMonths(packDetails.getDurationMonths());
        pack.setMonthlyPrice(packDetails.getMonthlyPrice());
        return packRepository.save(pack);
    }

    public void deletePack(Long id) {
        Pack pack = packRepository.findById(id).orElseThrow(() -> new RuntimeException("Pack not found"));
        packRepository.delete(pack);
    }
}