/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CMathPolygonCollision {

     static void calculateCollision(CScene pScene, CPolygon Polygon1, CPolygon Polygon2) {
        //calculateCollisionPolygon1ToPolygon2(Polygon1, Polygon2);
        //calculateCollisionPolygon1ToPolygon2(Polygon2, Polygon1);

        if(CMath.testCollisionProximity(Polygon1, Polygon2) && (false==Polygon1.m_bImmobile || false==Polygon2.m_bImmobile)){
            boolean bPointOf1CollidingWith2=false;
            boolean bPointOf2CollidingWith1=false;
            if(null==pScene.AreAlreadyCollidingWithoutVertexes(Polygon1, Polygon2)){
                bPointOf1CollidingWith2=calculateCollisionPolygon1ToPolygon2OnlyVertexes(pScene, Polygon1, Polygon2);
                bPointOf2CollidingWith1=calculateCollisionPolygon1ToPolygon2OnlyVertexes(pScene, Polygon2, Polygon1);
            }

            //System.out.println("Calculating collision");
            if(CONSTANTS.TRY_COLLISIONS_WITHOUT_VERTEXES){
                if ((false==bPointOf1CollidingWith2 && false==bPointOf2CollidingWith1)/* || null!=pScene.AreAlreadyCollidingWithoutVertexes(Polygon1, Polygon2)*/){
                    calculateCollisionPolygon1ToPolygon2SidesIntersection(pScene, Polygon1, Polygon2);
                }
            }
            //System.out.println("Calculated");
        }     
    }
     
    static boolean calculateCollisionPolygon1ToPolygon2OnlyVertexes(CScene pScene,CPolygon Polygon1, CPolygon Polygon2) {

        boolean bIsAnyTriangleColliding = false;
        boolean bIsAnyTriangleAlreadyColliding = false;
        for (int lVertexPolygon1 = 0; lVertexPolygon1 < Polygon1.m_lNumOfVertex; lVertexPolygon1++) {
            
            CCollision alreadyCollision=pScene.AreAlreadyColliding(Polygon1, lVertexPolygon1, Polygon2);

            boolean bAreAlreadyColliding = false;
            if(null!=alreadyCollision){
                bIsAnyTriangleAlreadyColliding=true;
                bAreAlreadyColliding = true;
            }
            
            CPointCartesianCoord Polygon1VertexCoord = new CPointCartesianCoord(Polygon1.m_lfXCoordVertexesNew[lVertexPolygon1], Polygon1.m_lfYCoordVertexesNew[lVertexPolygon1]);
            CPointCartesianCoord Polygon1VertexCoordOld = new CPointCartesianCoord(Polygon1.m_lfXCoordVertexesOld[lVertexPolygon1], Polygon1.m_lfYCoordVertexesOld[lVertexPolygon1]);
            
            boolean bIsThisTriangleColliding=false;
            CCollisionData CollisionData = new CCollisionData();
            if (calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(pScene,Polygon2,Polygon1VertexCoord, Polygon1VertexCoordOld, CollisionData)) {

                pScene.AddDebugLine(Polygon1VertexCoordOld.m_fX, Polygon1VertexCoordOld.m_fY, Polygon1VertexCoord.m_fX, Polygon1VertexCoord.m_fY, 100, 100, 0);

                bIsAnyTriangleColliding = true;
                bIsThisTriangleColliding = true;
                if (false == bAreAlreadyColliding) {

                    CCollision Collision = new CCollision(pScene.m_pPhysEngRunnableGameCanvas, Polygon1, lVertexPolygon1, Polygon2, CollisionData);
                    //m_vCollisions.addElement(Collision);
                    pScene.addCollision(Collision);
                }
            }
            else if(CMath.isInsideTriangle(Polygon2.m_lfXCoordVertexesNew[0], Polygon2.m_lfYCoordVertexesNew[0], Polygon2.m_lfXCoordVertexesNew[1], Polygon2.m_lfYCoordVertexesNew[1], Polygon2.m_lfXCoordVertexesNew[2], Polygon2.m_lfYCoordVertexesNew[2], Polygon1VertexCoord.m_fX, Polygon1VertexCoord.m_fY)){
                //CollisionData = calculateNewCollisionWhenPointInside(pScene,Polygon2,Polygon1VertexCoord, Polygon1VertexCoordOld);
                calculateNewCollisionWhenPointInside(pScene,Polygon2,Polygon1VertexCoord, Polygon1VertexCoordOld, CollisionData);
                pScene.AddDebugLine(Polygon1VertexCoordOld.m_fX, Polygon1VertexCoordOld.m_fY, Polygon1VertexCoord.m_fX, Polygon1VertexCoord.m_fY, 0, 100, 100);

                bIsAnyTriangleColliding = true;
                bIsThisTriangleColliding = true;
                if (false == bAreAlreadyColliding) {

                    CCollision Collision = new CCollision(pScene.m_pPhysEngRunnableGameCanvas, Polygon1, lVertexPolygon1, Polygon2, CollisionData);
                    //m_vCollisions.addElement(Collision);
                    pScene.addCollision(Collision);
                }

            }

            if (false == bIsThisTriangleColliding && true == bAreAlreadyColliding) {
                //System.out.println("removing collision at: "+ lCollisionIndex);
                //m_vCollisions.removeElementAt(lCollisionIndex);
//                pScene.removeCollision(alreadyCollision);
            }
            
        }
        if(bIsAnyTriangleAlreadyColliding || bIsAnyTriangleColliding ){
            return true;
        }else{
            return false;
        }


    }
    static boolean isIntersection(CScene pScene,CPolygon Polygon1, CPolygon Polygon2,CPointCartesianCoord pPointOfDetectedIntersection){
        
        for (int lVertex1 = 0; lVertex1 < Polygon1.m_lNumOfVertex; lVertex1++) {
            
            int a1=lVertex1;
            int b1;
            if(2==lVertex1)
                b1=0;
            else
                b1=lVertex1+1;
        
            for (int lVertex2 = 0; lVertex2 < Polygon2.m_lNumOfVertex; lVertex2++) {
                
                int a2=lVertex2;
                int b2;
                if(2==lVertex2)
                    b2=0;
                else
                    b2=lVertex2+1;
                
               // System.out.println("Testing intersection Polygon 1 vertex:"+lVertex1+"Polygon 2 vertex:"+lVertex2);
                if ( CMath.isIntersection(Polygon1.m_lfXCoordVertexesNew[a1],Polygon1.m_lfYCoordVertexesNew[a1],Polygon1.m_lfXCoordVertexesNew[b1],Polygon1.m_lfYCoordVertexesNew[b1],
                        Polygon2.m_lfXCoordVertexesNew[a2],Polygon2.m_lfYCoordVertexesNew[a2],Polygon2.m_lfXCoordVertexesNew[b2],Polygon2.m_lfYCoordVertexesNew[b2],
                        pPointOfDetectedIntersection)){
                    pScene.AddDebugLine(Polygon1.m_lfXCoordVertexesNew[a1],Polygon1.m_lfYCoordVertexesNew[a1],Polygon1.m_lfXCoordVertexesNew[b1],Polygon1.m_lfYCoordVertexesNew[b1], 100, 100, 0);
                    pScene.AddDebugLine(Polygon2.m_lfXCoordVertexesNew[a1],Polygon2.m_lfYCoordVertexesNew[a1],Polygon2.m_lfXCoordVertexesNew[b1],Polygon2.m_lfYCoordVertexesNew[b1], 100, 100, 0);
                    
                    return true;
                }
            }
        }
        return false;
    }
    static boolean calculateCollisionPolygon1ToPolygon2SidesIntersection(CScene pScene, CPolygon Polygon1, CPolygon Polygon2){

        CPointCartesianCoord pPointOfDetectedIntersection=new CPointCartesianCoord();
        boolean bIsIntersection=isIntersection(pScene,Polygon1,Polygon2,pPointOfDetectedIntersection);
        
//        boolean bIsIntersection=false;
//        for (int lVertex1 = 0; lVertex1 < Polygon1.m_lNumOfVertex-1; lVertex1++) {
//            for (int lVertex2 = 0; lVertex2 < Polygon2.m_lNumOfVertex-1; lVertex2++) {
//               // System.out.println("Testing intersection Polygon 1 vertex:"+lVertex1+"Polygon 2 vertex:"+lVertex2);
//                if ( CMath.isIntersection(Polygon1.m_lfXCoordVertexes[lVertex1],Polygon1.m_lfYCoordVertexes[lVertex1],Polygon1.m_lfXCoordVertexes[lVertex1+1],Polygon1.m_lfYCoordVertexes[lVertex1+1],Polygon2.m_lfXCoordVertexes[lVertex2],Polygon2.m_lfYCoordVertexes[lVertex2],Polygon2.m_lfXCoordVertexes[lVertex2+1],Polygon2.m_lfYCoordVertexes[lVertex2+1],
//                        pPointCoor)){
//                    pScene.m_pPhysEngRunnableGameCanvas.m_pScene.AddDebugLine(Polygon1.m_lfXCoordVertexes[lVertex1],Polygon1.m_lfYCoordVertexes[lVertex1],Polygon1.m_lfXCoordVertexes[lVertex1+1],Polygon1.m_lfYCoordVertexes[lVertex1+1], 100, 100, 0);
//                    pScene.m_pPhysEngRunnableGameCanvas.m_pScene.AddDebugLine(Polygon2.m_lfXCoordVertexes[lVertex1],Polygon2.m_lfYCoordVertexes[lVertex1],Polygon2.m_lfXCoordVertexes[lVertex1+1],Polygon2.m_lfYCoordVertexes[lVertex1+1], 100, 100, 0);
//                    bIsIntersection=true;
//                    break;
//                }
//            }
//            if(true==bIsIntersection)
//                break;
//        }

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

        CCollision alreadyCollision=pScene.AreAlreadyColliding(Polygon1, Polygon2);
        
        if(bIsIntersection){
            if(null==alreadyCollision ){
               //("Creating collision");

                float fAngleOfRepulsion1 = CMath.GetVectorAngle(Polygon2.getXNew(), Polygon2.getYNew(), Polygon1.getXNew(), Polygon1.getYNew());
                if (fAngleOfRepulsion1 > 360) {
                    fAngleOfRepulsion1 = fAngleOfRepulsion1 - 360;
                }
                //System.out.println("lAngleOfRepulsion1: "+lAngleOfRepulsion1);
                float fAngleOfRepulsion2 = fAngleOfRepulsion1 + 180;
                if (fAngleOfRepulsion2 > 360) {
                    fAngleOfRepulsion2 = fAngleOfRepulsion2 - 360;
                }

                //Relative coordinates of the collision to gravity centre of the polygon1:
                //Module:
                float CollAngle1 = CMath.GetVectorAngle(Polygon1.getXNew(), Polygon1.getYNew(), pPointOfDetectedIntersection.m_fX, pPointOfDetectedIntersection.m_fY);
                float CollXRel1 = pPointOfDetectedIntersection.m_fX - Polygon1.getXNew();
                float CollYRel1 = pPointOfDetectedIntersection.m_fY - Polygon1.getYNew();
                float CollModRel1 = (int) (((float) CollXRel1) * CMath.cosine(CollAngle1) + ((float) CollYRel1) * CMath.sine(CollAngle1));

               //Relative coordinates of the collision to gravity centre of the polygon2:
                //Module:
                float CollAngle2 = CMath.GetVectorAngle(Polygon2.getXNew(), Polygon2.getYNew(), pPointOfDetectedIntersection.m_fX, pPointOfDetectedIntersection.m_fY);
                float CollXRel2 = pPointOfDetectedIntersection.m_fX - Polygon2.getXNew();
                float CollYRel2 = pPointOfDetectedIntersection.m_fY - Polygon2.getYNew();
                float CollModRel2 = (int) (((float) CollXRel2) * CMath.cosine(CollAngle2) + ((float) CollYRel2) * CMath.sine(CollAngle2));

                CCollisionData CollisionDataPolygon1 = new CCollisionData(fAngleOfRepulsion2, fAngleOfRepulsion1, CollModRel1, CollAngle1);
                CCollisionData CollisionDataPolygon2 = new CCollisionData(fAngleOfRepulsion1, fAngleOfRepulsion2, CollModRel2, CollAngle2);

                CCollision Collision = new CCollision(pScene.m_pPhysEngRunnableGameCanvas, Polygon1,Polygon2,CollisionDataPolygon1,CollisionDataPolygon2);
                //m_vCollisions.addElement(Collision);
                pScene.addCollision(Collision);
                //System.out.println("Collision added");
            }
            return true;
        }
        else{
            //System.out.println("removing collision");
//            if(null!=alreadyCollision ){
//                //m_vCollisions.removeElementAt(alreadyCollision);
//                pScene.removeCollision(alreadyCollision);
//            }
            return false;
        }
    }
    
    
    /**
     * Collisions of Points with Polygons are evaluated when this method
     * is called
     */
    static void calculateCollision(CScene pScene,CPolygon Polygon,CPoint Point) {
       
        if(false==CMath.testCollisionProximity(Polygon, Point.getXNew(),Point.getYNew())){
            if(false==CMath.testCollisionProximity(Polygon, Point.getX(),Point.getY())){
                return;
            }
        }
        
        
        //System.out.println("Calculating collision of Point "+Point.m_lID+" with Polygon "+m_lID);
        //System.out.println("AreAlreadyColliding?");
        boolean bAreAlreadyColliding = false;
        //int lCollisionIndex = -1;

        CCollision AlreadyCollision=pScene.arePointAndPolygonAlreadyColliding(Point, Polygon);
        if (null!=AlreadyCollision){
            bAreAlreadyColliding = true;
        }

        /*
        for (int lCollision = 0; lCollision < m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.size(); lCollision++) {
            CCollision Collision = ((CCollision) (m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.elementAt(lCollision)));
            if (Collision.AreAlreadyColliding(Point,this)) {
                //System.out.println("AlreadyColliding");
                bAreAlreadyColliding = true;
                lCollisionIndex = lCollision;
                break;
            }
        }
        */

        //System.out.println("IsAnyTriangleColliding?");
        boolean bIsAnyTriangleColliding = false;
        //for (int lTriangle = 0; lTriangle < Polygon.m_lNumOfVertex / 3; lTriangle++) {
        CPointCartesianCoord PointCoord = new CPointCartesianCoord(Point.getXNew(), Point.getYNew());
        CPointCartesianCoord PointCoordOld = new CPointCartesianCoord(Point.getX(), Point.getY());
        CCollisionData CollisionData = new CCollisionData();

        //System.out.println("trying isPointInsideTriangle?");

        if (calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(pScene,Polygon,PointCoord, PointCoordOld, CollisionData)) {
            //System.out.println("true from calculateNewCollision(PointCoord, PointCoordOld, Polygon, CollisionData");
            bIsAnyTriangleColliding = true;
            if (false == bAreAlreadyColliding) {

                CCollision Collision = new CCollision(pScene.m_pPhysEngRunnableGameCanvas, Point, Polygon, CollisionData);
                //m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.addElement(Collision);
                pScene.addCollision(Collision);
                //break;//Only one  triangle can be collided at the same time
            }
        }
        //else if (isPointInsideTriangle(PointCoord)) {
        else if(CMath.isInsideTriangle(Polygon.m_lfXCoordVertexesNew[0], Polygon.m_lfYCoordVertexesNew[0], Polygon.m_lfXCoordVertexesNew[1], Polygon.m_lfYCoordVertexesNew[1], Polygon.m_lfXCoordVertexesNew[2], Polygon.m_lfYCoordVertexesNew[2], PointCoord.m_fX, PointCoord.m_fY)){
            //System.out.println("PointCollidingWithTriangle");
            bIsAnyTriangleColliding = true;
            if (false == bAreAlreadyColliding) {
                //System.out.println("trying calculateNewCollision(PointCoord, PointCoordOld, Polygon);");
                //CollisionData = calculateNewCollisionWhenPointInside(pScene,Polygon,PointCoord, PointCoordOld);
                calculateNewCollisionWhenPointInside(pScene,Polygon,PointCoord, PointCoordOld, CollisionData);

                CCollision Collision = new CCollision(pScene.m_pPhysEngRunnableGameCanvas, Point, Polygon, CollisionData);
                //m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.addElement(Collision);
                pScene.addCollision(Collision);
                //break;//Only one  triangle can be collided at the same time
            }
        }
        else
        {
            //Other type of collision... to do.
            //System.out.println("other type of collision");
        }

        //}
        if (false == bIsAnyTriangleColliding && true == bAreAlreadyColliding) {
            //System.out.println("removing collision at: "+ lCollisionIndex);
            //m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.removeCollisionAt(lCollisionIndex);
            pScene.removeCollision(AlreadyCollision);
        }
    }
        /**
 * The collision data of a collision between a Side of a Polygon and the line formed by the Points PointCoord and PointCoordOld is obtained.
 *
 */
    static void calculateCollisionWithSideOfPolygon(CScene pScene,CPolygon Polygon, int lSideOfCollisionIndex, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CCollisionData CollisionData) {

        float fAngleOfRepulsion1 = Polygon.getRepulsionAngleOfSideInDegrees(lSideOfCollisionIndex);
        float fAngleOfRepulsion2 = fAngleOfRepulsion1+180.0f;
        if (fAngleOfRepulsion2>360.0f)
            fAngleOfRepulsion2=fAngleOfRepulsion2-360.0f;
            
        

        //Relative coordinates of the collision to gravity centre of the polygon:
        //Module:
        float CollAngle = CMath.GetVectorAngle(Polygon.getX(), Polygon.getY(), PointCoordOld.m_fX, PointCoordOld.m_fY);
        float CollXRel = PointCoordOld.m_fX - Polygon.getX();
        float CollYRel = PointCoordOld.m_fY - Polygon.getY();
        float CollModRel = (int) (((float) CollXRel) * CMath.cosine(CollAngle) + ((float) CollYRel) * CMath.sine(CollAngle));

        //System.out.println("New Collision");
        //CCollision Collision=new CCollision(this, Point,lAngleOfRepulsion1,Polygon,lAngleOfRepulsion2,CollModRel,CollAngle);
        //atención, si ya están colisionando esos dos objetos, ya no debería añadir nuevos elemmentos a la lista
        //Necesito un metodo AreAlreadyColliding para ponerlo al principio del método
        
        CollisionData.init(fAngleOfRepulsion1, fAngleOfRepulsion2, CollModRel, CollAngle);
        //CCollisionData CollisionData = new CCollisionData(fAngleOfRepulsion1, fAngleOfRepulsion2, CollModRel, CollAngle);
        //return CollisionData;
    }
        /**
 * The collision data of a collision between a Side of a Polygon and the line formed by the Points PointCoord and PointCoordOld is obtained.
 * To do:There is other method with the same function. So, one of this methos is not needed.
 */
//    static void calculateCollisionWithSideOfPolygon(CScene pScene, CPolygon Polygon,int lSide, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CCollisionData CollisionData) {
//        //System.out.println("calculateCollisionWithSideOfPolygon(int lSide, int lTriangle, CPolygon Polygon, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld) {");
//        float[] fXVertexes = new float[3];
//        float[] fYVertexes = new float[3];
//        Polygon.getNewVertexesOfTriangle( fXVertexes, fYVertexes);
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
//        float CollAngle = CMath.GetVectorAngle(Polygon.getXNew(), Polygon.getYNew(), PointCoordOld.m_fX, PointCoordOld.m_fY);
//        float CollXRel = PointCoord.m_fX - Polygon.getXNew();
//        float CollYRel = PointCoord.m_fY - Polygon.getYNew();
//        float CollModRel = (int) (((float) CollXRel) * CMath.cosine(CollAngle) + ((float) CollYRel) * CMath.sine(CollAngle));
//
//        //System.out.println("New Collision. Total number of collisions = " + m_pPhysEngRunnableGameCanvas.m_Scene.m_vCollisions.size());
//        //CCollision Collision=new CCollision(this, Point,lAngleOfRepulsion1,Polygon,lAngleOfRepulsion2,CollModRel,CollAngle);
//        //atención, si ya están colisionando esos dos objetos, ya no debería añadir nuevos elemmentos a la lista
//        //Necesito un metodo AreAlreadyColliding para ponerlo al principio del método
//        CollisionData.init(fAngleOfRepulsion1, fAngleOfRepulsion2, CollModRel, CollAngle);
//        //return CollisionData;
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
//    static boolean calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(CScene pScene,CPolygon Polygon, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CCollisionData CollisionData) {
//        //System.out.println("Colisión");
//
//        //Con quién colisiona?? Qué linea del poligono?
//        float fLinePointAx = PointCoordOld.m_fX;
//        float fLinePointAy = PointCoordOld.m_fY;
//        float fLinePointBx = PointCoord.m_fX;
//        float fLinePointBy = PointCoord.m_fY;
//
//        float fSquarelDistanceFromAPointToCutPoint = -1;
//        
//        float fSideAxNearestCut = 0;
//        float fSideAyNearestCut = 0;
//        float fSideBxNearestCut = 0;
//        float fSideByNearestCut = 0;
//
//        boolean bIsThereAnyCut = false;
//        for (int lSide = 0; lSide < Polygon.m_lNumOfVertex; lSide++) {
//            float fModRelVertexA = Polygon.m_lModRelVertexes[lSide];
//            float fSideAx = Polygon.getXNew() + (fModRelVertexA * CMath.cosine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[lSide])));
//            float fSideAy = Polygon.getYNew() + (fModRelVertexA * CMath.sine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[lSide])));
//
//            float fSideBx;
//            float fSideBy;
//
//            if (lSide == Polygon.m_lNumOfVertex - 1) {
//                int lModRelVertexB = Polygon.m_lModRelVertexes[0];
//                fSideBx = Polygon.getXNew() + (lModRelVertexB * CMath.cosine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[0])));
//                fSideBy = Polygon.getYNew() + (lModRelVertexB * CMath.sine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[0])));
//            } else {
//                int lModRelVertexB = Polygon.m_lModRelVertexes[lSide + 1];
//                fSideBx = Polygon.getXNew() + (lModRelVertexB * CMath.cosine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[lSide + 1])));
//                fSideBy = Polygon.getYNew() + (lModRelVertexB * CMath.sine((Polygon.m_fDegree + Polygon.m_lAngRelVertexes[lSide + 1])));
//            }
//
//
//            CPointCartesianCoord CutPointCoord = new CPointCartesianCoord(0, 0);
//            
//            if (CMath.isIntersection(fLinePointAx,fLinePointAy,fLinePointBx,fLinePointBy,fSideAx,fSideAy,fSideBx,fSideBy,CutPointCoord)) {
//                pScene.m_pPhysEngRunnableGameCanvas.m_pScene.AddDebugLine(fLinePointAx,fLinePointAy,fLinePointBx,fLinePointBy,255,0,0);
//                pScene.m_pPhysEngRunnableGameCanvas.m_pScene.AddDebugLine(fSideAx,fSideAy,fSideBx,fSideBy,0,255,0);
//                pScene.m_pPhysEngRunnableGameCanvas.m_pScene.AddDebugPoint(CutPointCoord.m_fX,CutPointCoord.m_fY,0,0,255);
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
//        calculateCollisionWithSideOfPolygon(pScene,Polygon,fSideAxNearestCut, fSideAyNearestCut, fSideBxNearestCut, fSideByNearestCut, PointCoord, PointCoordOld,CollisionData);
//        return bIsThereAnyCut;
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
     static boolean calculateNewCollisionSearchingCuttingTrajectoriesOfPoints(CScene pScene,CPolygon Polygon, CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CCollisionData CollisionData) {
        //System.out.println("Colisión");

        //Con quién colisiona?? Qué linea del poligono?

        float fSquarelDistanceFromAPointToCutPoint = -1;
        
//        float fSideAxNearestCut = 0;
//        float fSideAyNearestCut = 0;
//        float fSideBxNearestCut = 0;
//        float fSideByNearestCut = 0;
        
        CLine pLineOfSideOfNearestCut=null;
        int lSideOfNearestCut=-1;

        //boolean bIsThereAnyCut = false;
        for (int lSide = 0; lSide < Polygon.m_lNumOfVertex; lSide++) {
    
//            float fSideAx=Polygon.m_lfXCoordVertexes[lSide];
//            float fSideAy=Polygon.m_lfYCoordVertexes[lSide];

//            float fSideBx;
//            float fSideBy;
//                    
//            if (lSide == Polygon.m_lNumOfVertex - 1) {
//                fSideBx = Polygon.m_lfXCoordVertexes[0];
//                fSideBy = Polygon.m_lfYCoordVertexes[0];
//            } else {
//                fSideBx = Polygon.m_lfXCoordVertexes[lSide+1];
//                fSideBy = Polygon.m_lfYCoordVertexes[lSide+1];
//            }
            
            CLine pLineOfSide= Polygon.getSideOfPolygonNew(lSide);
                    
            CPointCartesianCoord CutPointCoord = new CPointCartesianCoord(0, 0);
            
            if (CMath.isIntersection(PointCoordOld.m_fX,PointCoordOld.m_fY,PointCoord.m_fX,PointCoord.m_fY,
                    pLineOfSide.m_pPointA.m_fX,pLineOfSide.m_pPointA.m_fY,pLineOfSide.m_pPointB.m_fX,pLineOfSide.m_pPointB.m_fY,
                    CutPointCoord)) {
                pScene.AddDebugLine(PointCoordOld.m_fX,PointCoordOld.m_fY,PointCoord.m_fX,PointCoord.m_fY,
                        255,0,0);
                pScene.AddDebugLine(pLineOfSide.m_pPointA.m_fX,pLineOfSide.m_pPointA.m_fY,pLineOfSide.m_pPointB.m_fX,pLineOfSide.m_pPointB.m_fY,
                        0,255,0);
                pScene.AddDebugPoint(CutPointCoord.m_fX,CutPointCoord.m_fY,0,0,255);
                //System.out.println("cutting point "+CutPointCoord.m_fX+" "+CutPointCoord.m_fY);

                //bIsThereAnyCut = true;
                float fDistanceFromAPointToThisSideCutPoint = (CutPointCoord.m_fX - PointCoordOld.m_fX) * (CutPointCoord.m_fX - PointCoordOld.m_fX) + (CutPointCoord.m_fY - PointCoordOld.m_fY) * (CutPointCoord.m_fY - PointCoordOld.m_fY);
                if (fSquarelDistanceFromAPointToCutPoint == -1 || (fDistanceFromAPointToThisSideCutPoint < fSquarelDistanceFromAPointToCutPoint)) {
                    fSquarelDistanceFromAPointToCutPoint = fDistanceFromAPointToThisSideCutPoint;
                    //PointCoord = CutPointCoord;
//                    fSideAxNearestCut = fSideAx;
//                    fSideAyNearestCut = fSideAy;
//                    fSideBxNearestCut = fSideBx;
//                    fSideByNearestCut = fSideBy;
                    
                    pLineOfSideOfNearestCut=pLineOfSide;
                    lSideOfNearestCut=lSide;
                }
            }
        }

        if(null!=pLineOfSideOfNearestCut)
        {
            calculateCollisionWithSideOfPolygon(pScene,Polygon,lSideOfNearestCut,
                    PointCoord, PointCoordOld,CollisionData);
        }
        
        return null!=pLineOfSideOfNearestCut;

    }
    /**
     * Is used when you already know that there is a collision between PointCoord and
     * a triangle of a Polygon to get the data associated to the collision
     * */
    static void calculateNewCollisionWhenPointInside(CScene pScene, CPolygon Polygon,CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CCollisionData CollisionData) {
/*
        System.out.println("calculateNewCollisionSearchingCuttingTrajectoriesOfPoints X en triangle" + lTriangle);
        System.out.println("Point.m_lX: " + PointCoord.m_fX);
        System.out.println("Point.m_lXOld: " + PointCoordOld.m_fX);
        System.out.println("----");
*/
        //System.out.println("inside private CCollisionData calculateNewCollision(CPointCartesianCoord PointCoord, CPointCartesianCoord PointCoordOld, CPolygon Polygon) {");

//        float[] fXVertexes = new float[3];
//        float[] fYVertexes = new float[3];
//        Polygon.getNewVertexesOfTriangle(fXVertexes, fYVertexes);

        //Con quién colisiona?? Qué linea del poligono?
//        float fLinePointAx = PointCoordOld.m_fX;
//        float fLinePointAy = PointCoordOld.m_fY;
//        float fLinePointBx = PointCoord.m_fX;
//        float fLinePointBy = PointCoord.m_fY;

        //This means that the point is not colliding to the polygon,
        //so, it's the polygon that is colliding to the point

        //Utilizar aquí el método calculateCutPointOfTwoLines en vez de lo que hay a continuación

        float fAngleAbsFromPolygonToPoint = CMath.GetVectorAngle(Polygon.getX(), Polygon.getY(), PointCoordOld.m_fX, PointCoordOld.m_fY);

        float fAngleRelFromPolygonToPoint = fAngleAbsFromPolygonToPoint - Polygon.m_fDegreeOld;

        int lSide = Polygon.getTheSideAssociatedToThisAngle(fAngleRelFromPolygonToPoint);

        //System.out.println("before calculateCollisionWithSideOfPolygon(lSide, Polygon, PointCoord, PointCoordOld);");
        CMathPolygonCollision.calculateCollisionWithSideOfPolygon(pScene,Polygon,lSide, PointCoord, PointCoordOld, CollisionData);

    }
    
}
