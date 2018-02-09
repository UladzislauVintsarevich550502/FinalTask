package bsuir.vintsarevich.tag;

import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspElemetName;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ListTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(JspElemetName.USER.getValue());
        String action;

        if (user != null && user.getRole().equals("client")) {
            action = "/front/jsp/client/list.jsp";
        } else {
            if (user != null && user.getRole().equals("staff")) {
                action = "/front/jsp/staff/list.jsp";
            } else {
                if (user != null && user.getRole().equals("admin")) {
                    action = "/front/jsp/admin/list.jsp";
                } else {
                    action = "/front/jsp/common/list.jsp";
                }
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
