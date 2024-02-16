     ____  _____    _    ____  __  __ _____
    |  _ \| ____|  / \  |  _ \|  \/  | ____|
    | |_) |  _|   / _ \ | | | | |\/| |  _|
    |  _ <| |___ / ___ \| |_| | |  | | |___
    |_| \_\_____/_/   \_\____/|_|  |_|_____|

            Stroe Teodora, 321CA

## File guide

    .
    ├── .git
    ├── src
    │    ├── action
    │    │     - Action class: used to store each action from input
    │    │     - contains a sepparate class for each action type, each having static methods
    │    ├── actor
    │    │     - Actor class: used to store each actor input. In addition to the information
    │    │       from the ActorInputData class, it also retains the average rating calculated
    │    │       based on the average of each video in the filmography.
    │    │     - ActorsAwards: an enum for the award types an actor can have
    │    ├── checker
    │    │     - the checker's sources
    │    ├── common
    │    │     - Constants: contains constants used by the program
    │    ├── database
    │    │     - Database class: in which a copy of the input is stored. In its implementation,
    │    │       a lazy initialization of the Singleton Pattern is used.
    │    │     - Builder class: used for creating JSON objects and populating the result array,
    │    │       as well as the starting point for executing each action.
    │    ├── entertainment
    │    │     - Genre: an enum for the supported video genres
    │    │     - GenrePopularity class: is used for having a list with the popularity of each genre
    │    │     - Movie class: extends Video class, used for storing each movie from the input data.
    │    │       In addition to the information from the MovieInputData class, it also contains a 
    │    │       list of given ratings.
    │    │     - Season class: class for representing a Season of a tv show
    │    │     - Show class: extends Video class, used for storing each show from input data.
    │    │     - Video class: superclass for the Movie and Show classes. In addition to the fields
    │    │       given in the input classes, it can also retain the number of appearances in the
    │    │       user's favorites list, the total number of views and the position of the video in
    │    │       the database.
    │    ├── fileio
    │    │     - classes used for parsing the input files
    │    ├── main
    │    │     - Main class: used for testing the entire project. Contains the entry point of the
    │    │       implementation.
    │    │     - Test class: used for testing a single file
    │    ├── user
    │    │     - User class: used to store each user input. In addition to the information from the
    │    │       UserInputData class, it also contains a list of rated videos and the total number of
    │    │       given ratings.
    │    ├── utils
    │    │     - Counter class: contains static methods for increasing the favorite/view counter of a video
    │    │     - Sorter class: contains static methods used for sorting lists after certain criterias
    │    │     - Utils class: contains static methods that help with parsing and string modifying
    └── README.md

## Feedback
    PROS:
      - the deadline was very accessible
      - the topic was very interesting and helped me understand a practical use of the learned concepts
    
    CONS:
      - we were suggested to use certain concepts that have not yet been presented to us
      - the skeleton was very difficult to understand, we didn't have enough details in the README or
        enough comments in the code.
      - working with JSON libraries was quite difficult to understand at first, being a completely
        new thing.