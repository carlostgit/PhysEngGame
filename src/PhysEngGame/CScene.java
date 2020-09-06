/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

        
/**
 *
 * @author Carlos
 */
public class CScene {
    PhysEngRunnableGameCanvas m_pPhysEngRunnableGameCanvas=null;
    
    //public int m_bScenarioToBeCharged=CONSTANTS.GROUP_STAGE;
    
    //Camera
    private CCamera m_pCamera=null;
    private CObject m_pCamObj=null;

    //Debug
    long m_lTiempoControlObjects=0;
    long m_lTiempoControlCamara=0;
    long m_lTiempoIntegration=0;
    long m_lTiempoCollObjects=0;
    long m_lTiempoCollLimits=0;
    long m_lTiempoCollAcc=0;
    long m_lTiempoOldStates=0;
    long m_lTiempoDrawBack=0;
    long m_lTiempoDrawObjects=0;
    long m_lTiempoForeground=0;
    long m_lTiempoDrawDebug=0;
    
    //debug lines and points
    private Vector m_vDebugLines = new Vector(30);
    private Vector m_vDebugPoints = new Vector(30);
    
    
    
    
    //Limits. floor, ceiling, walls
    public float m_lfLeftLimit=0.0f;
    public float m_lfRightLimit=0.0f;
    public float m_lfTopLimit=0.0f;
    public float m_lfBottomLimit=0.0f;
    //public float m_lfLimitsWidth;

    private Vector m_vPoints = new Vector(30);
    private Vector m_vPolygons = new Vector(30);
    private Vector m_vCollisions = new Vector(100);
    private Vector m_vGroups = new Vector(30);
    
    
    //SceneControl
    //CSceneControl m_pSceneControl=null;

    public CScene(PhysEngRunnableGameCanvas pPhysEngRunnableGameCanvas,CGraphics pMyGraphics){
        m_pPhysEngRunnableGameCanvas=pPhysEngRunnableGameCanvas;
        m_pCamera=new CCamera(pPhysEngRunnableGameCanvas,this,pMyGraphics);
        
        
    }
    
    public void destroyThisObjectFromPolygonsCollisionsAndPointsVectors(CObject pObject){
        if(this.m_pCamObj==pObject)
            this.m_pCamObj=null;
        
        for (int lPoint = 0; lPoint < m_vPoints.size(); lPoint++) {
            CObject thisObject = ((CObject) (m_vPoints.elementAt(lPoint)));
            if (thisObject == pObject) {
                m_vPoints.removeElementAt(lPoint);
                lPoint--;
            }
        }
        for (int lPolygon = 0; lPolygon < m_vPolygons.size(); lPolygon++) {
            CObject thisObject = ((CObject) (m_vPolygons.elementAt(lPolygon)));
            if (thisObject == pObject) {
                m_vPolygons.removeElementAt(lPolygon);
                lPolygon--;
            }
        }
        for (int lCol = 0; lCol < m_vCollisions.size(); lCol++) {
            CCollision thisCollision = ((CCollision) (m_vCollisions.elementAt(lCol)));
            if (thisCollision.IsObjectInCollision(pObject)) {
                m_vCollisions.removeElementAt(lCol);
                lCol--;
            }
        }
    }
    
    public void destroyThisObjectEverywhere(CObject pObject){
        if(this.m_pCamObj==pObject)
            this.m_pCamObj=null;
        
        destroyThisObjectFromPolygonsCollisionsAndPointsVectors(pObject);      
        for (int i=0;i<m_vGroups.size();i++){
            ((CGroup)m_vGroups.elementAt(i)).destroyThisObject(pObject);
            CObject thisObject = (CObject)(m_vGroups.elementAt(i));
            if (thisObject==pObject){
                m_vGroups.removeElementAt(i);
                i--;
            }
            
            
        }
        
    }
//    public void shoot(CPolygon Polygon) {
//
//        CPoint Point = new CPoint(m_pPhysEngRunnableGameCanvas);
//        //Point.m_fX = Polygon.getX();
//        //Point.m_fY = Polygon.getY();
//        //Point.m_fXOld = Polygon.getX();
//        //Point.m_fYOld = Polygon.getY();
//        Point.setInitPos(Polygon.getX(), Polygon.getY());
//        
//        int lVelocidad = 1000;
//        Point.m_fVx = Polygon.m_fVxOld + (int) ((float) lVelocidad * CMath.cosine((int)Polygon.m_fDegreeOld));
//        Point.m_fVy = Polygon.m_fVyOld + (int) ((float) lVelocidad * CMath.sine((int)Polygon.m_fDegreeOld));
//        Point.m_lCyclesCount = 0;
//        Point.m_fMass = 1;
//        m_vPoints.addElement(Point);
//    }
    public void shoot(CPolygon Polygon,float fModRelShootPoint,float fAngRelShootPoint) {

        CPoint Point = new CPoint(m_pPhysEngRunnableGameCanvas);
        //Point.m_fX = Polygon.getX()+fModRelShootPoint*CMath.cosine((int)(Polygon.m_fDegreeOld+fAngRelShootPoint));
        //Point.m_fY = Polygon.getY()+fModRelShootPoint*CMath.sine((int)(Polygon.m_fDegreeOld+fAngRelShootPoint));
        //Point.m_fXOld = Point.m_fX;
        //Point.m_fYOld = Point.m_fY;        
        Point.setInitPos(Polygon.getX()+fModRelShootPoint*CMath.cosine((int)(Polygon.m_fDegreeOld+fAngRelShootPoint)), Polygon.getY()+fModRelShootPoint*CMath.sine((int)(Polygon.m_fDegreeOld+fAngRelShootPoint)));

        int lVelocidad = 300;
        Point.m_fVx = Polygon.m_fVxOld + (int) ((float) lVelocidad * CMath.cosine((int)Polygon.m_fDegreeOld));
        Point.m_fVy = Polygon.m_fVyOld + (int) ((float) lVelocidad * CMath.sine((int)Polygon.m_fDegreeOld));
        Point.m_lMaxCount = 50;
        Point.m_fMass = 0.1f;
        m_vPoints.addElement(Point);
    }
    public void velocityAndPositionIntegration() {
        for (int lPoint = 0; lPoint < m_vPoints.size(); lPoint++) {
            CPoint Point = ((CPoint) (m_vPoints.elementAt(lPoint)));
            Point.integrate();
        }
        for (int lPolygon = 0; lPolygon < m_vPolygons.size(); lPolygon++) {
            CPolygon Polygon = ((CPolygon) (m_vPolygons.elementAt(lPolygon)));
            Polygon.integrate();
        }
        for (int lGroup = 0; lGroup < m_vGroups.size(); lGroup++) {
            CGroup Group= ((CGroup) (m_vGroups.elementAt(lGroup)));
            Group.integrate();
        }
    }
    public void saveOldStates() {
        for (int lPoint = 0; lPoint < m_vPoints.size(); lPoint++) {
            CPoint Point = ((CPoint) (m_vPoints.elementAt(lPoint)));
            Point.saveOldStates();
        }
        
        for (int lPolygon = 0; lPolygon < m_vPolygons.size(); lPolygon++) {
            CPolygon Polygon = ((CPolygon) (m_vPolygons.elementAt(lPolygon)));
            Polygon.saveOldStates();
        }
        for (int lGroup = 0; lGroup < m_vGroups.size(); lGroup++) {
            CGroup Group= ((CGroup) (m_vGroups.elementAt(lGroup)));
            Group.saveOldStates();
        }
    }
    public void sumCollisionAccelerations() {

        for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
            CCollision Collision = ((CCollision) (m_vCollisions.elementAt(lCollision)));
            Collision.sumCollisionAccelerations();            
        }
    }
    public void drawObjects() {
        m_pPhysEngRunnableGameCanvas.setColor(255, 255, 255);
        for (int lPoint = 0; lPoint < m_vPoints.size(); lPoint++) {
            CPoint Point = ((CPoint) (m_vPoints.elementAt(lPoint)));
            Point.draw();
        }
        for (int lPolygon = 0; lPolygon < m_vPolygons.size(); lPolygon++) {
            CPolygon Polygon = ((CPolygon) (m_vPolygons.elementAt(lPolygon)));
            Polygon.draw();
        }
        for (int lGroup = 0; lGroup < m_vGroups.size(); lGroup++) {
            CGroup Group= ((CGroup) (m_vGroups.elementAt(lGroup)));
            Group.draw();
        }
        for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
            CCollision Collision = ((CCollision) (m_vCollisions.elementAt(lCollision)));
            Collision.draw();
        }
    }
    public void controlObjects() {


        //Control of points
        //Destruction of points because of the time lapsed

        for (int lPoint = 0; lPoint < m_vPoints.size(); lPoint++) {
            CPoint Point = ((CPoint) (m_vPoints.elementAt(lPoint)));
            Point.control();
        }

        //Polygons
        for (int lPolygon = 0; lPolygon < m_vPolygons.size(); lPolygon++) {
            CPolygon Polygon = ((CPolygon) (m_vPolygons.elementAt(lPolygon)));
            if (Polygon.m_bActivated) {
                Polygon.control();
            }
        }

        //Collisions
        for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
            CCollision Collision = ((CCollision) (m_vCollisions.elementAt(lCollision)));
            Collision.control();
        }

        //Groups
        for (int lGroup = 0; lGroup < m_vGroups.size(); lGroup++) {
            CGroup Group = ((CGroup) m_vGroups.elementAt(lGroup));
            Group.control();
        }

    }
    public void calculateCollisionsWithLimitsForObjects() {

        int lCollisionAcceleration = 10;
        float fMovement=1;
        for (int lPolygon = 0; lPolygon < m_vPolygons.size(); lPolygon++) {
            CPolygon Polygon = ((CPolygon) m_vPolygons.elementAt(lPolygon));

            if (Polygon.getXNew() < m_lfLeftLimit) {
                Polygon.m_fVx = Polygon.m_fVxOld+lCollisionAcceleration;
                Polygon.incrementNewX(fMovement);

            } else if (Polygon.getXNew() > m_lfRightLimit) {
                Polygon.m_fVx = Polygon.m_fVxOld-lCollisionAcceleration;
                Polygon.incrementNewX(-fMovement);
            }
            if (Polygon.getYNew() < m_lfBottomLimit) {
                Polygon.m_fVy = Polygon.m_fVyOld+lCollisionAcceleration;
                Polygon.incrementNewY(fMovement);
            } else if (Polygon.getYNew() > m_lfTopLimit) {
                Polygon.m_fVy = Polygon.m_fVyOld-lCollisionAcceleration;
                Polygon.incrementNewY(-fMovement);
            }
        }
        for (int lGroup = 0; lGroup < m_vGroups.size(); lGroup++) {
            CGroup Group = ((CGroup) m_vGroups.elementAt(lGroup));

            if (Group.getXNew() < m_lfLeftLimit) {
                Group.m_fVx = Group.m_fVxOld+lCollisionAcceleration;
            } else if (Group.getXNew() > m_lfRightLimit) {
                Group.m_fVx = Group.m_fVxOld-lCollisionAcceleration;
            }
            if (Group.getYNew() < m_lfBottomLimit) {
                Group.m_fVy = Group.m_fVyOld+lCollisionAcceleration;
            } else if (Group.getYNew() > m_lfTopLimit) {
                Group.m_fVy = Group.m_fVyOld-lCollisionAcceleration;
            }
        }
    }

    /*
    private boolean isPointInsideTriangle(CPointCartesianCoord PointCoord, CPolygon Polygon) {

        float[] fXVertexes = new float[3];
        float[] fYVertexes = new float[3];
        getVertexesOfTriangle(Polygon, fXVertexes, fYVertexes);

        int lNumOfLinesWithTheXOfThePoint = 0;//it may be 0 or two (in a triangle)

        int lNumOfVertexes = 3;
        int[] lIndexesOfLinesWithTheXOfThePoint = new int[lNumOfVertexes - 1];
        for (int lVert = 0; lVert < lNumOfVertexes; lVert++) {
            int lVertSiguiente = lVert + 1;
            if (lVert >= lNumOfVertexes - 1) {
                lVertSiguiente = 0;
            }

            if ((PointCoord.m_fX > fXVertexes[lVertSiguiente] && PointCoord.m_fX <= fXVertexes[lVert]) || (PointCoord.m_fX <= fXVertexes[lVertSiguiente] && PointCoord.m_fX > fXVertexes[lVert])) {
                lIndexesOfLinesWithTheXOfThePoint[lNumOfLinesWithTheXOfThePoint] = lVert;
                lNumOfLinesWithTheXOfThePoint++;
            }
        }
        boolean[] bPointIsHigher = new boolean[lNumOfLinesWithTheXOfThePoint];

        for (int lLine = 0; lLine < lNumOfLinesWithTheXOfThePoint; lLine++) {
            //System.out.println(lNumOfLinesWithTheXOfThePoint);

            int lLinePointA = lIndexesOfLinesWithTheXOfThePoint[lLine];
            int lLinePointB = lIndexesOfLinesWithTheXOfThePoint[lLine] + 1;

            //System.out.println("A:"+lLinePointA);
            //System.out.println("B:"+lLinePointB);

            if (lIndexesOfLinesWithTheXOfThePoint[lLine] == lNumOfVertexes - 1) {
                lLinePointB = 0;
            }

            float lfSlope = ((float) (fYVertexes[lLinePointB] - fYVertexes[lLinePointA])) / ((float) (fXVertexes[lLinePointB] - fXVertexes[lLinePointA]));
            float lfXIncrease = (float) (PointCoord.m_fX) - fXVertexes[lLinePointA];
            float lYLine = fYVertexes[lLinePointA] + (int) (lfSlope * lfXIncrease);
            //System.out.println("lfSlope:"+lfSlope);
            //System.out.println("YPoint:"+m_lYPoint[lPoint]);
            //System.out.println("YLine:"+lYLine);

            if (PointCoord.m_fY > lYLine) {
                bPointIsHigher[lLine] = true;
            } else {
                bPointIsHigher[lLine] = false;
            }
        }

        //If the point is higher than a odd number of lines, then the point is inside the polygon
        int lNumOfLinesBelow = 0;
        for (int lLine = 0; lLine < lNumOfLinesWithTheXOfThePoint; lLine++) {
            if (true == bPointIsHigher[lLine]) {
                lNumOfLinesBelow++;
            }
        }

        //Tendría que mirar si lNumOfLinesBelow es impar
        //Par=even (but only in maths)
        //Impar=odd
        if (CMath.esImpar(lNumOfLinesBelow)) {
            return true;
        } else {
            return false;
        }
    }
    */

    /**
     * All types of collision are evaluated when this method is called
     */
    public void calculateCollisionsBetweenObjects() {
        //collisions of shots with Polygons
        for (int lPoint = 0; lPoint < m_vPoints.size(); lPoint++) {
            CPoint Point = ((CPoint) (m_vPoints.elementAt(lPoint)));
            for (int lPolygon = 0; lPolygon < m_vPolygons.size(); lPolygon++) {
                CPolygon Polygon = ((CPolygon) m_vPolygons.elementAt(lPolygon));
                if (Polygon.m_bCollisionable) {
                    CMathPolygonCollision.calculateCollision(this,Polygon,Point);
                }
            }
        }
        //collisions of Polygons with Polygons
        for (int lPolygon1 = 0; lPolygon1 < m_vPolygons.size(); lPolygon1++) {
            CPolygon Polygon1 = ((CPolygon) m_vPolygons.elementAt(lPolygon1));
            for (int lPolygon2 = lPolygon1 + 1; lPolygon2 < m_vPolygons.size(); lPolygon2++) {
                CPolygon Polygon2 = ((CPolygon) m_vPolygons.elementAt(lPolygon2));
                if (Polygon1.m_bCollisionable && Polygon2.m_bCollisionable) {
                    CMathPolygonCollision.calculateCollision(this,Polygon1, Polygon2);
                }

            }
        }
        //collisions of shots with Groups
        for (int lPoint = 0; lPoint < m_vPoints.size(); lPoint++) {
            CPoint Point = ((CPoint) (m_vPoints.elementAt(lPoint)));
            for (int lGroup = 0; lGroup < m_vGroups.size(); lGroup++) {
                CGroup Group = ((CGroup) m_vGroups.elementAt(lGroup));
                if (Group.m_bCollisionable) {
                    Group.calculateCollision(Point);
                }
            }
        }

        //collisions of Polygons with Groups
        for (int lPolygon = 0; lPolygon < m_vPolygons.size(); lPolygon++) {
            CPolygon Polygon = ((CPolygon) (m_vPolygons.elementAt(lPolygon)));
            for (int lGroup = 0; lGroup < m_vGroups.size(); lGroup++) {
                CGroup Group = ((CGroup) m_vGroups.elementAt(lGroup));
                if (Group.m_bCollisionable) {
                    Group.calculateCollision(Polygon);
                }
            }
        }
        
        //collisions of Groups with Groups
        for (int lGroup1 = 0; lGroup1 < m_vGroups.size(); lGroup1++) {
            CGroup Group1 = ((CGroup) (m_vGroups.elementAt(lGroup1)));
            for (int lGroup = lGroup1+1; lGroup < m_vGroups.size(); lGroup++) {
                CGroup Group = ((CGroup) m_vGroups.elementAt(lGroup));
                if (Group.m_bCollisionable) {
                    Group.calculateCollision(Group1);
                }
            }
        }
        
        //Calculate end of collisions
        for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
            CCollision pCollision = ((CCollision) m_vCollisions.elementAt(lCollision));
            if (pCollision.hasToBeFinished()) {
                //System.out.println("Deleting");
                m_vCollisions.removeElementAt(lCollision);
                lCollision--;
                //System.out.println("Deleted");
            }

        }
        
        
    }
    /**
     * Collisions of Points with Polygons are evaluated when this method
     * is called
     */
    /*
    private void calculateCollision(CPoint Point, CPolygon Polygon) {
        System.out.println("Calculating collision of Point "+Point.m_lID+" with Polygon "+Polygon.m_lID);
        System.out.println("AreAlreadyColliding?");
        boolean bAreAlreadyColliding = false;
        int lCollisionIndex = -1;
        for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
            CCollision Collision = ((CCollision) (m_vCollisions.elementAt(lCollision)));
            if (Collision.AreAlreadyColliding(Point, Polygon)) {
                System.out.println("AlreadyColliding");
                bAreAlreadyColliding = true;
                lCollisionIndex = lCollision;
                break;
            }
        }

        System.out.println("IsAnyTriangleColliding?");
        boolean bIsAnyTriangleColliding = false;
        //for (int lTriangle = 0; lTriangle < Polygon.m_lNumOfVertex / 3; lTriangle++) {
        CPointCartesianCoord PointCoord = new CPointCartesianCoord(Point.m_fX, Point.m_fY);
        CPointCartesianCoord PointCoordOld = new CPointCartesianCoord(Point.m_fXOld, Point.m_fYOld);
        CCollisionData CollisionData = new CCollisionData();

        System.out.println("trying isPointInsideTriangle?");
      
        if (calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(PointCoord, PointCoordOld, Polygon, CollisionData)) {
            System.out.println("true from calculateNewCollision(PointCoord, PointCoordOld, Polygon, CollisionData");
            bIsAnyTriangleColliding = true;
            if (false == bAreAlreadyColliding) {

                CCollision Collision = new CCollision(m_pPhysEngRunnableGameCanvas, Point, Polygon, CollisionData);
                m_vCollisions.addElement(Collision);
                //break;//Only one  triangle can be collided at the same time
            }
        }
        else if (isPointInsideTriangle(PointCoord, Polygon)) {
            System.out.println("PointCollidingWithTriangle");
            bIsAnyTriangleColliding = true;
            if (false == bAreAlreadyColliding) {
                System.out.println("trying calculateNewCollision(PointCoord, PointCoordOld, Polygon);");
                CollisionData = calculateNewCollisionWhenPointInside(PointCoord, PointCoordOld, Polygon);

                CCollision Collision = new CCollision(m_pPhysEngRunnableGameCanvas, Point, Polygon, CollisionData);
                m_vCollisions.addElement(Collision);
                //break;//Only one  triangle can be collided at the same time
            }
        }
        
        //}
        if (false == bIsAnyTriangleColliding && true == bAreAlreadyColliding) {
            System.out.println("removing collision at: "+ lCollisionIndex);
            m_vCollisions.removeCollisionAt(lCollisionIndex);
        }
    }
    */
    /**
     * Possible collisions between two polygons are evaluated when this method
     * is called
     */
//    protected void calculateCollision(CPolygon Polygon1, CPolygon Polygon2) {
//        //calculateCollisionPolygon1ToPolygon2(Polygon1, Polygon2);
//        //calculateCollisionPolygon1ToPolygon2(Polygon2, Polygon1);
//
//        if(CMath.testCollisionProximity(Polygon1, Polygon2)){
//
//            boolean bPointOf1Inside2=calculateCollisionPolygon1ToPolygon2OnlyVertexes(Polygon1, Polygon2);
//            boolean bPointOf2Inside1=calculateCollisionPolygon1ToPolygon2OnlyVertexes(Polygon2, Polygon1);
//
//            if (false==bPointOf1Inside2 && false==bPointOf2Inside1){
//                calculateCollisionPolygon1ToPolygon2SidesIntersection(Polygon1, Polygon2);
//            }
//        }
//
//        //to do
//        //todo
///*
//        //change this for a collision test of 2 polygons with their vertexes
//        calculateCollisionPolygon1ToPolygon2OnlyVertex
//        //and in case the test fail, a test of sides of the polygons (with a angle of reation parallel to the line between the 2 centres)
//        boolean bTestResultOfIntersectionOfSides = false;
//        if(false==bIsAnyPointOfATriangleInsideOtherTriangle){
//           bTestResultOfIntersectionOfSides = testCollisionUsingIntersectionOfSidesTest(Polygon1,Polygon2);
//        }
//        //but first, make a simple test by squares to know if the 2 polygons are close enough to collide
// */
//    }
    /**
     * Possible collisions between vertexes of Polygon 1 and
     * sides of Polygon 2 are evaluated when this method
     * is called
     */
//    protected void calculateCollisionPolygon1ToPolygon2(CPolygon Polygon1, CPolygon Polygon2) {
//
//        boolean bIsAnyTriangleColliding = false;
//        for (int lVertexPolygon1 = 0; lVertexPolygon1 < Polygon1.m_lNumOfVertex; lVertexPolygon1++) {
//            int lCollisionIndex = -1;
//            boolean bAreAlreadyColliding = false;
//            for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
//                CCollision Collision = ((CCollision) (m_vCollisions.elementAt(lCollision)));
//                if (Collision.AreAlreadyColliding(Polygon1, lVertexPolygon1, Polygon2)) {
//                    //System.out.println("AlreadyColliding");
//                    bAreAlreadyColliding = true;
//                    lCollisionIndex = lCollision;
//                    break;
//                }
//
//            }
//
//            //float lModRelVertex = Polygon1.m_lModRelVertexes[lVertexPolygon1];
//            //float lXVertexPolygon1 = Polygon1.m_fX + (int) (lModRelVertex * CMath.cosine(Polygon1.m_fDegree + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//            //float lYVertexPolygon1 = Polygon1.m_fY + (int) (lModRelVertex * CMath.sine(Polygon1.m_fDegree + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//            //float lXVertexPolygon1Old = Polygon1.m_fXOld + (int) (lModRelVertex * CMath.cosine(Polygon1.m_fDegreeOld + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//            //float lYVertexPolygon1Old = Polygon1.m_fYOld + (int) (lModRelVertex * CMath.sine(Polygon1.m_fDegreeOld + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//
//            float lXVertexPolygon1 = Polygon1.m_lfXCoordVertexes[lVertexPolygon1];
//            float lYVertexPolygon1 = Polygon1.m_lfYCoordVertexes[lVertexPolygon1];
//            float lXVertexPolygon1Old = Polygon1.m_lfXCoordVertexesOld[lVertexPolygon1];
//            float lYVertexPolygon1Old = Polygon1.m_lfYCoordVertexesOld[lVertexPolygon1];
//
// 
//            CPointCartesianCoord PointCoord = new CPointCartesianCoord(lXVertexPolygon1, lYVertexPolygon1);
//            CPointCartesianCoord PointCoordOld = new CPointCartesianCoord(lXVertexPolygon1Old, lYVertexPolygon1Old);
//
//            //for (int lTriangle = 0; lTriangle < Polygon2.m_lNumOfVertex / 3; lTriangle++) {
//            CCollisionData CollisionData = new CCollisionData();
//            if (Polygon2.calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(PointCoord, PointCoordOld, CollisionData)) {
//                bIsAnyTriangleColliding = true;
//                if (false == bAreAlreadyColliding) {
//
//                    CCollision Collision = new CCollision(m_pPhysEngRunnableGameCanvas, Polygon1, lVertexPolygon1, Polygon2, CollisionData);
//                    m_vCollisions.addElement(Collision);
//                    break;//Only one  triangle can be collided at the same time
//                }
//            } else if (calculateNewCollisionSearchingCuttingPointsOfPolygonSides(lVertexPolygon1, Polygon1, Polygon2, CollisionData))
//            {
//                bIsAnyTriangleColliding = true;
//                if (false == bAreAlreadyColliding) {
//
//                    CCollision Collision = new CCollision(m_pPhysEngRunnableGameCanvas, Polygon1, lVertexPolygon1, Polygon2, CollisionData);
//                    m_vCollisions.addElement(Collision);
//                    break;//Only one  triangle can be collided at the same time
//                }
//            }
//            if (bIsAnyTriangleColliding) {
//                break;
//            }
//
//            //}
//            if (false == bIsAnyTriangleColliding && true == bAreAlreadyColliding) {
//                //System.out.println("removing collision at: "+ lCollisionIndex);
//                m_vCollisions.removeElementAt(lCollisionIndex);
//            }
//        }
//
//
//    }
//    protected boolean calculateCollisionPolygon1ToPolygon2OnlyVertexes(CPolygon Polygon1, CPolygon Polygon2) {
//
//        boolean bIsAnyTriangleColliding = false;
//        boolean bIsAnyTriangleAlreadyColliding = false;
//        for (int lVertexPolygon1 = 0; lVertexPolygon1 < Polygon1.m_lNumOfVertex; lVertexPolygon1++) {
//            int lCollisionIndex = -1;
//            boolean bAreAlreadyColliding = false;
//            for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
//                CCollision Collision = ((CCollision) (m_vCollisions.elementAt(lCollision)));
//                if (Collision.AreAlreadyColliding(Polygon1, lVertexPolygon1, Polygon2)) {
//                    //System.out.println("AlreadyColliding");
//                    bAreAlreadyColliding = true;
//                    bIsAnyTriangleAlreadyColliding =true;
//                    lCollisionIndex = lCollision;
//                    break;
//                }
//
//            }
//
//            /*
//            float lModRelVertex = Polygon1.m_lModRelVertexes[lVertexPolygon1];
//            float lXVertexPolygon1 = Polygon1.m_fX + (int) (lModRelVertex * CMath.cosine(Polygon1.m_fDegree + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//            float lYVertexPolygon1 = Polygon1.m_fY + (int) (lModRelVertex * CMath.sine(Polygon1.m_fDegree + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//            float lXVertexPolygon1Old = Polygon1.m_fXOld + (int) (lModRelVertex * CMath.cosine(Polygon1.m_fDegreeOld + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//            float lYVertexPolygon1Old = Polygon1.m_fYOld + (int) (lModRelVertex * CMath.sine(Polygon1.m_fDegreeOld + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//
//
//
//            CPointCartesianCoord PointCoord = new CPointCartesianCoord(lXVertexPolygon1, lYVertexPolygon1 );
//            CPointCartesianCoord PointCoordOld = new CPointCartesianCoord(lXVertexPolygon1Old, lYVertexPolygon1Old );
//            */
//
//            CPointCartesianCoord PointCoord = new CPointCartesianCoord(Polygon1.m_lfXCoordVertexes[lVertexPolygon1], Polygon1.m_lfYCoordVertexes[lVertexPolygon1]);
//            CPointCartesianCoord PointCoordOld = new CPointCartesianCoord(Polygon1.m_lfXCoordVertexesOld[lVertexPolygon1], Polygon1.m_lfYCoordVertexesOld[lVertexPolygon1]);
//
//            
//            //I think there are problems with the Old states of m_lfXCoordVertexesOld. They should be initialized to m_lfXCoordVertexes
//
//            boolean bIsThisTriangleColliding=false;
//            CCollisionData CollisionData = new CCollisionData();
//            if (Polygon2.calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(PointCoord, PointCoordOld, CollisionData)) {
//
//                m_pPhysEngRunnableGameCanvas.AddDebugLine(PointCoordOld.m_fX, PointCoordOld.m_fY, PointCoord.m_fX, PointCoord.m_fY, 100, 100, 0);
//
//                bIsAnyTriangleColliding = true;
//                bIsThisTriangleColliding = true;
//                if (false == bAreAlreadyColliding) {
//
//                    CCollision Collision = new CCollision(m_pPhysEngRunnableGameCanvas, Polygon1, lVertexPolygon1, Polygon2, CollisionData);
//                    m_vCollisions.addElement(Collision);
//                }
//            }
//            else if(CMath.isInsideTriangle(Polygon2.m_lfXCoordVertexes[0], Polygon2.m_lfYCoordVertexes[0], Polygon2.m_lfXCoordVertexes[1], Polygon2.m_lfYCoordVertexes[1], Polygon2.m_lfXCoordVertexes[2], Polygon2.m_lfYCoordVertexes[2], PointCoord.m_fX, PointCoord.m_fY)){
//                CollisionData = Polygon2.calculateNewCollisionWhenPointInside(PointCoord, PointCoordOld);
//                m_pPhysEngRunnableGameCanvas.AddDebugLine(PointCoordOld.m_fX, PointCoordOld.m_fY, PointCoord.m_fX, PointCoord.m_fY, 0, 100, 100);
//
//                bIsAnyTriangleColliding = true;
//                bIsThisTriangleColliding = true;
//                if (false == bAreAlreadyColliding) {
//
//                    CCollision Collision = new CCollision(m_pPhysEngRunnableGameCanvas, Polygon1, lVertexPolygon1, Polygon2, CollisionData);
//                    m_vCollisions.addElement(Collision);
//                }
//
//            }
//
//            if (false == bIsThisTriangleColliding && true == bAreAlreadyColliding) {
//                //System.out.println("removing collision at: "+ lCollisionIndex);
//                m_vCollisions.removeElementAt(lCollisionIndex);
//            }
//            
//        }
//        if(bIsAnyTriangleAlreadyColliding || bIsAnyTriangleColliding ){
//            return true;
//        }else{
//            return false;
//        }
//
//
//    }
    /**
     * Checks if there is a collision between the vertex lVertexPolygon1 and the Polygon2
     */
//    private boolean calculateNewCollisionSearchingCuttingPointsOfPolygonSides(int lVertexPolygon1, CPolygon Polygon1, CPolygon Polygon2, CCollisionData CollisionData) {
//        int lVertexPolygon1Previous = 0;
//        int lVertexPolygon1Next = 0;
//        if (0 == lVertexPolygon1) {
//            lVertexPolygon1Next = lVertexPolygon1 + 1;
//            lVertexPolygon1Previous = Polygon1.m_lNumOfVertex - 1;
//        } else if (lVertexPolygon1 == Polygon1.m_lNumOfVertex - 1) {
//            lVertexPolygon1Next = 0;
//            lVertexPolygon1Previous = lVertexPolygon1 - 1;
//        } else {
//            lVertexPolygon1Next = lVertexPolygon1 + 1;
//            lVertexPolygon1Previous = lVertexPolygon1 - 1;
//        }
//
//        float fModRelVertex = Polygon1.m_lModRelVertexes[lVertexPolygon1];
//        float fXVertexPolygon1 = Polygon1.m_fX + (fModRelVertex * CMath.cosine(Polygon1.m_fDegree + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//        float fYVertexPolygon1 = Polygon1.m_fY + (fModRelVertex * CMath.sine(Polygon1.m_fDegree + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//
//        float fModRelVertexPrevious = Polygon1.m_lModRelVertexes[lVertexPolygon1Previous];
//        float fXVertexPolygon1Previous = Polygon1.m_fX + (fModRelVertexPrevious * CMath.cosine(Polygon1.m_fDegree + Polygon1.m_lAngRelVertexes[lVertexPolygon1Previous]));
//        float fYVertexPolygon1Previous = Polygon1.m_fY + (fModRelVertexPrevious * CMath.sine(Polygon1.m_fDegree + Polygon1.m_lAngRelVertexes[lVertexPolygon1Previous]));
//
//        float fModRelVertexNext = Polygon1.m_lModRelVertexes[lVertexPolygon1Next];
//        float fXVertexPolygon1Next = Polygon1.m_fX + (fModRelVertexNext * CMath.cosine(Polygon1.m_fDegree + Polygon1.m_lAngRelVertexes[lVertexPolygon1Next]));
//        float fYVertexPolygon1Next = Polygon1.m_fY + (fModRelVertexNext * CMath.sine(Polygon1.m_fDegree + Polygon1.m_lAngRelVertexes[lVertexPolygon1Next]));
//
//        //Los 2 lados adyacentes al vértice del polígono 1 deben cortar cada uno con dos lados del Polygono 2.
//        int lNumberOfCutsOfSidePrevious=0;
//        int lNumberOfCutsOfSideNext=0;
//        float fSideAxFarestCut = 0;
//        float fSideAyFarestCut = 0;
//        float fSideBxFarestCut = 0;
//        float fSideByFarestCut = 0;
//        float fSquareDistanceToFarestCuttingSide=0;
//        for (int lSide = 0; lSide < Polygon2.m_lNumOfVertex; lSide++) {
//            float fModRelVertexA = Polygon2.m_lModRelVertexes[lSide];
//            float fSideAx = Polygon2.m_fX + (fModRelVertexA * CMath.cosine(Polygon2.m_fDegree + Polygon2.m_lAngRelVertexes[lSide]));
//            float fSideAy = Polygon2.m_fY + (fModRelVertexA * CMath.sine(Polygon2.m_fDegree + Polygon2.m_lAngRelVertexes[lSide]));
//
//            float fSideBx;
//            float fSideBy;
//
//            if (lSide == Polygon2.m_lNumOfVertex - 1) {
//                int lModRelVertexB = Polygon2.m_lModRelVertexes[0];
//                fSideBx = Polygon2.m_fX + (lModRelVertexB * CMath.cosine(Polygon2.m_fDegree + Polygon2.m_lAngRelVertexes[0]));
//                fSideBy = Polygon2.m_fY + (lModRelVertexB * CMath.sine(Polygon2.m_fDegree  + Polygon2.m_lAngRelVertexes[0]));
//            } else {
//                int lModRelVertexB = Polygon2.m_lModRelVertexes[lSide + 1];
//                fSideBx = Polygon2.m_fX + (lModRelVertexB * CMath.cosine(Polygon2.m_fDegree + Polygon2.m_lAngRelVertexes[lSide + 1]));
//                fSideBy = Polygon2.m_fY + (lModRelVertexB * CMath.sine(Polygon2.m_fDegree + Polygon2.m_lAngRelVertexes[lSide + 1]));
//            }
//
//
//            CPointCartesianCoord CutPointCoord = new CPointCartesianCoord(0, 0);
//            //if (CMath.calculateCutPointOfTwoLines(fXVertexPolygon1, fYVertexPolygon1, fXVertexPolygon1Previous, fYVertexPolygon1Previous, fSideAx, fSideAy, fSideBx, fSideBy, CutPointCoord)) {
//            if (CMath.isIntersection(fXVertexPolygon1,fYVertexPolygon1,fXVertexPolygon1Previous,fYVertexPolygon1Previous,fSideAx,fSideAy,fSideBx,fSideBy,CutPointCoord)){
//                lNumberOfCutsOfSidePrevious++;
//
//                float lSquareDistanceToCuttingSide = ((CutPointCoord.m_fX - fXVertexPolygon1) * (CutPointCoord.m_fX - fXVertexPolygon1)) + ((CutPointCoord.m_fY - fYVertexPolygon1) * (CutPointCoord.m_fY - fYVertexPolygon1));
//
//                if (fSquareDistanceToFarestCuttingSide < lSquareDistanceToCuttingSide) {
//                    fSquareDistanceToFarestCuttingSide = lSquareDistanceToCuttingSide;
//                    fSideAxFarestCut = fSideAx;
//                    fSideAyFarestCut = fSideAy;
//                    fSideBxFarestCut = fSideBx;
//                    fSideByFarestCut = fSideBy;
//                }
//            }
//            //if (CMath.calculateCutPointOfTwoLines(fXVertexPolygon1, fYVertexPolygon1, fXVertexPolygon1Next, fYVertexPolygon1Next, fSideAx, fSideAy, fSideBx, fSideBy, CutPointCoord)) {
//            if (CMath.isIntersection(fXVertexPolygon1,fYVertexPolygon1,fXVertexPolygon1Next,fYVertexPolygon1Next,fSideAx,fSideAy,fSideBx,fSideBy,CutPointCoord)){
//                lNumberOfCutsOfSideNext++;
//
//                float lSquareDistanceToCuttingSide = ((CutPointCoord.m_fX - fXVertexPolygon1) * (CutPointCoord.m_fX - fXVertexPolygon1)) + ((CutPointCoord.m_fY - fYVertexPolygon1) * (CutPointCoord.m_fY - fYVertexPolygon1));
//
//                if (fSquareDistanceToFarestCuttingSide < lSquareDistanceToCuttingSide) {
//                    fSquareDistanceToFarestCuttingSide = lSquareDistanceToCuttingSide;
//                    fSideAxFarestCut = fSideAx;
//                    fSideAyFarestCut = fSideAy;
//                    fSideBxFarestCut = fSideBx;
//                    fSideByFarestCut = fSideBy;
//                }
//            }
//        }
//        if (1<=lNumberOfCutsOfSideNext && 1<=lNumberOfCutsOfSidePrevious)
//        {
//            float fXVertexPolygon1Old = Polygon1.m_fXOld + (fModRelVertex * CMath.cosine((int)Polygon1.m_fDegreeOld + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//            float fYVertexPolygon1Old = Polygon1.m_fYOld + (fModRelVertex * CMath.sine((int)Polygon1.m_fDegreeOld + Polygon1.m_lAngRelVertexes[lVertexPolygon1]));
//
//            CPointCartesianCoord PointCoord=new CPointCartesianCoord(fXVertexPolygon1,fYVertexPolygon1);
//            CPointCartesianCoord PointCoordOld=new CPointCartesianCoord(fXVertexPolygon1Old,fYVertexPolygon1Old);
//
//            CCollisionData CollisionDataNew = Polygon2.calculateCollisionWithSideOfPolygon(fSideAxFarestCut, fSideAyFarestCut, fSideBxFarestCut, fSideByFarestCut, PointCoord, PointCoordOld);
//            CollisionData.m_fCollPositionAngle=CollisionDataNew.m_fCollPositionAngle;
//            CollisionData.m_fCollPositionModRel=CollisionDataNew.m_fCollPositionModRel;
//            CollisionData.m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon=CollisionDataNew.m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon;
//            CollisionData.m_fAngleOfRepulsionOfPolygon=CollisionDataNew.m_fAngleOfRepulsionOfPolygon;
//
//            return true;
//        }
//        return false;
//    }

    public void addCollision(CCollision Collision){
        m_vCollisions.addElement(Collision);
    }
//    public void removeCollisionAt(int index){
//        m_vCollisions.removeElementAt(index);
//    }
    public void removeCollision(CCollision Collision){
        m_vCollisions.removeElement((Object)Collision);
    }

    public CCollision arePointAndPolygonAlreadyColliding(CPoint Point, CPolygon Polygon){

        
        for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
            CCollision Collision = ((CCollision)m_vCollisions.elementAt(lCollision));
            if (Collision.AreAlreadyColliding(Point,Polygon)) {
                //System.out.println("AlreadyColliding");
                return Collision;
            }
        }
        return null;
    }
    public CObject GetCamTarget(){
//        for (int lPolygon = 0; lPolygon < m_vPolygons.size(); lPolygon++) {
//            CPolygon Polygon = ((CPolygon) (m_vPolygons.elementAt(lPolygon)));
//            if (Polygon.m_bActivated) {
//                if (true == Polygon.m_bHandControlled) {
//                    pPointCartCoord.m_fX=Polygon.getX();
//                    pPointCartCoord.m_fY=Polygon.getY();                    
//                }
//            }
//        }
        return m_pCamObj;       
    }
    public void SetCamObjective(CObject pObject){
        m_pCamObj=pObject;
    }
    
    

    /**
     * Is used when you already know that there is a collision between PointCoord and
     * a triangle of a Polygon to get the data associated to the collision
     * */
    /*
    private CCollisionData calculateNewCollisionWhenPointInside(CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CPolygon Polygon) {

        System.out.println("inside private CCollisionData calculateNewCollision(CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CPolygon Polygon) {");

        float[] fXVertexes = new float[3];
        float[] fYVertexes = new float[3];
        getVertexesOfTriangle(Polygon, fXVertexes, fYVertexes);

        //Con quién colisiona?? Qué linea del poligono?
        float fLinePointAx = PointCoordOld.m_fX;
        float fLinePointAy = PointCoordOld.m_fY;
        float fLinePointBx = PointCoord.m_fX;
        float fLinePointBy = PointCoord.m_fY;

        //This means that the point is not colliding to the polygon,
        //so, it's the polygon that is colliding to the point

        //Utilizar aquí el método calculateCutPointOfTwoLines en vez de lo que hay a continuación

        float fAngleAbsFromPolygonToPoint = CMath.GetVectorAngle(Polygon.m_fXOld, Polygon.m_fYOld, fLinePointBx, fLinePointBy);

        float fAngleRelFromPolygonToPoint = fAngleAbsFromPolygonToPoint - Polygon.m_fDegreeOld;

        int lSide = getTheSideAssociatedToThisAngle(Polygon, fAngleRelFromPolygonToPoint);

        System.out.println("before calculateCollisionWithSideOfPolygon(lSide, Polygon, PointCoord, PointCoordOld);");
        return calculateCollisionWithSideOfPolygon(lSide, Polygon, PointCoord, PointCoordOld);

    }
    */
    /**
     * Possible collisions between a Point and a Polygon are evaluated when this method
     * is called in the following way: The cut points between the lines formed by the Polygon sides, and the
     * line formed by the PointOld to PointNew points are evaluated.
     *
     * This method will fail finding a collision if for example, PointCoord and PointCoordOld are
     * the same point (the point has not been moved). So, the methods isPointInsideTriangle and
     * calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(PointCoord, PointCoordOld, Polygon) are needed to
     * complement this method. Those methods will find the collisions that this method cant.
     *
     */
    /*
    private boolean calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CPolygon Polygon, CCollisionData CollisionData) {

        //System.out.println("Colisión");

        //Con quién colisiona?? Qué linea del poligono?
        float fLinePointAx = PointCoordOld.m_fX;
        float fLinePointAy = PointCoordOld.m_fY;
        float fLinePointBx = PointCoord.m_fX;
        float fLinePointBy = PointCoord.m_fY;

        float fSquarelDistanceFromAPointToCutPoint = -1;
        //int lFirstSideCutting=0;
        float fSideAxNearestCut = 0;
        float fSideAyNearestCut = 0;
        float fSideBxNearestCut = 0;
        float fSideByNearestCut = 0;

        boolean bIsThereAnyCut = false;
        for (int lSide = 0; lSide < Polygon.m_lNumOfVertex; lSide++) {
            float fModRelVertexA = Polygon.m_lModRelVertexes[lSide];
            float fSideAx = Polygon.m_fX + (fModRelVertexA * CMath.cosine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[lSide])));
            float fSideAy = Polygon.m_fY + (fModRelVertexA * CMath.sine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[lSide])));

            float fSideBx;
            float fSideBy;

            if (lSide == Polygon.m_lNumOfVertex - 1) {
                int lModRelVertexB = Polygon.m_lModRelVertexes[0];
                fSideBx = Polygon.m_fX + (lModRelVertexB * CMath.cosine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[0])));
                fSideBy = Polygon.m_fY + (lModRelVertexB * CMath.sine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[0])));
            } else {
                int lModRelVertexB = Polygon.m_lModRelVertexes[lSide + 1];
                fSideBx = Polygon.m_fX + (lModRelVertexB * CMath.cosine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[lSide + 1])));
                fSideBy = Polygon.m_fY + (lModRelVertexB * CMath.sine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[lSide + 1])));
            }


            CPointCartesianCoord CutPointCoord = new CPointCartesianCoord(0, 0);
            if (calculateCutPointOfTwoLines(fLinePointAx, fLinePointAy, fLinePointBx, fLinePointBy, fSideAx, fSideAy, fSideBx, fSideBy, CutPointCoord)) {
                bIsThereAnyCut = true;
                float fDistanceFromAPointToThisSideCutPoint = (CutPointCoord.m_fX - fLinePointAx) * (CutPointCoord.m_fX - fLinePointAx) + (CutPointCoord.m_fY - fLinePointAy) * (CutPointCoord.m_fY - fLinePointAy);
                if (fSquarelDistanceFromAPointToCutPoint == -1) {
                    fSquarelDistanceFromAPointToCutPoint = fDistanceFromAPointToThisSideCutPoint;
                    PointCoord = CutPointCoord;
                    fSideAxNearestCut = fSideAx;
                    fSideAyNearestCut = fSideAy;
                    fSideBxNearestCut = fSideBx;
                    fSideByNearestCut = fSideBy;

                } else if (fDistanceFromAPointToThisSideCutPoint < fSquarelDistanceFromAPointToCutPoint) {
                    fSquarelDistanceFromAPointToCutPoint = fDistanceFromAPointToThisSideCutPoint;
                    PointCoord = CutPointCoord;
                    fSideAxNearestCut = fSideAx;
                    fSideAyNearestCut = fSideAy;
                    fSideBxNearestCut = fSideBx;
                    fSideByNearestCut = fSideBy;
                }
            }
        }

        CCollisionData CollisionDataNew = calculateCollisionWithSideOfPolygon(fSideAxNearestCut, fSideAyNearestCut, fSideBxNearestCut, fSideByNearestCut, Polygon, PointCoord, PointCoordOld);
        CollisionData.m_fCollPositionAngle = CollisionDataNew.m_fCollPositionAngle;
        CollisionData.m_fCollPositionModRel = CollisionDataNew.m_fCollPositionModRel;
        CollisionData.m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon = CollisionDataNew.m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon;
        CollisionData.m_fAngleOfRepulsionOfPolygon = CollisionDataNew.m_fAngleOfRepulsionOfPolygon;

        return bIsThereAnyCut;

    }
    */
    /*
    private boolean calculateCutPointOfTwoLines(float fLine1Ax, float fLine1Ay, float fLine1Bx, float fLine1By, float fLine2Ax, float fLine2Ay, float fLine2Bx, float fLine2By, CPointCartesianCoord PointCoord) {
        float fMoreLeftLine1X = 0;
        float fMoreRightLine1X = 0;
        float fMoreLeftLine1Y = 0;
        float fMoreRightLine1Y = 0;

        if (fLine1Ax < fLine1Bx) {
            fMoreLeftLine1X = fLine1Ax;
            fMoreLeftLine1Y = fLine1Ay;
            fMoreRightLine1X = fLine1Bx;
            fMoreRightLine1Y = fLine1By;

        } else {
            fMoreLeftLine1X = fLine1Bx;
            fMoreRightLine1X = fLine1Ax;
            fMoreLeftLine1Y = fLine1By;
            fMoreRightLine1Y = fLine1Ay;
        }

        float fMoreLeftLine2X = 0;
        float fMoreRightLine2X = 0;
        float fMoreLeftLine2Y = 0;
        float fMoreRightLine2Y = 0;

        if (fLine2Ax < fLine2Bx) {
            fMoreLeftLine2X = fLine2Ax;
            fMoreRightLine2X = fLine2Bx;
            fMoreLeftLine2Y = fLine2Ay;
            fMoreRightLine2Y = fLine2By;
        } else {
            fMoreLeftLine2X = fLine2Bx;
            fMoreRightLine2X = fLine2Ax;
            fMoreLeftLine2Y = fLine2By;
            fMoreRightLine2Y = fLine2Ay;
        }

        float fLessLeftOfLeftExtremesX = 0;
        if (fMoreLeftLine1X > fMoreLeftLine2X) {
            fLessLeftOfLeftExtremesX = fMoreLeftLine1X;
        } else {
            fLessLeftOfLeftExtremesX = fMoreLeftLine2X;
        }

        float fLessRightOfRightExtremesX = 0;
        if (fMoreRightLine1X < fMoreRightLine2X) {
            fLessRightOfRightExtremesX = fMoreRightLine1X;
        } else {
            fLessRightOfRightExtremesX = fMoreRightLine2X;
        }

        if (fLessRightOfRightExtremesX < fLessLeftOfLeftExtremesX) {
            return false;
        }

        if (fLine1Ax == fLine1Bx) {
            float fXCutPoint = fLine1Ax;
            float lfPendiente2 = ((float) (fLine2By - fLine2Ay) / (float) (fLine2Bx - fLine2Ax));
            PointCoord.m_fX = (int) fXCutPoint;
            PointCoord.m_fY = (int) (fLine2Ay + lfPendiente2 * (fXCutPoint - fLine2Ax));
            if ((fLine1Ay<=PointCoord.m_fY && PointCoord.m_fY<=fLine1By) ||
                (fLine1Ay>=PointCoord.m_fY && PointCoord.m_fY>=fLine1By))
            {
                return true;
            }
            return false;
        }
        if (fLine2Ax == fLine2Bx) {
            float fXCutPoint = fLine2Ax;
            float lfPendiente1 = ((float) (fLine1By - fLine1Ay) / (float) (fLine1Bx - fLine1Ax));
            PointCoord.m_fX = (int) fXCutPoint;
            PointCoord.m_fY = (int) (fLine1Ay + lfPendiente1 * (fXCutPoint - fLine1Ax));
            if ((fLine2Ay<=PointCoord.m_fY && PointCoord.m_fY<=fLine2By) ||
                (fLine2Ay>=PointCoord.m_fY && PointCoord.m_fY>=fLine2By))
            {
                return true;
            }
            return false;
        }

        float lfPendiente1 = ((float) (fLine1By - fLine1Ay) / (float) (fLine1Bx - fLine1Ax));
        float lfPendiente2 = ((float) (fLine2By - fLine2Ay) / (float) (fLine2Bx - fLine2Ax));

        float fLine1LessLeftY = (fLine1Ay + lfPendiente1 * (fLessLeftOfLeftExtremesX - fLine1Ax));
        float fLine2LessLeftY = (fLine2Ay + lfPendiente2 * (fLessLeftOfLeftExtremesX - fLine2Ax));
        float fLine1LessRightY =(fLine1Ay + lfPendiente1 * (fLessRightOfRightExtremesX - fLine1Ax));
        float fLine2LessRightY =(fLine2Ay + lfPendiente2 * (fLessRightOfRightExtremesX - fLine2Ax));

        if (fLine1LessLeftY > fLine2LessLeftY && fLine1LessRightY > fLine2LessRightY) {
            //No hay colisión
            return false;
        } else if (fLine1LessLeftY < fLine2LessLeftY && fLine1LessRightY < fLine2LessRightY) {
            //No hay colisión
            return false;
        }

        float lfCollisionXNumerator = ((float) fLine1Ay - ((float) fLine1Ax * lfPendiente1)) - ((float) fLine2Ay - ((float) fLine2Ax * lfPendiente2));
        float lfCollisionXDenominator = lfPendiente2 - lfPendiente1;

        float lfCollisionX = lfCollisionXNumerator / lfCollisionXDenominator;

        PointCoord.m_fX = lfCollisionX;
        PointCoord.m_fY = (fLine1Ay + lfPendiente1 * (lfCollisionX - fLine1Ax));

        return true;
    }
 */
    /**
 * The collision data of a collision between a Side of a Polygon and the line formed by the Points PointCoord and PointCoordOld is obtained.
 *
 */
    /*
    private CCollisionData calculateCollisionWithSideOfPolygon(float fSideAx, float fSideAy, float fSideBx, float fSideBy, CPolygon Polygon, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld) {

        System.out.println("inside private CCollisionData calculateCollisionWithSideOfPolygon(float fSideAx, float fSideAy, float fSideBx, float fSideBy, CPolygon Polygon, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld) {");
        float fLinePolygonAx = fSideAx;
        float fLinePolygonAy = fSideAy;

        float fLinePolygonBx = fSideBx;
        float fLinePolygonBy = fSideBy;

        float fLineAngle = CMath.GetVectorAngle(fLinePolygonAx, fLinePolygonAy, fLinePolygonBx, fLinePolygonBy);

        float fAngleOfRepulsion1 = fLineAngle + 270;
        if (fAngleOfRepulsion1 > 360) {
            fAngleOfRepulsion1 = fAngleOfRepulsion1 - 360;
        }
        //System.out.println("lAngleOfRepulsion1: "+lAngleOfRepulsion1);
        float fAngleOfRepulsion2 = fLineAngle + 90;
        if (fAngleOfRepulsion2 > 360) {
            fAngleOfRepulsion2 = fAngleOfRepulsion2 - 360;
        }

        //Relative coordinates of the collision to gravity centre of the polygon:
        //Module:
        float CollAngle = CMath.GetVectorAngle(Polygon.m_fX, Polygon.m_fY, PointCoord.m_fX, PointCoord.m_fY);
        float CollXRel = PointCoord.m_fX - Polygon.m_fX;
        float CollYRel = PointCoord.m_fY - Polygon.m_fY;
        float CollModRel = (int) (((float) CollXRel) * CMath.cosine(CollAngle) + ((float) CollYRel) * CMath.sine(CollAngle));

        System.out.println("New Collision");
        //CCollision Collision=new CCollision(this, Point,lAngleOfRepulsion1,Polygon,lAngleOfRepulsion2,CollModRel,CollAngle);
        //atención, si ya están colisionando esos dos objetos, ya no debería añadir nuevos elemmentos a la lista
        //Necesito un metodo AreAlreadyColliding para ponerlo al principio del método
        CCollisionData CollisionData = new CCollisionData(fAngleOfRepulsion1, fAngleOfRepulsion2, CollModRel, CollAngle);
        return CollisionData;
    }
 */
    /**
 * The collision data of a collision between a Side of a Polygon and the line formed by the Points PointCoord and PointCoordOld is obtained.
 * To do:There is other method with the same function. So, one of this methos is not needed.
 */
    /*
    private CCollisionData calculateCollisionWithSideOfPolygon(int lSide, CPolygon Polygon, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld) {
        System.out.println("calculateCollisionWithSideOfPolygon(int lSide, int lTriangle, CPolygon Polygon, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld) {");
        float[] fXVertexes = new float[3];
        float[] fYVertexes = new float[3];
        getVertexesOfTriangle(Polygon, fXVertexes, fYVertexes);

        //Saco la linea que le corresponde a cada vértice
        int lVert = lSide;
        int lNumOfVertexes = 3;
        int lVertSiguiente = lVert + 1;
        if (lVert >= lNumOfVertexes - 1) {
            lVertSiguiente = 0;
        }

        System.out.println("1");
        System.out.println("lVert="+lVert);
        System.out.println("lVertSiguiente="+lVertSiguiente);
        float fLinePolygonAx = fXVertexes[lVert];
        float fLinePolygonAy = fYVertexes[lVert];
            System.out.println("2");
        float fLinePolygonBx = fXVertexes[lVertSiguiente];
        float fLinePolygonBy = fYVertexes[lVertSiguiente];
        System.out.println("3");
        
        float fLineAngle = CMath.GetVectorAngle(fLinePolygonAx, fLinePolygonAy, fLinePolygonBx, fLinePolygonBy);
        System.out.println("4");
        float fAngleOfRepulsion1 = fLineAngle + 270;
        if (fAngleOfRepulsion1 > 360) {
            fAngleOfRepulsion1 = fAngleOfRepulsion1 - 360;
        }
        //System.out.println("lAngleOfRepulsion1: "+lAngleOfRepulsion1);
        float fAngleOfRepulsion2 = fLineAngle + 90;
        if (fAngleOfRepulsion2 > 360) {
            fAngleOfRepulsion2 = fAngleOfRepulsion2 - 360;
        }
        //Relative coordinates of the collision to gravity centre of the polygon:
        //Module:
        float CollAngle = CMath.GetVectorAngle(Polygon.m_fX, Polygon.m_fY, PointCoord.m_fX, PointCoord.m_fY);
        float CollXRel = PointCoord.m_fX - Polygon.m_fX;
        float CollYRel = PointCoord.m_fY - Polygon.m_fY;
        float CollModRel = (int) (((float) CollXRel) * CMath.cosine(CollAngle) + ((float) CollYRel) * CMath.sine(CollAngle));


        System.out.println("New Collision. Total number of collisions = " + m_vCollisions.size());
        //CCollision Collision=new CCollision(this, Point,lAngleOfRepulsion1,Polygon,lAngleOfRepulsion2,CollModRel,CollAngle);
        //atención, si ya están colisionando esos dos objetos, ya no debería añadir nuevos elemmentos a la lista
        //Necesito un metodo AreAlreadyColliding para ponerlo al principio del método
        CCollisionData CollisionData = new CCollisionData(fAngleOfRepulsion1, fAngleOfRepulsion2, CollModRel, CollAngle);
        return CollisionData;
    }
    */
    /*
    private void getVertexesOfTriangle(CPolygon Polygon, float[] fXVertexes, float[] fYVertexes) {

        float fX1Rel = ((float) (Polygon.m_lModRelVertexes[0]) * CMath.cosine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[0])));
        float fY1Rel = ((float) (Polygon.m_lModRelVertexes[0]) * CMath.sine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[0])));

        float fX2Rel = ((float) (Polygon.m_lModRelVertexes[1]) * CMath.cosine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[1])));
        float fY2Rel = ((float) (Polygon.m_lModRelVertexes[1]) * CMath.sine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[1])));

        float fX3Rel = ((float) (Polygon.m_lModRelVertexes[2]) * CMath.cosine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[2])));
        float fY3Rel = ((float) (Polygon.m_lModRelVertexes[2]) * CMath.sine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[2])));

        //Points of the triangle in absolute coordinates
        float fX1Abs = Polygon.m_fX + fX1Rel;
        float fY1Abs = Polygon.m_fY + fY1Rel;
        float fX2Abs = Polygon.m_fX + fX2Rel;
        float fY2Abs = Polygon.m_fY + fY2Rel;
        float fX3Abs = Polygon.m_fX + fX3Rel;
        float fY3Abs = Polygon.m_fY + fY3Rel;

        //int []fXVertexes=new int[3];
        fXVertexes[0] = fX1Abs;
        fXVertexes[1] = fX2Abs;
        fXVertexes[2] = fX3Abs;

        //int []fYVertexes=new int[3];
        fYVertexes[0] = fY1Abs;
        fYVertexes[1] = fY2Abs;
        fYVertexes[2] = fY3Abs;
    }
    */
    /*
    private int getTheSideAssociatedToThisAngle(CPolygon Polygon, float fAngle) {

        int lIndexOfVertexes = -1;
        float fAngleMin = 360;

        for (int lVertex = 0; lVertex < Polygon.m_lNumOfVertex; lVertex++) {

            float fAngleWidthVertexToAngle = CMath.getAngleSubstraction(Polygon.m_lAngRelVertexes[lVertex],fAngle);

            if (fAngleWidthVertexToAngle < fAngleMin) {
                fAngleMin = fAngleWidthVertexToAngle;
                lIndexOfVertexes = lVertex - 1;
            }
        }
        if (lIndexOfVertexes < 0) {
            lIndexOfVertexes = Polygon.m_lNumOfVertex - 1;
        }
        return lIndexOfVertexes;
    }
    */


//    boolean calculateCollisionPolygon1ToPolygon2SidesIntersection(CPolygon Polygon1, CPolygon Polygon2){
//
//        CPointCartesianCoord pPointCoor=new CPointCartesianCoord();
//        boolean bIsIntersection=false;
//        for (int lVertex1 = 0; lVertex1 < Polygon1.m_lNumOfVertex-1; lVertex1++) {
//            for (int lVertex2 = 0; lVertex2 < Polygon2.m_lNumOfVertex-1; lVertex2++) {
//               // System.out.println("Testing intersection Polygon 1 vertex:"+lVertex1+"Polygon 2 vertex:"+lVertex2);
//                if ( CMath.isIntersection(Polygon1.m_lfXCoordVertexes[lVertex1],Polygon1.m_lfYCoordVertexes[lVertex1],Polygon1.m_lfXCoordVertexes[lVertex1+1],Polygon1.m_lfYCoordVertexes[lVertex1+1],Polygon2.m_lfXCoordVertexes[lVertex2],Polygon2.m_lfYCoordVertexes[lVertex2],Polygon2.m_lfXCoordVertexes[lVertex2+1],Polygon2.m_lfYCoordVertexes[lVertex2+1],
//                        pPointCoor)){
//                    m_pPhysEngRunnableGameCanvas.AddDebugLine(Polygon1.m_lfXCoordVertexes[lVertex1],Polygon1.m_lfYCoordVertexes[lVertex1],Polygon1.m_lfXCoordVertexes[lVertex1+1],Polygon1.m_lfYCoordVertexes[lVertex1+1], 100, 100, 0);
//                    m_pPhysEngRunnableGameCanvas.AddDebugLine(Polygon2.m_lfXCoordVertexes[lVertex1],Polygon2.m_lfYCoordVertexes[lVertex1],Polygon2.m_lfXCoordVertexes[lVertex1+1],Polygon2.m_lfYCoordVertexes[lVertex1+1], 100, 100, 0);
//                    bIsIntersection=true;
//                    break;
//                }
//            }
//        }
//
//        boolean bAreAlreadyColliding =false;
//        int lCollisionIndex=0;
//        for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
//            CCollision Collision = ((CCollision) (m_vCollisions.elementAt(lCollision)));
//            if (Collision.AreAlreadyColliding(Polygon1, Polygon2)) {
//                //System.out.println("AlreadyColliding");
//                bAreAlreadyColliding = true;
//                lCollisionIndex=lCollision;
//                break;
//            }
//        }
//
//        if(bIsIntersection){
//            if(false==bAreAlreadyColliding ){
//               //("Creating collision");
//
//                float fAngleOfRepulsion1 = CMath.GetVectorAngle(Polygon2.m_fX, Polygon2.m_fY, Polygon1.m_fX, Polygon1.m_fY);
//                if (fAngleOfRepulsion1 > 360) {
//                    fAngleOfRepulsion1 = fAngleOfRepulsion1 - 360;
//                }
//                //System.out.println("lAngleOfRepulsion1: "+lAngleOfRepulsion1);
//                float fAngleOfRepulsion2 = fAngleOfRepulsion1 + 180;
//                if (fAngleOfRepulsion2 > 360) {
//                    fAngleOfRepulsion2 = fAngleOfRepulsion2 - 360;
//                }
//
//                //Relative coordinates of the collision to gravity centre of the polygon1:
//                //Module:
//                float CollAngle1 = CMath.GetVectorAngle(Polygon1.m_fX, Polygon1.m_fY, pPointCoor.m_fX, pPointCoor.m_fY);
//                float CollXRel1 = pPointCoor.m_fX - Polygon1.m_fX;
//                float CollYRel1 = pPointCoor.m_fY - Polygon1.m_fY;
//                float CollModRel1 = (int) (((float) CollXRel1) * CMath.cosine(CollAngle1) + ((float) CollYRel1) * CMath.sine(CollAngle1));
//
//               //Relative coordinates of the collision to gravity centre of the polygon1:
//                //Module:
//                float CollAngle2 = CMath.GetVectorAngle(Polygon2.m_fX, Polygon2.m_fY, pPointCoor.m_fX, pPointCoor.m_fY);
//                float CollXRel2 = pPointCoor.m_fX - Polygon2.m_fX;
//                float CollYRel2 = pPointCoor.m_fY - Polygon2.m_fY;
//                float CollModRel2 = (int) (((float) CollXRel2) * CMath.cosine(CollAngle2) + ((float) CollYRel2) * CMath.sine(CollAngle2));
//
//                CCollisionData CollisionDataPolygon1 = new CCollisionData(fAngleOfRepulsion2, fAngleOfRepulsion1, CollModRel1, CollAngle1);
//                CCollisionData CollisionDataPolygon2 = new CCollisionData(fAngleOfRepulsion1, fAngleOfRepulsion2, CollModRel2, CollAngle2);
//
//                CCollision Collision = new CCollision(m_pPhysEngRunnableGameCanvas, Polygon1,Polygon2,CollisionDataPolygon1,CollisionDataPolygon2);
//                m_vCollisions.addElement(Collision);
//                //System.out.println("Collision added");
//            }
//            return true;
//        }
//        else{
//            //System.out.println("removing collision");
//            if(true==bAreAlreadyColliding ){
//                m_vCollisions.removeElementAt(lCollisionIndex);
//            }
//            return false;
//        }
//    }
    public void addPolygon(CPolygon pPolygon){
        m_vPolygons.addElement(pPolygon);
    }
    public void addGroup(CGroup pGroup){
        m_vGroups.addElement(pGroup);
    }
    public void setLimits(float lfLeftLimit,float lfRightLimit,float lfTopLimit,float lfBottomLimit){
        m_lfLeftLimit=lfLeftLimit;
        m_lfRightLimit=lfRightLimit;
        m_lfTopLimit=lfTopLimit;
        m_lfBottomLimit=lfBottomLimit;

    }
    public CCollision AreAlreadyColliding(CPolygon Polygon1,CPolygon Polygon2){
        CCollision Collision = null;
        for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
            Collision = ((CCollision) (m_vCollisions.elementAt(lCollision)));
            if (Collision.AreAlreadyColliding(Polygon1, Polygon2)) {
                //System.out.println("AlreadyColliding");
                //bAreAlreadyColliding = true;
                //lCollisionIndex=lCollision;
                return Collision;
            }
        }
        return null;
    }
    public CCollision AreAlreadyCollidingWithoutVertexes(CPolygon Polygon1,CPolygon Polygon2){
        CCollision Collision = null;
        for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
            Collision = ((CCollision) (m_vCollisions.elementAt(lCollision)));
            if (Collision.AreAlreadyCollidingWithoutVertexes(Polygon1, Polygon2)) {
                //System.out.println("AlreadyColliding");
                //bAreAlreadyColliding = true;
                //lCollisionIndex=lCollision;
                return Collision;             
            }
        }
        return null;
    }
    public CCollision AreAlreadyColliding(CPolygon Polygon1,int lVertexPolygon1,CPolygon Polygon2){
        CCollision Collision = null;
        //int lCollisionIndex = -1;
        //boolean bAreAlreadyColliding = false;
        for (int lCollision = 0; lCollision < m_vCollisions.size(); lCollision++) {
            Collision = ((CCollision) (m_vCollisions.elementAt(lCollision)));
            if (Collision.AreAlreadyColliding(Polygon1, lVertexPolygon1, Polygon2)) {
                //System.out.println("AlreadyColliding");
                //bAreAlreadyColliding = true;
                //bIsAnyTriangleAlreadyColliding =true;
                //lCollisionIndex = lCollision;
                //break;
                return Collision;
            }
        }
        return null;
    }
    public void run(){
        long lTiempoInicioTest=System.currentTimeMillis();


        //Acceleration control
        controlObjects();
        long lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoControlObjects=lTiempoFinTest-lTiempoInicioTest;


        //Cam control
        //System.out.println("controlCamera();");
        lTiempoInicioTest=System.currentTimeMillis();
        m_pCamera.controlCamera();
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoControlCamara=lTiempoFinTest-lTiempoInicioTest;
        //System.out.println("m_Scene.velocityAndPositionIntegration();");
        lTiempoInicioTest=System.currentTimeMillis();
        velocityAndPositionIntegration();
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoIntegration=lTiempoFinTest-lTiempoInicioTest;
        //Collisions
        //with other objects
        //System.out.println("m_Scene.calculateCollisionsBetweenObjects();");
        int lNumRep=1;
        for (int lRep=0;lRep<lNumRep;lRep++)
        {
            lTiempoInicioTest=System.currentTimeMillis();
            calculateCollisionsBetweenObjects();
            lTiempoFinTest=System.currentTimeMillis();
            m_lTiempoCollObjects=lTiempoFinTest-lTiempoInicioTest;
            //with Limits
            //(with cushion)
            //long collision acceleration
            //System.out.println("m_Scene.calculateCollisionsWithLimitsForObjects();");
            lTiempoInicioTest=System.currentTimeMillis();
            calculateCollisionsWithLimitsForObjects();
            lTiempoFinTest=System.currentTimeMillis();
            m_lTiempoCollLimits=lTiempoFinTest-lTiempoInicioTest;
            //System.out.println("m_Scene.sumCollisionAccelerations();");
            lTiempoInicioTest=System.currentTimeMillis();
            sumCollisionAccelerations();
            lTiempoFinTest=System.currentTimeMillis();
            m_lTiempoCollAcc=lTiempoFinTest-lTiempoInicioTest;
            //System.out.println("m_Scene.saveOldStates();");
            lTiempoInicioTest=System.currentTimeMillis();
            saveOldStates();
            lTiempoFinTest=System.currentTimeMillis();
            m_lTiempoOldStates=lTiempoFinTest-lTiempoInicioTest;
        }
        
        //System.out.println("drawBackground();");
        lTiempoInicioTest=System.currentTimeMillis();
        drawBackground();
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoDrawBack=lTiempoFinTest-lTiempoInicioTest;
        //System.out.println("m_Scene.drawObjects();");
        lTiempoInicioTest=System.currentTimeMillis();
        drawObjects();
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoDrawObjects=lTiempoFinTest-lTiempoInicioTest;
        //System.out.println("drawForeground();");
        lTiempoInicioTest=System.currentTimeMillis();
        drawForeground();
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoForeground=lTiempoFinTest-lTiempoInicioTest;
        //System.out.println("flushGraphics();");
        lTiempoInicioTest=System.currentTimeMillis();
        drawDebug();
        lTiempoFinTest=System.currentTimeMillis();
        m_lTiempoDrawDebug=lTiempoFinTest-lTiempoInicioTest;

        long lDrawTimeDebug=m_lTiempoDrawBack+m_lTiempoDrawObjects+m_lTiempoForeground+m_lTiempoDrawDebug;
        System.out.println("Draw time:");
        System.out.println(lDrawTimeDebug);
        long lSceneTimeDebug=m_lTiempoDrawBack+m_lTiempoDrawObjects+m_lTiempoForeground+m_lTiempoDrawDebug+
                m_lTiempoOldStates+m_lTiempoCollAcc+m_lTiempoCollLimits+m_lTiempoCollObjects+m_lTiempoIntegration+m_lTiempoControlCamara+
                m_lTiempoControlObjects;
//        System.out.println("Scene time:");
//        System.out.println(lSceneTimeDebug);
        long lSceneTimeWithoutDrawDebug=lSceneTimeDebug-lDrawTimeDebug;
        System.out.println("Scene without draw time:");
        System.out.println(lSceneTimeWithoutDrawDebug);
        
        

    }
    public CCamera getCamera(){
        return m_pCamera;
    }
    private void drawBackground() {

        //Background
        m_pPhysEngRunnableGameCanvas.m_g.setColor(0, 0, 0);
        m_pPhysEngRunnableGameCanvas.m_g.fillRect(0, 0, m_pPhysEngRunnableGameCanvas.m_g.getClipWidth(), m_pPhysEngRunnableGameCanvas.m_g.getClipHeight());

        m_pPhysEngRunnableGameCanvas.m_g.setColor(0, 0, 0);
        m_pPhysEngRunnableGameCanvas.m_g.fillRect(5, 5, m_pPhysEngRunnableGameCanvas.getWidth() - 10, m_pPhysEngRunnableGameCanvas.getHeight() - 10);

        m_pPhysEngRunnableGameCanvas.m_g.setColor(255, 255, 0);
        m_pPhysEngRunnableGameCanvas.m_g.fillArc(160, 250, 50, 50, 0, 45);

        float fSpaceBetweenLines=1000;
        float fMapSizeX=m_lfRightLimit-m_lfLeftLimit;
        float fMapSizeY=m_lfTopLimit-m_lfBottomLimit;

        float fDistPointXToDraw=m_lfLeftLimit;
        m_pPhysEngRunnableGameCanvas.m_myGraphics.setColor(100, 200, 100);
        while(fDistPointXToDraw<fMapSizeX)
        {
            float fDistPointYToDraw=m_lfBottomLimit;
            while(fDistPointYToDraw<fMapSizeY)
            {
                //m_g.drawRect(GetXCoordinateInScreen(fDistPointXToDraw), GetYCoordinateInScreen(fDistPointYToDraw), 1, 1);
                //m_g.drawRect((int)fDistPointXToDraw, (int)fDistPointYToDraw, 1, 1);
                getCamera().fillRectInBackground(fDistPointXToDraw,fDistPointYToDraw,100,100);
                //m_pPhysEngRunnableGameCanvas.m_myGraphics.fillRect((int)fDistPointXToDraw, (int)fDistPointYToDraw, 1, 1);
                fDistPointYToDraw=fDistPointYToDraw+fSpaceBetweenLines;
            }
            fDistPointXToDraw=fDistPointXToDraw+fSpaceBetweenLines;
        }
    }
private void drawForeground() {
        //Todo
        //Draw debuging info, minimap, health bar, score, times

        //Scores
    
        
        int y = 0;
        if (CONSTANTS.SHOW_SCORE){
            m_pPhysEngRunnableGameCanvas.m_g.setColor(0, 50, 150);
            m_pPhysEngRunnableGameCanvas.m_g.drawString ("SCORE:", 0, 10, Graphics.LEFT|Graphics.TOP);
            m_pPhysEngRunnableGameCanvas.m_g.drawString (""+CGameInfo.getScore()+"", 0, 20, Graphics.LEFT|Graphics.TOP);        
        }
        
        m_pPhysEngRunnableGameCanvas.m_g.setColor(0, 50, 150);
        m_pPhysEngRunnableGameCanvas.m_g.drawString ("LIFES:", 50, 10, Graphics.LEFT|Graphics.TOP);
        m_pPhysEngRunnableGameCanvas.m_g.drawString (""+CGameInfo.getLifes()+"", 50, 20, Graphics.LEFT|Graphics.TOP);        

        m_pPhysEngRunnableGameCanvas.m_g.setColor(0, 50, 150);
        m_pPhysEngRunnableGameCanvas.m_g.drawString ("LIFE POINTS:", 100, 10, Graphics.LEFT|Graphics.TOP);
        m_pPhysEngRunnableGameCanvas.m_g.drawString (""+CGameInfo.getLifePoints()+"", 100, 20, Graphics.LEFT|Graphics.TOP);
        
        m_pPhysEngRunnableGameCanvas.m_g.setColor(0, 50, 150);
        m_pPhysEngRunnableGameCanvas.m_g.drawString ("STAGE NAME:", 150, 10, Graphics.LEFT|Graphics.TOP);
        m_pPhysEngRunnableGameCanvas.m_g.drawString (CGameInfo.getStageName(), 150, 20, Graphics.LEFT|Graphics.TOP);
        
        

     
        
        
        if(CONSTANTS.TIMES_INFO)
        {
            //int y=0;
//            y = write(y, m_spufoDebug);
//            y = write(y, m_spufoDebug2);
//            y = write(y, new String("m_lTiempoControlObjects: "+m_lTiempoControlObjects));
//            y = write(y, new String("m_lTiempoControlCamara: "+m_lTiempoControlCamara));
//            y = write(y, new String("m_lTiempoIntegration: "+m_lTiempoIntegration));
//            y = write(y, new String("m_lTiempoCollObjects: "+m_lTiempoCollObjects));
//            y = write(y, new String("m_lTiempoCollLimits: "+m_lTiempoCollLimits));
//            y = write(y, new String("m_lTiempoCollAcc: "+m_lTiempoCollAcc));
//            y = write(y, new String("m_lTiempoOldStates: "+m_lTiempoOldStates));
//            y = write(y, new String("m_lTiempoDrawBack: "+m_lTiempoDrawBack));
//            y = write(y, new String("m_lTiempoDrawObjects: "+m_lTiempoDrawObjects));
//            y = write(y, new String("m_lTiempoForeground: "+m_lTiempoForeground));
//            y = write(y, new String("m_lTiempoDrawDebug: "+m_lTiempoDrawDebug));
//            y = write(y, new String("m_lTiempoFlush: "+m_lTiempoFlush));
//            y = m_pPhysEngRunnableGameCanvas.write(y, new String("m_lTTotal: "+m_lTiempoTotal));
//            y = m_pPhysEngRunnableGameCanvas.write(y, new String("m_lTTestSumaLong: "+m_lTiempoTestSumaLong));
//            y = m_pPhysEngRunnableGameCanvas.write(y, new String("m_lTTestSumaDouble: "+m_lTiempoTestSumaDouble));
//            y = m_pPhysEngRunnableGameCanvas.write(y, new String("m_lTTestMultDouble: "+m_lTiempoTestMultDouble));
//            y = m_pPhysEngRunnableGameCanvas.write(y, new String("m_lTTestMultLong: "+m_lTiempoTestMultLong));
//            y = m_pPhysEngRunnableGameCanvas.write(y, new String("m_lTTestDivLong: "+m_lTiempoTestDivLong));
//            y = m_pPhysEngRunnableGameCanvas.write(y, new String("m_lTTestDivDouble: "+m_lTiempoTestDivDouble));
//            y = m_pPhysEngRunnableGameCanvas.write(y, new String("m_lTCount: "+m_lTiempoCount));
//            y = m_pPhysEngRunnableGameCanvas.write(y, new String("m_lTBitWise: "+m_lTiempoBitWise));
//            y = m_pPhysEngRunnableGameCanvas.write(y, new String("m_lTDrawRect: "+m_lTiempoDrawRect));
            
        }
    }
    private void drawDebug(){
        if(CONSTANTS.DEBUG_COLLISIONS){
            m_pPhysEngRunnableGameCanvas.m_g.setColor(255,0,255);
            for (int lLine = 0; lLine < m_vDebugLines.size(); lLine ++) {
                CDebugLine DebugLine= ((CDebugLine) (m_vDebugLines.elementAt(lLine )));
                DebugLine.draw(m_pPhysEngRunnableGameCanvas);
                if(DebugLine.hasToBeDestroyed()){
                    m_vDebugLines.removeElementAt(lLine);
                    lLine--;
                }
                //DebugLine.m_lCount--;

            }
            m_pPhysEngRunnableGameCanvas.m_g.setColor(200,0,200);
            for (int lLine = 0; lLine < m_vDebugPoints.size(); lLine ++) {
                CDebugPoint DebugPoint= ((CDebugPoint) (m_vDebugPoints.elementAt(lLine )));
                DebugPoint.draw(m_pPhysEngRunnableGameCanvas);
                if(DebugPoint.hasToBeDestroyed()){
                    m_vDebugPoints.removeElementAt(lLine);
                    lLine--;
                }
                //DebugPoint.m_lCount--;
            }
        }
    }
    public void AddDebugLine(float fLinePointAx,float fLinePointAy,float fLinePointBx,float fLinePointBy,int r, int g, int b){
        if(CONSTANTS.DEBUG_COLLISIONS){

            CDebugLine pDebugLine=new CDebugLine(fLinePointAx,fLinePointAy,fLinePointBx,fLinePointBy,r,g,b);
            m_vDebugLines.addElement(pDebugLine);
        }

    }

    public void AddDebugPoint(float fX,float fY,int r, int g, int b){
        if(CONSTANTS.DEBUG_COLLISIONS){
            CDebugPoint pDebugPoint=new CDebugPoint(fX,fY,r,g,b);
            m_vDebugPoints.addElement(pDebugPoint);
        }

    }
    public void AddPermanentDebugLine(float fLinePointAx,float fLinePointAy,float fLinePointBx,float fLinePointBy,int r, int g, int b){
        if(CONSTANTS.DEBUG_COLLISIONS){

            CDebugLine pDebugLine=new CDebugLine(fLinePointAx,fLinePointAy,fLinePointBx,fLinePointBy,r,g,b);
            pDebugLine.setPermanent();
            m_vDebugLines.addElement(pDebugLine);
        }

    }

    public void AddPermanentDebugPoint(float fX,float fY,int r, int g, int b){
        if(CONSTANTS.DEBUG_COLLISIONS){
            CDebugPoint pDebugPoint=new CDebugPoint(fX,fY,r,g,b);
            pDebugPoint.setPermanent();
            m_vDebugPoints.addElement(pDebugPoint);
        }

    }
    public void clearScene(){

        //Debug
        m_lTiempoControlObjects=0;
        m_lTiempoControlCamara=0;
        m_lTiempoIntegration=0;
        m_lTiempoCollObjects=0;
        m_lTiempoCollLimits=0;
        m_lTiempoCollAcc=0;
        m_lTiempoOldStates=0;
        m_lTiempoDrawBack=0;
        m_lTiempoDrawObjects=0;
        m_lTiempoForeground=0;
        m_lTiempoDrawDebug=0;

        //debug lines and points
        m_vDebugLines.removeAllElements();
        m_vDebugPoints.removeAllElements();

        //Limits. floor, ceiling, walls
        m_lfLeftLimit=0.0f;
        m_lfRightLimit=0.0f;
        m_lfTopLimit=0.0f;
        m_lfBottomLimit=0.0f;
        //public float m_lfLimitsWidth;

        m_vPoints.removeAllElements();
        m_vPolygons.removeAllElements();
        m_vCollisions.removeAllElements();
        m_vGroups.removeAllElements();

    }
    
    public boolean isThereSpaceForThisGroup(CGroup pGroup){
        Vector vObjectsInGroup=pGroup.getObjetsInGroup();
        for(int lObject=0;lObject<vObjectsInGroup.size();lObject++){
            if(CONSTANTS.POLYGON==((CObject)(vObjectsInGroup.elementAt(lObject))).m_lType){
                if (false==isThereSpaceForThisPolygon((CPolygon)(vObjectsInGroup.elementAt(lObject))))
                    return false;
            }
        }
        return true;        
    }
    
    public boolean isThereSpaceForThisPolygon(CPolygon pPolygon){
        
        
        for (int lPolygon = 0; lPolygon < m_vPolygons.size(); lPolygon++) {
            CPolygon thisPolygon = ((CPolygon) (m_vPolygons.elementAt(lPolygon)));
            if (true==CMath.testCollisionProximity(pPolygon, thisPolygon))
                return false;            
        }
        for (int lPoint = 0; lPoint < m_vPoints.size(); lPoint++) {
            CPoint thisPoint = ((CPoint) (m_vPoints.elementAt(lPoint)));
            if (true==CMath.testCollisionProximity(pPolygon, thisPoint.getX(),thisPoint.getY()))
                return false;
        }
        for (int lGroup = 0; lGroup < m_vGroups.size(); lGroup++) {
            CGroup thisGroup = ((CGroup) m_vGroups.elementAt(lGroup));
            if (true==CMath.testCollisionProximity(pPolygon, thisGroup))
                return false;
        }

        
 
//        m_vGroups
//        CMath.testCollisionProximity2(pPolygon, pGroup);
        
        return true;
    }
    
//    public void setSceneControl(CSceneControl pSceneControl){
//        m_pSceneControl=pSceneControl;
//    }
}

