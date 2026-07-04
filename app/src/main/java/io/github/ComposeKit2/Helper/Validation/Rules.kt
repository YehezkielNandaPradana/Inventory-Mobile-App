package ComposeKit.Helper.Validation

import android.util.Patterns

object Rules {

    fun required(value: String, message: String = "Field wajib diisi"): ValidationResult {
        return if (value.isBlank()) ValidationResult(false, message)
        else ValidationResult(true)
    }

    fun email(value: String, message: String = "Format email tidak valid"): ValidationResult {
        val pattern = Patterns.EMAIL_ADDRESS
        return if (!pattern.matcher(value).matches())
            ValidationResult(false, message)
        else ValidationResult(true)
    }

    fun min(value: String, length: Int, message: String? = null): ValidationResult {
        return if (value.length < length)
            ValidationResult(false, message ?: "Minimal $length karakter")
        else ValidationResult(true)
    }
}