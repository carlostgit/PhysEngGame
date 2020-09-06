/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Enemies;
import PhysEngGame.*;
//import PhysEngGame.Enemies.CEnemy;
import java.util.Vector;

/**
 *
 * @author Carlos
 */
public class CEnemyRock extends CEnemy implements IForceListener {
    protected float m_fDamageThreshold=0.0f;
    protected float m_fAccumulatedDamage=0.0f;
    protected float m_fMaximumDamage=100.0f;
    private Vector m_vSubEnemyRocks = new Vector(30);
    
    public CEnemyRock(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CScene pScene,CStage pStage){
        super(pPhysEngRunnableGameCanvas,pScene,pStage);        

        /*
        CPolygon Polygon1 = new CPolygon(m_pPhysEngRunnableGameCanvas);
        Polygon1.m_lColorR=255;
        Polygon1.m_lColorG=0;
        Polygon1.m_lColorB=0;
        Polygon1.setInitPos(0,0);
        Polygon1.m_fVx = 0;
        Polygon1.m_fVxOld = 0;
        Polygon1.m_fVy = 0;
        Polygon1.m_fVyOld = 0;
        Polygon1.m_lNumOfVertex = 3;
        Polygon1.m_lModRelVertexes[0] = 300;
        Polygon1.m_lAngRelVertexes[0] = 350;
        Polygon1.m_lModRelVertexes[1] = 310;
        Polygon1.m_lAngRelVertexes[1] = 90;
        Polygon1.m_lModRelVertexes[2] = 320;
        Polygon1.m_lAngRelVertexes[2] = 190;

        Polygon1.m_bHandControlled = false;
        Polygon1.m_bActivated = true;
        Polygon1.m_fMass = 1;
        Polygon1.m_fDegree = 0;
        Polygon1.m_fDegreeOld = 0;
        Polygon1.m_fVAngular = 0;
        Polygon1.m_fVAngularOld = 0;
        
        CPolygon Polygon2 = new CPolygon(m_pPhysEngRunnableGameCanvas);
        Polygon2.m_lColorR=255;
        Polygon2.m_lColorG=0;
        Polygon2.m_lColorB=0;
        
        Polygon2.setInitPos(300,300);
        Polygon2.m_fVx = 0;
        Polygon2.m_fVxOld = 0;
        Polygon2.m_fVy = 0;
        Polygon2.m_fVyOld = 0;
        Polygon2.m_lNumOfVertex = 3;
        Polygon2.m_lModRelVertexes[0] = 300;
        Polygon2.m_lAngRelVertexes[0] = 350;
        Polygon2.m_lModRelVertexes[1] = 310;
        Polygon2.m_lAngRelVertexes[1] = 90;
        Polygon2.m_lModRelVertexes[2] = 320;
        Polygon2.m_lAngRelVertexes[2] = 190;

        Polygon2.m_bHandControlled = false;
        Polygon2.m_bActivated = true;
        Polygon2.m_fMass = 1;
        Polygon2.m_fDegree = 0;
        Polygon2.m_fDegreeOld = 0;
        Polygon2.m_fVAngular = 0;
        Polygon2.m_fVAngularOld = 0;
        */
        
        //public CPolygon(PhysEngRunnableGameCanvas pPruebasGameCanvas, float fX, float fY, float fVx, float fVy, int lSize, float fDegreesTurned, float fVAngular, float fMass)
//        CPolygon Polygon3 = new CPolygon(m_pPhysEngRunnableGameCanvas, 500f, 600f, 0f, 0f, 200, 0f, 0f, 1f);
//        CPolygon Polygon4 = new CPolygon(m_pPhysEngRunnableGameCanvas, 500f, 0f, 0f, 0f, 200, 0f, 0f, 1f);
//        CPolygon Polygon5 = new CPolygon(m_pPhysEngRunnableGameCanvas, 0f, 600f, 0f, 0f, 200, 0f, 0f, 1f);
//        CPolygon Polygon6 = new CPolygon(m_pPhysEngRunnableGameCanvas, 0f, -600f, 0f, 0f, 200, 0f, 0f, 1f);
//        
        Vector vPolygonsOfRock = new Vector(30);
        
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 0f, 0f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 0f, 200f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 0f, 400f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 0f, 600f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 200f, 0f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 200f, 200f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 200f, 400f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 200f, 600f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 400f, 0f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 400f, 200f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 400f, 400f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 400f, 600f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 600f, 0f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 600f, 200f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 600f, 400f, 0f, 0f, 200, 0f, 0f, 1f));
        vPolygonsOfRock.addElement(new CPolygon(m_pPhysEngRunnableGameCanvas, 600f, 600f, 0f, 0f, 200, 0f, 0f, 1f));
        
        
//        vPolygonsOfRock.addElement(Polygon1);
//        vPolygonsOfRock.addElement(Polygon2);
//        vPolygonsOfRock.addElement(Polygon3);
//        vPolygonsOfRock.addElement(Polygon4);
//        vPolygonsOfRock.addElement(Polygon5);
//        vPolygonsOfRock.addElement(Polygon6);
        CGroup pGroup=new CGroup(m_pPhysEngRunnableGameCanvas,pScene,vPolygonsOfRock);
        
//        CSubEnemyRock pSubEnemyRock1=new CSubEnemyRock(this,pScene,Polygon3);
//        CSubEnemyRock pSubEnemyRock2=new CSubEnemyRock(this,pScene,Polygon4);
//        CSubEnemyRock pSubEnemyRock3=new CSubEnemyRock(this,pScene,Polygon5);
//        CSubEnemyRock pSubEnemyRock4=new CSubEnemyRock(this,pScene,Polygon6);

        for(int lPolRock=0;lPolRock<vPolygonsOfRock.size();lPolRock++)
        {
            m_vSubEnemyRocks.addElement(new CSubEnemyRock(this,pScene,(CPolygon)vPolygonsOfRock.elementAt(lPolRock)));          
        }
        
//        m_vSubEnemyRocks.addElement(pSubEnemyRock1);
//        m_vSubEnemyRocks.addElement(pSubEnemyRock2);
//        m_vSubEnemyRocks.addElement(pSubEnemyRock3);
//        m_vSubEnemyRocks.addElement(pSubEnemyRock4);
        
        //m_vPolygons.addElement(Polygon1);
        //m_pScene.addPolygon(Polygon1);
        
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
            destroyThisEnemy();
        }
    }
    public void eraseSubEnemyRock(CSubEnemyRock pSubEnemyRock){
        m_vSubEnemyRocks.removeElement(pSubEnemyRock);
        if (m_vSubEnemyRocks.size()==0){
            m_pObject.eraseForceListener((IForceListener)this);
            destroyThisEnemy();
            CGameInfo.addScore(100);
        }
            
    }
    
}
