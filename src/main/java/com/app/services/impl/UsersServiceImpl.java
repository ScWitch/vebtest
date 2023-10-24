package com.app.services.impl;

import com.app.dtos.UsersDto;
import com.app.models.Users;
import com.app.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.services.UsersService;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UsersServiceImpl implements UsersService<Integer> {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UsersDto registerUsers(UsersDto users) {
        Users user = modelMapper.map(users, Users.class);
        return modelMapper.map(usersRepository.save(user), UsersDto.class);
    }

    @Override
    public void expelUsers(UsersDto users) {
        usersRepository.deleteAllById(Collections.singleton(users.getId()));

    }

    @Override
    public void expelUsers(Integer id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Optional<UsersDto> findUsers(Integer id) {
        Optional<Users> modelsOptional = usersRepository.findById(id);
        return modelsOptional.map(users -> modelMapper.map(users, UsersDto.class));
    }

    @Override
    public List<UsersDto> getAll() {
        return usersRepository.findAll().stream().map(users -> modelMapper.map(users, UsersDto.class)).collect(Collectors.toList());
    }
}
