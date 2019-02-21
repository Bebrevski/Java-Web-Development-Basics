package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.service.UserServiceModel;
import panda.domain.models.view.PackageViewModel;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {
    private List<PackageViewModel> pending;

    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initPackages();
    }

    private void initPackages() {
        UserServiceModel userServiceModel = this.userService
                .findUserByUsername((String) ((HttpSession) FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .getSession(true))
                        .getAttribute("username"));
        this.pending = userServiceModel
                .getPackages()
                .stream()
                .filter(p -> p.getStatus().name().equals("Pending"))
                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> getPackages() {
        return this.pending;
    }

    public void setPackages(List<PackageViewModel> packages) {
        this.pending = packages;
    }
}
