package com.leeparkim.wetube.presentation.api.post

import com.leeparkim.wetube.presentation.ApiResponse
import com.leeparkim.wetube.presentation.api.post.dto.PatchPostRequestDto
import com.leeparkim.wetube.presentation.api.post.dto.PostDetailResponseDto
import com.leeparkim.wetube.presentation.api.post.dto.PostResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable
import org.springframework.web.bind.annotation.*

@Tag(name = "동영상", description = "동영상 관련 api")
@RestController
@RequestMapping("/api/post")
class PostController {
    @Operation(summary = "동영상 리스트 API")
    @GetMapping
    fun getPostList(pageable: Pageable): ApiResponse<Array<PostResponseDto>> {
        return ApiResponse.success()
    }

    @Operation(summary = "동영상 세부정보 API")
    @GetMapping("/{postId}")
    fun getPostDetail(@PathVariable postId: String,
                      @Parameter(hidden = true) @ModelAttribute userId: Long): ApiResponse<PostDetailResponseDto> {
        return ApiResponse.success()
    }

    @Operation(summary = "동영상 생성 API", description = "추후 바뀔 예정")
    @PostMapping
    fun postVideo(@Parameter(hidden = true) @ModelAttribute userId: Long) {
    }

    @Operation(summary = "동영상 내용 수정 API")
    @PatchMapping("/{postId}")
    fun patchVideo(@PathVariable postId: String,
                   @Parameter(hidden = true) @ModelAttribute userId: Long,
                   @RequestBody patchPostReq: PatchPostRequestDto) {
    }

    @Operation(summary = "동영상 내용 삭제 API")
    @DeleteMapping("/{postId}")
    fun deleteVideo(@PathVariable postId: String,
                    @Parameter(hidden = true) @ModelAttribute userId: Long) {
    }

    @Operation(summary = "동영상 검색 API")
    @GetMapping("/search")
    fun searchVideo(@RequestParam query: String,
                    pageable: Pageable,
                    @Parameter(hidden = true) @ModelAttribute userId: Long): ApiResponse<Array<PostResponseDto>> {
        return ApiResponse.success()
    }

    @Operation(summary = "유저의 동영상 API")
    @GetMapping("/user/{targetUserId}")
    fun getUserVideo(@PathVariable targetUserId: String,
                     pageable: Pageable,
                     @Parameter(hidden = true) @ModelAttribute userId: Long): ApiResponse<Array<PostResponseDto>> {
        return ApiResponse.success()
    }
}