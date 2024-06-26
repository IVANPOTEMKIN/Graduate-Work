package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.ImageService;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static ru.skypro.homework.constants.documentation.CodesAndDescriptions.*;
import static ru.skypro.homework.constants.documentation.TagsAndNames.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;

    @Operation(
            tags = TAG_IMAGES,
            summary = GET_AVATAR,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
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

    @GetMapping(value = "/user/{id}", produces = {IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getAvatar(@PathVariable int id) {
        return ResponseEntity.ok(imageService.downloadImage(id));
    }

    @Operation(
            tags = TAG_IMAGES,
            summary = GET_IMAGE,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
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

    @GetMapping(value = "/ad/{id}", produces = {IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        return ResponseEntity.ok(imageService.downloadImage(id));
    }
}