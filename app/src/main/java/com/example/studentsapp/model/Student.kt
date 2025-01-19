import java.io.Serializable

data class Student(
    var name: String,
    var id: String,
    var avatarUrl: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean
) : Serializable
