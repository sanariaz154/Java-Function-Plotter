import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author sanar
 *
 */

class p146114_paint_plotDraw extends JPanel {
	int id=-1,l,r,xtic,ytic;
	double fact=1;
    boolean zoomer=false;
    private double prevZoomFactor = 1;
    public p146114_paint_plotDraw()
    {
    }
    
    
    void snapshot(JPanel p)
    {
    	JFileChooser j = new JFileChooser();
    	int retVal = j.showSaveDialog(null);
    	if(retVal==JFileChooser.APPROVE_OPTION){
    	    File f = j.getSelectedFile();
    	    String test = f.getAbsolutePath();
    	    System.out.println(f.getName());
    	    BufferedImage image = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_ARGB);
        	Graphics g = image.getGraphics();
        	p.paint(g);
        	 try {
        	        ImageIO.write(image, "png", new File(test));
        	    } catch (IOException ex) {
        	    	System.out.println("Error");
        	   }
    	 }

    }
    double Draw_sine(double x) {
        return Math.sin(x);
    }
    double Draw_cos(double y) {
        return Math.cos(y);
    }
    double Draw_tan(double y) {
        return Math.tan(y);
    }
    void paint()
    {
    	repaint();
    }

    void setid(int a)
    {
    	id=a;
    }
    protected void paintComponent(Graphics g)
    {
       super.paintComponent(g);
      
  		 
       String range=p146114_ass02.rangetext.getText();
     	if(range.isEmpty())
    	{
     	l=-240;
     	r=520;
     	}
     	else
     	{
     		String[] sa=range.split(",");
     		l=Integer.parseInt( sa[0]);
     		System.out.println(l);
     		r=Integer.parseInt( sa[1]);
     	}
     	String xt=p146114_ass02.xtext.getText();
     	String yt=p146114_ass02.ytext.getText();
     	if(xt.isEmpty()||yt.isEmpty())
    	{
     	xtic=20;
     	ytic=20;
     	}
     	else
     	{
     		xtic=Integer.parseInt( xt);
     		ytic=Integer.parseInt( yt);
     	}

     	for(int j=0;j<p146114_ass02.p2.getHeight()-ytic;j+=ytic)
        {
     		for(int k=0;k<p146114_ass02.p2.getWidth()-xtic;k+=xtic)
        	{
        		g.drawRect (k,j , xtic, ytic);
        	}
        }
     	System.out.println(l);
        Graphics2D g2d = (Graphics2D) g.create();
        
    	// ********* Zooming *****************	
  		System.out.println("zoomer: " +zoomer);
  		 if (zoomer) {
  		        AffineTransform at = new AffineTransform();
  		        at.scale(fact, fact);
  		        prevZoomFactor = fact;
  		        g2d.transform(at);
  		        zoomer = false;
  		 }		 
  	//************************************
        
        
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.GREEN);
        g2d.drawLine(50, p146114_ass02.p2.getHeight()/2, p146114_ass02.p2.getWidth()-50, p146114_ass02.p2.getHeight()/2);
        g2d.drawLine(p146114_ass02.p2.getWidth()/2, 50, p146114_ass02.p2.getWidth()/2,p146114_ass02.p2.getHeight()-50);
        int xsp=0;
        int xsn=0;
        int ysp=0;
        int ysn=0;
        g2d.setColor(Color.BLACK);
        int a=(p146114_ass02.p2.getWidth())/2;
        int b=(p146114_ass02.p2.getWidth())/2;
        int c=(p146114_ass02.p2.getHeight())/2;
        int d=(p146114_ass02.p2.getHeight())/2;
        for(int i=0;i<13;i++)
        {
        		g2d.drawString(xsp+"", a,c);
        		xsp+=50;
        		a+=50;
        		g2d.drawString(xsn+"", b,c);
        		xsn-=50;
        		b-=50;
        }
        a=(p146114_ass02.p2.getWidth())/2;
        b=(p146114_ass02.p2.getWidth())/2;

        for(int i=0;i<10;i++)
        {
        		g2d.drawString(ysp+"", a,c);
        		ysp+=50;
        		c+=50;
        		g2d.drawString(ysn+"", a,d);
        		ysn-=50;
        		d-=50;
        }
        Polygon p = new Polygon();
        g2d.setStroke(new BasicStroke(2));

        if(id==0)
        {	int k=0;
        	int yp=0;
        	int xp=0;
        	System.out.println(-1*l);

        	for (int x = l; x <= r; x++) {
        		yp=270 - (int) (100 * Draw_sine((x / 100.0) * 2* Math.PI));
        		xp=x +500;
        		p.addPoint(xp, yp);
                k++;
                if(k == 10) {
                 k = 0;
                 g2d.setColor(Color.BLACK);
                 g2d.drawString(xp-500+"", xp+5,yp+5);
                }
        	}
       g2d.setColor(Color.RED);
       g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
        }

        if(id==1)
        {	int k=0;
       for (int x = l; x <= r; x++) {
    	   int yp=270 - (int) (100 * Draw_cos((x / 100.0) * 2* Math.PI));
   		int xp=x + 500;
           p.addPoint(xp, yp);
           k++;
           if(k == 10) {
            k = 0;
            g2d.setColor(Color.BLACK);
            g2d.drawString(xp-500+"", xp+2,yp+2);
           }
       }
       g2d.setColor(Color.BLUE);
       g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
        }
        if(id==2)
        {	int k=0;

        	 for (int x = l; x <= r; x++) {
        		 int yp=270 - (int) (80 * Draw_tan((x / 100.0) * 2* Math.PI));
         		int xp=x + 500;
                 p.addPoint(xp, yp);
                 k++;
                 if(k == 10) {
                  k = 0;
                  g2d.setColor(Color.BLACK);
                 g2d.drawString(xp-500+"", xp+2,yp+2);
                 }

        }
       g2d.setColor(Color.CYAN);
       g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
        }

        if(id==3)
        {	int k=0;
        	for (int x = l; x <= r; x++) {
        		int yp=270 - (int) (100 * Draw_sine((x / 100.0) * 2* Math.PI));
        		int xp=x + 500;
                p.addPoint(xp, yp);
                k++;
                if(k == 15) {
                 k = 0;
                 g2d.setColor(Color.BLACK);
                g2d.drawString(xp-500+"", xp+2,yp+2);
                }

        	}
       g2d.setColor(Color.RED);
       g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
        	Polygon p1 = new Polygon();
        	k=0;
       for (int x = l; x <= r; x++) {
    	   	int yp=270 - (int) (100 * Draw_cos((x / 100.0) * 2* Math.PI));
   			int xp=x + 500;
           p1.addPoint(xp, yp);
           k++;
           if(k == 15) {
            k = 0;
            g2d.setColor(Color.BLACK);
           g2d.drawString(xp-500+"", xp+2,yp+2);
           }

       }
       g2d.setColor(Color.BLUE);
       g2d.drawPolyline(p1.xpoints, p1.ypoints, p1.npoints);
        }
        if(id==4)
        {	int k=0;
        	for (int x = l; x <= r; x++) {
        		int yp=270 - (int) (100 * Draw_cos((x / 100.0) * 2* Math.PI));
        		int xp=x + 500;
                p.addPoint(xp, yp);
                k++;
                if(k == 15) {
                 k = 0;
                 g2d.setColor(Color.BLACK);
                g2d.drawString(xp-500+"", xp+2,yp+2);
                }

        	}
       g2d.setColor(Color.BLUE);
       g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);

        	Polygon p1 = new Polygon();
        	k=0;
        	for (int x = l; x <= r; x++) {
        		int yp=270 - (int) (100 * Draw_tan((x / 100.0) * 2* Math.PI));
        		int xp=x + 500;
                p1.addPoint(xp, yp);
                k++;
                if(k == 15) {
                 k = 0;
                 g2d.setColor(Color.BLACK);
                g2d.drawString(xp-500+"", xp+2,yp+2);
                }        }       //g2d.setStroke(new BasicStroke(3));
       g2d.setColor(Color.CYAN);
       g2d.drawPolyline(p1.xpoints, p1.ypoints, p1.npoints);
        }
        if(id==5)
        {	int k=0;
        	  for (int x = l; x <= r; x++) {
        		  int yp=270 - (int) (100 * Draw_sine((x / 100.0) * 2* Math.PI));
          		int xp=x + 500;
                  p.addPoint(xp, yp);
                  k++;
                  if(k == 15) {
                   k = 0;
                   g2d.setColor(Color.BLACK);
                  g2d.drawString(xp-500+"", xp+2,yp+2);
                  }
                 }
              g2d.setColor(Color.RED);
              g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);

        	Polygon p1 = new Polygon();
        	
        	k=0;
        	for (int x = l; x <= r; x++) {
        		int yp=270 - (int) (100 * Draw_tan((x / 100.0) * 2* Math.PI));
        		int xp=x + 500;
                p1.addPoint(xp, yp);
                k++;
                if(k == 15) {
                 k = 0;
                 g2d.setColor(Color.BLACK);
                g2d.drawString(xp-500+"", xp+2,yp+2);
                }
        }   
       g2d.setColor(Color.CYAN);
       g2d.drawPolyline(p1.xpoints, p1.ypoints, p1.npoints);
        }
        if(id==6)
        {	int k=0;
        	for (int x = l; x <= r; x++) {
        		int yp=270 - (int) (100 * Draw_sine((x / 100.0) * 2* Math.PI));
        		int xp=x + 500;
                p.addPoint(xp, yp);
                k++;
                if(k == 15) {
                 k = 0;
                 g2d.setColor(Color.BLACK);
                g2d.drawString(xp-500+"", xp+2,yp+2);
                }
        	}
        	g2d.setColor(Color.RED);
        	g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);

        	Polygon p2 = new Polygon();
        	k=0;
        	for (int x = l; x <= r; x++) {
        		int yp=270 - (int) (100 * Draw_cos((x / 100.0) * 2* Math.PI));
        		int xp=x + 500;
                p2.addPoint(xp, yp);
                k++;
                if(k == 15) {
                 k = 0;
                 g2d.setColor(Color.BLACK);
                g2d.drawString(xp-500+"", xp+2,yp+2);
                }
               }
              g2d.setColor(Color.BLUE);
              g2d.drawPolyline(p2.xpoints, p2.ypoints, p2.npoints);

        	Polygon p1 = new Polygon();
        	k=0;
        	for (int x = l; x <= r; x++) {
        		int yp=270 - (int) (100 * Draw_tan((x / 100.0) * 2* Math.PI));
        		int xp=x + 500;
                p1.addPoint(xp, yp);
                k++;
                if(k == 15) {
                 k = 0;
                 g2d.setColor(Color.BLACK);
                g2d.drawString(xp-500+"", xp+2, yp+2);
                }
        }       
       g2d.setColor(Color.CYAN);
       g2d.drawPolyline(p1.xpoints, p1.ypoints, p1.npoints);
        }


    }



}


