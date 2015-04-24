import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;


public class LibraryBooks extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象

	
	public LibraryBooks() {
		super();
		try {
			
			
		setTitle("library books");
		setBounds(100, 100, 510, 375);
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		 MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         // Now connect to your databases
         DB db = mongoClient.getDB( "library" );

    
         DBCollection books = db.getCollection("books");

         
         List<DBObject> bookobject=new ArrayList<DBObject>();
         DBCursor cursor = books.find();
         while (cursor.hasNext()) { 
        	bookobject.add(cursor.next());
         }

     	String[] columnNames = { "ISBN", "Year", "Title", "Author" }; 
     	
         String[][] tableValues = new String[bookobject.size()][4];
			for (int i = 0; i < bookobject.size(); i++) { 
				 tableValues[i][0] = (String)bookobject.get(i).get("ISBN"); }
			 for (int i = 0; i < bookobject.size(); i++) { 
				 tableValues[i][1] = (String)bookobject.get(i).get("year"); }
			 for (int i = 0; i < bookobject.size(); i++) { 
				 tableValues[i][2] = (String)bookobject.get(i).get("title"); }
			 for (int i = 0; i < bookobject.size(); i++) { 
				 tableValues[i][3] = (String)bookobject.get(i).get("author"); }
		 
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