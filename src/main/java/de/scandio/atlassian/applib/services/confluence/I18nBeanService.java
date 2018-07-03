package de.scandio.atlassian.applib.services.confluence;

import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.util.i18n.I18NBean;

/**
 * Loads the I18nBean object for a given user.
 */
public interface I18nBeanService {

    I18NBean getI18NBean(ConfluenceUser user);

    I18NBean getSiteDefaultI18NBean();

}
