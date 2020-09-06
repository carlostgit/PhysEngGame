/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CDebugPoint {

    int m_lCount=200;
    float m_fAX=0f;
    float m_fAY=0f;
    int m_lR=0;
    int m_lG=0;
    int m_lB=0;
    boolean m_bPermanent=false;

    public CDebugPoint(float fAX,float fAY,int r,int g,int b){
        m_fAX=fAX;
        m_fAY=fAY;
        m_lR=r;
        m_lG=g;
        m_lB=b;

    }
    public void draw(PhysEngRunnableGameCanvas pPruebasGameCanvas){
        if(m_lCount>=0){
            float width=100f;
            float height=100f;
            pPruebasGameCanvas.setColor(m_lR, m_lG, m_lB);
            pPruebasGameCanvas.m_pScene.getCamera().fillRect(m_fAX, m_fAY,width,height);
            if (false==m_bPermanent)
                m_lCount--;
        }
    }
    public void setPermanent(){
        m_bPermanent=true;
    }
    public boolean hasToBeDestroyed(){
        if(m_lCount<0)
            return true;
        else
            return false;
    }

}
