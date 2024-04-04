package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static ru.skypro.homework.constants.documentation.CodesAndDescriptions.*;
import static ru.skypro.homework.constants.documentation.TagsAndNames.*;

@RestController
@RequestMapping("/ads")
public class CommentController {

    @Operation(
            tags = TAG_COMMENTS,
            summary = GET_ALL_COMMENTS_OF_AD,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content(
                                    mediaType = APPLICATION_OCTET_STREAM_VALUE,
                                    schema = @Schema(implementation = CommentsDTO.class)
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

    @GetMapping("/{id}/comments")
    public CommentsDTO getAllCommentsOfAd(@PathVariable int id) {
        return new CommentsDTO();
    }

    @Operation(
            tags = TAG_COMMENTS,
            summary = ADD_COMMENT_TO_AD,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content(
                                    mediaType = APPLICATION_OCTET_STREAM_VALUE,
                                    schema = @Schema(implementation = CommentDTO.class)
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

    @PostMapping("/{id}/comments")
    public CommentDTO addCommentToAd(@PathVariable int id,
                                     @RequestBody CreateOrUpdateCommentDTO dto) {
        return new CommentDTO();
    }

    @Operation(
            tags = TAG_COMMENTS,
            summary = DELETE_COMMENT,
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
                    )
            }
    )

    @DeleteMapping("/{adid}/comments/{commentid}")
    public ResponseEntity<?> deleteComment(@PathVariable int adid,
                                           @PathVariable int commentid) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = TAG_COMMENTS,
            summary = UPDATE_COMMENT,
            responses = {
                    @ApiResponse(
                            responseCode = CODE_200,
                            description = DESCRIPTION_CODE_200,
                            content = @Content(
                                    mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDTO.class)
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

    @PatchMapping("/{adid}/comments/{commentid}")
    public CommentDTO updateComment(@PathVariable int adid,
                                    @PathVariable int commentid,
                                    @RequestBody CreateOrUpdateCommentDTO dto) {
        return new CommentDTO();
    }
}