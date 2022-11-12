import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class VideoLike (
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,
        @ManyToOne(fetch = FetchType.LAZY)
        val video: Video,
)
