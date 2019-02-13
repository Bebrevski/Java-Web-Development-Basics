package metubev2.repository;

import metubev2.domain.entity.Tube;

public interface TubeRepository extends GenericRepository<Tube, String> {

    Tube update(Tube entity);
}
