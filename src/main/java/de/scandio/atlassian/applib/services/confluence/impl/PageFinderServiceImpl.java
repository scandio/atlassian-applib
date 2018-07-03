package de.scandio.atlassian.applib.services.confluence.impl;

import com.atlassian.confluence.macro.query.BooleanQueryFactory;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.search.service.ContentTypeEnum;
import com.atlassian.confluence.search.v2.*;
import com.atlassian.confluence.search.v2.query.ContentTypeQuery;
import com.atlassian.confluence.search.v2.query.InSpaceQuery;
import com.atlassian.confluence.search.v2.query.MacroUsageQuery;
import com.atlassian.confluence.search.v2.searchfilter.SiteSearchPermissionsSearchFilter;
import com.atlassian.confluence.search.v2.searchfilter.SpacePermissionsSearchFilter;
import com.atlassian.confluence.search.v2.sort.ModifiedSort;
import de.scandio.atlassian.applib.services.confluence.PageFinderService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PageFinderServiceImpl implements PageFinderService {

    private SearchManager searchManager;

    @Override
    public List<Page> findPagesWithMacro(String macroName, String spaceKey) {
        BooleanQueryFactory query = new BooleanQueryFactory();
        query.addMust(new ContentTypeQuery(ContentTypeEnum.PAGE));
        query.addMust(new MacroUsageQuery(macroName));

        if (spaceKey != null) {
            query.addMust(new InSpaceQuery(spaceKey));
        }

        List<Page> result = new ArrayList<>();
        SearchResults searchResults;

        int startOffset = 0;
        do {
            ISearch search = new ContentSearch(query.toBooleanQuery(),
                    new ModifiedSort(SearchSort.Order.DESCENDING),
                    SpacePermissionsSearchFilter.getInstance().and(SiteSearchPermissionsSearchFilter.getInstance()),
                    startOffset,
                    SearchConstants.MAX_LIMIT);
            try {
                searchResults = searchManager.search(search);
            } catch (InvalidSearchException e) {
                throw new RuntimeException("Error searching for pages", e);
            }
            result.addAll((List<Page>) (List<?>) searchManager.convertToEntities(searchResults, SearchManager.EntityVersionPolicy.LATEST_VERSION));
            startOffset += SearchConstants.MAX_LIMIT;

        } while(searchResults.getUnfilteredResultsCount() > result.size());

        return result;
    }

    @Override
    public List<Page> findPagesWithMacroParameter(String macroName, String parameterKey, String parameterValue, String spaceKey) {
        return this.findPagesWithMacro(macroName, spaceKey)
                .stream()
                .filter(page -> {
                    Document document = Jsoup.parse(page.getBodyAsString(), "", Parser.xmlParser());
                    document.outputSettings(new Document.OutputSettings().prettyPrint(false));

                    Elements foundMacros = document.select("ac|structured-macro[ac:name=" + macroName + "]");

                    for (Element macro : foundMacros) {
                        String foundParameterValue = macro.select("> ac|parameter[ac:name=" + parameterKey + "]").html();

                        if (parameterValue.equals(foundParameterValue)) {
                            return true;
                        }
                    }
                    return false;
                }).collect(Collectors.toList());
    }

    public void setSearchManager(SearchManager searchManager) {
        this.searchManager = searchManager;
    }
}
