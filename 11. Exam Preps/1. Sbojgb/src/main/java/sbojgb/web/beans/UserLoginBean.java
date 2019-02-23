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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named("userLoginBean")
@RequestScoped
public class UserLoginBean extends BaseBean {
    private UserLoginBindingModel model;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.model = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getModel() {
        return this.model;
    }

    public void setModel(UserLoginBindingModel model) {
        this.model = model;
    }

    public void login() {
        UserServiceModel userServiceModel = this.userService
                .loginUser(this.modelMapper.map(model, UserServiceModel.class));

        if (userServiceModel == null){
            throw new IllegalArgumentException("USER DID NOT LOGIN!!!!!!!!!!!!!!");
        }

        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();

        HttpSession session = request.getSession();

        session.setAttribute("username", userServiceModel.getUsername());
        session.setAttribute("id", userServiceModel.getId());

        this.redirect("home");
    }
}
