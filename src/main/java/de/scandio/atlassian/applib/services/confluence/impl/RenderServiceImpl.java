package de.scandio.atlassian.applib.services.confluence.impl;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.content.render.xhtml.DefaultConversionContext;
import com.atlassian.confluence.content.render.xhtml.XhtmlException;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.renderer.PageContext;
import com.atlassian.confluence.xhtml.api.XhtmlContent;
import de.scandio.atlassian.applib.services.confluence.RenderService;

import javax.xml.stream.XMLStreamException;

public class RenderServiceImpl implements RenderService {

    private XhtmlContent xhtmlContent;

    @Override
    public String renderXhtml(String xhtml, ConversionContext context) throws XhtmlException, XMLStreamException {
        if (context == null) {
            context = new DefaultConversionContext(new Page().toPageContext());
        }
        return this.xhtmlContent.convertStorageToView(xhtml, context);
    }

    @Override
    public String renderXhtml(String xhtml, PageContext pageContext) throws XhtmlException, XMLStreamException {
        if (pageContext == null) {
            pageContext = new Page().toPageContext();
        }
        return this.renderXhtml(xhtml, new DefaultConversionContext(pageContext));
    }

    @Override
    public String renderXhtml(String xhtml, Page page) throws XhtmlException, XMLStreamException {
        if (page == null) {
            page = new Page();
        }
        return this.renderXhtml(xhtml, new DefaultConversionContext(page.toPageContext()));
    }

    @Override
    public String renderXhtml(String xhtml) throws XhtmlException, XMLStreamException {
        return this.renderXhtml(xhtml, (ConversionContext) null);
    }

    public void setXhtmlContent(XhtmlContent xhtmlContent) {
        this.xhtmlContent = xhtmlContent;
    }
}
