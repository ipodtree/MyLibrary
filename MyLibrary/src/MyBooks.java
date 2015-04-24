import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;


public class MyBooks extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// ������ģ�Ͷ���
	private JTable table;// ���������

	
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
			 tableValues[i][3] = parseur.listauthor.get(i); }// ��������������
		 
		// ����ָ����������ͱ�����ݵı��ģ��
		tableModel = new DefaultTableModel(tableValues, columnNames);
		
		table = new JTable(tableModel);// ����ָ�����ģ�͵ı��
		table.setRowSorter(new TableRowSorter<>(tableModel));// ���ñ���������
		// ���ñ���ѡ��ģʽΪ��ѡ
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		
		final JPanel panel2 = new JPanel();
		getContentPane().add(panel2, BorderLayout.NORTH);
		JLabel jl = new JLabel("You can click the ColumnNames to sort the books up or down."); // ����һ��JLabel��ǩ
		// ʹ��ǩ�ϵ����־���
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(jl);
		
		setVisible(true);
	}
		catch (Exception e ){
			e.printStackTrace();
		}
	}
}