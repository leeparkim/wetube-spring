import javax.persistence.Entity
import javax.persistence.ManyToOne
import java.time.OffsetDateTime

@Entity
class Subscription (
        @ManyToOne(fetch = FetchType.LAZY)
        val follower: User,
        @ManyToOne(fetch = FetchType.LAZY)
        val following: User,
)
