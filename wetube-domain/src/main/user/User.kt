import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class User (
        val profileUrl: String?,
        val username: String,
        val socialId: String,
        @Enumerated(EnumType.STRING) val socialType: SocialType,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null
)
