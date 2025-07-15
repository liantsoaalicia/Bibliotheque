<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliothèque - Accueil</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: white;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            max-width: 600px;
            width: 100%;
            background: #ffffff;
            border-radius: 20px;
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        
        .header {
            background: linear-gradient(135deg, #8b0000 0%, #660000 100%);
            color: white;
            padding: 60px 40px;
            text-align: center;
            position: relative;
        }
        
        .header::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"><circle cx="20" cy="20" r="2" fill="white" opacity="0.1"/><circle cx="80" cy="40" r="3" fill="white" opacity="0.1"/><circle cx="40" cy="80" r="1" fill="white" opacity="0.1"/></svg>');
        }
        
        .header h1 {
            font-size: 48px;
            font-weight: 700;
            margin-bottom: 10px;
            position: relative;
            z-index: 1;
        }
        
        .header .subtitle {
            font-size: 18px;
            opacity: 0.9;
            position: relative;
            z-index: 1;
        }
        
        .header .icon {
            font-size: 80px;
            margin-bottom: 20px;
            position: relative;
            z-index: 1;
        }
        
        .content {
            padding: 50px 40px;
        }
        
        .welcome-text {
            text-align: center;
            margin-bottom: 40px;
            color: #2c3e50;
        }
        
        .welcome-text h2 {
            font-size: 28px;
            margin-bottom: 15px;
            color: #2c3e50;
        }
        
        .welcome-text p {
            font-size: 16px;
            line-height: 1.6;
            color: #7f8c8d;
        }
        
        .login-options {
            display: flex;
            gap: 20px;
            justify-content: center;
            flex-wrap: wrap;
        }
        
        .login-card {
            flex: 1;
            min-width: 200px;
            background: #f8f9fa;
            border-radius: 15px ;
            padding: 30px 20px;
            text-align: center;
            text-decoration: none;
            /* color: inherit; */
            transition: all 0.3s ease;
            border: 2px solid #660000;
        }
        
        .login-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
            border-color: #e0e0e0;
        }
        
        .login-card.bibliothecaire {
            background: white;
            color: black;
        }
        
        .login-card.bibliothecaire:hover {
            background: white;
            box-shadow: 0 10px 25px rgba(139, 0, 0, 0.3);
        }
        
        .login-card.adherent {
            background: white;
            color: black;
        }
        
        .login-card.adherent:hover {
            background: white;
            box-shadow: 0 10px 25px rgba(160, 0, 0, 0.3);
        }
        
        .login-card .icon {
            font-size: 48px;
            margin-bottom: 15px;
            display: block;
        }
        
        .login-card h3 {
            font-size: 20px;
            margin-bottom: 10px;
            font-weight: 600;
        }
        
        .login-card p {
            font-size: 14px;
            opacity: 0.9;
            line-height: 1.4;
        }
        
        .footer {
            text-align: center;
            padding: 30px 40px;
            background: #f8f9fa;
            color: #7f8c8d;
            font-size: 14px;
        }
        
        .footer a {
            color: #8b0000;
            text-decoration: none;
            margin: 0 10px;
        }
        
        .footer a:hover {
            text-decoration: underline;
        }
        
        @media (max-width: 768px) {
            .header {
                padding: 40px 20px;
            }
            
            .header h1 {
                font-size: 36px;
            }
            
            .header .icon {
                font-size: 60px;
            }
            
            .content {
                padding: 30px 20px;
            }
            
            .login-options {
                flex-direction: column;
            }
            
            .login-card {
                min-width: auto;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="icon">📚</div>
            <h1>Bibliothèque</h1>
            <p class="subtitle">Système de gestion numérique</p>
        </div>
        
        <div class="content">
            <div class="welcome-text">
                <h2>Bienvenue</h2>
                <p>Connectez-vous à votre espace personnel pour accéder aux services de la bibliothèque</p>
            </div>
            
            <div class="login-options">
                <a href="${pageContext.request.contextPath}/bibliothecaire/form" class="login-card bibliothecaire">
                    <span class="icon">👨‍💼</span>
                    <h3>Bibliothécaire</h3>
                    <p>Gestion des livres, prêts, réservations et adhérents</p>
                </a>
                
                <a href="${pageContext.request.contextPath}/adherant/form" class="login-card adherent">
                    <span class="icon">👤</span>
                    <h3>Adhérent</h3>
                    <p>Consultation du catalogue et gestion de vos emprunts</p>
                </a>
            </div>
        </div>
        
        <div class="footer">
            <p>&copy; 2025 Système de Bibliothèque | 
            <a href="#">Aide</a> | 
            <a href="#">Contact</a> | 
            <a href="#">À propos</a></p>
        </div>
    </div>
</body>
</html>