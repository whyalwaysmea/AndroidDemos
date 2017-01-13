package com.whyalwaysmea.unittest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Long
 * on 2016/12/1.
 */
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void add() throws Exception {
        // this is right
        assertEquals(5, mCalculator.add(3,2));

        // this is wrong
//        assertEquals(5, mCalculator.add(3,7));

    }

}