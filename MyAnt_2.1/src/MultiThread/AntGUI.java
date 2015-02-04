package MultiThread;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.List;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import net.miginfocom.swing.MigLayout;

public class AntGUI {

	private JFrame frame;
	private final JLabel lblNewLabel = new JLabel("New label");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		final GraphM g = new GraphM();
	    HashMap<String, JLabel> list= new HashMap<String, JLabel>();
		Loader l = new Loader();
		Configurator c = new Configurator();
		l.load(c.getTagValueSTR("NODE_PATH"), g);
		AntGUI window = new AntGUI(g,list);
    	window.frame.setVisible(true);		
		Drier d=new Drier ();
		AnthillM n = new AnthillM(g.node(c.getTagValueSTR("BEGIN_NODE")),
				g.node(c.getTagValueSTR("END_NODE")), c.getTagValueINT("NUMBER_ANTS"), g,list);
		d.ThreadCreation(n);
		try {
			n.antManager.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("FINEEEEEEEEEEEEEEEEEEE");
	}

	/**
	 * Create the application.
	 * @param g 
	 * @param list 
	 */
	public AntGUI(GraphM g,HashMap<String, JLabel>list) {
		HashMap<String, JLabel> guiLabels = initialize(g,list);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param g 
	 * @param list 
	 */
	private HashMap<String, JLabel> initialize(GraphM g, HashMap<String, JLabel>list) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[]", "[][]"));
		Iterator<Bridge> i = g.getBridges().iterator();
		GridBagConstraints gbc_lblNode = new GridBagConstraints();
		int count=0;
		while (i.hasNext()){
			Bridge node = i.next();
				JLabel lblNode = new JLabel("Node "+node.a.getName()+"===>"+"Node "+node.b.getName());
				count++;
				frame.getContentPane().add(lblNode, "cell 0 "+count);
				list.put(node.a.getName()+""+node.b.getName(),lblNode);
				count++;

			}
		
		return list;
	}

}
