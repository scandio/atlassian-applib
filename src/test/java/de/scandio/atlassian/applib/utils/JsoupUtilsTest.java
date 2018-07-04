package de.scandio.atlassian.applib.utils;

import de.scandio.atlassian.applib.utils.confluence.JsoupUtils;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsoupUtilsTest {

    @Test
    public void restoreCdataPlainText() {
        String xhtmlWithCdata = "<ac:structured-macro ac:name=\"code\" ac:schema-version=\"1\" ac:macro-id=\"5940c830-c9eb-4d51-95cb-ec1b5fdd01c1\"><ac:plain-text-body><![CDATA[test]]></ac:plain-text-body></ac:structured-macro>";
        Document document = JsoupUtils.parseXhtml(xhtmlWithCdata);
        assertEquals(xhtmlWithCdata, JsoupUtils.serializeXhtml(document));
    }

    @Test
    public void restoreCdataPlainTextLink() {
        String xhtmlWithCdata = "<ac:link ac:anchor=\"test\"><ac:plain-text-link-body><![CDATA[123#test]]></ac:plain-text-link-body></ac:link>";
        Document document = JsoupUtils.parseXhtml(xhtmlWithCdata);
        assertEquals(xhtmlWithCdata, JsoupUtils.serializeXhtml(document));
    }

    @Test
    public void replaceBr() {
        Document document = JsoupUtils.parseXhtml("<p><br></p>");
        assertEquals("<p><br/></p>", JsoupUtils.serializeXhtml(document));
    }

}