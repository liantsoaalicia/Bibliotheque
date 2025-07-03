CREATE TABLE auteur(
   id INT,
   nom VARCHAR(50) NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom)
);

CREATE TABLE maisonedition(
   id INT,
   nom VARCHAR(50) NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom)
);

CREATE TABLE genrelitteraire(
   id INT,
   libelle VARCHAR(50) NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(libelle)
);

CREATE TABLE livre(
   id INT,
   titre VARCHAR(200) NOT NULL,
   edition VARCHAR(100),
   tag VARCHAR(100),
   ageRequis INT NOT NULL,
   id_maison INT NOT NULL,
   id_genre INT NOT NULL,
   id_auteur INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_maison) REFERENCES maisonedition(id),
   FOREIGN KEY(id_genre) REFERENCES genrelitteraire(id),
   FOREIGN KEY(id_auteur) REFERENCES auteur(id)
);

CREATE TABLE exemplaire(
   id INT,
   numExemplaire VARCHAR(50) NOT NULL,
   id_livre INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_livre) REFERENCES livre(id)
);

CREATE TABLE profil(
   id INT,
   libelle VARCHAR(50) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE adherant(
   id INT,
   nom VARCHAR(100) NOT NULL,
   email VARCHAR(100),
   dateNaissance DATE NOT NULL,
   mdp VARCHAR(20) NOT NULL,
   id_profil INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_profil) REFERENCES profil(id)
);

CREATE TABLE adherantabonnement(
   id INT,
   dateDebut DATE NOT NULL,
   dateFin DATE NOT NULL,
   id_adherant INT NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(id_adherant),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id)
);

CREATE TABLE typePret(
   id INT,
   libelle VARCHAR(50) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE pret(
   id INT,
   dateEmprunt DATE NOT NULL,
   dateRetour DATE,
   id_exemplaire INT NOT NULL,
   id_adherant INT NOT NULL,
   id_type INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_exemplaire) REFERENCES exemplaire(id),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id),
   FOREIGN KEY(id_type) REFERENCES typePret(id)
);

CREATE TABLE penaliteconfig(
   id INT,
   nbJourPenalite SMALLINT NOT NULL,
   dateChangement DATE NOT NULL,
   id_profil INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_profil) REFERENCES profil(id)
);

CREATE TABLE adherantpenalite(
   id INT,
   dateDebut DATE NOT NULL,
   dateFin DATE NOT NULL,
   id_adherant INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id)
);

CREATE TABLE jourferie(
   id INT,
   dateFerie DATE NOT NULL,
   libelle VARCHAR(100) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE quotaexemplaire(
   id INT,
   nbExemplaire SMALLINT NOT NULL,
   dateChangement DATE NOT NULL,
   id_profil INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_profil) REFERENCES profil(id)
);

CREATE TABLE pretconfig(
   id INT,
   nbJourPret SMALLINT,
   dateChangement DATE NOT NULL,
   id_profil INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_profil) REFERENCES profil(id)
);

CREATE TABLE quotareservation(
   id INT,
   nbReservation SMALLINT NOT NULL,
   dateChangement DATE,
   id_profil INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_profil) REFERENCES profil(id)
);

CREATE TABLE bibliothecaire(
   id INT,
   nom VARCHAR(100) NOT NULL,
   email VARCHAR(50),
   mdp VARCHAR(20) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE statut(
   id INT,
   statut VARCHAR(50) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE prolongementpret(
   id INT,
   dateDebut DATE NOT NULL,
   dateFin DATE,
   dateStatut DATE NOT NULL,
   id_statut INT NOT NULL,
   id_pret INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_statut) REFERENCES statut(id),
   FOREIGN KEY(id_pret) REFERENCES pret(id)
);

CREATE TABLE reservation(
   id INT,
   dateDebut DATE NOT NULL,
   dateFin DATE,
   dateStatut DATE NOT NULL,
   id_statut INT NOT NULL,
   id_exemplaire INT NOT NULL,
   id_adherant INT NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(id_exemplaire),
   FOREIGN KEY(id_statut) REFERENCES statut(id),
   FOREIGN KEY(id_exemplaire) REFERENCES exemplaire(id),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id)
);
