package com.example.fmrapidev.services;

import com.example.fmrapidev.exceptions.LoginFailedException;
import com.example.fmrapidev.models.AppUser;
import com.example.fmrapidev.repositories.AppUserRepository;
import com.example.fmrapidev.security.AppUserDetails;
import com.example.fmrapidev.util.JwtUtils;
import com.example.fmrapidev.viewmodels.LoginViewModel;
import com.example.fmrapidev.viewmodels.TokenResponseViewModel;
import com.example.fmrapidev.viewmodels.UserRegisterViewModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AppUserService appUserService;

    public AuthServiceImplementation(
            AppUserRepository appUserRepository, 
            PasswordEncoder passwordEncoder, 
            JwtUtils jwtUtils, 
            AppUserService appUserService
    ) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.appUserService = appUserService;
    }

    @Override
    public TokenResponseViewModel getToken(LoginViewModel viewModel) {
        AppUser user = appUserRepository.findUserByUsername(viewModel.getUsername())
                .orElseThrow(LoginFailedException::new);

        if (!passwordEncoder.matches(viewModel.getPassword(), user.getPassword())) {
            throw new LoginFailedException();
        }

        String token = jwtUtils.generateToken(new AppUserDetails(user));

        return new TokenResponseViewModel(token);
    }

    @Override
    public void register(UserRegisterViewModel viewModel) {
        appUserService.register(viewModel);
    }
}
