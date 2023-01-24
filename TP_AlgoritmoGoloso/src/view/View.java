package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import model.Model;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View
{

	public JFrame frame;
	private JTextArea areaPlanilla;
	private JScrollPane scroll;
	private String texto;
	private JLabel lblNewLabel;



	public View(String s)
	{
		texto = s;
		initialize(texto);
	}

	private void initialize(String texto)
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		lblNewLabel = new JLabel("FIXTURE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 50));	
		lblNewLabel.setBounds(165, 20, 367, 67);	
		frame.getContentPane().add(lblNewLabel);
		
		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(10,110,665,542);
		frame.getContentPane().add(scroll);
		areaPlanilla(texto);
		
		JButton btnNewButton = new JButton("Solucion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				areaPlanilla(Model.getSolucion().toString());
			}
		});
		btnNewButton.setBounds(542, 43, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		
	
}
	public JTextArea areaPlanilla(String s)
	{
		areaPlanilla = new JTextArea();
		areaPlanilla.setBackground(new Color(255, 204, 209));
		scroll.setViewportView(areaPlanilla);
		areaPlanilla.setLineWrap(true);
		areaPlanilla.setWrapStyleWord(true);
		areaPlanilla.setFont(new Font("MS PGothic", Font.BOLD, 28));
		areaPlanilla.setText(s);
		return areaPlanilla;

	}
}
