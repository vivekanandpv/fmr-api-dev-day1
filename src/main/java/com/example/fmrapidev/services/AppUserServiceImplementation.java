package com.example.fmrapidev.services;

import com.example.fmrapidev.exceptions.RecordNotFoundException;
import com.example.fmrapidev.models.AppUser;
import com.example.fmrapidev.repositories.AppRoleRepository;
import com.example.fmrapidev.repositories.AppUserRepository;
import com.example.fmrapidev.viewmodels.UserRegisterViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

public class AppUserServiceImplementation implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImplementation(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser register(UserRegisterViewModel viewModel) {
        AppUser entity = new AppUser();
        BeanUtils.copyProperties(viewModel, entity, "password");

        entity.setAppRoles(
                viewModel
                        .getRoles()
                        .stream()
                        .map(r -> this.appRoleRepository.findByRole(r).orElseThrow(RecordNotFoundException::new))
                        .collect(Collectors.toSet())
        );

        entity.setPassword(this.passwordEncoder.encode(viewModel.getPassword()));

        return appUserRepository.saveAndFlush(entity);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findUserByUsername(username)
                .orElseThrow(RecordNotFoundException::new);
    }
}
