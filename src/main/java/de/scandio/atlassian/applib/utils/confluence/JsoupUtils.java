package de.scandio.atlassian.applib.utils.confluence;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.web.util.HtmlUtils;

/**
 * Parses and serializes XHTML with Jsoup.
 */
public class JsoupUtils {

    public static Document parseXhtml(String xhtml) {
        Document document = Jsoup.parse(xhtml, "", Parser.xmlParser());
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));
        return document;
    }

    public static String serializeXhtml(Document document) {
        return restoreCDATA(
                restoreCDATA(document.html(),
                "ac:plain-text-body"),
                "ac:plain-text-link-body")
                .replace("<br>", "<br/>");
    }

    /**
     * Borrowed from: https://github.com/jhy/jsoup/issues/406#issuecomment-255584560
     *
     * Restores plain text in HTML to it's CDATA equivalent.
     * For example, jsoup parses CDATA section and returns HTML escaped string. This function restores it.
     * This is a
     *
     * @param html The html code
     * @param tag The enclosing tag of the text that shall be restored.
     * @return
     */
    private static String restoreCDATA(String html, String tag) {
        int startIdx;
        int endIdx;
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag + ">";

        // 1. Find next occurrence
        startIdx = html.indexOf(startTag);
        while (startIdx >= 0) {

            // 2. Find end boundary
            endIdx = html.indexOf(endTag, startIdx);
            if (endIdx < 0) break;

            // 3. Replace with "unescaped" text
            startIdx += startTag.length();
            html = html.substring(0, startIdx) + "<![CDATA[" + HtmlUtils.htmlUnescape(html.substring(startIdx, endIdx)) + "]]>" + html.substring(endIdx, html.length());

            // 5. Repeat for all occurrences
            startIdx = html.indexOf(startTag, endIdx + endTag.length());
        }
        return html;
    }
}
