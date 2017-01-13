package com.whyalwaysmea.unittest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *
 * Unit Tests 这种方式，跑的测试代码运行在本机 JVM 上，不需要编译Apk ，不需要 Android 设备的支持，速度相对快，
 * 当然，测试的对象不能含有 Android 的 API，否则运行时会报错。
 *
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}