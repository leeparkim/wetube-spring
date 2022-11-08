import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


enum class SocialType {
    GOOGLE
}

@Entity
data class User (
        val profileUrl: String,
        val username: String,
        val socialId: String,
        val socialType: SocialType,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0
)
