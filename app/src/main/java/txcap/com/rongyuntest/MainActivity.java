package txcap.com.rongyuntest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ContactNotificationMessage;

public class MainActivity extends FragmentActivity implements IUnReadMessageObserver {



    List<Fragment> mFragment = new ArrayList<>();


    private ViewPager mViewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;

    /**
     * 会话列表的fragment
     */
    private ConversationListFragment mConversationListFragment = null;
    private Fragment conversationFragment;
    private boolean isDebug;
    private Context mContext;
    private Conversation.ConversationType[] mConversationsTypes = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        conversationFragment = initConversationList();
        HomeFragment homeFragment = HomeFragment.getInstance();
        mFragment.add(conversationFragment);
        mFragment.add(homeFragment);
//        initData();
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        mViewPager.setAdapter(fragmentPagerAdapter);


    }





    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "true")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "true")//订阅号
//                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .build();
            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }

    protected void initData() {

        final Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
        };

        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, conversationTypes);
        getConversationPush();// 获取 push 的 id 和 target
        getPushMessage();
    }

    private void getConversationPush() {
        if (getIntent() != null && getIntent().hasExtra("PUSH_CONVERSATIONTYPE") && getIntent().hasExtra("PUSH_TARGETID")) {

            final String conversationType = getIntent().getStringExtra("PUSH_CONVERSATIONTYPE");
            final String targetId = getIntent().getStringExtra("PUSH_TARGETID");


            RongIM.getInstance().getConversation(Conversation.ConversationType.valueOf(conversationType), targetId, new RongIMClient.ResultCallback<Conversation>() {
                @Override
                public void onSuccess(Conversation conversation) {

                    if (conversation != null) {

                        if (conversation.getLatestMessage() instanceof ContactNotificationMessage) { //好友消息的push
//                            startActivity(new Intent(MainActivity.this, NewFriendListActivity.class));
                        } else {
                            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon().appendPath("conversation")
                                    .appendPath(conversationType).appendQueryParameter("targetId", targetId).build();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {

                }
            });
        }
    }

    /**
     * 得到不落地 push 消息
     */
    private void getPushMessage() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {
            String path = intent.getData().getPath();
            if (path.contains("push_message")) {
                SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
                String cacheToken = sharedPreferences.getString("loginToken", "");
                if (TextUtils.isEmpty(cacheToken)) {
//                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    if (!RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED)) {
//                        LoadDialog.show(mContext);
//                        RongIM.connect(cacheToken, new RongIMClient.ConnectCallback() {
//                            @Override
//                            public void onTokenIncorrect() {
//                                LoadDialog.dismiss(mContext);
//                            }
//
//                            @Override
//                            public void onSuccess(String s) {
//                                LoadDialog.dismiss(mContext);
//                            }
//
//                            @Override
//                            public void onError(RongIMClient.ErrorCode e) {
//                                LoadDialog.dismiss(mContext);
//                            }
//                        });
                    }
                }
            }
        }
    }

    @Override
    public void onCountChanged(int count) {
//        if (count == 0) {
//            mUnreadNumView.setVisibility(View.GONE);
//        } else if (count > 0 && count < 100) {
//            mUnreadNumView.setVisibility(View.VISIBLE);
//            mUnreadNumView.setText(String.valueOf(count));
//        } else {
//            mUnreadNumView.setVisibility(View.VISIBLE);
//            mUnreadNumView.setText(R.string.no_read_message);
//        }
    }

}
