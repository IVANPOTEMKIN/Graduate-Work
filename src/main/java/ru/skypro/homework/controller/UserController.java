package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static ru.skypro.homework.constants.documentation.CodesAndDescriptions.*;
import static ru.skypro.homework.constants.documentation.TagsAndNames.*;

@RestController
@RequestMapping("/users")
public class UserController {

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
                    )
            }
    )

    @PostMapping("/set_password")
    public ResponseEntity<?> updatePassword(@RequestBody(required = false) NewPasswordDTO dto) {
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
                    )
            }
    )

    @GetMapping("/me")
    public UserDTO getInfoAboutUser() {
        return new UserDTO();
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
                            responseCode = CODE_401,
                            description = DESCRIPTION_CODE_401,
                            content = @Content()
                    )
            }
    )

    @PatchMapping("/me")
    public UpdateUserDTO updateInfoAboutUser(@RequestBody(required = false) UpdateUserDTO dto) {
        return new UpdateUserDTO();
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
                    )
            }
    )

    @PatchMapping(value = "/me/image", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAvatarOfUser(@RequestParam MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}