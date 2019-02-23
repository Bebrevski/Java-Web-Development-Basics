package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.models.binding.UserRegisterBindingModel;
import sbojgb.domain.models.service.UserServiceModel;
import sbojgb.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("userRegisterBean")
@RequestScoped
public class UserRegisterBean extends BaseBean{
    private UserRegisterBindingModel model;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.model = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getModel() {
        return this.model;
    }

    public void setModel(UserRegisterBindingModel model) {
        this.model = model;
    }

    public void register(){
        if (!this.model.getPassword().equals(this.model.getConfirmPassword())){
            throw new IllegalArgumentException("PASSWORDS DON'T MATCH!!!!!!!!!!!!!!!!!");
        }

        if (!this.userService.registerUser(this.modelMapper.map(this.model, UserServiceModel.class))){
            throw new IllegalArgumentException("SOMETHING WENT WRONG!!!!!!!!!!!!!!!!!!");
        }

        this.redirect("login");
    }
}
