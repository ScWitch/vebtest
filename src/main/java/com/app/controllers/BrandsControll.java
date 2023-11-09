package com.app.controllers;

import com.app.dtos.BrandsDto;
import com.app.services.BrandsServices;
import com.app.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandsControll {
    private BrandsServices<Integer> brandsService;

    @Autowired
    public void setRolesService(BrandsServices<Integer> brandsService) {
        this.brandsService = brandsService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerBrand(@RequestBody BrandsDto brandDto) {
        Date currentDate = new Date();
        brandDto.setCreated(currentDate);
        brandDto.setModified(currentDate);
        BrandsDto savedBrand = brandsService.registerBrands(brandDto);
        return ResponseEntity.ok("Brand registered with ID: " + savedBrand.getId());
    }



    @GetMapping("/{id}")
    public ResponseEntity<BrandsDto> getBrandsById(@PathVariable Integer id) {
        return brandsService.findBrands(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/home")
    public String home() {
        return "homePaege.html";
    }

    @GetMapping("/all")
    public ResponseEntity<List<BrandsDto>> getAllBrands() {
        List<BrandsDto> allBrands = brandsService.getAll();
        return ResponseEntity.ok(allBrands);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrands(@PathVariable Integer id) {
        brandsService.expelBrands(id);
        return ResponseEntity.noContent().build();
    }

}