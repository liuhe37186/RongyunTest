package txcap.com.rongyuntest;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.model.Conversation;

/**
 * Created by weiqinxiao on 15/11/5.
 */
public class ConversationListAdapterEx extends ConversationListAdapter {
    public ConversationListAdapterEx(Context context) {
        super(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup group) {
        return super.newView(context, position, group);
    }

    @Override
    protected void bindView(View v, int position, UIConversation data) {
        if (data != null) {
            if (data.getConversationType().equals(Conversation.ConversationType.SYSTEM)){
                data.setConversationGatherState(false);
                data.setUnreadType(UIConversation.UnreadRemindType.REMIND_ONLY);
                data.setUIConversationTitle("互动消息");
                data.setIconUrl(Uri.parse("https://test3.txdsd.com/platform-erp/images/imagesteam/logo.png"));
                Log.e("xxxx","uri"+data.getIconUrl().toString());

                /*if(data.getMessageContent() instanceof NewFriendsMessage){
                    data.setUIConversationTitle("新的粉丝");
                }else {
                    data.setUIConversationTitle("系统消息");
                }*/
            }

        }
        super.bindView(v, position, data);
    }
}
