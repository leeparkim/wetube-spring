import javax.persistence.Entity
import javax.persistence.ManyToOne
import java.time.OffsetDateTime

@Entity
data class Video (
        @ManyToOne
        val user: User,
        val fileUrl: String,
        val createdAt: OffsetDateTime = OffsetDateTime.now(),
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0
)
