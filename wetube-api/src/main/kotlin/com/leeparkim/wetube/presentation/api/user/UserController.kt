package com.leeparkim.wetube.presentation.api.user

import com.leeparkim.wetube.application.user.UserApplicationService
import com.leeparkim.wetube.presentation.ApiResponse
import com.leeparkim.wetube.presentation.api.user.dto.UserResponseDto
import com.leeparkim.wetube.presentation.api.user.dto.OtherUserResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "유저", description = "유저 관련 api")
@RestController
@RequestMapping("/api/user")
class UserController(private val userApplicationService: UserApplicationService) {
    @Operation(summary = "내 정보 검색 API")
    @GetMapping("/me")
    fun getMe(@Parameter(hidden = true) @ModelAttribute("userId") userId: Long): ApiResponse<UserResponseDto> {
        return ApiResponse.success(userApplicationService.getMe(userId))
    }

    @Operation(summary = "다른 사람 유저 정보 검색 API")
    @GetMapping("/other/{targetUserId}")
    fun getOtherUser(@PathVariable targetUserId: Long,
                     @Parameter(hidden = true) @ModelAttribute("userId") userId: Long): ApiResponse<OtherUserResponseDto> {
        return ApiResponse.success(userApplicationService.getOtherUser(targetUserId, userId))
    }
}