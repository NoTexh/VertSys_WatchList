package dhbw.vertsys.watchlist.controller;

import dhbw.vertsys.watchlist.model.User;
import dhbw.vertsys.watchlist.ejb.UserBean;
import dhbw.vertsys.watchlist.ejb.ValidationBean;
import dhbw.vertsys.watchlist.web.FormValues;
import dhbw.vertsys.watchlist.web.WebUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/app/movies/management/"})
public class ManagementServlet extends HttpServlet {

     @EJB
     ValidationBean validationBean;
            
    @EJB
    UserBean userBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        User user = this.userBean.getCurrentUser();
        request.setAttribute("first_name", user.getFirstname());
        request.setAttribute("last_name", user.getLastname());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/management/management.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        User user = this.userBean.getCurrentUser();
        String firstname = request.getParameter("signup_firstname");
        String lastname = request.getParameter("signup_lastname");
        String password1 = request.getParameter("signup_password1");
        String password2 = request.getParameter("signup_password2");
        List<String> errors = new ArrayList<>();
        
        User currentUser = this.userBean.getCurrentUser();
        
        if (password1 != null && password2 != null && !password1.equals(password2)) {
            errors.add("Die beiden Passwörter stimmen nicht überein.");
        }
        
        if(firstname != null && !"".equals(firstname)){
            currentUser.setFirstname(firstname);
        }
        if(lastname != null && !"".equals(lastname)){
            currentUser.setLastname(lastname);
        }
        if(password1 != null && !"".equals(password1)){
            currentUser.setPassword(password1);
        }
        
        
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            //request.login(username, password1);
            this.userBean.update(currentUser);
            response.sendRedirect(WebUtils.appUrl(request, "/app/dashboard/"));
        } else {
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);
            
            HttpSession session = request.getSession();
            session.setAttribute("signup_form", formValues);
            
            response.sendRedirect(request.getRequestURI());
        }
        
        
    }
}