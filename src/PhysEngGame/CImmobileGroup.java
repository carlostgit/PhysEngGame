/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;
import java.util.Vector;
/**
 *
 * @author Carlos
 */
public class CImmobileGroup extends CGroup {

    public CImmobileGroup(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas, CScene pScene, Vector vObjects)
    {        
        super(pPhysEngRunnableGameCanvas,pScene,vObjects);
        m_fMass=Float.MAX_VALUE;
        m_fMomentOfInertia=Float.MAX_VALUE;
        m_bImmobile=true;
        init();

    }
    
    public CImmobileGroup(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas, CScene pScene, CObject pObjects)
    {
        super(pPhysEngRunnableGameCanvas,pScene,pObjects);
        m_fMass=Float.MAX_VALUE;
        m_fMomentOfInertia=Float.MAX_VALUE;
        m_bImmobile=true;
        init();
    }

    
    public void init(){
        //I have to give to old states a good starting value
        //Value for the square to discard collision
        m_bImmobile=true;
        //super.init();
    }
    
    public void integrate(){
        //An immobileObject has not inertia
        //m_fX=this.getX();
        //m_fY=this.getY();        
        this.setNewPos(this.getX(), this.getY());
        
        m_fVx=m_fVxOld;
        m_fVy=m_fVyOld;

        m_fAx=m_fAxOld;
        m_fAy=m_fAyOld;

        if (m_fDegree> 360) {
            m_fDegree= m_fDegree- 360;
        } else if (m_fDegree< 0) {
            m_fDegree = m_fDegree+ 360;
        }

        m_fDegree=m_fDegreeOld;

    }
    public void saveOldStates(){
//        System.out.println("O");
//        this.setOldPos(this.getX(),this.getY());
//        m_fXOld=m_fX;
//        m_fYOld=m_fY;
//
//        m_fVxOld=m_fVx;
//        m_fVyOld=m_fVy;
//
//        m_fAxOld=m_fAx;
//        m_fAyOld=m_fAy;
//
//        if (m_fDegree> 360) {
//            m_fDegree= m_fDegree- 360;
//        } else if (m_fDegree< 0) {
//            m_fDegree = m_fDegree+ 360;
//        }
//
//        m_fDegreeOld=m_fDegree;
    }

    public void ApplyForceToObject(float fForce, float fAngleOfRepulsion, float fExcentricity){
        //An immobileObject can't be moved
        for(int i=0;i<m_vForceListeners.size();i++){
            ((IForceListener)m_vForceListeners.elementAt(i)).applyForce(this, fForce);
        }

    }
    public void ApplyForceToObjectInPointInCartesianAbsCoord(float fForce, float fAngleOfRepulsion, float fXAplicationPointAbsCoord, float fYAplicationPointAbsCoord){
        //An immobileObject can't be moved
        //notify listeners
        for(int i=0;i<m_vForceListeners.size();i++){
            ((IForceListener)m_vForceListeners.elementAt(i)).applyForce(this, fForce);
        }
    }
    /*
    public void setCollisionTestCoord(float fX,float fY){
        
        m_fCollisionTestXMax=-Float.MAX_VALUE;
        m_fCollisionTestXMin=Float.MAX_VALUE;
        m_fCollisionTestYMax=-Float.MAX_VALUE;
        m_fCollisionTestYMin=Float.MAX_VALUE;
    
        for (int lVertex = 0; lVertex < m_lNumOfVertex; lVertex++) {
            
            if(m_lfXCoordVertexesNew[lVertex] + CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE > m_fCollisionTestXMax)
                m_fCollisionTestXMax = m_lfXCoordVertexesNew[lVertex] + CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE;
            if(m_lfXCoordVertexesNew[lVertex] - CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE < m_fCollisionTestXMin)
                m_fCollisionTestXMin = m_lfXCoordVertexesNew[lVertex] - CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE;
            if(m_lfYCoordVertexesNew[lVertex] + CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE > m_fCollisionTestYMax)
                m_fCollisionTestYMax = m_lfYCoordVertexesNew[lVertex] + CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE;
            if(m_lfYCoordVertexesNew[lVertex] - CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE < m_fCollisionTestYMin)
                m_fCollisionTestYMin = m_lfYCoordVertexesNew[lVertex] - CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE;
                
        }

     }
    
    public void setOldPos(float fX,float fY){
        //I should calculate X and Y coordinates here, and keep them as states, instead of doing these calculations in draw method
        //These coordinates are very useful doing the collision tests

        for (int lVertex = 0; lVertex < m_lNumOfVertex; lVertex++) {
            float lfModRelVertex = m_lModRelVertexes[lVertex];
            m_lfXCoordVertexesOld[lVertex] = fX + (int) (lfModRelVertex * CMath.cosine(m_fDegree + m_lAngRelVertexes[lVertex]));
            m_lfYCoordVertexesOld[lVertex] = fY + (int) (lfModRelVertex * CMath.sine(m_fDegree + m_lAngRelVertexes[lVertex]));
        }
        setCollisionTestCoord(fX,fY);
        super.setOldPos(fX,fY);
    }
    public void setNewPos(float fX,float fY){
        //I should calculate X and Y coordinates here, and keep them as states, instead of doing these calculations in draw method
        //These coordinates are very useful doing the collision tests

        for (int lVertex = 0; lVertex < m_lNumOfVertex; lVertex++) {
            float lfModRelVertex = m_lModRelVertexes[lVertex];
            m_lfXCoordVertexesNew[lVertex] = fX + (int) (lfModRelVertex * CMath.cosine(m_fDegree + m_lAngRelVertexes[lVertex]));
            m_lfYCoordVertexesNew[lVertex] = fY + (int) (lfModRelVertex * CMath.sine(m_fDegree + m_lAngRelVertexes[lVertex]));
        }        
        super.setNewPos(fX,fY);        
    }

    public void setInitPos(float fX,float fY){
        //I should calculate X and Y coordinates here, and keep them as states, instead of doing these calculations in draw method
        //These coordinates are very useful doing the collision tests

       //I have to give to old states a good starting value
        for (int lVertex = 0; lVertex < m_lNumOfVertex; lVertex++) {
            float lfModRelVertex = m_lModRelVertexes[lVertex];
            m_lfXCoordVertexesNew[lVertex] = fX + (int) (lfModRelVertex * CMath.cosine(m_fDegree + m_lAngRelVertexes[lVertex]));
            m_lfYCoordVertexesNew[lVertex] = fY + (int) (lfModRelVertex * CMath.sine(m_fDegree + m_lAngRelVertexes[lVertex]));
            m_lfXCoordVertexesOld[lVertex] = fX + (int) (lfModRelVertex * CMath.cosine(m_fDegreeOld + m_lAngRelVertexes[lVertex]));
            m_lfYCoordVertexesOld[lVertex] = fY + (int) (lfModRelVertex * CMath.sine(m_fDegreeOld + m_lAngRelVertexes[lVertex]));
        }
        setCollisionTestCoord(fX,fY);
        super.setInitPos(fX,fY);        
    }
    */
    public void incrementNewX(float fX){
    }
    public void incrementNewY(float fY){
    }
    public void incrementNewVelX(float fX){       
    }
    public void incrementNewVelY(float fY){        
    }
    public void incrementNewAngle(float fAngle){        
    }

    

}

