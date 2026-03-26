data class UserInput(val name : String?, val email: String?, val age: String?) 

data class UserProfile(
    var name: String = " ",
    var email: String = " ",
    var age: Int = 0,
    var isAdult: Boolean = false
)


fun buildProfile(input: UserInput?, logs: MutableList<String>): UserProfile? {
        
    val safeInput = input ?: run {
        logs.add("Input is null")
        return null
    }
    
    val name = safeInput.name?.trim()?.takeIf {it.length >= 3}
        ?: run {
            logs.add(if (safeInput.name == null) "Name is null" else "Name too short")
            return null
        }

    val email = safeInput.email?.trim()?.lowercase()?.takeIf {it.contains("@")}
        ?: run {
            logs.add(if (safeInput.email == null) "Email is null" else "Invalid email")
            return null
        }

    val age = safeInput.age?.let {it.toIntOrNull() }
        ?: run {
            logs.add(if (safeInput.age == null) "Age is null" else "Age is not a number")
            return null
        }

    return UserProfile().apply {
        this.name = name
        this.email = email
        this.age = age
        this.isAdult = age >= 18
    }.also {
        logs.add("Profile created for $email")
    }

}


