package Project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class Frame extends JFrame implements ActionListener, MouseListener, WindowListener {
	
	JRadioButton jr[][]=new JRadioButton[2][2];
	String jrn[][]= {{"한국기준","일본기준"},{"한국기준","일본기준"}};
	String ln[]= {"카드명","발매일"};
	ButtonGroup group[]=new ButtonGroup[3];
	JButton jb;
	JPanel np,cp,sp;
	JProgressBar jpr;
	JFrame frame;

	public void init() {
		frame=new JFrame();
		frame.setTitle("Tool");
		
		frame.add(np=new JPanel(new GridLayout(0,1)),BorderLayout.NORTH);
		np.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JPanel jp;
		JLabel jl;
		for (int i = 0; i < 2; i++) {
			np.add(jp=new JPanel(new GridLayout(0,3)));
			jp.add(jl=new JLabel(ln[i]));
			group[i]=new ButtonGroup();
			for (int j = 0; j < 2; j++) {
				jp.add(jr[i][j]=new JRadioButton(jrn[i][j]));
				jr[i][j].addActionListener(this);
				group[i].add(jr[i][j]);
			}
		}
		
		frame.add(cp=new JPanel(new FlowLayout(FlowLayout.CENTER)),BorderLayout.CENTER);
		cp.add(jb=new JButton("추출"));
		jb.addActionListener(this);
		
		frame.add(sp=new JPanel(new FlowLayout()),BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(2);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	void wmsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	void imsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb) {
			if((!jr[0][0].isSelected() && !jr[0][1].isSelected()) || (!jr[1][0].isSelected() && !jr[1][1].isSelected())) {
				wmsg("옵션을 선택해주세요.");
				return;
			}
			
			crawler c=new crawler();
			c.init();
			
			if (jr[0][0].isSelected() && jr[1][0].isSelected()) {
				
				// 4000 ~ 19000
//				카드명 한국기준 / 발매일 한국기준
//				한국에 출시되지 않았다면 일본 이름, 일본 발매일로 가져옴
				for (int i = 4000; i <= 4030; i++) {
					System.out.println("============================================================");
					System.out.println("i = "+i);

					c.crawlingName_ko(i, true);
					c.crawlingDate_ko(i, true);
					
					if(c.arrText[0].size()==1) {
						c.arrText[0].remove(0);
					}
				}
			}else if (jr[0][0].isSelected() && jr[1][1].isSelected()) {
//				카드명 한국기준 / 발매일 일본기준
//				한국에 출시되지 않았다면 일본 이름으로 가져옴
				for (int i = 4000; i <= 4030; i++) {
					System.out.println("============================================================");
					System.out.println("i = "+i);

					c.crawlingName_ko(i, true);
					c.crawlingDate_ja(i);
				}
			}else if (jr[0][1].isSelected() && jr[1][0].isSelected()) {
//				카드명 일본기준 / 발매일 한국기준
//				한국에 출시되지 않았다면 정발 안함으로 표시
				for (int i = 5868; i <= 5870; i++) {
					System.out.println("============================================================");
					System.out.println("i = "+i);
		
					c.crawlingName_ja(i);
					c.crawlingDate_ko(i,true);
				}
			}else if (jr[0][1].isSelected() && jr[1][1].isSelected()) {
//				카드명 일본기준 / 발매일 일본기준
//				변수없음
				for (int i = 4000; i <= 4030; i++) {
					System.out.println("============================================================");
					System.out.println("i = "+i);
		
					c.crawlingName_ja(i);
					c.crawlingDate_ja(i);
				}
			}
						
			c.outCSV();
			imsg("Complete");
			
			System.exit(0);
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
