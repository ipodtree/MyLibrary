import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class MyJFrame extends JFrame {
	private static final long serialVersionUID = 1L;

// ����һ����̳�JFrame��
	public void CreateJFrame(String title) { // ����һ��CreateJFrame()����
		JFrame jf = new JFrame(title); // ʵ����һ��JFrame����
		jf.setVisible(true); // ʹ�������
		jf.setBounds(0, 0, 600, 400);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// ���ô���رշ�ʽ
		
		Container container = jf.getContentPane(); // ��ȡһ������
		container.setLayout(null);
		container.setBackground(Color.white);//���������ı�����ɫ
		
		JLabel jl = new JLabel("Welcome to your library"); // ����һ��JLabel��ǩ
		// ʹ��ǩ�ϵ����־���
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setBounds(195, 25, 200, 50);
		container.add(jl);
		
		
		
		JButton b1 = new JButton("my books"); // ����һ����ť
		b1.addActionListener(new ActionListener() { // Ϊ��ť�����굥���¼�
					public void actionPerformed(ActionEvent e) {
						//open mybooks
						new MyBooks();	
					}
				});
		b1.setBounds(100, 100, 180, 80);
		container.add(b1);
	
		
		JButton b2 = new JButton("borrow"); // ����һ����ť
		b2.addActionListener(new ActionListener() { // Ϊ��ť�����굥���¼�
					public void actionPerformed(ActionEvent e) {
						// open borrow
						new Borrow();
					}
				});
		b2.setBounds(300, 220, 180, 80);
		container.add(b2);
		
		JButton b3 = new JButton("return"); // ����һ����ť
		b3.addActionListener(new ActionListener() { // Ϊ��ť�����굥���¼�
					public void actionPerformed(ActionEvent e) {
						//open return
						new Return();
					}
				});
		b3.setBounds(100, 220, 180, 80);
		container.add(b3);
		
		JButton b4 = new JButton("library books"); // ����һ����ť
		b4.addActionListener(new ActionListener() { // Ϊ��ť�����굥���¼�
					public void actionPerformed(ActionEvent e) {
						//open library books
						new LibraryBooks();
					}
				});
		b4.setBounds(300, 100, 180, 80);
		
		container.add(b4);
	}
}
