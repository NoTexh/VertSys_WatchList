/*
 * Copyright © 2019 Markus Scheuring, Kai Schmid, Tobias Frietsch
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbw.vertsys.watchlist.model;

public enum MovieStatus {
    GESEHEN, NICHT_GESEHEN;

    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case GESEHEN:
                return "Watched";
            case NICHT_GESEHEN:
                return "to Watch";
            default:
                return this.toString();
        }
    }

    
}
