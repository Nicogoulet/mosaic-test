package com.nicolas.mosaic;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
* Represents a mosaic with the associated metadata
*/
public class Mosaic {
    private int rowsTotal;
    private int columnsTotal;
    private int minColorCellsInPiece;
    private int maxCellsInPiece;
    private char[][] matrix;

    public Mosaic (int rowsTotal, int columnsTotal, int minColorCellsInPiece, int maxCellsInPiece,char[][] matrix) {
        this.rowsTotal = rowsTotal;
        this.columnsTotal = columnsTotal;
        this.minColorCellsInPiece = minColorCellsInPiece;
        this.maxCellsInPiece = maxCellsInPiece;
        this.matrix = matrix;
    }

    public int getRowsTotal() {
        return rowsTotal;
    }

    public int getColumnsTotal() {
        return columnsTotal;
    }

    public int getminColorCellsInPiece() {
        return minColorCellsInPiece;
    }

    public int getmaxCellsInPiece() {
        return maxCellsInPiece;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] newMatrix) {
        matrix = newMatrix;
    }

    // Return portion of the matrix according to coordinates
    public char[][] getSubMatrix(int startRow, int startColumn, int endRow, int endColumn) {
        char[][] subMatrix = new char[endRow - startRow + 1][endColumn - startColumn + 1];
        for (int rowIndex = startRow; rowIndex <= endRow; rowIndex++) {
            for (int columnIndex = startColumn; columnIndex <= endColumn; columnIndex++) {
                subMatrix[rowIndex - startRow][columnIndex - startColumn] = matrix[rowIndex][columnIndex];
            }
        }
        return subMatrix;
    }

    // Read mosaic metadata and matrix from file
    public static Mosaic createMosaicFromFile(String fileName) throws IOException {
        // Create scanner for the file
        Scanner fileScanner = new Scanner(Paths.get(fileName));
        fileScanner.useDelimiter(System.getProperty("line.separator"));
        // Create scanner for the first line
        Scanner metadataScanner = new Scanner(fileScanner.next());
        // Scan the first line for metadata
        metadataScanner.useDelimiter(" ");
        int rowsTotal = metadataScanner.nextInt();
        int columnsTotal = metadataScanner.nextInt();
        int minColorCellsInPiece = metadataScanner.nextInt();
        int maxCellsInPiece = metadataScanner.nextInt();
        metadataScanner.close();
        // Scan the rest of the file to load the matrix
        int matrixIndex = 0;
        char [][] matrix = new char[rowsTotal][columnsTotal];
        while(fileScanner.hasNext()) {
            matrix[matrixIndex] = fileScanner.next().toCharArray();
            matrixIndex++;
        }
        //Close scanner and return new Mosaic
        fileScanner.close();
        return new Mosaic(rowsTotal,columnsTotal,minColorCellsInPiece,maxCellsInPiece,matrix);
    }

    //Write C character in place of piece in matrix to avoid selecting a cell multiple times
    public void cutPiece(Piece piece) {
        for (int matrixRow = piece.getStartRow(); matrixRow <= piece.getEndRow(); matrixRow++) {
            for (int matrixColumn = piece.getStartColumn(); matrixColumn <= piece.getEndColumn(); matrixColumn++) {
                matrix[matrixRow][matrixColumn] = 'C';
            }
        }
    }

    // Override of equals method to compare two mosaics
    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) {
            return false; 
        }
        Mosaic mosaic = (Mosaic) obj; 
        if (this.rowsTotal != mosaic.getRowsTotal()) {
            return false;
        }
        if (this.columnsTotal != mosaic.getColumnsTotal()) {
            return false;
        }       
        if (this.minColorCellsInPiece != mosaic.getminColorCellsInPiece()) {
            return false;
        }
        if (this.maxCellsInPiece != mosaic.getmaxCellsInPiece()) {
            return false;
        }
        if(this.matrix.length != mosaic.getMatrix().length ) {
            return false;
        }
        if(this.matrix[0].length != mosaic.getMatrix()[0].length ) {
            return false;
        }
        for (int indexRow = 0; indexRow < this.matrix.length; indexRow++) {
            for (int indexColumn = 0; indexColumn < this.matrix[0].length; indexColumn++) {
                if (this.matrix[indexRow][indexColumn] != mosaic.matrix[indexRow][indexColumn]) {
                    return false;
                }
            }
        }
        return true;
    }
}