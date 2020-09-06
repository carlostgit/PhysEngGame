/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CPolygon extends CObject {


    
    //Params:
    public int m_lMaxNumVertex = 3 * 20; //Deprecated. I have to change this. Polygons always will be treangles
    public int m_lNumOfVertex=3; //Deprecated. I have to change this. Polygons always will be treangles
    public int[] m_lModRelVertexes = new int[m_lMaxNumVertex];
    public int[] m_lAngRelVertexes = new int[m_lMaxNumVertex];
    public boolean m_bHandControlled=false;
    
    
    //States:
    //public float m_fCollisionTestSquareSize=0;
    
    
    
    
    public float[] m_lfXCoordVertexesNew = new float[m_lMaxNumVertex];
    public float[] m_lfYCoordVertexesNew = new float[m_lMaxNumVertex];

    public float[] m_lfXCoordVertexesOld = new float[m_lMaxNumVertex];
    public float[] m_lfYCoordVertexesOld = new float[m_lMaxNumVertex];


    public CPolygon(PhysEngRunnableGameCanvas pPruebasGameCanvas)
    {
        super(pPruebasGameCanvas);
        m_lType=CONSTANTS.POLYGON; //Type 1 = CPolygon

        init();
        
    }
    
    public CPolygon(PhysEngRunnableGameCanvas pPruebasGameCanvas, float fX1, float fY1, float fX2, float fY2, float fX3, float fY3, int lR, int lG, int lB, float fMass){
        
        super(pPruebasGameCanvas);
        m_lType=CONSTANTS.POLYGON; //Type 1 = CPolygon
        
        float fXCentre=(fX1+fX2+fX3)/3;
        float fYCentre=(fY1+fY2+fY3)/3;
        
        float fModule1=CMath.calculateModule(fX1-fXCentre,fY1-fYCentre);
        float fModule2=CMath.calculateModule(fX2-fXCentre,fY2-fYCentre);
        float fModule3=CMath.calculateModule(fX3-fXCentre,fY3-fYCentre);
        
        float fAngle1=CMath.GetVectorAngle(fXCentre,fYCentre,fX1,fY1);
        float fAngle2=CMath.GetVectorAngle(fXCentre,fYCentre,fX2,fY2);
        float fAngle3=CMath.GetVectorAngle(fXCentre,fYCentre,fX3,fY3);
  
        //m_fX = fXCentre;                
        //m_fY = fYCentre;        
        
        
        m_lNumOfVertex = 3;
        m_lModRelVertexes[0] = (int)fModule1;
        m_lAngRelVertexes[0] = (int)fAngle1;
        m_lModRelVertexes[1] = (int)fModule2;
        m_lAngRelVertexes[1] = (int)fAngle2;
        m_lModRelVertexes[2] = (int)fModule3;
        m_lAngRelVertexes[2] = (int)fAngle3;
        m_bHandControlled = false;
        m_bActivated = true;
        m_fMass = fMass;
        
        setColor(lR, lG, lB);
        
        this.setInitPos(fXCentre,fYCentre);
        
        init();        
    }

    public CPolygon(PhysEngRunnableGameCanvas pPruebasGameCanvas, float fX, float fY, float fVx, float fVy, int lSize, float fDegreesTurned, float fVAngular, float fMass)
    {
        super(pPruebasGameCanvas);
        m_lType=CONSTANTS.POLYGON; //Type 1 = CPolygon
        //m_fX = fX;
        //m_fXOld = fX;
        //m_fY = fY;
        //m_fYOld = fY;
        
        m_fVx = fVx;
        m_fVxOld = fVx;
        m_fVy = fVy;
        m_fVyOld = fVy;
        m_lNumOfVertex = 3;
        m_lModRelVertexes[0] = lSize;
        m_lAngRelVertexes[0] = 0;
        m_lModRelVertexes[1] = lSize;
        m_lAngRelVertexes[1] = 120;
        m_lModRelVertexes[2] = lSize;
        m_lAngRelVertexes[2] = 240;
        m_bHandControlled = false;
        m_bActivated = true;
        m_fMass = fMass;
        m_fDegree = fDegreesTurned;
        m_fDegreeOld = fDegreesTurned;
        m_fVAngular = fVAngular;
        m_fVAngularOld = fVAngular;

        this.setInitPos(fX,fY);

        init();

    }
    public void init(){
   
    }
        
    public void integrate(){
        super.integrate();
        nextStates();
        

    }
    public void saveOldStates(){
        super.saveOldStates();
        m_fVAngularOld=m_fVAngular;
        m_fAAngularOld=m_fAAngular;
        for (int lVertex = 0; lVertex < m_lNumOfVertex; lVertex++) {
            m_lfXCoordVertexesOld[lVertex] = m_lfXCoordVertexesNew[lVertex];
            m_lfYCoordVertexesOld[lVertex] = m_lfYCoordVertexesNew[lVertex];
        }
    }
    public void draw(){
  /*
        for (int lTriangle = 0; lTriangle < m_lNumOfVertex / 3; lTriangle++) {
            m_pPhysEngRunnableGameCanvas.m_g.setColor(255, 255, 255);
            int lX1Rel = (int) ((float) (m_lModRelVertexes[lTriangle + 0]) * CMath.cosine((m_fDegree + m_lAngRelVertexes[lTriangle + 0])));
            int lY1Rel = (int) ((float) (m_lModRelVertexes[lTriangle + 0]) * CMath.sine((m_fDegree + m_lAngRelVertexes[lTriangle + 0])));
            int lY1RelOpposite= - lY1Rel;

            
            int lX2Rel = (int) ((float) (m_lModRelVertexes[lTriangle + 1]) * CMath.cosine((m_fDegree + m_lAngRelVertexes[lTriangle + 1])));
            int lY2Rel = (int) ((float) (m_lModRelVertexes[lTriangle + 1]) * CMath.sine((m_fDegree + m_lAngRelVertexes[lTriangle + 1])));
            int lY2RelOpposite= - lY2Rel;
            
            int lX3Rel = (int) ((float) (m_lModRelVertexes[lTriangle + 2]) * CMath.cosine((m_fDegree + m_lAngRelVertexes[lTriangle + 2])));
            int lY3Rel = (int) ((float) (m_lModRelVertexes[lTriangle + 2]) * CMath.sine((m_fDegree + m_lAngRelVertexes[lTriangle + 2])));
            int lY3RelOpposite= - lY3Rel;
            
            int lYOpposite=(m_pPhysEngRunnableGameCanvas.getHeight()<< m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel) - (int)m_fY ;
            m_pPhysEngRunnableGameCanvas.m_g.fillTriangle(((int)m_fX + lX1Rel) >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel, (lYOpposite + lY1RelOpposite) >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel, ((int)m_fX + lX2Rel) >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel, (lYOpposite + lY2RelOpposite) >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel, ((int)m_fX + lX3Rel) >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel, (lYOpposite + lY3RelOpposite) >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel);
            
            m_pPhysEngRunnableGameCanvas.m_g.setColor(50, 100, 50);
            m_pPhysEngRunnableGameCanvas.m_g.fillRect((int)m_fX >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel, lYOpposite >> m_pPhysEngRunnableGameCanvas.m_lZoomOutLevel, 10, 10);
        }
   */
        /*
        for (int lTriangle = 0; lTriangle < m_lNumOfVertex / 3; lTriangle++) {
            
            float fX1Rel = ((float) (m_lModRelVertexes[3*lTriangle + 0]) * CMath.cosine((m_fDegree + m_lAngRelVertexes[3*lTriangle + 0])));
            float fY1Rel = ((float) (m_lModRelVertexes[3*lTriangle + 0]) * CMath.sine((m_fDegree + m_lAngRelVertexes[3*lTriangle + 0])));
  
            float fX2Rel = ((float) (m_lModRelVertexes[3*lTriangle + 1]) * CMath.cosine((m_fDegree + m_lAngRelVertexes[3*lTriangle + 1])));
            float fY2Rel = ((float) (m_lModRelVertexes[3*lTriangle + 1]) * CMath.sine((m_fDegree + m_lAngRelVertexes[3*lTriangle + 1])));
  
            float fX3Rel = ((float) (m_lModRelVertexes[3*lTriangle + 2]) * CMath.cosine((m_fDegree + m_lAngRelVertexes[3*lTriangle + 2])));
            float fY3Rel = ((float) (m_lModRelVertexes[3*lTriangle + 2]) * CMath.sine((m_fDegree + m_lAngRelVertexes[3*lTriangle + 2])));

            m_pPhysEngRunnableGameCanvas.setColor(0, 0, 200);

            m_pPhysEngRunnableGameCanvas.drawLine(
                    m_fX + fX1Rel,
                    m_fY + fY1Rel,
                    m_fX + fX2Rel,
                    m_fY + fY2Rel);
            m_pPhysEngRunnableGameCanvas.drawLine(
                    m_fX + fX3Rel,
                    m_fY + fY3Rel,
                    m_fX + fX1Rel,
                    m_fY + fY1Rel);

            m_pPhysEngRunnableGameCanvas.setColor(m_lColorR, m_lColorG, m_lColorB);
            m_pPhysEngRunnableGameCanvas.fillTriangle(
                    m_fX + fX1Rel,
                    m_fY + fY1Rel,
                    m_fX + fX2Rel,
                    m_fY + fY2Rel,
                    m_fX + fX3Rel,
                    m_fY + fY3Rel);

            m_pPhysEngRunnableGameCanvas.setColor(50, 100, 50);
            float fSizeOfGravityCentre=500;
            m_pPhysEngRunnableGameCanvas.fillRect(
                    m_fX,
                    m_fY,
                    fSizeOfGravityCentre,
                    fSizeOfGravityCentre);
        }

        */
        /*
        m_pPhysEngRunnableGameCanvas.setColor(0, 0, 200);

        m_pPhysEngRunnableGameCanvas.drawLine(
                m_lfXCoordVertexes[0],
                m_lfYCoordVertexes[0],
                m_lfXCoordVertexes[1],
                m_lfYCoordVertexes[1]);
        m_pPhysEngRunnableGameCanvas.drawLine(
                m_lfXCoordVertexes[2],
                m_lfYCoordVertexes[2],
                m_lfXCoordVertexes[0],
                m_lfYCoordVertexes[0]);
        */
        
        
        //to view a debug triangle representing the collision test rectangle
        if(CONSTANTS.DEBUG_VIEW_COLLISION_TEST_RECTANGLE)
        {
            m_pPhysEngRunnableGameCanvas.setColor(100, 100, 0);
            m_pPhysEngRunnableGameCanvas.m_pScene.getCamera().fillTriangle(this.m_fCollisionTestXMin, this.m_fCollisionTestYMin, this.m_fCollisionTestXMin, this.m_fCollisionTestYMax, this.m_fCollisionTestXMax, this.m_fCollisionTestYMax);
        }
        m_pPhysEngRunnableGameCanvas.setColor(m_lColorR, m_lColorG, m_lColorB);

        m_pPhysEngRunnableGameCanvas.m_pScene.getCamera().fillTriangle(
                m_lfXCoordVertexesNew[0],
                m_lfYCoordVertexesNew[0],
                m_lfXCoordVertexesNew[1],
                m_lfYCoordVertexesNew[1],
                m_lfXCoordVertexesNew[2],
                m_lfYCoordVertexesNew[2]);

        
        m_pPhysEngRunnableGameCanvas.setColor(50, 100, 50);
        float fSizeOfGravityCentre=10;
        m_pPhysEngRunnableGameCanvas.m_pScene.getCamera().fillRect(
                this.getXNew(),
                this.getYNew(),
                fSizeOfGravityCentre,
                fSizeOfGravityCentre);


    }
    public void control(){
        if (true == m_bHandControlled) {

            float fControlAcceleration = 5;
            int lControlAngularAcceleration = 5;
            int lVAngularControl = 1;
            if (m_pPhysEngRunnableGameCanvas.m_bLeftPressed) {
                m_fVAngular = m_fVAngularOld + lVAngularControl;
            }
            if (m_pPhysEngRunnableGameCanvas.m_bRightPressed) {
                m_fVAngular = m_fVAngularOld - lVAngularControl;
            }
            if (m_pPhysEngRunnableGameCanvas.m_bUpPressed) {
                m_fAx = m_fAxOld + ((float) fControlAcceleration) * CMath.cosine((int)m_fDegreeOld);
                m_fAy = m_fAyOld + ((float) fControlAcceleration) * CMath.sine((int)m_fDegreeOld);
            } else {
                m_fAx = 0;
                m_fAy = 0;
            }
            //if (m_pPhysEngRunnableGameCanvas.m_bDownPressed) {
            //    m_pPhysEngRunnableGameCanvas.m_pScene.shoot(this);
            //}
        }
    }
    /**
     * Checks if a Point (PointCoord) is inside a triangle of a Polygon.
     * Then calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(PointCoord, PointCoordOld, Polygon, lTriangle)
     * can be used to get the data associated tho the collision.
     */
    public boolean isPointInsideTriangleNewPosition(CPointCartesianCoord PointCoord) {

        float[] fXVertexes = new float[3];
        float[] fYVertexes = new float[3];
        getNewVertexesOfTriangle(fXVertexes, fYVertexes);

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

    public void getNewVertexesOfTriangle(float[] fXVertexes, float[] fYVertexes) {

        float fX1Rel = ((float) (m_lModRelVertexes[0]) * CMath.cosine((m_fDegree + m_lAngRelVertexes[0])));
        float fY1Rel = ((float) (m_lModRelVertexes[0]) * CMath.sine((m_fDegree + m_lAngRelVertexes[0])));

        float fX2Rel = ((float) (m_lModRelVertexes[1]) * CMath.cosine((m_fDegree + m_lAngRelVertexes[1])));
        float fY2Rel = ((float) (m_lModRelVertexes[1]) * CMath.sine((m_fDegree + m_lAngRelVertexes[1])));

        float fX3Rel = ((float) (m_lModRelVertexes[2]) * CMath.cosine((m_fDegree + m_lAngRelVertexes[2])));
        float fY3Rel = ((float) (m_lModRelVertexes[2]) * CMath.sine((m_fDegree + m_lAngRelVertexes[2])));

        //Points of the triangle in absolute coordinates
        float fX1Abs = this.getXNew() + fX1Rel;
        float fY1Abs = this.getYNew() + fY1Rel;
        float fX2Abs = this.getXNew() + fX2Rel;
        float fY2Abs = this.getYNew() + fY2Rel;
        float fX3Abs = this.getXNew() + fX3Rel;
        float fY3Abs = this.getYNew() + fY3Rel;

        //int []fXVertexes=new int[3];
        fXVertexes[0] = fX1Abs;
        fXVertexes[1] = fX2Abs;
        fXVertexes[2] = fX3Abs;

        //int []fYVertexes=new int[3];
        fYVertexes[0] = fY1Abs;
        fYVertexes[1] = fY2Abs;
        fYVertexes[2] = fY3Abs;
    }
     /**
     * Collisions of Points with Polygons are evaluated when this method
     * is called
     */
//    public void calculateCollision(CPoint Point) {
//       
//        if(false==CMath.testCollisionProximity(this, Point.m_fX,Point.m_fY)){
//            if(false==CMath.testCollisionProximity(this, Point.m_fXOld,Point.m_fYOld)){
//                return;
//            }
//        }
//        
//        
//        //System.out.println("Calculating collision of Point "+Point.m_lID+" with Polygon "+m_lID);
//        //System.out.println("AreAlreadyColliding?");
//        boolean bAreAlreadyColliding = false;
//        //int lCollisionIndex = -1;
//
//        CCollision AlreadyCollision=m_pPhysEngRunnableGameCanvas.m_Scene.arePointAndPolygonAlreadyColliding(Point, this);
//        if (null!=AlreadyCollision){
//            bAreAlreadyColliding = true;
//        }
//
//        /*
//        for (int lCollision = 0; lCollision < m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.size(); lCollision++) {
//            CCollision Collision = ((CCollision) (m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.elementAt(lCollision)));
//            if (Collision.AreAlreadyColliding(Point,this)) {
//                //System.out.println("AlreadyColliding");
//                bAreAlreadyColliding = true;
//                lCollisionIndex = lCollision;
//                break;
//            }
//        }
//        */
//
//        //System.out.println("IsAnyTriangleColliding?");
//        boolean bIsAnyTriangleColliding = false;
//        //for (int lTriangle = 0; lTriangle < Polygon.m_lNumOfVertex / 3; lTriangle++) {
//        CPointCartesianCoord PointCoord = new CPointCartesianCoord(Point.m_fX, Point.m_fY);
//        CPointCartesianCoord PointCoordOld = new CPointCartesianCoord(Point.m_fXOld, Point.m_fYOld);
//        CCollisionData CollisionData = new CCollisionData();
//
//        //System.out.println("trying isPointInsideTriangle?");
//
//        if (calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(PointCoord, PointCoordOld, CollisionData)) {
//            //System.out.println("true from calculateNewCollision(PointCoord, PointCoordOld, Polygon, CollisionData");
//            bIsAnyTriangleColliding = true;
//            if (false == bAreAlreadyColliding) {
//
//                CCollision Collision = new CCollision(m_pPhysEngRunnableGameCanvas, Point, this, CollisionData);
//                //m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.addElement(Collision);
//                m_pPhysEngRunnableGameCanvas.m_Scene.addCollision(Collision);
//                //break;//Only one  triangle can be collided at the same time
//            }
//        }
//        //else if (isPointInsideTriangle(PointCoord)) {
//        else if(CMath.isInsideTriangle(m_lfXCoordVertexes[0], m_lfYCoordVertexes[0], m_lfXCoordVertexes[1], m_lfYCoordVertexes[1], m_lfXCoordVertexes[2], m_lfYCoordVertexes[2], PointCoord.m_fX, PointCoord.m_fY)){
//            //System.out.println("PointCollidingWithTriangle");
//            bIsAnyTriangleColliding = true;
//            if (false == bAreAlreadyColliding) {
//                //System.out.println("trying calculateNewCollision(PointCoord, PointCoordOld, Polygon);");
//                CollisionData = calculateNewCollisionWhenPointInside(PointCoord, PointCoordOld);
//
//                CCollision Collision = new CCollision(m_pPhysEngRunnableGameCanvas, Point, this, CollisionData);
//                //m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.addElement(Collision);
//                m_pPhysEngRunnableGameCanvas.m_Scene.addCollision(Collision);
//                //break;//Only one  triangle can be collided at the same time
//            }
//        }
//
//        //}
//        if (false == bIsAnyTriangleColliding && true == bAreAlreadyColliding) {
//            //System.out.println("removing collision at: "+ lCollisionIndex);
//            //m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.removeCollisionAt(lCollisionIndex);
//            m_pPhysEngRunnableGameCanvas.m_Scene.removeCollision(AlreadyCollision);
//        }
//    }
     /**
     * Is used when you already know that there is a collision between PointCoord and
     * a triangle of a Polygon to get the data associated to the collision
     * */
//    protected CCollisionData calculateNewCollisionWhenPointInside(CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld) {
///*
//        System.out.println("calculateNewCollisionSearchingCuttingTrajectoriesOfPoints X en triangle" + lTriangle);
//        System.out.println("Point.m_lX: " + PointCoord.m_fX);
//        System.out.println("Point.m_lXOld: " + PointCoordOld.m_fX);
//        System.out.println("----");
//*/
//        //System.out.println("inside private CCollisionData calculateNewCollision(CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CPolygon Polygon) {");
//
//        float[] fXVertexes = new float[3];
//        float[] fYVertexes = new float[3];
//        getVertexesOfTriangle(fXVertexes, fYVertexes);
//
//        //Con quién colisiona?? Qué linea del poligono?
//        float fLinePointAx = PointCoordOld.m_fX;
//        float fLinePointAy = PointCoordOld.m_fY;
//        float fLinePointBx = PointCoord.m_fX;
//        float fLinePointBy = PointCoord.m_fY;
//
//        //This means that the point is not colliding to the polygon,
//        //so, it's the polygon that is colliding to the point
//
//        //Utilizar aquí el método calculateCutPointOfTwoLines en vez de lo que hay a continuación
//
//        float fAngleAbsFromPolygonToPoint = CMath.GetVectorAngle(m_fXOld, m_fYOld, fLinePointAx, fLinePointAy);
//
//        float fAngleRelFromPolygonToPoint = fAngleAbsFromPolygonToPoint - m_fDegreeOld;
//
//        int lSide = getTheSideAssociatedToThisAngle(fAngleRelFromPolygonToPoint);
//
//        //System.out.println("before calculateCollisionWithSideOfPolygon(lSide, Polygon, PointCoord, PointCoordOld);");
//        return calculateCollisionWithSideOfPolygon(lSide, PointCoord, PointCoordOld);
//
//
//    }
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
//    public boolean calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CCollisionData CollisionData) {
//        //System.out.println("Colisión");
//
//        //Con quién colisiona?? Qué linea del poligono?
//        float fLinePointAx = PointCoordOld.m_fX;
//        float fLinePointAy = PointCoordOld.m_fY;
//        float fLinePointBx = PointCoord.m_fX;
//        float fLinePointBy = PointCoord.m_fY;
//
//        float fSquarelDistanceFromAPointToCutPoint = -1;
//        //int lFirstSideCutting=0;
//        float fSideAxNearestCut = 0;
//        float fSideAyNearestCut = 0;
//        float fSideBxNearestCut = 0;
//        float fSideByNearestCut = 0;
//
//        boolean bIsThereAnyCut = false;
//        for (int lSide = 0; lSide < m_lNumOfVertex; lSide++) {
//            float fModRelVertexA = m_lModRelVertexes[lSide];
//            float fSideAx = m_fX + (fModRelVertexA * CMath.cosine((m_fDegree + m_lAngRelVertexes[lSide])));
//            float fSideAy = m_fY + (fModRelVertexA * CMath.sine((m_fDegree + m_lAngRelVertexes[lSide])));
//
//            float fSideBx;
//            float fSideBy;
//
//            if (lSide == m_lNumOfVertex - 1) {
//                int lModRelVertexB = m_lModRelVertexes[0];
//                fSideBx = m_fX + (lModRelVertexB * CMath.cosine((m_fDegree + m_lAngRelVertexes[0])));
//                fSideBy = m_fY + (lModRelVertexB * CMath.sine((m_fDegree + m_lAngRelVertexes[0])));
//            } else {
//                int lModRelVertexB = m_lModRelVertexes[lSide + 1];
//                fSideBx = m_fX + (lModRelVertexB * CMath.cosine((m_fDegree + m_lAngRelVertexes[lSide + 1])));
//                fSideBy = m_fY + (lModRelVertexB * CMath.sine((m_fDegree + m_lAngRelVertexes[lSide + 1])));
//            }
//
//
//            CPointCartesianCoord CutPointCoord = new CPointCartesianCoord(0, 0);
//            //if (CMath.calculateCutPointOfTwoLines(fLinePointAx, fLinePointAy, fLinePointBx, fLinePointBy, fSideAx, fSideAy, fSideBx, fSideBy, CutPointCoord)) {
//            if (CMath.isIntersection(fLinePointAx,fLinePointAy,fLinePointBx,fLinePointBy,fSideAx,fSideAy,fSideBx,fSideBy,CutPointCoord)) {
//                m_pPhysEngRunnableGameCanvas.AddDebugLine(fLinePointAx,fLinePointAy,fLinePointBx,fLinePointBy,255,0,0);
//                m_pPhysEngRunnableGameCanvas.AddDebugLine(fSideAx,fSideAy,fSideBx,fSideBy,0,255,0);
//                m_pPhysEngRunnableGameCanvas.AddDebugPoint(CutPointCoord.m_fX,CutPointCoord.m_fY,0,0,255);
//                //System.out.println("cutting point "+CutPointCoord.m_fX+" "+CutPointCoord.m_fY);
//
//                bIsThereAnyCut = true;
//                float fDistanceFromAPointToThisSideCutPoint = (CutPointCoord.m_fX - fLinePointAx) * (CutPointCoord.m_fX - fLinePointAx) + (CutPointCoord.m_fY - fLinePointAy) * (CutPointCoord.m_fY - fLinePointAy);
//                if (fSquarelDistanceFromAPointToCutPoint == -1) {
//                    fSquarelDistanceFromAPointToCutPoint = fDistanceFromAPointToThisSideCutPoint;
//                    PointCoord = CutPointCoord;
//                    fSideAxNearestCut = fSideAx;
//                    fSideAyNearestCut = fSideAy;
//                    fSideBxNearestCut = fSideBx;
//                    fSideByNearestCut = fSideBy;
//
//                } else if (fDistanceFromAPointToThisSideCutPoint < fSquarelDistanceFromAPointToCutPoint) {
//                    fSquarelDistanceFromAPointToCutPoint = fDistanceFromAPointToThisSideCutPoint;
//                    PointCoord = CutPointCoord;
//                    fSideAxNearestCut = fSideAx;
//                    fSideAyNearestCut = fSideAy;
//                    fSideBxNearestCut = fSideBx;
//                    fSideByNearestCut = fSideBy;
//                }
//            }
//        }
//
//        CCollisionData CollisionDataNew = calculateCollisionWithSideOfPolygon(fSideAxNearestCut, fSideAyNearestCut, fSideBxNearestCut, fSideByNearestCut, PointCoord, PointCoordOld);
//        CollisionData.m_fCollPositionAngle = CollisionDataNew.m_fCollPositionAngle;
//        CollisionData.m_fCollPositionModRel = CollisionDataNew.m_fCollPositionModRel;
//        CollisionData.m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon = CollisionDataNew.m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon;
//        CollisionData.m_fAngleOfRepulsionOfPolygon = CollisionDataNew.m_fAngleOfRepulsionOfPolygon;
//
//        return bIsThereAnyCut;
//
//    }
    public int getTheSideAssociatedToThisAngle(float fAngle) {

        int lIndexOfVertexes = -1;
        float fAngleMin = 360;

        for (int lVertex = 0; lVertex < m_lNumOfVertex; lVertex++) {

            float fAngleWidthVertexToAngle = CMath.getAngleSubstraction(m_lAngRelVertexes[lVertex],fAngle);

            if (fAngleWidthVertexToAngle < fAngleMin) {
                fAngleMin = fAngleWidthVertexToAngle;
                lIndexOfVertexes = lVertex - 1;
            }
        }
        if (lIndexOfVertexes < 0) {
            lIndexOfVertexes = m_lNumOfVertex - 1;
        }
        return lIndexOfVertexes;
    }
        /**
 * The collision data of a collision between a Side of a Polygon and the line formed by the Points PointCoord and PointCoordOld is obtained.
 *
 */
//    public CCollisionData calculateCollisionWithSideOfPolygon(float fSideAx, float fSideAy, float fSideBx, float fSideBy, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld) {
//
//        //System.out.println("inside private CCollisionData calculateCollisionWithSideOfPolygon(float fSideAx, float fSideAy, float fSideBx, float fSideBy, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld) {");
//        float fLinePolygonAx = fSideAx;
//        float fLinePolygonAy = fSideAy;
//
//        float fLinePolygonBx = fSideBx;
//        float fLinePolygonBy = fSideBy;
//
//        float fLineAngle = CMath.GetVectorAngle(fLinePolygonAx, fLinePolygonAy, fLinePolygonBx, fLinePolygonBy);
//
//        float fAngleOfRepulsion1 = fLineAngle + 270;
//        if (fAngleOfRepulsion1 > 360) {
//            fAngleOfRepulsion1 = fAngleOfRepulsion1 - 360;
//        }
//        //System.out.println("lAngleOfRepulsion1: "+lAngleOfRepulsion1);
//        float fAngleOfRepulsion2 = fLineAngle + 90;
//        if (fAngleOfRepulsion2 > 360) {
//            fAngleOfRepulsion2 = fAngleOfRepulsion2 - 360;
//        }
//
//        //Relative coordinates of the collision to gravity centre of the polygon:
//        //Module:
//        float CollAngle = CMath.GetVectorAngle(m_fX, m_fY, PointCoord.m_fX, PointCoord.m_fY);
//        float CollXRel = PointCoord.m_fX - m_fX;
//        float CollYRel = PointCoord.m_fY - m_fY;
//        float CollModRel = (int) (((float) CollXRel) * CMath.cosine(CollAngle) + ((float) CollYRel) * CMath.sine(CollAngle));
//
//        //System.out.println("New Collision");
//        //CCollision Collision=new CCollision(this, Point,lAngleOfRepulsion1,Polygon,lAngleOfRepulsion2,CollModRel,CollAngle);
//        //atención, si ya están colisionando esos dos objetos, ya no debería añadir nuevos elemmentos a la lista
//        //Necesito un metodo AreAlreadyColliding para ponerlo al principio del método
//        CCollisionData CollisionData = new CCollisionData(fAngleOfRepulsion1, fAngleOfRepulsion2, CollModRel, CollAngle);
//        return CollisionData;
//    }
        /**
 * The collision data of a collision between a Side of a Polygon and the line formed by the Points PointCoord and PointCoordOld is obtained.
 * To do:There is other method with the same function. So, one of this methos is not needed.
 */
//    public CCollisionData calculateCollisionWithSideOfPolygon(int lSide, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld) {
//        //System.out.println("calculateCollisionWithSideOfPolygon(int lSide, int lTriangle, CPolygon Polygon, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld) {");
//        float[] fXVertexes = new float[3];
//        float[] fYVertexes = new float[3];
//        getVertexesOfTriangle( fXVertexes, fYVertexes);
//
//        //Saco la linea que le corresponde a cada vértice
//        int lVert = lSide;
//        int lNumOfVertexes = 3;
//        int lVertSiguiente = lVert + 1;
//        if (lVert >= lNumOfVertexes - 1) {
//            lVertSiguiente = 0;
//        }
//
//        //System.out.println("1");
//        //System.out.println("lVert="+lVert);
//        //System.out.println("lVertSiguiente="+lVertSiguiente);
//        float fLinePolygonAx = fXVertexes[lVert];
//        float fLinePolygonAy = fYVertexes[lVert];
//        //System.out.println("2");
//        float fLinePolygonBx = fXVertexes[lVertSiguiente];
//        float fLinePolygonBy = fYVertexes[lVertSiguiente];
//        //System.out.println("3");
//
//        float fLineAngle = CMath.GetVectorAngle(fLinePolygonAx, fLinePolygonAy, fLinePolygonBx, fLinePolygonBy);
//        //System.out.println("4");
//        float fAngleOfRepulsion1 = fLineAngle + 270;
//        if (fAngleOfRepulsion1 > 360) {
//            fAngleOfRepulsion1 = fAngleOfRepulsion1 - 360;
//        }
//        //System.out.println("lAngleOfRepulsion1: "+lAngleOfRepulsion1);
//        float fAngleOfRepulsion2 = fLineAngle + 90;
//        if (fAngleOfRepulsion2 > 360) {
//            fAngleOfRepulsion2 = fAngleOfRepulsion2 - 360;
//        }
//        //Relative coordinates of the collision to gravity centre of the polygon:
//        //Module:
//        float CollAngle = CMath.GetVectorAngle(m_fX, m_fY, PointCoord.m_fX, PointCoord.m_fY);
//        float CollXRel = PointCoord.m_fX - m_fX;
//        float CollYRel = PointCoord.m_fY - m_fY;
//        float CollModRel = (int) (((float) CollXRel) * CMath.cosine(CollAngle) + ((float) CollYRel) * CMath.sine(CollAngle));
//
//
//        //System.out.println("New Collision. Total number of collisions = " + m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.size());
//        //CCollision Collision=new CCollision(this, Point,lAngleOfRepulsion1,Polygon,lAngleOfRepulsion2,CollModRel,CollAngle);
//        //atención, si ya están colisionando esos dos objetos, ya no debería añadir nuevos elemmentos a la lista
//        //Necesito un metodo AreAlreadyColliding para ponerlo al principio del método
//        CCollisionData CollisionData = new CCollisionData(fAngleOfRepulsion1, fAngleOfRepulsion2, CollModRel, CollAngle);
//        return CollisionData;
//    }
    public void nextStates(){

        
    }
   
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
    public void SetCinematicVariablesTo(float fX,float fY,float fVx,float fVy,float fVAngular,float fDegree){
        super.SetCinematicVariablesTo(fX,fY,fVx,fVy,fVAngular,fDegree);
        for (int lVertex = 0; lVertex < m_lNumOfVertex; lVertex++) {
            float lfModRelVertex = m_lModRelVertexes[lVertex];
            m_lfXCoordVertexesNew[lVertex] = fX + (int) (lfModRelVertex * CMath.cosine(m_fDegree + m_lAngRelVertexes[lVertex]));
            m_lfYCoordVertexesNew[lVertex] = fY + (int) (lfModRelVertex * CMath.sine(m_fDegree + m_lAngRelVertexes[lVertex]));
        }
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
    
    public CLine getSideOfPolygonNew(int lSideIndex){
        
        if(lSideIndex>m_lNumOfVertex-1)
            System.out.println("Index of polygon out of range");
        
        float fSideAx=m_lfXCoordVertexesNew[lSideIndex];
        float fSideAy=m_lfYCoordVertexesNew[lSideIndex];
        
        float fSideBx=0.0f;
        float fSideBy=0.0f;
        if (lSideIndex == m_lNumOfVertex - 1) {
            fSideBx = m_lfXCoordVertexesNew[0];
            fSideBy = m_lfYCoordVertexesNew[0];
        } else {
            fSideBx = m_lfXCoordVertexesNew[lSideIndex+1];
            fSideBy = m_lfYCoordVertexesNew[lSideIndex+1];
        }
        return new CLine(new CPointCartesianCoord(fSideAx,fSideAy),new CPointCartesianCoord(fSideBx,fSideBy));        
    }
    
    public CLine getSideOfPolygonOld(int lSideIndex){
        
        if(lSideIndex>m_lNumOfVertex-1)
            System.out.println("Index of polygon out of range");
        
        float fSideAx=m_lfXCoordVertexesOld[lSideIndex];
        float fSideAy=m_lfYCoordVertexesOld[lSideIndex];
        
        float fSideBx=0.0f;
        float fSideBy=0.0f;
        if (lSideIndex == m_lNumOfVertex - 1) {
            fSideBx = m_lfXCoordVertexesOld[0];
            fSideBy = m_lfYCoordVertexesOld[0];
        } else {
            fSideBx = m_lfXCoordVertexesOld[lSideIndex+1];
            fSideBy = m_lfYCoordVertexesOld[lSideIndex+1];
        }
        return new CLine(new CPointCartesianCoord(fSideAx,fSideAy),new CPointCartesianCoord(fSideBx,fSideBy));        
    }
    
    float getRepulsionAngleOfSideInDegrees(int lSideIndex){
    
        CLine plineSide=getSideOfPolygonNew(lSideIndex);
        float fLineAngle = CMath.GetVectorAngle(plineSide.m_pPointA.m_fX, plineSide.m_pPointA.m_fY, plineSide.m_pPointB.m_fX, plineSide.m_pPointB.m_fY);

        float fAngleOfRepulsion1 = fLineAngle + 270;
        if (fAngleOfRepulsion1 > 360) {
            fAngleOfRepulsion1 = fAngleOfRepulsion1 - 360;
        }        
        return fAngleOfRepulsion1;
    }
}
