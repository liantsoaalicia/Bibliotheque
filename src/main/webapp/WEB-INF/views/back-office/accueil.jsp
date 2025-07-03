<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid">
    <div class="row mb-4">
        <div class="col">
            <h3 class="fw-semibold">Bienvenue, <c:out value="${bibliothecaire_connecte.nom}"/> !</h3>
            <p class="text-muted">Voici un apercu de votre bibliotheque aujourd'hui.</p>
        </div>
    </div>
    <div class="row g-4 mb-4">
        <div class="col-md-3 col-6">
            <div class="card shadow-sm border-0 text-center">
                <div class="card-body">
                    <i class="bi bi-book fs-2 text-primary mb-2"></i>
                    <h5 class="card-title mb-0">245</h5>
                    <div class="text-muted small">Livres</div>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-6">
            <div class="card shadow-sm border-0 text-center">
                <div class="card-body">
                    <i class="bi bi-people fs-2 text-success mb-2"></i>
                    <h5 class="card-title mb-0">87</h5>
                    <div class="text-muted small">Membres</div>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-6">
            <div class="card shadow-sm border-0 text-center">
                <div class="card-body">
                    <i class="bi bi-arrow-repeat fs-2 text-warning mb-2"></i>
                    <h5 class="card-title mb-0">12</h5>
                    <div class="text-muted small">Emprunts en cours</div>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-6">
            <div class="card shadow-sm border-0 text-center">
                <div class="card-body">
                    <i class="bi bi-exclamation-triangle fs-2 text-danger mb-2"></i>
                    <h5 class="card-title mb-0">3</h5>
                    <div class="text-muted small">Retards</div>
                </div>
            </div>
        </div>
    </div>
    <div class="row mb-4">
        <div class="col">
            <div class="d-flex flex-wrap gap-3">
                <a href="${pageContext.request.contextPath}/livre/add" class="btn btn-primary"><i class="bi bi-plus-circle me-1"></i> Ajouter un livre</a>
                <a href="#" class="btn btn-success"><i class="bi bi-person-plus me-1"></i> Nouveau membre</a>
                <a href="#" class="btn btn-warning"><i class="bi bi-arrow-repeat me-1"></i> Nouvel emprunt</a>
                <a href="#" class="btn btn-outline-secondary"><i class="bi bi-graph-up me-1"></i> Statistiques</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-7 mb-4 mb-lg-0">
            <div class="card border-0 shadow-sm">
                <div class="card-header bg-white fw-semibold">Derniers emprunts</div>
                <div class="card-body p-2">
                    <table class="table table-sm align-middle mb-0">
                        <thead>
                            <tr>
                                <th>Livre</th>
                                <th>Membre</th>
                                <th>Date</th>
                                <th>Statut</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Le Petit Prince</td>
                                <td>Jean Dupont</td>
                                <td>02/07/2025</td>
                                <td><span class="badge bg-success">En cours</span></td>
                            </tr>
                            <tr>
                                <td>L'Étranger</td>
                                <td>Marie Curie</td>
                                <td>01/07/2025</td>
                                <td><span class="badge bg-warning text-dark">En retard</span></td>
                            </tr>
                            <tr>
                                <td>1984</td>
                                <td>Paul Martin</td>
                                <td>30/06/2025</td>
                                <td><span class="badge bg-secondary">Rendu</span></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-lg-5">
            <div class="card border-0 shadow-sm h-100">
                <div class="card-header bg-white fw-semibold">Raccourcis utiles</div>
                <div class="card-body d-flex flex-column gap-2">
                    <a href="#" class="text-decoration-none"><i class="bi bi-book me-2 text-primary"></i> Gérer les livres</a>
                    <a href="#" class="text-decoration-none"><i class="bi bi-people me-2 text-success"></i> Gérer les membres</a>
                    <a href="#" class="text-decoration-none"><i class="bi bi-arrow-repeat me-2 text-warning"></i> Gérer les emprunts</a>
                    <a href="#" class="text-decoration-none"><i class="bi bi-gear me-2 text-secondary"></i> Paramètres</a>
                </div>
            </div>
        </div>
    </div>
</div>