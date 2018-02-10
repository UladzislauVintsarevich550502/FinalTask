package bsuir.vintsarevich.tag;

import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.AttributeName;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HomeTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(AttributeName.USER.getValue());
        String home;

        if (user != null && user.getRole().equals("staff")){
            home = "/front/jsp/staff/home.jsp";
        }else{
            home = "/front/jsp/common/home.jsp";
        }
        try {
            pageContext.include(home);
        } catch (IOException | ServletException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}