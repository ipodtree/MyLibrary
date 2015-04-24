import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class MyJFrame extends JFrame {
	private static final long serialVersionUID = 1L;

// 定义一个类继承JFrame类
	public void CreateJFrame(String title) { // 定义一个CreateJFrame()方法
		JFrame jf = new JFrame(title); // 实例化一个JFrame对象
		jf.setVisible(true); // 使窗体可视
		jf.setBounds(0, 0, 600, 400);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 设置窗体关闭方式
		
		Container container = jf.getContentPane(); // 获取一个容器
		container.setLayout(null);
		container.setBackground(Color.white);//设置容器的背景颜色
		
		JLabel jl = new JLabel("Welcome to your library"); // 创建一个JLabel标签
		// 使标签上的文字居中
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setBounds(195, 25, 200, 50);
		container.add(jl);
		
		
		
		JButton b1 = new JButton("my books"); // 定义一个按钮
		b1.addActionListener(new ActionListener() { // 为按钮添加鼠标单击事件
					public void actionPerformed(ActionEvent e) {
						//open mybooks
						new MyBooks();	
					}
				});
		b1.setBounds(100, 100, 180, 80);
		container.add(b1);
	
		
		JButton b2 = new JButton("borrow"); // 定义一个按钮
		b2.addActionListener(new ActionListener() { // 为按钮添加鼠标单击事件
					public void actionPerformed(ActionEvent e) {
						// open borrow
						new Borrow();
					}
				});
		b2.setBounds(300, 220, 180, 80);
		container.add(b2);
		
		JButton b3 = new JButton("return"); // 定义一个按钮
		b3.addActionListener(new ActionListener() { // 为按钮添加鼠标单击事件
					public void actionPerformed(ActionEvent e) {
						//open return
						new Return();
					}
				});
		b3.setBounds(100, 220, 180, 80);
		container.add(b3);
		
		JButton b4 = new JButton("library books"); // 定义一个按钮
		b4.addActionListener(new ActionListener() { // 为按钮添加鼠标单击事件
					public void actionPerformed(ActionEvent e) {
						//open library books
						new LibraryBooks();
					}
				});
		b4.setBounds(300, 100, 180, 80);
		
		container.add(b4);
	}
}
