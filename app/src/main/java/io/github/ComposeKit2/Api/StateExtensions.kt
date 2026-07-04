package ComposeKit.Helper.Api

fun <T> State<T>.set(value: T) {
    this.value = value
}
fun <T> State<T>.update(value: T) = set(value)