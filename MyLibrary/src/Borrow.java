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
	private DefaultTableModel tableModel;// ������ģ�Ͷ���
	private JTable table;// ���������

	
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
		 
		// ����ָ����������ͱ�����ݵı��ģ��
		tableModel = new DefaultTableModel(tableValues, columnNames);
		
		table = new JTable(tableModel);// ����ָ�����ģ�͵ı��
		table.setRowSorter(new TableRowSorter<>(tableModel));// ���ñ���������
		// ���ñ���ѡ��ģʽΪ��ѡ
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		
		
		
		final JButton delButton = new JButton("borrow");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// ��ñ�ѡ���е�����
				if (selectedRow != -1)// �ж��Ƿ���ڱ�ѡ����
					
					// �ӱ��ģ�͵���ɾ��ָ����
					
				
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
		JLabel jl = new JLabel("Here is the book list in the library, please choose one to borrow."); // ����һ��JLabel��ǩ
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