package txcap.com.rongyuntest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


/**
 * 会话页面
 * 1，设置 ActionBar title
 * 2，加载会话页面
 * 3，push 和 通知 判断
 */
public class ConversationActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

    }

}
