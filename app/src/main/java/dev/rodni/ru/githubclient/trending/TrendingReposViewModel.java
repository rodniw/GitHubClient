package dev.rodni.ru.githubclient.trending;

import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.R;
import dev.rodni.ru.githubclient.di.ScreenScope;
import dev.rodni.ru.githubclient.model.Repo;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
class TrendingReposViewModel {

    //RxPM -> Relays are RxJava types which are both an Observable and a Consumer.
    //BehaviorRelay – хранит последнее полученное значение и рассылает его каждый раз при подписке.
    //Лучше всего подходит для хранения и изменения состояний.
    //private final BehaviorRelay<List<Repo>> reposRelay = BehaviorRelay.create();
    private final BehaviorRelay<List<Repo>> reposRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    TrendingReposViewModel() {

    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Observable<List<Repo>> repos() {
        return reposRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Consumer<List<Repo>> reposUpdated() {
        errorRelay.accept(-1);
        return reposRelay;
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading Repos");
            errorRelay.accept(R.string.api_error_repos);
        };
    }
}

//from rx docs:
//A Subject is a sort of bridge or proxy that is available in some implementations
//of ReactiveX that acts both as an observer and as an Observable.
//Because it is an observer, it can subscribe to one or more Observables,
//and because it is an Observable, it can pass through the items it observes by reemitting them,
// and it can also emit new items.
//
//Because a Subject subscribes to an Observable,
// it will trigger that Observable to begin emitting items
// (if that Observable is “cold” — that is, if it waits for a subscription before it begins to emit items).
// This can have the effect of making the resulting Subject a “hot” Observable variant of the original “cold” Observable.
