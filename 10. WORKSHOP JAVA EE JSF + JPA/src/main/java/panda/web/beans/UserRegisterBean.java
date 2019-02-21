package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.binding.UserRegisterBindingModel;
import panda.domain.models.service.UserServiceModel;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {
    private UserRegisterBindingModel userRegisterBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return this.userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        if(!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("register.xhtml");
            return;
        }

        this.userService
                .userRegister(
                        this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class)
                );

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("login.xhtml");
    }
}
