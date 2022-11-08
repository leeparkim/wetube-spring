import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
data class CommentLike (
        @ManyToOne
        val user: User,
        @ManyToOne
        val comment: Comment,
)