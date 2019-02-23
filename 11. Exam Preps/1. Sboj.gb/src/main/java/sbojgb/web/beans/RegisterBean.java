package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.models.binding.UserRegisterBindingModel;
import sbojgb.domain.models.service.UserServiceModel;
import sbojgb.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named("registerBean")
@RequestScoped
public class RegisterBean extends BaseBean{
    private UserRegisterBindingModel userRegisterBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return this.userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register(){
        if(!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())) {
            this.redirect("register'");
            return;
        }

        this.userService.userRegister(
                this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class)
        );

        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);

        session.setAttribute("username", userRegisterBindingModel.getUsername());

        this.redirect("home");
    }
}
