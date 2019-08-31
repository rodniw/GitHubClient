package dev.rodni.ru.githubclient.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dev.rodni.ru.githubclient.di.Injector;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseController extends Controller {

    private final CompositeDisposable disposables = new CompositeDisposable();

    private boolean injected = false;
    private Unbinder unbinder;

    public BaseController() {
    }

    public BaseController(Bundle bundle) {
        super(bundle);
    }

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        // Controller instances are retained across config changes, so this method can be called more than once. This makes
        // sure we don't waste any time injecting more than once, though technically it wouldn't change functionality.
        if (!injected) {
            Injector.inject(this);
            injected = true;
        }
        super.onContextAvailable(context);
    }

    @NonNull
    @Override
    protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View v = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, v);
        onViewBound(v);
        disposables.addAll(subscriptions());
        return v;
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        //note
        //we need to use clear() not dispose() because dispose method not allow us to reuse disposables
        disposables.clear();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    protected void onViewBound(View view) {

    }

    //list of disposables that was disposed
    protected Disposable[] subscriptions() {
        return new Disposable[0];
    }

    @LayoutRes
    protected abstract int layoutRes();
}


//SOME EDU INFO NOT FOR THE EXACT APP

/*
Conductor позиционируется как замена стандартным фрагментам.
Основная идея обернуть View и дать доступ к методам жизненного цикла.
Conductor имеет свой жизненный цикл, который сильно проще чем у фрагметов, но и в нём есть свои хитрости.

Основные преимущества, которые даёт Conductor:

Упрощение кода
Транзакции выполняются мгновенно
Возможность построить приложение на одной Activity
Не ограничивает в выборе архитектуры приложения
Легко встраиваемые анимации
Отсутствие необходимости сохранять состояния между изменениями конфигураций

Так же в коробке вы получите:

Работа с бэкстеком
Стандартные коллбеки активити легко доступны
Несколько стандартных анимаций
Привязка жизненного цикла к RxJava
Быстрая интеграция с ViewPager

to change controller the same as fragment transaction we need to execute this:

      getRouter().pushController(RouterTransaction.with(new ConeController(conesCount)));

to get to the previous controller do this:

      @Override
         public void onBackPressed() {
             if (!router.handleBack()) {
                 super.onBackPressed();
             }
         }

to solve problem with closing with the last tap because of the backstack we need to do this:

setPopsLastView с параметром false

onAttach — вызывается при показе контроллера на экране
onDetach — вызывается при удалении контроллера с экрана
onDestroyView — вызывается при уничтожении вида привязанного к контроллеру
onCreateView — вызывается при создании вида для контроллера
onDestroy — вызывается перед тем как контроллер будет уничтожен

Следующие методы вызываются в процессе жизненного цикла, но по факту не могут быть к нему отнесены. Порядок их вызовов может зависеть от анимации перехода. И опираться ни на что кроме обработки анимации в них не стоит.
onChangeStarted — вызывается перед началом анимации
onChangeEnded — вызывается по завершению анимации
 */

/*
В конструктор мы передали параметр который должен быть наследником Controller и реализовывать интерфейс нашего слушателя.
Запомнили его с помощью вызова метода setTargetController.

При уходе с контроллера мы обновляем количество шишек в HomeController вызовом conesLeft(...) у слушателя.


//File: ConeController.java
public class ConeController extends Controller {
    private int conesCount = 0;
    private TextView textField;

    public <T extends Controller & ConeListener> ConeController(int conesCount, T listener) {
        this.conesCount = conesCount;
        getArgs().putInt("conesCount", conesCount);
        setTargetController(listener);
    }

    public ConeController(@Nullable Bundle args) {
        super(args);
        conesCount = args.getInt("conesCount");
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_cone, container, false);
        textField = (TextView) view.findViewById(R.id.textField);

        view.findViewById(R.id.collectConeButton)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getTargetController() != null) {
                            conesCount--;
                            getArgs().putInt("conesCount", conesCount);
                            update();
                        }
                    }
                });
        return view;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        update();
    }

    @Override
    public boolean handleBack() {
        ((ConeListener) getTargetController()).conesLeft(conesCount);
        return super.handleBack();
    }

    private void update() {
        textField.setText("Cones: " + conesCount);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        textField = null;
    }

    public interface ConeListener {
        void conesLeft(int count);
    }
}

//HomeController.java
public class HomeController extends Controller implements ConeController.ConeListener {

....

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_home, container, false);
        tree = view.findViewById(R.id.tree);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGrown) {
                    getRouter().pushController(RouterTransaction.with(new ConeController(conesCount, HomeController.this)));
                } else {
                    isGrown = true;
                    update();
                }
            }
        });

        return view;
    }

    @Override
    public void conesLeft(int count) {
        conesCount = count;
    }
 */
