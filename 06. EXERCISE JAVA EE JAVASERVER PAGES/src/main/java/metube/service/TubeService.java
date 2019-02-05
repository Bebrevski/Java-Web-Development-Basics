package metube.service;

import metube.domain.models.service.TubeServiceModel;

public interface TubeService {
    void saveTube(TubeServiceModel tubeServiceModel);

    TubeServiceModel findTubeByName(String name);
}
