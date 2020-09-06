/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Players;
import PhysEngGame.*;
//import PhysEngGame.Players.CMortalPlayer;
//import PhysEngGame.Players.CPlayer;
import java.util.Vector;
/**
 *
 * @author Carlos
 */
public class CMortalPlayerDefault extends CMortalPlayer implements IForceListener {
    protected float m_fDamageThreshold=100.0f;
    protected float m_fAccumulatedDamage=0.0f;
    protected float m_fMaximumDamage=10000000.0f;
    private Vector m_vSubMortalPlayerDefault = new Vector(30);
    
    public CMortalPlayerDefault(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CScene pScene){
        super(pPhysEngRunnableGameCanvas,pScene);        

         
        Vector vPolygonsOfPlayer = new Vector(30);
        
        CPlayer pPlayer = new CPlayer(m_pPhysEngRunnableGameCanvas,0,0,0f);
//        pPlayer.m_lColorR=0;
//        pPlayer.m_lColorG=240;
//        pPlayer.m_lColorB=0;
        pPlayer.setColor(0,240,0);
        
        //vPolygonsOfRock.addElement(new CPlayer(m_pPhysEngRunnableGameCanvas,0,0,0f));
        vPolygonsOfPlayer.addElement(pPlayer);
        
        CGroup pGroup=new CGroup(m_pPhysEngRunnableGameCanvas,pScene,vPolygonsOfPlayer);
  
        
        for(int lPolRock=0;lPolRock<vPolygonsOfPlayer.size();lPolRock++)
        {
            m_vSubMortalPlayerDefault.addElement(new CSubMortalPlayerDefault(this,pScene,(CPolygon)vPolygonsOfPlayer.elementAt(lPolRock)));          
        }
        
        
        m_pObject=pGroup;
        
        m_pObject.addForceListener((IForceListener)this);

    }
    protected void finalize() throws Throwable
    {
      //do finalization here
        m_pObject.eraseForceListener((IForceListener)this);
      //super.finalize(); //not necessary if extending Object.
    } 

    public void applyForce(Object pObject,float fForce){
        if(fForce>m_fDamageThreshold){
            m_fAccumulatedDamage=m_fAccumulatedDamage+fForce;
        }
        if(m_fAccumulatedDamage>m_fMaximumDamage){
            destroyThisMortalPlayer();
        }
    }
    public void eraseSubMortalPlayerDefault(CSubMortalPlayerDefault pSubMortalPlayerDefault){
        m_vSubMortalPlayerDefault.removeElement(pSubMortalPlayerDefault);
        if (m_vSubMortalPlayerDefault.size()==0)
            destroyThisMortalPlayer();
    }
    public void run(){
        super.run();
        for(int i=0;i<m_vSubMortalPlayerDefault.size();i++){
            ((CSubMortalPlayerDefault)m_vSubMortalPlayerDefault.elementAt(i)).run();
        }      
        
    }
    public void reset(){     
        super.reset();
                
        m_fDamageThreshold=100.0f;
        m_fAccumulatedDamage=0.0f;
        m_fMaximumDamage=10000000.0f;
        
        for(int i=0;i<m_vSubMortalPlayerDefault.size();i++){
            ((CSubMortalPlayerDefault)m_vSubMortalPlayerDefault.elementAt(i)).reset();
        }
    }
    
}

