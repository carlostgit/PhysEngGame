/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Platforms;
import PhysEngGame.*;
//import PhysEngGame.Platforms.CPlatform;
import java.util.Vector;

/**
 *
 * @author Carlos
 */
public class CPlatformDefault extends CPlatform implements IForceListener {
    protected float m_fDamageThreshold=0.0f;
    protected float m_fAccumulatedDamage=0.0f;
    protected float m_fMaximumDamage=100.0f;
    private Vector m_vSubPlatformsDefault = new Vector(30);
    
    public CPlatformDefault(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CScene pScene,CStage pStage){
        super(pPhysEngRunnableGameCanvas,pScene,pStage);        
          
        Vector vPolygonsOfRock = new Vector(30);
        
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 0f, 0f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 0f, 200f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 0f, 400f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 0f, 600f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 200f, 0f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 200f, 200f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 200f, 400f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 200f, 600f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 400f, 0f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 400f, 200f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 400f, 400f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 400f, 600f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 600f, 0f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 600f, 200f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 600f, 400f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CImmobilePolygon(m_pPhysEngRunnableGameCanvas, 600f, 600f, 0f, 0f, 200, 0f, 0f, 1f));

        
        
        CImmobileGroup pGroup=new CImmobileGroup(m_pPhysEngRunnableGameCanvas,pScene,vPolygonsOfRock);

        for(int lPolRock=0;lPolRock<vPolygonsOfRock.size();lPolRock++)
        {
            m_vSubPlatformsDefault.addElement(new CSubPlatformDefault(this,pScene,(CImmobilePolygon)vPolygonsOfRock.elementAt(lPolRock)));          
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
            destroyThisPlatform();
        }
    }
    public void eraseSubPlatform(CSubPlatformDefault pSubPlatform){
        m_vSubPlatformsDefault.removeElement(pSubPlatform);
        if (m_vSubPlatformsDefault.size()==0)
            destroyThisPlatform();
    }
    
    public void nextStates(){
    }
    
}

