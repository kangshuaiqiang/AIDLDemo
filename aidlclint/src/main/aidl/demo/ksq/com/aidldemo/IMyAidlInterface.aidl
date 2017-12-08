// IMyAidlInterface.aidl
package demo.ksq.com.aidldemo;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            //计算两个数的和
            int add(int num1,int num2);

}
