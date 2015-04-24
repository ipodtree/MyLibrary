import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;


public class Return extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象

	
	public Return() {
		super();
		try {
			
			parser parseur  = new parser();
			parseur.parse("bib.xml");
			
			
			
		setTitle("return");
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
		
		
		
		
		final JButton delButton = new JButton("return");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// 获得被选中行的索引
				if (selectedRow != -1)// 判断是否存在被选中行
					
					// 从表格模型当中删除指定行
					
				
				//delete from XML
				try {deletexml parseur  = new deletexml();
				parseur.parse("bib.xml",(String)table.getValueAt(selectedRow, 0));}
				catch (Exception e1 ){
					e1.printStackTrace();
				}
				
				//add to database
				try{
					
					MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
					
					DB db = mongoClient.getDB("library");
		
					DBCollection coll = db.getCollection("books");
					BasicDBObject doc = new BasicDBObject("ISBN", table.getValueAt(selectedRow, 0)).
					append("year", table.getValueAt(selectedRow, 1)).
					append("title",table.getValueAt(selectedRow, 2)).
					append("author", table.getValueAt(selectedRow, 3));
					coll.insert(doc);

					}catch(Exception e2){
					System.err.println( e2.getClass().getName() + ": " + e2.getMessage() );
					}
				tableModel.removeRow(selectedRow);
					
				
			}
		});
		panel.add(delButton);
		
		final JPanel panel2 = new JPanel();
		getContentPane().add(panel2, BorderLayout.NORTH);
		JLabel jl = new JLabel("Here is your book list, please choose one to return."); // 创建一个JLabel标签
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