package de.scandio.atlassian.applib.services.confluence.impl;

import com.atlassian.config.util.BootstrapUtils;
import com.atlassian.confluence.core.DateFormatter;
import com.atlassian.confluence.core.FormatSettingsManager;
import com.atlassian.confluence.core.TimeZone;
import com.atlassian.confluence.languages.LocaleManager;
import com.atlassian.confluence.setup.BootstrapManager;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.user.UserAccessor;
import com.atlassian.sal.api.timezone.TimeZoneManager;
import de.scandio.atlassian.applib.services.confluence.FormatterService;

public class FormatterServiceImpl implements FormatterService {

    private UserAccessor userAccessor;
    private TimeZoneManager timeZoneManager;
    private FormatSettingsManager formatSettingsManager;
    private LocaleManager localeManager;

    @Override
    public DateFormatter getDateFormatter(ConfluenceUser user) {
        BootstrapManager bootstrapManager = (BootstrapManager) BootstrapUtils.getBootstrapManager();

        TimeZone timeZone;

        if (bootstrapManager.isSetupComplete() && user != null) {
            timeZone = userAccessor.getConfluenceUserPreferences(user).getTimeZone();
        } else {
            timeZone = TimeZone.getInstance(timeZoneManager.getDefaultTimeZone().getID());
        }

        return new DateFormatter(timeZone, formatSettingsManager, localeManager);
    }

    public void setUserAccessor(UserAccessor userAccessor) {
        this.userAccessor = userAccessor;
    }

    public void setTimeZoneManager(TimeZoneManager timeZoneManager) {
        this.timeZoneManager = timeZoneManager;
    }

    public void setFormatSettingsManager(FormatSettingsManager formatSettingsManager) {
        this.formatSettingsManager = formatSettingsManager;
    }

    public void setLocaleManager(LocaleManager localeManager) {
        this.localeManager = localeManager;
    }
}
