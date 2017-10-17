package txcap.com.rongyuntest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class LoginActivity extends AppCompatActivity implements RongIM.UserInfoProvider,View.OnClickListener{

    private String token1 = "+wLSFsW5U/rMV1eNIWSElIBZ2oFNc7q9lN7Obgj4MPlEu0+2lziTbINmEak8z3EG+74SJheB0SVKDgFpEs0uE95WTTYaWQba";

    private String token2 = "gBppXfCOuiKH5xLDMLlJAIBZ2oFNc7q9lN7Obgj4MPlEu0+2lziTbADXeqVANNYWs7Ne8XJkqQ8GthpdNFFnaw==";
    Button b1,b2,b3,b4;
    UserInfo mUserInfo1,mUserInfo2;
    List<UserInfo> userInfoList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

        mUserInfo1 = new UserInfo("18800154599","刘贺", Uri.parse("https://test3.txdsd.com/platform-erp/images/imagesteam/logo.png"));
        mUserInfo2 = new UserInfo("10086","移动", Uri.parse("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2766148860,1117555599&fm=27&gp=0.jpg"));
        userInfoList.add(mUserInfo1);
        userInfoList.add(mUserInfo2);
//        RongIM.setUserInfoProvider(this,true);
    }
    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    private void connect(String token) {

//        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.e("xxxx","onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.e("xxxx", "--onSuccess" + userid);
                    if(userid.equals("10086")){
                        b2.setText("连接服务器成功");
                        b1.setEnabled(false);
                    }else {
                        b1.setText("连接服务器成功");
                        b2.setEnabled(false);
                    }

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.e("xxxx","errorCode"+errorCode);
                }
            });
//        }
    }

    @Override
    public UserInfo getUserInfo(String s) {

        for(UserInfo userInfo : userInfoList){
            if(userInfo.getUserId().equals(s)){
                return new UserInfo(userInfo.getUserId(),userInfo.getName(),userInfo.getPortraitUri());
            }
        }

        return null;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                RongIM.setUserInfoProvider(this,true);
                connect(token1);
                break;
            case R.id.btn2:
                RongIM.setUserInfoProvider(this,true);
                connect(token2);
                break;
            case R.id.btn3:
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                break;
            case R.id.btn4:

                break;
        }
    }
}
