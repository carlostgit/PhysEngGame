/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Screens;

import PhysEngGame.*;
import javax.microedition.lcdui.*;

/**
 *
 * @author Carlos
 */
public class CEnterNameScreen implements IKeyPressedAndReleasedListener {
     private CSceneControl m_pSceneControl=null;
     private PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;
     private int m_lCount=0;
     private boolean m_bIsFinished=false;
     
     public CEnterNameScreen(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CSceneControl pSceneControl){
        m_pPhysEngRunnableGameCanvas=pPhysEngRunnableGameCanvas;
        m_pSceneControl=pSceneControl;
        m_pPhysEngRunnableGameCanvas.addKeyPressedListener(this);
     }
     public void run(){
         drawBackground();
         m_pPhysEngRunnableGameCanvas.m_g.setColor(200, 200, 200);
         m_pPhysEngRunnableGameCanvas.write(m_pPhysEngRunnableGameCanvas.getHeight()/2, new String("This is the enter name screen. Press any key to continue"));
         m_lCount++;
         
         
         if(50==m_lCount){
             displayForm();
         }
          

         
         
//         if(m_pPhysEngRunnableGameCanvas.isAnyKeyPressed())
//             m_pSceneControl.setState(CSceneControl.MENU_SCREEN);
         
     }
     private void displayForm(){

         int maxNameSize=20;
         TextField myTextField = new TextField("Enter Name TextBox", "Your name", maxNameSize, TextField.ANY);
         
         
                 
         //new Command("OK", Command.BACK, 1)                 
         Form myForm = new Form("Enter Name");
         myForm.append(myTextField);
         
         myForm.addCommand(new Command("OK",Command.OK,1));
         
         m_pPhysEngRunnableGameCanvas.getDisplay().setCurrent(myForm);
         
         
         
     }
     public void drawBackground(){
        m_pPhysEngRunnableGameCanvas.m_g.setColor(0, 100, 0);
        m_pPhysEngRunnableGameCanvas.m_g.fillRect(0, 0, m_pPhysEngRunnableGameCanvas.m_g.getClipWidth(), m_pPhysEngRunnableGameCanvas.m_g.getClipHeight());
     }
     public void keyPressedAndReleased(int keyCode){ 
         if(m_lCount>100)
         {
             if(m_pSceneControl.getState()==CSceneControl.ENTER_NAME_SCREEN){
                m_bIsFinished=true;
                m_pPhysEngRunnableGameCanvas.eraseKeyPressedListener(this);
                m_lCount=0;
                m_pPhysEngRunnableGameCanvas.getDisplay().setCurrent(m_pPhysEngRunnableGameCanvas);
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