package com.leeparkim.wetube.presentation.api.comment

import com.leeparkim.wetube.application.comment.CommentApplicationService
import com.leeparkim.wetube.presentation.ApiResponse
import com.leeparkim.wetube.presentation.api.comment.dto.CommentResponseDto
import com.leeparkim.wetube.presentation.api.comment.dto.PostCommentRequestDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "댓글", description = "댓글 관련 api")
@RestController
@RequestMapping("/api")
class CommentController(private val commentApplicationService: CommentApplicationService) {

    @Operation(summary = "댓글 생성 API")
    @PostMapping("/post/{postId}/comment")
    fun postComment(@PathVariable postId: Long,
                    @Parameter(hidden = true) @ModelAttribute userId: Long,
                    @RequestBody postCommentReq: PostCommentRequestDto): ApiResponse<Unit> {
        commentApplicationService.createComment(userId, postId, postCommentReq.content)
        return ApiResponse.success()
    }

    @Operation(summary = "댓글 삭제 API")
    @DeleteMapping("/comment/{commentId}")
    fun deleteComment(@PathVariable commentId: Long): ApiResponse<Unit> {
        commentApplicationService.deleteComment(commentId)
        return ApiResponse.success()
    }

    @Operation(summary = "댓글 수정 API")
    @PatchMapping("/comment/{commentId}")
    fun patchComment(@PathVariable commentId: Long,
                     @RequestBody postCommentReq: PostCommentRequestDto): ApiResponse<Unit> {
        commentApplicationService.updateComment(commentId, postCommentReq.content)
        return ApiResponse.success()
    }

    @Operation(summary = "나의 댓글 확인 API")
    @GetMapping("/me/comments")
    fun getMyComments(pageable: Pageable,
                      @Parameter(hidden = true) @ModelAttribute userId: Long): ApiResponse<Page<CommentResponseDto>> {
        return ApiResponse.success(commentApplicationService.getUserComment(userId, pageable))
    }

    @Operation(summary = "Post의 댓글 확인 API")
    @GetMapping("/post/{postId}/comments")
    fun getPostComments(@PathVariable postId: Long,
                        pageable: Pageable): ApiResponse<Page<CommentResponseDto>> {
        return ApiResponse.success(commentApplicationService.getPostComment(postId, pageable))
    }
}