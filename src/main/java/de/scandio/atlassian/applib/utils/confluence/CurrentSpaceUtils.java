package de.scandio.atlassian.applib.utils.confluence;

import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.themes.ThemeContext;
import com.atlassian.confluence.web.context.HttpContext;
import com.atlassian.confluence.web.context.StaticHttpContext;

/**
 * Gets the current space.
 */
public class CurrentSpaceUtils {

    public static Space getCurrentSpace() {
        Space currentSpace = null;

        HttpContext httpContext = new StaticHttpContext();
        ThemeContext themeContext = ThemeContext.get(httpContext.getRequest());

        if (themeContext != null) {
            if (themeContext.getSpace() != null) {
                currentSpace = themeContext.getSpace();
            }
        }

        return currentSpace;
    }

    public static String getCurrentSpaceKey() {
        Space currentSpace = getCurrentSpace();
        return currentSpace != null ? currentSpace.getKey() : null;
    }
}
