/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */

public class CCollidingObject {

    //Params
    boolean bDrawCollisionsForDebug=CONSTANTS.DEBUG_COLLISIONS;

    int m_lObjectCollType=CONSTANTS.NO_TYPE_OF_COLLIDING_OBJECT;
    float m_fAngleOfRepulsion=0.0f;
    CObject m_Object=null;
    int m_lVertexOfPolygon=CONSTANTS.NO_VERTEX;
    
    //Relative position of the point of collision:
    float m_fCollPositionModRel=0.0f;
    float m_fCollPositionAngle=0.0f;

    PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;

    //Colliding object is a Polygon colliding with a side
    public CCollidingObject(PhysEngRunnableGameCanvas pPruebasGameCanvas, CPolygon Polygon, float fAngleOfRepulsionOfPolygon, float fCollPositionModRel, float fCollPositionAngle){
        m_lObjectCollType=CONSTANTS.POLYGON_COLLIDING_WITH_A_SIDE;
        m_Object=(CObject)Polygon;
        m_fAngleOfRepulsion=fAngleOfRepulsionOfPolygon;
        m_fCollPositionModRel=fCollPositionModRel;
        m_fCollPositionAngle=fCollPositionAngle;

        m_pPhysEngRunnableGameCanvas=pPruebasGameCanvas;
    }
    //Colliding object is a Polygon colliding with a vertex
    public CCollidingObject(PhysEngRunnableGameCanvas pPruebasGameCanvas, CPolygon PolygonOfVertex, int lVertexOfPolygon, float fAngleOfRepulsionOfVertex){
        m_lObjectCollType=CONSTANTS.POLYGON_COLLIDING_WITH_A_VERTEX;
        m_Object=(CObject)PolygonOfVertex;
        m_lVertexOfPolygon=lVertexOfPolygon;
        m_fAngleOfRepulsion=fAngleOfRepulsionOfVertex;
        m_fCollPositionModRel=PolygonOfVertex.m_lModRelVertexes[lVertexOfPolygon];
        m_fCollPositionAngle=PolygonOfVertex.m_lAngRelVertexes[lVertexOfPolygon]+m_Object.m_fDegree;

        m_pPhysEngRunnableGameCanvas=pPruebasGameCanvas;
    }
    //Colliding object is a Point
    public CCollidingObject(PhysEngRunnableGameCanvas pPruebasGameCanvas, CPoint Point, float fAngleOfRepulsionOfPoint){
        m_lObjectCollType=CONSTANTS.POINT_COLLIDING;
        m_Object=(CObject)Point;
        m_fAngleOfRepulsion=fAngleOfRepulsionOfPoint;

        m_pPhysEngRunnableGameCanvas=pPruebasGameCanvas;
    }

    protected float GetExcentricity(){
        float fAngleSubstraction = CMath.getAngleSubstraction(m_fAngleOfRepulsion,m_fCollPositionAngle);
        return (float)m_fCollPositionModRel*CMath.sine((int)(fAngleSubstraction));
    }
    public float GetExcentricityWithSign(){
        if((GetExcentricity()>0 && m_Object.m_fVAngular>=0) || (GetExcentricity()<0 && m_Object.m_fVAngular<=0))
            return GetExcentricity();
        else
            return -GetExcentricity();
    }

    public void ApplyForceToObject(float fForce){

        //m_Object.ApplyForceToObject(fForce, m_fAngleOfRepulsion, GetExcentricity());

        CPointCartesianCoord pCollCartesCoordAbs=getAbsoluteCartesianCoordOfCollision();
        m_Object.ApplyForceToObjectInPointInCartesianAbsCoord(fForce, m_fAngleOfRepulsion, pCollCartesCoordAbs.m_fX, pCollCartesCoordAbs.m_fY);

        /*
        //TODO: Next step: take into account that some colliding objets, could form part
        //of a Group. I need an ApplyForceToObject(lForce, fAngleOfRepulsion) method for CObject class

        float fModAccelerationOfPolygon = fForce/(float)m_Object.m_fMass;

        float fXAccelerationOfPolygon = (float)(CMath.cosine((int)(m_fAngleOfRepulsion))*fModAccelerationOfPolygon);
        float fYAccelerationOfPolygon = (float)(CMath.sine((int)(m_fAngleOfRepulsion))*fModAccelerationOfPolygon);

        m_Object.m_fVx=m_Object.m_fVx+fXAccelerationOfPolygon;
        m_Object.m_fVy=m_Object.m_fVy+fYAccelerationOfPolygon;
        
        float fAngularAccelerationOfPolygon=(float)((180f/3.14159f)*GetExcentricity()*((float)fForce/(float)m_Object.m_fMomentOfInertia));

        m_Object.m_fVAngular=m_Object.m_fVAngularOld + fAngularAccelerationOfPolygon;

        //Desplazamiento obligado para evitar solapes indeseados:
        float fParam=1;//To do, calcular este parámetro, que esterá relacionada con la penetración de un objeto sobre otro. De momento lo hago así
        float fAlejamientoX=fXAccelerationOfPolygon*fParam;
        float fAlejamientoY=fYAccelerationOfPolygon*fParam;
         //To do, calcular este giro, que esterá relacionado con la penetración de un objeto sobre otro (y la excentricidad). De momento lo hago así
        float fGiro=fAngularAccelerationOfPolygon*fParam;

        m_Object.setX(m_Object.getXNew()+fAlejamientoX);
        m_Object.setY(m_Object.getYNew()+fAlejamientoY);
        m_Object.setDegree(m_Object.getDegreeNew()+fGiro);
        */
        float fModAccelerationOfPolygon = fForce/(float)m_Object.m_fMass;

        float fXAccelerationOfPolygon = (float)(CMath.cosine((int)(m_fAngleOfRepulsion))*fModAccelerationOfPolygon);
        float fYAccelerationOfPolygon = (float)(CMath.sine((int)(m_fAngleOfRepulsion))*fModAccelerationOfPolygon);

        if (bDrawCollisionsForDebug)
            DrawCollisionData(fXAccelerationOfPolygon,fYAccelerationOfPolygon);
        
    }
    protected void DrawCollisionData(float fFX, float fFY){

        float fXOriginOfArrow=m_Object.getXNew() + m_fCollPositionModRel*CMath.cosine(m_fCollPositionAngle);
        float fYOriginOfArrow=m_Object.getYNew() + m_fCollPositionModRel*CMath.sine(m_fCollPositionAngle);

        m_pPhysEngRunnableGameCanvas.setColor(255, 0, 0);

        float fAmplificationOfForce=100;
        m_pPhysEngRunnableGameCanvas.m_pScene.getCamera().drawLine(
                fXOriginOfArrow,
                fYOriginOfArrow,
                fXOriginOfArrow+fAmplificationOfForce*fFX,
                fYOriginOfArrow+fAmplificationOfForce*fFY);

//        if (bDrawCollisionsForDebug){
//            m_pPhysEngRunnableGameCanvas.flushGraphics();
//            try {
//                   Thread.sleep(500);
//
//            } catch (InterruptedException ie) {
//
//            }
//        }
    }
    public CPointCartesianCoord getAbsoluteCartesianCoordOfCollision(){
        CPointCartesianCoord pCollCartesCoordRel=CMath.polarToCartesian(m_fCollPositionModRel,m_fCollPositionAngle);
        CPointCartesianCoord pCollCartesCoordAbs= new CPointCartesianCoord(m_Object.getX()+pCollCartesCoordRel.m_fX,m_Object.getY()+pCollCartesCoordRel.m_fY);
        return pCollCartesCoordAbs;
    }
    public float getVNewAngleOfSuperGroup(){        
        return m_Object.getVNewAngleOfSuperGroup();
    }
    public float getVNewModuleOfSuperGroup(){
        return m_Object.getVNewModuleOfSuperGroup();
    }
    public float getAngleOfRepulsion(){
        return m_fAngleOfRepulsion;
    }
    public float getVAngularOfSuperGroup(){
        return m_Object.getVAngularOfSuperGroup();
    }
    public float getExcentricityWithSignOfSuperGroup(){
        CPointCartesianCoord pCollCartesCoordAbs=getAbsoluteCartesianCoordOfCollision();
        return m_Object.getExcentricityWithSignOfVectorOfSuperGroup(pCollCartesCoordAbs.m_fX, pCollCartesCoordAbs.m_fY, m_fAngleOfRepulsion);
        
    }
    public float getMomentOfInertiaOfSuperGroup(){
        return m_Object.getMomentOfInertiaOfSuperGroup();
    }
    public float getMassOfSuperGroup(){
        return m_Object.getMassOfSuperGroup();
    }

}
