# Atlassian AppLib

Library that makes developing Atlassian apps easier.

## Usage

Add the atlassian-applib to your `pom.xml`.

    <dependency>
        <groupId>de.scandio</groupId>
        <artifactId>atlassian-applib</artifactId>
        <version>0.1.0</version>
    </dependency>


## Services

Services are added as components to the `atlassian-plugin.xml`.

_Confluence:_

### FormatterService

Loads the DateFormatter for a given user.

     <component key="formatter-service" name="Formatter Service"
                class="de.scandio.atlassian.applib.services.confluence.impl.FormatterServiceImpl">
         <interface>de.scandio.atlassian.applib.services.confluence.FormatterService</interface>
     </component>
     
### I18nBeanService

Loads the I18nBean object for a given user.

    <component key="i18n-bean-service" name="I18n Bean Service"
               class="de.scandio.atlassian.applib.services.confluence.impl.I18nBeanServiceImpl">
        <interface>de.scandio.atlassian.applib.services.confluence.I18nBeanService</interface>
    </component>
    
### PageFinderService    

Finds pages with a specific macro and/or with a specific parameter and value.
    
    <component key="page-finder-service" name="Page Finder Service"
               class="de.scandio.atlassian.applib.services.confluence.impl.PageFinderServiceImpl">
        <interface>de.scandio.atlassian.applib.services.confluence.PageFinderService</interface>
    </component>
  
    
## Utils

- CommaUtils - Joins and splits comma separated lists.
- JsoupUtils - Parses and serializes XHTML with Jsoup.
- VersionUtils - Compares versions.

_Confluence:_

- CurrentSpaceUtils - Gets the current space.


## Conditions

Use conditions in your web items and web resources.

### PluginIsEnabledCondition

Applies when a plugin is enabled.

    <condition class="de.scandio.atlassian.applib.conditions.PluginIsEnabledCondition">
        <param name="themeKey">de.scandio.confluence.plugins.lively-theme</param>
    </condition>
    
_Confluence:_

### ThemeIsEnabledCondition

Applies when a theme is enabled.

    <condition class="de.scandio.atlassian.applib.conditions.confluence.ThemeIsEnabledCondition">
        <param name="themeKey">de.scandio.confluence.plugins.lively-theme:lively-theme</param>
    </condition>
    
### ThemeIsGloballyEnabledCondition

Applies when a theme is enabled globally.

    <condition class="de.scandio.atlassian.applib.conditions.confluence.ThemeIsGloballyEnabledCondition">
        <param name="themeKey">de.scandio.confluence.plugins.lively-theme:lively-theme</param>
    </condition>
    