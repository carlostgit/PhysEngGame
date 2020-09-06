/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Enemies;
import PhysEngGame.*;
import java.util.Vector;
/**
 *
 * @author Carlos
 */
public class CEnemy {
    CScene m_pScene=null;
    CObject m_pObject=null;
    CStage m_pStage=null;
    PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;
    boolean m_bDestroyed=false;
    boolean m_bInScene=false;
    
    public CEnemy(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CScene pScene,CStage pStage){
        m_pStage=pStage;
        m_pScene=pScene;
        m_pPhysEngRunnableGameCanvas=pPhysEngRunnableGameCanvas;
        
    }
    
    public void putEnemyInRandomPlace(){
    
        //
        //pPolygon1.m_bCollisionable=false;        
        
        Vector vApparitionPoints=m_pStage.getApparitionPoints();
       
        for(int i=0;i<vApparitionPoints.size();i++)
        {
            CPointCartesianCoord pPoint=(CPointCartesianCoord)vApparitionPoints.elementAt(i);
            
            //pPolygon1.m_fX = pPoint.m_fX;
            //pPolygon1.m_fXOld = pPoint.m_fX;
            //pPolygon1.m_fY = pPoint.m_fY;
            //pPolygon1.m_fYOld = pPoint.m_fY;
            //pPolygon1.setOldPos(pPoint.m_fX,pPoint.m_fY);
            if(CONSTANTS.POLYGON==m_pObject.getType()){
                CPolygon pPolygon1=(CPolygon)m_pObject;
                pPolygon1.setInitPos(pPoint.m_fX,pPoint.m_fY);
                if(m_pScene.isThereSpaceForThisPolygon(pPolygon1)){
                    m_pScene.addPolygon(pPolygon1);
                    m_bInScene=true;
                    m_pScene.AddPermanentDebugLine(pPoint.m_fX-100,pPoint.m_fY-100,pPoint.m_fX+100,pPoint.m_fY+100,255,255,0);
                    break;
                }
            }
            else if (CONSTANTS.GROUP==m_pObject.getType()){
                CGroup pGroup=(CGroup)m_pObject;
                pGroup.setInitPos(pPoint.m_fX,pPoint.m_fY);
                if(m_pScene.isThereSpaceForThisGroup(pGroup)){
                    m_pScene.addGroup(pGroup);
                    m_bInScene=true;
                    m_pScene.AddPermanentDebugLine(pPoint.m_fX-100,pPoint.m_fY-100,pPoint.m_fX+100,pPoint.m_fY+100,255,255,0);
                    break;
                }
            }
        }
    }
    
    public void putEnemy(float x, float y)
    {
        if(CONSTANTS.POLYGON==m_pObject.getType()){
            CPolygon pPolygon1=(CPolygon)m_pObject;
            pPolygon1.setInitPos(x,y);
            if(m_pScene.isThereSpaceForThisPolygon(pPolygon1)){
                m_pScene.addPolygon(pPolygon1);                
                m_bInScene=true;
                //m_pScene.AddPermanentDebugLine(pPoint.m_fX-100,pPoint.m_fY-100,pPoint.m_fX+100,pPoint.m_fY+100,255,255,0);
                //break;
            }
        }
        else if (CONSTANTS.GROUP==m_pObject.getType()){
            CGroup pGroup=(CGroup)m_pObject;
            pGroup.setInitPos(x,y);
            if(m_pScene.isThereSpaceForThisGroup(pGroup)){
                m_pScene.addGroup(pGroup);                
                m_bInScene=true;
                //m_pScene.AddPermanentDebugLine(pPoint.m_fX-100,pPoint.m_fY-100,pPoint.m_fX+100,pPoint.m_fY+100,255,255,0);
                //break;
            }
        }
    }

    
    public void destroyThisEnemy(){
        m_bDestroyed=true;
        m_pScene.destroyThisObjectEverywhere(m_pObject);
        m_bInScene=false;
    }
    public boolean isDestroyed(){
        return m_bDestroyed;
    }
    public boolean isInScene(){
        return m_bInScene;
    }    
}
