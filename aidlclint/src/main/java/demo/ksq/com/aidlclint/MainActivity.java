package demo.ksq.com.aidlclint;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import demo.ksq.com.aidldemo.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private EditText ed1;
    private EditText ed2;
    private Button button;
    private TextView textView;
    private IMyAidlInterface iMyAidlInterface;
    private ServiceConnection conn = new ServiceConnection() {


        //绑定上服务的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //拿到了远程的服务
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);

        }

        //断开服务的时候
        @Override
        public void onServiceDisconnected(ComponentName name) {
            //回收
            iMyAidlInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.edit1);
        ed2 = findViewById(R.id.edit2);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.tv);
        //只要一启动就会绑定服务
        bindSerVice();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用远程服务
                int num1 = Integer.parseInt(ed1.getText().toString());
                int num2 = Integer.parseInt(ed2.getText().toString());

                try {
                    int add = iMyAidlInterface.add(num1, num2);
                    textView.setText(add + "");
                }catch (Exception e){

                }




            }
        });


    }


    public void bindSerVice() {
        //获取服务端   直接绑定服务
        Intent intent = new Intent();
        //服务端的包名和类名   是全类名绑定服务 必须显示intent去启动绑定服务   5.0后新加
        intent.setComponent(new ComponentName("demo.ksq.com.aidldemo", "demo.ksq.com.aidldemo.IRemoteService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindService(conn);
    }
}
