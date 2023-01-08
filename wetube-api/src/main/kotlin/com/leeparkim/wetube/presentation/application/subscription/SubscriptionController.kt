package com.leeparkim.wetube.presentation.application.subscription

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "구독", description = "구독 관련 api")
@RestController
@RequestMapping("/api")
class SubscriptionController {
    @Operation(summary = "구독 추가/삭제 API", description = "구독이 안눌린 상태에서는 추가, 구독이 눌린 상태에서는 삭제")
    @PostMapping("/user/{targetUserId}/subscription")
    fun postSubscription(@PathVariable targetUserId: Long,
                         @Parameter(hidden = true) @ModelAttribute userId: Long) {

    }
}