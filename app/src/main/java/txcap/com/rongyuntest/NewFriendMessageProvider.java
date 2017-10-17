package txcap.com.rongyuntest;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;

/**
 * 文件名:txcap.com.rongyuntest.NewFriendMessageProvider
 * 描 述:
 * 作 者:liuhe
 * 时 间:2017-10-11 15:35
 */

@ProviderTag(messageContent = NewFriendsMessage.class)
public class NewFriendMessageProvider extends IContainerItemProvider.MessageProvider<NewFriendsMessage> {

    class ViewHolder{
        TextView message;
    }

    @Override
    public void bindView(View view, int i, NewFriendsMessage newFriendsMessage, UIMessage uiMessage) {

    }

    @Override
    public Spannable getContentSummary(NewFriendsMessage newFriendsMessage) {
        return new SpannableString("这是一条自定义消息");
    }

    @Override
    public void onItemClick(View view, int i, NewFriendsMessage newFriendsMessage, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        return null;
    }
}
