import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class CommentLike (
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,
        @ManyToOne(fetch = FetchType.LAZY)
        val comment: Comment,
)
