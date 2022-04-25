import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;

public class Source {

	private JFrame frame;
	private JTextArea textArea;
	private String inputStr = "";
	JTextArea textAreaToCount;
	private JLabel lblStringToCount;
	private JLabel lblNewLabel_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Source window = new Source();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Source() {
		initialize();
	}

	public int handleStr(String str, String substr)
	{
		StringTokenizer st = new StringTokenizer(str, " \t\n\r,.");
		int count = 0;
		
		while(st.hasMoreTokens())
		{
			if(st.nextToken().substring(0, substr.length()).compareTo(substr) == 0)
				count++;
		}
		return count;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 617, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner sc = new Scanner(new File("input.txt"));
					
					inputStr = "";
					while(sc.hasNextLine())
					{
						inputStr = inputStr + sc.nextLine() + "\n";
					}
					textArea.setText(inputStr);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(502, 23, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Process");
		btnNewButton_1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//String handledStr = handleStr(inputStr);
				int count = handleStr(inputStr, textAreaToCount.getText());
				String handledStr = "" + count;
				lblNewLabel_1.setText(handledStr);
				
				try {
					String outputFileName = "outputSite.html";
					BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
					String outputStr = "<html>\r\n"
							+ "<title>Hello</title>\r\n"
							+ "<body>\r\n"
							+ "\r\n"
							+ "<b>Лабораторная работа №9</b><br>\r\n"
							+ "<i>Вариант B</i><br>\n"
							+ "Выполнил студент группы ИВТАСбд-11 <b>Долгов А.П</b><br>\n";
					
					outputStr = outputStr + "Файл: <b>" + outputFileName + "</b><br>\n";		
					outputStr = outputStr + "Начало слова: <b>" + textAreaToCount.getText() + "<b><br>\n";
					outputStr = outputStr + "Количество найденный слов: <b>" + count + "</b><br>\n";
					outputStr = outputStr + "</body>\r\n </html>";
					
					writer.write(outputStr);
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}});
		btnNewButton_1.setBounds(502, 57, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 65, 475, 279);
		frame.getContentPane().add(textArea);
		
		textAreaToCount = new JTextArea();
		textAreaToCount.setBounds(10, 27, 248, 27);
		frame.getContentPane().add(textAreaToCount);
		
		JLabel lblNewLabel = new JLabel("String to count");
		lblNewLabel.setBounds(10, 11, 120, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblStringToCount = new JLabel("Strings:");
		lblStringToCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStringToCount.setBounds(268, 23, 141, 31);
		frame.getContentPane().add(lblStringToCount);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(324, 23, 79, 31);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
