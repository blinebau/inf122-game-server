package app.model;

import java.io.Serializable;

/**
 * Created by jgreene on 3/5/16.
 */
public class BoardIndex implements Serializable {
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
    
    @Override
    public boolean equals(Object o) {
    	if(!(o instanceof BoardIndex))
    		return false;
    	BoardIndex index = (BoardIndex)o;
    	return columnIndex == index.getColumnIndex() && rowIndex == index.getRowIndex();
    }

    public String toString() {
        return "(" + Integer.toString(columnIndex) + "," + Integer.toString(rowIndex) + ")";
    }
}
