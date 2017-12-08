package demo.ksq.com.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 黑白 on 2017/12/8.
 */

public class IRemoteService extends Service {
    /**
     * 当客户端绑定到该服务的时候会调用
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IMyAidlInterface.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int num1, int num2) throws RemoteException {

            Log.d("zzzzzz", "收到了远程请求数据，输入的内容是" + num1 + "====" + num2);

            return num1 + num2;
        }
    };
}
