/*
 * Due: Monday Feb 12th, 2024
 * Completed By: Naina and Uruba
 * Testing Range
 */

package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exRange;
    private Range exRange2;
    private Range exRange3;
    private Range exRange4;
    private Range exRange5;
    private Range minMaxRange;
    
    @BeforeClass 
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception { 
    	//negative to positive ranges
    	exRange = new Range(-1, 1);
    	
    	//zero to positive ranges
    	exRange2 = new Range(0, 10);
    	
    	//completely positive ranges
    	exRange3 = new Range(1, 10);
    	
    	//zero to negative range
    	exRange4 = new Range(-10, 0);
    	
    	//completely negative ranges
    	exRange5 = new Range(-50, -10);
    	
    	//min and max range values
    	minMaxRange = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
    	
    }

    //Test getCentralValue() function ******************************************************************************************
    @Test     
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exRange.getCentralValue(), .000000001d);
    }
    @Test
    public void centralValueShouldBePositive() {
    	assertEquals("The central value of 0 and 10 should be 5",
    	        5, exRange2.getCentralValue(), .000000001d);
    }
    @Test
    public void centralValueShouldBeDecimal() {
    	assertEquals("The central value of 1 and 10 should be 5.5",
    	       5.5 , exRange3.getCentralValue(), .000000001d);
    }
    @Test
    public void centralValueShouldBeNegative() {
    	assertEquals("The central value of -10 and 0 should be -5",
    	        -5, exRange4.getCentralValue(), .000000001d);
    }
    @Test
    public void getCentralValueMinMaxTest() {
    	double avg = (Double.MAX_VALUE + Double.MIN_VALUE)/2;
    	
        assertEquals("The central value of MIN_VALUE and MIN_VALUE should be " + avg,
        		avg, minMaxRange.getCentralValue(), .000000001d);
    }

    //Testing contains() function *******************************************************************************
    @Test
    public void containsEdgeValue() {assertTrue(exRange.contains(-1)); }
    
    @Test
    public void containsZeroValue() {assertTrue(exRange.contains(0)); }
    
    @Test
    public void containsNegativeValue() {assertTrue(exRange4.contains(-6)); }
    
    @Test
    public void containsPositiveValue() {assertTrue(exRange2.contains(5)); }
    
    @Test
    public void doesntContainAboveUpperBound() {assertFalse(exRange2.contains(11));	}
    
    @Test
    public void doesntContainBelowLowerBound() {assertFalse(exRange2.contains(-2)); }
    
    @Test
    public void containsMinTest() {
    	assertTrue("Value " + Double.MIN_VALUE + " is within " + Double.MIN_VALUE + " and " + Double.MAX_VALUE,  minMaxRange.contains(Double.MIN_VALUE));
    }
    
    @Test
    public void containsMaxTest() {
    	assertTrue("Value " + Double.MAX_VALUE + " is within " + Double.MIN_VALUE + " and " + Double.MAX_VALUE,  minMaxRange.contains(Double.MAX_VALUE));

    }
    //Testing constrain() Function *******************************************************************************        
    
    @Test
    public void constrainShouldBePositiveValue() {
    	assertEquals("The constrain value of 5.4 in range 0 to 10 should be 5.4",
    	        5.4, exRange2.constrain(5.4), .000000001d);
    }
    
    @Test
    public void constrainShouldBeNegativeValue() {
    	assertEquals("The constrain value of -5.4 in range -10 to 0 should be -5.4",
    	        -5.4, exRange4.constrain(-5.4), .000000001d);
    }
    
    @Test
    public void constrainShouldBeZero() {
    	assertEquals("The constrain value of 0 in range 0 to 10 should be 0",
    	        0, exRange2.constrain(0), .000000001d);
    }
    
    @Test
    public void constrainShouldBeUpperBoundaryValue() {
    	assertEquals("The constrain value of 100 in range 0 to 10 should be 10",
    	        10, exRange2.constrain(100), .000000001d);
    }
    @Test
    public void constrainShouldBeLowerBoundaryValue() {
    	assertEquals("The constrain value of -50 in range 0 to 10 should be 0",
    	        0, exRange2.constrain(-50), .000000001d);
    }
    
    @Test
    public void constrainMaxTest() {
    	assertEquals("The closed value from (" + Double.MIN_VALUE + ", " +  Double.MAX_VALUE + ") to " + Double.MAX_VALUE + " is " + Double.MAX_VALUE,
    			Double.MAX_VALUE, minMaxRange.constrain(Double.MAX_VALUE), .000000001d);
    	}
    @Test
    public void constrainMinTest() {
    	assertEquals("The closed value from (" + Double.MIN_VALUE + ", " +  Double.MAX_VALUE + ") to " + Double.MIN_VALUE + " is " + Double.MIN_VALUE,
    			Double.MIN_VALUE, minMaxRange.constrain(Double.MIN_VALUE), .000000001d);

        
    }
    
    //Testing double getUpperBound() Function *******************************************************************************
    @Test
    public void getUpperBoundPositiveValue() {
    	assertEquals("The upper bound in range 0 to 10 is 10",
    	        10, exRange2.getUpperBound(), .000000001d);
    }
    @Test
    public void getUpperBoundNegativeValue() {
    	assertEquals("The upper bound in range -50 to -10 is -10",
    	        -10, exRange5.getUpperBound(), .000000001d);
    }
    @Test
    public void getUpperBoundZeroValue() { 
    	assertEquals("The upper bound in range -10 to 0 is 0", 
    			0, exRange4.getUpperBound(), .000000001d);
    }
    @Test
    public void getUpperBoundMinMaxTest() {
    	assertEquals("The upperbound of MIN_VALUE and MIN_VALUE should be " + Double.MAX_VALUE, 
    			Double.MAX_VALUE, minMaxRange.getUpperBound(), .000000001d);
    }
    //Testing getLowerBound() Function *******************************************************************************
    
    @Test
    public void getLowerBoundPositiveValue() {
    	assertEquals("The lower bound in range 1 to 10 is 1",
    	        1, exRange3.getLowerBound(), .000000001d);
    }
    @Test
    public void getLowerBoundNegativeValue() {
    	assertEquals("The lower bound in range -10 to 0 is -10",
    	        10, exRange4.getLowerBound(), .000000001d);
    }
    @Test
    public void getLowerBoundZeroValue() {
    	assertEquals("The lower bound in range 0 to 10 is 0",
    	        0, exRange2.getLowerBound(), .000000001d);
    }
    @Test
    public void getLowerBoundMinMaxTest() {
    	assertEquals("The lowerbound of MIN_VALUE and MIN_VALUE should be " + Double.MIN_VALUE, 
    			Double.MIN_VALUE, minMaxRange.getLowerBound(), .000000001d);
    }
    
    @After
    public void tearDown() throws Exception {
    	 exRange = null;
    	 exRange2 = null;
    	 exRange3 = null;
    	 exRange4 = null;
    	 exRange5 = null;
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    	System.out.println("All Range Testing Completed!");
    }
    
}
