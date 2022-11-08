import javax.persistence.Entity
import javax.persistence.ManyToOne
import java.time.OffsetDateTime

@Entity
data class Comment (
        @ManyToOne
        val video: Video,
        @ManyToOne
        val user: User,
        val content: String,
        val createdAt: OffsetDateTime = OffsetDateTime.now(),
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0
)