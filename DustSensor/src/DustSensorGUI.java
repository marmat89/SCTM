import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import java.awt.BorderLayout;

import javax.swing.JButton;

import org.jfree.ui.RefineryUtilities;

import Charts.GraficoALinea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DustSensorGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DustSensorGUI window = new DustSensorGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DustSensorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {


		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		frame.getContentPane().add(textPane, BorderLayout.CENTER);
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText(textPane.getText()+"\n"+ "ciao");
			}
		});
		frame.getContentPane().add(btnStart, BorderLayout.NORTH);
	

		final GraficoALinea demo = new GraficoALinea(
				"Grafici al gusto di caffè");
		int count=7;
		while(true)
		{
			demo.ds.add(count, Math.random()*1000);
			if(demo.ds.getItemCount()>30)
			demo.ds.remove(0);
			count++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
