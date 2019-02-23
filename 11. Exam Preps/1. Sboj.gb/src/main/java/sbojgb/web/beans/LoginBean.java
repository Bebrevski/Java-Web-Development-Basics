package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.models.binding.UserLoginBindingModel;
import sbojgb.domain.models.service.UserServiceModel;
import sbojgb.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named("loginBean")
@RequestScoped
public class LoginBean extends BaseBean {
    private UserLoginBindingModel userLoginBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public void login() throws IOException {
        UserServiceModel userServiceModel = this.userService
                .userLogin(
                        this.modelMapper.map(userLoginBindingModel, UserServiceModel.class)
                );

        if(userServiceModel == null) {
            this.redirect("login");
            return;
        }

        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);

        session.setAttribute("username", userServiceModel.getUsername());

        this.redirect("home");
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return this.userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }
}
