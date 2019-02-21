package panda.service;

import panda.domain.entity.Status;
import panda.domain.models.service.PackageServiceModel;

import java.util.List;

public interface PackageService {
    void packageCreate(PackageServiceModel packageServiceModel);

    List<PackageServiceModel> findAllPackagesByStatus(Status status);
}