/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CLine {
    public CPointCartesianCoord m_pPointA=null;
    public CPointCartesianCoord m_pPointB=null;
    public CLine()
    {

    }
    public CLine(CPointCartesianCoord pPointA, CPointCartesianCoord pPointB)
    {
        m_pPointA=pPointA;
        m_pPointB=pPointB;
    }
}