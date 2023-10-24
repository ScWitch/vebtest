package com.app.services.impl;

import com.app.dtos.BrandsDto;
import com.app.models.Brands;
import com.app.repositories.BrandsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.services.BrandsServices;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BrandsServiceImpl implements BrandsServices<Integer> {
    @Autowired
    private BrandsRepository brandsRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BrandsDto registerBrands(BrandsDto brands) {
        Brands newBrands = modelMapper.map(brands, Brands.class);
        return modelMapper.map(brandsRepository.save(newBrands), BrandsDto.class);
    }

    @Override
    public void expelBrands(BrandsDto brands) {
        brandsRepository.deleteById(brands.getId());
    }

    @Override
    public void expelBrands(Integer id) {
        brandsRepository.deleteById(id);
    }

    @Override
    public Optional<BrandsDto> findBrands(Integer id) {
        Optional<Brands> brandsOptional = brandsRepository.findById(id);
        return brandsOptional.map(brands -> modelMapper.map(brands, BrandsDto.class));
    }

    @Override
    public List<BrandsDto> getAll() {
        return brandsRepository.findAll().stream().map(brands -> modelMapper.map(brands, BrandsDto.class)).collect(Collectors.toList());
    }
}