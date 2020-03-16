package com.nicolas.mosaic;

/**
* Represents a piece of the mosaic with the associated metadata
*/
public class Piece {
    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;
    private char[][] matrix;

    public Piece (int startRow, int startColumn, int endRow, int endColumn, char[][] matrix) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
        this.matrix = matrix;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    /* Check if piece is valid for selection: 
    * - number of cells is less than the max
    * - all cells are either balck or white
    * - mininum number of cells colors is met
    */
    public Boolean isValid(int minColorsNumber, int maxCellsNumber) {

        int rowsNumber = matrix.length;
        int columnsNumber = matrix[0].length;

        // Check Number of cells in piece
        if ( rowsNumber * columnsNumber > maxCellsNumber ) {
            return false;
        }
        // Count Number of black and white cells
        int whiteNumber = 0;
        int blackNumber = 0;
        int rowIndex = 0;
        int columnIndex = 0;
        for (rowIndex = 0; rowIndex < rowsNumber; rowIndex++) {
            for (columnIndex = 0; columnIndex < columnsNumber; columnIndex++) {
                if ( matrix[rowIndex][columnIndex] == 'W' ) {
                    whiteNumber++;
                }
                else if ( matrix[rowIndex][columnIndex] == 'B') {
                    blackNumber++;
                }
                else {
                    return false;
                }
            }
        }
        // Check the minimum number of colors
        if ( whiteNumber >= minColorsNumber && blackNumber >= minColorsNumber ) {
            return true;
        }
        else {
            return false;
        }
    }

    // Override of equals method to compare two pieces
    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }   
        Piece piece = (Piece) obj; 
        if (this.startRow != piece.getStartRow()) {
            return false;
        }
        if (this.startColumn != piece.getStartColumn()) {
            return false;
        }
        if (this.endRow != piece.getEndRow()) {
            return false;
        }
        if (this.endColumn != piece.getEndColumn()) {
            return false;
        }
        if(this.matrix.length != piece.getMatrix().length ) {
            return false;
        }
        if(this.matrix[0].length != piece.getMatrix()[0].length ) {
            return false;
        }
        for (int indexRow = 0; indexRow < this.matrix.length; indexRow++) {
            for (int indexColumn = 0; indexColumn < this.matrix[0].length; indexColumn++) {
                if (this.matrix[indexRow][indexColumn] != piece.matrix[indexRow][indexColumn]) {
                    return false;
                }
            }
        }
        return true;
    }
}