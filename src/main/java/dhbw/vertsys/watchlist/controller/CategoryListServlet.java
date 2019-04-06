/*
 * Copyright © 2019 Markus Scheuring, Kai Schmid, Tobias Frietsch
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbw.vertsys.watchlist.controller;

import dhbw.vertsys.watchlist.ejb.CategoryBean;
import dhbw.vertsys.watchlist.ejb.MovieBean;
import dhbw.vertsys.watchlist.ejb.ValidationBean;
import dhbw.vertsys.watchlist.model.Category;
import dhbw.vertsys.watchlist.model.Movie;
import dhbw.vertsys.watchlist.web.FormValues;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/app/movies/categories/"})
public class CategoryListServlet extends HttpServlet {

    @EJB
    CategoryBean categoryBean;

    @EJB
    MovieBean movieBean;

    @EJB
    ValidationBean validationBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Alle vorhandenen Kategorien ermitteln
        request.setAttribute("categories", this.categoryBean.findAllSorted());

        // Anfrage an dazugerhörige JSP weiterleiten
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/movies/category_list.jsp");
        dispatcher.forward(request, response);

        // Alte Formulardaten aus der Session entfernen
        HttpSession session = request.getSession();
        session.removeAttribute("categories_form");
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
            case "create":
                this.createCategory(request, response);
                break;
            case "delete":
                this.deleteCategories(request, response);
                break;
        }
    }

    /**
     * Aufgerufen in doPost(): Neue Kategorie anlegen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void createCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Formulareingaben prüfen
        String name = request.getParameter("name");

        Category category = new Category(name);
        List<String> errors = this.validationBean.validate(category);

        // Neue Kategorie anlegen
        if (errors.isEmpty()) {
            this.categoryBean.saveNew(category);
        }

        // Browser auffordern, die Seite neuzuladen
        if (!errors.isEmpty()) {
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("categories_form", formValues);
        }

        response.sendRedirect(request.getRequestURI());
    }

    /**
     * Aufgerufen in doPost(): Markierte Kategorien löschen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteCategories(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Markierte Kategorie IDs auslesen
        String[] categoryIds = request.getParameterValues("category");

        if (categoryIds == null) {
            categoryIds = new String[0];
        }

        // Kategorien löschen
        for (String categoryId : categoryIds) {
            // Zu löschende Kategorie ermitteln
            Category category;

            try {
                category = this.categoryBean.findById(Long.parseLong(categoryId));
            } catch (NumberFormatException ex) {
                continue;
            }

            if (category == null) {
                continue;
            }

            // Bei allen betroffenen Aufgaben, den Bezug zur Kategorie aufheben
            List<Movie> movies = category.getMovies();

            if (movies != null) {
                movies.forEach((Movie movie) -> {
                    movie.setCategory(null);
                    this.movieBean.update(movie);
                });
            }

            // Und weg damit
            this.categoryBean.delete(category);
        }

        // Browser auffordern, die Seite neuzuladen
        response.sendRedirect(request.getRequestURI());
    }

}
