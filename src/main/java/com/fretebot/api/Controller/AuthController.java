
package com.fretebot.api.Controller;

import com.fretebot.api.DTO.LoginDTO;
import com.fretebot.api.DTO.LoginResponseDTO;
import com.fretebot.api.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginDTO dto) {

        return ResponseEntity.ok(
                authService.login(dto)
        );
    }
}


