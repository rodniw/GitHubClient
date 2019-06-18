package dev.rodni.ru.githubclient.di;

//we do not have @ControllerKey annotation for free so we need to create it by ourselves

import com.bluelinelabs.conductor.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

@MapKey
@Target(ElementType.METHOD)
public @interface ControllerKey {

    Class<? extends Controller> value();
}
