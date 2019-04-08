package dhbw.vertsys.watchlist.dashboard;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Hilfsklasse zur Beschreibung einer Dashboard-Rubrik und ihrer Kacheln.
 */
@Data
public class DashboardSection {

    private String label = "";
    private List<DashboardTile> tiles = new ArrayList<>();
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public DashboardSection() {
    }
    
    public DashboardSection(String label, List<DashboardTile> tiles) {
        this.label = label;
        this.tiles = tiles;
    }
    //</editor-fold>
}
