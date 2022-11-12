import javax.persistence.Entity
import javax.persistence.ManyToOne
import java.time.OffsetDateTime

@Entity
class Comment (
        @ManyToOne(fetch = FetchType.LAZY)
        val video: Video,
        @ManyToOne
        val user: User,
        val content: String,
        val createdAt: OffsetDateTime = OffsetDateTime.now(),
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null
)
