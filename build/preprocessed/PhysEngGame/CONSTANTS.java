/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CONSTANTS {
  public static final int NO_TYPE = 0;
  public static final int POLYGON = 1;
  public static final int POINT = 2;
  public static final int GROUP = 3;

  public static final boolean DEBUG_COLLISIONS = false;
  public static final boolean DEBUG_VIEW_COLLISION_TEST_RECTANGLE = false;
  public static final boolean TIMES_INFO = true; 

  public static final int SIMPLE_STAGE = 0;
  public static final int GROUP_STAGE = 1;
  
  public static final int NO_TYPE_OF_COLLIDING_OBJECT = 0;
  public static final int POLYGON_COLLIDING_WITH_A_SIDE = 1;
  public static final int POLYGON_COLLIDING_WITH_A_VERTEX = 2;
  public static final int POINT_COLLIDING = 3;
  
  public static final int NO_TYPE_OF_COLLISION = 0;
  public static final int POLYGON_COLLIDING_WITH_POINT = 1;
  public static final int VERTEX_OF_POLYGON_COLLIDING_WITH_POLYGON = 2;
  public static final int POLYGON_COLLIDING_WITH_POLYGON_WITHOUT_VERTEXES = 3;

  public static final boolean DECIMAL_ZOOM = true;
  
  public static final int NO_VERTEX = -1;
  
  public static final boolean TRY_COLLISIONS_WITHOUT_VERTEXES = true;
  public static final int EXTRA_ROOM_FOR_COLLISION_TEST_SQUARE = 100;
  
  public static final float PI = 3.14159265f;
  public static final float DEGREES_TO_RADIANS = (3.14159265f/180f);
  public static final float RADIANS_TO_DEGREES = (180f/3.14159265f);
  
  //Types of enemies
  public static final int SIMPLE_ROCK = 1;
  
  //Modes of appearance of teams
  public static final int ORDER_APPEARANCE_AFTER_PREVIOUS_IN_RANDOM_PLACE = 1;
  
  //Graphics mode
  public static final boolean USE_MY_GRAPHICS = false;
  
  //CCamera
  public static final boolean CONSIDER_ABERRATION_OF_LATERAL_DISTANCE = false; //se tiene en cuenta el efecto de abombamiento típico de cuando miras un edificio (u otra cosa recta) con un ojo de pez. Tiene sentido, porque el angulo de la camara virtual será mucho mayor que el ángulo con el que verá el ojo del usuario la pantalla del dispositivo.
  
  //Math
  public static final boolean USE_MY_MATH = false;
 
  //Stages
  public static final int DEFAULT_STAGE = 1;
  public static final int STAGE_1 = 2;
  public static final int STAGE_2 = 3;
  
  //Foreground Text
  public static final boolean SHOW_SCORE=true;
  
}
