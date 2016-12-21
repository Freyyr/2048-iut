import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class Case extends JPanel {

	private JPanel defaultPanel;

	public Case() {
		defaultPanel = new JPanel();
		defaultPanel.setBackground(Color.GRAY);
		this.add(defaultPanel);
	}

	public Case(int value) {
		defaultPanel = new JPanel();
		switch (value) {
		case 2:
			defaultPanel.setBackground(Color.ORANGE);
			JLabel a = new JLabel("2",SwingConstants.CENTER);
			defaultPanel.add(a);
			break;
		case 4:
			defaultPanel.setBackground(Color.YELLOW);
			JLabel b = new JLabel("4");
			defaultPanel.add(b);
			break;
		case 8:
			defaultPanel.setBackground(Color.GREEN);
			JLabel c = new JLabel("8");
			defaultPanel.add(c);
			break;
		case 16:
			defaultPanel.setBackground(Color.CYAN);
			JLabel d = new JLabel("16");
			defaultPanel.add(d);
			break;
		case 32:
			defaultPanel.setBackground(Color.BLUE);
			JLabel e = new JLabel("32");
			defaultPanel.add(e);
			break;
		case 64:
			defaultPanel.setBackground(Color.RED);
			JLabel f = new JLabel("64");
			defaultPanel.add(f);
			break;
		case 128:
			defaultPanel.setBackground(Color.MAGENTA);
			JLabel g = new JLabel("128");
			defaultPanel.add(g);
			break;
		case 256:
			defaultPanel.setBackground(Color.PINK);
			JLabel h = new JLabel("256");
			defaultPanel.add(h);
			break;
		case 512:
			defaultPanel.setBackground(new Color(153, 0, 153));
			JLabel i = new JLabel("512");
			defaultPanel.add(i);
			break;
		case 1024:
			defaultPanel.setBackground(new Color(102, 51, 0));
			JLabel j = new JLabel("1024");
			defaultPanel.add(j);
			break;
		case 2048:
			defaultPanel.setBackground(new Color(128, 128, 128));
			JLabel k = new JLabel("2048");
			defaultPanel.add(k);
			break;
		}
		this.add(defaultPanel);
	}
	
	 public void setVisible(boolean b) {
	        defaultPanel.setVisible(b);
	    }

	public static void main(String args[]){
		JFrame test = new JFrame();
		test.setMinimumSize(new Dimension(600, 400));
	    test.setLocationRelativeTo(null);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Case ntm = new Case(2048);
		ntm.setVisible(true);
		//ntm.setBackground(Color.BLUE);
	    Container contentpane = test.getContentPane();
	    contentpane.add(ntm);
		test.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
	}
}
