<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Library App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/template-biblio.css" />
</head>
<body>
    <div class="sidebar" id="sidebar">
        <button class="sidebar-toggle" onclick="toggleSidebar()">
            <i class="bi bi-list"></i>
        </button>
        
        <div class="sidebar-header">
            <h3><i class="bi bi-book"></i> Library</h3>
        </div>
        
        <nav>
            <ul class="sidebar-nav">
                <%-- <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/bibliothecaire/accueil" class="nav-link active">
                        <i class="bi bi-house-door"></i>
                        <span>Accueil</span>
                    </a>
                </li> --%>
                
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="bi bi-search"></i>
                        <span>Recherche</span>
                    </a>
                </li>
                
                <li class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                        <i class="bi bi-book"></i>
                        <span>Reservation</span>
                    </a>
                    <ul class="dropdown-menu">
                        <%-- <li><a href="${pageContext.request.contextPath}/livre/list" class="dropdown-item">Tous les livres</a></li> --%>
                        <%-- <li><a href="${pageContext.request.contextPath}/livre/add" class="dropdown-item">Ajouter un livre</a></li> --%>
                    </ul>
                </li>
                
                <%-- <li class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                        <i class="bi bi-people"></i>
                        <span>Reservation</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/adherantpenalite/list" class="dropdown-item">Liste</a></li>
                        <li><a href="#" class="dropdown-item">Nouveau membre</a></li>
                        <li><a href="#" class="dropdown-item">Abonnements</a></li>
                    </ul>
                </li> --%>
            </ul>
        </nav>
    </div>

    <div class="main-content" id="mainContent">
        <header class="content-header py-2 px-3 d-flex align-items-center justify-content-between" style="background: #f8f9fa; border-radius: 0.5rem; margin-bottom: 1rem;">
            <div>
                <h4 class="mb-0">Bienvenue ${adherant_connecte.nom} </h4>
                <small class="text-muted">Profitez de notre bibliotheque</small>
            </div>
            <div class="header-profile d-flex align-items-center gap-2">
                <span class="fw-semibold text-dark me-2" style="font-size:1.05rem;">
                    <c:out value="${adherant_connecte.nom}"/>
                </span>
                <img src="https://ui-avatars.com/api/?name=${adherant_connecte.nom}&background=3b82f6&color=fff&rounded=true&size=40" alt="Profil" class="rounded-circle shadow" style="width:40px;height:40px;object-fit:cover;">
            </div>
        </header>
        
        <main class="content-body">
            <jsp:include page="${contentPage}.jsp" />
        </main>
        
        <footer class="footer">
            <p>&copy; 2025 My Library App - Tous droits réservés</p>
        </footer>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/template-biblio.js"></script>
</body>
</html>