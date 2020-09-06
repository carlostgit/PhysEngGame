/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CGameInfo {
    
    static int m_lScore=0;
    static int m_lLifes=0;
    static float m_lfLifePoints=0.0f;
    static int m_lStageIndex=0;
    static String m_sStageName="No name";

    public static void addScore(int lScore)
    {
        m_lScore=m_lScore + lScore;        
    }
    public static int getScore()
    {
        return m_lScore;
    }
    public static void reset(){
        m_lScore=0;
        m_lLifes=0;
        m_lfLifePoints=0.0f;
        m_lStageIndex=0;
        m_sStageName="No name";

    }            
    public static void setLifes(int lLifes){
        m_lLifes=lLifes;
    }
    public static int getLifes(){
        return m_lLifes;
    }
    public static void setLifePoints(float lfLifePoints){
        m_lfLifePoints=lfLifePoints;
    }
    public static float getLifePoints(){
        return m_lfLifePoints;
    }
    public static void setStageIndex(int lStageIndex){
        m_lStageIndex=lStageIndex;
    }
    public static int getStageIndex(){
        return m_lStageIndex;
    }
    public static void setStageName(String sStageName){
        m_sStageName=sStageName;
    }
    public static String getStageName(){
        return m_sStageName;
    }
}
