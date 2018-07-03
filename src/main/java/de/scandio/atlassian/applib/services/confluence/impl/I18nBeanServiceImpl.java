package de.scandio.atlassian.applib.services.confluence.impl;

import com.atlassian.confluence.languages.LocaleManager;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.util.i18n.I18NBean;
import com.atlassian.confluence.util.i18n.I18NBeanFactory;
import de.scandio.atlassian.applib.services.confluence.I18nBeanService;

import java.util.Locale;

public class I18nBeanServiceImpl implements I18nBeanService {

    private I18NBeanFactory i18NBeanFactory;
    private LocaleManager localeManager;

    @Override
    public I18NBean getI18NBean(ConfluenceUser user) {
        Locale locale = localeManager.getLocale(user);
        return i18NBeanFactory.getI18NBean(locale);
    }

    @Override
    public I18NBean getSiteDefaultI18NBean() {
        Locale locale = localeManager.getSiteDefaultLocale();
        return i18NBeanFactory.getI18NBean(locale);
    }

    public void setI18NBeanFactory(I18NBeanFactory i18NBeanFactory) {
        this.i18NBeanFactory = i18NBeanFactory;
    }

    public void setLocaleManager(LocaleManager localeManager) {
        this.localeManager = localeManager;
    }
}
