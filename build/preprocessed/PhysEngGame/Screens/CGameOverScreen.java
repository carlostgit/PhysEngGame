/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Screens;
import javax.microedition.lcdui.Graphics;

import PhysEngGame.*;

/**
 *
 * @author Carlos
 */
public class CGameOverScreen implements IKeyPressedAndReleasedListener {
     private CSceneControl m_pSceneControl=null;
     private PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;
     private int m_lCount=0;
     private boolean m_bIsFinished=false;
     
     public CGameOverScreen(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CSceneControl pSceneControl){
        m_pPhysEngRunnableGameCanvas=pPhysEngRunnableGameCanvas;
        m_pSceneControl=pSceneControl;
        m_pPhysEngRunnableGameCanvas.addKeyPressedListener(this);
     }
     public void run(){
         drawBackground();
         m_pPhysEngRunnableGameCanvas.m_g.setColor(200, 200, 200);
         //m_pPhysEngRunnableGameCanvas.write(m_pPhysEngRunnableGameCanvas.getHeight()/2, new String("GAME OVER"));

         m_pPhysEngRunnableGameCanvas.m_g.drawString ("GAME OVER", 80, 100, Graphics.LEFT|Graphics.TOP);
         //m_pPhysEngRunnableGameCanvas.m_g.drawString (""+CGameInfo.getLifes()+"", 50, 20, Graphics.LEFT|Graphics.TOP);        

         m_lCount++;
//         if(m_pPhysEngRunnableGameCanvas.isAnyKeyPressed())
//             m_pSceneControl.setState(CSceneControl.MENU_SCREEN);
         
     }
     public void drawBackground(){
        m_pPhysEngRunnableGameCanvas.m_g.setColor(0, 0, 0);
        m_pPhysEngRunnableGameCanvas.m_g.fillRect(0, 0, m_pPhysEngRunnableGameCanvas.m_g.getClipWidth(), m_pPhysEngRunnableGameCanvas.m_g.getClipHeight());
     }
     public void keyPressedAndReleased(int keyCode){ 
         if(m_lCount>100)
         {
             if(m_pSceneControl.getState()==CSceneControl.GAME_OVER_SCREEN){
                m_bIsFinished=true;
                m_pPhysEngRunnableGameCanvas.eraseKeyPressedListener(this);
                m_lCount=0;
             }
         }
     }
     public boolean isFinished(){
         return m_bIsFinished;
     }
     public void restart(){
         m_lCount=0;
         m_bIsFinished=false;
         m_pPhysEngRunnableGameCanvas.addKeyPressedListener(this);
     }


}
