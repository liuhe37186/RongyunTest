package txcap.com.rongyuntest;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.ImagePlugin;
import io.rong.imkit.widget.provider.LocationPlugin;
import io.rong.imlib.model.Conversation;

/**
 * Created by txcap on 2017/10/17.
 */

public class MyExtensionModule extends DefaultExtensionModule {
    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
//        List<IEmoticonTab> emoticonTabs = super.getEmoticonTabs();
//        emoticonTabs.add(myEmoticon);
//        return emoticonTabs;
        return super.getEmoticonTabs();
    }

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
//        return super.getPluginModules(conversationType);
        List<IPluginModule> pluginModuleList = new ArrayList<>();
        pluginModuleList.add(new ImagePlugin());
        pluginModuleList.add(new LocationPlugin());
        return pluginModuleList;
    }
}
