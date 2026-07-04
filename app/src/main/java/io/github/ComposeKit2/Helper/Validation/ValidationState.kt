package ComposeKit.Helper.Validation

data class ValidationState(
    val isError: Boolean = false,
    val errorMessage: String = ""
)