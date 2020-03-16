package com.nicolas.mosaic;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;

/**
* Unit test for simple App.
*/
public class ProcessingTest extends TestCase {
    /**
    * Create the test case
    *
    * @param testName name of the test case
    */
    public ProcessingTest(String testName) {
        super(testName);
    }

    /**
    * @return the suite of tests being tested
    */
    public static Test suite() {
        return new TestSuite(ProcessingTest.class);
    }

    /**
    * Testing validation function
    */
    public void testProcessing() {
        try {
            Mosaic testMosaic = Mosaic.createMosaicFromFile("src/test/resources/testmosaic.in");
            ArrayList<Piece> resultList = Processing.cutMosaic(testMosaic);
            // Create expected pieces
            ArrayList<Piece> expectedList = new ArrayList<Piece>();
            char [][] piece1Matrix = {
                {'W','W'},
                {'W','B'},
            };
            Piece piece1 = new Piece(0,0,1,1,piece1Matrix);
            expectedList.add(piece1);
            char [][] piece2Matrix = {
                {'W'},
                {'B'},
            };
            Piece piece2 = new Piece(0,2,1,2,piece2Matrix);
            expectedList.add(piece2);
            char [][] piece3Matrix = {
                {'W'},
                {'B'},
            };
            Piece piece3 = new Piece(0,3,1,3,piece3Matrix);
            expectedList.add(piece3);
            char [][] piece4Matrix = {
                {'W'},
                {'B'},
            };
            Piece piece4 = new Piece(0,4,1,4,piece4Matrix);
            expectedList.add(piece4);
            // Compare lists of pieces
            assertEquals(expectedList,resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}