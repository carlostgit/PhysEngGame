/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Platforms;

import PhysEngGame.*;
//import PhysEngGame.Platforms.CPlatformDefault;

/**
 *
 * @author Carlos
 */
public class CSubPlatformDefault implements IForceListener {
    protected float m_fDamageThreshold=0.0f;
    protected float m_fAccumulatedDamage=0.0f;
    protected float m_fMaximumDamage=100.0f;
    
    CObject m_pObject=null;
    CScene m_pScene=null;
    CPlatformDefault m_pPlatform=null;
    
    public CSubPlatformDefault(CPlatformDefault pEnemyRock,CScene pScene,CObject pObject){
        m_pPlatform=pEnemyRock;
        m_pObject=pObject;
        m_pScene=pScene;    
                
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
            m_pScene.destroyThisObjectEverywhere(m_pObject);
            m_pPlatform.eraseSubPlatform(this);
        }
    }
}
