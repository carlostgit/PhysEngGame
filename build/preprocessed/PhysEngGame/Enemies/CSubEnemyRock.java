/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Enemies;

import PhysEngGame.*;
//import PhysEngGame.Enemies.CEnemyRock;

/**
 *
 * @author Carlos
 */
public class CSubEnemyRock implements IForceListener {
    protected float m_fDamageThreshold=0.0f;
    protected float m_fAccumulatedDamage=0.0f;
    protected float m_fMaximumDamage=100.0f;
    
    CObject m_pObject=null;
    CScene m_pScene=null;
    CEnemyRock m_pEnemyRock=null;
    
    public CSubEnemyRock(CEnemyRock pEnemyRock,CScene pScene,CObject pObject){
        m_pEnemyRock=pEnemyRock;
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
            m_pObject.eraseForceListener((IForceListener)this); //first I delete listener in m_pObject. Perhaps this is not necessary
            m_pScene.destroyThisObjectEverywhere(m_pObject); //then I delete m_pObject
            m_pEnemyRock.eraseSubEnemyRock(this);
            CGameInfo.addScore(10);
        }
    }
}
