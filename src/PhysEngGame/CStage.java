/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;
import PhysEngGame.Platforms.CPlatformDefault;
import PhysEngGame.Enemies.CEnemyTeam;
//import PhysEngGame.Players.CMortalPlayerDefault;
import PhysEngGame.Players.CMortalPlayer;
import PhysEngGame.Players.CPlayer;
import PhysEngGame.Screens.CStageFinishScreen;
import PhysEngGame.Screens.CStageIntroductionScreen;
import java.util.Vector;
/**
 *
 * @author Carlos
 */
public class CStage {

    private PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;
    public CScene m_pScene=null;
    public CSceneControl m_pSceneControl=null;
    private int m_bScenarioToBeCharged=CONSTANTS.GROUP_STAGE;
    
    private int m_lStageDataId=CONSTANTS.DEFAULT_STAGE;
    private String m_sStageName="";
    
    private CMortalPlayer m_pMortalPlayer=null;
    private Vector m_vPlatforms= new Vector(30);
    
    private long m_lTime=0;
    private long m_lTimeOld=0;

    private Vector m_vEnemyTeams = new Vector(30);
    private Vector m_vEvents = new Vector(30);
    private Vector m_vEnemiesApparitionPoints = new Vector(30);
            
    private CStageIntroductionScreen m_pStageIntroductionScreen=null;
    private CStageFinishScreen m_pStageFinishScreen=null;
  
    
    public CStage(PhysEngRunnableGameCanvas pPruebasGameCanvas, CScene pScene, CSceneControl pSceneControl){
        m_pPhysEngRunnableGameCanvas=pPruebasGameCanvas;
        m_pScene=pScene;
        m_pSceneControl=pSceneControl;
        
    }
    
    public void loadDefaultStage(Integer IntegerStageDataId){
        //To do
        //Do here, what is done in init()... and more

        //Load statics elements
        
        int lStageDataId=IntegerStageDataId.intValue();
        m_lStageDataId=lStageDataId;
        
        loadDefaultSize();
        //loadDefaultPlayer();
        loadDefaultStaticElements();
        loadDefaultEnemyTeams();
        loadDefaultPointsOfAppearance();
        
        m_sStageName="Default stage";
        
        m_pStageIntroductionScreen = new CStageIntroductionScreen(m_pPhysEngRunnableGameCanvas,m_pSceneControl,this);
        m_pStageFinishScreen = new CStageFinishScreen(m_pPhysEngRunnableGameCanvas,m_pSceneControl,this);
        
    }
    
    public void loadDefaultPointsOfAppearance(){
        
        float fX=7000;
        float fY=1000;
        CPointCartesianCoord pPoint=new CPointCartesianCoord(fX, fY);
        fX=2000;
        fY=2000;
        CPointCartesianCoord pPoint2=new CPointCartesianCoord(fX, fY);
        fX=3000;
        fY=3000;
        CPointCartesianCoord pPoint3=new CPointCartesianCoord(fX, fY);
        fX=4000;
        fY=4000;
        CPointCartesianCoord pPoint4=new CPointCartesianCoord(fX, fY);
        fX=5000;
        fY=5000;
        CPointCartesianCoord pPoint5=new CPointCartesianCoord(fX, fY);
        fX=6000;
        fY=6000;
        CPointCartesianCoord pPoint6=new CPointCartesianCoord(fX, fY);
        
        m_vEnemiesApparitionPoints.addElement(pPoint);
        m_vEnemiesApparitionPoints.addElement(pPoint2);
        m_vEnemiesApparitionPoints.addElement(pPoint3);
        m_vEnemiesApparitionPoints.addElement(pPoint4);
        m_vEnemiesApparitionPoints.addElement(pPoint5);
        m_vEnemiesApparitionPoints.addElement(pPoint6);
        
    }
    
    private void loadDefaultSize(){
        //Limits. floor, ceiling, walls
        float lfLeftLimit = 0f;
        float lfRightLimit = 10000f;
        float lfTopLimit = 10000f;
        float lfBottomLimit = 0f;
        m_pScene.setLimits(lfLeftLimit,lfRightLimit,lfTopLimit,lfBottomLimit);

    }
    
//    private void loadDefaultPlayer(){
//        
// 
//        m_pMortalPlayer=new CMortalPlayerDefault(m_pPhysEngRunnableGameCanvas,m_pScene);
//        m_pMortalPlayer.putPlayer(5000f, 1000f);
//    }
    public void putPlayer(CMortalPlayer pMortalPlayer){
        m_pMortalPlayer=pMortalPlayer;
        m_pMortalPlayer.putPlayer(500f,1000);
    }
    
    private void loadDefaultStaticElements(){

        
//        float fX=5300;
//        float fY=5000;
//        float fVx=0;
//        float fVy=0;
//        int lSize=1000;
//        float fDegreesTurned=90;
//        float fVAngular=0;
//        float fMass=1;
//        CImmobilePolygon Polygon1 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, fX, fY, fVx, fVy,lSize,fDegreesTurned,fVAngular,fMass);
//        Polygon1.m_bHandControlled = false;
//        Polygon1.m_bActivated = true;
//        Polygon1.m_lColorR=0;
//        Polygon1.m_lColorG=0;
//        Polygon1.m_lColorB=255;
//        Polygon1.m_sName="Azul";
//        Polygon1.init();
//        m_pScene.addPolygon(Polygon1);
//
//        fX=7300;
//        fY=7000;
//        fVx=0;
//        fVy=0;
//        lSize=1000;
//        fDegreesTurned=90;
//        fVAngular=0;
//        fMass=1;
//        CImmobilePolygon Polygon3 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, fX, fY, fVx, fVy,lSize,fDegreesTurned,fVAngular,fMass);
//        Polygon3.m_bHandControlled = false;
//        Polygon3.m_bActivated = true;
//        Polygon3.m_lColorR=0;
//        Polygon3.m_lColorG=0;
//        Polygon3.m_lColorB=255;
//        Polygon3.m_sName="Azul";
//        Polygon3.init();
//        m_pScene.addPolygon(Polygon3);
//        
//        CImmobilePolygon Polygon4 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 0f, 0f, 1000f, 0f,0f,1000f,0,255,0);        
//        m_pScene.addPolygon(Polygon4);
//        CImmobilePolygon Polygon5 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 0f, 10000f, 0f, 9000f,1000f,10000f,0,200,0);        
//        m_pScene.addPolygon(Polygon5);
//        CImmobilePolygon Polygon6 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 10000f, 0f, 10000f, 1000f,9000f,0f,0,155,0);        
//        m_pScene.addPolygon(Polygon6);
//        CImmobilePolygon Polygon7 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 10000f, 10000f, 9000f, 10000f,10000f,9000f,0,100,0);        
//        m_pScene.addPolygon(Polygon7);
//        m_pScene.addPolygon(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 7000f, 2000f, 9000f, 2000f,7000f,3000f,0,50,0));

        CPlatformDefault pPlatform=new CPlatformDefault(m_pPhysEngRunnableGameCanvas,m_pScene,this);
        
        //pPlatform.putPlatform(0,10000);
        pPlatform.putPlatform(10000,1000);
        m_vPlatforms.addElement(pPlatform);
        
        
    }
    private void loadDefaultEnemyTeams(){ 
        //m_vEnemyTeams.addElement(new CEnemyTeam(m_pPhysEngRunnableGameCanvas,m_pSceneControl,this,CONSTANTS.SIMPLE_ROCK,2,CONSTANTS.ORDER_APPEARANCE_AFTER_PREVIOUS_IN_RANDOM_PLACE));
        m_vEnemyTeams.addElement(new CEnemyTeam(m_pPhysEngRunnableGameCanvas,m_pSceneControl,this,CONSTANTS.SIMPLE_ROCK,1,CONSTANTS.ORDER_APPEARANCE_AFTER_PREVIOUS_IN_RANDOM_PLACE));

    }

    public void init(){
        //To do
        //Fill m_vEnemyTeams
        
        m_vEnemyTeams.addElement(new CEnemyTeam(m_pPhysEngRunnableGameCanvas,m_pSceneControl,this,CONSTANTS.SIMPLE_ROCK,4,CONSTANTS.ORDER_APPEARANCE_AFTER_PREVIOUS_IN_RANDOM_PLACE));
        
        if (m_bScenarioToBeCharged==CONSTANTS.SIMPLE_STAGE)
            initStageSimple();
        else if (m_bScenarioToBeCharged==CONSTANTS.GROUP_STAGE)
            initStageWithGroups();
        else
            initStageSimple();
    }
    
    private void initStageSimple()
    {
        //Limits. floor, ceiling, walls
        float lfLeftLimit = 1000f;
        float lfRightLimit = 35000f;
        float lfTopLimit = 35000f;
        float lfBottomLimit = 1000f;
        //m_lLimitsWidth = 1000;

        m_pScene.setLimits(lfLeftLimit,lfRightLimit,lfTopLimit,lfBottomLimit);

        //Player
        CPlayer Player = new CPlayer(m_pPhysEngRunnableGameCanvas,20000f,20000f,0f);
        Player.m_lColorR=0;
        Player.m_lColorG=0;
        Player.m_lColorB=0;
        //m_vPolygons.addElement((CPolygon)Player);

        m_pScene.addPolygon((CPolygon)Player);

        //Polygon 1
        CPolygon Polygon1 = new CPolygon(m_pPhysEngRunnableGameCanvas);
        Polygon1.m_lColorR=255;
        Polygon1.m_lColorG=0;
        Polygon1.m_lColorB=0;
        //Polygon1.m_fX = 12000;
        //Polygon1.m_fXOld = 12000;
        //Polygon1.m_fY = 12000;
        //Polygon1.m_fYOld = 12000;
        Polygon1.setInitPos(12000, 12000);
        Polygon1.m_fVx = 0;
        Polygon1.m_fVxOld = 0;
        Polygon1.m_fVy = 100;
        Polygon1.m_fVyOld = 100;
        Polygon1.m_lNumOfVertex = 3;
        Polygon1.m_lModRelVertexes[0] = 2000;
        Polygon1.m_lAngRelVertexes[0] = 350;
        Polygon1.m_lModRelVertexes[1] = 2100;
        Polygon1.m_lAngRelVertexes[1] = 90;
        Polygon1.m_lModRelVertexes[2] = 2200;
        Polygon1.m_lAngRelVertexes[2] = 190;

        Polygon1.m_bHandControlled = false;
        Polygon1.m_bActivated = true;
        Polygon1.m_fMass = 1;
        Polygon1.m_fDegree = 0;
        Polygon1.m_fDegreeOld = 0;
        Polygon1.m_fVAngular = 2;
        Polygon1.m_fVAngularOld = 2;

        //m_vPolygons.addElement(Polygon1);
        m_pScene.addPolygon(Polygon1);

    }

    private void initStageWithGroups() {
        //Limits. floor, ceiling, walls
        float lfLeftLimit = 0f;
        float lfRightLimit = 10000f;
        float lfTopLimit = 10000f;
        float lfBottomLimit = 0f;
        //float lfLimitsWidth = 500f;

        m_pScene.setLimits(lfLeftLimit,lfRightLimit,lfTopLimit,lfBottomLimit);

        //Polygons
        CPlayer Player = new CPlayer(m_pPhysEngRunnableGameCanvas,2000f,4100f,0f);
        Player.m_lColorR=200;
        Player.m_lColorG=0;
        Player.m_lColorB=0;
        Player.m_sName="Player";
        Player.m_fVx=10;
        Player.m_fVxOld=10;
        Player.init();
        //m_vPolygons.addElement((CPolygon)Player);
        m_pScene.addPolygon((CPolygon)Player);

  


        float fX=5300;
        float fY=5000;
        float fVx=0;
        float fVy=0;
        int lSize=1000;
        float fDegreesTurned=90;
        float fVAngular=0;
        float fMass=1;
        CImmobilePolygon Polygon1 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, fX, fY, fVx, fVy,lSize,fDegreesTurned,fVAngular,fMass);
        Polygon1.m_bHandControlled = false;
        Polygon1.m_bActivated = true;
        Polygon1.m_lColorR=0;
        Polygon1.m_lColorG=0;
        Polygon1.m_lColorB=255;
        Polygon1.m_sName="Azul";
        Polygon1.init();
        m_pScene.addPolygon(Polygon1);

        fX=7300;
        fY=7000;
        fVx=0;
        fVy=0;
        lSize=1000;
        fDegreesTurned=90;
        fVAngular=0;
        fMass=1;
        CImmobilePolygon Polygon3 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, fX, fY, fVx, fVy,lSize,fDegreesTurned,fVAngular,fMass);
        Polygon3.m_bHandControlled = false;
        Polygon3.m_bActivated = true;
        Polygon3.m_lColorR=0;
        Polygon3.m_lColorG=0;
        Polygon3.m_lColorB=255;
        Polygon3.m_sName="Azul";
        Polygon3.init();
        m_pScene.addPolygon(Polygon3);
        

        
        CImmobilePolygon Polygon4 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 0f, 0f, 1000f, 0f,0f,1000f,0,255,0);        
        m_pScene.addPolygon(Polygon4);
        CImmobilePolygon Polygon5 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 0f, 10000f, 0f, 9000f,1000f,10000f,0,200,0);        
        m_pScene.addPolygon(Polygon5);
        CImmobilePolygon Polygon6 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 10000f, 0f, 10000f, 1000f,9000f,0f,0,155,0);        
        m_pScene.addPolygon(Polygon6);
        CImmobilePolygon Polygon7 = new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 10000f, 10000f, 9000f, 10000f,10000f,9000f,0,100,0);        
        m_pScene.addPolygon(Polygon7);
        

                
        m_pScene.addPolygon(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 7000f, 2000f, 9000f, 2000f,7000f,3000f,0,50,0));

        

//        //Group1
//        CGroup Group=new CGroup(m_pPhysEngRunnableGameCanvas, this, Polygon1);
//        //Group.addObjectToGroup(Polygon2);
//        m_vGroups.addElement(Group);

        //Group1, Polygon 1
        //public CPolygon(PhysEngRunnableGameCanvas pPruebasGameCanvas, float fX, float fY, float fVx, float fVy, int lSize, float fDegreesTurned, float fVAngular, float fMass)
        float fX2=3500;
        float fY2=3800;
        float fVx2=0;
        float fVy2=0;
        int lSize2=500;
        float fDegreesTurned2=220;
        float fVAngular2=0;
        float fMass2=1;
        //CPolygon Polygon1 = new CPolygon(m_pPhysEngRunnableGameCanvas, fX, fY, fVx, fVy,lSize,fDegreesTurned,fVAngular,fMass);
        CPolygon Polygon2 = new CPolygon(m_pPhysEngRunnableGameCanvas, fX2, fY2, fVx2, fVy2,lSize2,fDegreesTurned2,fVAngular2,fMass2);
        Polygon2.m_bHandControlled = false;
        Polygon2.m_bActivated = true;
        Polygon2.m_lColorR=200;
        Polygon2.m_lColorG=0;
        Polygon2.m_lColorB=255;
        Polygon2.m_sName="Morado";
        Polygon2.init();

        CPolygon Polygon2B = new CPolygon(m_pPhysEngRunnableGameCanvas, fX2+800, fY2, fVx2, fVy2,lSize2,fDegreesTurned2,fVAngular2,fMass2);
        Polygon2B.m_bHandControlled = false;
        Polygon2B.m_bActivated = true;
        Polygon2B.m_lColorR=200;
        Polygon2B.m_lColorG=100;
        Polygon2B.m_lColorB=255;
        Polygon2B.m_sName="MoradoB";
        Polygon2B.init();

        
        //m_vPolygons.addElement(Polygon2);
        m_pScene.addPolygon(Polygon2);

        //Group1
        CGroup Group2=new CGroup(m_pPhysEngRunnableGameCanvas, m_pScene, Polygon2);
        Group2.m_fVAngular=0;
        Group2.m_fVAngularOld=0;
        Group2.addObjectToGroup(Polygon2B);
        //m_vGroups.addElement(Group2);
        m_pScene.addGroup(Group2);

    }
    
    public void nextStates(){

        
    }
    
     public void saveOldStates(){
        m_lTimeOld=m_lTime;
     }
     
     public CEnemyTeam getPreviousEnemyTeam(CEnemyTeam pEnemyTeam){
         int indexOfEnemyTeam=m_vEnemyTeams.indexOf(pEnemyTeam);         
         if(0==indexOfEnemyTeam)
             return null;
         else
             return (CEnemyTeam)m_vEnemyTeams.elementAt(indexOfEnemyTeam-1);         
     }
     public int getNumberOfEnemiesInScene(){
         int lNumberOfEnemiesInScene=0;
         for(int i=0;i<m_vEnemyTeams.size();i++){
             lNumberOfEnemiesInScene=lNumberOfEnemiesInScene+((CEnemyTeam)m_vEnemyTeams.elementAt(i)).getNumberOfEnemiesInScene();
         }
         return lNumberOfEnemiesInScene;
     }
     public boolean isFinished(){
         //to do
         //see if all m_vEnemyTeams are destroyed to return true
         //provisionally
         if (getNumberOfEnemiesDestroyed()==getNumberOfEnemiesOfTeams()){
             return true;
         }
             
         return false;
     }
     public int getNumberOfEnemiesDestroyed(){
         int lNumberOfEnemiesDestroyed=0;
         for(int i=0;i<m_vEnemyTeams.size();i++){
             lNumberOfEnemiesDestroyed=lNumberOfEnemiesDestroyed+((CEnemyTeam)m_vEnemyTeams.elementAt(i)).getNumberOfEnemiesDestroyed();
         }
         return lNumberOfEnemiesDestroyed;
     }
     public int getNumberOfEnemiesOfTeams(){
         int lNumberOfEnemies=0;
         for(int i=0;i<m_vEnemyTeams.size();i++){
             lNumberOfEnemies=lNumberOfEnemies+((CEnemyTeam)m_vEnemyTeams.elementAt(i)).getNumberOfEnemiesOfTeam();
         }
         return lNumberOfEnemies;
     }
     public void saveDataForStatistics(){
         //save scores etc.
     }
     
     public void run(){
         nextStates();
         
         //To do
        //If condition to appear nextEnemy: make it appear in an appropiate place and erase it from vector
        for (int lEnemyTeam = 0; lEnemyTeam < m_vEnemyTeams.size(); lEnemyTeam++) {
            CEnemyTeam thisObject = ((CEnemyTeam) (m_vEnemyTeams.elementAt(lEnemyTeam)));
            thisObject.nextStates();
            //if (thisObject IsConditionToAppear()) {
            //    m_vEnemyTeams.removeElementAt(lEnemyTeam);
        }
         
        for (int lPlatform = 0; lPlatform < m_vPlatforms.size(); lPlatform++)
        {
            CPlatformDefault thisObject = ((CPlatformDefault) (m_vPlatforms.elementAt(lPlatform)));
            thisObject.nextStates();
            
        }
         
         saveOldStates();
     }
     public CStageIntroductionScreen getIntroductionScreen(){
         return m_pStageIntroductionScreen;
     }
     public CStageFinishScreen getFinishScreen(){
         return m_pStageFinishScreen;
     }
     
     public Vector getApparitionPoints(){
         return m_vEnemiesApparitionPoints;
     }
     
     public void clearStage(){
         resetVariables();
         m_pScene.clearScene();         
     }
     public void resetVariables(){
        //m_pPhysEngRunnableGameCanvas=null;
        //m_pScene=null;
        //m_pSceneControl=null;
        m_bScenarioToBeCharged=CONSTANTS.GROUP_STAGE;

        m_lStageDataId=CONSTANTS.DEFAULT_STAGE;
        //m_pMortalPlayer=null;
        m_vPlatforms.removeAllElements();

        m_lTime=0;
        m_lTimeOld=0;

        m_vEnemyTeams.removeAllElements();
        m_vEvents.removeAllElements();
        m_vEnemiesApparitionPoints.removeAllElements();

        m_pStageIntroductionScreen=null;
        m_pStageFinishScreen=null;
        
        m_sStageName="";
     }
     
     public String getStageName(){
        return m_sStageName;
     }
    
             

    
}

