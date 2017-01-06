package com.xaut.example.myapi.dependent;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by pc on 2016/12/14.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface PerActivity {
}
