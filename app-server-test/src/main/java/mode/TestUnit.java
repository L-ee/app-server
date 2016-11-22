package mode;

import java.util.Arrays;

/**
 * Author:  Administrator
 * Date:    2016/10/15 10:41
 * Function:Please input the function of this class!
 */
public class TestUnit {

    private short aShort;
    private double aDouble;
    private byte aByte;
    private char aChar;
    private long aLong;
    private float aFloat;
    private boolean aBoolean;
    private int[] aIntArr;
    private TestUnit testUnit;
    private TestUnit[] testUnitArr;


    public boolean equals(Object obj){
        if(!(obj instanceof TestUnit)){
            return false;
        }

        TestUnit unit = (TestUnit) obj;

        if(unit.aByte == this.aByte && unit.aChar == this.aChar
                && unit.aLong == this.aLong && unit.aShort == this.aShort
                && unit.aBoolean == this.aBoolean
                && Float.floatToIntBits(unit.aFloat) == Float.floatToIntBits(this.aFloat)
                && Double.doubleToLongBits(unit.aDouble) == Double.doubleToLongBits(this.aDouble)
                && unit.testUnit.equals(this.testUnit)
                && Arrays.equals(unit.aIntArr,this.aIntArr)
                && Arrays.equals(unit.testUnitArr,this.testUnitArr)){
            return true;
        } else {
            return false;
        }
    }

    public int hashCode(){

        int hashcode = 17;
        hashcode = 31 * hashcode + (int)this.aShort;
        hashcode = 31 * hashcode + (int)this.aChar;
        hashcode = 31 * hashcode + (int)this.aByte;
        hashcode = 31 * hashcode + Float.floatToIntBits(this.aFloat);
        hashcode = 31 * hashcode + (aBoolean ? 0 : 1);
        hashcode = 31 * hashcode + (int)(aLong ^ (aLong >>> 32));
        hashcode = 31 * hashcode + (int)(Double.doubleToLongBits(this.aDouble) ^ (aLong >>> 32));
        hashcode = 31 * hashcode + this.testUnit.hashCode();
        hashcode = 31 * hashcode + intArrHashCode();
        hashcode = 31 * hashcode + unitArrHashCode();

        return hashcode;

    }

    private int unitArrHashCode(){
        int hashcode = 17;
        for (TestUnit unit : testUnitArr) {
            hashcode = 31 * hashcode + unit.hashCode();
        }
        return hashcode;
    }

    private int intArrHashCode(){
        int hashcode = 17;
        for (int i : aIntArr) {
            hashcode = 31 * hashcode + i;
        }
        return hashcode;
    }




}
