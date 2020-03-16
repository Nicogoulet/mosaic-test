package com.nicolas.mosaic;

import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            // Create mosaic object from file
            Mosaic testMosaic = Mosaic.createMosaicFromFile("src/main/resources/mosaic.in");
            // Cut mosaic into valid piece
            ArrayList<Piece> testResults = Processing.cutMosaic(testMosaic);
            // Write those pieces to output file
            Processing.writeResultsToFile(testResults,"src/main/resources/mosaic.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}