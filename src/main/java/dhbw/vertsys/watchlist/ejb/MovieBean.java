/*
 * Copyright © 2019 Markus Scheuring, Kai Schmid, Tobias Frietsch
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbw.vertsys.watchlist.ejb;

import dhbw.vertsys.watchlist.model.Category;
import dhbw.vertsys.watchlist.model.Movie;
import dhbw.vertsys.watchlist.model.MovieStatus;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
@RolesAllowed("app-user")
public class MovieBean extends EntityBean<Movie, Long> {

    public MovieBean() {
        super(Movie.class);
    }

    /**
     * Alle Aufgaben eines Benutzers, nach Fälligkeit sortiert zurückliefern.
     *
     * @param username Benutzername
     * @return Alle Aufgaben des Benutzers
     */
    public List<Movie> findByUsername(String username) {
        return em.createQuery("SELECT m FROM Movie m WHERE m.owner.username = :username ORDER BY m.creationDate")
                .setParameter("username", username)
                .getResultList();
    }

    /**
     * Suche nach Aufgaben anhand ihrer Bezeichnung, Kategorie und Status.
     *
     * Anders als in der Vorlesung behandelt, wird die SELECT-Anfrage hier mit
     * der CriteriaBuilder-API vollkommen dynamisch erzeugt.
     *
     * @param search In der Kurzbeschreibung enthaltener Text (optional)
     * @param category Kategorie (optional)
     * @param status Status (optional)
     * @return Liste mit den gefundenen Aufgaben
     */
    public List<Movie> search(String search, Category category, MovieStatus status) {
        // Hilfsobjekt zum Bauen des Query
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        // SELECT m FROM Movie m
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> from = query.from(Movie.class);
        query.select(from);

        // ORDER BY creationDate
        query.orderBy(cb.asc(from.get("creationDate")));

        // WHERE movieTitle LIKE :search
        Predicate p = cb.conjunction();

        if (search != null && !search.trim().isEmpty()) {
            p = cb.and(p, cb.like(from.get("movieTitle"), "%" + search + "%"));
            query.where(p);
        }

        // WHERE category = :category
        if (category != null) {
            p = cb.and(p, cb.equal(from.get("category"), category));
            query.where(p);
        }

        // WHERE status = :status
        if (status != null) {
            p = cb.and(p, cb.equal(from.get("movieStatus"), status));
            query.where(p);
        }

        return em.createQuery(query).getResultList();
    }

}
