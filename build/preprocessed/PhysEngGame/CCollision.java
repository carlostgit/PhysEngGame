/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CCollision {
    int m_lTypeOfCollision = CONSTANTS.NO_TYPE_OF_COLLISION;
    boolean m_bFirstImpact=false;
    
    CCollidingObject m_CollidingObject1=null;
    CCollidingObject m_CollidingObject2=null;

    PhysEngRunnableGameCanvas m_pPruebasGameCanvas;

    public CCollision (PhysEngRunnableGameCanvas pPruebasGameCanvas,CPoint Point,CPolygon Polygon, CCollisionData CollisionData)
    {
        m_pPruebasGameCanvas=pPruebasGameCanvas;
        m_bFirstImpact=true;
        m_lTypeOfCollision=CONSTANTS.POLYGON_COLLIDING_WITH_POINT;
        
        m_CollidingObject1=new CCollidingObject(m_pPruebasGameCanvas, Point, CollisionData.m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon);
        m_CollidingObject2=new CCollidingObject(m_pPruebasGameCanvas, Polygon, CollisionData.m_fAngleOfRepulsionOfPolygon, CollisionData.m_fCollPositionModRel, CollisionData.m_fCollPositionAngle);

        //System.out.println("New collision" +m_CollidingObject1.m_Object.m_sName+" and "+m_CollidingObject2.m_Object.m_sName);
    }
    
    public CCollision (PhysEngRunnableGameCanvas pPruebasGameCanvas,CPolygon Polygon1,int lVertexOfPolygon1, CPolygon Polygon2, CCollisionData CollisionData)
    {
        m_pPruebasGameCanvas=pPruebasGameCanvas;
        m_bFirstImpact=true;
        m_lTypeOfCollision=CONSTANTS.POLYGON_COLLIDING_WITH_A_VERTEX;

        m_CollidingObject1=new CCollidingObject(m_pPruebasGameCanvas, Polygon1, lVertexOfPolygon1, CollisionData.m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon);
        m_CollidingObject2=new CCollidingObject(m_pPruebasGameCanvas, Polygon2, CollisionData.m_fAngleOfRepulsionOfPolygon, CollisionData.m_fCollPositionModRel, CollisionData.m_fCollPositionAngle);
        
        //System.out.println("New collision" +m_CollidingObject1.m_Object.m_sName+" and "+m_CollidingObject2.m_Object.m_sName);
    }
    public CCollision (PhysEngRunnableGameCanvas pPruebasGameCanvas,CPolygon Polygon1, CPolygon Polygon2, CCollisionData CollisionData1, CCollisionData CollisionData2)
    {
        m_pPruebasGameCanvas=pPruebasGameCanvas;
        m_bFirstImpact=true;
        m_lTypeOfCollision=CONSTANTS.POLYGON_COLLIDING_WITH_POLYGON_WITHOUT_VERTEXES;

        m_CollidingObject1=new CCollidingObject(m_pPruebasGameCanvas, Polygon1, CollisionData1.m_fAngleOfRepulsionOfPolygon, CollisionData1.m_fCollPositionModRel, CollisionData1.m_fCollPositionAngle);
        m_CollidingObject2=new CCollidingObject(m_pPruebasGameCanvas, Polygon2, CollisionData2.m_fAngleOfRepulsionOfPolygon, CollisionData2.m_fCollPositionModRel, CollisionData2.m_fCollPositionAngle);

        //System.out.println("New collision" +m_CollidingObject1.m_Object.m_sName+" and "+m_CollidingObject2.m_Object.m_sName);
    }
    
    public boolean AreAlreadyColliding(CPoint Point, CPolygon Polygon)
    {
        if (Point==m_CollidingObject1.m_Object && Polygon==m_CollidingObject2.m_Object)
        {
            return true;
        }
        else
        {  
            return false;
        }
        
    }
    public boolean AreAlreadyColliding(CPolygon Polygon1, int lVertexOfPolygon1, CPolygon Polygon2)
    {
        if ((Polygon1==m_CollidingObject1.m_Object && lVertexOfPolygon1==m_CollidingObject1.m_lVertexOfPolygon && Polygon2==m_CollidingObject2.m_Object))
        {
            return true;
        }
        else
        {  
            return false;
        }
        
    }
    
    public boolean AreAlreadyColliding(CPolygon Polygon1, CPolygon Polygon2)
    {
        if ((Polygon1==m_CollidingObject1.m_Object && Polygon2==m_CollidingObject2.m_Object))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    
    public boolean AreAlreadyCollidingWithoutVertexes(CPolygon Polygon1, CPolygon Polygon2)
    {
        if ((CONSTANTS.POLYGON_COLLIDING_WITH_POLYGON_WITHOUT_VERTEXES==m_lTypeOfCollision && Polygon1==m_CollidingObject1.m_Object && Polygon2==m_CollidingObject2.m_Object ))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public void sumCollisionAccelerations(){

        //System.out.println("Calculating collision");

        //Movement, to separate the 2 objects colliding

        float CollAngle12 = CMath.GetVectorAngle(m_CollidingObject1.m_Object.getXNew(), m_CollidingObject1.m_Object.getYNew(), m_CollidingObject2.m_Object.getXNew(), m_CollidingObject2.m_Object.getYNew());

        float fUnionModule=CMath.calculateModule(m_CollidingObject2.m_Object.getXNew()-m_CollidingObject1.m_Object.getXNew(), m_CollidingObject2.m_Object.getYNew()-m_CollidingObject1.m_Object.getYNew());
        float fDebugUnionLineX=(float)(CMath.cosine((int)(CollAngle12 ))*fUnionModule);
        float fDebugUnionLineY= (float)(CMath.sine((int)(CollAngle12 ))*fUnionModule);
        m_pPruebasGameCanvas.m_pScene.AddDebugLine(m_CollidingObject1.m_Object.getXNew(),m_CollidingObject1.m_Object.getYNew(),
                m_CollidingObject1.m_Object.getXNew() + fDebugUnionLineX, m_CollidingObject1.m_Object.getYNew() + fDebugUnionLineY,
                255, 255, 255);

        float fMovementMod=-1;
        float fXMovement1= (float)(CMath.cosine((int)(CollAngle12 ))*fMovementMod);
        float fYMovement1= (float)(CMath.sine((int)(CollAngle12 ))*fMovementMod);

        m_CollidingObject1.m_Object.incrementNewX(fXMovement1);
        m_CollidingObject1.m_Object.incrementNewY(fYMovement1);

        float fXMovement2= -fXMovement1;
        float fYMovement2= -fYMovement1;

        m_CollidingObject2.m_Object.incrementNewX(fXMovement2);
        m_CollidingObject2.m_Object.incrementNewY(fYMovement2);

        
        //Calculate force for solid collision in one cycle
        //if (m_bFirstImpact)
        {

            float fForceOfCollision=0.0f;
            float fForceOfCollisionWithEnergyConservation=0;

            float fV1AngleDegrees=m_CollidingObject1.getVNewAngleOfSuperGroup();
            float fV2AngleDegrees=m_CollidingObject2.getVNewAngleOfSuperGroup();

            float fV1Mod=m_CollidingObject1.getVNewModuleOfSuperGroup();
            float fV2Mod=m_CollidingObject2.getVNewModuleOfSuperGroup();
            float fCosF1V1=CMath.cosine(((m_CollidingObject1.getAngleOfRepulsion()-fV1AngleDegrees+180)));
            float fCosF2V2=CMath.cosine(((m_CollidingObject2.getAngleOfRepulsion()-fV2AngleDegrees+180)));
            float fVAng1Radians=(CONSTANTS.DEGREES_TO_RADIANS)*(m_CollidingObject1.getVAngularOfSuperGroup());
            float fVAng2Radians=(CONSTANTS.DEGREES_TO_RADIANS)*(m_CollidingObject2.getVAngularOfSuperGroup());
            float fEccentricity1=m_CollidingObject1.getExcentricityWithSignOfSuperGroup();
            float fEccentricity2=m_CollidingObject2.getExcentricityWithSignOfSuperGroup();
            float fMomentOfInertia1=(float)m_CollidingObject1.getMomentOfInertiaOfSuperGroup();
            float fMomentOfInertia2=(float)m_CollidingObject2.getMomentOfInertiaOfSuperGroup();
            float fMass1=(float)m_CollidingObject1.getMassOfSuperGroup();
            float fMass2=(float)m_CollidingObject2.getMassOfSuperGroup();

            float fNumeratorWithEnergyConservation=
                    fV1Mod*fCosF1V1 - fVAng1Radians*fEccentricity1 +
                    fV2Mod*fCosF2V2 - fVAng2Radians*fEccentricity2;

            float fDenominatorWithEnergyConservation=
                    (1.0f/fMass1)+ (fEccentricity1*fEccentricity1/fMomentOfInertia1)+
                    (1.0f/fMass2)+ (fEccentricity2*fEccentricity2/fMomentOfInertia2);

            fForceOfCollisionWithEnergyConservation=(float)(fNumeratorWithEnergyConservation/fDenominatorWithEnergyConservation);
            if(fForceOfCollisionWithEnergyConservation<0)
                fForceOfCollisionWithEnergyConservation=0;

            //Derivating the equations we can see that the maximum lost of energy in the colission is with half of the force for
            //the colission wher all the energy is conserved.
            float fForceOfCollisionWithMaxEnergyLost=fForceOfCollisionWithEnergyConservation/2;

            //float fForceOfCollisionWithSomeEnergyLost=(float)(1.0*(float)fForceOfCollisionWithEnergyConservation);
            float fForceOfCollisionWithSomeEnergyLost=(float)(1*(float)fForceOfCollisionWithEnergyConservation);

            fForceOfCollision=fForceOfCollisionWithSomeEnergyLost;

            //With immobile objects I double the force, because the immobile object will return all the force instead of absorbing it
            //I have to do this, becaus I'm artificially impeding the movement of the immobile objet after the collision, but I am
            //calculating the collision with a immobile objet as if the immobile object was a mobile object with a great mass.
            if(m_CollidingObject1.m_Object.m_bImmobile || m_CollidingObject2.m_Object.m_bImmobile)
            {
                fForceOfCollision=fForceOfCollision*1;
            }
            m_CollidingObject1.ApplyForceToObject(fForceOfCollision);
            m_CollidingObject2.ApplyForceToObject(fForceOfCollision);

            //System.out.println("First impact collision " +m_lTypeOfCollision);

            m_bFirstImpact=false;
        }
//        else
        //Calculate effects of collision state in all cycles
        {
            
            //Increment of velocity
            //float fVelIncrementMod=-1;
            float fVelIncrementMod=0;
            
            float fXVelIncrement1= (float)(CMath.cosine((int)(CollAngle12 ))*fVelIncrementMod);
            float fYVelIncrement1= (float)(CMath.sine((int)(CollAngle12 ))*fVelIncrementMod);

            m_CollidingObject1.m_Object.incrementNewVelX(fXVelIncrement1);
            m_CollidingObject1.m_Object.incrementNewVelY(fYVelIncrement1);

            float fXVelIncrement2= -fXVelIncrement1;
            float fYVelIncrement2= -fYVelIncrement1;

            m_CollidingObject2.m_Object.incrementNewVelX(fXVelIncrement2);
            m_CollidingObject2.m_Object.incrementNewVelY(fYVelIncrement2);            

            //Increment of position
            //float fIncrementMod=1;
            
            
            //Increment of position with angle between objects
//            float fXIncrement1= (float)(CMath.cosine((int)(CollAngle12 ))*fIncrementMod);
//            float fYIncrement1= (float)(CMath.sine((int)(CollAngle12 ))*fIncrementMod);
//            
            
            float fIncrementAngle=0.1f;
            if(m_CollidingObject1.getExcentricityWithSignOfSuperGroup()>0)
                m_CollidingObject1.m_Object.incrementNewAngle(fIncrementAngle);
            else
                m_CollidingObject1.m_Object.incrementNewAngle(-fIncrementAngle);
            if(m_CollidingObject2.getExcentricityWithSignOfSuperGroup()>0)
                m_CollidingObject2.m_Object.incrementNewAngle(fIncrementAngle);
            else
                m_CollidingObject2.m_Object.incrementNewAngle(-fIncrementAngle);
            
            float fIncrementMod=1;
            //Increment of position with angle of repulsion
            float fXIncrement1= (float)(CMath.cosine((int)(m_CollidingObject1.getAngleOfRepulsion() ))*fIncrementMod);
            float fYIncrement1= (float)(CMath.sine((int)(m_CollidingObject1.getAngleOfRepulsion() ))*fIncrementMod);
           
             //Increment of position with angle of repulsion
            float fXIncrement2= (float)(CMath.cosine((int)(m_CollidingObject2.getAngleOfRepulsion() ))*fIncrementMod);
            float fYIncrement2= (float)(CMath.sine((int)(m_CollidingObject2.getAngleOfRepulsion() ))*fIncrementMod);
            
            m_CollidingObject1.m_Object.incrementNewX(fXIncrement1);
            m_CollidingObject1.m_Object.incrementNewY(fYIncrement1);            
            
            m_CollidingObject2.m_Object.incrementNewX(fXIncrement2);
            m_CollidingObject2.m_Object.incrementNewY(fYIncrement2);            

            //Acceleration
            //float fIncrementAcceleration=7f;
            float fIncrementAcceleration=1f;
            m_CollidingObject1.ApplyForceToObject(fIncrementAcceleration);
            m_CollidingObject2.ApplyForceToObject(fIncrementAcceleration);
            
            
            //Change to old position                                    
//            m_CollidingObject1.m_Object.setNewPos(m_CollidingObject1.m_Object.getX(), m_CollidingObject1.m_Object.getY(),m_CollidingObject1.m_Object.m_fDegreeOld);
//            m_CollidingObject2.m_Object.setNewPos(m_CollidingObject2.m_Object.getX(), m_CollidingObject2.m_Object.getY(),m_CollidingObject2.m_Object.m_fDegreeOld);

        }

        //System.out.println("Collision calculated");
    }

    void control(){

    }

    void draw(){

        m_pPruebasGameCanvas.setColor(255, 0,0);

        float fSizeOfRect=200;
        m_pPruebasGameCanvas.m_pScene.getCamera().fillRect(
                m_CollidingObject1.m_Object.getXNew(),
                m_CollidingObject1.m_Object.getYNew(),
                fSizeOfRect,
                fSizeOfRect);
        m_pPruebasGameCanvas.m_pScene.getCamera().fillRect(
                m_CollidingObject2.m_Object.getXNew(),
                m_CollidingObject2.m_Object.getYNew(),
                fSizeOfRect,
                fSizeOfRect);
    }
    boolean hasAlreadyHadAFirstImpact(){
        return false==m_bFirstImpact;
    }
    
    boolean hasToBeFinished(){
        
        if (hasAlreadyHadAFirstImpact()){
            if(CONSTANTS.POLYGON_COLLIDING_WITH_POLYGON_WITHOUT_VERTEXES==m_lTypeOfCollision){
                if(false==CMath.testCollisionProximity((CPolygon)m_CollidingObject1.m_Object, (CPolygon)m_CollidingObject2.m_Object)){
                    return true;
                }
                else{
                    CPointCartesianCoord pPointOfDetectedIntersection= new CPointCartesianCoord();
                    if(CMathPolygonCollision.isIntersection(m_pPruebasGameCanvas.m_pScene, (CPolygon)m_CollidingObject1.m_Object, (CPolygon)m_CollidingObject2.m_Object, pPointOfDetectedIntersection)){
                        return false;
                    }
                    else{
                        return true;
                    }                   
                }                
            }
            else{
                return true;
            }
            
        }
        else{
            return false;
        }
    }
    public boolean IsObjectInCollision(CObject Obj1)
    {
        if ((Obj1==m_CollidingObject1.m_Object || Obj1==m_CollidingObject2.m_Object))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}