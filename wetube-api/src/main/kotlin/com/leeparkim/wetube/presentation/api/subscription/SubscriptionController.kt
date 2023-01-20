package com.leeparkim.wetube.presentation.api.subscription

import com.leeparkim.wetube.application.subscription.SubscriptionApplicationService
import com.leeparkim.wetube.presentation.ApiResponse
import com.leeparkim.wetube.presentation.api.subscription.dto.SubscriptionResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@Tag(name = "구독", description = "구독 관련 api")
@RestController
@RequestMapping("/api")
class SubscriptionController(private val subscriptionApplicationService: SubscriptionApplicationService) {
    @Operation(summary = "구독 추가/삭제 API", description = "구독이 안눌린 상태에서는 추가, 구독이 눌린 상태에서는 삭제")
    @PostMapping("/user/{targetUserId}/subscription")
    fun postSubscription(@PathVariable targetUserId: Long,
                         @Parameter(hidden = true) @ModelAttribute userId: Long) {
        subscriptionApplicationService.createSubscription(userId, targetUserId)
    }

    @Operation(summary = "구독자 List API", description = "Target user의 구독자를 보여주는 API")
    @GetMapping("/user/{targetUserId}/subscription")
    fun getFollowers(@PathVariable targetUserId: Long,
                     pageable: Pageable): ApiResponse<Page<SubscriptionResponseDto>> {
        return ApiResponse.success(subscriptionApplicationService.getFollowers(targetUserId, pageable))
    }

    @Operation(summary = "구독중인 List API", description = "현재 로그인된 사용자가 구독중인 유저를 보여주는 API")
    @GetMapping("/subscription")
    fun getFollowings(@Parameter(hidden = true) @ModelAttribute userId: Long,
                      pageable: Pageable): ApiResponse<Page<SubscriptionResponseDto>> {
        return ApiResponse.success(subscriptionApplicationService.getFollowings(userId, pageable))
    }
}