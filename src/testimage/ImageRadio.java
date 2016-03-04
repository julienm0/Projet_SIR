package testimage;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.PI;
import static java.lang.Math.exp;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.round;
import java.text.MessageFormat;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;

import java.awt.event.*;
import static java.lang.Math.PI;
import static java.lang.Math.exp;
import static java.lang.Math.floor;
import static java.lang.Math.log10;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import javax.swing.event.MouseInputAdapter;
import static testimage.Grapher.MARGIN;

public class ImageRadio extends JComponent {

    BufferedImage sauvegarde = null;
    BufferedImage monImage = null;
    Graphics2D g2d;
    //Graphics g;
    protected double xmin, xmax;
    protected double ymin, ymax;

    Interaction interaction;

    static final int MARGIN = 40;
    static final int STEP = 5;
    Cadre c;

    public ImageRadio() {
        super();

        xmin = -PI / 2.;
        xmax = 3 * PI / 2;
        ymin = -1.5;
        ymax = 1.5;
        at = null;
        att = null;
                //g2d.translate(-MARGIN, -MARGIN);

        // x values
		/*final int N = monImage.getWidth()/STEP + 1;
         final double dx = dx(STEP);
         double xs[] = new double[N];
         int    Xs[] = new int[N];
         for(int i = 0; i < N; i++) {
         double x = xmin + i*dx;
         xs[i] = x;
         Xs[i] = X(x);
         }*/
    }

    public ImageRadio(Cadre c) {
        super();

        xmin = -PI / 2.;
        xmax = 3 * PI / 2;
        ymin = -1.5;
        ymax = 1.5;
        this.c = c;
        at = null;
    }

    /*protected void paintComponent(Graphics g)
     {   
     super.paintComponent(g);
     if(monImage != null)
     g.drawImage(monImage, 0, 0, null);
              
            

     }*/
    protected int width = 400;
    protected int height = 300;
    private double angle = 0;
    public static final double PI2 = 2 * Math.PI;
    //  private Stroke stroke = new BasicStroke(3.0f);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        width = getWidth() / 2;
        height = getHeight() / 2;
        if (monImage == null || width == 0 || height == 0) {
            return;
        }
        g2d = (Graphics2D) g.create();
        try {
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g2d.translate(width, height);
            if (at == null) {
                int dx = -monImage.getWidth() / 2;
                int dy = -monImage.getHeight() / 2;

                g2d.drawImage(monImage, dx, dy, null);
                g2d.setComposite(AlphaComposite.SrcOver);
                c.setBounds(0, 0, monImage.getWidth(), monImage.getHeight() + 62);
            } else {
                g2d.drawImage(monImage, at, null);
                g2d.setComposite(AlphaComposite.SrcOver);
                this.c.setBounds(0, 0, monImage.getHeight(), monImage.getWidth() + 62);
            }
        } finally {
            g2d.dispose();
        }
        interaction = new Interaction(this, g2d, c);
        /*g2d.translate(MARGIN, MARGIN);
         width -= 2*MARGIN;
         height -= 2*MARGIN;
         if(width < 0 || height < 0) { 
         return; 
         }
         g2d.clipRect(0, 0, width, height);
         g2d.translate(-MARGIN, -MARGIN);*/
    }

    protected void paintComponent(Graphics g, int x, int y) {
        super.paintComponent(g);
        if (monImage != null) {
            g.drawImage(monImage, x, y, null);
        }
    }

    protected void reduireImage() {
        if (monImage != null) {
            BufferedImage imageReduite = new BufferedImage((int) (monImage.getWidth() * 0.5), (int) (monImage.getHeight() * 0.5), monImage.getType());
            AffineTransform reduire = AffineTransform.getScaleInstance(0.5, 0.5);
            int interpolation = AffineTransformOp.TYPE_BICUBIC;
            AffineTransformOp retaillerImage = new AffineTransformOp(reduire, interpolation);
            retaillerImage.filter(monImage, imageReduite);
            monImage = imageReduite;
            interaction = new Interaction(this, this.g2d, c);

            repaint();
            System.out.println("-- Zoom - --");
        } else {
            System.out.println("Il n'y a pas d'image. Veillez à bien insérer une image avant d'utiliser des fonctions");
        }
    }
    AffineTransform at = null;

    protected AffineTransform getAffTrans() {
        return at;
    }

    protected void rotate() {
        if (monImage != null) {
            at = new AffineTransform();

            //at.translate(getWidth() / 2, getHeight() / 2);
            at.rotate(Math.PI / 2);

            //at.scale(0.5, 0.5);
            at.translate(-monImage.getWidth() / 2, -monImage.getHeight() / 2);

            //interaction = new Interaction(this, g2d, c);
            repaint();
            System.out.println("-- Rotation --");
        } else {
            System.out.println("Il n'y a pas d'image. Veillez à bien insérer une image avant d'utiliser des fonctions");
        }

    }

    protected void agrandirImage() {
        if (monImage != null) {
            BufferedImage imageZoomer = new BufferedImage((int) (monImage.getWidth() * 1.5), (int) (monImage.getHeight() * 1.5), monImage.getType());
            AffineTransform agrandir = AffineTransform.getScaleInstance(1.5, 1.5);
            int interpolation = AffineTransformOp.TYPE_BICUBIC;
            AffineTransformOp retaillerImage = new AffineTransformOp(agrandir, interpolation);
            retaillerImage.filter(monImage, imageZoomer);
            monImage = imageZoomer;
            interaction = new Interaction(this, this.g2d, c);
            repaint();
            System.out.println("-- Zoom + --");
        } else {
            System.out.println("Il n'y a pas d'image. Veillez à bien insérer une image avant d'utiliser des fonctions");
        }
    }

    /*protected void imageConvolue()//on va utiliser le masque flou 
    {
        if (monImage != null) {
            BufferedImage imageFlou = new BufferedImage(monImage.getWidth(), monImage.getHeight(), monImage.getType());
            float[] masqueFlou
                    = {
                        0.1f, 0.1f, 0.1f,
                        0.1f, 0.2f, 0.1f,
                        0.1f, 0.1f, 0.1f
                    };

            Kernel masque = new Kernel(3, 3, masqueFlou);
            ConvolveOp operation = new ConvolveOp(masque);
            operation.filter(monImage, imageFlou);
            monImage = imageFlou;
            System.out.println("convolution effectuée");
            interaction = new Interaction(this, this.g2d, c);
            repaint();
        } else {
            System.out.println("Il n'y a pas d'image. Veillez à bien insérer une image avant d'utiliser des fonctions");
        }

    }*/

    protected void imageEclaircie() {
       
        if (monImage != null) {
            BufferedImage imgBrillant = new BufferedImage(monImage.getWidth(), monImage.getHeight(), monImage.getType());
            RescaleOp brillance = new RescaleOp(1.1f, 1.1f, null);
            brillance.filter(monImage, imgBrillant);
            monImage = imgBrillant;
            interaction = new Interaction(this, this.g2d, c);
            repaint();
            System.out.println("-- Eclaircissement --");
        } else {
            System.out.println("Il n'y a pas d'image. Veillez à bien insérer une image avant d'utiliser des fonctions");
        }

    }

    protected void imageSombre() {
       
        if (monImage != null) {
            BufferedImage imgSombre = new BufferedImage(monImage.getWidth(), monImage.getHeight(), monImage.getType());
            RescaleOp assombrir = new RescaleOp(0.7f, 10, null);
            assombrir.filter(monImage, imgSombre);
            monImage = imgSombre;
            //System.out.println("assombrir effectué");
            interaction = new Interaction(this, this.g2d, c);
            repaint();
            System.out.println("-- Assombrissement --");
        } else {
            System.out.println("Il n'y a pas d'image. Veillez à bien insérer une image avant d'utiliser des fonctions");
        }
    }

    /*protected void imageBinaire() {
        if (monImage != null) {
            BufferedImage imgBinaire = new BufferedImage(monImage.getWidth(), monImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D surfaceImg = imgBinaire.createGraphics();
            surfaceImg.drawImage(monImage, null, null);
            monImage = imgBinaire;
            interaction = new Interaction(this, this.g2d, c);
            repaint();

        } else {
            System.out.println("Il n'y a pas d'image. Veillez à bien insérer une image avant d'utiliser des fonctions");
        }
    }
*/
    protected void imageEnNiveauGris() {
        if (monImage != null) {
            /*BufferedImage imageGris = new BufferedImage(monImage.getWidth(), monImage.getHeight(), monImage.getType());
             Graphics2D surfaceImg = imageGris.createGraphics();
             surfaceImg.drawImage(monImage, null, null);	      
             monImage = imageGris;*/

            RescaleOp op = new RescaleOp(-1.0f, 255f, null);
            BufferedImage resultat = op.filter(monImage, null);
            monImage = resultat;
            interaction = new Interaction(this, this.g2d, c);
            repaint();
            System.out.println("-- Niveaux de gris inversés --");
        } else {
            System.out.println("Il n'y a pas d'image. Veillez à bien insérer une image avant d'utiliser des fonctions");
        }
    }

    protected void ajouterImage(File fichierImage) {   // desiiner une image � l'ecran	
        try {
            monImage = ImageIO.read(fichierImage);

            //g=monImage.getGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
       // System.out.println("Largeur = " + monImage.getWidth());
       // System.out.println("Hauteur = " + monImage.getHeight());

        interaction = new Interaction(this, this.g2d, c);
        sauvegarde = monImage;
        repaint();
        System.out.println("-- Visualisation de l'image --");
    }

    protected void setImage(BufferedImage image) {
        this.monImage = image;
        repaint();
    }

    protected void reinitialiser() {
        if (monImage == null) {
            System.out.println("Vous n'avez encore ouvert aucune image. Vous ne pouvez donc pas réinitialiser");
        } else {
            monImage = sauvegarde;
            at = null;
            att = null;
            this.c.setBounds(0, 0, monImage.getWidth(), monImage.getHeight());
           // System.out.println("Longueur = " + monImage.getHeight() + "\n Largeur = " + monImage.getWidth());
            interaction = new Interaction(this, this.g2d, c);
            repaint();
            System.out.println("-- Réinitialisation de l'image --");
        }
    }

    protected BufferedImage getImagePanneau() {      // r�cup�rer une image du panneau
        int width = this.getWidth();
        int height = this.getHeight();
		//BufferedImage image = new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB);
        //Graphics2D g = image.createGraphics();

        //this.paintAll(g);
        //g.dispose();
        return monImage;
    }
    /* L'image à parcourir */

    protected void Retourner() {
        if (monImage == null) {
            System.out.println("Vous n'avez encore ouvert aucune image. Vous ne pouvez donc pas réinitialiser");
        } else {
            int w = monImage.getWidth();
            int h = monImage.getHeight();
            int pixb;
            int pixd;
            /*pixb = monImage.getRGB(1, 1);
            monImage.setRGB(10, 10, pixb);
            System.out.println(pixb);*/
            if (!(w % 2 == 0)) {
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w / 2; j++) {
                        /* Accéder aux pixels (i, j) */
                        pixb = monImage.getRGB(j, i);
                        pixd = monImage.getRGB(w - 1 - j, i);
                        monImage.setRGB(w - 1 - j, i, pixb);
                        monImage.setRGB(j, i, pixd);
                        
                    }
                }
            } else {
                for (int i = 0; i < h; i++) {
                   
                    for (int j = 0; j < (w / 2) - 1; j++) {
                        /* Accéder aux pixels (i, j) */
                        //monImage.getRGB
                        pixb = monImage.getRGB(j, i);
                        pixd = monImage.getRGB(w - 1 - j, i);
                        monImage.setRGB(w - 1 - j, i, pixb);
                        monImage.setRGB(j, i, pixd);
                    }
                }
            }
            interaction = new Interaction(this, this.g2d, c);
            repaint();
            System.out.println("-- Retournement de l'image sympétriquement par rapport à l'axe vertical central --");
        }
    }

    protected void enregistrerImage(File fichierImage) {
        if (monImage != null) {
            String format = "JPG";
            BufferedImage image = getImagePanneau();
            try {
                ImageIO.write(image, format, fichierImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("-- Enregistrement de l'image effectué --");
        } else {
            System.out.println("Il n'y a pas d'image. Veillez à bien insérer une image avant d'utiliser des fonctions");
        }
    }

    protected double dx(int dX) {
        return (double) ((xmax - xmin) * dX / monImage.getWidth());
    }

    protected double dy(int dY) {
        return -(double) ((ymax - ymin) * dY / monImage.getHeight());
    }

    protected double x(int X) {
        return xmin + dx(X - MARGIN);
    }

    protected double y(int Y) {
        return ymin + dy((Y - MARGIN) - monImage.getHeight());
    }

    protected int X(double x) {
        int Xs = (int) round((x - xmin) / (xmax - xmin) * monImage.getWidth());
        return Xs + MARGIN;
    }

    protected int Y(double y) {
        int Ys = (int) round((y - ymin) / (ymax - ymin) * monImage.getHeight());
        return (monImage.getHeight() - Ys) + MARGIN;
    }

    protected void drawXTick(Graphics2D g2, double x) {
        if (x > xmin && x < xmax) {
            final int X0 = X(x);
            g2.drawLine(X0, MARGIN, X0, monImage.getHeight() + MARGIN);
            g2.drawString((new Double(x)).toString(), X0, monImage.getHeight() + MARGIN + 15);
        }
    }

    protected void drawYTick(Graphics2D g2, double y) {
        if (y > ymin && y < ymax) {
            final int Y0 = Y(y);
            g2.drawLine(0 + MARGIN, Y0, monImage.getWidth() + MARGIN, Y0);
            g2.drawString((new Double(y)).toString(), 5, Y0);
        }
    }

    protected static double unit(double w) {
        double scale = pow(10, floor(log10(w)));
        w /= scale;
        if (w < 2) {
            w = 2;
        } else if (w < 5) {
            w = 5;
        } else {
            w = 10;
        }
        return w * scale;
    }

    /**
     *
     * @param dX
     * @param dY
     */
    AffineTransform att = null;

    public void translate(int dX, int dY) {
        att = new AffineTransform();
        att.translate(-monImage.getWidth() / 2, -monImage.getHeight() / 2);
        att.translate(dX, dY);
        //System.out.println(at.getTranslateX());
        repaint();
    }

    public void zoom(Point center, int dz) {
        double x = x(center.x);
        double y = y(center.y);
        double ds = exp(dz * .01);
        xmin = x + (xmin - x) / ds;
        xmax = x + (xmax - x) / ds;
        ymin = y + (ymin - y) / ds;
        ymax = y + (ymax - y) / ds;
        repaint();
    }

    public void zoom(Point p0, Point p1) {
        double x0 = x(p0.x);
        double y0 = y(p0.y);
        double x1 = x(p1.x);
        double y1 = y(p1.y);
        xmin = min(x0, x1);
        xmax = max(x0, x1);
        ymin = min(y0, y1);
        ymax = max(y0, y1);
        repaint();
    }

    public void maj() {
        repaint();
    }
}
