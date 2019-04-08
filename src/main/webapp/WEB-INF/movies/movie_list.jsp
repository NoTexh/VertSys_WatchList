<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Deine Wachlist
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/movie_list.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/dashboard/"/>">Dashboard</a>
        </div>

        <div class="menuitem">
            <a href="<c:url value="/app/movies/movie/new/"/>">Film hinzufügen</a>
        </div>

        <div class="menuitem">
            <a href="<c:url value="/app/movies/categories/"/>">Kategorien bearbeiten</a>
        </div>
        <div class="menuitem">
            <a href="<c:url value="/app/movies/management/"/>">Benutzer verwalten</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <%-- Suchfilter --%>
        <form method="GET" class="horizontal" id="search">
            <input type="text" name="search_text" value="${param.search_text}" placeholder="Beschreibung"/>

            <select name="search_category">
                <option value="">Alle Kategorien</option>

                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}" ${param.search_category == category.id ? 'selected' : ''}>
                        <c:out value="${category.name}" />
                    </option>
                </c:forEach>
            </select>

            <select name="search_status">
                <option value="">Alle Stati</option>

                <c:forEach items="${statuses}" var="status">
                    <option value="${status}" ${param.search_status == status ? 'selected' : ''}>
                        <c:out value="${status.label}"/>
                    </option>
                </c:forEach>
            </select>

            <button class="icon-search" type="submit">
                Suchen
            </button>
        </form>

        <%-- Gefundene Aufgaben --%>
        <c:choose>
            <c:when test="${empty movies}">
                <p>
                    Es wurden keine Filme gefunden.
                </p>
            </c:when>
            <c:otherwise>
                <jsp:useBean id="utils" class="dhbw.vertsys.watchlist.web.WebUtils"/>
                
                <table>
                    <thead>
                        <tr>
                            <th>Titel</th>
                            <th>Kategorie</th>
                            <th>Eigentümer</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <c:forEach items="${movies}" var="movie">
                        <tr>
                            <td>
                                <a class="sectionTitle" href="<c:url value="/app/movies/movie/${movie.movieId}/"/>">
                                    <c:out value="${movie.movieTitle}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${movie.category.name}"/>
                            </td>
                            <td>
                                <c:out value="${movie.owner.username}"/>
                            </td>
                            <td>
                                <c:out value="${movie.movieStatus.label}"/>
                            </td>
                            <td>
                                <c:out value="${utils.formatDate(movie.creationDate)}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</template:base>
