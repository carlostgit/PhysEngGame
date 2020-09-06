
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Players;

import PhysEngGame.*;
//import PhysEngGame.Players.CMortalPlayerDefault;

/**
 *
 * @author Carlos
 */
public class CSubMortalPlayerDefault implements IForceListener {
    protected float m_fDamageThreshold=10.0f;
    protected float m_fAccumulatedDamage=0.0f;
    protected float m_fMaximumDamage=1000.0f;
    
    CObject m_pObject=null;
    CScene m_pScene=null;
    CMortalPlayerDefault m_pMortalPlayerDefault=null;
    
    public CSubMortalPlayerDefault(CMortalPlayerDefault pMortalPlayerDefault,CScene pScene,CObject pObject){
        m_pMortalPlayerDefault=pMortalPlayerDefault;
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
            m_pMortalPlayerDefault.eraseSubMortalPlayerDefault(this);
        }
    }
    public void run(){
        CGameInfo.setLifePoints(m_fMaximumDamage-m_fAccumulatedDamage);
    }
    
    public void reset(){     
        m_fDamageThreshold=10.0f;
        m_fAccumulatedDamage=0.0f;
        m_fMaximumDamage=1000.0f;
    }
}

