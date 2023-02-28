import io.reactivex.Observable
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.assertThrows
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

class FutureUtilsKtTest {
    @Test
    fun `toObservable success case`() {
        val future = CompletableFuture.completedFuture(1)
        val observable = toObservable(future)
        val res = observable.blockingIterable().toList()
        assertEquals(listOf(1), res)
    }
    
    @Test
    @Timeout(10, unit = TimeUnit.SECONDS)
    fun `toObservable fail case`() {
        val future = CompletableFuture<Int>()
        val observable = toObservable(future)
        future.completeExceptionally(RuntimeException("test"))
        val e = assertThrows<RuntimeException> {
            observable.blockingSubscribe()
        }
        assertEquals("test", e.message)
    }
}
