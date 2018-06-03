import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Date;
import java.text.*;

import java.awt.geom.*;
import javax.script.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;

/**
 * @author sanar
 *
 */

public class p146114_ass02 {
	static double factor = 0.5;
	private volatile static int draggedAtX;
	private volatile static int draggedAtY;
	public static JFrame j=new JFrame();
	public static JPanel p=new JPanel(	);
	public static JPanel p1=new JPanel();
	public static JPanel p2=new JPanel();
	public static JPanel p3=new JPanel();
	public static JButton draw=new JButton("Draw");
	public static JButton save=new JButton("Sava Image");
	public static JButton zoomout=new JButton("+");
	public static JButton zoomin=new JButton("-");
	public static JButton clear=new JButton("Clear");
	public static BorderLayout g=new BorderLayout();
	public static GridLayout g1=new GridLayout(3,4,5,5);
	public static GridLayout g2=new GridLayout(1,1);
	public static GridLayout g3=new GridLayout(1,2,5,5);
	static Font fieldFont = new Font("Arial", Font.PLAIN, 20);
    
	public static JTextField rangetext=new JTextField(10);
	public static JTextField xtext=new JTextField(10);
	public static JTextField ytext=new JTextField(10);
	
	
	//*****************************************************
			// 6. To plot multiple functions
	public static String fun[] = {"sin(x) (defualt)", "cos(x)", "tan(x)","sin(x),cos(x)","cos(x),tan(x)","sin(x),tan(x)","sin(x),cos(x),tan(x)"};
	public static JComboBox<String> func = new JComboBox<>(fun);
	public static int selectedIndex=0;
	
	public static JLabel lfun=new JLabel("Select function/s");
	
	
	//*****************************************************
	        //4. Ability to set range of x-axis and y-axis
	public static JLabel lrange=new JLabel("Enter range between -270 & 580 (i.e -170,480) ");
	public static JLabel lx=new JLabel("Xtics ");
	public static JLabel ly=new JLabel("Ytics");
	static javax.swing.border.Border border = LineBorder.createGrayLineBorder();
	public static void main(String[] args) {
		j.setSize(1200, 700);
		j.setVisible(true);
		j.setLayout(g);
		j.add(p2);
		j.add(p1,BorderLayout.NORTH);
		p1.setBackground(Color.PINK);
		p.setBackground(Color.PINK);
		p3.setBackground(Color.PINK);
		p3.setLayout(g3);
		p146114_paint_plotDraw c=new p146114_paint_plotDraw();
		p2.setLayout(g2);
		p2.add(c);
		p1.add(p);
		p.setLayout(g1);
		
		javax.swing.border.Border border = BorderFactory.createLineBorder(Color.red,2);
		rangetext.setBorder(border);
		rangetext.setFont(fieldFont);		
		rangetext.setForeground(Color.gray.brighter());
		xtext.setFont(fieldFont);		
		xtext.setForeground(Color.gray.brighter());
		xtext.setBorder(border);
		ytext.setFont(fieldFont);		
		ytext.setForeground(Color.gray.brighter());
		ytext.setBorder(border);
		func.setFont(fieldFont);		
		func.setForeground(Color.gray.brighter());
		func.setBackground(Color.white.brighter());
		func.setBorder(border);
		
		p.add(lx);
		p.add(xtext);
		p.add(ly);
		p.add(ytext);
		p.add(lrange);
		p.add(rangetext);
		p.add(lfun);
		p.add(func);
		p.add(draw);
		draw.setBackground(Color.LIGHT_GRAY);
		p.add(save);
		clear.setBackground(Color.LIGHT_GRAY);
		zoomin.setBackground(Color.LIGHT_GRAY);
		zoomout.setBackground(Color.LIGHT_GRAY);
		save.setBackground(Color.LIGHT_GRAY);
	
		p3.add(zoomin);
		p3.add(zoomout);
		p.add(p3);
		//p3.add(new JPanel());
		p.add(clear);
		
		
		
				
		func.addItemListener(new ItemListener()
	    {
			public void itemStateChanged(ItemEvent ev) {
			  selectedIndex = func.getSelectedIndex();
				
				
			}
	    	});
		
	    	
			

//*******************************************************
 // 1. Functionality to save the plot as image
	save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			c.snapshot(p2);

		}
		});	
		
//*******************************************************

//*********************************************************
// 2. Functionality to zoom in, and zoom out	
	
	
	zoomout.setPreferredSize(new Dimension(20, 20));
	zoomout.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{ 
			c.fact=c.fact * 1.05;
			zoomout.setEnabled(factor < 4.0);
			c.zoomer=true;
		    c.paint();  //	repaint
		}
		});
	zoomin.setPreferredSize(new Dimension(20, 20));
	zoomin.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{ 
			c.fact=c.fact / 1.05; 
			c.zoomer=true;
			zoomin.setEnabled(factor > 0.1);
			c.paint();   //	repaint
		
		}
		});
		
//******************************************************	
	//*****************************************************
		// 3. Functionality to use your mouse to move around the window	
			
			c.addMouseListener(new MouseAdapter(){
		            public void mousePressed(MouseEvent e){
		                draggedAtX = e.getX();
		                draggedAtY = e.getY();
		             
		            }
		        });
			 
			c. addMouseMotionListener(new MouseMotionAdapter(){
		            public void mouseDragged(MouseEvent e){
		                j.setLocation(e.getX() - draggedAtX + j.getLocation().x,
		                        e.getY() - draggedAtY + j.getLocation().y);
		            }
		        });
			
		//*******************************************************
	     // 4. plotting multiple functions	
			draw.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				  c.setid(selectedIndex);
					p2.add(c);
					c.paint();
					
				}
			});	
			
			//***********************************************
			// 7. Functionality to clear the graph
			clear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				  c.setid(-1);
					p2.add(c);
					c.paint();
					
				}
			});
		//*********************************************	
	}
	
	
	
	

}

