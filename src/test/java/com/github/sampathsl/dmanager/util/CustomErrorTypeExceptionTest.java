package com.github.sampathsl.dmanager.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomErrorTypeExceptionTest {

  private String sampleError;

  private CustomErrorTypeException customErrorTypeException;

  @Before
  public void setUp() {
    sampleError = "Sample Error";
    customErrorTypeException = new CustomErrorTypeException(sampleError);
  }

  @Test
  public void testGetErrorMessage() {
    Assert.assertEquals(customErrorTypeException.getErrorMessage(), sampleError);
  }
}
