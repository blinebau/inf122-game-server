package app.model;

/**
 * Created by jgreene on 3/5/16.
 */
public class BoardIndex {
    private int columnIndex;
    private int rowIndex;

    public BoardIndex(int columnIndex, int rowIndex) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public String toString() {
        return "(" + Integer.toString(columnIndex) + "," + Integer.toString(rowIndex) + ")";
    }
}
