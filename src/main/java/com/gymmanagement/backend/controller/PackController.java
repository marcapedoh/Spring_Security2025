package com.gymmanagement.backend.controller;


import com.gymmanagement.backend.model.Pack;
import com.gymmanagement.backend.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/packs")
public class PackController {

    @Autowired
    private PackService packService;

    @PostMapping
    public ResponseEntity<Pack> addPack(@RequestBody Pack pack) {
        Pack createdPack = packService.addPack(pack);
        return ResponseEntity.ok(createdPack);
    }

    @GetMapping
    public ResponseEntity<List<Pack>> getAllPacks() {
        List<Pack> packs = packService.getAllPacks();
        return ResponseEntity.ok(packs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pack> getPackById(@PathVariable Long id) {
        Optional<Pack> pack = packService.getPackById(id);
        return ResponseEntity.ok(pack.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pack> updatePack(@PathVariable Long id, @RequestBody Pack pack) {
        Pack updatedPack = packService.updatePack(id, pack);
        return ResponseEntity.ok(updatedPack);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePack(@PathVariable Long id) {
        packService.deletePack(id);
        return ResponseEntity.noContent().build();
    }
}