/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PhysEngGame;

import javax.microedition.lcdui.*;

/**
 *
 * @author Carlos
 */
public class CGraphics {

    public Graphics m_g = null;
    // screen pixels and size vars
    int m_xsize;
    int m_ysize;
    int m_lRGB=246256;
    int m_data[];
    int m_dataBlackScreen[];

    public CGraphics(Graphics pGraphics, int lWidth, int lHeight) {
        m_g = pGraphics;
        m_xsize = lWidth;
        m_ysize = lHeight;
        m_data = new int[m_xsize * m_ysize];
        m_dataBlackScreen = new int[m_xsize * m_ysize];

    }

    public void lineSimple(int x0, int y0, int x1, int y1, int lRGB) {
        m_lRGB = lRGB;
        int dx = x1 - x0;
        int dy = y1 - y0;

        //raster.setPixel(pix, x0, y0);
        drawPixel(x0, y0);
        if (dx != 0) {
            float m = (float) dy / (float) dx;
            float b = y0 - m * x0;
            dx = (x1 > x0) ? 1 : -1;
            while (x0 != x1) {
                x0 += dx;
                y0 = CMath.round(m * x0 + b);
                //raster.setPixel(pix, x0, y0);
                drawPixel(x0, y0);
            }
        }

    }

    public void lineFast(int x0, int y0, int x1, int y1, int lRGB) {
        //int pix = color.getRGB();
        m_lRGB = lRGB;
        int dy = y1 - y0;
        int dx = x1 - x0;
        int stepx, stepy;

        if (dy < 0) {
            dy = -dy;
            stepy = -m_xsize;
        } else {
            stepy = m_xsize;
        }
        if (dx < 0) {
            dx = -dx;
            stepx = -1;
        } else {
            stepx = 1;
        }
        dy <<= 1;
        dx <<= 1;

        y0 *= m_xsize;
        y1 *= m_xsize;
        m_data[x0 + y0] = m_lRGB;
        if (dx > dy) {
            int fraction = dy - (dx >> 1);
            while (x0 != x1) {
                if (fraction >= 0) {
                    y0 += stepy;
                    fraction -= dx;
                }
                x0 += stepx;
                fraction += dy;
                m_data[x0 + y0] = m_lRGB;
            }
        } else {
            int fraction = dx - (dy >> 1);
            while (y0 != y1) {
                if (fraction >= 0) {
                    x0 += stepx;
                    fraction -= dy;
                }
                y0 += stepy;
                fraction += dx;
                m_data[x0 + y0] = m_lRGB;
            }
        }
    }

    private void drawPixel(int lRGB, int x, int y) {
        if(x<m_xsize && y<m_ysize)
            m_data[x + y * m_xsize] = lRGB;
    }

    private void drawPixel(int x, int y) {
        if(x>=0 && x<m_xsize && y>=0 && y<m_ysize)
            m_data[x + y * m_xsize] = m_lRGB;
    }

    /** fill triangle
     */
    public void fill_triangle(int xi1, int yi1, int xi2, int yi2, int xi3, int yi3, int lRGB) {

        m_g.setColor(lRGB);

        double x0 = xi1;
        double x1 = xi2;
        double x2 = xi3;

        double y0 = yi1;
        double y1 = yi2;
        double y2 = yi3;

        double d0 = 0;
        double d1 = 0;
        double d2 = 0;

        double dx0 = 0;
        double dx1 = 0;
        double dx2 = 0;

        double dy0 = 0;
        double dy1 = 0;
        double dy2 = 0;

        //double dx[] = {0,0,0};
        double dy[] = {0, 0, 0};
        double d[] = {0, 0, 0};

        double dt;

        int i;

        if (y1 < y0) {
            dt = y0;
            y0 = y1;
            y1 = dt;
            dt = x0;
            x0 = x1;
            x1 = dt;
        }
        if (y2 < y1) {
            dt = y1;
            y1 = y2;
            y2 = dt;
            dt = x1;
            x1 = x2;
            x2 = dt;
        }
        if (y2 < y0) {
            dt = y0;
            y0 = y2;
            y2 = dt;
            dt = x0;
            x0 = x2;
            x2 = dt;
        }

        dx0 = x1 - x0;
        dy0 = y1 - y0;

        dx1 = x2 - x1;
        dy1 = y2 - y1;

        dx2 = x0 - x2;
        dy2 = y0 - y2;

        if (dy0 != 0) {
            d0 = dx0 / dy0;
        } else {
            d0 = 0;
        }

        if (dy1 != 0) {
            d1 = dx1 / dy1;
        } else {
            d1 = 0;
        }

        if (dy2 != 0) {
            d2 = dx2 / dy2;
        } else {
            d2 = 0;
        }

        int sx, ex;

        double id;

        for (i = (int) y0; i < y1; i++) {
            id = (double) i;

            sx = (int) (x0 + ((id - y0) * d2));
            ex = (int) (x0 + ((id - y0) * d0));

            if (sx < ex) {
                qscan(sx, i, ex - sx);
            } else {
                qscan(ex, i, sx - ex);
            }
        }

        for (i = (int) y1; i < y2; i++) {
            sx = (int) (x0 + ((i - y0) * d2));
            ex = (int) (x1 + ((i - y1) * d1));

            if (sx < ex) {
                qscan(sx, i, ex - sx);
            } else {
                qscan(ex, i, sx - ex);
            }
        }

    }

    /** private quick scan
     * 
     *  @param x    x
     *  @param y    y
     *  @param size size of scan
     * 
     */
    private void qscan(int x, int y, int size) {
        // clip y
        if (y >= 0 && y < m_ysize && x>=0 && x < m_xsize) {
            // clip left hand side of scan
            if (x < 0) {
                size += x;
                x = 0;
            }
            if(size+x>=m_xsize)
                size=m_xsize-x;

            y = y * m_xsize + x;

            x = y + size;
            
            for (; y < x; y++) {
                m_data[y] = m_lRGB;
            }
        }
    }

    private void clearData() {
        System.arraycopy(m_dataBlackScreen, 0, m_data, 0, m_xsize * m_ysize);
    }

    public void setColor(int lRGB) {
        if(CONSTANTS.USE_MY_GRAPHICS){
            m_lRGB = lRGB;
        }
        else{
            m_g.setColor(lRGB);
        }
    }
    public void setColor(int red, int green, int blue) {
        if(CONSTANTS.USE_MY_GRAPHICS){
            //AARRGGBB
            int lalpha = 255 << 24 ;
            int lR = red    << 16 ;
            int lG = green  << 8 ;
            int lB = blue   << 0 ;

            m_lRGB = lalpha | lR | lG | lB;
        }
        else{
            m_g.setColor(red,green,blue);
        }
    }
    
    public void flushGraphics(){
        if(CONSTANTS.USE_MY_GRAPHICS){
            //m_g.drawRGB(m_dataBlackScreen,0,m_xsize,0,0,m_xsize,m_ysize,false); 
            m_g.drawRGB(m_data,0,m_xsize,0,0,m_xsize,m_ysize,false); 
            clearData();    
        }
    }
    
    public void drawLine(int x0, int y0, int x1, int y1){
        //m_lRGB=23530;
        if(CONSTANTS.USE_MY_GRAPHICS){
            lineSimple(x0, y0, x1, y1, m_lRGB);
        }
        else{
            m_g.drawLine(x0, y0, x1, y1);
        }
            
        
    }
    
    public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        //m_lRGB=23530;
        //fill_triangle(x1, y1, x2, y2, x3, y3, m_lRGB);
        if(CONSTANTS.USE_MY_GRAPHICS){
            myFillTriangle(x1, y1, x2, y2, x3, y3, m_lRGB);
        }
        else{
            m_g.fillTriangle(x1, y1, x2, y2, x3, y3);
        }
    }
    public void fillRect(int x, int y, int width, int height) {
        if(CONSTANTS.USE_MY_GRAPHICS){
            drawPixel(x, y);
        }
        else{
            m_g.fillRect(x, y, width, height);
        }
        //m_g.fillRect(x, y, width, height);
    }
    
    public void myFillTriangle(int xi1, int yi1, int xi2, int yi2, int xi3, int yi3, int lRGB) {

        lineSimple(xi1, yi1, xi2, yi2, lRGB);
        lineSimple(xi2, yi2, xi3, yi3, lRGB);
        lineSimple(xi3, yi3, xi1, yi1, lRGB);
    }
    Font getFont(){
        return m_g.getFont(); 
    }
    public void drawString(String str, int x, int y, int anchor) {
        m_g.drawString(str, x, y, anchor);
    }
}
