package de.scandio.atlassian.applib.services.confluence;

import com.atlassian.confluence.core.DateFormatter;
import com.atlassian.confluence.user.ConfluenceUser;

/**
 * Loads the DateFormatter for a given user.
 */
public interface FormatterService {

    DateFormatter getDateFormatter(ConfluenceUser user);
}
