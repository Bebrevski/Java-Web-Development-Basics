package panda.repository;

import panda.domain.entity.Package;
import panda.domain.entity.Status;

import java.util.List;

public interface PackageRepository extends GenericRepository<Package, String> {
    List<Package> findAllPackagesByStatus(Status status);

    Package updatePackage(Package entity);
}
