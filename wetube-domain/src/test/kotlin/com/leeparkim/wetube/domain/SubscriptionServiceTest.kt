package com.leeparkim.wetube.domain

import com.leeparkim.wetube.domain.subscription.Subscription
import com.leeparkim.wetube.domain.subscription.SubscriptionService
import com.leeparkim.wetube.domain.user.SocialType
import com.leeparkim.wetube.domain.user.User
import com.leeparkim.wetube.domain.user.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
class SubscriptionServiceTest {
    @Autowired
    lateinit var subscriptionService: SubscriptionService

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun test_isSubscribedByUserId() {
        // given
        val user1 = User(socialId = "1", socialType = SocialType.GOOGLE, username = "user1")
        val user2 = User(socialId = "2", socialType = SocialType.GOOGLE, username = "user2")
        userRepository.save(user1)
        userRepository.save(user2)

        val subscription = Subscription(user1.id, user2.id)
        subscriptionService.createSubscription(subscription)

        // when
        val result1 = subscriptionService.isSubscribedByUserId(user1.id, user2.id)
        val result2 = subscriptionService.isSubscribedByUserId(user2.id, user1.id)

        // then
        assertTrue(result1)
        assertFalse(result2)
    }

    @Test
    fun test_getFollowersAndFollowings() {
        // given
        val user1 = User(socialId = "1", socialType = SocialType.GOOGLE, username = "user1")
        val user2 = User(socialId = "2", socialType = SocialType.GOOGLE, username = "user2")
        val user3 = User(socialId = "3", socialType = SocialType.GOOGLE, username = "user3")
        val user4 = User(socialId = "4", socialType = SocialType.GOOGLE, username = "user4")
        userRepository.save(user1)
        userRepository.save(user2)
        userRepository.save(user3)
        userRepository.save(user4)

        subscriptionService.createSubscription(Subscription(user2.id, user1.id))
        subscriptionService.createSubscription(Subscription(user3.id, user1.id))
        subscriptionService.createSubscription(Subscription(user4.id, user1.id))

        subscriptionService.createSubscription(Subscription(user1.id, user2.id))
        subscriptionService.createSubscription(Subscription(user1.id, user3.id))
        subscriptionService.createSubscription(Subscription(user1.id, user4.id))

        // when
        var followers = subscriptionService.getFollowers(user1.id, PageRequest.of(0, 10))
        var followings = subscriptionService.getFollowers(user1.id, PageRequest.of(0, 10))

        // then
        assertEquals(followers.content.size, 3)
        assertEquals(followings.content.size, 3)

        // when
        followers = subscriptionService.getFollowers(user1.id, PageRequest.of(0, 2))
        followings = subscriptionService.getFollowers(user1.id, PageRequest.of(0, 2))

        // then
        assertEquals(followers.content.size, 2)
        assertEquals(followings.content.size, 2)
    }
}