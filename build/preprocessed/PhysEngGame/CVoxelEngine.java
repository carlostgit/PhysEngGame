/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CVoxelEngine {

    public static final int SCREEN_HEIGHT =200;
    public static final int SCREEN_WIDTH =200;
    public static final int MAP_HEIGHT = 400;
    public static final int MAP_WIDTH = 400;
    public static final int MAP_NUM_CELLS = MAP_HEIGHT*MAP_WIDTH;
    public static final int MAP_CELL_SIZE = 100;
    public static final int RED = 0x00FF0000;
    public static final int GREEN = 0x0000FF00;
    public static final int BLUE = 0x000000FF;
    public static final int TRANSPARENT = 0x00000000;
    
    public int[] m_lHeightMap = new int[MAP_NUM_CELLS];
    public int[] m_lColorMap = new int[MAP_NUM_CELLS];
    public int[] m_lPasoX = new int[SCREEN_WIDTH];
    public int[] m_lPasoY = new int[SCREEN_WIDTH];
    public int m_lNumPasos= 100;
    public int m_lPasoSize= 100;
    public int[] m_lScreen = new int[SCREEN_WIDTH*SCREEN_HEIGHT];
    
    public int m_lYPosition=100;
    public int m_lXPosition=100;
    public int m_lDistanceMin=200;
    public int m_lAltitude=5000;
    public int m_lDistanceFactor=10;
    
    
    
    
    public CVoxelEngine(){
        
        //Init of arrays
        int lColumn=0;
        int lRow=0;
        for(int i=0;i<MAP_NUM_CELLS;i++){

           m_lHeightMap[i]=0;
           m_lColorMap[i]=RED;

           //Update of column and row indexes
           lColumn++;
           if(lColumn==MAP_WIDTH){
                lColumn=0;
                lRow++;
           }
        }
        for(int i=0;i<SCREEN_WIDTH;i++){
            int lAngleMax=45;
            int lAnglePerColumn=lAngleMax/SCREEN_WIDTH;
            int lAngle=-lAngleMax+lAnglePerColumn*i;
            m_lPasoY[i]=(int)(m_lPasoSize*CMath.cosineTable((float)lAngle));
            m_lPasoX[i]=(int)(m_lPasoSize*CMath.sineTable((float)lAngle));
        }
//        for (int i=0;i<m_lScreen.length;i++){
//            m_lScreen[i]=RED;
//        }
         
    }
    
    void renderMap(){        
        
        for (int lCol=0;lCol<SCREEN_WIDTH;lCol++){
            int lPasoX=m_lPasoX[lCol];
            int lPasoY=m_lPasoY[lCol];
            int lX=m_lXPosition;
            int lY=m_lYPosition;
            int lDistance=0;
            int lXIndex=lX/MAP_CELL_SIZE;
            int lYIndex=lY/MAP_CELL_SIZE;
            int lLastPixelPaintedInScreen=0;
            
            for(int lPaso=1;lPaso<m_lNumPasos;lPaso++){
                lX=lX+lPasoX;
                lY=lY+lPasoY;
                lDistance=lDistance+m_lPasoSize;
                
                int lColor=m_lColorMap[lXIndex+lYIndex*MAP_WIDTH];
                int lHeight=m_lHeightMap[lXIndex+lYIndex*MAP_WIDTH];
            
                int YDistanceFronCentreOfScreen=(m_lAltitude-lHeight)*m_lDistanceFactor/lDistance;
                int lPixel=SCREEN_HEIGHT/2+YDistanceFronCentreOfScreen;
                if (lPixel<SCREEN_HEIGHT && lPixel>lLastPixelPaintedInScreen){
                    int lPixelToBePainted=lLastPixelPaintedInScreen+1;
                    while(lPixelToBePainted>=lPixel){
                        m_lScreen[lCol+lPixelToBePainted*SCREEN_WIDTH]=lColor;
                        lPixelToBePainted--;
                    }
                }
                        
            }            
        }
    }
}
