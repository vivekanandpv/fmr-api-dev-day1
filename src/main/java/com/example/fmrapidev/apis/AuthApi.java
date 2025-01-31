package com.example.fmrapidev.apis;

import com.example.fmrapidev.services.AuthService;
import com.example.fmrapidev.viewmodels.LoginViewModel;
import com.example.fmrapidev.viewmodels.TokenResponseViewModel;
import com.example.fmrapidev.viewmodels.UserRegisterViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthApi {
    private final AuthService authService;

    public AuthApi(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<TokenResponseViewModel> login(@RequestBody LoginViewModel viewModel) {
        return ResponseEntity.ok(authService.getToken(viewModel));
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterViewModel viewModel) {
        authService.register(viewModel);
        return ResponseEntity.noContent().build();
    }
}
