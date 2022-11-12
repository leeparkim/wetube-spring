import javax.persistence.Entity
import javax.persistence.ManyToOne
import java.time.OffsetDateTime

@Entity
class Video (
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,
        val fileUrl: String,
        val createdAt: OffsetDateTime = OffsetDateTime.now(),
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null
)
