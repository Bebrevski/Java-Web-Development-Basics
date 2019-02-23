package app.web.beans;

import app.domain.model.binding.UserRegisterBindingModel;
import app.domain.model.service.UserServiceModel;
import app.service.UserService;
import org.modelmapper.ModelMapper;

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
//        if (!this.model.getPassword().equals(this.model.getConfirmPassword())){
//            this.redirect("register"); //ForPrettyFaces may need to be "/register" and so on
//            return;
//        }
//
//        if (!this.userService.registerUser(this.modelMapper.map(this.model, UserServiceModel.class))){
//            throw new IllegalArgumentException("SOMETHING WENT WRONG!!!!!!!!!!!!!!!!!!");
//        }
//
//        this.redirect("login");
    }
}
