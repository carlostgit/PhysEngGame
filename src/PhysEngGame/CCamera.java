/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 * 
 *    z
 *    ^
 *    . 
 *    .
 *    ------------------------> y
 *    
 * 
 * ---->YCam
 *     Cam ----------------------------------
 *      .     .  
 *      . __/    .    .
 *      .  CamPitch .       .                                         
 *      .             B .             .                               
 *      .                   .                  .
 *      . Altitude              .       A            .
 *      . ------------------------------------------------------ Obj
 *                                    .            .D         -->YObj
 *                                       C . RectAngle 
 *                                             .
 * 
 * 
 *    Altitude= ModuleOf(ZCam-ZObj,XObj-XCam)
 *    B=Altitude/cos(CamPitch) = ModuleOf(Altitude,tan(CamPitch)*Altitude)
 *    A=YObj - YCam - Altitude*tan(CamPitch)
 *    C=A*sin(CamPitch)
 *    D=A*cos(CamPitch)
 *    
 *      
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class CCamera {

    //Camera Point
//    private float m_fCamX=0;
//    private float m_fCamY=0;
//    private float m_fCamZ=1000;
//    private float m_fHeading=0;
//    private float m_fPitch=75;
//   
    
     //Camera Point
    private float m_fCamObjX=0;
    private float m_fCamObjY=0;
    private float m_fAltitude=1500;
    private float m_fHeading=0;
    private float m_fPitch=60;
    private float m_fAngleOfVision=60f;
    
    //Aux variables. (Rotation matrix)    
    private float m_fSineHeading=0.0f;
    private float m_fCosineHeading=0.0f;
    private float m_fSinePitch=0.0f;
    private float m_fCosinePitch=0.0f;
    private float m_fTanPitch=0.0f;    
    private float m_B_ForNormalAltitude=0.0f; //Altitude/cosPitch
    private float m_CamCentreY_ForNormalAltitude=0.0f; //Altitude*tan(Pitch)     
    //
    
    
    private float m_fBackgroundDeep=1000;

    private CScene m_Scene=null;
    private PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;
    
    private CGraphics m_pMyGraphics=null;
    
    public CCamera(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CScene pScene,CGraphics pMyGraphics){
        m_pPhysEngRunnableGameCanvas=pPhysEngRunnableGameCanvas;
        m_Scene=pScene;
        m_pMyGraphics=pMyGraphics;
    }
    
    
    
//    public CPointCartesianCoord GetPointInCameraPerspective(CPointCartesianCoord pCoord){
//        
//        CPointCartesianCoord pNewCoord=new CPointCartesianCoord();
//        return pNewCoord;
//    }
//     public int GetXCoordinateInScreenWithPersp(float fX, float fY, float fAngle, float fAltitude) {  
////        System.out.println("Entrando en GetXCoordinateInScreenWithPersp");
//        float fGiro=-m_fHeading;
//        float fObjetivePointX=fX-m_fCamObjX;
//        float fObjetivePointY=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch);
//        float fXObjetivePointTurned=fObjetivePointX*CMath.cosine(fGiro)-fObjetivePointY*CMath.sine(fGiro);
//        float fYObjetivePointTurned=fObjetivePointX*CMath.sine(fGiro)+fObjetivePointY*CMath.cosine(fGiro);
//        float fXCamPointTurned=fXObjetivePointTurned;
//        float fYCamPointTurned=fYObjetivePointTurned+fAltitude*CMath.tanTable(m_fPitch);
//        
//        float fDistanceToPointProyectonOnYZPlane=CMath.calculateModule(fAltitude, fYCamPointTurned);///CMath.cosineTable(CMath.aTanTable(fYLocTurned/fAltitude));
//        float fTan=fXCamPointTurned/fDistanceToPointProyectonOnYZPlane;
//        return (int)((getWidth()>>1) + 300*fTan);
//
//    }
//    public int GetYCoordinateInScreenWithPersp(float fX, float fY, float fAngle, float fAltitude) {
//
//        float fGiro=-m_fHeading;
//        float fObjetivePointX=fX-m_fCamObjX;
//        float fObjetivePointY=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch);
//        float fXObjetivePointTurned=fObjetivePointX*CMath.cosine(fGiro)-fObjetivePointY*CMath.sine(fGiro);
//        float fYObjetivePointTurned=fObjetivePointX*CMath.sine(fGiro)+fObjetivePointY*CMath.cosine(fGiro);
//        float fXCamPointTurned=fXObjetivePointTurned;
//        float fYCamPointTurned=fYObjetivePointTurned+fAltitude*CMath.tanTable(m_fPitch);
//        
//        //float fDistanceToPointProyectonOnXZPlane=CMath.calculateModule(fAltitude, fXCamPointTurned);///CMath.cosineTable(CMath.aTanTable(fXLocTurned/fAltitude));
//        
//        float fAuxB=fAltitude/CMath.cosineTable(m_fPitch);
//        
//        float fAuxA=fYObjetivePointTurned;
//        float fAuxC=fAuxA*CMath.sineTable(m_fPitch);
//        
//        //return (int)((getHeight()>>1) - 300*(fAuxA*CMath.cosineTable(m_fPitch))/(fAuxB+fAuxC));
//        return (int)((getHeight()>>1) - 300*(fAuxA*CMath.cosineTable(m_fPitch))/(CMath.calculateModule((fAuxB+fAuxC),fXCamPointTurned)));
//        
//    }
//    public int GetDistanceInScreenWithPersp(float fX, float fY,float fSize, float fAngle, float fAltitude){        
////        System.out.println("Entrando en GetDistanceInScreenWithPersp");
//
//        float fGiro=-m_fHeading;
//        float fObjetivePointX=fX-m_fCamObjX;
//        float fObjetivePointY=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch);
//        float fXObjetivePointTurned=fObjetivePointX*CMath.cosine(fGiro)-fObjetivePointY*CMath.sine(fGiro);
//        float fYObjetivePointTurned=fObjetivePointX*CMath.sine(fGiro)+fObjetivePointY*CMath.cosine(fGiro);
//        float fXCamPointTurned=fXObjetivePointTurned;
//        float fYCamPointTurned=fYObjetivePointTurned+fAltitude*CMath.tanTable(m_fPitch);
//        
//        float fOffCentreModule=CMath.calculateModule(fXCamPointTurned,fYCamPointTurned);
//        float fRealAltitude=CMath.calculateModule(fOffCentreModule,fAltitude);
//        //float fAngleToPixels=getWidth()/m_fAngleOfVision; //Angle of vision. This should be a param
//        float fTan=fSize/fRealAltitude;
//        return (int)(300*fTan);
//        //return (int)(fAngleToPixels*CMath.aTanTable(fTan));            
//    }
    
    public void controlCamera(){
        CPointCartesianCoord pPointCoord=new CPointCartesianCoord();
        CObject pTargetObject = m_Scene.GetCamTarget();
        if(null!=pTargetObject)
        {
            m_fCamObjX=pTargetObject.getX();
            m_fCamObjY=pTargetObject.getY();
            m_fHeading=90f-pTargetObject.getDegree();
        }
        //Aux variables. (Rotation matrix)    
        m_fSineHeading=CMath.sine(m_fHeading);
        m_fCosineHeading=CMath.cosine(m_fHeading);
        m_fSinePitch=CMath.sine(m_fPitch);
        m_fCosinePitch=CMath.cosine(m_fPitch);
        m_B_ForNormalAltitude=m_fAltitude/m_fCosinePitch; //Altitude/cosPitch
        m_CamCentreY_ForNormalAltitude=m_fAltitude*CMath.tanTable(m_fPitch); //Altitude*tan(Pitch)        

        
    }
    public int GetXCoordinateInScreenWithPersp(float fX, float fY, float fAngle, float fAltitude) {  
//        System.out.println("Entrando en GetXCoordinateInScreenWithPersp");
        float fGiro=m_fHeading;
        float fObjetivePointX=fX-m_fCamObjX;
        float fObjetivePointY=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch);
        float fXObjetivePointTurned=fObjetivePointX*CMath.cosine(fGiro)-fObjetivePointY*CMath.sine(fGiro);
        float fYObjetivePointTurned=fObjetivePointX*CMath.sine(fGiro)+fObjetivePointY*CMath.cosine(fGiro);
        float fXCamPointTurned=fXObjetivePointTurned;
        float fYCamPointTurned=fYObjetivePointTurned+fAltitude*CMath.tanTable(m_fPitch);
        
        float fDistanceToPointProyectonOnYZPlane=CMath.calculateModule(fAltitude, fYCamPointTurned);///CMath.cosineTable(CMath.aTanTable(fYLocTurned/fAltitude));
        float fTan=fXCamPointTurned/fDistanceToPointProyectonOnYZPlane;
        return (int)((m_pPhysEngRunnableGameCanvas.getWidth()>>1) + 300*fTan);        
        
    }
    public int GetYCoordinateInScreenWithPersp(float fX, float fY, float fAngle, float fAltitude) {
        /*
        float fGiro=m_fHeading;
        float fObjetivePointX=fX-m_fCamObjX; //X respecto al objetivo de la camara. (El objetivo de la camara es el que hace intersección entre la linea de dirección de la cámara y el plano del punto (del que estamos sacando las coordenadas de camara).
        float fObjetivePointY=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch); //Y respecto al objetivo de la camara
        float fXObjetivePointTurned=fObjetivePointX*CMath.cosine(fGiro)-fObjetivePointY*CMath.sine(fGiro); //X respecto al objetivo de la camara teniendo en cuenta el giro
        float fYObjetivePointTurned=fObjetivePointX*CMath.sine(fGiro)+fObjetivePointY*CMath.cosine(fGiro); //Y respecto al objetivo de la camara teniendo en cuenta el giro
        float fXCamPointTurned=fXObjetivePointTurned; //X respecto a la cámara
        float fYCamPointTurned=fYObjetivePointTurned+fAltitude*CMath.tanTable(m_fPitch); //Y respecto a la cámara
        
        //float fDistanceToPointProyectonOnXZPlane=CMath.calculateModule(fAltitude, fXCamPointTurned);///CMath.cosineTable(CMath.aTanTable(fXLocTurned/fAltitude));
        
        float fAuxB=fAltitude/CMath.cosineTable(m_fPitch);
        
        float fAuxA=fYObjetivePointTurned;
        float fAuxC=fAuxA*CMath.sineTable(m_fPitch);
                
        if(CONSTANTS.CONSIDER_ABERRATION_OF_LATERAL_DISTANCE)        
            return (int)((m_pPhysEngRunnableGameCanvas.getHeight()>>1) - 300*(fAuxA*CMath.cosineTable(m_fPitch))/(CMath.calculateModule((fAuxB+fAuxC),fXCamPointTurned))); //se añade lo de fXCamPointTurned para introducir un efecto de aberración por distancia. Porque el angulo de la camara virtual será mucho mayor que el ángulo con el que verá el ojo del usuario la pantalla del dispositivo
        else
            return (int)((m_pPhysEngRunnableGameCanvas.getHeight()>>1) - 300*(fAuxA*CMath.cosineTable(m_fPitch))/(fAuxB+fAuxC));
        */
        ///
        float fXRelToCamCentre=fX-m_fCamObjX; //X respecto al objetivo de la camara. (El objetivo de la camara es el que hace intersección entre la linea de dirección de la cámara y el plano del punto (del que estamos sacando las coordenadas de camara).
        float fYRelToCamCentre=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch); //Y respecto al objetivo de la camara
        
        float fAuxA=fXRelToCamCentre*m_fSineHeading+fYRelToCamCentre*m_fCosineHeading; //Y relative to cam centre after turning m_fHeading degrees        
        float fYAngleObjetive=fAuxA*m_fCosinePitch/(m_B_ForNormalAltitude+fAuxA*m_fSinePitch);

        float fYRelativeToCamCentre=300*fYAngleObjetive;
        
        return (int)((m_pPhysEngRunnableGameCanvas.getHeight()>>1)-fYRelativeToCamCentre);                        
    }
    public CPointCartesianCoord GetPointInScreeWithPersp(float fX, float fY){
        float fXRelToCamCentre=fX-m_fCamObjX; //X respecto al objetivo de la camara. (El objetivo de la camara es el que hace intersección entre la linea de dirección de la cámara y el plano del punto (del que estamos sacando las coordenadas de camara).
        float fYRelToCamCentre=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch); //Y respecto al objetivo de la camara
        
        float fYRelToCamCentreTurned=fXRelToCamCentre*m_fSineHeading+fYRelToCamCentre*m_fCosineHeading; // = A
        float fYTanAngleObjetive=fYRelToCamCentreTurned*m_fCosinePitch/(m_B_ForNormalAltitude+fYRelToCamCentreTurned*m_fSinePitch);

        float fYPixelsRelativeToCamCentre=300*fYTanAngleObjetive;
        
        float fYCam=((m_pPhysEngRunnableGameCanvas.getHeight()>>1)-fYPixelsRelativeToCamCentre);                        
                
        float fXRelToCamCentreTurned=fXRelToCamCentre*m_fCosineHeading-fYRelToCamCentre*m_fSineHeading; //X relative to cam centre after turning m_fHeading degrees        
        float fDistanceToPointProyectonOnYZPlane=m_B_ForNormalAltitude+(fYRelToCamCentreTurned*m_fSinePitch);//más o menos? No es así, pero por no calcular el modulo...
        float fXTanAngleObjetive=fXRelToCamCentreTurned/fDistanceToPointProyectonOnYZPlane;
        float fXPixelsRelativeToCamCentre=300*fXTanAngleObjetive;
        float fXCam=((m_pPhysEngRunnableGameCanvas.getWidth()>>1) + fXPixelsRelativeToCamCentre);        
        return (new CPointCartesianCoord(fXCam,fYCam));

    }
    public CPointInScreen GetPointInScreeWithPersp(float fX, float fY, float fSize){
        //Y
        float fXRelToCamCentre=fX-m_fCamObjX; //X respecto al objetivo de la camara. (El objetivo de la camara es el que hace intersección entre la linea de dirección de la cámara y el plano del punto (del que estamos sacando las coordenadas de camara).
        float fYRelToCamCentre=fY-m_fCamObjY;//+fAltitude*CMath.tanTable(m_fPitch); //Y respecto al objetivo de la camara
                
        float fYRelToCamCentreTurned=fXRelToCamCentre*m_fSineHeading+fYRelToCamCentre*m_fCosineHeading; // = A
        
        if (fYRelToCamCentreTurned<-3000)
            fYRelToCamCentreTurned=-3000;
//        if (fYRelToCamCentreTurned>2000000)
//            fYRelToCamCentreTurned=2000000;
                
        float fYTanAngleObjetive=fYRelToCamCentreTurned*m_fCosinePitch/(m_B_ForNormalAltitude+fYRelToCamCentreTurned*m_fSinePitch);
        
        float fYPixelsRelativeToCamCentre=300*fYTanAngleObjetive;
        
        
        float fYCam=((m_pPhysEngRunnableGameCanvas.getHeight()>>1)-fYPixelsRelativeToCamCentre);                        
           
        //X        
        float fXRelToCamCentreTurned=fXRelToCamCentre*m_fCosineHeading-fYRelToCamCentre*m_fSineHeading; //X relative to cam centre after turning m_fHeading degrees        
        
//        if (fXRelToCamCentreTurned<-1000000)
//            fXRelToCamCentreTurned=-1000000;        
//        if (fXRelToCamCentreTurned>1000000)
//            fXRelToCamCentreTurned=1000000;        
        
        float fDistanceToPointProyectonOnYZPlane=m_B_ForNormalAltitude+(fYRelToCamCentreTurned*m_fSinePitch);//más o menos? No es así, pero por no calcular el modulo...
        float fXTanAngleObjetive=fXRelToCamCentreTurned/fDistanceToPointProyectonOnYZPlane;
        float fXPixelsRelativeToCamCentre=300*fXTanAngleObjetive;
               
        float fXCam=((m_pPhysEngRunnableGameCanvas.getWidth()>>1) + fXPixelsRelativeToCamCentre);
        
        float fSizeInScreen=0.0f;
        if(fSize>0)
        {
            //Size
    //        float fYRelToCam=m_CamCentreY_ForNormalAltitude+fYRelToCamCentreTurned; //Altitude*tan(Pitch) 
    //        float fXRelToCam=fXRelToCamCentreTurned;
    //        float fModuleXYRelToCam=CMath.calculateModule(fYRelToCam, fXRelToCam);
            //float fDistanceToObject=CMath.calculateModule(fDistanceToPointProyectonOnYZPlane, fXRelToCamCentreTurned);        
            float fDistanceToObject=fDistanceToPointProyectonOnYZPlane;    //Se puede hacer esta simplificación    
            float fTanSize=fSize/fDistanceToObject;
            fSizeInScreen=300*fTanSize;
        }                
        return (new CPointInScreen(fXCam,fYCam,fSizeInScreen));

    }
    public int GetDistanceInScreenWithPersp(float fX, float fY,float fSize, float fAngle, float fAltitude){        
//        System.out.println("Entrando en GetDistanceInScreenWithPersp");

        float fGiro=m_fHeading;
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
    public void fillRect(float x, float y, float width, float height){
        //CPointCartesianCoord pPointCartesianCoord1=GetPointInScreeWithPersp(x, y);
        CPointInScreen pPointInScreen=GetPointInScreeWithPersp(x, y,width);
        m_pMyGraphics.fillRect(
                (int)(pPointInScreen.m_fX),
                (int)(pPointInScreen.m_fY),
                (int)(pPointInScreen.m_fSize),
                (int)(pPointInScreen.m_fSize));
        
        
//         m_pMyGraphics.fillRect(
//                GetXCoordinateInScreenWithPersp(x,y,0,m_fAltitude),
//                GetYCoordinateInScreenWithPersp(x,y,0,m_fAltitude),
//                GetDistanceInScreenWithPersp(x,y,width,0,m_fAltitude),
//                GetDistanceInScreenWithPersp(x,y,height,0,m_fAltitude));
//        
    }
    public void fillRectInBackground(float x, float y, float width, float height){

        CPointInScreen pPointInScreen=GetPointInScreeWithPersp(x, y,width);
        m_pMyGraphics.fillRect(
                (int)(pPointInScreen.m_fX),
                (int)(pPointInScreen.m_fY),
                (int)(pPointInScreen.m_fSize),
                (int)(pPointInScreen.m_fSize));
        
        
//        m_pMyGraphics.fillRect(
//                GetXCoordinateInScreenWithPersp(x,y,0,m_fAltitude+m_fBackgroundDeep),
//                GetYCoordinateInScreenWithPersp(x,y,0,m_fAltitude+m_fBackgroundDeep),
//                GetDistanceInScreenWithPersp(x,y,width,0,m_fAltitude+m_fBackgroundDeep),
//                GetDistanceInScreenWithPersp(x,y,height,0,m_fAltitude+m_fBackgroundDeep));
        
    }
    public void drawLine(float x1, float y1, float x2, float y2) {
        CPointInScreen pPointInScreen1=GetPointInScreeWithPersp(x1, y1,0);
        CPointInScreen pPointInScreen2=GetPointInScreeWithPersp(x1, y1,0);
        
        m_pMyGraphics.drawLine((int)(pPointInScreen1.m_fX),(int)(pPointInScreen1.m_fY),
                (int)(pPointInScreen2.m_fX),(int)(pPointInScreen2.m_fY));
        
//        m_pMyGraphics.drawLine(GetXCoordinateInScreenWithPersp(x1,y1,0,m_fAltitude),GetYCoordinateInScreenWithPersp(x1,y1,0,m_fAltitude),
//                GetXCoordinateInScreenWithPersp(x2,y2,0,m_fAltitude),GetYCoordinateInScreenWithPersp(x2,y2,0,m_fAltitude));
//        
//        m_g.drawLine(GetXCoordinateInScreen(x1),GetYCoordinateInScreen(y1),
//                GetXCoordinateInScreen(x2), GetYCoordinateInScreen(y2));
    }
    public void fillTriangle(float x1, float y1, float x2, float y2, float x3, float y3){
      //m_pMyGraphics.setColor(255,0,0);
      //m_pMyGraphics.fillTriangle(20, 20, 200, 200, 20, 200);
      
        CPointInScreen pPointInScreen1=GetPointInScreeWithPersp(x1, y1,0);
        CPointInScreen pPointInScreen2=GetPointInScreeWithPersp(x2, y2,0);
        CPointInScreen pPointInScreen3=GetPointInScreeWithPersp(x3, y3,0);
        
        m_pMyGraphics.fillTriangle((int)(pPointInScreen1.m_fX), (int)(pPointInScreen1.m_fY),
                (int)(pPointInScreen2.m_fX), (int)(pPointInScreen2.m_fY),
                (int)(pPointInScreen3.m_fX), (int)(pPointInScreen3.m_fY));
       
//       m_pMyGraphics.fillTriangle(GetXCoordinateInScreenWithPersp(x1,y1,0,m_fAltitude), GetYCoordinateInScreenWithPersp(x1,y1,0,m_fAltitude),
//                GetXCoordinateInScreenWithPersp(x2,y2,0,m_fAltitude), GetYCoordinateInScreenWithPersp(x2,y2,0,m_fAltitude),
//                GetXCoordinateInScreenWithPersp(x3,y3,0,m_fAltitude), GetYCoordinateInScreenWithPersp(x3,y3,0,m_fAltitude));
//                
       // m_g.fillTriangle(GetXCoordinateInScreen(x1), GetYCoordinateInScreen(y1),
       //         GetXCoordinateInScreen(x2), GetYCoordinateInScreen(y2),
       //         GetXCoordinateInScreen(x3), GetYCoordinateInScreen(y3));
    }

}
