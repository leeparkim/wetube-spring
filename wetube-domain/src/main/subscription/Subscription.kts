import javax.persistence.Entity
import javax.persistence.ManyToOne
import java.time.OffsetDateTime

@Entity
data class Subscription (
        @ManyToOne
        val follower: User,
        @ManyToOne
        val following: User,
)