package Calculator;

import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.negateExact;
import static java.lang.Math.scalb;

public class CORDICcalculator {
    //list of angles in radians that satisfy this requirement: tan^-1(angles(n)) = 2^(-n)
    List<Double> angles = Arrays.asList(0.78539816339745,   0.46364760900081, 0.24497866312686,   0.12435499454676,
            0.06241880999596,   0.03123983343027,   0.01562372862048,   0.00781234106010,
            0.00390623013197,   0.00195312251648,   0.00097656218956,   0.00048828121119,
            0.00024414062015,   0.00012207031189,   0.00006103515617,   0.00003051757812,
            0.00001525878906,   0.00000762939453,   0.00000381469727,   0.00000190734863,
            0.00000095367432,   0.00000047683716,  0.00000023841858,   0.00000011920929,
            0.00000005960464,   0.00000002980232,  0.00000001490116,   0.00000000745058);
    //list of K value constants of the repeated products of cos(angles(n)) iterations
    List<Double> Kvalues = Arrays.asList(
            0.70710678118655,   0.63245553203368,   0.61357199107790,   0.60883391251775,
            0.60764825625617,   0.60735177014130,   0.60727764409353,   0.60725911229889,
            0.60725447933256,   0.60725332108988,   0.60725303152913,   0.60725295913894,
            0.60725294104140,  0.60725293651701,   0.60725293538591,   0.60725293510314,
            0.60725293503245,  0.60725293501477,   0.60725293501035,   0.60725293500925,
            0.60725293500897,  0.60725293500890,   0.60725293500889,   0.60725293500888);

    public List<Double> Rotate(double inputangle, double maxError, int round){
        //get angle to between one full unit circle rotation
        while (inputangle > 360){
            inputangle = inputangle - 360;
        }
        //find which quadrant it is in
        int quadrant = 1;
        ArrayList<Integer> quadrants = new ArrayList<>(Arrays.asList(90,180,270));
        for (int i = 0; i < quadrants.size(); i++) {
            if (inputangle > quadrants.get(i)){
                quadrant = i + 2;
            }
        }
        //reduce the input angle to an acute angle
        while (inputangle > 90){
            inputangle = inputangle - 90;
        }
        //convert our special angles to degrees from radians
        List<Double> degrees = new ArrayList<>(){};
        for (Double angle:angles){
             degrees.add(angle * 180 / Math.PI);
        }
        double currentRotation = 0;
        int currentIteration = 0;
        List<Double> inputvector = Arrays.asList(0.0,1.0);
        //apply rotation matrix with inverse tan angle power of twos (doesn't require multiplication or addition). This will loop
        //until acceptable error is reached.
        while(abs(currentRotation - inputangle) > maxError || currentIteration < 6) {
            double error = abs(currentRotation - inputangle);
            int RotationSign = inputangle > currentRotation ? 1 : -1;
            Double x0 = inputvector.get(0);
            Double y0 = inputvector.get(1);
            if (currentIteration < degrees.size()){
                if (RotationSign > 0){
                    currentRotation = currentRotation + degrees.get(currentIteration);
                    Double x1 = x0 - scalb(y0,-currentIteration);
                    Double y1 =  scalb(x0,-currentIteration) + y0;
                    inputvector.set(0,x1);
                    inputvector.set(1,y1);
                }
                else{
                    currentRotation = currentRotation - degrees.get(currentIteration);
                    Double x1 = x0 + scalb(y0,-currentIteration);
                    Double y1 =  y0 - scalb(x0,-currentIteration);
                    inputvector.set(0,x1);
                    inputvector.set(1,y1);
                }
                currentIteration++;
            }
            else{
                break;
            }

        }
        //scale by K value
        for (int i = 0; i < inputvector.size(); i++) {
            //if there is a specific Kvalue for this iteration, use that scale factor
            if (currentIteration < Kvalues.size()){
                inputvector.set(i, inputvector.get(i) * Kvalues.get(currentIteration - 1));
            }
            //else just use the last scale factor in our list. It converges so this isn't really a problem with approximations.
            else {
                inputvector.set(i, inputvector.get(i) * Kvalues.get(Kvalues.size() - 1));
            }
        }
        switch (quadrant){
            case 1 : inputvector.set(0,inputvector.get(0) * -1);
            break;
            case 2 : inputvector.set(0,inputvector.get(0) * -1);
            inputvector.set(1,inputvector.get(1) * -1);
            break;
            case 3 : inputvector.set(1,inputvector.get(1) * -1);
            default: break;
        }
        return inputvector;
    }
}


