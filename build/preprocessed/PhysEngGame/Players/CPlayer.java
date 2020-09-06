/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame.Players;

import PhysEngGame.*;

/**
 *
 * @author Carlos
 */
public class CPlayer extends CPolygon {


    long m_lMinCountForRepeat=3;
    long m_lCountForRepeat=0;

    float m_fModRelShootPoint=600;
    float m_fAngRelShootPoint=0;

    public CPlayer(PhysEngRunnableGameCanvas pPruebasGameCanvas){
        super(pPruebasGameCanvas);
        initPlayer();
     
    }

    public CPlayer(PhysEngRunnableGameCanvas pPruebasGameCanvas, float fX, float fY, float fDegree){
        super(pPruebasGameCanvas);
        initPlayer();
        //States
        //m_fX=fX;
        //m_fY=fY;
        //m_fXOld=fX;
        //m_fYOld=fY;
        this.setInitPos(fX, fY);
        m_fDegree=fDegree;
        m_fDegreeOld=fDegree;
    }
    private void initPlayer(){
       //Params:
        m_fMass=1.0f;
        //m_fMomentOfInertia=4000000;//400000000f;
        m_fMomentOfInertia=200000;
        m_lNumOfVertex=3;

        //Params:
        m_lModRelVertexes[0]=500;
        m_lAngRelVertexes[0]=200;
        m_lModRelVertexes[1]=500;
        m_lAngRelVertexes[1]=0;
        m_lModRelVertexes[2]=500;
        m_lAngRelVertexes[2]=160;
        m_bHandControlled=true;
        m_bCollisionable=true;
    }
    public void control(){
        if (true == m_bHandControlled) {

            float fControlAcceleration = 0.8f;
            int lControlAngularAcceleration = 5;
            float fVAngularControl = 0.1f;
            if (m_pPhysEngRunnableGameCanvas.isLeftPressed()) {
                //m_fVAngular = m_fVAngularOld + fVAngularControl;
                ApplyMomentOfForce(500);
            }
            if (m_pPhysEngRunnableGameCanvas.isRightPressed()) {
                //m_fVAngular = m_fVAngularOld - fVAngularControl;
                ApplyMomentOfForce(-500);
            }
            if (m_pPhysEngRunnableGameCanvas.isDownLeftPressed()) {
//                m_fVx = m_fVxOld + ((float) fControlAcceleration) * CMath.cosine((int)m_fDegreeOld+90f);
//                m_fVy = m_fVyOld + ((float) fControlAcceleration) * CMath.sine((int)m_fDegreeOld+90f);
                this.ApplyRelForce(90f, 2f);
            }
            if (m_pPhysEngRunnableGameCanvas.isDownRightPressed()) {
//                m_fVx = m_fVxOld + ((float) fControlAcceleration) * CMath.cosine((int)m_fDegreeOld-90f);
//                m_fVy = m_fVyOld + ((float) fControlAcceleration) * CMath.sine((int)m_fDegreeOld-90f);
                this.ApplyRelForce(-90f, 2f);
            }
            if (m_pPhysEngRunnableGameCanvas.isUpPressed()) {                
//                m_fVx = m_fVxOld + ((float) fControlAcceleration) * CMath.cosine((int)m_fDegreeOld);
//                m_fVy = m_fVyOld + ((float) fControlAcceleration) * CMath.sine((int)m_fDegreeOld);
                this.ApplyRelForce(0f, 2f);
            }
            if (m_pPhysEngRunnableGameCanvas.isDownPressed()) {
                this.ApplyRelForce(180f, 2f);
            }

            m_lCountForRepeat++;
            if (m_pPhysEngRunnableGameCanvas.isCentrePressed() && m_lCountForRepeat>m_lMinCountForRepeat) {
                m_pPhysEngRunnableGameCanvas.getScene().shoot((CPolygon)this,m_fModRelShootPoint,m_fAngRelShootPoint);
                m_lCountForRepeat=0;
            }
        }
    }
    public void draw(){
        super.draw();
      //  System.out.println("O");
    }
    public void integrate(){
        super.integrate();
        nextStates();
        

    }
    public void nextStates(){
        //System.out.println("O");
    }
}
