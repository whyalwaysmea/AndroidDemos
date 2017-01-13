package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Long
 * on 2017/1/13.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface DIActivity {

}