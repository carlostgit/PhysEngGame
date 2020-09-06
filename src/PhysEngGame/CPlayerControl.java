/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;
import PhysEngGame.Players.*;


/**
 *
 * @author Carlos
 */
public class CPlayerControl {
    int m_lLifes=3;
    CMortalPlayer m_pMortalPlayer = null;
    PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas = null;
    CScene m_pScene = null;
    long m_lCount=0;
    boolean m_bCountToLoadPlayerStarted=false;
    
    CSceneControl m_pSceneControl=null;
    //boolean m_bPlayerIsInScene=false;
    
    public CPlayerControl(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas, CSceneControl pSceneControl, CScene pScene){
        m_pPhysEngRunnableGameCanvas = pPhysEngRunnableGameCanvas;
        m_pSceneControl=pSceneControl;
        m_pScene=pScene;
        
        loadDefaultPlayer();
    }
    
    public void loadDefaultPlayer(){
        m_pMortalPlayer=new CMortalPlayerDefault(m_pPhysEngRunnableGameCanvas,m_pScene);
        m_lLifes--;
    }
    
    public boolean isGameOver(){
        if(m_lLifes<0)
            return true;
        else
            return false;
    }
    
    public void run(){
        
        CGameInfo.setLifes(m_lLifes);
        
        m_pMortalPlayer.run();
        
        if(m_lLifes>=0 &&  m_pMortalPlayer.isDestroyed()){
            m_bCountToLoadPlayerStarted=true;
        }
        if(m_bCountToLoadPlayerStarted){
            m_lCount++;
        }
        
        if(m_lCount>100){
            loadDefaultPlayer();
            m_lCount=0;
            m_bCountToLoadPlayerStarted=false;
        }
        
        if(false==m_pMortalPlayer.isInScene()){
            if(null != m_pSceneControl.getStage() && false==m_pSceneControl.getStage().isFinished()){                
                m_pSceneControl.getStage().putPlayer(m_pMortalPlayer);
            }
        }                                    
                
    }
    
    public void resetForNewStage(){
        m_pMortalPlayer.resetForNewStage();
        
    }
    
    public void reset(){
       resetForNewStage();
       
        m_lLifes=3;
        loadDefaultPlayer();
        m_lCount=0;
        m_bCountToLoadPlayerStarted=false;
        
        m_pMortalPlayer.reset();

    }
    
    

}
