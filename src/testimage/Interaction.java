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
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Cursor;

import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import static java.lang.Math.abs;
import javax.swing.event.*;
import javax.swing.JList;



public  class Interaction 
	extends MouseInputAdapter
	implements MouseWheelListener, ListSelectionListener{
    
         int nbfois=0;
   
        BufferedImage monImage = null;
        BufferedImage sauvegarde = null;
	final int D_DRAG = 5;
		
	final Cursor hand_cursor = new Cursor(Cursor.HAND_CURSOR);
	final Cursor default_cursor = Cursor.getDefaultCursor();
        Graphics2D g2d;
        AffineTransform at=null;
    @Override
    public void valueChanged(ListSelectionEvent e) {
       pan.maj();
    }
		
	enum State { IDLE, ZOOMIN_OR_PAN, PAN, ZOOMOUT_OR_SELECT, SELECT }
	protected State state = State.IDLE;
		
	protected Point down;
	protected Rectangle feedback = null;
		
	protected ImageRadio pan;
        protected Cadre c;
        public Interaction(ImageRadio p, Graphics2D g2d, Cadre c) {
            //System.out.println("Nouvelle Interaction");
		pan = p;
		pan.addMouseListener(this);
		pan.addMouseMotionListener(this);
		pan.addMouseWheelListener(this);
                this.g2d=g2d;
                monImage=pan.monImage;
                sauvegarde=monImage;
                this.c=c;
                this.at=p.getAffTrans();
               
                //this.c.setBounds(0, 0, monImage.getWidth(), monImage.getHeight()+62);
               
                     }
	
	public Interaction(ImageRadio p, Graphics2D g2d) {
		pan = p;
		pan.addMouseListener(this);
		pan.addMouseMotionListener(this);
		pan.addMouseWheelListener(this);
                this.g2d=g2d;
                monImage=pan.getImagePanneau();
                
              
                     }
public Interaction(ImageRadio p) {
		pan = p;
		pan.addMouseListener(this);
		pan.addMouseMotionListener(this);
		pan.addMouseWheelListener(this);
                this.g2d=null;
                     }


Point positionP;
	public void mousePressed(MouseEvent e) {
             
		 positionP = e.getPoint();
		switch(state) {
		case IDLE:
			switch(e.getButton()) {
			case MouseEvent.BUTTON1:
				down = positionP;
				state = State.ZOOMIN_OR_PAN;
                                
				break;
			case MouseEvent.BUTTON3:
				down = positionP;
				state = State.ZOOMOUT_OR_SELECT;
				break;
			}
		default:
			break;
		}
             }
            
	
Point positionR;
	public void mouseReleased(MouseEvent e) {
		 positionR = e.getPoint();
                
		switch(state) {
		case ZOOMIN_OR_PAN:
			if(e.getButton() == MouseEvent.BUTTON1) {
				BufferedImage imageZoomer = new BufferedImage((int)(monImage.getWidth()*1.5),(int)( monImage.getHeight()*1.5), monImage.getType());
                                AffineTransform agrandir = AffineTransform.getScaleInstance(1.5, 1.5);
                                int interpolation = AffineTransformOp.TYPE_BICUBIC;
                                AffineTransformOp retaillerImage = new AffineTransformOp(agrandir, interpolation);
                                retaillerImage.filter(monImage, imageZoomer );
                                monImage = imageZoomer ;
                                pan.setImage(monImage);
                                pan.repaint();
				state = State.IDLE;
			}
			break;
		case ZOOMOUT_OR_SELECT:
			if(e.getButton() == MouseEvent.BUTTON3) {
				BufferedImage imageReduite = new BufferedImage((int)(monImage.getWidth()*0.5),(int)( monImage.getHeight()*0.5), monImage.getType());
                                AffineTransform reduire = AffineTransform.getScaleInstance(0.5, 0.5);
                                int interpolation = AffineTransformOp.TYPE_BICUBIC;
                                AffineTransformOp retaillerImage = new AffineTransformOp(reduire, interpolation);
                                retaillerImage.filter(monImage, imageReduite );
                                monImage = imageReduite ;
                                pan.setImage(monImage);
                                pan.repaint();
				state = State.IDLE;
			}
			break;
		case PAN:
			if(e.getButton() == MouseEvent.BUTTON1) {
				pan.setCursor(default_cursor);
				state = State.IDLE;
			}
			break;
		case SELECT:
			if(e.getButton() == MouseEvent.BUTTON3) {
                            //System.out.println(monImage.getWidth() + "\n" + monImage.getHeight());
                         /*  int posx=positionR.x -positionP.x;
                           int posy=positionR.y -positionP.y;
                           System.out.println("----------------");
                        System.out.println("positionR.x - positionP.x =" + positionR.x + " - " +positionP.x + " = " + posx);
                         System.out.println("positionR.y - positionP.y =" + positionR.y + " - " +positionP.y + " = " + posy);
                             
                             System.out.println(min(positionP.x,positionP.y));
                             System.out.println("PositionR.x = " + positionR.x);
                             System.out.println("positionR.y = " + positionR.y);
                             
                              System.out.println("feedback.width = " + feedback.width);
                               System.out.println("feedback.height = " + feedback.height); */
                            try{
			monImage=sauvegarde.getSubimage(min(positionP.x,positionR.x) ,min(positionP.y,positionR.y), largeurRect(positionP, positionR), hauteurRect(positionP, positionR) );
                            }
                            catch (Exception ex) {
                               
                            }
// c.setBounds(0, 0, monImage.getWidth(), monImage.getHeight()+62);
                        /*int posx=positionR.x -positionP.x;
                           int posy=positionR.y -positionP.y;
                        System.out.println("positionR.x - positionP.x =" + positionR.x + " - " +positionP.x + " = " + posx);
                         System.out.println("positionR.y - positionP.y =" + positionR.y + " - " +positionP.y + " = " + posy);
                             System.out.println("----------------");*/
                             /*System.out.println(min(positionP.x,positionP.y));
                             System.out.println("PositionR.x = " + positionR.x);
                             System.out.println("positionR.y = " + positionR.y);
                             monImage.getSu
                              System.out.println("feedback.width = " + feedback.width);
                               System.out.println("feedback.height = " + feedback.height);*/
                           
                        
			//feedback.add(positionR);
			pan.setImage(monImage);
                        pan.maj();
                        state=state.IDLE;
			}
			break;
		default:
			break;
		}
			
	}
		
	public void mouseDragged(MouseEvent e) {
		Point position1 = e.getPoint();
		switch(state) {
		case ZOOMIN_OR_PAN:
			if(position1.distance(down) > D_DRAG) {
				pan.setCursor(hand_cursor);
				state = State.PAN;
			}
			break;
		case ZOOMOUT_OR_SELECT:
			if(position1.distance(down) > D_DRAG) {
				state = State.SELECT;
			}
			break;
		case PAN:
                    
                    
                    
                     
                       /* pan.translate(largeurRect(positionP, position1),hauteurRect(positionP, position1));
                        
			down = position1;
                        pan.setImage(monImage);*/
                        pan.maj();
			break;
		case SELECT:
			feedback = new Rectangle(down);
			feedback.add(position1);
			pan.repaint();
			break;
		default:
			break;
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
	if(e.getWheelRotation()>0){
            e.getPoint();
            
            BufferedImage imageZoomer = new BufferedImage((int)(monImage.getWidth()*0.5),(int)( monImage.getHeight()*0.5), monImage.getType());
		AffineTransform agrandir = AffineTransform.getScaleInstance(0.5, 0.5);
		int interpolation = AffineTransformOp.TYPE_BICUBIC;
		AffineTransformOp retaillerImage = new AffineTransformOp(agrandir, interpolation);
		retaillerImage.filter(monImage, imageZoomer );
		monImage = imageZoomer ;
                 pan.setImage(monImage);
               pan.maj();
        }
        else {
           e.getPoint();
            
             
            BufferedImage imageZoomer = new BufferedImage((int)(monImage.getWidth()*1.5),(int)( monImage.getHeight()*1.5), monImage.getType());
		AffineTransform agrandir = AffineTransform.getScaleInstance(1.5, 1.5);
		int interpolation = AffineTransformOp.TYPE_BICUBIC;
		AffineTransformOp retaillerImage = new AffineTransformOp(agrandir, interpolation);
		retaillerImage.filter(monImage, imageZoomer );
		monImage = imageZoomer ;
                 pan.setImage(monImage);
            pan.maj();
            
        }
	}
	
	public void drawFeedback(Graphics2D g2) {
		if(feedback != null) {
			g2.draw(feedback);
		}
	}
        
        public int min(int x1, int x2){
            if(x1<x2){
            return x1;}
            else return x2;
        }
        
        public boolean RectHG(Point p, Point r){
            if((min(p.x, r.x)==p.x) && (min(p.y, r.y)==p.y)){
                return true;}
            else{
                return false;
                }
        }
        public boolean RectHD(Point p, Point r){
            if((min(p.x, r.x)==r.x) && (min(p.y, r.y)==p.y)){
                return true;}
            else{
                return false;
                }
        }
        public boolean RectBG(Point p, Point r){
            if((min(p.x, r.x)==p.x) && (min(p.y, r.y)==r.y)){
                return true;}
            else{
                return false;
                }
        }
        public boolean RectBD(Point p, Point r){
            if((min(p.x, r.x)==r.x) && (min(p.y, r.y)==r.y)){
                return true;}
            else{
                return false;
                }
        }
        
        public int largeurRect(Point p, Point r){
            if(RectHG(p, r) || RectBG(p, r)){
                return r.x-p.x;
            }
            else {
                return p.x-r.x;
            }
          
        }
         public int hauteurRect(Point p, Point r){
            if(RectHG(p, r) || RectHD(p, r)){
                return r.y-p.y;
            }
            else {
                return p.y-r.y;
            }
          
        }
         
         public void setImage(BufferedImage img){
             monImage=img;
         }
}
