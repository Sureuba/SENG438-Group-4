**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#:      | 4 |
| -------------- | --- |
| Student Names: | Uruba Alam|
|                | Naina Gupta|

# 1 Introduction

The goal of this assignment is to learn how to use black box testing methodolies for Range Class Testing and DataUtiltiies Class Testing through developing proper unit tests for a system under test. We focused on Equivalence Class Testing (ECT) and Boundary Value Testing (BVT). We also used JUnit along with JMock to create test cases.

# 2 Detailed description of unit test strategy

Re-reading lecture slides, we created our testing plan based on black-box testing since we were not given the contents of the JFreeChart. Testing 5 methods from  the DataUtilities class, and chose 5 methods from the Range class that required no mocking. For the Range class we desided to do seperate in range and out range tests for all of the 5 functions as well as outlier tests for a few extra cases. We do not test the constructor since the initialization is before the test methods, assuming that the Range objects have no errors upon creation.


**Developing Test Cases**

Based on our test strategy, we have the follow test methods. Any parts of our actual code that are not included here are not important for our testing outcomes and wil be improvised when making the JMock tests on eclipse. The finite min and max values are from the built in Double class to test the limits of double variables.



**Class DataUtilities Test Plan**


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

Testing **createNumberArray2D()**

| Testing Function | Equivalent Classes | Expected Outcomes | 
| --- | --- | --- |
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
| getCumulativePercentagesNullTest |  items: null | InvalidParameterException and returns: null for getItemCount|
| getCumulativePercentagesExceptionTest | key0: 0, key1: 'a' | calls InvalidParameterException |
| getCumulativePercentagesMinMaxTest |  key0: 0, key1: finite max value | returns key0: 0.0, key1: 1.0 |






**Class Range Test Plan**

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


Benefits & Drawbacks of Mocking:

Mocking presents both benefits and drawbacks. On the positive side, it enabled isolation, which allowed us to test individual units of code without relying on external dependencies that might be slow or unreliable. This leads to faster test execution and simpler debugging. Mocking also allowed us to create scenarios that are difficult to replicate in real-world conditions, ensuring thorough test coverage. However, we found that mocking can lead to overlooking edge cases or misunderstanding how our code interacts with real-world dependencies. Excessive mocking can also make it harder to understand the true dependencies and flow of our system, potentially creating weak tests that break easily with code changes. Ultimately, mocking can accelerate test development and ensure quality, but it’s also important to validate the tests created against real-world scenarios to guarantee the application functions as required. 

# 4 How the team work/effort was divided and managed

The teamwork was divided up between our pair. We both decided to do DataUtilities tests and Range tests so we can simultaneously learn how to conduct both types of testing strategies. We evenly split the testing, worked independently and then came to peer review one another’s tests to catch any issues the other member missed. We used a peer-peer coding strategy.

Through this lab, we learned that peer reviewing each other’s testing code is very important as it improves the overall clarity of each test. We were able to catch each other's bugs and improve the quality of one another's tests. Managing everything was not difficult as we collaborated together and communicated well amongst one another.


# 5 Difficulties encountered, challenges overcome, and lessons learned

Using a new IDE was a difficulty we encountered. Since we are both VS Code users, learning how to use and initially set up Eclipse with all the JAR files was a challenge. Another challenge we encountered was when we first started coding the unit tests. Testing is different from writing a regular program, and is something we were not used to so it took time to absorb the new concepts. 

Range testing was intuitive, we were able to code out the scenarios from our planning phase quickly. We had done some variation for boundary testing in the past so it was familiar to us. However, DataUtilities was a completely brand new concept. Learning how to use JMock, creating mockeries, and making tests without the code was definitely a challenge, but with patience we were able to successfully complete it. 

Through this process, we now have a better understanding of black box testing methodologies. We have also learned the value of planning your tests beforehand. That made the process of creating the Unit Tests a lot smoother, and it is something we will implement moving forward as software engineers. 


# 6 Comments/feedback on the lab itself

This lab was beneficial and helped us extensively explore the different kinds of black boxing methodologies. It was challenging, but doable. However, the lab document can be overwhelming to look at so feedback would be to change the formatting of the lab. There is too much information all at once, and due to that, it takes a really long time just to understand what to do. 
