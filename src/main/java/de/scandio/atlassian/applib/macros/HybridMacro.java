package de.scandio.atlassian.applib.macros;

import com.atlassian.confluence.content.render.xhtml.DefaultConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.RenderMode;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.MacroException;

import java.util.Map;

/**
 * By extending this class, a macro can added as both an old `macro` module and a `xhtml-macro` module.
 */
public abstract class HybridMacro extends BaseMacro implements Macro {

    @Override
    public String execute(Map parameters, String body, RenderContext renderContext) throws MacroException {
        try {
            return this.execute(parameters, body, new DefaultConversionContext(renderContext));
        } catch (MacroExecutionException e) {
            throw new MacroException(e);
        }
    }

    @Override
    public boolean hasBody() {
        return !Macro.BodyType.NONE.equals(this.getBodyType());
    }

    @Override
    public RenderMode getBodyRenderMode() {
        return Macro.OutputType.INLINE.equals(this.getOutputType())
                ? RenderMode.INLINE
                : Macro.BodyType.RICH_TEXT.equals(this.getBodyType())
                ? RenderMode.ALL
                : RenderMode.NO_RENDER;
    }
}
