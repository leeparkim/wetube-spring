package com.leeparkim.wetube.presentation.api.user

import com.leeparkim.wetube.presentation.ApiResponse
import com.leeparkim.wetube.presentation.api.user.dto.UserResDTO
import com.leeparkim.wetube.presentation.api.user.dto.OtherUserResDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "유저", description = "유저 관련 api")
@RestController
@RequestMapping("/api/user")
class UserController {
    @Operation(summary = "내 정보 검색 API")
    @GetMapping("/me")
    fun getMe(@Parameter(hidden = true) @ModelAttribute userId: Long): ApiResponse<UserResDTO> {
        return ApiResponse.success()
    }

    @Operation(summary = "다른 사람 유저 정보 검색 API")
    @GetMapping("/other/{targetUserId}")
    fun getOtherUser(@PathVariable targetUserId: Long,
                     @Parameter(hidden = true) @ModelAttribute userId: Long): ApiResponse<OtherUserResDTO> {
        return ApiResponse.success()
    }
}