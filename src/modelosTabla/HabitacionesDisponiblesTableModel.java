package modelosTabla;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class HabitacionesDisponiblesTableModel extends AbstractTableModel {
	
	private Object[][] data = null;
	
	
	//This method defines how way the data is displayed
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}