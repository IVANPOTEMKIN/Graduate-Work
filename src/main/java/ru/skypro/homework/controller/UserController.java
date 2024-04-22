package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.service.UserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static ru.skypro.homework.constants.documentation.CodesAndDescriptions.*;
import static ru.skypro.homework.constants.documentation.TagsAndNames.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    private final UserService service;

    @Operation(
            tags = TAG_USERS,
            summary = UPDATE_PASSWORD,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = CODE_401,
                            description = DESCRIPTION_CODE_401,
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = CODE_403,
                            description = DESCRIPTION_CODE_403,
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

    @PostMapping("/set_password")
    public ResponseEntity<?> updatePassword(@RequestBody NewPasswordDTO dto) {
        service.updatePassword(dto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = TAG_USERS,
            summary = GET_INFO_ABOUT_USER,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content(
                                    mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
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

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getInfoAboutUser() {
        return ResponseEntity.ok(service.getInfoAboutUser());
    }

    @Operation(
            tags = TAG_USERS,
            summary = UPDATE_INFO_ABOUT_USER,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content(
                                    mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateUserDTO.class)
                            )
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

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateInfoAboutUser(@RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(service.updateInfoAboutUser(dto));
    }

    @Operation(
            tags = TAG_USERS,
            summary = UPDATE_AVATAR_OF_USER,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
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
                            responseCode = CODE_417,
                            description = DESCRIPTION_CODE_417,
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = CODE_500,
                            description = DESCRIPTION_CODE_500,
                            content = @Content()
                    )
            }
    )

    @PatchMapping(value = "/me/image", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateAvatarOfUser(@RequestParam(name = "image")
                                                MultipartFile file) {

        service.updateAvatarOfUser(file);
        return ResponseEntity.ok().build();
    }
}