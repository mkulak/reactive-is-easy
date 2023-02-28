import io.reactivex.Observable
import java.util.concurrent.CompletableFuture


fun <T : Any> toObservable(future: CompletableFuture<T>): Observable<T> =
    Observable.create { subscriber ->
        future.whenComplete { result: T, error: Throwable? ->
            if (error != null) {
                subscriber.onError(error)
            } else {
                subscriber.onNext(result)
                subscriber.onComplete()
            }
        }
    }
