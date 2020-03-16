### Mosaic Test
This application processes an input file containing a mosaic, processes it in order to cut it into unique separate pieces and output the results in the same folder.

## Use
* `mvn clean install` to run the unit tests and build the app
* `java -jar target/mosaic-test-1.jar` to launch the app

## Structure
* `App.java`: Contains the main function. Load the input file, process it and return the results in the output file
* `Mosaic.java`: Contains the Mosaic class representing a mosaic and the associated metadata
* `Piece.java`: Contains the Piece class representing a piece cut from the mosaic and the associated metadata
* `Processing.java`: Contains the functions necessary to cut the mosaic in pieces and to return the results to a file