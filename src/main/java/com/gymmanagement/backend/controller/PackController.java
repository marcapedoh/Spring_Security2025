package com.gymmanagement.backend.controller;


import com.gymmanagement.backend.model.Pack;
import com.gymmanagement.backend.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.gymmanagement.backend.constant.Utils.APP_ROOT;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(APP_ROOT+"packs")
public class PackController {

    @Autowired
    private PackService packService;

    @PostMapping("/create")
    public ResponseEntity<Pack> addPack(@RequestBody Pack pack) {
        Pack createdPack = packService.addPack(pack);
        return ResponseEntity.ok(createdPack);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pack>> getAllPacks() {
        List<Pack> packs = packService.getAllPacks();
        return ResponseEntity.ok(packs);
    }

    @GetMapping("/packById/{id}")
    public ResponseEntity<Pack> getPackById(@PathVariable Long id) {
        Optional<Pack> pack = packService.getPackById(id);
        return ResponseEntity.ok(pack.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pack> updatePack(@PathVariable Long id, @RequestBody Pack pack) {
        Pack updatedPack = packService.updatePack(id, pack);
        return ResponseEntity.ok(updatedPack);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePack(@PathVariable Long id) {
        packService.deletePack(id);
        return ResponseEntity.noContent().build();
    }
}