package com.example.fmrapidev.services;

import com.example.fmrapidev.viewmodels.LoginViewModel;
import com.example.fmrapidev.viewmodels.TokenResponseViewModel;
import com.example.fmrapidev.viewmodels.UserRegisterViewModel;

public interface AuthService {
    TokenResponseViewModel getToken(LoginViewModel viewModel);
    void register(UserRegisterViewModel viewModel);
}
