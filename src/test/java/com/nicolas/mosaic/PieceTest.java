package com.nicolas.mosaic;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
* Unit test for simple App.
*/
public class PieceTest extends TestCase {
    /**
    * Create the test case
    *
    * @param testName name of the test case
    */
    public PieceTest(String testName) {
        super(testName);
    }

    /**
    * @return the suite of tests being tested
    */
    public static Test suite() {
        return new TestSuite(PieceTest.class);
    }

    /**
    * Testing validation function
    */
    public void testPieceIsValid() {
        try {
            //test valid piece
            char [][] validMatrix = {
                {'W','W'},
                {'W','B'},
                {'W','W'}
            };
            Piece validPiece = new Piece(0,0,2,1,validMatrix);
            assertTrue(validPiece.isValid(1, 6));
            //test piece with invalid values
            char [][] invalidColorMatrix = {
                {'W','W'},
                {'W','B'},
                {'W','C'}
            };
            Piece invalidPiece = new Piece(0,0,2,1,invalidColorMatrix);
            assertFalse(invalidPiece.isValid(1, 6));
            //test piece with invalid dimensions
            char [][] invalidSizeMatrix = {
                {'W','W','W'},
                {'W','B','B'},
                {'W','W','W'}
            };
            invalidPiece = new Piece(0,0,2,1,invalidSizeMatrix);
            assertFalse(invalidPiece.isValid(1, 6));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}