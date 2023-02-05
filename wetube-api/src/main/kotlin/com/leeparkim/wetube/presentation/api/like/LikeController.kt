package com.leeparkim.wetube.presentation.api.like

import com.leeparkim.wetube.application.like.LikeApplicationService
import com.leeparkim.wetube.presentation.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "좋아요", description = "좋아요 관련 api")
@RestController
@RequestMapping("/api")
class LikeController(private val likeApplicationService: LikeApplicationService) {
    @Operation(summary = "댓글 좋아요 추가/삭제 API", description = "좋아요가 안눌린 상태에서는 추가, 좋아요가 눌린 상태에서는 삭제")
    @PostMapping("/comment/{commentId}")
    fun postCommentLike(@PathVariable commentId: Long,
                        @Parameter(hidden = true) @ModelAttribute userId: Long): ApiResponse<Unit> {
        likeApplicationService.clickCommentLike(userId, commentId)
        return ApiResponse.success()
    }

    @Operation(summary = "동영상 좋아요 추가/삭제 API", description = "좋아요가 안눌린 상태에서는 추가, 좋아요가 눌린 상태에서는 삭제")
    @PostMapping("/post/{postId}")
    fun postPostLike(@PathVariable postId: Long,
                     @Parameter(hidden = true) @ModelAttribute userId: Long): ApiResponse<Unit> {
        likeApplicationService.clickVideoLike(userId, postId)
        return ApiResponse.success()
    }
}