package com.leeparkim.wetube.presentation.api.comment

import com.leeparkim.wetube.presentation.ApiResponse
import com.leeparkim.wetube.presentation.api.comment.dto.CommentResDTO
import com.leeparkim.wetube.presentation.api.comment.dto.PostCommentReqDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "댓긇", description = "댓글 관련 api")
@RestController
@RequestMapping("/api")
class CommentController {

    @Operation(summary = "댓글 생성 API")
    @PostMapping("/post/{postId}/comment")
    fun postComment(@PathVariable postId: Long,
                    @Parameter(hidden = true) @ModelAttribute userId: Long,
                    @RequestBody postCommentReq: PostCommentReqDTO) {

    }

    @Operation(summary = "댓글 삭제 API")
    @DeleteMapping("/comment/{commentId}")
    fun deleteComment(@PathVariable commentId: Long,
                      @Parameter(hidden = true) @ModelAttribute userId: Long) {

    }

    @Operation(summary = "댓글 수정 API")
    @PatchMapping("/comment/{commentId}")
    fun patchComment(@PathVariable commentId: Long,
                     @Parameter(hidden = true) @ModelAttribute userId: Long,
                     @RequestBody postCommentReq: PostCommentReqDTO) {

    }

    @Operation(summary = "나의 댓글 확인 API")
    @GetMapping("/me/comments")
    fun getMyComments(pageable: Pageable,
                      @Parameter(hidden = true) @ModelAttribute userId: Long): ApiResponse<Array<CommentResDTO>> {
        return ApiResponse.success()
    }

    @Operation(summary = "Post의 댓글 확인 API")
    @GetMapping("/post/{postId}/comments")
    fun getPostComments(@PathVariable postId: Long,
                        pageable: Pageable): ApiResponse<Array<CommentResDTO>> {
        return ApiResponse.success()
    }
}