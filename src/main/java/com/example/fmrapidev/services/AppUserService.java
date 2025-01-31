package com.example.fmrapidev.services;

import com.example.fmrapidev.models.AppUser;
import com.example.fmrapidev.viewmodels.UserRegisterViewModel;

public interface AppUserService {
    AppUser register(UserRegisterViewModel viewModel);
    AppUser findUserByUsername(String username);
}
