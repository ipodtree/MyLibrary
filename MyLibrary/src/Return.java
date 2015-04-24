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
	private DefaultTableModel tableModel;// ������ģ�Ͷ���
	private JTable table;// ���������

	
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
		
		
		
		
		final JButton delButton = new JButton("return");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// ��ñ�ѡ���е�����
				if (selectedRow != -1)// �ж��Ƿ���ڱ�ѡ����
					
					// �ӱ��ģ�͵���ɾ��ָ����
					
				
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
		JLabel jl = new JLabel("Here is your book list, please choose one to return."); // ����һ��JLabel��ǩ
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