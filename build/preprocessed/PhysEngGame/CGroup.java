/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;
import java.util.Vector;

/** A Group is formed by Objects like polygons. Polygons can be added to or removed from a Group.
 * The physics of a Group depends on the physics of the polygons (and other objects) that form the
 * Group.
 * The physical state (of its variables) that a polygon has just before it is added to a Group, affect the
 * physical state of the group inmediately after it is added to the Group.
 *
 * When a Polygon from the m_vPolygons vector of CScene is added to a Group, that polygon is removed
 * from the m_vPolygons vector, and added to m_vObjectsInGroup vector in CGroup
 *
 * todo:
 * -paint the objets that form the Group
 * -make changes in velocities of group, depending on the position and velocities
 * of the object added
 * -make possible to remove objects from the group (recalculating mechanic
 * properties
 * -The collision with the objects of the groups, have to be calculated
 * see calculateCollisionsBetweenObjects. It is necessary to add the groups there.
 *
 * @author Carlos
 */
public class CGroup extends CObject{

    private Vector m_vObjectsInGroup = new Vector(30);
    private CScene m_pScene=null;
    //public float m_fX Is the X coordinate of the Gravity Centre
    //public float m_fY Is the Y coordinate of the Gravity Centre

    public CGroup(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas, CScene pScene, Vector vObjects)
    {        
        super(pPhysEngRunnableGameCanvas);
        this.m_lType = CONSTANTS.GROUP;
        m_pScene=pScene;
        for (int lObject = 0; lObject < vObjects.size(); lObject++) {
            CObject thisObject = ((CObject) (vObjects.elementAt(lObject)));
            addObjectToGroup(thisObject);
        }

    }
    
    public CGroup(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas, CScene pScene, CObject pObjects)
    {
        super(pPhysEngRunnableGameCanvas);
        m_pScene=pScene;
        addObjectToGroup(pObjects);
    }

    public void recalculateGroupProperties(){
        //Calculation of the Mass
        recalculateMass();
        //Calculation of the gravity centre
        recalculateGroupPositions();
        //Calculation of the moment of inertia
        recalculateMomentOfInertia();
        //Calculation of relative coordenates of the objects to the Group Centre
        recalculateRelativeCoordinatesOfGroupObjects();

    }
    public void addObjectToGroup(CObject pObject){
        m_vObjectsInGroup.addElement(pObject);
        pObject.m_Group=this;
        m_pScene.destroyThisObjectFromPolygonsCollisionsAndPointsVectors(pObject);
        applyForcesOfAddingObjectToGroup(pObject);
        recalculateGroupProperties();

    }
    private void recalculateGroupPositions(){
        
        //Group centre position (m_fX, m_fY) will be its gravity centre
        double lfSumOfX=0.0;
        double lfSumOfY=0.0;
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            lfSumOfX = lfSumOfX + thisObject.getXNew();
            lfSumOfY = lfSumOfY + thisObject.getYNew();
        }
        //m_fX=(float)lfSumOfX/m_vObjectsInGroup.size();
        //m_fY=(float)lfSumOfY/m_vObjectsInGroup.size();
        super.setInitPos((float)lfSumOfX/m_vObjectsInGroup.size(), (float)lfSumOfY/m_vObjectsInGroup.size());
        
        //Recalculate Relative Positions Of Objects
        recalculateRelativeCoordinatesOfGroupObjects();

    }
    
    public void setInitPos(float fX,float fY){
        
        //Primero se calculan los valores relativos de cada objeto
        recalculateRelativeCoordinatesOfGroupObjects();
        
        //Luego se asignan las coordenadas al grupo
        super.setInitPos(fX,fY);            
        
        //Luego se les asignan las coordenadas a los objetos a partir de sus coordenadas relativas
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));

            //X and Y of object
            //Angle of Object position reltative to the Object (but in the absolute system of coordinates)            
            float fAngleOfObjectPositionRelToGroupInAbsoluteSystemOfCoord=thisObject.m_fAngleOfObjectPositionRelativeToGroupCentre;
            float fXObject=this.getXNew()+thisObject.m_fDistanceOfObjectPositionRelativeToGroupCentre*CMath.cosine(fAngleOfObjectPositionRelToGroupInAbsoluteSystemOfCoord);
            float fYObject=this.getYNew()+thisObject.m_fDistanceOfObjectPositionRelativeToGroupCentre*CMath.sine(fAngleOfObjectPositionRelToGroupInAbsoluteSystemOfCoord);

            //Angle of object
            float fDegreeObject=thisObject.m_fAngleOfObjectRelativeToGroupAngle+m_fDegree;
            
            thisObject.setInitPos(fXObject,fYObject,fDegreeObject);
        }

        
        
    }
    
    public void setInitPos(float fX,float fY, float fAngle){
        
        //Primero se calculan los valores relativos de cada objeto
        recalculateRelativeCoordinatesOfGroupObjects();
        
        //Luego se asignan las coordenadas al grupo
        super.setInitPos(fX,fY,fAngle);            
        
        //Luego se les asignan las coordenadas a los objetos a partir de sus coordenadas relativas
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));

            //X and Y of object
            //Angle of Object position reltative to the Object (but in the absolute system of coordinates)            
            float fAngleOfObjectPositionRelToGroupInAbsoluteSystemOfCoord=thisObject.m_fAngleOfObjectPositionRelativeToGroupCentre;
            float fXObject=this.getXNew()+thisObject.m_fDistanceOfObjectPositionRelativeToGroupCentre*CMath.cosine(fAngleOfObjectPositionRelToGroupInAbsoluteSystemOfCoord);
            float fYObject=this.getYNew()+thisObject.m_fDistanceOfObjectPositionRelativeToGroupCentre*CMath.sine(fAngleOfObjectPositionRelToGroupInAbsoluteSystemOfCoord);

            //Angle of object
            float fDegreeObject=thisObject.m_fAngleOfObjectRelativeToGroupAngle+m_fDegree;
            
            thisObject.setInitPos(fXObject,fYObject,fDegreeObject);
        }

        
        
    }
    
    private void RecalculateRelativePositionsOfObjects(float fXMovOfGC,float fYMovOfGC){
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            thisObject.m_fAngleOfObjectPositionRelativeToGroupCentre=CMath.GetVectorAngle(this.getXNew(), this.getYNew(), thisObject.getXNew(), thisObject.getYNew())-m_fDegreeOld;
            thisObject.m_fDistanceOfObjectPositionRelativeToGroupCentre=CMath.calculateModule(thisObject.getXNew()-this.getXNew(), thisObject.getYNew()-this.getYNew());
            thisObject.m_fAngleOfObjectRelativeToGroupAngle=thisObject.m_fDegreeOld-m_fDegreeOld;
        }
    }
    private void recalculateMomentOfInertia(){
        double lfSumOfMomentOfInertia=0.0;
        for (int lPoint = 0; lPoint < m_vObjectsInGroup.size(); lPoint++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lPoint)));
            //float fDistance=CMath.calculateModule(thisObject.m_fX-m_fXGravityCentre,thisObject.m_fY-m_fYGravityCentre);

            lfSumOfMomentOfInertia=
                    lfSumOfMomentOfInertia
                    //+thisObject.m_fMomentOfInertia
                    +thisObject.m_fMass*(CMath.pow2(thisObject.getX()-this.getXNew()) + CMath.pow2(thisObject.getY()-this.getYNew()))
                    +thisObject.m_fMomentOfInertia;

        }
        m_fMomentOfInertia=(float)lfSumOfMomentOfInertia;
        
    }

    private void recalculateRelativeCoordinatesOfGroupObjects(){

        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            thisObject.m_fAngleOfObjectPositionRelativeToGroupCentre=CMath.GetVectorAngle(this.getXNew(), this.getYNew(), thisObject.getXNew(), thisObject.getYNew())-m_fDegreeOld;
            thisObject.m_fDistanceOfObjectPositionRelativeToGroupCentre=CMath.calculateModule(thisObject.getXNew()-this.getXNew(), thisObject.getYNew()-this.getYNew());
            thisObject.m_fAngleOfObjectRelativeToGroupAngle=thisObject.m_fDegreeOld-m_fDegreeOld;
        }

    }
    private void recalculateMass(){
        float fGroupMass=0.0f;
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++){
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            fGroupMass=fGroupMass+thisObject.m_fMass;
        }
        m_fMass=fGroupMass;

    }
    private void applyForcesOfAddingObjectToGroup(CObject pObject)
    {
        
        //TO DO
        //This is the method that will make that, when you add a polygon
        //that has a velocity to a group that is still, the group will move
        //todo
    }
    public void draw(){
        super.draw();
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            thisObject.draw();
        }
    }
    public void calculateCollision(CPoint Point) {
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            if (thisObject.m_lType == CONSTANTS.POLYGON){
                CMathPolygonCollision.calculateCollision(m_pScene,((CPolygon)thisObject),Point);    
            }   
        }
    }
    public void calculateCollision(CPolygon Polygon) {
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            if (thisObject.m_lType == CONSTANTS.POLYGON){
                CMathPolygonCollision.calculateCollision(m_pScene,Polygon,(CPolygon)thisObject);
            }
        }
    }
    public void calculateCollision(CGroup Group) {
        Vector vObjectsInGroup = Group.getObjetsInGroup();
        for (int lObject = 0; lObject < vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (vObjectsInGroup.elementAt(lObject)));
            if (thisObject.m_lType == CONSTANTS.POLYGON){
                CPolygon thisPolygon = ((CPolygon) (vObjectsInGroup.elementAt(lObject)));
                calculateCollision(thisPolygon);
            }
        }
    }
    public void integrate(){
        super.integrate();
        
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            //thisObject.integrate();
            

            //Angle of Object position reltative to the Object (but in the absolute system of coordinates)
            float fAngleOfObjectPositionRelToGroupInAbsoluteSystemOfCoord=thisObject.m_fAngleOfObjectPositionRelativeToGroupCentre+m_fDegree;
            float fX=this.getXNew()+thisObject.m_fDistanceOfObjectPositionRelativeToGroupCentre*CMath.cosine(fAngleOfObjectPositionRelToGroupInAbsoluteSystemOfCoord);
            float fY=this.getYNew()+thisObject.m_fDistanceOfObjectPositionRelativeToGroupCentre*CMath.sine(fAngleOfObjectPositionRelToGroupInAbsoluteSystemOfCoord);

            //v=w*r
            float fPerimeterVelocity=m_fVAngular*(CONSTANTS.DEGREES_TO_RADIANS)*thisObject.m_fDistanceOfObjectPositionRelativeToGroupCentre;
            float fVx=this.getVxNew()+fPerimeterVelocity*CMath.sine(thisObject.m_fAngleOfObjectPositionRelativeToGroupCentre+m_fDegree);
            float fVy=this.getVyNew()+fPerimeterVelocity*CMath.cosine(thisObject.m_fAngleOfObjectPositionRelativeToGroupCentre+m_fDegree);

                        
            float fVAngular=m_fVAngular;
            float fDegree=thisObject.m_fAngleOfObjectRelativeToGroupAngle+m_fDegree;

            thisObject.SetCinematicVariablesTo(fX,fY,fVx,fVy,fVAngular,fDegree);            
            thisObject.nextStates();

        }

        /*
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            thisObject.integrate();
        }
        */
    }
    public void saveOldStates(){
        super.saveOldStates();
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            thisObject.saveOldStates();
        }
    }
    
    public float getCollisionTestXMax(){
        float fCollisionTestXMax=0.0f;
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            if (0==lObject){
                fCollisionTestXMax=thisObject.getX();
            }
            if (thisObject.m_lType == CONSTANTS.POLYGON){
                if (0==lObject){
                    fCollisionTestXMax=((CPolygon)thisObject).m_fCollisionTestXMax;
                }
                else{
                    if(((CPolygon)thisObject).m_fCollisionTestXMax>fCollisionTestXMax){
                        fCollisionTestXMax=((CPolygon)thisObject).m_fCollisionTestXMax;
                    }
                }
                   
                    
            }   
        }
        return fCollisionTestXMax;
    }
    public float getCollisionTestXMin(){
        float fCollisionTestXMin=0.0f;
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            if (0==lObject){
                fCollisionTestXMin=thisObject.getX();
            }
            if (thisObject.m_lType == CONSTANTS.POLYGON){
                if (0==lObject){
                    fCollisionTestXMin=((CPolygon)thisObject).m_fCollisionTestXMin;
                }
                else{
                    if(((CPolygon)thisObject).m_fCollisionTestXMin<fCollisionTestXMin){
                        fCollisionTestXMin=((CPolygon)thisObject).m_fCollisionTestXMin;
                    }
                }
                   
                    
            }   
        }
        return fCollisionTestXMin;
    }
    public float getCollisionTestYMax(){
        float fCollisionTestYMax=0.0f;
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            if (0==lObject){
                fCollisionTestYMax=thisObject.getX();
            }
            if (thisObject.m_lType == CONSTANTS.POLYGON){
                if (0==lObject){
                    fCollisionTestYMax=((CPolygon)thisObject).m_fCollisionTestYMax;
                }
                else{
                    if(((CPolygon)thisObject).m_fCollisionTestYMax>fCollisionTestYMax){
                        fCollisionTestYMax=((CPolygon)thisObject).m_fCollisionTestYMax;
                    }
                }
                   
                    
            }   
        }
        return fCollisionTestYMax;
    }
    public float getCollisionTestYMin(){
        float fCollisionTestYMin=0.0f;
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            if (0==lObject){
                fCollisionTestYMin=thisObject.getX();
            }
            if (thisObject.m_lType == CONSTANTS.POLYGON){
                if (0==lObject){
                    fCollisionTestYMin=((CPolygon)thisObject).m_fCollisionTestYMin;
                }
                else{
                    if(((CPolygon)thisObject).m_fCollisionTestYMin<fCollisionTestYMin){
                        fCollisionTestYMin=((CPolygon)thisObject).m_fCollisionTestYMin;
                    }
                }
                   
                    
            }   
        }
        return fCollisionTestYMin;
    }
    public void ApplyForceToGroupInPointInCartesianAbsCoord(float fForce, float fAngleOfRepulsion, float fXAplicationPointAbsCoord, float fYAplicationPointAbsCoord){

        if (m_Group==null)
        {
            float fModAccelerationOfPolygon = fForce/(float)m_fMass;

            float fXAccelerationOfPolygon = (float)(CMath.cosine((int)(fAngleOfRepulsion))*fModAccelerationOfPolygon);
            float fYAccelerationOfPolygon = (float)(CMath.sine((int)(fAngleOfRepulsion))*fModAccelerationOfPolygon);

            m_fVx=m_fVx+fXAccelerationOfPolygon;
            m_fVy=m_fVy+fYAccelerationOfPolygon;

            float fExcentricity=GetExcentricityOfVector(fXAplicationPointAbsCoord,fYAplicationPointAbsCoord,fAngleOfRepulsion);

            float fAngularAccelerationOfPolygon=(float)((CONSTANTS.RADIANS_TO_DEGREES)*fExcentricity*((float)fForce/(float)m_fMomentOfInertia));

            m_fVAngular=m_fVAngular + fAngularAccelerationOfPolygon;

            //Desplazamiento obligado para evitar solapes indeseados:
            //float fParam=1;//To do, calcular este parámetro, que esterá relacionada con la penetración de un objeto sobre otro. De momento lo hago así
            //float fAlejamientoX=fXAccelerationOfPolygon*fParam;
            //float fAlejamientoY=fYAccelerationOfPolygon*fParam;
             //To do, calcular este giro, que esterá relacionado con la penetración de un objeto sobre otro (y la excentricidad). De momento lo hago así
            //float fGiro=fAngularAccelerationOfPolygon*fParam;

            //setX(getXNew()+fAlejamientoX);
            //setY(getYNew()+fAlejamientoY);
            //setDegree(getDegreeNew()+fGiro);
        }
        else
        {
            m_Group.ApplyForceToGroupInPointInCartesianAbsCoord(fForce, fAngleOfRepulsion, fXAplicationPointAbsCoord, fYAplicationPointAbsCoord);
        }
    }
    public void destroyThisObject(CObject pObject){
        m_vObjectsInGroup.removeElement(pObject);
        recalculateGroupProperties();
    }
    public Vector getObjetsInGroup(){
        return m_vObjectsInGroup;
    }
    
    public void control(){
        for (int lObject = 0; lObject < m_vObjectsInGroup.size(); lObject++) {
            CObject thisObject = ((CObject) (m_vObjectsInGroup.elementAt(lObject)));
            thisObject.control();
        }
    }
    
}

