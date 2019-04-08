<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="base_url" value="<%=request.getContextPath()%>" />

<template:base>
    <jsp:attribute name="title">
        Registrierung
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/logout/"/>">Einloggen</a>
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
                        <input type="text" class="form-control" name="signup_firstname" placeholder="Vorname" required="required">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="signup_lastname" placeholder="Nachname" required="required">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="signup_username" placeholder="Benutzername" required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="signup_password1" placeholder="Passwort" required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="signup_password2" placeholder="Passwort wiederholen" required="required">
                    </div>        
                    <div class="form-group">
                        <button type="submit" class="btn btn-secondary btn-lg btn-block">Jetzt Registrieren</button>
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