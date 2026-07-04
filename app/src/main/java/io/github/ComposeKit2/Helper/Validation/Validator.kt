package ComposeKit.Helper.Validation

class Validator(private val value: String) {

    private var error: String? = null

    fun required(message: String = "Wajib diisi"): Validator {
        if (error != null) return this
        val result = Rules.required(value, message)
        if (!result.isValid) error = result.message
        return this
    }

    fun email(message: String = "Email tidak valid"): Validator {
        if (error != null) return this
        val result = Rules.email(value, message)
        if (!result.isValid) error = result.message
        return this
    }

    fun min(length: Int, message: String? = null): Validator {
        if (error != null) return this
        val result = Rules.min(value, length, message)
        if (!result.isValid) error = result.message
        return this
    }

    fun validate(): ValidationResult {
        return if (error == null) ValidationResult(true)
        else ValidationResult(false, error)
    }
}