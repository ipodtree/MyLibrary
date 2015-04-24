import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;


public class MyBooks extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象

	
	public MyBooks() {
		super();
		try {
			
			parser parseur  = new parser();
			parseur.parse("bib.xml");
			
			
			
		setTitle("my books");
		setBounds(100, 100, 510, 375);
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "ISBN", "Year", "Title", "Author" }; 
		String[][] tableValues = new String[parseur.listyear.size()][4];
		for (int i = 0; i < parseur.listyear.size(); i++) { 
			 tableValues[i][0] = parseur.listisbn.get(i); }
		 for (int i = 0; i < parseur.listyear.size(); i++) { 
			 tableValues[i][1] = parseur.listyear.get(i); }
		 for (int i = 0; i < parseur.listyear.size(); i++) { 
			 tableValues[i][2] = parseur.listtitle.get(i); }
		 for (int i = 0; i < parseur.listyear.size(); i++) { 
			 tableValues[i][3] = parseur.listauthor.get(i); }// 定义表格数据数组
		 
		// 创建指定表格列名和表格数据的表格模型
		tableModel = new DefaultTableModel(tableValues, columnNames);
		
		table = new JTable(tableModel);// 创建指定表格模型的表格
		table.setRowSorter(new TableRowSorter<>(tableModel));// 设置表格的排序器
		// 设置表格的选择模式为单选
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		
		final JPanel panel2 = new JPanel();
		getContentPane().add(panel2, BorderLayout.NORTH);
		JLabel jl = new JLabel("You can click the ColumnNames to sort the books up or down."); // 创建一个JLabel标签
		// 使标签上的文字居中
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(jl);
		
		setVisible(true);
	}
		catch (Exception e ){
			e.printStackTrace();
		}
	}
}