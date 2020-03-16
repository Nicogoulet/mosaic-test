package com.nicolas.mosaic;

import java.util.ArrayList;
import java.io.FileWriter;

/**
* Store the processing functionalities
*/
public class Processing {
    // Write array of pieces to result file
    public static void writeResultsToFile(ArrayList<Piece> results, String filename) {

        System.out.println("######## Writing results to file ########");
        try {
            FileWriter writer = new FileWriter(filename);
            // Write number of pieces on top of file
            writer.write(results.size() + System.lineSeparator());
            // Write all pieces to file
            for (Piece piece: results) {
                writer.write(piece.getStartRow() + " " + piece.getStartColumn() + " " + piece.getEndRow() + " " + piece.getEndColumn() + System.lineSeparator());
            }
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Cut mosaic into valid pieces
    public static ArrayList<Piece> cutMosaic(Mosaic mosaic) {
        ArrayList<Piece> returnList = new ArrayList<Piece>();
        int minColorsNumber = mosaic.getminColorCellsInPiece();
        int maxCellsNumber = mosaic.getmaxCellsInPiece();
        int rowsTotal = mosaic.getRowsTotal();
        int columnsTotal = mosaic.getColumnsTotal();

        // Iterate the processing on every cell of the matrix
        System.out.println("######## Processing Mosaic ########");
        for (int startRow = 0; startRow < rowsTotal; startRow++) {
            for (int startColumn = 0; startColumn < columnsTotal; startColumn++) {
                // Check if the starting cell is correct to start checking
                if (mosaic.getMatrix()[startRow][startColumn] == 'B' || mosaic.getMatrix()[startRow][startColumn] == 'W') {
                    // Check every possible array starting from the current cell
                    System.out.println("#### Processing is finding all valid pieces starting on the cell: " + startRow + "-" + startColumn + " ####");
                    for (int endRow = startRow; endRow < rowsTotal; endRow++) {
                        for (int endColumn = startColumn; endColumn < columnsTotal; endColumn++) {
                            // Check if the ending cell of the array is valid
                            if (mosaic.getMatrix()[endRow][endColumn] == 'B' || mosaic.getMatrix()[endRow][endColumn] == 'W') {
                                Piece tempPiece = new Piece(startRow, startColumn, endRow, endColumn, mosaic.getSubMatrix(startRow, startColumn, endRow, endColumn));
                                // Check if the new piece is valid and add it to the list 
                                if (tempPiece.isValid(minColorsNumber, maxCellsNumber)) {
                                    System.out.println("# Processing found a new valid piece: " + startRow + "-" + startColumn + " / " + endRow + "-" + endColumn +" #");
                                    returnList.add(tempPiece);
                                    // Remove the new piece from the existing matrix to avoid selecting the same cells in multiple pieces
                                    mosaic.cutPiece(tempPiece);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("######## Finished Processing ########");
        return returnList;
    }
}