/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CPoint extends CObject {
    //Param
    long m_lMaxCount=50;

            
    //States
    long m_lCyclesCount=0;

    public CPoint(PhysEngRunnableGameCanvas pPruebasGameCanvas)
    {
        super(pPruebasGameCanvas);
        m_lType=CONSTANTS.POINT; //Type 2 = CPoint
        
    }
    public void control(){
        if (m_lCyclesCount > m_lMaxCount) {
            m_pPhysEngRunnableGameCanvas.m_pScene.destroyThisObjectFromPolygonsCollisionsAndPointsVectors((CObject)this);
        }
        m_lCyclesCount++;

    }

}
