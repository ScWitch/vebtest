package com.app.services.impl;

import com.app.dtos.OffersDto;
import com.app.models.Models;
import com.app.models.Offers;
import com.app.models.Users;
import com.app.repositories.OffersRepository;
import com.app.services.OffersService;
import com.app.repositories.ModelsRepository;
import com.app.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OffersService<Integer> {
    private final OffersRepository offersRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ModelsRepository modelsRepository;

    @Autowired
    public OfferServiceImpl(OffersRepository offersRepository, ModelMapper modelMapper) {
        this.offersRepository = offersRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OffersDto registerOffers(OffersDto offersDto) {
        Offers offer = modelMapper.map(offersDto, Offers.class);
        Users seller = usersRepository.findById(offersDto.getSeller_id())
                .orElseThrow(() -> new IllegalArgumentException("Error"));
        Models model = modelsRepository.findById(offersDto.getModel_id())
                .orElseThrow(() -> new IllegalArgumentException("Error"));
        offer.setSeller(seller);
        offer.setModel(model);
        Offers savedOffer = offersRepository.save(offer);
        return modelMapper.map(savedOffer, OffersDto.class);
    }

    @Override
    public void expelOffers(OffersDto offersDto) {
        offersRepository.deleteById(offersDto.getId());
    }

    @Override
    public void expelOffers(Integer id) {
        offersRepository.deleteById(id);
    }

    @Override
    public Optional<OffersDto> findOffers(Integer id) {
        return offersRepository.findById(id).map(offers -> modelMapper.map(offers, OffersDto.class));
    }

    @Override
    public List<OffersDto> getAll() {
        List<Offers> offersList = offersRepository.findAll();
        return offersList.stream()
                .map(offers -> modelMapper.map(offers, OffersDto.class))
                .collect(Collectors.toList());
    }
}
