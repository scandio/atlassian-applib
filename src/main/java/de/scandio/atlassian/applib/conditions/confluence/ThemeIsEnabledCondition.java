package de.scandio.atlassian.applib.conditions.confluence;

import com.atlassian.confluence.themes.ThemeManager;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.Condition;
import de.scandio.atlassian.applib.utils.confluence.CurrentSpaceUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Applies when a theme is enabled.
 */
public class ThemeIsEnabledCondition implements Condition {

    private ThemeManager themeManager;

    protected String themeKey;

    @Override
    public void init(Map<String, String> map) throws PluginParseException {
        this.themeKey = map.get("themeKey");
    }

    @Override
    public boolean shouldDisplay(Map<String, Object> map) {
        String themeKey = null;

        String currentSpaceKey = CurrentSpaceUtils.getCurrentSpaceKey();

        if (currentSpaceKey != null) {
            String spaceThemeKey = themeManager.getSpaceThemeKey(currentSpaceKey);
            if (StringUtils.isNotBlank(spaceThemeKey)) {
                themeKey = spaceThemeKey;
            }
        }

        if (themeKey == null) {
            themeKey = themeManager.getGlobalThemeKey();
        }

       return this.themeKey.equals(themeKey);
    }

    public void setThemeManager(ThemeManager themeManager) {
        this.themeManager = themeManager;
    }
}
