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
public class CPauseScreen {
     private CSceneControl m_pSceneControl=null;
     private PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;
     private int m_lStateToReturnTo=CSceneControl.PLAYING_STAGE_SCREEN;
     
     public CPauseScreen(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CSceneControl pSceneControl){
        m_pPhysEngRunnableGameCanvas=pPhysEngRunnableGameCanvas;
        m_pSceneControl=pSceneControl;
     }
     public void run(){
         drawBackground();
         m_pPhysEngRunnableGameCanvas.m_g.setColor(200, 200, 200);
         m_pPhysEngRunnableGameCanvas.write(m_pPhysEngRunnableGameCanvas.getHeight()/2, new String("This is the pause screen. Press any key to continue"));
         
         if(m_pPhysEngRunnableGameCanvas.isAnyKeyPressed())
             m_pSceneControl.setState(m_lStateToReturnTo);
         
     }
     public void drawBackground(){
        m_pPhysEngRunnableGameCanvas.m_g.setColor(0, 0, 0);
        m_pPhysEngRunnableGameCanvas.m_g.fillRect(0, 0, m_pPhysEngRunnableGameCanvas.m_g.getClipWidth(), m_pPhysEngRunnableGameCanvas.m_g.getClipHeight());
     }
     public void setStateToReturnTo(int lStateToReturnTo){
         m_lStateToReturnTo=lStateToReturnTo;
     }
             
     
}
