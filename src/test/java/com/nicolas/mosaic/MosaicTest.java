package com.nicolas.mosaic;

import java.util.Arrays;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
* Unit test for simple App.
*/
public class MosaicTest extends TestCase {
    /**
    * Create the test case
    *
    * @param testName name of the test case
    */
    public MosaicTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(MosaicTest.class);
    }

    /**
    * Testing subMatrix function
    */
    public void testGetSubMatrix() {
        try {
            Mosaic mosaic = Mosaic.createMosaicFromFile("src/test/resources/testmosaic.in");
            char[][] cutPiece = mosaic.getSubMatrix(0,0,2,1);
            char [][] pieceMatrix = {
                {'W','W'},
                {'W','B'},
                {'W','W'}
            };
            assertTrue(Arrays.deepEquals(pieceMatrix,cutPiece));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * Testing creation function
    */
    public void testCreateMosaicFromFile() {
        try {
            Mosaic testMosaic = Mosaic.createMosaicFromFile("src/test/resources/testmosaic.in");
            char [][] expectedMatrix = {
                {'W','W','W','W','W','W'},
                {'W','B','B','B','B','W'},
                {'W','W','W','W','W','W'}
            };
            Mosaic expectedMosaic = new Mosaic(3, 6, 1, 6, expectedMatrix);
            assertEquals(testMosaic, expectedMosaic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * Testing cutting function
    */
    public void testCutPiece() {
        try {
            Mosaic mosaic = Mosaic.createMosaicFromFile("src/test/resources/testmosaic.in");
            char [][] pieceMatrix = {
                {'W','W'},
                {'W','B'},
                {'W','W'}
            };
            Piece cutPiece = new Piece(0,0,2,1,pieceMatrix);
            mosaic.cutPiece(cutPiece);
            assertTrue(Arrays.equals(mosaic.getMatrix()[0], new char[]{'C','C','W','W','W','W'}));
            assertTrue(Arrays.equals(mosaic.getMatrix()[1], new char[]{'C','C','B','B','B','W'}));
            assertTrue(Arrays.equals(mosaic.getMatrix()[2], new char[]{'C','C','W','W','W','W'}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}