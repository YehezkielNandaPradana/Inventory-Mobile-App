package ComposeKit.Button

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

object AppButton {
    object Auth {
        @Composable
        fun Login(
            loading: Boolean,
            enabled: Boolean = true,
            onClick: () -> Unit,
        ) {
            UiButton.Button(
                text = "Login",
                onClick = onClick,
                loading = loading,
                enabled = enabled,
                variant = ButtonVariant.Primary
            )
        }
        @Composable
        fun Register(
            loading: Boolean,
            onClick: () -> Unit,
        ) {
            UiButton.Button(
                text = "Register",
                onClick = onClick,
                loading = loading,
                variant = ButtonVariant.Primary
            )
        }
        @Composable
        fun Logout(
            onClick: () -> Unit,
        ) {
            UiButton.Button(
                text = "Logout",
                onClick = onClick,
                variant = ButtonVariant.Danger
            )
        }
        @Composable
        fun ForgotPassword(
            onClick: () -> Unit,
        ) {
            UiButton.Button(
                text = "Forgot Password",
                onClick = onClick,
                variant = ButtonVariant.Ghost
            )
        }
    }
    object Crud {
        @Composable
        fun Save(
            loading: Boolean,
            onClick: () -> Unit,
        ) {
            UiButton.Button(
                text = "Save",
                onClick = onClick,
                loading = loading,
                variant = ButtonVariant.Primary
            )
        }

        @Composable
        fun Update(
            loading: Boolean,
            onClick: () -> Unit,
        ) {
            UiButton.Button(
                text = "Update",
                onClick = onClick,
                loading = loading,
                variant = ButtonVariant.Primary
            )
        }
        @Composable
        fun Delete(
            loading: Boolean,
            onConfirm: () -> Unit,
        ) {
            UiButton.Button(
                text = "Delete",
                variant = ButtonVariant.Danger,
                loading = loading,
                onClick = {
                    onConfirm()
                }
            )
        }
        @Composable
        fun Cancel(
            onClick: () -> Unit,
        ) {
            UiButton.Button(
                text = "Cancel",
                variant = ButtonVariant.Outline,
                onClick = onClick
            )
        }
    }
    object Nav {
        @Composable
        fun Back(
            navController: NavController,
        ) {
            UiButton.Button(
                text = "Back",
                variant = ButtonVariant.Ghost,
                onClick = {
                    navController.popBackStack()
                }
            )
        }
        @Composable
        fun Next(
            onClick: () -> Unit,
        ) {
            UiButton.Button(
                text = "Next",
                variant = ButtonVariant.Primary,
                onClick = onClick
            )
        }
        @Composable
        fun Close(
            onClick: () -> Unit,
        ) {
            UiButton.Button(
                text = "Close",
                variant = ButtonVariant.Ghost,
                onClick = onClick
            )
        }
    }
    object Dialog {
        @Composable
        fun Confirm(
            loading: Boolean,
            onConfirm: () -> Unit,
        ) {
            UiButton.Button(
                text = "Confirm",
                loading = loading,
                variant = ButtonVariant.Primary,
                onClick = onConfirm
            )
        }
        @Composable
        fun Cancel(
            onClick: () -> Unit,
        ) {
            UiButton.Button(
                text = "Cancel",
                variant = ButtonVariant.Outline,
                onClick = onClick
            )
        }
        @Composable
        fun DangerConfirm(
            loading: Boolean,
            onConfirm: () -> Unit,
        ) {
            UiButton.Button(
                text = "Yes, Delete",
                loading = loading,
                variant = ButtonVariant.Danger,
                onClick = onConfirm
            )
        }
    }
}