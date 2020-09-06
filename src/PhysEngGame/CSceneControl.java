/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;
import PhysEngGame.Screens.*;
import java.util.Vector;
/**
 *
 * @author Carlos
 */
public class CSceneControl {

    private PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;
    public CScene m_pScene=null;
    boolean m_bChangeTonNextStageInNextCycle=false;
    
    private long m_lTime=0;
    private long m_lTimeOld=0;

    private Vector m_vStageIDs = new Vector(30);
    private CStage m_pStage=null;
            
    private int m_lIndexOfActualStage=0;
    
    
    public static final int INTRODUCTION_SCREEN = 1;
    public static final int MENU_SCREEN = 2;
    public static final int STAGE_INTRODUCTION_SCREEN = 3;
    public static final int PLAYING_STAGE_SCREEN = 4;
    public static final int PAUSE_SCREEN = 5;
    public static final int STAGE_FINISH_SCREEN = 6;
    public static final int FINISH_SCREEN = 7;
    public static final int GAME_OVER_SCREEN = 8;
    public static final int ENTER_NAME_SCREEN = 9;
    
    private int m_lState=MENU_SCREEN;
    private int m_lStateOld=MENU_SCREEN;
    
    private CIntroductionScreen m_pIntroductionScreen=null;
    private CMenuScreen m_pMenuScreen=null;            
    private CPauseScreen m_pPauseScreen=null;
    private CFinishScreen m_pFinishScreen=null;
    private CGameOverScreen m_pGameOverScreen=null;
    private CEnterNameScreen m_pEnterNameScreen=null;
            
    //private CGameScore m_GameScore=new CGameScore();
    CPlayerControl m_pPlayerControl=null;
    
    public CSceneControl(PhysEngRunnableGameCanvas pPruebasGameCanvas, CScene pScene){
        m_pPhysEngRunnableGameCanvas=pPruebasGameCanvas;
        m_pScene=pScene;        
        loadData();
    }

    public void loadData(){

        //Load data
        //In the future, I'll have to do this loading data from a file
        m_vStageIDs.addElement(new Integer(CONSTANTS.DEFAULT_STAGE));
        m_vStageIDs.addElement(new Integer(CONSTANTS.STAGE_1));
        m_vStageIDs.addElement(new Integer(CONSTANTS.STAGE_2));
        
        m_pPlayerControl=new CPlayerControl(m_pPhysEngRunnableGameCanvas, this, m_pScene);
        
        m_pStage=new CStage(m_pPhysEngRunnableGameCanvas,m_pScene,this);
        m_pStage.loadDefaultStage((Integer)m_vStageIDs.firstElement());
        //m_vStages.addElement(pStageSimple);
        
        //todo No se pueden cargar las pantallas desde el principio. Se deben cargar solo cuando llegas a ellas
        
//        CStage pStageSimple2=new CStage(m_pPhysEngRunnableGameCanvas,m_pScene,this);
//        pStageSimple2.loadDefaultStage();
//        m_vStages.addElement(pStageSimple2);
    
        m_pIntroductionScreen = new CIntroductionScreen(m_pPhysEngRunnableGameCanvas,this);
        m_pMenuScreen = new CMenuScreen(m_pPhysEngRunnableGameCanvas,this);
        m_pPauseScreen = new CPauseScreen(m_pPhysEngRunnableGameCanvas,this);
        m_pFinishScreen = new CFinishScreen(m_pPhysEngRunnableGameCanvas,this);
        m_pGameOverScreen = new CGameOverScreen(m_pPhysEngRunnableGameCanvas,this);
        m_pEnterNameScreen = new CEnterNameScreen(m_pPhysEngRunnableGameCanvas,this);
        
        CGameInfo.reset();

        
    }

    
    public void nextStates(){
        
        
    }
    
     public void saveOldStates(){
        m_lTimeOld=m_lTime;
        m_lStateOld=m_lState;
     }

     public void setState(int lState){
         m_lState=lState;
     }
     public int getState(){
         return m_lStateOld;
     }
     public CStage getStage(){
         return m_pStage;
     }
     private void changeToStage(int lIndexOfNewStage){
         if(lIndexOfNewStage<m_vStageIDs.size()){
             m_pStage.saveDataForStatistics();
             m_pStage.clearStage();
             m_pScene.clearScene();
             m_lIndexOfActualStage=lIndexOfNewStage;
             m_pStage.loadDefaultStage((Integer)m_vStageIDs.elementAt(lIndexOfNewStage));
             this.setState(STAGE_INTRODUCTION_SCREEN);
         }
     }
     public void changeToNextStageInNextCycle(){
         m_bChangeTonNextStageInNextCycle=true;
     }
     private void changeToNextStage(){
         if(m_lIndexOfActualStage+1<m_vStageIDs.size()){
             m_pStage.saveDataForStatistics();
             m_pStage.clearStage();
             m_pScene.clearScene();             
             m_lIndexOfActualStage++;
             m_pStage.loadDefaultStage((Integer)m_vStageIDs.elementAt(m_lIndexOfActualStage));
             this.setState(STAGE_INTRODUCTION_SCREEN);
         }
         else
         {             
             this.setState(FINISH_SCREEN);             
         }
     }
     private void resetSceneControl(){

            m_pStage.saveDataForStatistics();
            m_pStage.clearStage();
            m_pScene.clearScene();
            m_pPlayerControl.reset();
            CGameInfo.reset();
            m_lIndexOfActualStage=0;
            m_pStage.loadDefaultStage((Integer)m_vStageIDs.elementAt(m_lIndexOfActualStage));
            setState(CSceneControl.MENU_SCREEN);
     }
     
     public void run(){
         nextStates();

         m_pPlayerControl.run();
         
         runStageControl();
         
         CGameInfo.setStageIndex(this.m_lIndexOfActualStage);
         if (null!=this.m_pStage){
            CGameInfo.setStageName(m_pStage.getStageName());
         }
         
        
         saveOldStates();

     }           
 
     private void runStageControl(){
         if(m_bChangeTonNextStageInNextCycle){
            m_bChangeTonNextStageInNextCycle=false;
            this.changeToNextStage();
         }
         
         if(m_pPlayerControl.isGameOver()){
             setState(GAME_OVER_SCREEN);
         }
             
         switch(m_lStateOld){ 
         case INTRODUCTION_SCREEN:            
            m_pIntroductionScreen.run();
            if(m_pIntroductionScreen.isFinished())
            {
                setState(STAGE_INTRODUCTION_SCREEN);
                m_pIntroductionScreen.restart();
            }
            break;
         case MENU_SCREEN:
            m_pMenuScreen.run();
            if(m_pMenuScreen.isFinished())
            {
                setState(INTRODUCTION_SCREEN);
                m_pMenuScreen.restart();
            }
            break;
         case STAGE_INTRODUCTION_SCREEN:
            m_pStage.getIntroductionScreen().run();
            if(m_pStage.getIntroductionScreen().isFinished())
            {
                setState(PLAYING_STAGE_SCREEN);
            }
            break;
         case PLAYING_STAGE_SCREEN:            
            m_pStage.run();            
            if(m_pStage.isFinished())
            {
                this.setState(STAGE_FINISH_SCREEN);                
            }
            
            m_pScene.run();

            break;
         case PAUSE_SCREEN:
            m_pPauseScreen.run();
            break;
         case STAGE_FINISH_SCREEN:
            //Delete all elements in screen and change index of screen or go to Finish_screen
            //provisionally only delete   
            
            m_pStage.getFinishScreen().run();
            if(m_pStage.getFinishScreen().isFinished())
            {
                changeToNextStageInNextCycle();
                m_pPlayerControl.resetForNewStage();
            }
            
            
            break;
         case FINISH_SCREEN:
            m_pFinishScreen.run();
            if(m_pFinishScreen.isFinished()){
                setState(CSceneControl.GAME_OVER_SCREEN);
                m_pFinishScreen.restart();
                //resetSceneControl();
            }
            break;
         case GAME_OVER_SCREEN:
            m_pGameOverScreen.run();
            if(m_pGameOverScreen.isFinished()){
                setState(CSceneControl.ENTER_NAME_SCREEN);
                m_pGameOverScreen.restart();
                //resetSceneControl();
            }
            break;
         case ENTER_NAME_SCREEN:
            m_pEnterNameScreen.run();
            if(m_pEnterNameScreen.isFinished()){
                setState(CSceneControl.MENU_SCREEN);
                m_pEnterNameScreen.restart();
                resetSceneControl();
            }
            break;
         default:
            ;
        } 
          
     }
     
     public CPlayerControl getPlayerControl(){
         return m_pPlayerControl;
     }
       
 
 
             
}
