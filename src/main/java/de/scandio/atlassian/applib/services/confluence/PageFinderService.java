package de.scandio.atlassian.applib.services.confluence;

import com.atlassian.confluence.pages.Page;

import java.util.List;

/**
 * Finds pages with a specific macro and/or with a specific parameter and value.
 */
public interface PageFinderService {

    List<Page> findPagesWithMacro(String macroName, String spaceKey);

    List<Page> findPagesWithMacroParameter(String macroName, String parameterKey, String parameterValue, String spaceKey);
}
