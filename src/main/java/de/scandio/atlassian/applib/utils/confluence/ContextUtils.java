package de.scandio.atlassian.applib.utils.confluence;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.xhtml.api.MacroDefinition;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Gets stuff from the conversion context.
 */
public class ContextUtils {

    public static String getBodyXhtml(ConversionContext context) throws IOException {
        String bodyStorageFormat = "";

        MacroDefinition macroDefinition = (MacroDefinition)context.getProperty("macroDefinition");
        if (macroDefinition != null) {
            StringWriter writer = new StringWriter();
            macroDefinition.getStorageBodyStream().writeTo(writer);
            bodyStorageFormat = writer.toString();
        }
        return bodyStorageFormat;
    }
}
