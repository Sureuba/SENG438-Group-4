**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#:      | 4 |
| -------------- | --- |
| Student Names: | Uruba Alam|
|                | Naina Gupta|

# 1 Introduction

The goal of this assignment is to learn how to develop proper unit tests for a system under test. We will be focusing on black-box testing using Equivalence Class Testing (ECT) and Boundary Value Testing (BVT). We will be using JUnit along with JMock to create test cases.

# 2 Detailed description of unit test strategy

Re-reading lecture slides we created our testing plan based on black-box testing since we were not given the contents of the JFreeChart. Testing 5 methods from  the DataUtilities class, and chose 5 methods from the Range class that required no mocking. For the Range class we desided to do seperate in range and out range tests for all of the 5 functions as well as outlier tests for a few extra cases. We do not test the constructor since the initialization is before the test methods, assuming that the Range objects have no errors upon creation. 

**Class DataUtilities**
NOTE: Valid meaning valid input for the function being tested.
      Invalid meaning an invalid input for the function being tested (could be true or false         based on the expected output)
      
double calculateColumnTotal(Values2D data, int column)
- Data contains positive value, valid
- A2. Data contains negative value, valid
- A3. Data contains zero value, valid
- A4. Data contains null value, invalid
- A5. Data contains invalid data object, invalid

double calculateRowTotal(Values2D data, int row)
- B1. Data contains positive value, valid
- B2. Data contains negative value, valid
- B3. Data contains zero value, valid
- B4. Data contains null value, invalid
- B5. Data contains invalid data object, invalid

java.lang.Number[] createNumberArray(double[] data)
- C1. Data contains normal value, valid (contains positive, negative, and zero values)
- C2. Data is empty, valid
- C3. Data contains null value, invalid

java.lang.Number[][] createNumberArray2D(double[][] data)
- D1. Data contains normal value, valid (contains positive, negative, and zero values)
- D2. Data is empty, valid
- D3. Data contains null value, invalid

*Negative values in cumulative percentages makes no sense as we cannot have negative percentages.* <br />
KeyedValues getCumulativePercentages(KeyedValues data)
- E1. Data contains positive value, valid
- E2. Data is empty, valid
- E3. Data contains zero value, valid
- E4. Data contains null value, invalid
- E5. Data contains invalid data object, invalid

**Class Range**
(create testing table!)

double getCentralValue():
Returns the central (or median) value for the range.
- F1. Data contains positive value, valid 
- F2. Data contains negative value, valid
- F3. Data contains zero value, valid
- F4. Data contains decimal (double) value, valid

double getUpperBound():
Returns the upper bound for the range.
- G1. Data contains positive value, valid
- G2. Data contains negative value, valid
- G3. Data contains zero value, valid

double getLowerBound():
Returns the lower bound for the range.
- H1. Data contains positive value, valid
- H2. Data contains negative value, valid
- H3. Data contains zero value, valid

boolean contains(double value):
Returns true if the specified value is within the range and false otherwise.
- I1. Data contains positive value, valid
- I2. Data contains negative value, valid
- I3. Data contains zero value, valid
- I4. Value is within AUB of Range, invalid
- I5. Value is within BLB of Range, invalid
- I6. Edge Value is within bounds, valid


double constrain(double value):
Returns the value within the range that is closest to the specified value.
- J1. Data contains positive value, valid
- J2. Data contains negative value, valid
- J3. Data contains zero value, valid
- J4. Value is within AUB of Range, valid
- J5. Value is within BLB of Range, valid

*Since all methods uses double as their variable type, there is no real boundary other than testing the methods can handle the min and max values for the double data type unless otherwise specified*

- UB : Double.MAX_VALUE (upper bound value)
- NOM: anything between Double.MIN_VALUE and Double.MAX_VALUE (between range values)
- LB : Double.MIN_VALUE (lower bound value)

# 3 Test cases developed

Based on our test strategy, we have the follow test methods. Any parts of our actual code that are not included here are not important for our testing outcomes and wil be improvised when making the JMock tests on eclipse. The finite min and max values are from the built in Double class to test the limits of double variables.

**Class DataUtilities**

Testing **calculateColumnTotal()**

| Testing Function | Equivalent Classes | Expected Outcomes | 
| --- | --- | --- |
 calculateColumnTotalTest | Mockery table values: (0, 0): 7.5, (1,0): 2.5 , using col 0 | returns: 10 |
| calculateColumnTotalNegativeTest | Mockery table values: (0, 0): -7.5, (1,0): -7.5 , using col 0 | returns: -15 |
| calculateColumnTotalZeroTest | Mockery table values: (0, 0): 0, (1,0): 0 , using col 0 | returns: 0|
| calculateColumnTotalNullTest | Mockery table values: (0, 0): null, (1,0): null , using col 0 | returns: 0|
| calculateColumnTotalExceptionTest | Mockery table values: (0, 0): 'a', (1,0): 7.5 , using col 0 | returns: should call InvalidParameterException|
| calculateColumnTotalMinMaxTest | Mockery table values:  (0, 0): finite min value, (1,0): finite max value , using col 0 | returns: sum of max and min values|
| calculateColumnTotalColumnOneTest | Mockery table values (3 by 2):  (0, 1): 3.5, (1, 1): 4.5, (2, 1): 5.5, using col 1 | returns: 13.5|

Testing **calculateRowTotal()**

| Testing Function | Equivalent Classes | Expected Outcomes | 
| --- | --- | --- |
| calculateRowTotalTest | Mockery table values: (0, 0): 7.5, (0,1): 2.5 , using row 0 | returns: 10 |
| calculateRowTotalNegativeTest | Mockery table values: (0, 0): -7.5, (0,1): -7.5 , using row 0 | returns: -15 |
| calculateRowTotalZeroTest | Mockery table values: (0, 0): 0, (0,1): 0 , using row 0 | returns: 0 |
| calculateRowTotalNullTest | Mockery table values: (0, 0): null, (0,1): null , using row 0 | returns: 0|
| calculateRowTotalExceptionTest | Mockery table values: (0, 0): 'a', (0,1): 7.5 , using row 0 | returns: should call InvalidParameterException|
| calculateRowMinMaxTest | Mockery table values: (0, 0): finite min value, (0,1): finite max value , using row 0 | returns: sum of max and min values|
| calculateRowTotalRowOneTest | Mockery table values (2 by 3): (1, 0):3.5 , (1,1): 4.5 , (1,2): 5.5, using row 0 | returns: 13.5|

Testing **createNumberArray()**

| Testing Function | Equivalent Classes | Expected Outcomes | 
| --- | --- | --- |
| createNumberArrayTest | data = { 1.0, 2.5, -3.5, 4.5, 0.0 } | length: 5, for loop succeeds tests |
| createNumberArrayEmptyTest | data = {} | length: 0 |
| createNumberArrayNullTest | data = null | calls InvalidParameterException|
| createNumberArrayMinMaxTest | data = { using min and max values from Double built in class}; | length: compare data and result, for loop succeeds tests|
| createNumberArray2DTest | data = { { 1.0, -2.5, 3.5, 0 }, { -5.0, 6.5, 7.5, 8.5 } }; | length: dataLength, for loop succeeds tests for all rows and cols |
| createNumberArray2DEmptyTest |  data = {} | length: 0 |
| createNumberArray2DNullTest | data = null; | calls InvalidParameterException|
| createNumberArray2DMinMaxTest |  data = { 2D array using min and max values from Double built in class} | length: compare data and result, for loop succeeds tests |

Testing **getCumulativePercentages()**

| Testing Function | Equivalent Classes | Expected Outcomes | 
| --- | --- | --- |
| getCumulativePercentagesTest | key0: 5, key1: 9 key2: 2 | results key0: 0.3125, key1: 0.875, key2: 1.0 |
| getCumulativePercentagesEmptyTest |  items: 0 | results: 0  |
| getCumulativePercentagesZeroTest |  key0: 0, key1: 0 | results key0: 0.0, key1: 0.0 |
| getCumulativePercentagesNullTest |  key0: 5, key1: 9 key2: 2 | results key0: 0.3125, key1: 0.875, key2: 1.0 |
| getCumulativePercentagesExceptionTest | getCumulativePercentagesExceptionTest | E5; |
| getCumulativePercentagesMinMaxTest | getCumulativePercentagesMinMaxTest | E1; UB; LB = 0; |

**Class Range**
Testing **getCentralValue()**

| Testing Function | Equivalent Classes | Expected Outcomes | 
| --- | --- | --- |
| centralValueShouldBeZero | Between valid range values. Range: -1 to 1 | returns: 0 |
| centralValueShouldBePositive | Between valid range values. Range: 0 to 10 | returns: 5 |
| centralValueShouldBeDecimal | Between valid range values. Range: 1 to 10 | returns: 5.5 |
| centralValueShouldBeNegative | Between valid range values. Range: -10 to 0 | returns: -5 |
| getCentralValueMinMaxTest | Testing the finite ranges from built in Double class | returns: average (should succeed assert) |

Testing **contains()**

| Testing Function | Equivalent Classes | Expected Outcomes | 
| --- | --- | --- |
| containsEdgeValue | Between valid range values. Range: -1 to 1 | returns: True |
| containsZeroValue | Between valid range values. Range: -1 to 1 | returns: True|
| containsPositiveValue | Between valid range values. Range: 0 to 10 | returns: True|
| containsNegativeValue | Between valid range values. Range: -10 to 0 | returns: True|
| doesntContainAboveUpperBound | Between valid range values. Range: 0 to 10 | returns: False |
| doesntContainBelowLowerBound | Between valid range values. Range: 0 to 10 | returns: False |
| containsMinTest |Testing the finite ranges from built in Double class| returns: True|
| containsMaxTest |Testing the finite ranges from built in Double class | returns: True|

Testing **constrain()**

| Testing Function | Equivalent Classes | Expected Outcomes | 
| --- | --- | --- |
| constrainShouldBePositiveValue | Input between valid range values. Range: 0 to 10 | returns: 5.4 |
| constrainShouldBeNegativeValue | Input between valid range values. Range: -10 to 0 | returns: -5.4 
| constrainShouldBeZero | Input between valid range values. Range: 0 to 10 | returns: 10 |
| constrainShouldBeUpperBoundaryValue | Input not between valid range values. Range: 0 to 10 | returns: 10|
| constrainShouldBeLowerBoundaryValue |  Input not between valid range values. Range: 0 to 10 | returns: 0|
| constrainMaxTest |Testing the finite ranges from built in Double class | returns: finite Max value|
| constrainMinTest |Testing the finite ranges from built in Double class | returns: finite Min value|

Testing **getUpperBound()**

| Testing Function | Equivalent Classes | Expected Outcomes | 
| --- | --- | --- |
| getUpperBoundPositiveValue | Between valid range values. Range: 0 to 10  | returuns: 10 |
| getUpperBoundNegativeValue | Between valid range values. Range: 0 to 10  | returuns: 10 |
| getUpperBoundZeroValue | Between valid range values. Range: -10 to 0  | returuns: 0 |
| getUpperBoundMinMaxTest | Between valid range values. Range: from built in Double class  | returns: finite max value |

Testing **getLowerBound()**

| Testing Function | Equivalent Classes | Expected Outcomes | 
| --- | --- | --- |
| getLowerBoundPositiveValue | Between valid range values. Range: 1 to 10  | returuns: 1 |
| getLowerBoundNegativeValue | Between valid range values. Range: -10 to 0  | returuns: -10 |
| getLowerBoundZeroValue | Between valid range values. Range: 0 to 10  | returuns: 0 |
| getLowerBoundMinMaxTest | Between valid range values. Range: from built in Double class  | returns: finite min value |

Note on Mocking:

Mocking is an easy to use tool that allows us to simulate a response from another interface. The benefit is that it allowed us to set the return value to what we desired. In an integrated testing scenario, we needed to set up the interface which takes time and potentially introduces more bugs to our code than intended. One drawback we noticed is that the mocked object may not fully represent the behavior of the interface. Because we are only simulating certain responds we may miss other functions that could alter the result. Mocking is also hard to use. We feel this is introduced by black-box testing. We are trying to mimic a interface's response, but with no access to the source code, mocking becomes harder as we have to figure out what we need in the return type. For example, calculateColumnTotal(Values2D data, int column) does not tell us we need to return getRowCount() and getValue(int row, int column) from Values2D in order to make the calculations work. 

# 4 How the team work/effort was divided and managed

The testing was divided into 2 teams. Two people were in charge of testing DataUtilities, and the other two tested Range. Since we already developed the testing cases, it was relatively easy to convert them into codes. This division allowed us to work independently with each other. We could push and pull requests from our git repo without any conflicts. After all test cases were created, we looked at each other's code and made adjustments as needed.

We learned that checking other's work is important, even when the code itself is easy to understand. Some test cases only have a few lines of code. However, during peer review we found multiple mistakes where the test cases were not following our test strategies. Some mistakes were assertion errors, some were caused by errors in initializing variables and mocks. Almost all errors were logic error which makes them hard to detect.

# 5 Difficulties encountered, challenges overcome, and lessons learned

One difficulty we faced was how to setup our project in Eclipse and make it work across different machines. Following the tutorial, the JAR files were initially set up as external libraries. We would get an error saying Eclipse cannot find these JAR files. Then we realized that they needed to be set up as libraries and included in the Eclipse project.

Another challenge, as mentioned above, is to understand how the method works without the access to source code. This issue is not relevant in Range class as our testing cases did not require mocking. In DataUtilities class however, it took some time to get used to. Different methods may require different return types, and may call different methods from the mocked interface. All we can do is trail and error to figure out how to implement it.

We learned that unit testing requires a lot of planning beforehand. If we just starts to write testing codes without a strategy, we could be missing a lot of testing cases and boundary conditions. The test cases will be messy and disorganized. Unit testing in our previous classes only needed us to write a couple test cases, and because we wrote the code, the unit tests were easy to write. In this assignment we are only giving documentations on the methods and we needed to learn how to develop test cases in a systematic way.

# 6 Comments/feedback on the lab itself

As mentioned above, the tutorial section for how to setup the JAR files was not applicable to this assignment. It would be better if the instructions are on how to use the JAR files in a shared project setting. Another feedback is the documentations of certain methods. For example, calculateColumnTotal says for the data parameter "null not permitted", but it also mentions that "With invalid input, a total of zero will be returned", and throws "InvalidParameterException - if invalid data object is passed in." It is not abundantly clear what should happen if a null value is passed it. Should it displays zero or throws an exception? Is null considered an invalid parameter? We argue this is left to our interpretation and could result in incorrect testing results.
