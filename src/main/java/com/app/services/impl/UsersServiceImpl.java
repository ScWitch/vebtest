package com.app.services.impl;

import com.app.dtos.OffersDto;
import com.app.dtos.UsersDto;
import com.app.models.Offers;
import com.app.models.Roles;
import com.app.models.Users;
import com.app.repositories.RolesRepository;
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

    private UsersRepository usersRepository;

    private RolesRepository rolesRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository){
        this.rolesRepository = rolesRepository;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public UsersDto registerUsers(UsersDto users) {
        Users user = modelMapper.map(users, Users.class);
        Roles role = rolesRepository.findById(users.getRole_id())
                .orElseThrow(() -> new IllegalArgumentException("Error"));
        user.setRole(role);
        Users savedUser = usersRepository.save(user);
        return modelMapper.map(savedUser, UsersDto.class);
    }

    @Override
    public void expelUsers(UsersDto users) {
        usersRepository.deleteById(users.getId());
    }

    @Override
    public void expelUsers(Integer id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Optional<UsersDto> findUsers(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<UsersDto> getAll() {
            List<Users> usersList = usersRepository.findAll();
            return usersList.stream()
                    .map(users -> modelMapper.map(users, UsersDto.class))
                    .collect(Collectors.toList());
        }
}
