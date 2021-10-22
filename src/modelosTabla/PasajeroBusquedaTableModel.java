package modelosTabla;

import javax.swing.table.AbstractTableModel;

public class PasajeroBusquedaTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Apellido", "Nombre", "Tipo Documento", "Número"};
	private Object[][] data = null;
	
	
	//This method defines how way the data is displayed
	public Class getColumnClass(int c) {
		return getValueAt(0,c).getClass();
	}
	
	public void setData(Object[][] _data) {
		this.data = _data;
	}
	
	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	 public String getColumnName(int columnIndex) {
	    return columnNames[columnIndex];
	 }


}