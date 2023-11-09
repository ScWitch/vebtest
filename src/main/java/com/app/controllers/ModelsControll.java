package com.app.controllers;

import com.app.dtos.ModelsDto;
import com.app.services.ModelsService;
import com.app.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelsControll {
    private ModelsService<Integer> modelsService;

    @Autowired
    public void setModelsService(ModelsService<Integer> modelsService) {
        this.modelsService = modelsService;
    }

    @PostMapping("/register")
    public ResponseEntity<ModelsDto> registerModels(@RequestBody ModelsDto modelsDto) {
        ModelsDto registeredModels = modelsService.registerModels(modelsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelsDto> getModelsById(@PathVariable Integer id) {
        return modelsService.findModels(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<ModelsDto>>getAllModels() {
        List<ModelsDto> allModels = modelsService.getAll();
        return ResponseEntity.ok(allModels);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModels(@PathVariable Integer id) {
        modelsService.expelModels(id);
        return ResponseEntity.noContent().build();
    }
}