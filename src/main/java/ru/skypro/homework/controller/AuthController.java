package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.auth.LoginDTO;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.service.AuthService;

import static ru.skypro.homework.constants.documentation.CodesAndDescriptions.*;
import static ru.skypro.homework.constants.documentation.TagsAndNames.*;

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
        return authService.login(dto);
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
        return authService.register(dto);
    }
}