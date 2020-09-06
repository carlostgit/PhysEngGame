/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PhysEngGame;

import javax.microedition.lcdui.game.GameCanvas;
//import java.lang.Thread;
import javax.microedition.lcdui.*;
import java.util.Vector;


/**
 *
 * @author Carlos
 */
public class PhysEngRunnableGameCanvas extends GameCanvas implements Runnable {

    //Debug
    String m_spufoDebug="Pufo debug";
    String m_spufoDebug2="Pufo debug2";

    
    
    
    
    long m_lTiempoFlush=0;
    long m_lTiempoTotal=0;
    
    long m_lTiempoTestSumaLong=0;
    long m_lTiempoTestSumaDouble=0;
    long m_lTiempoTestMultDouble=0;
    long m_lTiempoTestMultLong=0;
    long m_lTiempoTestDivLong=0;
    long m_lTiempoTestDivDouble=0;
    long m_lTiempoCount=0;
    long m_lTiempoBitWise=0;
    long m_lTiempoDrawRect=0;
    
    
    
    
    
    

    
    
    

  

    public Graphics m_g; //hacer mis propias funciones drawLine etc dentro de esta gente
    public CGraphics m_myGraphics;
    private long m_lCount=0;
    private long m_lFrameTime=30;

    public int m_lZoomOutLevel=8; //deprecated. Only if DECIMAL_ZOOM is false
    public float m_fZoomOutLevel=50.0f;

    //Drawing variables for limits
    private int m_lLeftLimitD;
    private int m_lRightLimitD;
    private int m_lTopLimitD;
    private int m_lBottomLimitD;
    private int m_lLimitsWidthD;
    //keyboard variables
    boolean m_bLeftPressed = false;
    boolean m_bRightPressed = false;
    boolean m_bDownPressed = false;
    boolean m_bUpPressed = false;

    boolean m_bDownLeftPressed = false;
    boolean m_bDownRightPressed = false;
    boolean m_bCentrePressed = false;
    
    //keyPressed listeners
    private Vector m_vKeyPressedAndReleasedListeners = new Vector(30);

    //Exit Variable
    private boolean m_bExitWhile;

    //Drawing variables for info
    private float m_fLineAngle1 = 0;
    private float m_fLineAngle2 = 0;
    private float m_fLineAngle3 = 0;

    //Camera Point
    private float m_fCamObjX=0;
    private float m_fCamObjY=0;
    private float m_fAltitude=1000;
    private float m_fHeading=0;
    private float m_fPitch=75;
    private float m_fAngleOfVision=60f;
    
    
    private float m_fBackgroundDeep=1000;

    //Scene
    CScene m_pScene=null;
    
    //CStageControl
    CSceneControl m_pSceneControl=null;
    
    //Display
    Display m_pDisplay = null;
    
//    CVoxelEngine m_pVoxelEngine=null;

    public PhysEngRunnableGameCanvas(boolean suppressKeyEvents, Display display) {
        super(suppressKeyEvents);
        m_g = getGraphics();
        
        m_myGraphics = new CGraphics(m_g,this.getWidth(),this.getHeight());
        m_pScene = new CScene(this,m_myGraphics);
        m_pSceneControl=new CSceneControl(this,m_pScene);

        m_bExitWhile = false;
        
        m_pDisplay=display;
        
        
        
//        m_pVoxelEngine=new CVoxelEngine();
        //tests();
    }
    
    public Display getDisplay(){
        return m_pDisplay;
    }

    protected void keyPressed(int keyCode) {

        m_spufoDebug="KeyPressed keyCode "+keyCode+" ";
        m_spufoDebug2="KeyPressed keyCode "+getGameAction(keyCode)+" ";

        if (KEY_NUM4 == (keyCode) || LEFT == (keyCode)) {
            m_bLeftPressed = true;
        }
        if (KEY_NUM5 == (keyCode) ) {
            m_bCentrePressed = true;
        }
        if (KEY_NUM6 == (keyCode) || RIGHT == (keyCode)) {
            m_bRightPressed = true;
        }
        if (KEY_NUM7 == (keyCode)) {
            m_bDownLeftPressed = true;
        }
        if (KEY_NUM2 == (keyCode) || UP == (keyCode)) {
            m_bUpPressed = true;
        }
        if (KEY_NUM8 == (keyCode) || DOWN == (keyCode)) {
            m_bDownPressed = true;
        }
        if (KEY_NUM9 == (keyCode)) {
            m_bDownRightPressed = true;
        }
        
//        System.out.println("GUI keyPressed");
    }

    protected void keyReleased(int keyCode) {

        m_spufoDebug="keyReleased keyCode "+keyCode+" ";
        m_spufoDebug2="keyReleased keyCode "+getGameAction(keyCode)+" ";

        if (KEY_NUM4 == (keyCode) || LEFT == (keyCode)) {
            m_bLeftPressed = false;
        }
        if (KEY_NUM5 == (keyCode) ) {
            m_bCentrePressed = false;
        }
        if (KEY_NUM6 == (keyCode) || RIGHT == (keyCode)) {
            m_bRightPressed = false;
        }
        if (KEY_NUM7 == (keyCode) ) {
            m_bDownLeftPressed = false;
        }
        if (KEY_NUM2 == (keyCode) || UP == (keyCode)) {
            m_bUpPressed = false;
        }
        if (KEY_NUM8 == (keyCode) || DOWN == (keyCode)) {
            m_bDownPressed = false;
        }
        if (KEY_NUM9 == (keyCode) ) {
            m_bDownRightPressed = false;
        }
//        System.out.println("GUI keyReleased");
        
        for(int i=0;i<m_vKeyPressedAndReleasedListeners.size();i++){
            ((IKeyPressedAndReleasedListener)(m_vKeyPressedAndReleasedListeners.elementAt(i))).keyPressedAndReleased(keyCode);
        }
    }

    public void keyRepeated (int keyCode) {
        m_spufoDebug="keyRepeated keyCode "+keyCode+" ";
        m_spufoDebug2="keyRepeated keyCode "+getGameAction(keyCode)+" ";
    }
    public boolean isAnyKeyPressed(){
       
        if(m_bLeftPressed ||
            m_bCentrePressed ||
            m_bRightPressed ||
            m_bDownLeftPressed ||
            m_bUpPressed ||
            m_bDownPressed ||
            m_bDownRightPressed
            )
            return true;
        else
            return false;
    }

    public void run() {
        while (false == m_bExitWhile) {
            long lTiempoInicio = System.currentTimeMillis();

            
           
            m_pSceneControl.run();
            
            
            //int lWidth=this.getWidth();
            //int lHeight=this.getHeight();
//            int lWidth=200;
//            int lHeight=200;
//            m_g.drawRGB(m_pVoxelEngine.m_lScreen,0,CVoxelEngine.SCREEN_WIDTH,0,0,lWidth,lHeight,false);  
            
            
            //m_myGraphics.setColor(255,255,0);
            //m_myGraphics.fillTriangle(0, 0, this.getWidth(), this.getHeight()/2, 0, this.getHeight()*2);
//            m_myGraphics.lineFast(20, 20, 200, 200, 23824);
            //m_myGraphics.fill_triangle(20, 20, 200, 200, 20, 200, 23824);
            long lTiempoInicioTest=System.currentTimeMillis();
            m_myGraphics.flushGraphics();                        
            flushGraphics();
            long lTiempoFinTest=System.currentTimeMillis();
            m_lTiempoFlush=lTiempoFinTest-lTiempoInicioTest;
            
            System.out.println("Tiempo flush:"+m_lTiempoFlush);
            
            m_lCount++;
            long lTiempoFinal = System.currentTimeMillis();
            m_lTiempoTotal=lTiempoFinal - lTiempoInicio;
            long lTiempoSobrante = m_lFrameTime - (lTiempoFinal - lTiempoInicio);

            if (lTiempoSobrante > 0) {
                try {
                    Thread.sleep(lTiempoSobrante);

                } catch (InterruptedException ie) {

                }
            }

        }
    }
    
    public int GetXCoordinateInScreen(float fX) {
        if(CONSTANTS.DECIMAL_ZOOM){
            return (int)((getWidth()/2) + ((fX-m_fCamObjX) / m_fZoomOutLevel));
        }
        else {
            return (getWidth()/2) + ((int)(fX-m_fCamObjX) >> m_lZoomOutLevel);    
        }

    }
    public int GetYCoordinateInScreen(float fY) {
        if(CONSTANTS.DECIMAL_ZOOM){
            int lYOpposite=(int)(getHeight()/2 * m_fZoomOutLevel)-(int)(fY-m_fCamObjY);
            return (int)(lYOpposite / m_fZoomOutLevel);
        }
        else {
            int lYOpposite=(getHeight()/2 << m_lZoomOutLevel)-(int)(fY-m_fCamObjY);
            return lYOpposite >> m_lZoomOutLevel;
        }
        
    }
    public int GetDistanceInScreen(float fDistance){
        if(CONSTANTS.DECIMAL_ZOOM){
            return (int)(fDistance / m_fZoomOutLevel);
        }
        else {
            return (int)fDistance >> m_lZoomOutLevel;
        }
        
    }
   
    public void setColor(int red, int green, int blue){
        m_myGraphics.setColor(red,green,blue);
    }
   

    public int write ( int y, String s) {
        m_myGraphics.setColor(0, 50, 150);
        m_myGraphics.drawString (s, 0, y, Graphics.LEFT|Graphics.TOP);
        return y + m_myGraphics.getFont ().getHeight ();        
    }

    
    
    
    public int GetXCoordinateInScreenWithPersp(float fX, float fY, float fAngle, float fAltitude) {  
//        System.out.println("Entrando en GetXCoordinateInScreenWithPersp");
        float fGiro=-m_fHeading;
        float fObjetivePointX=fX-m_fCamObjX;
        float fObjetivePointY=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch);
        float fXObjetivePointTurned=fObjetivePointX*CMath.cosine(fGiro)-fObjetivePointY*CMath.sine(fGiro);
        float fYObjetivePointTurned=fObjetivePointX*CMath.sine(fGiro)+fObjetivePointY*CMath.cosine(fGiro);
        float fXCamPointTurned=fXObjetivePointTurned;
        float fYCamPointTurned=fYObjetivePointTurned+fAltitude*CMath.tanTable(m_fPitch);
        
        float fDistanceToPointProyectonOnYZPlane=CMath.calculateModule(fAltitude, fYCamPointTurned);///CMath.cosineTable(CMath.aTanTable(fYLocTurned/fAltitude));
        float fTan=fXCamPointTurned/fDistanceToPointProyectonOnYZPlane;
        return (int)((getWidth()>>1) + 300*fTan);

    }
    public int GetYCoordinateInScreenWithPersp(float fX, float fY, float fAngle, float fAltitude) {

        float fGiro=-m_fHeading;
        float fObjetivePointX=fX-m_fCamObjX;
        float fObjetivePointY=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch);
        float fXObjetivePointTurned=fObjetivePointX*CMath.cosine(fGiro)-fObjetivePointY*CMath.sine(fGiro);
        float fYObjetivePointTurned=fObjetivePointX*CMath.sine(fGiro)+fObjetivePointY*CMath.cosine(fGiro);
        float fXCamPointTurned=fXObjetivePointTurned;
        float fYCamPointTurned=fYObjetivePointTurned+fAltitude*CMath.tanTable(m_fPitch);
        
        //float fDistanceToPointProyectonOnXZPlane=CMath.calculateModule(fAltitude, fXCamPointTurned);///CMath.cosineTable(CMath.aTanTable(fXLocTurned/fAltitude));
        
        float fAuxB=fAltitude/CMath.cosineTable(m_fPitch);
        
        float fAuxA=fYObjetivePointTurned;
        float fAuxC=fAuxA*CMath.sineTable(m_fPitch);
        
        //return (int)((getHeight()>>1) - 300*(fAuxA*CMath.cosineTable(m_fPitch))/(fAuxB+fAuxC));
        return (int)((getHeight()>>1) - 300*(fAuxA*CMath.cosineTable(m_fPitch))/(CMath.calculateModule((fAuxB+fAuxC),fXCamPointTurned)));
        
    }
    public int GetDistanceInScreenWithPersp(float fX, float fY,float fSize, float fAngle, float fAltitude){        
//        System.out.println("Entrando en GetDistanceInScreenWithPersp");

        float fGiro=-m_fHeading;
        float fObjetivePointX=fX-m_fCamObjX;
        float fObjetivePointY=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch);
        float fXObjetivePointTurned=fObjetivePointX*CMath.cosine(fGiro)-fObjetivePointY*CMath.sine(fGiro);
        float fYObjetivePointTurned=fObjetivePointX*CMath.sine(fGiro)+fObjetivePointY*CMath.cosine(fGiro);
        float fXCamPointTurned=fXObjetivePointTurned;
        float fYCamPointTurned=fYObjetivePointTurned+fAltitude*CMath.tanTable(m_fPitch);
        
        float fOffCentreModule=CMath.calculateModule(fXCamPointTurned,fYCamPointTurned);
        float fRealAltitude=CMath.calculateModule(fOffCentreModule,fAltitude);
        //float fAngleToPixels=getWidth()/m_fAngleOfVision; //Angle of vision. This should be a param
        float fTan=fSize/fRealAltitude;
        return (int)(300*fTan);
        //return (int)(fAngleToPixels*CMath.aTanTable(fTan));            
    }
    public void tests(){
        
        System.out.println("Starting tests");
        CMath.cosineTable(90f);
        for (long i=0;i<360;i++){
            
            //System.out.println(CMath.cosineTable((float)i));
        }
        System.out.println("finish");
        for (long i=-360;i<360;i++){
            
            //System.out.println(CMath.aTanTable((float)i));
        }
        
        
            
        
        long lTiempoInicioTest=System.currentTimeMillis();
        long lSuma=0;
        for (long i=0;i<100000;i++)
        {
            lSuma=lSuma*i;
        }
        long lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoTestSumaLong=lTiempoFinTest-lTiempoInicioTest;
        
        
        lTiempoInicioTest=System.currentTimeMillis();
        double lfSuma=0;
        for (double i=0.0251846345;i<100000;i++)
        {
            lfSuma=lfSuma*i;
        }
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoTestSumaDouble=lTiempoFinTest-lTiempoInicioTest;
        
        lTiempoInicioTest=System.currentTimeMillis();
        double lfMultDouble=1.010358170518772849527;
        for (double i=1;i<100000;i++)
        {
            if(lfMultDouble>9999999)
                lfMultDouble=1.010358170518772849527;
            lfMultDouble=lfMultDouble*i;
        }
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoTestMultDouble=lTiempoFinTest-lTiempoInicioTest;
        
        lTiempoInicioTest=System.currentTimeMillis();
        long lfMultLong=1;
        for (long i=1;i<100000;i++)
        {
            if(lfMultLong>9999999)
                lfMultLong=1;
            lfMultLong=lfMultLong*i;
        }
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoTestMultLong=lTiempoFinTest-lTiempoInicioTest;
        
        lTiempoInicioTest=System.currentTimeMillis();
        long lfDivLong=999999999;
        for (long i=1;i<100000;i++)
        {
            if(lfDivLong<10)
                lfDivLong=999999999;
            lfDivLong=lfDivLong/i;
        }
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoTestDivLong=lTiempoFinTest-lTiempoInicioTest;
        
        lTiempoInicioTest=System.currentTimeMillis();
        double lfDivDouble=999999999.256256;
        for (double i=1.245262572;i<100000;i++)
        {
            if(lfDivDouble<10)
                lfDivDouble=999999999.256256;
            lfDivDouble=lfDivDouble/i;
        }
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoTestDivDouble=lTiempoFinTest-lTiempoInicioTest;
        
        lTiempoInicioTest=System.currentTimeMillis();
        long lCount=0;
        while(lCount<100000)
        {
            lCount++;
        }
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoCount=lTiempoFinTest-lTiempoInicioTest;
        
        
        lTiempoInicioTest=System.currentTimeMillis();
        long lBitWise=999999;
        for (long i=0;i<100000;i++)
        {
            lBitWise=lBitWise<<1;
            lBitWise=lBitWise>>1;
        }
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoBitWise=lTiempoFinTest-lTiempoInicioTest;
        
        
        m_myGraphics.setColor(200, 200, 200);
        lTiempoInicioTest=System.currentTimeMillis();
        for (long i=0;i<100000;i++)
        {
            //m_g.drawRect(1,1,200,200);
            m_myGraphics.drawLine(1,2,1,2);
        }
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoDrawRect=lTiempoFinTest-lTiempoInicioTest;
        
        

        
        int lNumOfPixels=this.getWidth()*this.getHeight();
        int lWidth=this.getWidth();
        int lHeight=this.getHeight();
        int[] imagenARGB = new int[lWidth*lHeight];
        //int whiteValue=8388607;
        
        lTiempoInicioTest=System.currentTimeMillis();            
        int whiteValue=0;
        boolean bWhite=false;
        int[] lineaHorizon=new int[]{1000,1000,1000,1000,1000,1000,1000,1000,1000,1000};
        for (int i=0;i<lNumOfPixels;i++)
        {
            whiteValue++;
            if(whiteValue>=lWidth)
                whiteValue=0;
            if (bWhite)
                imagenARGB[i]=whiteValue;
            else{
                if(i<lNumOfPixels-10)
                    System.arraycopy(lineaHorizon, 0, imagenARGB, 0, 10);
                else
                    imagenARGB[i]=0;
                
            }
            bWhite=!bWhite;
            //imagenARGB[i]=whiteValue;
            
        }
        
        
        
        //Image image=Image.createRGBImage(int[] rgb, int width, int height, boolean processAlpha);

//            int x=20;
//            int y=20;
//            whiteValue=8388607;
        //imagenARGB[x+y*lWidth]=whiteValue;

//        Image image=Image.createRGBImage(imagenARGB, lWidth, lHeight, false);
//        Image copy = Image.createImage(lWidth, lHeight);  
        
        //copy=Image.createRGBImage(imagenARGB, lWidth, lHeight, false);
        //m_g.drawImage(image, 0, 0, UP | LEFT);
//        m_g.drawRGB(imagenARGB,0,lWidth,0,0,lWidth,lHeight,false);  
        //int[] rgbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoDrawRect=lTiempoFinTest-lTiempoInicioTest;

        
        
//        lTiempoInicioTest=System.currentTimeMillis();
//        m_pVoxelEngine.renderMap();
//        lTiempoFinTest=System.currentTimeMillis();
//        m_lTiempoDrawRect=lTiempoFinTest-lTiempoInicioTest;

    }
    
    public void addKeyPressedListener(IKeyPressedAndReleasedListener pKeyPressedListener){
        m_vKeyPressedAndReleasedListeners.addElement(pKeyPressedListener);
    }
    
    public void eraseKeyPressedListener(IKeyPressedAndReleasedListener pKeyPressedListener){
        m_vKeyPressedAndReleasedListeners.removeElement(pKeyPressedListener);
    }
    
    public boolean isLeftPressed(){
        return m_bLeftPressed;
    }
    public boolean isRightPressed(){
        return m_bRightPressed;
    }
    public boolean isDownPressed(){
        return m_bDownPressed;
    }
    public boolean isUpPressed(){
        return m_bUpPressed;
    }
    public boolean isDownLeftPressed(){
        return m_bDownLeftPressed;
    }
    public boolean isDownRightPressed(){
        return m_bDownRightPressed;
    }
    public boolean isCentrePressed(){
        return m_bCentrePressed;
    }
    public CScene getScene(){
        return m_pScene;
    }
     
}

