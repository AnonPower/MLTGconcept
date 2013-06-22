import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTextField input; 
	public static JTextArea output;
	private JScrollPane outputScrollPane;
	private DefaultCaret caret;
	public String text = "";
	private TextFieldStreamer tFS;

	public void windowInit()
	{
		new Window().setVisible(true);
	}
	
	public Window()
	{
		super("Magic Engine");
		setSize(800, 600);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		InputStream is = this.getClass().getResourceAsStream("/font/destructobeambb_reg.ttf");
		
		Font f = null;
		try
		{
			f = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f);
		}
		catch(Exception ex)
		{
		}
		
		output = new JTextArea();
		output.setEditable(false);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		output.setBackground(Color.BLACK);
		output.setForeground(Color.GREEN);
		output.setFont(f);
		PrintStream ps = new PrintStream(new TextAreaOutputStream(output));
		System.setOut(ps);
		System.setErr(ps);
		
		outputScrollPane = new JScrollPane(output);
		outputScrollPane.setAutoscrolls(true);
		
	    caret = (DefaultCaret)output.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	    
		input = new JTextField();
		input.setEditable(true);
		input.setBackground(Color.BLACK);
		input.setForeground(Color.GREEN);
		input.setCaretColor(Color.GREEN);
		input.setFont(f);
		tFS = new TextFieldStreamer(input);
		input.addKeyListener(tFS);
		System.setIn(tFS);
		
		add(outputScrollPane, BorderLayout.CENTER);
		add(input, BorderLayout.SOUTH);
	}
}