package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.AdsDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.service.AdService;

import static org.springframework.http.MediaType.*;
import static ru.skypro.homework.constants.documentation.CodesAndDescriptions.*;
import static ru.skypro.homework.constants.documentation.TagsAndNames.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    private final AdService service;

    @Operation(
            tags = TAG_ADS,
            summary = GET_ALL_ADS,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content(
                                    mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            }
    )

    @GetMapping
    public ResponseEntity<AdsDTO> getAllAds() {
        return service.getAllAds();
    }

    @Operation(
            tags = TAG_ADS,
            summary = ADD_AD,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_201,
                            description = DESCRIPTION_CODE_201,
                            content = @Content(
                                    mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = CODE_401,
                            description = DESCRIPTION_CODE_401,
                            content = @Content()
                    )
            }
    )

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> AddAd(@RequestPart CreateOrUpdateAdDTO properties,
                                       @RequestPart MultipartFile image,
                                       Authentication auth) {

        return service.AddAd(properties, image, auth);
    }

    @Operation(
            tags = TAG_ADS,
            summary = GET_INFO_ABOUT_AD,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content(
                                    mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExtendedAdDTO.class)
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
                    )
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getInfoAboutAd(@PathVariable int id) {
        return service.getInfoAboutAd(id);
    }

    @Operation(
            tags = TAG_ADS,
            summary = DELETE_AD,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_204,
                            description = DESCRIPTION_CODE_204,
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
                    )
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable int id) {
        return service.deleteAd(id);
    }

    @Operation(
            tags = TAG_ADS,
            summary = UPDATE_INFO_ABOUT_AD,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content(
                                    mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
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
                    )
            }
    )

    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateInfoAboutAd(@PathVariable int id,
                                                   @RequestBody CreateOrUpdateAdDTO dto) {

        return service.updateInfoAboutAd(id, dto);
    }

    @Operation(
            tags = TAG_ADS,
            summary = GET_ALL_ADS_OF_USER,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content(
                                    mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
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
    public ResponseEntity<AdsDTO> getAllAdsOfUser(Authentication auth) {
        return service.getAllAdsOfUser(auth);
    }

    @Operation(
            tags = TAG_ADS,
            summary = UPDATE_IMAGE_OF_AD,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content(
                                    mediaType = APPLICATION_OCTET_STREAM_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = String.class)
                                    )
                            )
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
                    )
            }
    )

    @PatchMapping(value = "/{id}/image", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImageOfAd(@PathVariable int id,
                                                  @RequestPart MultipartFile image) {

        return service.updateImageOfAd(id, image);
    }
}