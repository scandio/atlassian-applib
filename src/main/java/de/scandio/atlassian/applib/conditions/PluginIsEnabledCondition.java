package de.scandio.atlassian.applib.conditions;

import com.atlassian.plugin.PluginAccessor;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.Condition;

import java.util.Map;

/**
 * Applies when a plugin is enabled.
 */
public class PluginIsEnabledCondition implements Condition {

    private PluginAccessor pluginAccessor;
    private String pluginKey;

    @Override
    public void init(Map<String, String> map) throws PluginParseException {
        this.pluginKey = map.get("pluginKey");
    }

    @Override
    public boolean shouldDisplay(Map<String, Object> map) {

        return pluginAccessor.isPluginEnabled(this.pluginKey);
    }

    public void setPluginAccessor(PluginAccessor pluginAccessor) {
        this.pluginAccessor = pluginAccessor;
    }
}
