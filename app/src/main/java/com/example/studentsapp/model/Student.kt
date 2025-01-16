import java.io.Serializable

data class Student(
    var name: String,
    var id: String,
    var avatarUrl: String,
    var isChecked: Boolean
) : Serializable

