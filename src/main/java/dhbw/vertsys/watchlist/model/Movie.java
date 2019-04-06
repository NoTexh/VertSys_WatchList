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

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "movie_ids")
    @TableGenerator(name = "movie_ids", initialValue = 0, allocationSize = 50)
    private long movieId;

    @ManyToOne
    @NotNull(message = "Der Film muss einem Benutzer geordnet werden.")
    private User owner;

    @ManyToOne
    private Category category;

    private Date creationDate;

    @Column(length = 50)
    @NotNull(message = "Der Filmtitel darf nicht leer sein.")
    @Size(min = 1, max = 50, message = "Der Filmtitel muss zwischen 1 und 50 Zeichen lang sein.")
    private String movieTitle;

    @Lob
    @NotNull
    private String movieDescription;

    @Enumerated(EnumType.STRING)
    @NotNull
    private MovieStatus movieStatus = MovieStatus.NICHT_GESEHEN;

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Movie() {
    }

    public Movie(User owner, Category category, String movieTitle, String movieDescription, Date creationDate) {
        this.owner = owner;
        this.category = category;
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.creationDate = creationDate;

    }

}
