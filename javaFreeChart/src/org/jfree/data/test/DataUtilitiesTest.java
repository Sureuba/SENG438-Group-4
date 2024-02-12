/*
 * Due: Monday Feb 12th, 2024
 * Completed By: Naina and Uruba
 * Testing DataUtilities
 */

package org.jfree.data.test;
import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.KeyedValues;

public class DataUtilitiesTest extends DataUtilities {
	private Mockery mockery;
	private Values2D values;
	private KeyedValues keyedValues;

	@Before
	public void setUp() throws Exception {
		mockery = new Mockery();
		values = mockery.mock(Values2D.class);
		keyedValues = mockery.mock(KeyedValues.class);
	}

//Testing calculateColumnTotal() **************************************************************8
	
	@Test  // this test covers normal values for variable data and column
	public void calculateColumnTotalTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(2.5));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(10.0, result, .000000001d);
	}
	
	
	@Test  // this test covers normal values for variable data and column
	public void calculateColumnTotalNegativeTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(-7.5));
				one(values).getValue(1, 0);
				will(returnValue(-7.5));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(-15, result, .000000001d);
	}

	
	@Test // this test covers normal values for variable data and column
	public void calculateColumnTotalZeroTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(0));
				one(values).getValue(1, 0);
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(0, result, .000000001d);
	}
	
	
	@Test // this test covers invalid values for variable data and normal values for column
	public void calculateColumnTotalNullTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(null));
				one(values).getValue(1, 0);
				will(returnValue(null));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(0, result, .000000001d);
	}
	
	
	@Test // this test covers invalid values for variable data and normal values for column
	(expected = InvalidParameterException.class)
	public void calculateColumnTotalExceptionTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue('a'));
				one(values).getValue(1, 0);
				will(returnValue(7.5));
			}
		});
		DataUtilities.calculateColumnTotal(values, 0);
	}
	
	
	@Test // this test covers maximum and minimum values for variable data and normal values for column
	public void calculateColumnTotalMinMaxTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(Double.MIN_VALUE));
				one(values).getValue(1, 0);
				will(returnValue(Double.MAX_VALUE));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(Double.MIN_VALUE + Double.MAX_VALUE, result, .000000001d);
	}

	// calculates the column total of the second column
	
	@Test  // this test covers normal values for variable data and column
	public void calculateColumnTotalColumnOneTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));
				one(values).getValue(0, 0);
				will(returnValue(0));
				one(values).getValue(1, 0);
				will(returnValue(1.5));
				one(values).getValue(2, 0);
				will(returnValue(2.5));
				one(values).getValue(0, 1);
				will(returnValue(3.5));
				one(values).getValue(1, 1);
				will(returnValue(4.5));
				one(values).getValue(2, 1);
				will(returnValue(5.5));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 1);
		assertEquals(13.5, result, .000000001d);
	}
//Testing calculateRowTotal() **************************************************************8
	
	@Test // this test covers normal values for variable data and row
	public void calculateRowTotalTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(0, 1);
				will(returnValue(2.5));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(10.0, result, .000000001d);
	}

	// this test covers normal values for variable data and row
	@Test
	public void calculateRowTotalNegativeTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(-7.5));
				one(values).getValue(0, 1);
				will(returnValue(-7.5));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(-15, result, .000000001d);
	}

	// this test covers normal values for variable data and row
	@Test
	public void calculateRowTotalZeroTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(0));
				one(values).getValue(0, 1);
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(0, result, .000000001d);
	}
	
	// this test covers invalid values for variable data and normal values for row
	@Test
	public void calculateRowTotalNullTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(null));
				one(values).getValue(0, 1);
				will(returnValue(null));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(0, result, .000000001d);
	}
	
	// this test covers invalid values for variable data and normal values for row
	@Test(expected = InvalidParameterException.class)
	public void calculateRowTotalExceptionTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue('a'));
				one(values).getValue(0, 1);
				will(returnValue(7.5));
			}
		});
		DataUtilities.calculateRowTotal(values, 0);
	}

	// this test covers maximum and minimum values for variable data and normal values for row
	@Test
	public void calculateRowMinMaxTest() {
		mockery.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(Double.MIN_VALUE));
				one(values).getValue(0, 1);
				will(returnValue(Double.MAX_VALUE));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(Double.MIN_VALUE + Double.MAX_VALUE, result, .000000001d);
	}

	// calculates the column total of the second row
	// this test covers normal values for variable data and row
	@Test
	public void calculateRowTotalRowOneTest() {
		mockery.checking(new Expectations() {
			{
			
				one(values).getColumnCount();
				will(returnValue(3));
				one(values).getValue(0, 0);
				will(returnValue(0));
				one(values).getValue(0, 1);
				will(returnValue(1.5));
				one(values).getValue(0, 2);
				will(returnValue(2.5));
				one(values).getValue(1, 0);
				will(returnValue(3.5));
				one(values).getValue(1, 1);
				will(returnValue(4.5));
				one(values).getValue(1, 2);
				will(returnValue(5.5));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 1);
		assertEquals(13.5, result, .000000001d);
	}
	
	//Testing createNumberArray() **************************************************************8

	
	@Test // this test covers normal values for variable data
	public void createNumberArrayTest() {
		double[] data = { 1.0, 2.5, -3.5, 4.5, 0.0 };
		Number[] result = DataUtilities.createNumberArray(data);

		assertEquals(data.length, result.length);

		for (int i = 0; i < data.length - 1; i++) {
			assertEquals(data[i], result[i].doubleValue(), .000000001d);
		}
	}

	// this test covers empty values for variable data
	@Test
	public void createNumberArrayEmptyTest() {
		double[] data = {};
		Number[] result = DataUtilities.createNumberArray(data);

		assertEquals(0, result.length);
	}

	// this test covers invalid values for variable data
	@Test(expected = InvalidParameterException.class)
	public void createNumberArrayNullTest() {
		double[] data = null;
		DataUtilities.createNumberArray(data);
	}
	
	// this test covers maximum and minimum values for variable data
	@Test
	public void createNumberArrayMinMaxTest() {
		double[] data = { Double.MAX_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MAX_VALUE };
		Number[] result = DataUtilities.createNumberArray(data);

		assertEquals(data.length, result.length);

		for (int i = 0; i < data.length - 1; i++) {
			assertEquals(data[i], result[i].doubleValue(), .000000001d);
		}
	}
	
	// this test covers normal values for variable data
	@Test
	public void createNumberArray2DTest() {
		double[][] data = { { 1.0, -2.5, 3.5, 0 }, { -5.0, 6.5, 7.5, 8.5 } };
		Number[][] result = DataUtilities.createNumberArray2D(data);

		assertEquals(data.length, result.length);

		for (int i = 0; i < data.length - 1; i++) {
			
			assertEquals(data[i].length, result[i].length);

			for (int j = 0; j < data[i].length - 1; j++) {
				assertEquals(data[i][j], result[i][j].doubleValue(), .000000001d);
			}
		}
	}

	// this test covers empty values for variable data
	@Test
	public void createNumberArray2DEmptyTest() {
		double[][] data = {};
		Number[][] result = DataUtilities.createNumberArray2D(data);

		assertEquals(0, result.length);
	}

	// this test covers invalid values for variable data
	@Test(expected = InvalidParameterException.class)
	public void createNumberArray2DNullTest() {
		double[][] data = null;
		DataUtilities.createNumberArray2D(data);
	}
	
	// this test covers maximum and minimum values for variable data
	@Test
	public void createNumberArray2DMinMaxTest() {
		double[][] data = { { Double.MAX_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MAX_VALUE },
				{ Double.MAX_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MAX_VALUE } };
		Number[][] result = DataUtilities.createNumberArray2D(data);

		assertEquals(data.length, result.length);

		for (int i = 0; i < data.length - 1; i++) {
			
			assertEquals(data[i].length, result[i].length);

			for (int j = 0; j < data[i].length - 1; j++) {
				assertEquals(data[i][j], result[i][j].doubleValue(), .000000001d);
			}
		}
	}
	
//Testing createNumberArray() **************************************************************8

	// this test covers normal values for variable data
	@Test
	public void getCumulativePercentagesTest() {
		mockery.checking(new Expectations() {
			{
				allowing(keyedValues).getItemCount();
				will(returnValue(3));
				allowing(keyedValues).getValue(0);
				will(returnValue(5));
				allowing(keyedValues).getValue(1);
				will(returnValue(9));
				allowing(keyedValues).getValue(2);
				will(returnValue(2));
				allowing(keyedValues).getKey(0);
				will(returnValue(0));
				allowing(keyedValues).getKey(1);
				will(returnValue(1));
				allowing(keyedValues).getKey(2);
				will(returnValue(2));
			}
		});

		KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

		assertEquals(0.3125, result.getValue(0));
		assertEquals(0.875, result.getValue(1));
		assertEquals(1.0, result.getValue(2));
	}
	
	// this test covers empty values for variable data
	@Test
	public void getCumulativePercentagesEmptyTest() {
		mockery.checking(new Expectations() {
			{
				allowing(keyedValues).getItemCount();
				will(returnValue(0));
			}
		});

		KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

		assertEquals(0, result.getItemCount());
	}
	
	// the cumulative percentage of 0 should be 0%
	// this test covers normal values for variable data
	@Test
	public void getCumulativePercentagesZeroTest() {
		mockery.checking(new Expectations() {
			{
				allowing(keyedValues).getItemCount();
				will(returnValue(2));
				allowing(keyedValues).getValue(0);
				will(returnValue(0));
				allowing(keyedValues).getValue(1);
				will(returnValue(0));
				allowing(keyedValues).getKey(0);
				will(returnValue(0));
				allowing(keyedValues).getKey(1);
				will(returnValue(1));
			}
		});

		KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

		assertEquals(0.0, result.getValue(0));
		assertEquals(0.0, result.getValue(1));
	}

	// this test covers invalid values for variable data
	@Test(expected = InvalidParameterException.class)
	public void getCumulativePercentagesNullTest() {
		mockery.checking(new Expectations() {
			{
				allowing(keyedValues).getItemCount();
				will(returnValue(null));
			}
		});

		DataUtilities.getCumulativePercentages(keyedValues);

	}
	
	// this test covers invalid values for variable data
	@Test(expected = InvalidParameterException.class)
	public void getCumulativePercentagesExceptionTest() {
		mockery.checking(new Expectations() {
			{
				allowing(keyedValues).getItemCount();
				will(returnValue(2));
				allowing(keyedValues).getValue(0);
				will(returnValue(0));
				allowing(keyedValues).getValue(1);
				will(returnValue('a'));
				allowing(keyedValues).getKey(0);
				will(returnValue(0));
				allowing(keyedValues).getKey(1);
				will(returnValue(1));
			}
		});

		DataUtilities.getCumulativePercentages(keyedValues);

	}
	
	// this test covers maximum and minimum values for variable data
	@Test
	public void getCumulativePercentagesMinMaxTest() {
		mockery.checking(new Expectations() {
			{
				allowing(keyedValues).getItemCount();
				will(returnValue(2));
				allowing(keyedValues).getValue(0);
				will(returnValue(0));
				allowing(keyedValues).getValue(1);
				will(returnValue(Double.MAX_VALUE));
				allowing(keyedValues).getKey(0);
				will(returnValue(0));
				allowing(keyedValues).getKey(1);
				will(returnValue(1));
			}
		});

		KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

		assertEquals(0.0, result.getValue(0));
		assertEquals(1.0, result.getValue(1));
	}

	@After
	public void tearDown() throws Exception {
		mockery = null;
		values = null;
		keyedValues = null;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("DataUtilitiesTest completed.");
	}
}