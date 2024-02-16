Copyright Teodora Stroe 321CA 2022

## Repository folder structure

The structure of the current project is as follows:
```
    .
    ├── .git
    ├── src
    │    ├── checker    [1]
    │    ├── common     [2]
    │    ├── databases  [3]
    │    ├── entities   [4]
    │    ├── enums      [5]
    │    ├── fileio     [6]
    │    ├── main       [7]
    │    ├── simulation [8]
    │    └── strategies [9]
    └── README.md
```
* **[1]** - package containing the checker files
* **[2]** - package containing used constants
* **[3]** - package containing both the Input Database and the Main Database   
* **[4]** - package containing the classes used for children, updates, gifts and the initial data
* **[5]** - package containing enum classes used for constants
* **[6]** - package containing classes used for reading / writing the input / output
* **[7]** - package containing classes used for testing
* **[8]** - package containing classes used for executing the program
* **[9]** - package containing the used strategies

## Phase 1

### Implementation
The program uses 2 methods of testing:
* Main class - used for testing the implementation on a set of 25 tests;
* Test class - used to individually run the program, on a specific input file.

The program uses 2 databases:
* Input - retains **all** the input data;
* Database - retains the data used in the current round.

The program uses 2 entities for *reading* and *writing* the data:
* InputLoader - parses the input data into the Input Database
* OutputLoader - writes the result data into the correct output file

The **entry point** of the program's execution is the Manager class, which performs the following
operations:
* performs a **standard execution** for Round 0;
* for each of the following years:
  * updates the database;
  * performs a standard execution for the current round;

A **standard execution** involves the *actual simulation* of the round and the *writing of the current
data* into the OutputLoader.

### Pattern usage

A lazy implementation of the **Singleton Pattern** was used for the InputLoader, OutputLoader and the
databases, because the program uses a single instance of each class in various places across the
implementation.

A combo of the **Strategy and Factory Patterns** was used in implementing the algorithm used to compute
the average score based on the child type.

## Feedback

* Working with JSON files and having to create the necessary tools to read / write in such files
presented the main challenge of the project.
* There were untreated corner cases in the project documentation.

## License

Licensed under the [MIT](LICENSE) License.
