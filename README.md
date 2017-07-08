# Message Processing Application

## Brief overview of some of the classes

 MessageProcessor:
  - Main class that is kind of the starting point for message processing
  - Based on the message type (type 1/type 2/type3), processes the message accordingly
  - Stores the computed data into the data store (in memory hash map)
  - Does the computation required for reporting purposes as and when the message is received, rather than leaving the computation to the end.
 
 InMemoryDataPersistenceImpl
  - This is an implementation class for the DataPersistenceInterface interface.
  - This implementation uses a concurrent hash map for the data store. Not that a concurrent hash map is required, since this is only expected to be a single threaded app.
 
 ReportGenerator
  - This class exposes static methods, that prints out the required reports. 

## Unit tests
Most of the unit tests are based on the above 3 classes. And the name of the unit tests are modelled to reflect their actual intention.

## Build tool used
 - Gradle.
 - Running gradlew build downloads the dependencies, compiles the class and runs the unit test.
 - "gradlew -i test" will run the tests alone
 
## Sample report from the unit tests
Intermediate report
```
Sale Type           # Sales   Total Value of Sales
Apple               2         10.0      
Orange              4         20.0      
Grapes              4         20.0   
```

Final report
```
-------------------------------------------------------
Adjustments for Sale Type: Apple
No adjustments made during the period
-------------------------------------------------------
-------------------------------------------------------
Adjustments for Sale Type: Orange
No adjustments made during the period
-------------------------------------------------------
-------------------------------------------------------
Adjustments for Sale Type: Grapes
Operation Amount    
ADD       10.0      
SUBTRACT  5.0       
SUBTRACT  5.0       
-------------------------------------------------------
```
