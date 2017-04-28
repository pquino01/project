
package controller;


import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pablo
 */
public class ProductsTableModel extends AbstractTableModel {

    private String[] columns;
    private Object[][] rows;
   

    public ProductsTableModel() {
    }

    public ProductsTableModel(Object[][] data, String[] columnName) {
        this.rows = data;
        this.columns = columnName;
    }

    public void removeRow(int row) {
        for(int i=0;i<columns.length;i++){
            rows[row][i]=null;
        }
    }

    public Class getColumnClass(int column) {
        if (column == 0) {
            return Icon.class;
        } else {
            return getValueAt(0, column).getClass();
        }
    }

    public int getRowCount() {
        return rows.length;
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }

    public String getColumnName(int col) {
        return columns[col];
    }

}
