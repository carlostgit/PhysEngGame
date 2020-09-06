/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Screens;

import PhysEngGame.*;

/**
 *
 * @author Carlos
 */
public class CStageIntroductionScreen implements IKeyPressedAndReleasedListener {
     private CSceneControl m_pSceneControl=null;
     private PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;
     private CStage m_pStage=null;
     private int m_lCount=0;
     private boolean m_bIsFinished=false;
     
     public CStageIntroductionScreen(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CSceneControl pSceneControl,CStage pStage){
        m_pPhysEngRunnableGameCanvas=pPhysEngRunnableGameCanvas;
        m_pSceneControl=pSceneControl;
        m_pStage=pStage;
        m_pPhysEngRunnableGameCanvas.addKeyPressedListener(this);
     }
     public void run(){
         drawBackground();
         m_pPhysEngRunnableGameCanvas.m_g.setColor(200, 200, 200);
         m_pPhysEngRunnableGameCanvas.write(m_pPhysEngRunnableGameCanvas.getHeight()/2, new String("This is the stage introduction screen. Press any key to continue"));
         m_lCount++;
         //if(m_pPhysEngRunnableGameCanvas.isAnyKeyPressed())
         //    m_pSceneControl.setState(CSceneControl.PLAYING_STAGE_SCREEN);
         
     }
     public void drawBackground(){
        m_pPhysEngRunnableGameCanvas.m_g.setColor(0, 0, 50);
        m_pPhysEngRunnableGameCanvas.m_g.fillRect(0, 0, m_pPhysEngRunnableGameCanvas.m_g.getClipWidth(), m_pPhysEngRunnableGameCanvas.m_g.getClipHeight());
     }
     public void keyPressedAndReleased(int keyCode){ 
         if(m_lCount>20)
         {
             if(m_pSceneControl.getState()==CSceneControl.STAGE_INTRODUCTION_SCREEN){
                 m_pPhysEngRunnableGameCanvas.eraseKeyPressedListener(this);
                 m_bIsFinished=true;
                 m_lCount=0;
             }
                
         }
     }
     public boolean isFinished(){
         return m_bIsFinished;
     }

    

}
