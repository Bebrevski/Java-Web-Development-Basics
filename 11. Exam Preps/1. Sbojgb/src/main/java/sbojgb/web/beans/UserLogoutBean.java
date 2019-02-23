package sbojgb.web.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named("userLogoutBean")
@RequestScoped
public class UserLogoutBean extends BaseBean{
    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        session.invalidate();

        this.redirect("index");
    }
}
