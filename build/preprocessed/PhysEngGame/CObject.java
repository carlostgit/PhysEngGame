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
public class CObject {
    
    
    //ForceListener
    protected Vector m_vForceListeners = new Vector(30);
        
    //Variables of Group
    protected CGroup m_Group=null;
    protected float m_fAngleOfObjectPositionRelativeToGroupCentre=0.0f;
    protected float m_fDistanceOfObjectPositionRelativeToGroupCentre=0.0f;
    protected float m_fAngleOfObjectRelativeToGroupAngle=0.0f;

    //PhysEngRunnableGameCanvas reference
    protected PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas;
    
    //Params:
    protected boolean m_bCollisionable=true;
    protected boolean m_bImmobile=false;
    protected int m_lType=0;
    protected boolean m_bActivated=true;
    protected float m_fMass=1.0f;
    protected float m_fMomentOfInertia=400000;//4000000;
    //States
    private float m_fX=0;
    private float m_fY=0;
    private float m_fZ=0;
    private float m_fXOld=0;
    private float m_fYOld=0;
    private float m_fZOld=0;
    
    public float m_fCollisionTestXMax=0;
    public float m_fCollisionTestXMin=0;
    public float m_fCollisionTestYMax=0;
    public float m_fCollisionTestYMin=0;
    
    public float m_fVx=0;
    public float m_fVy=0;
    public float m_fVz=0;
    public float m_fVxOld=0;
    public float m_fVyOld=0;
    public float m_fVzOld=0;
    
    public float m_fAx=0;
    public float m_fAy=0;
    public float m_fAz=0;

    public float m_fAxOld=0;
    public float m_fAyOld=0;
    public float m_fAzOld=0;
    
    public float m_fDegree=0;
    public float m_fDegreeOld=0;

    public float m_fVAngular=0;
    public float m_fVAngularOld=0;
    public float m_fAAngular=0;
    public float m_fAAngularOld=0;
    //ID
    public int m_lID=0;
    public String m_sName="ninguno";
    static public int m_lCount=0;
    //Color
    protected int m_lColorR=0;
    protected int m_lColorG=255;
    protected int m_lColorB=255;

    public CObject(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas)
    {
        m_pPhysEngRunnableGameCanvas=pPhysEngRunnableGameCanvas;
        m_lCount=m_lCount+1;
        m_lID=m_lCount;
        
    }
    public void integrate(){

        //if (m_Group==null)
        //{
        //m_fX=m_fX+m_fVxOld;
        //m_fY=m_fY+m_fVyOld;
        this.setNewPos(this.getXNew()+this.getVX(),this.getYNew()+this.getVY());
        m_fZ=m_fZ+m_fVzOld;

        m_fVx=m_fVx+m_fAxOld;
        m_fVy=m_fVy+m_fAyOld;
        m_fVz=m_fVz+m_fAzOld;

        m_fVAngular=m_fVAngular+m_fAAngularOld;

        m_fDegree=m_fDegree+m_fVAngular;

        if (m_fDegree> 360) {
            m_fDegree= m_fDegree- 360;
        } else if (m_fDegree< 0) {
            m_fDegree = m_fDegree+ 360;
        }
        /*
        }
        else
        {
            m_fX = m_fDistanceOfObjectPositionRelativeToGroupCentre*CMath.cosine(m_Group.m_fDegreeOld+m_fAngleOfObjectPositionRelativeToGroupCentre);
            m_fY = m_fDistanceOfObjectPositionRelativeToGroupCentre*CMath.sine(m_Group.m_fDegreeOld+m_fAngleOfObjectPositionRelativeToGroupCentre);

            m_fVx=m_Group.m_fVAngularOld*(CMath.PI()/180.0f)*m_fDistanceOfObjectPositionRelativeToGroupCentre*CMath.sine(m_Group.m_fDegreeOld+m_fAngleOfObjectPositionRelativeToGroupCentre);
            m_fVy=m_Group.m_fVAngularOld*(CMath.PI()/180.0f)*m_fDistanceOfObjectPositionRelativeToGroupCentre*CMath.cosine(m_Group.m_fDegreeOld+m_fAngleOfObjectPositionRelativeToGroupCentre);

            m_fVAngular=m_Group.m_fVAngularOld;

            m_fDegree=m_Group.m_fDegreeOld+m_fAngleOfObjectRelativeToGroupAngle;

        }
        */
    }
    public void saveOldStates(){
        //m_fXOld=m_fX;
        //m_fYOld=m_fY;
        setOldPos(m_fX,m_fY);
        m_fZOld=m_fZ;
        
        m_fVxOld=m_fVx;
        m_fVyOld=m_fVy;
        m_fVzOld=m_fVz;
        
        m_fVAngularOld=m_fVAngular;

        m_fAxOld=m_fAx;
        m_fAyOld=m_fAy;
        m_fAzOld=m_fAz;
        
        if (m_fDegree> 360) {
            m_fDegree= m_fDegree- 360;
        } else if (m_fDegree< 0) {
            m_fDegree = m_fDegree+ 360;
        }

        m_fDegreeOld=m_fDegree;
    }
    public void draw(){
        m_pPhysEngRunnableGameCanvas.setColor(m_lColorR, m_lColorG, m_lColorB);
        //int lYOpposite=(m_pPhysEngRunnableGameCanvas.getHeight() << m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel)-(int)m_fY;
        //m_pPhysEngRunnableGameCanvas.m_g.fillRect((int)m_fX>> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel, lYOpposite >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel, 5000 >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel, 5000 >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel);
        float fSizeOfRect=100;
        m_pPhysEngRunnableGameCanvas.m_pScene.getCamera().fillRect(
                m_fX,
                m_fY,
                fSizeOfRect,
                fSizeOfRect);
    }
    public void addAcceleration(int lXAcceleration,int lYAcceleration){
        m_fAx=m_fAx+lXAcceleration;
        m_fAy=m_fAx+lYAcceleration;
    }
    public float getVNewAngle(){
        return CMath.GetVectorAngle(m_fVx, m_fVy);
    }
    public float getVNewModule(){
        return CMath.calculateModule(m_fVx,m_fVy);
        //float fPolygon1SquareVModule=m_fVx*m_fVx+m_fVy*m_fVy;
        //return CMath.SquareRoot(0, fPolygon1SquareVModule, fPolygon1SquareVModule, 30, 0);
    }
    public void control(){
    }
    public float getX(){
        return m_fXOld;
    }
    public float getY(){
        return m_fYOld;
    }
    public float getXNew(){
        return m_fX;
    }
    public float getYNew(){
        return m_fY;
    }
    public void setX(float fX){
        m_fX=fX;
    }
    public void setY(float fY){
        m_fY=fY;
    }
    public void setNewPos(float fX,float fY){
        m_fX=fX;
        m_fY=fY;
    }
    public void setOldPos(float fX,float fY){
        m_fXOld=fX;
        m_fYOld=fY;
        setCollisionTestCoord(fX,fY);
    }
    public void setInitPos(float fX,float fY){
        setNewPos(fX,fY);
        setOldPos(fX,fY);
    }
    public void setNewPos(float fX,float fY, float fAngle){
        setNewPos(fX,fY);
        m_fDegree=fAngle;
    }
    public void setOldPos(float fX,float fY, float fAngle){
        setOldPos(fX,fY);
        m_fDegreeOld=fAngle;
    }
    public void setInitPos(float fX,float fY, float fAngle){
        setNewPos(fX,fY,fAngle);
        setOldPos(fX,fY,fAngle);
    }
    
     public void setCollisionTestCoord(float fX,float fY){
        //I should calculate X and Y coordinates here, and keep them as states, instead of doing these calculations in draw method
        //These coordinates are very useful doing the collision tests
        
        m_fCollisionTestXMax=-Float.MAX_VALUE;
        m_fCollisionTestXMin=Float.MAX_VALUE;
        m_fCollisionTestYMax=-Float.MAX_VALUE;
        m_fCollisionTestYMin=Float.MAX_VALUE;
    
        if(m_fXOld + CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE > m_fCollisionTestXMax)
            m_fCollisionTestXMax = m_fXOld + CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE;
        if(m_fXOld - CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE < m_fCollisionTestXMin)
            m_fCollisionTestXMin = m_fXOld - CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE;
        if(m_fYOld + CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE > m_fCollisionTestYMax)
            m_fCollisionTestYMax = m_fYOld + CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE;
        if(m_fYOld - CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE < m_fCollisionTestYMin)
            m_fCollisionTestYMin = m_fYOld - CONSTANTS.EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE;
                


    }

    public float getVX(){
        return m_fVxOld;
    }
    public float getVY(){
        return m_fVyOld;
    }
    public float getVxNew(){
        return m_fVx;
    }
    public float getVyNew(){
        return m_fVy;
    }
    public void setVx(float fVx){
        m_fVx=fVx;
    }
    public void setVy(float fVy){
        m_fVy=fVy;
    }
    public float getDegree(){
        return m_fDegreeOld;
    }
    public float getDegreeNew(){
        return m_fDegree;
    }
    public void setDegree(float fDegree){
        m_fDegree=fDegree;
    }

    /* I replace ApplyForceToObject with ApplyForceToToObjectInPointInCartesianAbsCoord, because the last
     * one can be use recursively with groups, so that the group is affected by the force, and not the object
     * or the group that is inside a group
    */
    public void ApplyForceToObject(float fForce, float fAngleOfRepulsion, float fExcentricity){

        //TODO: Next step: take into account that some colliding objets, could form part
        //of a Group. I need an ApplyForceToObject(lForce, fAngleOfRepulsion) method for CObject class

        float fModAccelerationOfPolygon = fForce/(float)m_fMass;

        float fXAccelerationOfPolygon = (float)(CMath.cosine((int)(fAngleOfRepulsion))*fModAccelerationOfPolygon);
        float fYAccelerationOfPolygon = (float)(CMath.sine((int)(fAngleOfRepulsion))*fModAccelerationOfPolygon);

        m_fVx=m_fVx+fXAccelerationOfPolygon;
        m_fVy=m_fVy+fYAccelerationOfPolygon;

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
    public void ApplyForceToObjectInPointInCartesianAbsCoord(float fForce, float fAngleOfRepulsion, float fXAplicationPointAbsCoord, float fYAplicationPointAbsCoord){

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
            
            for(int i=0;i<m_vForceListeners.size();i++){
                ((IForceListener)m_vForceListeners.elementAt(i)).applyForce(this, fForce);
            }
        }
        else
        {
            m_Group.ApplyForceToGroupInPointInCartesianAbsCoord(fForce, fAngleOfRepulsion, fXAplicationPointAbsCoord, fYAplicationPointAbsCoord);
        }
        
        //notify listeners
        for(int i=0;i<m_vForceListeners.size();i++){
            ((IForceListener)m_vForceListeners.elementAt(i)).applyForce(this, fForce);
        }
    }
    
    protected float GetExcentricityOfVector(float fXOriginInAbsCoord,float fYOriginInAbsCoord, float fAngle){
        float fCollPositionRelAngle=CMath.GetVectorAngle(m_fX,m_fY,fXOriginInAbsCoord,fYOriginInAbsCoord);
        float fAngleSubstraction = CMath.getAngleSubstraction(fAngle,fCollPositionRelAngle);


        float fXOriginInRelCoord=fXOriginInAbsCoord-m_fX;
        float fYOriginInRelCoord=fYOriginInAbsCoord-m_fY;

        float fCollPositionRelMod=CMath.SquareRoot(fXOriginInRelCoord*fXOriginInRelCoord+fYOriginInRelCoord*fYOriginInRelCoord);

        return (float)fCollPositionRelMod*CMath.sine((int)(fAngleSubstraction));
    }
    public void SetCinematicVariablesTo(float fX,float fY,float fVx,float fVy,float fVAngular,float fDegree){
        m_fX=fX;
        m_fY=fY;
        m_fVx=fVx;
        m_fVy=fVy;
        m_fVAngular=fVAngular;
        m_fDegree=fDegree;

        if (m_fDegree> 360) {
            m_fDegree= m_fDegree- 360;
        } else if (m_fDegree< 0) {
            m_fDegree = m_fDegree+ 360;
        }

    }


    public float getVNewAngleOfSuperGroup(){
        if (m_Group==null){
            return getVNewAngle();
        }
        else{
            return m_Group.getVNewAngleOfSuperGroup();
        }

    }
    public float getVNewModuleOfSuperGroup(){
        if (m_Group==null){
            return getVNewModule();
        }
        else{
            return m_Group.getVNewModuleOfSuperGroup();
        }

    }
    public float getVAngularOfSuperGroup(){
        if (m_Group==null){
            return getVAngular();
        }
        else{
            return m_Group.getVAngularOfSuperGroup();
        }

    }
    public float getVAngular(){
        return m_fVAngular;
    }

    public float getExcentricityWithSignOfVectorOfSuperGroup(float fXOriginInAbsCoord,float fYOriginInAbsCoord, float fAngle){
        if (m_Group==null){
            return GetExcentricityOfVector(fXOriginInAbsCoord,fYOriginInAbsCoord,fAngle);
        }
        else{
            return m_Group.getExcentricityWithSignOfVectorOfSuperGroup(fXOriginInAbsCoord,fYOriginInAbsCoord,fAngle);
        }
    }


    public float getMomentOfInertiaOfSuperGroup(){
        if (m_Group==null){
            return getMomentOfInertia();
        }
        else{
            return m_Group.getMomentOfInertiaOfSuperGroup();
        }

    }
    public float getMomentOfInertia(){
        return this.m_fMomentOfInertia;
    }
    public float getMassOfSuperGroup(){
        if (m_Group==null){
            return getMass();
        }
        else{
            return m_Group.getMassOfSuperGroup();
        }

    }
    public float getMass(){
        return this.m_fMass;
    }
    public void incrementNewX(float fX){
        if (m_Group==null){
            m_fX=m_fX+fX;
        }
        else{
            m_Group.incrementNewX(fX);
        }
    }
    public void incrementNewY(float fY){
        if (m_Group==null){
            m_fY=m_fY+fY;
        }
        else{
            m_Group.incrementNewY(fY);
        }
        
    }
    public void incrementNewVelX(float fX){       
        if (m_Group==null){
           m_fVx=m_fVx+fX;
        }
        else{
            m_Group.incrementNewVelX(fX);
        }
    }
    public void incrementNewVelY(float fY){        
        if (m_Group==null){
           m_fVy=m_fVy+fY;
        }
        else{
            m_Group.incrementNewVelY(fY);
        }
    }
    public void incrementNewAngle(float fAngle){        
        if (m_Group==null){
           this.m_fDegree=this.m_fDegree+fAngle;
        }
        else{
            m_Group.incrementNewAngle(fAngle);
        }
    }
    
    public void setColor(int R, int G, int B){
        m_lColorR=R;
        m_lColorG=G;
        m_lColorB=B;
    
    }
    public void nextStates(){

    }
    
    public CPointPolarCoord GetRelativePolarCoordinatesFromAbsolute(float fX, float fY)
    {
        float fAngleRel=CMath.GetVectorAngle(m_fXOld, m_fYOld,fX,fY);
        float fModuleRel=CMath.calculateModule(fX-m_fXOld, fY-m_fYOld);
        return (new CPointPolarCoord(fModuleRel, fAngleRel));
    }
    public CPointCartesianCoord GetRelativeCartesianCoordinatesFromAbsolute(float fX, float fY)
    {
        return (new CPointCartesianCoord(fX-m_fXOld,fY-m_fYOld));
    }
    public CPointCartesianCoord GetAbsoluteCoordinatesFromRelative(float fXRel, float fYRel)
    {
        return (new CPointCartesianCoord(m_fXOld + fXRel,m_fYOld + fYRel));
    }
    public CPointCartesianCoord GetAbsoluteCoordinatesFromRelative(CPointPolarCoord pPolarCoordRel)
    {
        return (CMath.polarToCartesian(pPolarCoordRel.m_fModule, pPolarCoordRel.m_fDegree));
    }
    public void ApplyRelForce(float fAngle, float fForce)
    {
        ApplyForceToObjectInPointInCartesianAbsCoord(fForce, m_fDegreeOld + fAngle, m_fXOld, m_fYOld);   
    }
    public void ApplyPolarRelForce(float fApplicationPositionDistance, float fApplicationPositionAngle, float fRelAngleOfForce, float fForce)
    {
        CPointCartesianCoord pApplicationPosition=CMath.polarToCartesian(fApplicationPositionDistance, fApplicationPositionAngle);
        ApplyForceToObjectInPointInCartesianAbsCoord(fForce, m_fDegreeOld + fRelAngleOfForce, m_fXOld + pApplicationPosition.m_fX, m_fYOld + pApplicationPosition.m_fY);
    }
    public void ApplyCartRelForce(float fApplicationPositionX, float fApplicationPositionY, float fRelAngleOfForce, float fForce)
    {
        ApplyForceToObjectInPointInCartesianAbsCoord(fForce, m_fDegreeOld + fRelAngleOfForce, m_fXOld + fApplicationPositionX, m_fYOld + fApplicationPositionY);
    }
    
    public void ApplyMomentOfForce(float fMomentOfForce)
    {
        if (m_Group==null)
        {
            m_fVAngular=m_fVAngular + (fMomentOfForce/m_fMomentOfInertia)*((CONSTANTS.RADIANS_TO_DEGREES));
        }
        else
        {
            m_Group.ApplyMomentOfForce(fMomentOfForce);
        }
    }
    public void addForceListener(IForceListener pForceListener){
        m_vForceListeners.addElement(pForceListener);
    }
    public void eraseForceListener(IForceListener pForceListener){
        m_vForceListeners.removeElement(pForceListener);
    }
    public int getType(){
        return m_lType;
    }
}
