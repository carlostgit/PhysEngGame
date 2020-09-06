/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysEngGame;

/**
 *
 * @author Carlos
 */
public class CMath {
    static float[] sineArray = new float[] {
        0.0f,
        0.0174524064373f,
        0.0348994967025f,
        0.0523359562429f,
        0.0697564737441f,
        0.0871557427477f,
        0.104528463268f,
        0.121869343405f,
        0.13917310096f,
        0.15643446504f,
        0.173648177667f,
        0.190808995377f,
        0.207911690818f,
        0.224951054344f,
        0.2419218956f,
        0.258819045103f,
        0.275637355817f,
        0.292371704723f,
        0.309016994375f,
        0.325568154457f,
        0.342020143326f,
        0.358367949545f,
        0.374606593416f,
        0.390731128489f,
        0.406736643076f,
        0.422618261741f,
        0.438371146789f,
        0.45399049974f,
        0.469471562786f,
        0.484809620246f,
        0.5f,
        0.51503807491f,
        0.529919264233f,
        0.544639035015f,
        0.559192903471f,
        0.573576436351f,
        0.587785252292f,
        0.601815023152f,
        0.615661475326f,
        0.62932039105f,
        0.642787609687f,
        0.656059028991f,
        0.669130606359f,
        0.681998360062f,
        0.694658370459f,
        0.707106781187f,
        0.719339800339f,
        0.731353701619f,
        0.743144825477f,
        0.754709580223f,
        0.766044443119f,
        0.777145961457f,
        0.788010753607f,
        0.798635510047f,
        0.809016994375f,
        0.819152044289f,
        0.829037572555f,
        0.838670567945f,
        0.848048096156f,
        0.857167300702f,
        0.866025403784f,
        0.874619707139f,
        0.882947592859f,
        0.891006524188f,
        0.898794046299f,
        0.906307787037f,
        0.913545457643f,
        0.920504853452f,
        0.927183854567f,
        0.933580426497f,
        0.939692620786f,
        0.945518575599f,
        0.951056516295f,
        0.956304755963f,
        0.961261695938f,
        0.965925826289f,
        0.970295726276f,
        0.974370064785f,
        0.978147600734f,
        0.981627183448f,
        0.984807753012f,
        0.987688340595f,
        0.990268068742f,
        0.992546151641f,
        0.994521895368f,
        0.996194698092f,
        0.99756405026f,
        0.998629534755f,
        0.999390827019f,
        0.999847695156f,
        1.0f    
    };

    static float[] cosineArray = new float[] {
        1.0f,
        0.999847695156f,
        0.999390827019f,
        0.998629534755f,
        0.99756405026f,
        0.996194698092f,
        0.994521895368f,
        0.992546151641f,
        0.990268068742f,
        0.987688340595f,
        0.984807753012f,
        0.981627183448f,
        0.978147600734f,
        0.974370064785f,
        0.970295726276f,
        0.965925826289f,
        0.961261695938f,
        0.956304755963f,
        0.951056516295f,
        0.945518575599f,
        0.939692620786f,
        0.933580426497f,
        0.927183854567f,
        0.920504853452f,
        0.913545457643f,
        0.906307787037f,
        0.898794046299f,
        0.891006524188f,
        0.882947592859f,
        0.874619707139f,
        0.866025403784f,
        0.857167300702f,
        0.848048096156f,
        0.838670567945f,
        0.829037572555f,
        0.819152044289f,
        0.809016994375f,
        0.798635510047f,
        0.788010753607f,
        0.777145961457f,
        0.766044443119f,
        0.754709580223f,
        0.743144825477f,
        0.731353701619f,
        0.719339800339f,
        0.707106781187f,
        0.694658370459f,
        0.681998360062f,
        0.669130606359f,
        0.656059028991f,
        0.642787609687f,
        0.62932039105f,
        0.615661475326f,
        0.601815023152f,
        0.587785252292f,
        0.573576436351f,
        0.559192903471f,
        0.544639035015f,
        0.529919264233f,
        0.51503807491f,
        0.5f,
        0.484809620246f,
        0.469471562786f,
        0.45399049974f,
        0.438371146789f,
        0.422618261741f,
        0.406736643076f,
        0.390731128489f,
        0.374606593416f,
        0.358367949545f,
        0.342020143326f,
        0.325568154457f,
        0.309016994375f,
        0.292371704723f,
        0.275637355817f,
        0.258819045103f,
        0.2419218956f,
        0.224951054344f,
        0.207911690818f,
        0.190808995377f,
        0.173648177667f,
        0.15643446504f,
        0.13917310096f,
        0.121869343405f,
        0.104528463268f,
        0.0871557427477f,
        0.0697564737441f,
        0.0523359562429f,
        0.0348994967025f,
        0.0174524064373f,
        0f

    };
    
    
    static float[] tanArray = new float[] {
        0.0f,
        0.0174550649282f,
        0.0349207694917f,
        0.052407779283f,
        0.0699268119435f,
        0.0874886635259f,
        0.105104235266f,
        0.122784560903f,
        0.140540834702f,
        0.158384440325f,
        0.176326980708f,
        0.194380309138f,
        0.21255656167f,
        0.230868191126f,
        0.249328002843f,
        0.267949192431f,
        0.286745385759f,
        0.305730681459f,
        0.324919696233f,
        0.34432761329f,
        0.363970234266f,
        0.383864035035f,
        0.404026225835f,
        0.42447481621f,
        0.445228685309f,
        0.466307658155f,
        0.487732588566f,
        0.509525449494f,
        0.531709431661f,
        0.554309051453f,
        0.57735026919f,
        0.600860619028f,
        0.624869351909f,
        0.649407593198f,
        0.674508516842f,
        0.70020753821f,
        0.726542528005f,
        0.753554050103f,
        0.781285626507f,
        0.809784033195f,
        0.839099631177f,
        0.869286737816f,
        0.900404044298f,
        0.932515086138f,
        0.965688774807f,
        1.0f,
        1.03553031379f,
        1.07236871002f,
        1.11061251483f,
        1.15036840722f,
        1.19175359259f,
        1.23489715654f,
        1.27994163219f,
        1.32704482162f,
        1.37638192047f,
        1.42814800674f,
        1.48256096851f,
        1.53986496381f,
        1.60033452904f,
        1.66427948235f,
        1.73205080757f,
        1.80404775527f,
        1.88072646535f,
        1.96261050551f,
        2.05030384158f,
        2.14450692051f,
        2.2460367739f,
        2.35585236582f,
        2.47508685342f,
        2.60508906469f,
        2.74747741945f,
        2.90421087768f,
        3.07768353718f,
        3.27085261848f,
        3.48741444384f,
        3.73205080757f,
        4.01078093354f,
        4.33147587428f,
        4.70463010948f,
        5.14455401597f,
        5.67128181962f,
        6.31375151468f,
        7.11536972238f,
        8.14434642797f,
        9.51436445422f,
        11.4300523028f,
        14.3006662567f,
        19.0811366877f,
        28.6362532829f,
        57.2899616308f,
        //1.63317787284e+016f
        Float.MAX_VALUE
    };
    
    
    
    
//El siguiente método tengo que asegurarme de que de un resultado positivo menor de 360
    static float getAngleSubstraction(float fAngle1, float fAngle2) {
        while (fAngle1 < 0) {
            fAngle1 = fAngle1 + 360;
        }
        while (fAngle1 > 360) {
            fAngle1 = fAngle1 - 360;
        }
        while (fAngle2 < 0) {
            fAngle2 = fAngle2 + 360;
        }
        while (fAngle2 > 360) {
            fAngle2 = fAngle2 - 360;
        }
        float fAngle = fAngle1 - fAngle2;
        while (fAngle < 0) {
            fAngle = fAngle + 360;
        }
        while (fAngle > 360) {
            fAngle = fAngle - 360;
        }

        return fAngle;
    }

     static float GetVectorAngle(float fPoint1x, float fPoint1y, float fPoint2x, float fPoint2y) {

        float fX = fPoint2x - fPoint1x;
        float fY = fPoint2y - fPoint1y;

        return GetVectorAngle(fX, fY);

    }

    static float GetVectorAngle(float fX, float fY) {

        float fAngle = 0;
        if (fX != 0 || fY != 0) {
            if (fX > 0 && fY > 0) {
                //1er cuadrante
                if (fX > fY) {
                    fAngle = AAbsTang((float) fY / (float) fX);
                } else {
                    fAngle = AAbsTang((float) fX / (float) fY);
                    fAngle = 90 - fAngle;
                }
            } else if (fX < 0 && fY > 0) {
                //2º cuadrante
                if (-fX > fY) {
                    fAngle = AAbsTang((float) fY / -(float) fX);
                } else {
                    fAngle = AAbsTang((float) fX / -(float) fY);
                    fAngle = 90 - fAngle;
                }

                fAngle = 180 - fAngle;
            } else if (fX < 0 && fY < 0) {
                //3er cuadrante
                if (-fX > -fY) {
                    fAngle = AAbsTang((float) fY / (float) fX);
                } else {
                    fAngle = AAbsTang((float) fX / (float) fY);
                    fAngle = 90 - fAngle;
                }

                fAngle = fAngle + 180;

            } else if (fX > 0 && fY < 0) {
                //4º cuadrante
                if (fX > -fY) {
                    fAngle = AAbsTang((float) fY / -(float) fX);
                } else {
                    fAngle = AAbsTang((float) fX / -(float) fY);
                    fAngle = 90 - fAngle;
                }

                fAngle = 360 - fAngle;

            } else if (fX == 0 || fY != 0) {
                if (fY >= 0) {
                    fAngle = 90;
                } else {
                    fAngle = 270;
                }
            } else if (fX != 0 || fX == 0) {
                if (fX >= 0) {
                    fAngle = 0;
                } else {
                    fAngle = 180;
                }
            }
        }

        return fAngle;
    }

    static float AAbsTang(float lfTan) {

        return aTanTable(lfTan);
        //return aTanTable(lfTan);
        /* 
        if (lfTan < 0) {
            lfTan = -lfTan;
        }

        float fAngle = 0;

        if (lfTan >= 0.0 && lfTan < 0.0174550649282) {
            fAngle = 0;
        } else if (lfTan >= 0.0174550649282 && lfTan < 0.0349207694917) {
            fAngle = 1;
        } else if (lfTan >= 0.0349207694917 && lfTan < 0.052407779283) {
            fAngle = 2;
        } else if (lfTan >= 0.052407779283 && lfTan < 0.0699268119435) {
            fAngle = 3;
        } else if (lfTan >= 0.0699268119435 && lfTan < 0.0874886635259) {
            fAngle = 4;
        } else if (lfTan >= 0.0874886635259 && lfTan < 0.105104235266) {
            fAngle = 5;
        } else if (lfTan >= 0.105104235266 && lfTan < 0.122784560903) {
            fAngle = 6;
        } else if (lfTan >= 0.122784560903 && lfTan < 0.140540834702) {
            fAngle = 7;
        } else if (lfTan >= 0.140540834702 && lfTan < 0.158384440325) {
            fAngle = 8;
        } else if (lfTan >= 0.158384440325 && lfTan < 0.176326980708) {
            fAngle = 9;
        } else if (lfTan >= 0.176326980708 && lfTan < 0.194380309138) {
            fAngle = 10;
        } else if (lfTan >= 0.194380309138 && lfTan < 0.21255656167) {
            fAngle = 11;
        } else if (lfTan >= 0.21255656167 && lfTan < 0.230868191126) {
            fAngle = 12;
        } else if (lfTan >= 0.230868191126 && lfTan < 0.249328002843) {
            fAngle = 13;
        } else if (lfTan >= 0.249328002843 && lfTan < 0.267949192431) {
            fAngle = 14;
        } else if (lfTan >= 0.267949192431 && lfTan < 0.286745385759) {
            fAngle = 15;
        } else if (lfTan >= 0.286745385759 && lfTan < 0.305730681459) {
            fAngle = 16;
        } else if (lfTan >= 0.305730681459 && lfTan < 0.324919696233) {
            fAngle = 17;
        } else if (lfTan >= 0.324919696233 && lfTan < 0.34432761329) {
            fAngle = 18;
        } else if (lfTan >= 0.34432761329 && lfTan < 0.363970234266) {
            fAngle = 19;
        } else if (lfTan >= 0.363970234266 && lfTan < 0.383864035035) {
            fAngle = 20;
        } else if (lfTan >= 0.383864035035 && lfTan < 0.404026225835) {
            fAngle = 21;
        } else if (lfTan >= 0.404026225835 && lfTan < 0.42447481621) {
            fAngle = 22;
        } else if (lfTan >= 0.42447481621 && lfTan < 0.445228685309) {
            fAngle = 23;
        } else if (lfTan >= 0.445228685309 && lfTan < 0.466307658155) {
            fAngle = 24;
        } else if (lfTan >= 0.466307658155 && lfTan < 0.487732588566) {
            fAngle = 25;
        } else if (lfTan >= 0.487732588566 && lfTan < 0.509525449494) {
            fAngle = 26;
        } else if (lfTan >= 0.509525449494 && lfTan < 0.531709431661) {
            fAngle = 27;
        } else if (lfTan >= 0.531709431661 && lfTan < 0.554309051453) {
            fAngle = 28;
        } else if (lfTan >= 0.554309051453 && lfTan < 0.57735026919) {
            fAngle = 29;
        } else if (lfTan >= 0.57735026919 && lfTan < 0.600860619028) {
            fAngle = 30;
        } else if (lfTan >= 0.600860619028 && lfTan < 0.624869351909) {
            fAngle = 31;
        } else if (lfTan >= 0.624869351909 && lfTan < 0.649407593198) {
            fAngle = 32;
        } else if (lfTan >= 0.649407593198 && lfTan < 0.674508516842) {
            fAngle = 33;
        } else if (lfTan >= 0.674508516842 && lfTan < 0.70020753821) {
            fAngle = 34;
        } else if (lfTan >= 0.70020753821 && lfTan < 0.726542528005) {
            fAngle = 35;
        } else if (lfTan >= 0.726542528005 && lfTan < 0.753554050103) {
            fAngle = 36;
        } else if (lfTan >= 0.753554050103 && lfTan < 0.781285626507) {
            fAngle = 37;
        } else if (lfTan >= 0.781285626507 && lfTan < 0.809784033195) {
            fAngle = 38;
        } else if (lfTan >= 0.809784033195 && lfTan < 0.839099631177) {
            fAngle = 39;
        } else if (lfTan >= 0.839099631177 && lfTan < 0.869286737816) {
            fAngle = 40;
        } else if (lfTan >= 0.869286737816 && lfTan < 0.900404044298) {
            fAngle = 41;
        } else if (lfTan >= 0.900404044298 && lfTan < 0.932515086138) {
            fAngle = 42;
        } else if (lfTan >= 0.932515086138 && lfTan < 0.965688774807) {
            fAngle = 43;
        } else if (lfTan >= 0.965688774807 && lfTan < 1.0) {
            fAngle = 44;
        } else if (lfTan >= 1.0 && lfTan < 1.03553031379) {
            fAngle = 45;
        } else if (lfTan >= 1.03553031379 && lfTan < 1.07236871002) {
            fAngle = 46;
        } else if (lfTan >= 1.07236871002 && lfTan < 1.11061251483) {
            fAngle = 47;
        } else if (lfTan >= 1.11061251483 && lfTan < 1.15036840722) {
            fAngle = 48;
        } else if (lfTan >= 1.15036840722 && lfTan < 1.19175359259) {
            fAngle = 49;
        } else if (lfTan >= 1.19175359259 && lfTan < 1.23489715654) {
            fAngle = 50;
        } else if (lfTan >= 1.23489715654 && lfTan < 1.27994163219) {
            fAngle = 51;
        } else if (lfTan >= 1.27994163219 && lfTan < 1.32704482162) {
            fAngle = 52;
        } else if (lfTan >= 1.32704482162 && lfTan < 1.37638192047) {
            fAngle = 53;
        } else if (lfTan >= 1.37638192047 && lfTan < 1.42814800674) {
            fAngle = 54;
        } else if (lfTan >= 1.42814800674 && lfTan < 1.48256096851) {
            fAngle = 55;
        } else if (lfTan >= 1.48256096851 && lfTan < 1.53986496381) {
            fAngle = 56;
        } else if (lfTan >= 1.53986496381 && lfTan < 1.60033452904) {
            fAngle = 57;
        } else if (lfTan >= 1.60033452904 && lfTan < 1.66427948235) {
            fAngle = 58;
        } else if (lfTan >= 1.66427948235 && lfTan < 1.73205080757) {
            fAngle = 59;
        } else if (lfTan >= 1.73205080757 && lfTan < 1.80404775527) {
            fAngle = 60;
        } else if (lfTan >= 1.80404775527 && lfTan < 1.88072646535) {
            fAngle = 61;
        } else if (lfTan >= 1.88072646535 && lfTan < 1.96261050551) {
            fAngle = 62;
        } else if (lfTan >= 1.96261050551 && lfTan < 2.05030384158) {
            fAngle = 63;
        } else if (lfTan >= 2.05030384158 && lfTan < 2.14450692051) {
            fAngle = 64;
        } else if (lfTan >= 2.14450692051 && lfTan < 2.2460367739) {
            fAngle = 65;
        } else if (lfTan >= 2.2460367739 && lfTan < 2.35585236582) {
            fAngle = 66;
        } else if (lfTan >= 2.35585236582 && lfTan < 2.47508685342) {
            fAngle = 67;
        } else if (lfTan >= 2.47508685342 && lfTan < 2.60508906469) {
            fAngle = 68;
        } else if (lfTan >= 2.60508906469 && lfTan < 2.74747741945) {
            fAngle = 69;
        } else if (lfTan >= 2.74747741945 && lfTan < 2.90421087768) {
            fAngle = 70;
        } else if (lfTan >= 2.90421087768 && lfTan < 3.07768353718) {
            fAngle = 71;
        } else if (lfTan >= 3.07768353718 && lfTan < 3.27085261848) {
            fAngle = 72;
        } else if (lfTan >= 3.27085261848 && lfTan < 3.48741444384) {
            fAngle = 73;
        } else if (lfTan >= 3.48741444384 && lfTan < 3.73205080757) {
            fAngle = 74;
        } else if (lfTan >= 3.73205080757 && lfTan < 4.01078093354) {
            fAngle = 75;
        } else if (lfTan >= 4.01078093354 && lfTan < 4.33147587428) {
            fAngle = 76;
        } else if (lfTan >= 4.33147587428 && lfTan < 4.70463010948) {
            fAngle = 77;
        } else if (lfTan >= 4.70463010948 && lfTan < 5.14455401597) {
            fAngle = 78;
        } else if (lfTan >= 5.14455401597 && lfTan < 5.67128181962) {
            fAngle = 79;
        } else if (lfTan >= 5.67128181962 && lfTan < 6.31375151468) {
            fAngle = 80;
        } else if (lfTan >= 6.31375151468 && lfTan < 7.11536972238) {
            fAngle = 81;
        } else if (lfTan >= 7.11536972238 && lfTan < 8.14434642797) {
            fAngle = 82;
        } else if (lfTan >= 8.14434642797 && lfTan < 9.51436445422) {
            fAngle = 83;
        } else if (lfTan >= 9.51436445422 && lfTan < 11.4300523028) {
            fAngle = 84;
        } else if (lfTan >= 11.4300523028 && lfTan < 14.3006662567) {
            fAngle = 85;
        } else if (lfTan >= 14.3006662567 && lfTan < 19.0811366877) {
            fAngle = 86;
        } else if (lfTan >= 19.0811366877 && lfTan < 28.6362532829) {
            fAngle = 87;
        } else if (lfTan >= 28.6362532829 && lfTan < 57.2899616308) {
            fAngle = 88;
        } else if (lfTan >= 57.2899616308 && lfTan < 1.63317787284e+016) {
            fAngle = 89;
        } else {
            fAngle = 90;
        }

        return fAngle;
        */
    }
    
    static float arcTang1and4quadrant(float fTan){
        float freturnAngle=AAbsTang(fTan);
        if(fTan<0){
            freturnAngle=-freturnAngle;
        }        
        return freturnAngle;
    }

    static boolean esImpar(int iNumero) {
        if (iNumero % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }
    static float SquareRoot(float fXMin, float fXMax, float fSquare, int lMaxNumberOfIterations, int lNumberOfIterationsDone) {
        lNumberOfIterationsDone++;

        //long fFMiddle = (fXMin + fXMax) >> 1;
        float fFMiddle=(fXMin+fXMax)/2.0f;
        float fTestSquare = fFMiddle * fFMiddle;

        if (lNumberOfIterationsDone >= lMaxNumberOfIterations) {
            return fFMiddle;
        }

        if (fTestSquare > fSquare) {
            return SquareRoot(fXMin,  fFMiddle, fSquare, lMaxNumberOfIterations, lNumberOfIterationsDone);
        } else {
            return SquareRoot( fFMiddle, fXMax, fSquare, lMaxNumberOfIterations, lNumberOfIterationsDone);
        }
    }
    static float SquareRoot(float fValue){
        //return SquareRoot(0, fValue, fValue, 30, 0);
        if(CONSTANTS.USE_MY_MATH)
            return SquareRoot(0, fValue, fValue, 30, 0);
        else
            return (float)Math.sqrt(fValue);
    }
    static float cosine(float fDegrees) {
        if(CONSTANTS.USE_MY_MATH)
            return cosineTable(fDegrees);
        else
            return ((float)Math.cos((float)(Math.toRadians(fDegrees))));
        //return cosineTable(fDegrees);
        /*
        //return cosineTable(fDegrees);
        if (fDegrees > 360) {
            fDegrees = fDegrees - 360;
        } else if (fDegrees < 0) {
            fDegrees = fDegrees + 360;
        }

        float fReturn;
        int lDegrees=(int)fDegrees;
        switch (lDegrees) {
            case 0:
                fReturn = 1.000000f;
                break;
            case 1:
                fReturn = 0.999848f;
                break;
            case 2:
                fReturn = 0.999391f;
                break;
            case 3:
                fReturn = 0.998630f;
                break;
            case 4:
                fReturn = 0.997564f;
                break;
            case 5:
                fReturn = 0.996195f;
                break;
            case 6:
                fReturn = 0.994522f;
                break;
            case 7:
                fReturn = 0.992546f;
                break;
            case 8:
                fReturn = 0.990268f;
                break;
            case 9:
                fReturn = 0.987688f;
                break;
            case 10:
                fReturn = 0.984808f;
                break;
            case 11:
                fReturn = 0.981627f;
                break;
            case 12:
                fReturn = 0.978148f;
                break;
            case 13:
                fReturn = 0.974370f;
                break;
            case 14:
                fReturn = 0.970296f;
                break;
            case 15:
                fReturn = 0.965926f;
                break;
            case 16:
                fReturn = 0.961262f;
                break;
            case 17:
                fReturn = 0.956305f;
                break;
            case 18:
                fReturn = 0.951057f;
                break;
            case 19:
                fReturn = 0.945519f;
                break;
            case 20:
                fReturn = 0.939693f;
                break;
            case 21:
                fReturn = 0.933580f;
                break;
            case 22:
                fReturn = 0.927184f;
                break;
            case 23:
                fReturn = 0.920505f;
                break;
            case 24:
                fReturn = 0.913545f;
                break;
            case 25:
                fReturn = 0.906308f;
                break;
            case 26:
                fReturn = 0.898794f;
                break;
            case 27:
                fReturn = 0.891007f;
                break;
            case 28:
                fReturn = 0.882948f;
                break;
            case 29:
                fReturn = 0.874620f;
                break;
            case 30:
                fReturn = 0.866025f;
                break;
            case 31:
                fReturn = 0.857167f;
                break;
            case 32:
                fReturn = 0.848048f;
                break;
            case 33:
                fReturn = 0.838671f;
                break;
            case 34:
                fReturn = 0.829038f;
                break;
            case 35:
                fReturn = 0.819152f;
                break;
            case 36:
                fReturn = 0.809017f;
                break;
            case 37:
                fReturn = 0.798636f;
                break;
            case 38:
                fReturn = 0.788011f;
                break;
            case 39:
                fReturn = 0.777146f;
                break;
            case 40:
                fReturn = 0.766044f;
                break;
            case 41:
                fReturn = 0.754710f;
                break;
            case 42:
                fReturn = 0.743145f;
                break;
            case 43:
                fReturn = 0.731354f;
                break;
            case 44:
                fReturn = 0.719340f;
                break;
            case 45:
                fReturn = 0.707107f;
                break;
            case 46:
                fReturn = 0.694658f;
                break;
            case 47:
                fReturn = 0.681998f;
                break;
            case 48:
                fReturn = 0.669131f;
                break;
            case 49:
                fReturn = 0.656059f;
                break;
            case 50:
                fReturn = 0.642788f;
                break;
            case 51:
                fReturn = 0.629320f;
                break;
            case 52:
                fReturn = 0.615661f;
                break;
            case 53:
                fReturn = 0.601815f;
                break;
            case 54:
                fReturn = 0.587785f;
                break;
            case 55:
                fReturn = 0.573576f;
                break;
            case 56:
                fReturn = 0.559193f;
                break;
            case 57:
                fReturn = 0.544639f;
                break;
            case 58:
                fReturn = 0.529919f;
                break;
            case 59:
                fReturn = 0.515038f;
                break;
            case 60:
                fReturn = 0.500000f;
                break;
            case 61:
                fReturn = 0.484810f;
                break;
            case 62:
                fReturn = 0.469472f;
                break;
            case 63:
                fReturn = 0.453990f;
                break;
            case 64:
                fReturn = 0.438371f;
                break;
            case 65:
                fReturn = 0.422618f;
                break;
            case 66:
                fReturn = 0.406737f;
                break;
            case 67:
                fReturn = 0.390731f;
                break;
            case 68:
                fReturn = 0.374607f;
                break;
            case 69:
                fReturn = 0.358368f;
                break;
            case 70:
                fReturn = 0.342020f;
                break;
            case 71:
                fReturn = 0.325568f;
                break;
            case 72:
                fReturn = 0.309017f;
                break;
            case 73:
                fReturn = 0.292372f;
                break;
            case 74:
                fReturn = 0.275637f;
                break;
            case 75:
                fReturn = 0.258819f;
                break;
            case 76:
                fReturn = 0.241922f;
                break;
            case 77:
                fReturn = 0.224951f;
                break;
            case 78:
                fReturn = 0.207912f;
                break;
            case 79:
                fReturn = 0.190809f;
                break;
            case 80:
                fReturn = 0.173648f;
                break;
            case 81:
                fReturn = 0.156434f;
                break;
            case 82:
                fReturn = 0.139173f;
                break;
            case 83:
                fReturn = 0.121869f;
                break;
            case 84:
                fReturn = 0.104528f;
                break;
            case 85:
                fReturn = 0.087156f;
                break;
            case 86:
                fReturn = 0.069756f;
                break;
            case 87:
                fReturn = 0.052336f;
                break;
            case 88:
                fReturn = 0.034899f;
                break;
            case 89:
                fReturn = 0.017452f;
                break;
            case 90:
                fReturn = 0.000000f;
                break;
            case 91:
                fReturn = -0.017452f;
                break;
            case 92:
                fReturn = -0.034899f;
                break;
            case 93:
                fReturn = -0.052336f;
                break;
            case 94:
                fReturn = -0.069756f;
                break;
            case 95:
                fReturn = -0.087156f;
                break;
            case 96:
                fReturn = -0.104528f;
                break;
            case 97:
                fReturn = -0.121869f;
                break;
            case 98:
                fReturn = -0.139173f;
                break;
            case 99:
                fReturn = -0.156434f;
                break;
            case 100:
                fReturn = -0.173648f;
                break;
            case 101:
                fReturn = -0.190809f;
                break;
            case 102:
                fReturn = -0.207912f;
                break;
            case 103:
                fReturn = -0.224951f;
                break;
            case 104:
                fReturn = -0.241922f;
                break;
            case 105:
                fReturn = -0.258819f;
                break;
            case 106:
                fReturn = -0.275637f;
                break;
            case 107:
                fReturn = -0.292372f;
                break;
            case 108:
                fReturn = -0.309017f;
                break;
            case 109:
                fReturn = -0.325568f;
                break;
            case 110:
                fReturn = -0.342020f;
                break;
            case 111:
                fReturn = -0.358368f;
                break;
            case 112:
                fReturn = -0.374607f;
                break;
            case 113:
                fReturn = -0.390731f;
                break;
            case 114:
                fReturn = -0.406737f;
                break;
            case 115:
                fReturn = -0.422618f;
                break;
            case 116:
                fReturn = -0.438371f;
                break;
            case 117:
                fReturn = -0.453990f;
                break;
            case 118:
                fReturn = -0.469472f;
                break;
            case 119:
                fReturn = -0.484810f;
                break;
            case 120:
                fReturn = -0.500000f;
                break;
            case 121:
                fReturn = -0.515038f;
                break;
            case 122:
                fReturn = -0.529919f;
                break;
            case 123:
                fReturn = -0.544639f;
                break;
            case 124:
                fReturn = -0.559193f;
                break;
            case 125:
                fReturn = -0.573576f;
                break;
            case 126:
                fReturn = -0.587785f;
                break;
            case 127:
                fReturn = -0.601815f;
                break;
            case 128:
                fReturn = -0.615661f;
                break;
            case 129:
                fReturn = -0.629320f;
                break;
            case 130:
                fReturn = -0.642788f;
                break;
            case 131:
                fReturn = -0.656059f;
                break;
            case 132:
                fReturn = -0.669131f;
                break;
            case 133:
                fReturn = -0.681998f;
                break;
            case 134:
                fReturn = -0.694658f;
                break;
            case 135:
                fReturn = -0.707107f;
                break;
            case 136:
                fReturn = -0.719340f;
                break;
            case 137:
                fReturn = -0.731354f;
                break;
            case 138:
                fReturn = -0.743145f;
                break;
            case 139:
                fReturn = -0.754710f;
                break;
            case 140:
                fReturn = -0.766044f;
                break;
            case 141:
                fReturn = -0.777146f;
                break;
            case 142:
                fReturn = -0.788011f;
                break;
            case 143:
                fReturn = -0.798636f;
                break;
            case 144:
                fReturn = -0.809017f;
                break;
            case 145:
                fReturn = -0.819152f;
                break;
            case 146:
                fReturn = -0.829038f;
                break;
            case 147:
                fReturn = -0.838671f;
                break;
            case 148:
                fReturn = -0.848048f;
                break;
            case 149:
                fReturn = -0.857167f;
                break;
            case 150:
                fReturn = -0.866025f;
                break;
            case 151:
                fReturn = -0.874620f;
                break;
            case 152:
                fReturn = -0.882948f;
                break;
            case 153:
                fReturn = -0.891007f;
                break;
            case 154:
                fReturn = -0.898794f;
                break;
            case 155:
                fReturn = -0.906308f;
                break;
            case 156:
                fReturn = -0.913545f;
                break;
            case 157:
                fReturn = -0.920505f;
                break;
            case 158:
                fReturn = -0.927184f;
                break;
            case 159:
                fReturn = -0.933580f;
                break;
            case 160:
                fReturn = -0.939693f;
                break;
            case 161:
                fReturn = -0.945519f;
                break;
            case 162:
                fReturn = -0.951057f;
                break;
            case 163:
                fReturn = -0.956305f;
                break;
            case 164:
                fReturn = -0.961262f;
                break;
            case 165:
                fReturn = -0.965926f;
                break;
            case 166:
                fReturn = -0.970296f;
                break;
            case 167:
                fReturn = -0.974370f;
                break;
            case 168:
                fReturn = -0.978148f;
                break;
            case 169:
                fReturn = -0.981627f;
                break;
            case 170:
                fReturn = -0.984808f;
                break;
            case 171:
                fReturn = -0.987688f;
                break;
            case 172:
                fReturn = -0.990268f;
                break;
            case 173:
                fReturn = -0.992546f;
                break;
            case 174:
                fReturn = -0.994522f;
                break;
            case 175:
                fReturn = -0.996195f;
                break;
            case 176:
                fReturn = -0.997564f;
                break;
            case 177:
                fReturn = -0.998630f;
                break;
            case 178:
                fReturn = -0.999391f;
                break;
            case 179:
                fReturn = -0.999848f;
                break;
            case 180:
                fReturn = -1.000000f;
                break;
            case 181:
                fReturn = -0.999848f;
                break;
            case 182:
                fReturn = -0.999391f;
                break;
            case 183:
                fReturn = -0.998630f;
                break;
            case 184:
                fReturn = -0.997564f;
                break;
            case 185:
                fReturn = -0.996195f;
                break;
            case 186:
                fReturn = -0.994522f;
                break;
            case 187:
                fReturn = -0.992546f;
                break;
            case 188:
                fReturn = -0.990268f;
                break;
            case 189:
                fReturn = -0.987688f;
                break;
            case 190:
                fReturn = -0.984808f;
                break;
            case 191:
                fReturn = -0.981627f;
                break;
            case 192:
                fReturn = -0.978148f;
                break;
            case 193:
                fReturn = -0.974370f;
                break;
            case 194:
                fReturn = -0.970296f;
                break;
            case 195:
                fReturn = -0.965926f;
                break;
            case 196:
                fReturn = -0.961262f;
                break;
            case 197:
                fReturn = -0.956305f;
                break;
            case 198:
                fReturn = -0.951057f;
                break;
            case 199:
                fReturn = -0.945519f;
                break;
            case 200:
                fReturn = -0.939693f;
                break;
            case 201:
                fReturn = -0.933580f;
                break;
            case 202:
                fReturn = -0.927184f;
                break;
            case 203:
                fReturn = -0.920505f;
                break;
            case 204:
                fReturn = -0.913545f;
                break;
            case 205:
                fReturn = -0.906308f;
                break;
            case 206:
                fReturn = -0.898794f;
                break;
            case 207:
                fReturn = -0.891007f;
                break;
            case 208:
                fReturn = -0.882948f;
                break;
            case 209:
                fReturn = -0.874620f;
                break;
            case 210:
                fReturn = -0.866025f;
                break;
            case 211:
                fReturn = -0.857167f;
                break;
            case 212:
                fReturn = -0.848048f;
                break;
            case 213:
                fReturn = -0.838671f;
                break;
            case 214:
                fReturn = -0.829038f;
                break;
            case 215:
                fReturn = -0.819152f;
                break;
            case 216:
                fReturn = -0.809017f;
                break;
            case 217:
                fReturn = -0.798636f;
                break;
            case 218:
                fReturn = -0.788011f;
                break;
            case 219:
                fReturn = -0.777146f;
                break;
            case 220:
                fReturn = -0.766044f;
                break;
            case 221:
                fReturn = -0.754710f;
                break;
            case 222:
                fReturn = -0.743145f;
                break;
            case 223:
                fReturn = -0.731354f;
                break;
            case 224:
                fReturn = -0.719340f;
                break;
            case 225:
                fReturn = -0.707107f;
                break;
            case 226:
                fReturn = -0.694658f;
                break;
            case 227:
                fReturn = -0.681998f;
                break;
            case 228:
                fReturn = -0.669131f;
                break;
            case 229:
                fReturn = -0.656059f;
                break;
            case 230:
                fReturn = -0.642788f;
                break;
            case 231:
                fReturn = -0.629320f;
                break;
            case 232:
                fReturn = -0.615661f;
                break;
            case 233:
                fReturn = -0.601815f;
                break;
            case 234:
                fReturn = -0.587785f;
                break;
            case 235:
                fReturn = -0.573576f;
                break;
            case 236:
                fReturn = -0.559193f;
                break;
            case 237:
                fReturn = -0.544639f;
                break;
            case 238:
                fReturn = -0.529919f;
                break;
            case 239:
                fReturn = -0.515038f;
                break;
            case 240:
                fReturn = -0.500000f;
                break;
            case 241:
                fReturn = -0.484810f;
                break;
            case 242:
                fReturn = -0.469472f;
                break;
            case 243:
                fReturn = -0.453990f;
                break;
            case 244:
                fReturn = -0.438371f;
                break;
            case 245:
                fReturn = -0.422618f;
                break;
            case 246:
                fReturn = -0.406737f;
                break;
            case 247:
                fReturn = -0.390731f;
                break;
            case 248:
                fReturn = -0.374607f;
                break;
            case 249:
                fReturn = -0.358368f;
                break;
            case 250:
                fReturn = -0.342020f;
                break;
            case 251:
                fReturn = -0.325568f;
                break;
            case 252:
                fReturn = -0.309017f;
                break;
            case 253:
                fReturn = -0.292372f;
                break;
            case 254:
                fReturn = -0.275637f;
                break;
            case 255:
                fReturn = -0.258819f;
                break;
            case 256:
                fReturn = -0.241922f;
                break;
            case 257:
                fReturn = -0.224951f;
                break;
            case 258:
                fReturn = -0.207912f;
                break;
            case 259:
                fReturn = -0.190809f;
                break;
            case 260:
                fReturn = -0.173648f;
                break;
            case 261:
                fReturn = -0.156434f;
                break;
            case 262:
                fReturn = -0.139173f;
                break;
            case 263:
                fReturn = -0.121869f;
                break;
            case 264:
                fReturn = -0.104528f;
                break;
            case 265:
                fReturn = -0.087156f;
                break;
            case 266:
                fReturn = -0.069756f;
                break;
            case 267:
                fReturn = -0.052336f;
                break;
            case 268:
                fReturn = -0.034899f;
                break;
            case 269:
                fReturn = -0.017452f;
                break;
            case 270:
                fReturn = -0.000000f;
                break;
            case 271:
                fReturn = 0.017452f;
                break;
            case 272:
                fReturn = 0.034899f;
                break;
            case 273:
                fReturn = 0.052336f;
                break;
            case 274:
                fReturn = 0.069756f;
                break;
            case 275:
                fReturn = 0.087156f;
                break;
            case 276:
                fReturn = 0.104528f;
                break;
            case 277:
                fReturn = 0.121869f;
                break;
            case 278:
                fReturn = 0.139173f;
                break;
            case 279:
                fReturn = 0.156434f;
                break;
            case 280:
                fReturn = 0.173648f;
                break;
            case 281:
                fReturn = 0.190809f;
                break;
            case 282:
                fReturn = 0.207912f;
                break;
            case 283:
                fReturn = 0.224951f;
                break;
            case 284:
                fReturn = 0.241922f;
                break;
            case 285:
                fReturn = 0.258819f;
                break;
            case 286:
                fReturn = 0.275637f;
                break;
            case 287:
                fReturn = 0.292372f;
                break;
            case 288:
                fReturn = 0.309017f;
                break;
            case 289:
                fReturn = 0.325568f;
                break;
            case 290:
                fReturn = 0.342020f;
                break;
            case 291:
                fReturn = 0.358368f;
                break;
            case 292:
                fReturn = 0.374607f;
                break;
            case 293:
                fReturn = 0.390731f;
                break;
            case 294:
                fReturn = 0.406737f;
                break;
            case 295:
                fReturn = 0.422618f;
                break;
            case 296:
                fReturn = 0.438371f;
                break;
            case 297:
                fReturn = 0.453990f;
                break;
            case 298:
                fReturn = 0.469472f;
                break;
            case 299:
                fReturn = 0.484810f;
                break;
            case 300:
                fReturn = 0.500000f;
                break;
            case 301:
                fReturn = 0.515038f;
                break;
            case 302:
                fReturn = 0.529919f;
                break;
            case 303:
                fReturn = 0.544639f;
                break;
            case 304:
                fReturn = 0.559193f;
                break;
            case 305:
                fReturn = 0.573576f;
                break;
            case 306:
                fReturn = 0.587785f;
                break;
            case 307:
                fReturn = 0.601815f;
                break;
            case 308:
                fReturn = 0.615661f;
                break;
            case 309:
                fReturn = 0.629320f;
                break;
            case 310:
                fReturn = 0.642788f;
                break;
            case 311:
                fReturn = 0.656059f;
                break;
            case 312:
                fReturn = 0.669131f;
                break;
            case 313:
                fReturn = 0.681998f;
                break;
            case 314:
                fReturn = 0.694658f;
                break;
            case 315:
                fReturn = 0.707107f;
                break;
            case 316:
                fReturn = 0.719340f;
                break;
            case 317:
                fReturn = 0.731354f;
                break;
            case 318:
                fReturn = 0.743145f;
                break;
            case 319:
                fReturn = 0.754710f;
                break;
            case 320:
                fReturn = 0.766044f;
                break;
            case 321:
                fReturn = 0.777146f;
                break;
            case 322:
                fReturn = 0.788011f;
                break;
            case 323:
                fReturn = 0.798636f;
                break;
            case 324:
                fReturn = 0.809017f;
                break;
            case 325:
                fReturn = 0.819152f;
                break;
            case 326:
                fReturn = 0.829038f;
                break;
            case 327:
                fReturn = 0.838671f;
                break;
            case 328:
                fReturn = 0.848048f;
                break;
            case 329:
                fReturn = 0.857167f;
                break;
            case 330:
                fReturn = 0.866025f;
                break;
            case 331:
                fReturn = 0.874620f;
                break;
            case 332:
                fReturn = 0.882948f;
                break;
            case 333:
                fReturn = 0.891007f;
                break;
            case 334:
                fReturn = 0.898794f;
                break;
            case 335:
                fReturn = 0.906308f;
                break;
            case 336:
                fReturn = 0.913545f;
                break;
            case 337:
                fReturn = 0.920505f;
                break;
            case 338:
                fReturn = 0.927184f;
                break;
            case 339:
                fReturn = 0.933580f;
                break;
            case 340:
                fReturn = 0.939693f;
                break;
            case 341:
                fReturn = 0.945519f;
                break;
            case 342:
                fReturn = 0.951057f;
                break;
            case 343:
                fReturn = 0.956305f;
                break;
            case 344:
                fReturn = 0.961262f;
                break;
            case 345:
                fReturn = 0.965926f;
                break;
            case 346:
                fReturn = 0.970296f;
                break;
            case 347:
                fReturn = 0.974370f;
                break;
            case 348:
                fReturn = 0.978148f;
                break;
            case 349:
                fReturn = 0.981627f;
                break;
            case 350:
                fReturn = 0.984808f;
                break;
            case 351:
                fReturn = 0.987688f;
                break;
            case 352:
                fReturn = 0.990268f;
                break;
            case 353:
                fReturn = 0.992546f;
                break;
            case 354:
                fReturn = 0.994522f;
                break;
            case 355:
                fReturn = 0.996195f;
                break;
            case 356:
                fReturn = 0.997564f;
                break;
            case 357:
                fReturn = 0.998630f;
                break;
            case 358:
                fReturn = 0.999391f;
                break;
            case 359:
                fReturn = 0.999848f;
                break;
            case 360:
                fReturn = 1.0f;
                break;
            default:
                fReturn = 1.0f;
                System.out.println("fallo en la función cosine()");
//                System.out.println(fDegrees);
        }
        return fReturn;
        */

    }

    static float sine(float fDegrees) {
        //return sineTable(fDegrees);        
        if(CONSTANTS.USE_MY_MATH)
            return sineTable(fDegrees);
        else
            return ((float)Math.sin((float)(Math.toRadians(fDegrees))));
        /*
        //return sineTable(fDegrees);
        while (fDegrees > 360) {
            fDegrees = fDegrees - 360;
        }
        while (fDegrees < 0) {
            fDegrees = fDegrees + 360;
        }

        float fReturn;
        int lDegrees=(int)fDegrees;
        switch (lDegrees) {

            case 0:
                fReturn = 0.000000f;
                break;
            case 1:
                fReturn = 0.017452f;
                break;
            case 2:
                fReturn = 0.034899f;
                break;
            case 3:
                fReturn = 0.052336f;
                break;
            case 4:
                fReturn = 0.069756f;
                break;
            case 5:
                fReturn = 0.087156f;
                break;
            case 6:
                fReturn = 0.104528f;
                break;
            case 7:
                fReturn = 0.121869f;
                break;
            case 8:
                fReturn = 0.139173f;
                break;
            case 9:
                fReturn = 0.156434f;
                break;
            case 10:
                fReturn = 0.173648f;
                break;
            case 11:
                fReturn = 0.190809f;
                break;
            case 12:
                fReturn = 0.207912f;
                break;
            case 13:
                fReturn = 0.224951f;
                break;
            case 14:
                fReturn = 0.241922f;
                break;
            case 15:
                fReturn = 0.258819f;
                break;
            case 16:
                fReturn = 0.275637f;
                break;
            case 17:
                fReturn = 0.292372f;
                break;
            case 18:
                fReturn = 0.309017f;
                break;
            case 19:
                fReturn = 0.325568f;
                break;
            case 20:
                fReturn = 0.342020f;
                break;
            case 21:
                fReturn = 0.358368f;
                break;
            case 22:
                fReturn = 0.374607f;
                break;
            case 23:
                fReturn = 0.390731f;
                break;
            case 24:
                fReturn = 0.406737f;
                break;
            case 25:
                fReturn = 0.422618f;
                break;
            case 26:
                fReturn = 0.438371f;
                break;
            case 27:
                fReturn = 0.453990f;
                break;
            case 28:
                fReturn = 0.469472f;
                break;
            case 29:
                fReturn = 0.484810f;
                break;
            case 30:
                fReturn = 0.500000f;
                break;
            case 31:
                fReturn = 0.515038f;
                break;
            case 32:
                fReturn = 0.529919f;
                break;
            case 33:
                fReturn = 0.544639f;
                break;
            case 34:
                fReturn = 0.559193f;
                break;
            case 35:
                fReturn = 0.573576f;
                break;
            case 36:
                fReturn = 0.587785f;
                break;
            case 37:
                fReturn = 0.601815f;
                break;
            case 38:
                fReturn = 0.615661f;
                break;
            case 39:
                fReturn = 0.629320f;
                break;
            case 40:
                fReturn = 0.642788f;
                break;
            case 41:
                fReturn = 0.656059f;
                break;
            case 42:
                fReturn = 0.669131f;
                break;
            case 43:
                fReturn = 0.681998f;
                break;
            case 44:
                fReturn = 0.694658f;
                break;
            case 45:
                fReturn = 0.707107f;
                break;
            case 46:
                fReturn = 0.719340f;
                break;
            case 47:
                fReturn = 0.731354f;
                break;
            case 48:
                fReturn = 0.743145f;
                break;
            case 49:
                fReturn = 0.754710f;
                break;
            case 50:
                fReturn = 0.766044f;
                break;
            case 51:
                fReturn = 0.777146f;
                break;
            case 52:
                fReturn = 0.788011f;
                break;
            case 53:
                fReturn = 0.798636f;
                break;
            case 54:
                fReturn = 0.809017f;
                break;
            case 55:
                fReturn = 0.819152f;
                break;
            case 56:
                fReturn = 0.829038f;
                break;
            case 57:
                fReturn = 0.838671f;
                break;
            case 58:
                fReturn = 0.848048f;
                break;
            case 59:
                fReturn = 0.857167f;
                break;
            case 60:
                fReturn = 0.866025f;
                break;
            case 61:
                fReturn = 0.874620f;
                break;
            case 62:
                fReturn = 0.882948f;
                break;
            case 63:
                fReturn = 0.891007f;
                break;
            case 64:
                fReturn = 0.898794f;
                break;
            case 65:
                fReturn = 0.906308f;
                break;
            case 66:
                fReturn = 0.913545f;
                break;
            case 67:
                fReturn = 0.920505f;
                break;
            case 68:
                fReturn = 0.927184f;
                break;
            case 69:
                fReturn = 0.933580f;
                break;
            case 70:
                fReturn = 0.939693f;
                break;
            case 71:
                fReturn = 0.945519f;
                break;
            case 72:
                fReturn = 0.951057f;
                break;
            case 73:
                fReturn = 0.956305f;
                break;
            case 74:
                fReturn = 0.961262f;
                break;
            case 75:
                fReturn = 0.965926f;
                break;
            case 76:
                fReturn = 0.970296f;
                break;
            case 77:
                fReturn = 0.974370f;
                break;
            case 78:
                fReturn = 0.978148f;
                break;
            case 79:
                fReturn = 0.981627f;
                break;
            case 80:
                fReturn = 0.984808f;
                break;
            case 81:
                fReturn = 0.987688f;
                break;
            case 82:
                fReturn = 0.990268f;
                break;
            case 83:
                fReturn = 0.992546f;
                break;
            case 84:
                fReturn = 0.994522f;
                break;
            case 85:
                fReturn = 0.996195f;
                break;
            case 86:
                fReturn = 0.997564f;
                break;
            case 87:
                fReturn = 0.998630f;
                break;
            case 88:
                fReturn = 0.999391f;
                break;
            case 89:
                fReturn = 0.999848f;
                break;
            case 90:
                fReturn = 1.000000f;
                break;
            case 91:
                fReturn = 0.999848f;
                break;
            case 92:
                fReturn = 0.999391f;
                break;
            case 93:
                fReturn = 0.998630f;
                break;
            case 94:
                fReturn = 0.997564f;
                break;
            case 95:
                fReturn = 0.996195f;
                break;
            case 96:
                fReturn = 0.994522f;
                break;
            case 97:
                fReturn = 0.992546f;
                break;
            case 98:
                fReturn = 0.990268f;
                break;
            case 99:
                fReturn = 0.987688f;
                break;
            case 100:
                fReturn = 0.984808f;
                break;
            case 101:
                fReturn = 0.981627f;
                break;
            case 102:
                fReturn = 0.978148f;
                break;
            case 103:
                fReturn = 0.974370f;
                break;
            case 104:
                fReturn = 0.970296f;
                break;
            case 105:
                fReturn = 0.965926f;
                break;
            case 106:
                fReturn = 0.961262f;
                break;
            case 107:
                fReturn = 0.956305f;
                break;
            case 108:
                fReturn = 0.951057f;
                break;
            case 109:
                fReturn = 0.945519f;
                break;
            case 110:
                fReturn = 0.939693f;
                break;
            case 111:
                fReturn = 0.933580f;
                break;
            case 112:
                fReturn = 0.927184f;
                break;
            case 113:
                fReturn = 0.920505f;
                break;
            case 114:
                fReturn = 0.913545f;
                break;
            case 115:
                fReturn = 0.906308f;
                break;
            case 116:
                fReturn = 0.898794f;
                break;
            case 117:
                fReturn = 0.891007f;
                break;
            case 118:
                fReturn = 0.882948f;
                break;
            case 119:
                fReturn = 0.874620f;
                break;
            case 120:
                fReturn = 0.866025f;
                break;
            case 121:
                fReturn = 0.857167f;
                break;
            case 122:
                fReturn = 0.848048f;
                break;
            case 123:
                fReturn = 0.838671f;
                break;
            case 124:
                fReturn = 0.829038f;
                break;
            case 125:
                fReturn = 0.819152f;
                break;
            case 126:
                fReturn = 0.809017f;
                break;
            case 127:
                fReturn = 0.798636f;
                break;
            case 128:
                fReturn = 0.788011f;
                break;
            case 129:
                fReturn = 0.777146f;
                break;
            case 130:
                fReturn = 0.766044f;
                break;
            case 131:
                fReturn = 0.754710f;
                break;
            case 132:
                fReturn = 0.743145f;
                break;
            case 133:
                fReturn = 0.731354f;
                break;
            case 134:
                fReturn = 0.719340f;
                break;
            case 135:
                fReturn = 0.707107f;
                break;
            case 136:
                fReturn = 0.694658f;
                break;
            case 137:
                fReturn = 0.681998f;
                break;
            case 138:
                fReturn = 0.669131f;
                break;
            case 139:
                fReturn = 0.656059f;
                break;
            case 140:
                fReturn = 0.642788f;
                break;
            case 141:
                fReturn = 0.629320f;
                break;
            case 142:
                fReturn = 0.615661f;
                break;
            case 143:
                fReturn = 0.601815f;
                break;
            case 144:
                fReturn = 0.587785f;
                break;
            case 145:
                fReturn = 0.573576f;
                break;
            case 146:
                fReturn = 0.559193f;
                break;
            case 147:
                fReturn = 0.544639f;
                break;
            case 148:
                fReturn = 0.529919f;
                break;
            case 149:
                fReturn = 0.515038f;
                break;
            case 150:
                fReturn = 0.500000f;
                break;
            case 151:
                fReturn = 0.484810f;
                break;
            case 152:
                fReturn = 0.469472f;
                break;
            case 153:
                fReturn = 0.453990f;
                break;
            case 154:
                fReturn = 0.438371f;
                break;
            case 155:
                fReturn = 0.422618f;
                break;
            case 156:
                fReturn = 0.406737f;
                break;
            case 157:
                fReturn = 0.390731f;
                break;
            case 158:
                fReturn = 0.374607f;
                break;
            case 159:
                fReturn = 0.358368f;
                break;
            case 160:
                fReturn = 0.342020f;
                break;
            case 161:
                fReturn = 0.325568f;
                break;
            case 162:
                fReturn = 0.309017f;
                break;
            case 163:
                fReturn = 0.292372f;
                break;
            case 164:
                fReturn = 0.275637f;
                break;
            case 165:
                fReturn = 0.258819f;
                break;
            case 166:
                fReturn = 0.241922f;
                break;
            case 167:
                fReturn = 0.224951f;
                break;
            case 168:
                fReturn = 0.207912f;
                break;
            case 169:
                fReturn = 0.190809f;
                break;
            case 170:
                fReturn = 0.173648f;
                break;
            case 171:
                fReturn = 0.156434f;
                break;
            case 172:
                fReturn = 0.139173f;
                break;
            case 173:
                fReturn = 0.121869f;
                break;
            case 174:
                fReturn = 0.104528f;
                break;
            case 175:
                fReturn = 0.087156f;
                break;
            case 176:
                fReturn = 0.069756f;
                break;
            case 177:
                fReturn = 0.052336f;
                break;
            case 178:
                fReturn = 0.034899f;
                break;
            case 179:
                fReturn = 0.017452f;
                break;
            case 180:
                fReturn = 0.000000f;
                break;
            case 181:
                fReturn = -0.017452f;
                break;
            case 182:
                fReturn = -0.034899f;
                break;
            case 183:
                fReturn = -0.052336f;
                break;
            case 184:
                fReturn = -0.069756f;
                break;
            case 185:
                fReturn = -0.087156f;
                break;
            case 186:
                fReturn = -0.104528f;
                break;
            case 187:
                fReturn = -0.121869f;
                break;
            case 188:
                fReturn = -0.139173f;
                break;
            case 189:
                fReturn = -0.156434f;
                break;
            case 190:
                fReturn = -0.173648f;
                break;
            case 191:
                fReturn = -0.190809f;
                break;
            case 192:
                fReturn = -0.207912f;
                break;
            case 193:
                fReturn = -0.224951f;
                break;
            case 194:
                fReturn = -0.241922f;
                break;
            case 195:
                fReturn = -0.258819f;
                break;
            case 196:
                fReturn = -0.275637f;
                break;
            case 197:
                fReturn = -0.292372f;
                break;
            case 198:
                fReturn = -0.309017f;
                break;
            case 199:
                fReturn = -0.325568f;
                break;
            case 200:
                fReturn = -0.342020f;
                break;
            case 201:
                fReturn = -0.358368f;
                break;
            case 202:
                fReturn = -0.374607f;
                break;
            case 203:
                fReturn = -0.390731f;
                break;
            case 204:
                fReturn = -0.406737f;
                break;
            case 205:
                fReturn = -0.422618f;
                break;
            case 206:
                fReturn = -0.438371f;
                break;
            case 207:
                fReturn = -0.453990f;
                break;
            case 208:
                fReturn = -0.469472f;
                break;
            case 209:
                fReturn = -0.484810f;
                break;
            case 210:
                fReturn = -0.500000f;
                break;
            case 211:
                fReturn = -0.515038f;
                break;
            case 212:
                fReturn = -0.529919f;
                break;
            case 213:
                fReturn = -0.544639f;
                break;
            case 214:
                fReturn = -0.559193f;
                break;
            case 215:
                fReturn = -0.573576f;
                break;
            case 216:
                fReturn = -0.587785f;
                break;
            case 217:
                fReturn = -0.601815f;
                break;
            case 218:
                fReturn = -0.615661f;
                break;
            case 219:
                fReturn = -0.629320f;
                break;
            case 220:
                fReturn = -0.642788f;
                break;
            case 221:
                fReturn = -0.656059f;
                break;
            case 222:
                fReturn = -0.669131f;
                break;
            case 223:
                fReturn = -0.681998f;
                break;
            case 224:
                fReturn = -0.694658f;
                break;
            case 225:
                fReturn = -0.707107f;
                break;
            case 226:
                fReturn = -0.719340f;
                break;
            case 227:
                fReturn = -0.731354f;
                break;
            case 228:
                fReturn = -0.743145f;
                break;
            case 229:
                fReturn = -0.754710f;
                break;
            case 230:
                fReturn = -0.766044f;
                break;
            case 231:
                fReturn = -0.777146f;
                break;
            case 232:
                fReturn = -0.788011f;
                break;
            case 233:
                fReturn = -0.798636f;
                break;
            case 234:
                fReturn = -0.809017f;
                break;
            case 235:
                fReturn = -0.819152f;
                break;
            case 236:
                fReturn = -0.829038f;
                break;
            case 237:
                fReturn = -0.838671f;
                break;
            case 238:
                fReturn = -0.848048f;
                break;
            case 239:
                fReturn = -0.857167f;
                break;
            case 240:
                fReturn = -0.866025f;
                break;
            case 241:
                fReturn = -0.874620f;
                break;
            case 242:
                fReturn = -0.882948f;
                break;
            case 243:
                fReturn = -0.891007f;
                break;
            case 244:
                fReturn = -0.898794f;
                break;
            case 245:
                fReturn = -0.906308f;
                break;
            case 246:
                fReturn = -0.913545f;
                break;
            case 247:
                fReturn = -0.920505f;
                break;
            case 248:
                fReturn = -0.927184f;
                break;
            case 249:
                fReturn = -0.933580f;
                break;
            case 250:
                fReturn = -0.939693f;
                break;
            case 251:
                fReturn = -0.945519f;
                break;
            case 252:
                fReturn = -0.951057f;
                break;
            case 253:
                fReturn = -0.956305f;
                break;
            case 254:
                fReturn = -0.961262f;
                break;
            case 255:
                fReturn = -0.965926f;
                break;
            case 256:
                fReturn = -0.970296f;
                break;
            case 257:
                fReturn = -0.974370f;
                break;
            case 258:
                fReturn = -0.978148f;
                break;
            case 259:
                fReturn = -0.981627f;
                break;
            case 260:
                fReturn = -0.984808f;
                break;
            case 261:
                fReturn = -0.987688f;
                break;
            case 262:
                fReturn = -0.990268f;
                break;
            case 263:
                fReturn = -0.992546f;
                break;
            case 264:
                fReturn = -0.994522f;
                break;
            case 265:
                fReturn = -0.996195f;
                break;
            case 266:
                fReturn = -0.997564f;
                break;
            case 267:
                fReturn = -0.998630f;
                break;
            case 268:
                fReturn = -0.999391f;
                break;
            case 269:
                fReturn = -0.999848f;
                break;
            case 270:
                fReturn = -1.000000f;
                break;
            case 271:
                fReturn = -0.999848f;
                break;
            case 272:
                fReturn = -0.999391f;
                break;
            case 273:
                fReturn = -0.998630f;
                break;
            case 274:
                fReturn = -0.997564f;
                break;
            case 275:
                fReturn = -0.996195f;
                break;
            case 276:
                fReturn = -0.994522f;
                break;
            case 277:
                fReturn = -0.992546f;
                break;
            case 278:
                fReturn = -0.990268f;
                break;
            case 279:
                fReturn = -0.987688f;
                break;
            case 280:
                fReturn = -0.984808f;
                break;
            case 281:
                fReturn = -0.981627f;
                break;
            case 282:
                fReturn = -0.978148f;
                break;
            case 283:
                fReturn = -0.974370f;
                break;
            case 284:
                fReturn = -0.970296f;
                break;
            case 285:
                fReturn = -0.965926f;
                break;
            case 286:
                fReturn = -0.961262f;
                break;
            case 287:
                fReturn = -0.956305f;
                break;
            case 288:
                fReturn = -0.951057f;
                break;
            case 289:
                fReturn = -0.945519f;
                break;
            case 290:
                fReturn = -0.939693f;
                break;
            case 291:
                fReturn = -0.933580f;
                break;
            case 292:
                fReturn = -0.927184f;
                break;
            case 293:
                fReturn = -0.920505f;
                break;
            case 294:
                fReturn = -0.913545f;
                break;
            case 295:
                fReturn = -0.906308f;
                break;
            case 296:
                fReturn = -0.898794f;
                break;
            case 297:
                fReturn = -0.891007f;
                break;
            case 298:
                fReturn = -0.882948f;
                break;
            case 299:
                fReturn = -0.874620f;
                break;
            case 300:
                fReturn = -0.866025f;
                break;
            case 301:
                fReturn = -0.857167f;
                break;
            case 302:
                fReturn = -0.848048f;
                break;
            case 303:
                fReturn = -0.838671f;
                break;
            case 304:
                fReturn = -0.829038f;
                break;
            case 305:
                fReturn = -0.819152f;
                break;
            case 306:
                fReturn = -0.809017f;
                break;
            case 307:
                fReturn = -0.798636f;
                break;
            case 308:
                fReturn = -0.788011f;
                break;
            case 309:
                fReturn = -0.777146f;
                break;
            case 310:
                fReturn = -0.766044f;
                break;
            case 311:
                fReturn = -0.754710f;
                break;
            case 312:
                fReturn = -0.743145f;
                break;
            case 313:
                fReturn = -0.731354f;
                break;
            case 314:
                fReturn = -0.719340f;
                break;
            case 315:
                fReturn = -0.707107f;
                break;
            case 316:
                fReturn = -0.694658f;
                break;
            case 317:
                fReturn = -0.681998f;
                break;
            case 318:
                fReturn = -0.669131f;
                break;
            case 319:
                fReturn = -0.656059f;
                break;
            case 320:
                fReturn = -0.642788f;
                break;
            case 321:
                fReturn = -0.629320f;
                break;
            case 322:
                fReturn = -0.615661f;
                break;
            case 323:
                fReturn = -0.601815f;
                break;
            case 324:
                fReturn = -0.587785f;
                break;
            case 325:
                fReturn = -0.573576f;
                break;
            case 326:
                fReturn = -0.559193f;
                break;
            case 327:
                fReturn = -0.544639f;
                break;
            case 328:
                fReturn = -0.529919f;
                break;
            case 329:
                fReturn = -0.515038f;
                break;
            case 330:
                fReturn = -0.500000f;
                break;
            case 331:
                fReturn = -0.484810f;
                break;
            case 332:
                fReturn = -0.469472f;
                break;
            case 333:
                fReturn = -0.453990f;
                break;
            case 334:
                fReturn = -0.438371f;
                break;
            case 335:
                fReturn = -0.422618f;
                break;
            case 336:
                fReturn = -0.406737f;
                break;
            case 337:
                fReturn = -0.390731f;
                break;
            case 338:
                fReturn = -0.374607f;
                break;
            case 339:
                fReturn = -0.358368f;
                break;
            case 340:
                fReturn = -0.342020f;
                break;
            case 341:
                fReturn = -0.325568f;
                break;
            case 342:
                fReturn = -0.309017f;
                break;
            case 343:
                fReturn = -0.292372f;
                break;
            case 344:
                fReturn = -0.275637f;
                break;
            case 345:
                fReturn = -0.258819f;
                break;
            case 346:
                fReturn = -0.241922f;
                break;
            case 347:
                fReturn = -0.224951f;
                break;
            case 348:
                fReturn = -0.207912f;
                break;
            case 349:
                fReturn = -0.190809f;
                break;
            case 350:
                fReturn = -0.173648f;
                break;
            case 351:
                fReturn = -0.156434f;
                break;
            case 352:
                fReturn = -0.139173f;
                break;
            case 353:
                fReturn = -0.121869f;
                break;
            case 354:
                fReturn = -0.104528f;
                break;
            case 355:
                fReturn = -0.087156f;
                break;
            case 356:
                fReturn = -0.069756f;
                break;
            case 357:
                fReturn = -0.052336f;
                break;
            case 358:
                fReturn = -0.034899f;
                break;
            case 359:
                fReturn = -0.017452f;
                break;
            case 360:
                fReturn = 0.0f;
                break;
            default:
                fReturn = 0.0f;
                System.out.println("fallo en la función sine()");
//                System.out.println(fDegrees);
        }
        return fReturn;
        */
    }
    static boolean calculateCutPointOfTwoLines(float fLine1Ax, float fLine1Ay, float fLine1Bx, float fLine1By, float fLine2Ax, float fLine2Ay, float fLine2Bx, float fLine2By, CPointCartesianCoord PointCoord) {
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

    static float calculateModule(float fDistX, float fDistY){
//        float fSquareModule=fDistX*fDistX+fDistY*fDistY;
//        return CMath.SquareRoot(0, fSquareModule, fSquareModule, 30, 0);
//        
        if(CONSTANTS.USE_MY_MATH){
            float fSquareModule=fDistX*fDistX+fDistY*fDistY;
            return CMath.SquareRoot(0, fSquareModule, fSquareModule, 30, 0);
        
        }
        else
            return (float)Math.sqrt(fDistX*fDistX+fDistY*fDistY);                
    }
    static float pow2(float fValue){
        return (fValue*fValue);
    }
//    static float PI(){
//        return 3.14159265f;
//    }
    static CPointCartesianCoord polarToCartesian(float fModule, float fDegrees){
        CPointCartesianCoord pPoint=new CPointCartesianCoord(fModule*cosine(fDegrees),fModule*sine(fDegrees));
        return pPoint;
    }
    static CPointPolarCoord cartesianToPolar(float fX, float fY){
        CPointPolarCoord pPoint=new CPointPolarCoord(calculateModule(fX,fY),GetVectorAngle(fX,fY));
        return pPoint;
    }

    static boolean isIntersection(float f1AX,float f1AY,float f1BX,float f1BY,float f2AX,float f2AY,float f2BX,float f2BY,CPointCartesianCoord pPointCoor){

        if(f1AX<f2AX && f1AX<f2BX && f1BX<f2AX && f1BX<f2BX)
            return false;
        if(f1AX>f2AX && f1AX>f2BX && f1BX>f2AX && f1BX>f2BX)
            return false;
        if(f1AY<f2AY && f1AY<f2BY && f1BY<f2AY && f1BY<f2BY)
            return false;
        if(f1AY>f2AY && f1AY>f2BY && f1BY>f2AY && f1BY>f2BY)
            return false;
        
        float fDen=(f2BY-f2AY)*(f1BX-f1AX)-(f2BX-f2AX)*(f1BY-f1AY);

        if(0.0==fDen)
            return false;

        float fNumUa=(f2BX-f2AX)*(f1AY-f2AY)-(f2BY-f2AY)*(f1AX-f2AX);


        float fNumUb=(f1BX-f1AX)*(f1AY-f2AY)-(f1BY-f1AY)*(f1AX-f2AX);

        float fUa=fNumUa/fDen;
        
        if(fUa<0 || fUa>1.0)
            return false;

        float fUb=fNumUb/fDen;

        if(fUb<0 || fUb>1.0)
            return false;

        //System.out.println("Intersection detected");
        pPointCoor.m_fX=f1AX+fUa*(f1BX-f1AX);
        pPointCoor.m_fY=f1AY+fUa*(f1BY-f1AY);
        return true;
    }
    static float dotProduct(float fV1X,float fV1Y, float fV2X, float fV2Y){
            
        return fV1X*fV2X+fV1Y*fV2Y;
    }
    static boolean isInsideTriangle(float fAX,float fAY,float fBX,float fBY,float fCX,float fCY,float fPX,float fPY){

        /*
        // Compute vectors
        v0 = C - A
        v1 = B - A
        v2 = P - A

        // Compute dot products
        dot00 = dot(v0, v0)
        dot01 = dot(v0, v1)
        dot02 = dot(v0, v2)
        dot11 = dot(v1, v1)
        dot12 = dot(v1, v2)

        // Compute barycentric coordinates
        invDenom = 1 / (dot00 * dot11 - dot01 * dot01)
        u = (dot11 * dot02 - dot01 * dot12) * invDenom
        v = (dot00 * dot12 - dot01 * dot02) * invDenom

        // Check if point is in triangle
        return (u > 0) && (v > 0) && (u + v < 1)

        */

        if(fPX<fAX && fPX<fBX && fPX<fCX)
            return false;
        if(fPX>fAX && fPX>fBX && fPX>fCX)
            return false;
        if(fPY<fAY && fPY<fBY && fPY<fCY)
            return false;
        if(fPY>fAY && fPY>fBY && fPY>fCY)
            return false;


        float fv0X=fCX-fAX;
        float fv0Y=fCY-fAY;

        float fv1X=fBX-fAX;
        float fv1Y=fBY-fAY;

        float fv2X=fPX-fAX;
        float fv2Y=fPY-fAY;

        float fDot00 = dotProduct(fv0X,fv0Y, fv0X,fv0Y);
        float fDot01 = dotProduct(fv0X,fv0Y, fv1X,fv1Y);
        float fDot02 = dotProduct(fv0X,fv0Y, fv2X,fv2Y);
        float fDot11 = dotProduct(fv1X,fv1Y, fv1X,fv1Y);
        float fDot12 = dotProduct(fv1X,fv1Y, fv2X,fv2Y);

        float fDenom = fDot00 * fDot11 - fDot01 * fDot01;

        if (fDenom==0.0)
        {
            return false;
        }
        //TODO: I should assure that fInvDenom is not out of the range of float
        
        float fInvDenom = 1 / fDenom;

        float u = (fDot11 * fDot02 - fDot01 * fDot12) * fInvDenom;
        float v = (fDot00 * fDot12 - fDot01 * fDot02) * fInvDenom;

        // Check if point is in triangle
        return (u > 0) && (v > 0) && (u + v < 1);
    }
    
//    static boolean testCollisionProximityOld(CPolygon polygon1, CPolygon polygon2){ 
//        if(polygon1.m_fX - polygon1.m_fCollisionTestSquareSize < polygon2.m_fX + polygon2.m_fCollisionTestSquareSize){
//                    
//            if(polygon1.m_fX + polygon1.m_fCollisionTestSquareSize > polygon2.m_fX - polygon2.m_fCollisionTestSquareSize){
//                if(polygon1.m_fY - polygon1.m_fCollisionTestSquareSize < polygon2.m_fY + polygon2.m_fCollisionTestSquareSize){
//                    if(polygon1.m_fY + polygon1.m_fCollisionTestSquareSize > polygon2.m_fY - polygon2.m_fCollisionTestSquareSize){
//                        return true;
//                    }
//                    else{
//                        return false;
//                    }
//                }
//                else{
//                    return false;
//                }
//            }
//            else{
//                return false;
//            }
//        }
//        else{
//            return false;
//        }
//    }
//    static boolean testCollisionProximityOld(CPolygon polygon, float fx, float fy){ 
//        if(polygon.m_fX - polygon.m_fCollisionTestSquareSize < fx){
//                    
//            if(polygon.m_fX + polygon.m_fCollisionTestSquareSize > fx){
//                if(polygon.m_fY - polygon.m_fCollisionTestSquareSize < fy){
//                    if(polygon.m_fY + polygon.m_fCollisionTestSquareSize > fy){
//                        return true;
//                    }
//                    else{
//                        return false;
//                    }
//                }
//                else{
//                    return false;
//                }
//            }
//            else{
//                return false;
//            }
//        }
//        else{
//            return false;
//        }
//    }
    
            
    
    static boolean testCollisionProximity(CPolygon polygon1, CPolygon polygon2){ 
        if(polygon1.m_fCollisionTestXMin < polygon2.m_fCollisionTestXMax){
                    
            if(polygon1.m_fCollisionTestXMax > polygon2.m_fCollisionTestXMin){
                if(polygon1.m_fCollisionTestYMin < polygon2.m_fCollisionTestYMax){
                    if(polygon1.m_fCollisionTestYMax > polygon2.m_fCollisionTestYMin){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    static boolean testCollisionProximity(CPolygon polygon, float fx, float fy){ 
        if(polygon.m_fCollisionTestXMin < fx){
                    
            if(polygon.m_fCollisionTestXMax > fx){
                if(polygon.m_fCollisionTestYMin < fy){
                    if(polygon.m_fCollisionTestYMax > fy){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    static boolean testCollisionProximity(CPolygon polygon1, CGroup pGroup){ 
        if(polygon1.m_fCollisionTestXMin < pGroup.getCollisionTestXMax()){                    
            if(polygon1.m_fCollisionTestXMax > pGroup.getCollisionTestXMin()){
                if(polygon1.m_fCollisionTestYMin < pGroup.getCollisionTestYMax()){
                    if(polygon1.m_fCollisionTestYMax > pGroup.getCollisionTestYMin()){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    static float sineTaylor(float fAngleRadians){
        float fX=fAngleRadians;
        float fX2=fAngleRadians*fAngleRadians;
        float fX3=fAngleRadians*fX2;
        float fX5=fX3*fX2;
        float fX7=fX5*fX2;
        float fX9=fX7*fX2;
        float fX11=fX9*fX2;
        float fX13=fX11*fX2;
        return fX-(fX3/6f)+(fX5/120f)-(fX7/5040f)+(fX9/362880f)-(fX11/39916800f)+(fX13/6227020800f);
    }
    static float cosineTaylor(float fAngleRadians){
        
        float fX2=fAngleRadians*fAngleRadians;
        float fX4=fX2*fX2;
        float fX6=fX4*fX2;
        float fX8=fX6*fX2;
        float fX10=fX8*fX2;
        float fX12=fX10*fX2;
        float fX14=fX12*fX2;
        return 1-(fX2/2f)+(fX4/24f)-(fX6/720f)+(fX8/40320f)-(fX10/3628800f)+(fX12/479001600f)-(fX14/87178291200f);
    }
    static float tanTaylor(float fAngleRadians){
//        float fX=fAngleRadians;
//        float fX2=fAngleRadians*fAngleRadians;
//        float fX3=fAngleRadians*fX2;
//        float fX5=fX3*fX2;
//        float fX7=fX5*fX2;
//        return fX-(fX3/3f)+(2f*fX5/15f)-(17f*fX7/315f);
        return(sine(fAngleRadians)/cosine(fAngleRadians));
    }
    static float arctanTaylor(float tan){
        float fX=tan;
        float fX2=tan*tan;
        float fX3=tan*fX2;
        float fX5=fX3*fX2;
        float fX7=fX5*fX2;
        float fAngle=fX-(fX3/3f)+(fX5/5f)-(fX7/7f);
        return fX-(fX3/3f)+(fX5/5f)-(fX7/7f);
    }
    
    static float sineTable(float fAngle){
        //System.out.println("Entrando en sineTable");
        while (fAngle < 0) {
            fAngle = fAngle + 360;
        }
        while (fAngle > 360) {
            fAngle = fAngle - 360;
        }
        
        int lQuadrant=0;
        float fAngle0_90=0;
        if(fAngle>=0 && fAngle<=90){
            lQuadrant=1;
            fAngle0_90=fAngle;            
        }
        else if(fAngle>90 && fAngle<=180){
            lQuadrant=2;
            fAngle0_90=180-fAngle;            
        }
        else if(fAngle>180 && fAngle<=270){
            lQuadrant=3;
            fAngle0_90=fAngle-180;            
        }
        else {
            lQuadrant=4;
            fAngle0_90=360-fAngle;            
        }
        
        int lAngle=(int)fAngle0_90;
        float fAbsSine=0;
        if(lAngle==90)
            fAbsSine=1;
        else //for angles between 0 and 89 only
            fAbsSine=interpolate(lAngle,lAngle+1,fAngle0_90, sineArray[lAngle],sineArray[lAngle+1]);
        
        switch (lQuadrant){
            case 1:
                return fAbsSine;
            case 2:
                return fAbsSine;
            case 3:
                return -fAbsSine;
            case 4:
                return -fAbsSine;
            default:
                return 0;
        }        
    }
    
    static float cosineTable(float fAngle){
//        System.out.println("Entrando en cosineTable");
//        System.out.println(fAngle);
        while (fAngle < 0) {
            fAngle = fAngle + 360;
        }
        while (fAngle > 360) {
            fAngle = fAngle - 360;
        }
        
        int lQuadrant=0;
        float fAngle0_90=0;
        if(fAngle>=0 && fAngle<=90){
            lQuadrant=1;
            fAngle0_90=fAngle;            
        }
        else if(fAngle>90 && fAngle<=180){
            lQuadrant=2;
            fAngle0_90=180-fAngle;            
        }
        else if(fAngle>180 && fAngle<=270){
            lQuadrant=3;
            fAngle0_90=fAngle-180;            
        }
        else {
            lQuadrant=4;
            fAngle0_90=360-fAngle;            
        }
        
        int lAngle=(int)fAngle0_90;        
        float fAbsCosine=0;
        if(lAngle==90)
            fAbsCosine=0;
        else //for angles between 0 and 89 only
            fAbsCosine=interpolate(lAngle,lAngle+1,fAngle0_90, cosineArray[lAngle],cosineArray[lAngle+1]);
        
        switch (lQuadrant){
            case 1:
                return fAbsCosine;
            case 2:
                return -fAbsCosine;
            case 3:
                return -fAbsCosine;
            case 4:
                return fAbsCosine;
            default:
                return 0;
        }        
    }
    
    static float tanTable(float fAngle){
//        System.out.println("Entrando en tanTable");
        while (fAngle < 0) {
            fAngle = fAngle + 360;
        }
        while (fAngle > 360) {
            fAngle = fAngle - 360;
        }
        
        int lQuadrant=0;
        float fAngle0_90=0;
        if(fAngle>=0 && fAngle<=90){
            lQuadrant=1;
            fAngle0_90=fAngle;            
        }
        else if(fAngle>90 && fAngle<=180){
            lQuadrant=2;
            fAngle0_90=180-fAngle;            
        }
        else if(fAngle>180 && fAngle<=270){
            lQuadrant=3;
            fAngle0_90=fAngle-180;            
        }
        else {
            lQuadrant=4;
            fAngle0_90=360-fAngle;            
        }
        
        int lAngle=(int)fAngle0_90;          
        float fAbsTan=0;
        if(lAngle==90)
            fAbsTan=1;
        else //for angles between 0 and 89 only
            fAbsTan=interpolate(lAngle,lAngle+1,fAngle0_90, tanArray[lAngle],tanArray[lAngle+1]);
        
        switch (lQuadrant){
            case 1:
                return fAbsTan;
            case 2:
                return fAbsTan;
            case 3:
                return -fAbsTan;
            case 4:
                return -fAbsTan;
            default:
                return 0;
        }        
    }
    static float aTanTable(float fTan){
//        System.out.println("Entrando en aTanTable");
//        System.out.println(fTan);        
        float fAbsTan=fTan;
        if (fTan<0)
            fAbsTan=-fTan;
            
//        float fTanValueTable=0;
//        int lCount=0;
//        while(fTanValueTable<=fAbsTan && lCount<=90){
//            fTanValueTable=tanArray[lCount];
//            lCount++;            
//        }

        float fTanValueTable=0;
        int lCount=45;
        int lPrecision=lCount;       
        while(lPrecision!=0){            
           
            fTanValueTable=tanArray[lCount];
            if(fTanValueTable<fAbsTan)
                lCount=lCount+lPrecision;
            else
                lCount=lCount-lPrecision;
            lPrecision=lPrecision>>1;
//             if(lCount>90)
//                lCount=90;
            if(lCount<0)
                lCount=0;
        }
        
        while(fTanValueTable<=fAbsTan && lCount<=90){
            fTanValueTable=tanArray[lCount];
            lCount++;            
        }   
        
//        System.out.println("aTanTable lCount");
//        System.out.println(lCount);
        float fAbsAngle=0;
//        if(lCount-1==90)
//            fAbsAngle=1;
//        else //for angles between 0 and 89 only
        fAbsAngle=interpolate(tanArray[lCount-2],tanArray[lCount-1],fAbsTan, lCount-2,lCount-1);
        
        if(fTan<0)
            return -fAbsAngle;
        else
            return fAbsAngle;            
    }
    static float interpolate(float fXA, float fXB, float fXC, float fA, float fB){
        //return 1.0f;
        return fA+(((fB-fA)/(fXB-fXA))*(fXC-fXA));        
    }
    static int round(float fNumber){
        if(fNumber>0.0)
            return (int)(fNumber+0.5f);
        else
            return (int)(fNumber-0.5f);
    }
    static boolean isTriangleCollision(float[] m_lf1XCoordVertexes,float[] m_lf1YCoordVertexes,
                                        float[] m_lf2XCoordVertexes,float[] m_lf2YCoordVertexes ,CPointCartesianCoord pPointCoor)
    {
        for (int lSide1=0;lSide1<3;lSide1++)
        {
            int a1=lSide1;
            int b1;
            if(2==lSide1)
                b1=0;
            else
                b1=lSide1+1;
                            
            for (int lSide2=0;lSide2<3;lSide2++)
            {
                int a2=lSide2;
                int b2;
                if(2==lSide2)
                    b2=0;
                else
                    b2=lSide2+1;
                
                if(isIntersection(m_lf1XCoordVertexes[a1],m_lf1YCoordVertexes[a1],m_lf1XCoordVertexes[b1],m_lf1YCoordVertexes[b1],
                    m_lf2XCoordVertexes[a2],m_lf2YCoordVertexes[a2],m_lf2XCoordVertexes[b2],m_lf2YCoordVertexes[b2],pPointCoor))
                {
                    return true;
                }
                    
            }
            
        }
        //Para asegurarme de que no hay un polígono dentro del otro
        if(isInsideTriangle(m_lf1XCoordVertexes[0],m_lf1YCoordVertexes[0],m_lf1XCoordVertexes[1],m_lf1YCoordVertexes[1],m_lf1XCoordVertexes[2],m_lf1YCoordVertexes[2],m_lf2XCoordVertexes[0],m_lf2YCoordVertexes[0]))
        {   
            return true;            
        }
        return false;
    }
                                        
}
