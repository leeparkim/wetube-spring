import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
data class VideoLike (
        @ManyToOne
        val user: User,
        @ManyToOne
        val video: Video,
)