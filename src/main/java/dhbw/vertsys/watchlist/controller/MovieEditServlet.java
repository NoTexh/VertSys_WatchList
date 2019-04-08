package dhbw.vertsys.watchlist.controller;

import dhbw.vertsys.watchlist.ejb.CategoryBean;
import dhbw.vertsys.watchlist.ejb.MovieBean;
import dhbw.vertsys.watchlist.ejb.UserBean;
import dhbw.vertsys.watchlist.ejb.ValidationBean;
import dhbw.vertsys.watchlist.model.Movie;
import dhbw.vertsys.watchlist.model.MovieStatus;
import dhbw.vertsys.watchlist.web.FormValues;
import dhbw.vertsys.watchlist.web.WebUtils;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/app/movies/movie/*")
public class MovieEditServlet extends HttpServlet {

    @EJB
    MovieBean movieBean;

    @EJB
    CategoryBean categoryBean;

    @EJB
    UserBean userBean;

    @EJB
    ValidationBean validationBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        request.setAttribute("categories", this.categoryBean.findAllSorted());
        request.setAttribute("statuses", MovieStatus.values());

        // Zu bearbeitende Aufgabe einlesen
        HttpSession session = request.getSession();

        Movie movie = this.getRequestedMovie(request);
        request.setAttribute("edit", movie.getMovieId() != 0);

        if (session.getAttribute("movie_form") == null) {
            // Keine Formulardaten mit fehlerhaften Daten in der Session,
            // daher Formulardaten aus dem Datenbankobjekt übernehmen
            request.setAttribute("movie_form", this.createMovieForm(movie));
        }

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/movies/movie_edit.jsp").forward(request, response);

        session.removeAttribute("movie_form");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Angeforderte Aktion ausführen
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "save":
                this.saveMovie(request, response);
                break;
            case "delete":
                this.deleteMovie(request, response);
                break;
        }
    }

    /**
     * Aufgerufen in doPost(): Neue oder vorhandene Aufgabe speichern
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void saveMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Formulareingaben prüfen
        List<String> errors = new ArrayList<>();

        String movieCategory = request.getParameter("movie_category");
        String movieStatus = request.getParameter("movie_status");
        String movieTitle = request.getParameter("movie_title");
        String movieDescription = request.getParameter("movie_description");

        Movie movie = this.getRequestedMovie(request);

        if (movieCategory != null && !movieCategory.trim().isEmpty()) {
            try {
                movie.setCategory(this.categoryBean.findById(Long.parseLong(movieCategory)));
            } catch (NumberFormatException ex) {
                // Ungültige oder keine ID mitgegeben
            }
        }

        try {
            movie.setMovieStatus(MovieStatus.valueOf(movieStatus));
        } catch (IllegalArgumentException ex) {
            errors.add("Der ausgewählte Status ist nicht vorhanden.");
        }

        movie.setMovieTitle(movieTitle);
        movie.setMovieDescription(movieDescription);

        this.validationBean.validate(movie, errors);

        // Datensatz speichern
        if (errors.isEmpty()) {
            this.movieBean.update(movie);
        }

        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            response.sendRedirect(WebUtils.appUrl(request, "/app/movies/list/"));
        } else {
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("movie_form", formValues);

            response.sendRedirect(request.getRequestURI());
        }
    }

    /**
     * Aufgerufen in doPost: Vorhandene Aufgabe löschen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Datensatz löschen
        Movie movie = this.getRequestedMovie(request);
        this.movieBean.delete(movie);

        // Zurück zur Übersicht
        response.sendRedirect(WebUtils.appUrl(request, "/app/movies/list/"));
    }

    /**
     * Zu bearbeitende Aufgabe aus der URL ermitteln und zurückgeben. Gibt
     * entweder einen vorhandenen Datensatz oder ein neues, leeres Objekt
     * zurück.
     *
     * @param request HTTP-Anfrage
     * @return Zu bearbeitende Aufgabe
     */
    private Movie getRequestedMovie(HttpServletRequest request) {
        // Zunächst davon ausgehen, dass ein neuer Satz angelegt werden soll
        Movie movie = new Movie();
        movie.setOwner(this.userBean.getCurrentUser());
        movie.setCreationDate(new Date(System.currentTimeMillis()));

        // ID aus der URL herausschneiden
        String movieId = request.getPathInfo();

        if (movieId == null) {
            movieId = "";
        }

        movieId = movieId.substring(1);

        if (movieId.endsWith("/")) {
            movieId = movieId.substring(0, movieId.length() - 1);
        }

        // Versuchen, den Datensatz mit der übergebenen ID zu finden
        try {
            movie = this.movieBean.findById(Long.parseLong(movieId));
        } catch (NumberFormatException ex) {
            // Ungültige oder keine ID in der URL enthalten
        }

        return movie;
    }

    /**
     * Neues FormValues-Objekt erzeugen und mit den Daten eines aus der
     * Datenbank eingelesenen Datensatzes füllen. Dadurch müssen in der JSP
     * keine hässlichen Fallunterscheidungen gemacht werden, ob die Werte im
     * Formular aus der Entity oder aus einer vorherigen Formulareingabe
     * stammen.
     *
     * @param task Die zu bearbeitende Aufgabe
     * @return Neues, gefülltes FormValues-Objekt
     */
    private FormValues createMovieForm(Movie movie) {
        Map<String, String[]> values = new HashMap<>();

        values.put("movie_owner", new String[]{
            movie.getOwner().getUsername()
        });

        if (movie.getCategory() != null) {
            values.put("movie_category", new String[]{
                "" + movie.getCategory().getId()
            });
        }

        values.put("movie_creation_Date", new String[]{
            WebUtils.formatDate(movie.getCreationDate())
        });


        values.put("movie_status", new String[]{
            movie.getMovieStatus().toString()
        });

        values.put("movie_title", new String[]{
            movie.getMovieTitle()
        });

        values.put("movie_description", new String[]{
            movie.getMovieDescription()
        });

        FormValues formValues = new FormValues();
        formValues.setValues(values);
        return formValues;
    }

}