package de.scandio.atlassian.applib.conditions.confluence;

import com.atlassian.confluence.themes.ThemeManager;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.Condition;

import java.util.Map;

/**
 * Applies when a theme is enabled globally.
 *
 * @author Georg Schmidl
 */
public class ThemeIsGloballyEnabledCondition implements Condition {

    private ThemeManager themeManager;

    protected String themeKey;

    @Override
    public void init(Map<String, String> map) throws PluginParseException {
        this.themeKey = map.get("themeKey");
    }

    @Override
    public boolean shouldDisplay(Map<String, Object> map) {
        return this.themeKey.equals(themeManager.getGlobalThemeKey());
    }

    public void setThemeManager(ThemeManager themeManager) {
        this.themeManager = themeManager;
    }
}
