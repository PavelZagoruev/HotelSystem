package by.gstu.hotelsystem.tags;

import by.gstu.hotelsystem.util.TranslateUtility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
/**
 * Created by Pavel on 23.01.2017.
 */
public class TranslateTag extends TagSupport{
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(TranslateUtility.getValue(key));
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }
}
