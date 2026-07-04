package ComposeKit.Helper.Api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.reflect.KProperty

class State<T>(initial: T) {
    var value by mutableStateOf(initial)
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }
}