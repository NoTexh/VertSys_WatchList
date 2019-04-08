/*
 * Copyright © 2019 Markus Scheuring, Kai Schmid, Tobias Frietsch
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbw.vertsys.watchlist.soap;

import dhbw.vertsys.watchlist.ejb.MovieBean;
import dhbw.vertsys.watchlist.ejb.UserBean;
import dhbw.vertsys.watchlist.model.Movie;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
@Stateless
public class SoapService {

    @EJB
    MovieBean movieBean;
    @EJB
    UserBean userBean;

    @WebMethod
    @WebResult(name = "movies")
    public List<Movie> findMovies(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password
    ) throws UserBean.InvalidCredentialsException {
        this.userBean.validateUser(username, password);
        return movieBean.findAll();
    }

    @WebMethod
    @WebResult(name = "movies")
    public Movie findMovieById(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "id") long id
    ) throws UserBean.InvalidCredentialsException {
        this.userBean.validateUser(username, password);
        return movieBean.findById(id);
    }

    @WebMethod
    @WebResult(name = "movies")
    public List<Movie> findMoviesByUser(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "suchuser") String suchuser
    ) throws UserBean.InvalidCredentialsException {
        this.userBean.validateUser(username, password);
        return movieBean.findByUsername(suchuser);
    }

}