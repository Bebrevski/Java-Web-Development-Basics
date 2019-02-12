package metubev2.service;

import metubev2.domain.entity.Tube;
import metubev2.domain.models.service.TubeServiceModel;
import metubev2.repository.TubeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper, UserService userService) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public boolean uploadTube(TubeServiceModel tubeServiceModel) {
       tubeServiceModel.setUploader(this.userService.findUserByUsername(tubeServiceModel.getUploader().getUsername()));

       try{
           this.tubeRepository.save(this.modelMapper.map(tubeServiceModel, Tube.class));
       } catch (Exception e){
           return false;
       }

       return true;
    }
}
