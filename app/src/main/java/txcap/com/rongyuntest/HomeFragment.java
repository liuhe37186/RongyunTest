package txcap.com.rongyuntest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * 文件名:txcap.com.rongyuntest.HomeFragment
 * 描 述:
 * 作 者:liuhe
 * 时 间:2017-10-10 15:23
 */

public class HomeFragment extends Fragment {

    private static HomeFragment instance = null;

    public static HomeFragment getInstance(){
        if(instance == null){
            instance = new HomeFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Button button = new Button(getActivity());
        button.setText("HomeFragment");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RongIM.getInstance() != null){
                    RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE,"10086","单聊");
                }
            }
        });
        return button;
    }
}
