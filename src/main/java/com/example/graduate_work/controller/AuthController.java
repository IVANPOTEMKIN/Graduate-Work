package com.example.graduate_work.controller;

import com.example.graduate_work.dto.auth.LoginDTO;
import com.example.graduate_work.dto.auth.RegisterDTO;
import com.example.graduate_work.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.graduate_work.utils.documentation.CodesAndDescriptions.*;
import static com.example.graduate_work.utils.documentation.TagsAndNames.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    @Operation(
            tags = TAG_AUTHORIZATION,
            summary = AUTHORIZATION_USER,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = CODE_400,
                            description = DESCRIPTION_CODE_400,
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = CODE_401,
                            description = DESCRIPTION_CODE_401,
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = CODE_404,
                            description = DESCRIPTION_CODE_404,
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = CODE_500,
                            description = DESCRIPTION_CODE_500,
                            content = @Content()
                    )
            }
    )

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        authService.login(dto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = TAG_REGISTRATION,
            summary = REGISTRATION_USER,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_201,
                            description = DESCRIPTION_CODE_201,
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = CODE_400,
                            description = DESCRIPTION_CODE_400,
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = CODE_500,
                            description = DESCRIPTION_CODE_500,
                            content = @Content()
                    )
            }
    )

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO dto) {
        authService.register(dto);
        return ResponseEntity.status(CREATED).build();
    }
}