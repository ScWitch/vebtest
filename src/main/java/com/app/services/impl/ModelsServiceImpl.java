package com.app.services.impl;

import com.app.dtos.ModelsDto;
import com.app.models.Brands;
import com.app.models.Models;
import com.app.repositories.BrandsRepository;
import com.app.repositories.ModelsRepository;
import com.app.repositories.RolesRepository;
import org.springframework.stereotype.Service;
import com.app.services.ModelsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelsServiceImpl implements ModelsService<Integer> {

    private ModelsRepository modelsRepository;

    private BrandsRepository brandsRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelsRepository(ModelsRepository modelsRepository){
        this.modelsRepository = modelsRepository;
    }
    @Autowired
    public void setBrandsRepository(BrandsRepository brandsRepository){
        this.brandsRepository = brandsRepository;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelsDto registerModels(ModelsDto models) {
        Models newModels = modelMapper.map(models, Models.class);
        Brands brand = brandsRepository.findById(models.getBrand_id())
                .orElseThrow(() -> new IllegalArgumentException("Error"));
        newModels.setBrand(brand);
        newModels.setBrand_id(brand.getId());
        return modelMapper.map(modelsRepository.save(newModels), ModelsDto.class);
    }

    @Override
    public void expelModels(ModelsDto models) {
        modelsRepository.deleteById(models.getId());
    }

    @Override
    public void expelModels(Integer id) {
        modelsRepository.deleteById(id);
    }

    @Override
    public Optional<ModelsDto> findModels(Integer id) {
        Optional<Models> modelsOptional = modelsRepository.findById(id);
        return modelsOptional.map(models -> modelMapper.map(models, ModelsDto.class));
    }

    @Override
    public List<ModelsDto> getAll() {
        return modelsRepository.findAll().stream().map(models -> modelMapper.map(models, ModelsDto.class)).collect(Collectors.toList());
    }
}