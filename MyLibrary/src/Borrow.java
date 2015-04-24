import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;


public class Borrow extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象

	
	public Borrow() {
		super();
		try {
			
			
		setTitle("borrow");
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
		
		
		
		
		final JButton delButton = new JButton("borrow");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// 获得被选中行的索引
				if (selectedRow != -1)// 判断是否存在被选中行
					
					// 从表格模型当中删除指定行
					
				
				//delete from db
				try {   
				MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		        DB db = mongoClient.getDB( "library" );
		        DBCollection books = db.getCollection("books");

		        List<DBObject> bookobject=new ArrayList<DBObject>();
		         DBCursor cursor = books.find();
		         while (cursor.hasNext()) { 
		        	bookobject.add(cursor.next());
		         }
		         int x = 0;
		         for (int i = 0; i < bookobject.size(); i++) { 
					  if((boolean)bookobject.get(i).get("ISBN").equals(table.getValueAt(selectedRow, 0)))
					  {x=i;};
					  }
		         DBCursor cursor2 = books.find();
		         int j=0;
		         while (j<x) { 
		        	cursor2.next();
		        	j++;
		         }
		         books.remove(cursor2.next());
		        }
				catch(Exception e1){
				     System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
				  }
				
				
				//add to xml
				try {
				addxml parseur  = new addxml();
				parseur.parse("bib.xml",(String)table.getValueAt(selectedRow, 0),(String)table.getValueAt(selectedRow, 1),(String)table.getValueAt(selectedRow, 2),(String)table.getValueAt(selectedRow, 3));
				}
				catch (Exception e2 ){
					e2.printStackTrace();
				}
				
				tableModel.removeRow(selectedRow);
		}
		});
		panel.add(delButton);
		
		
		final JPanel panel2 = new JPanel();
		getContentPane().add(panel2, BorderLayout.NORTH);
		JLabel jl = new JLabel("Here is the book list in the library, please choose one to borrow."); // 创建一个JLabel标签
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