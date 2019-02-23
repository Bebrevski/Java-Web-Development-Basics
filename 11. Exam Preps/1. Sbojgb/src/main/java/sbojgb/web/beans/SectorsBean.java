package sbojgb.web.beans;

import sbojgb.domain.entities.Sector;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("sectorBean")
@RequestScoped
public class SectorsBean {
    private List<String> sectors = new ArrayList<>();

    public SectorsBean() {
    }

    @PostConstruct
    private void init() {
        for (Sector sector : Sector.values()) {
            sectors.add(sector.name());
        }
    }

    public List<String> getSectors() {
        return this.sectors;
    }

    public void setSectors(List<String> sectors) {
        this.sectors = sectors;
    }
}
