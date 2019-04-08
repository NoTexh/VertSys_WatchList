<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Login
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/signup/"/>">Registrieren</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="row">
            <div class="col"></div>
            <div class="login-form col-6">
                <form action="j_security_check" method="post" class="stacked">
                    <div class="column">
                        <%-- Eingabefelder --%>
                        <div class="form-group">
                            <input type="username" name="j_username" class="form-control" placeholder="Benutzername" required>
                        </div>
                        <div class="form-group">
                            <input type="password" name="j_password" class="form-control" placeholder="Passwort" required>
                        </div>

                        <%-- Button zum Abschicken --%>
                        <div class="form-group">
                            <button type="submit" class="btn btn-secondary btn-lg btn-block">Einloggen</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col"></div>
        </div>
    </jsp:attribute>
</template:base>