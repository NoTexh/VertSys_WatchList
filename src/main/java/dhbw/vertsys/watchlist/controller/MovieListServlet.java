package dhbw.vertsys.watchlist.controller;

import dhbw.vertsys.watchlist.ejb.CategoryBean;
import dhbw.vertsys.watchlist.ejb.MovieBean;
import dhbw.vertsys.watchlist.model.Category;
import dhbw.vertsys.watchlist.model.Movie;
import dhbw.vertsys.watchlist.model.MovieStatus;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/app/movies/list/"})
public class MovieListServlet extends HttpServlet {

    @EJB
    private CategoryBean categoryBean;
    
    @EJB
    private MovieBean movieBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        request.setAttribute("categories", this.categoryBean.findAllSorted());
        request.setAttribute("statuses", MovieStatus.values());

        // Suchparameter aus der URL auslesen
        String searchText = request.getParameter("search_text");
        String searchCategory = request.getParameter("search_category");
        String searchStatus = request.getParameter("search_status");

        // Anzuzeigende Aufgaben suchen
        Category category = null;
        MovieStatus status = null;

        if (searchCategory != null) {
            try {
                category = this.categoryBean.findById(Long.parseLong(searchCategory));
            } catch (NumberFormatException ex) {
                category = null;
            }
        }

        if (searchStatus != null) {
            try {
                status = MovieStatus.valueOf(searchStatus);
            } catch (IllegalArgumentException ex) {
                status = null;
            }

        }

        List<Movie> movies = this.movieBean.search(searchText, category, status);
        request.setAttribute("movies", movies);

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/movies/movie_list.jsp").forward(request, response);
    }
}


