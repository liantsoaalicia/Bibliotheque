<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">

<style>
    .login-container {
        max-width: 400px;
        margin: 80px auto;
        padding: 40px;
        background: #ffffff;
        border-radius: 10px;
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        border: 1px solid #e0e0e0;
    }
    
    .login-header {
        text-align: center;
        margin-bottom: 30px;
    }
    
    .login-header h2 {
        color: #2c3e50;
        font-size: 28px;
        font-weight: 600;
        margin-bottom: 10px;
    }
    
    .login-header .subtitle {
        color: #7f8c8d;
        font-size: 14px;
    }
    
    .form-group {
        margin-bottom: 20px;
    }
    
    .form-group label {
        display: block;
        margin-bottom: 8px;
        color: #34495e;
        font-weight: 500;
        font-size: 14px;
    }
    
    .form-group input {
        width: 100%;
        padding: 12px 16px;
        border: 2px solid #ecf0f1;
        border-radius: 8px;
        font-size: 16px;
        transition: border-color 0.3s ease;
        box-sizing: border-box;
    }
    
    .form-group input:focus {
        outline: none;
        border-color: #8b0000;
        box-shadow: 0 0 0 3px rgba(139, 0, 0, 0.1);
    }
    
    .login-btn {
        width: 100%;
        padding: 14px;
        background: linear-gradient(135deg, #8b0000, #660000);
        color: white;
        border: none;
        border-radius: 8px;
        font-size: 16px;
        font-weight: 600;
        cursor: pointer;
        transition: all 0.3s ease;
        margin-top: 10px;
    }
    
    .login-btn:hover {
        background: linear-gradient(135deg, #660000, #4d0000);
        transform: translateY(-2px);
        box-shadow: 0 8px 20px rgba(139, 0, 0, 0.3);
    }
    
    .login-btn:active {
        transform: translateY(0);
    }
    
    .icon {
        font-size: 48px;
        color: #8b0000;
        margin-bottom: 20px;
    }
    
    .alert {
        padding: 12px;
        border-radius: 6px;
        margin-bottom: 20px;
        font-size: 14px;
    }
    
    .alert-error {
        background-color: #fdeaea;
        color: #8b0000;
        border: 1px solid #f5c6cb;
    }
    
    body {
        background: white;
        min-height: 100vh;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        margin: 0;
        padding: 20px;
    }
</style>

<div class="login-container">
    <div class="login-header">
        <div class="icon"><i class="bi bi-book"></i></div>
        <h2>Connexion Bibliothecaire</h2>
        <p class="subtitle">Accedez a votre espace de gestion</p>
    </div>
    
    <c:if test="${not empty error}">
        <div class="alert alert-error">
            ${error}
        </div>
    </c:if>
    
    <form:form method="post" action="${pageContext.request.contextPath}/bibliothecaire/login" modelAttribute="bibliothecaire">
        <div class="form-group">
            <label for="email">Adresse email</label>
            <form:input path="email" id="email" placeholder="exemple@bibliotheque.com" />
        </div>

        <div class="form-group">
            <label for="mdp">Mot de passe</label>
            <form:password path="mdp" id="mdp" placeholder="Entrez votre mot de passe" />
        </div>

        <button type="submit" class="login-btn">Se connecter</button>
    </form:form>
</div>

