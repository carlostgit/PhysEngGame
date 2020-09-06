/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CCollisionData {
    public float m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon=0;
    public float m_fAngleOfRepulsionOfPolygon=0;
    public float m_fCollPositionModRel=0;
    public float m_fCollPositionAngle=0;

    public CCollisionData()
    {
    }
    public CCollisionData(float fAngleOfRepulsionOfAnythingCollidingWithThePolygon, float fAngleOfRepulsionOfPolygon, float fCollPolygonModRel, float fCollPolygonAngle)
    {
//        m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon=fAngleOfRepulsionOfAnythingCollidingWithThePolygon;
//        m_fAngleOfRepulsionOfPolygon=fAngleOfRepulsionOfPolygon;
//        m_fCollPositionModRel=fCollPolygonModRel;
//        m_fCollPositionAngle=fCollPolygonAngle;
        init(fAngleOfRepulsionOfAnythingCollidingWithThePolygon,fAngleOfRepulsionOfPolygon,fCollPolygonModRel,fCollPolygonAngle);
    }
    public void init(float fAngleOfRepulsionOfAnythingCollidingWithThePolygon, float fAngleOfRepulsionOfPolygon, float fCollPolygonModRel, float fCollPolygonAngle){
        m_fAngleOfRepulsionOfAnythingCollidingWithThePolygon=fAngleOfRepulsionOfAnythingCollidingWithThePolygon;
        m_fAngleOfRepulsionOfPolygon=fAngleOfRepulsionOfPolygon;
        m_fCollPositionModRel=fCollPolygonModRel;
        m_fCollPositionAngle=fCollPolygonAngle;
    }
}   