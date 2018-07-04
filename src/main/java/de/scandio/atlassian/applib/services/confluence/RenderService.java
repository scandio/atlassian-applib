package de.scandio.atlassian.applib.services.confluence;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.content.render.xhtml.XhtmlException;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.renderer.PageContext;

import javax.xml.stream.XMLStreamException;

/**
 * Renders XHTML to HTML.
 */
public interface RenderService {

    String renderXhtml(String xhtml, ConversionContext context) throws XhtmlException, XMLStreamException;

    String renderXhtml(String xhtml, PageContext pageContext) throws XhtmlException, XMLStreamException;

    String renderXhtml(String xhtml, Page page) throws XhtmlException, XMLStreamException;

    String renderXhtml(String xhtml) throws XhtmlException, XMLStreamException;
}
