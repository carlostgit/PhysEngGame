/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Enemies;
import PhysEngGame.*;
//import PhysEngGame.Enemies.CEnemyRock;
//import PhysEngGame.Enemies.CEnemy;
import java.util.Vector;
/**
 *
 * @author Carlos
 */
public class CEnemyTeam {
    
    PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;
    private CSceneControl m_pStageControl=null;
    CStage m_pStage=null;
    long m_lTipeOfEnemy=CONSTANTS.SIMPLE_ROCK;
    long m_lNumberOfEnemies=0;
    long m_lConditionOfAppearance=CONSTANTS.ORDER_APPEARANCE_AFTER_PREVIOUS_IN_RANDOM_PLACE;
    
    private Vector m_vObectsOfTeam = new Vector(30);
    //private Vector m_vObectsOfTeamPutInScene = new Vector(30);
    //private Vector m_vObectsOfTeamDestroyed = new Vector(30);
    
    public CEnemyTeam(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CSceneControl pStageControl,CStage pStage,long lTipeOfEnemy,long lNumberOfEnemies,long lConditionOfAppearance){
        m_pPhysEngRunnableGameCanvas=pPhysEngRunnableGameCanvas;
        m_pStageControl=pStageControl;
        m_pStage=pStage;
        m_lTipeOfEnemy=lTipeOfEnemy;
        m_lNumberOfEnemies=lNumberOfEnemies;
        m_lConditionOfAppearance=lConditionOfAppearance;
    
        if(lTipeOfEnemy==CONSTANTS.SIMPLE_ROCK)
        {
            for(int i=0;i<lNumberOfEnemies;i++)
            {
                m_vObectsOfTeam.addElement(new CEnemyRock(m_pPhysEngRunnableGameCanvas, m_pStageControl.m_pScene,m_pStage));
            }
        }
    }
    
    public void nextStates(){
        
        if(m_vObectsOfTeam.size() != (getNumberOfEnemiesInScene()+getNumberOfEnemiesDestroyed()))
        {
            boolean bMakeEnemiesAppear=false;
            if(m_lConditionOfAppearance==CONSTANTS.ORDER_APPEARANCE_AFTER_PREVIOUS_IN_RANDOM_PLACE)
            {                
                CEnemyTeam pPreviousEnemyTeam=m_pStage.getPreviousEnemyTeam(this);
                if(null==pPreviousEnemyTeam){
                    bMakeEnemiesAppear=true;
                }
                else if(pPreviousEnemyTeam.hasBeenPutInScene() && m_pStage.getNumberOfEnemiesInScene()<1){
                    bMakeEnemiesAppear=true;
                }
            }
            if(bMakeEnemiesAppear)
                makeEnemiesAppear();
        }
    }
    public boolean hasBeenPutInScene()
    {
        if(getNumberOfEnemiesInScene()>0 || getNumberOfEnemiesDestroyed()>0)
            return true;
        else
            return false;    
    }
    public int getNumberOfEnemiesInScene(){
        //return m_vObectsOfTeamPutInScene.size()-m_vObectsOfTeamDestroyed.size();
        int lNumberOfEnemies=0;
        for(int i=0;i<m_vObectsOfTeam.size();i++){
            if(((CEnemy)m_vObectsOfTeam.elementAt(i)).isInScene())
                lNumberOfEnemies++;                
        }
        return lNumberOfEnemies;
    }
    public int getNumberOfEnemiesDestroyed(){
        //return m_vObectsOfTeamPutInScene.size()-m_vObectsOfTeamDestroyed.size();
        int lNumberOfEnemies=0;
        for(int i=0;i<m_vObectsOfTeam.size();i++){
            if(((CEnemy)m_vObectsOfTeam.elementAt(i)).isDestroyed())
                lNumberOfEnemies++;                
        }
        return lNumberOfEnemies;
    }
    public void makeEnemiesAppear(){
        for(int i=0;i<m_vObectsOfTeam.size();i++){

            if(m_lTipeOfEnemy==CONSTANTS.SIMPLE_ROCK){
                CEnemy pEnemyToPut=(CEnemy)m_vObectsOfTeam.elementAt(i);
                if(false==pEnemyToPut.isInScene() && false==pEnemyToPut.isDestroyed())
                    pEnemyToPut.putEnemyInRandomPlace();
                //m_vObectsOfTeamPutInScene.addElement(pEnemyToPut);

            }
        }
    }
    public int getNumberOfEnemiesOfTeam(){
        return m_vObectsOfTeam.size();
    }    
}
