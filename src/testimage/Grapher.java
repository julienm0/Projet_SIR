/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testimage;

/**
 *
 * @author Julien
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JList;

import java.util.Vector;

import static java.lang.Math.*;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import javax.swing.DefaultListModel;


public class Grapher extends JPanel {
	static final int MARGIN = 40;
	static final int STEP = 5;
        BufferedImage monImage=null;
	
        
	static final BasicStroke dash = new BasicStroke(1, BasicStroke.CAP_ROUND,
	                                                   BasicStroke.JOIN_ROUND,
	                                                   1.f,
	                                                   new float[] { 4.f, 4.f },
	                                                   0.f);
	                                                   
	protected int W = 400;
	protected int H = 300;
	
	protected double xmin, xmax;
	protected double ymin, ymax;

	
	Interaction interaction;
	
	public Grapher() {
		xmin = -PI/2.; xmax = 3*PI/2;
		ymin = -1.5;   ymax = 1.5;
		
		//interaction = new Interaction(this);
                
		

	}
	
	
        public void maj(){
            repaint();
        }
        
     
        
        
        
	public Dimension getPreferredSize() { return new Dimension(W, H); }
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
                int scale=1;
BufferedImage theImg_scaled = new BufferedImage((int)(monImage.getWidth()*scale),
        (int)( monImage.getHeight()*scale),BufferedImage.TYPE_INT_ARGB);Graphics2D grph = 
                (Graphics2D) theImg_scaled.getGraphics();
grph.scale(scale, scale);
grph.drawImage(theImg_scaled, 0, 0, null);
		W = getWidth();
		H = getHeight();

		Graphics2D g2 = (Graphics2D)g;

		// background
		g2.setColor(Color.WHITE);
		//g2.fillRect(0, 0, W, H);
		
		g2.setColor(Color.BLACK);

		// box
		g2.translate(MARGIN, MARGIN);
		W -= 2*MARGIN;
		H -= 2*MARGIN;
		if(W < 0 || H < 0) { 
			return; 
		}
		
		g2.drawRect(0, 0, W, H);
		
		//g2.drawString("x", W, H+10);
		//g2.drawString("y", -10, 0);
		
		
		// plot
		//g2.clipRect(0, 0, W, H);
		g2.translate(-MARGIN, -MARGIN);

		// x values
		final int N = W/STEP + 1;
		final double dx = dx(STEP);
		double xs[] = new double[N];
		int    Xs[] = new int[N];
		for(int i = 0; i < N; i++) {
			double x = xmin + i*dx;
			xs[i] = x;
			Xs[i] = X(x);
		}
                /*
                for(int i=0; i<functions.getSize(); i++) {
			// y values
			int Ys[] = new int[N];
			for(int j = 0; j < N; j++) {
				Ys[j] = Y(functions.get(i).y(xs[j]));
			}
                        g2.drawPolyline(Xs, Ys, N);
                }
                */
                    
		g2.setClip(null);

		// axes
		drawXTick(g2, 0);
		drawYTick(g2, 0);
		
		double xstep = unit((xmax-xmin)/10);
		double ystep = unit((ymax-ymin)/10);

		g2.setStroke(dash);
		//for(double x = xstep; x < xmax; x += xstep)  { drawXTick(g2, x); }
		//for(double x = -xstep; x > xmin; x -= xstep) { drawXTick(g2, x); }
		//for(double y = ystep; y < ymax; y += ystep)  { drawYTick(g2, y); }
		//for(double y = -ystep; y > ymin; y -= ystep) { drawYTick(g2, y); }

		interaction.drawFeedback(g2);
                super.paintComponent(g);
		if(monImage != null)
			g.drawImage(monImage, 0, 0, null);
	
	}
	
	protected double dx(int dX) { return  (double)((xmax-xmin)*dX/W); }
	protected double dy(int dY) { return -(double)((ymax-ymin)*dY/H); }

	protected double x(int X) { return xmin+dx(X-MARGIN); }
	protected double y(int Y) { return ymin+dy((Y-MARGIN)-H); }
	
        
	protected int X(double x) { 
		int Xs = (int)round((x-xmin)/(xmax-xmin)*W);
		return Xs + MARGIN; 
	}
	protected int Y(double y) { 
		int Ys = (int)round((y-ymin)/(ymax-ymin)*H);
		return (H - Ys) + MARGIN;
	}
		
	protected void drawXTick(Graphics2D g2, double x) {
		if(x > xmin && x < xmax) {
			final int X0 = X(x);
			g2.drawLine(X0, MARGIN, X0, H+MARGIN);
			g2.drawString((new Double(x)).toString(), X0, H+MARGIN+15);
		}
	}
	
	protected void drawYTick(Graphics2D g2, double y) {
		if(y > ymin && y < ymax) {
			final int Y0 = Y(y);
			g2.drawLine(0+MARGIN, Y0, W+MARGIN, Y0);
			g2.drawString((new Double(y)).toString(), 5, Y0);
		}
	}
	
	protected static double unit(double w) {
		double scale = pow(10, floor(log10(w)));
		w /= scale;
		if(w < 2)      { w = 2; } 
		else if(w < 5) { w = 5; }
		else           { w = 10; }
		return w * scale;
	}
	
	public void translate(int dX, int dY) {
		double dx = dx(dX);
		double dy = dy(dY);
		xmin -= dx; xmax -= dx;
		ymin -= dy; ymax -= dy;
		repaint();
	}
	
	public void zoom(Point center, int dz) {
		double x = x(center.x);
		double y = y(center.y);
		double ds = exp(dz*.01);
		xmin = x + (xmin-x)/ds; xmax = x + (xmax-x)/ds;
		ymin = y + (ymin-y)/ds; ymax = y + (ymax-y)/ds;
		repaint();
	}
	
	public void zoom(Point p0, Point p1) {
		double x0 = x(p0.x);
		double y0 = y(p0.y);
		double x1 = x(p1.x);
		double y1 = y(p1.y);
		xmin = min(x0, x1); xmax = max(x0, x1);
		ymin = min(y0, y1); ymax = max(y0, y1);
		repaint();
	}
        
        protected void imageConvolue()//on va utiliser le masque flou 
	{
		BufferedImage imageFlou = new BufferedImage(monImage.getWidth(),monImage.getHeight(), monImage.getType());
		float[ ] masqueFlou = 
		{
				0.1f, 0.1f, 0.1f,
				0.1f, 0.2f, 0.1f,
				0.1f, 0.1f, 0.1f
		};

		Kernel masque = new Kernel(3, 3, masqueFlou);
		ConvolveOp operation = new ConvolveOp(masque);
		operation.filter(monImage, imageFlou);
		monImage = imageFlou;
		System.out.println("convolution effectu�e");
		repaint();

	}

	protected void imageEclaircie()
	{
		/*
		 *    RescaleOp brillance = new RescaleOp(A, K, null);
		 *    1.  A< 1, l�image devient plus sombre.
   			  2.  A > 1, l�image devient  plus brillante.
   			  3. K est compris entre 0 et 256 et ajoute un �clairement .
		 */
		BufferedImage imgBrillant = new BufferedImage(monImage.getWidth(), monImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		RescaleOp brillance = new RescaleOp(1.2f, 0, null);
		brillance.filter(monImage, imgBrillant);
		monImage = imgBrillant;
		repaint();


	}

	protected void imageSombre()
	{
		/* RescaleOp assombrir = new RescaleOp(A, K, null);
		 *    
		 *    1.  A < 1, l�image devient plus sombre.
   			  2.  A > 1, l�image devient  plus brillante.
   			  3.  K est compris entre 0 et 256 et ajoute un �clairement .
		 *    
		 */		
		BufferedImage imgSombre = new BufferedImage(monImage.getWidth(), monImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		RescaleOp assombrir = new RescaleOp(0.7f, 10, null);
		assombrir.filter(monImage, imgSombre);
		monImage = imgSombre;
		System.out.println("assombrir effectu�e");
		repaint();
	}

	protected void imageBinaire()
	{   
		BufferedImage imgBinaire = new BufferedImage(monImage.getWidth(), monImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D surfaceImg = imgBinaire.createGraphics();
		surfaceImg.drawImage(monImage, null, null);   
		monImage = imgBinaire;
		repaint();
	}

	protected void imageEnNiveauGris()
	{
		BufferedImage imageGris = new BufferedImage(monImage.getWidth(), monImage.getHeight(), BufferedImage.TYPE_USHORT_GRAY);
		Graphics2D surfaceImg = imageGris.createGraphics();
		surfaceImg.drawImage(monImage, null, null);	      
		monImage = imageGris;
		repaint(); 
	}

	protected void ajouterImage(File fichierImage)
	{   // desiiner une image � l'ecran	
		try {
			monImage = ImageIO.read(fichierImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint(); 
	}

	protected BufferedImage getImagePanneau()
	{      // r�cup�rer une image du panneau
		int width  = this.getWidth();
		int height = this.getHeight();
		BufferedImage image = new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();

		this.paintAll(g);
		g.dispose();
		return image;
	}

	protected void enregistrerImage(File fichierImage)
	{
		String format ="JPG";
		BufferedImage image = getImagePanneau();
		try {
			ImageIO.write(image, format, fichierImage);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
