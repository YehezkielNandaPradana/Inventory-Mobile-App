package ComposeKit.Helper.Validation

data class ValidationResult(
    val isValid: Boolean,
    val message: String? = null
)