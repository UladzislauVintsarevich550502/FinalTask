package bsuir.vintsarevich.tag;

import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspElemetName;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ActionTag extends TagSupport{

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(JspElemetName.USER.getValue());
        String action;

        if (user != null && user.getRole().equals("client")){
            action = "/front/jsp/client/action.jsp";
        }else{
            if (user != null && user.getRole().equals("admin")) {
                action = "/front/jsp/admin/action.jsp";
            }else{
                action = "/front/jsp/common/action.jsp";
            }
        }
        System.out.println(action);
        try {
            pageContext.include(action);
        } catch (IOException | ServletException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
