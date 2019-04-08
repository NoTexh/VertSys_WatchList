<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Benutzerverwaltung
    </jsp:attribute>

    <jsp:attribute name="head">

    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/dashboard/"/>">Dashboard</a>
        </div>
        
        <div class="menuitem">
            <a href="<c:url value="/app/movies/list/"/>">Liste</a>
        </div>

        <div class="menuitem">
            <a href="<c:url value="/app/movies/movie/new/"/>">Film hinzufügen</a>
        </div>

        <div class="menuitem">
            <a href="<c:url value="/app/movies/categories/"/>">Kategorien bearbeiten</a>
        </div>
    </jsp:attribute>
        
    <jsp:attribute name="content">
        <div class="row">
            <div class="col"></div>
            <div class="signup-form col-6">
                <form method="post" class="stacked">
                    <%-- CSRF-Token --%>
                    <input type="hidden" name="csrf_token" value="${csrf_token}">
                    <div class="form-group">
                        <input type="text" class="form-control" name="signup_firstname" value="${first_name}">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="signup_lastname" value="${last_name}">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="signup_password1" placeholder="Passwort">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="signup_password2" placeholder="Passwort wiederholen">
                    </div>        
                    <div class="form-group">
                        <button type="submit" class="btn btn-secondary btn-lg btn-block">Jetzt Ändern</button>
                    </div>

                    <%-- Fehlermeldung --%>
                    <c:if test="${!empty signup_form.errors}">
                        <ul class="errors">
                            <c:forEach items="${signup_form.errors}" var="error">
                                <li>${error}</li>
                                </c:forEach>
                        </ul>
                    </c:if>
                </form>
            </div>
            <div class="col"></div>
        </div>
    </jsp:attribute>
</template:base>