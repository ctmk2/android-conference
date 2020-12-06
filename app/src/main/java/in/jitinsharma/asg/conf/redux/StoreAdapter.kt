package `in`.jitinsharma.asg.conf.redux

import androidx.compose.runtime.*
import org.rekotlin.BlockSubscriber
import org.rekotlin.StateType
import org.rekotlin.StoreType

@Composable
fun <T : StateType> StoreType<T>.observeAsState(): State<T?> {
    val state = remember { mutableStateOf<T?>(null) }
    val subscriber = BlockSubscriber<T> {
        state.value = it
    }
    onActive {
        subscribe(subscriber)
        onDispose {
            unsubscribe(subscriber)
        }
    }
    return state
}
