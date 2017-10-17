package txcap.com.rongyuntest;

import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;


/**
 * 文件名:txcap.com.rongyuntest.NewFriendsMessage
 * 描 述:
 * 作 者:liuhe
 * 时 间:2017-10-11 11:08
 */

@MessageTag(value = "app:newFriend",flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class NewFriendsMessage extends MessageContent {
    private String content;

    public NewFriendsMessage(byte[] data){
        String jsonStr = null;

        try {
            jsonStr = new String(data,"UTF-8");
            JSONObject jsonObject = new JSONObject(jsonStr);
            if(jsonObject.has("content")){
                content = jsonObject.optString("content");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public NewFriendsMessage(Parcel in){
        content = ParcelUtils.readFromParcel(in);
    }

    public static final Creator<NewFriendsMessage> CREATOR = new Creator<NewFriendsMessage>() {
        @Override
        public NewFriendsMessage createFromParcel(Parcel source) {
            return new NewFriendsMessage(source);
        }

        @Override
        public NewFriendsMessage[] newArray(int size) {
            return new NewFriendsMessage[size];
        }
    };

    @Override
    public byte[] encode() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("content","消息内容");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("xxx","JSONException:"+e.getMessage());
        }

        try {
            return jsonObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        ParcelUtils.writeToParcel(dest,content);

    }


}
