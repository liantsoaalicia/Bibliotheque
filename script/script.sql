CREATE DATABASE biblio;
\c biblio;

CREATE TABLE auteur (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE maisonedition(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE genrelitteraire (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE livre (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(200) NOT NULL,
    edition VARCHAR(100),
    tag VARCHAR(100),
    idauteur INT REFERENCES auteur(id),
    idmaison INT REFERENCES maison(id),
    idgenre INT REFERENCES genrelitteraire(id)
);

CREATE TABLE exemplaire (
    id SERIAL PRIMARY KEY,
    numexemplaire VARCHAR(50) NOT NULL,
    idlivre INT REFERENCES livre(id)
);

-- Etudiant, Prof, Pro, Anonyme
CREATE TABLE profil (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

CREATE TABLE inscription (
    id SERIAL PRIMARY KEY,
    duree INT NOT NULL,
    montant DECIMAL(10, 2) NOT NULL,
    idprofil INT REFERENCES profil(id)
);

CREATE TABLE adherant (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    datenaissance DATE,
    idprofil INT REFERENCES profil(id),
    mdp VARCHAR(20)
);

CREATE TABLE adherantinscription (
    id SERIAL PRIMARY KEY,
    idadherant INT REFERENCES adherant(id),
    idinscription INT REFERENCES inscription(id),
    montantpaye DECIMAL(10, 2) NOT NULL,
    dateinscription DATE NOT NULL
);

CREATE TABLE typepret (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

-- A domicile / sur place
CREATE TABLE pret (
    id SERIAL PRIMARY KEY,
    idadherant INT REFERENCES adherant(id),
    idexemplaire INT REFERENCES exemplaire(id),
    idtypepret INT REFERENCES typepret(id),
    dateemprunt DATE NOT NULL,
    dateretour DATE
);

-- En cours / accepté / refusé 
CREATE TABLE etat (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

CREATE TABLE prolongementpret (
    id SERIAL PRIMARY KEY,
    idpret INT REFERENCES pret(id),
    prolongement INT NOT NULL,
    idetat INT REFERENCES etat(id)
);

CREATE TABLE penaliteprofil (
    id SERIAL PRIMARY KEY,
    idprofil INT REFERENCES profil(id),
    restriction INT NOT NULL -- nb de jour tsy azo angalana boky
);

CREATE TABLE adherantpenalite (
    id SERIAL PRIMARY KEY,
    idadherant INT REFERENCES adherant(id),
    idpenalite INT REFERENCES penaliteprofil(id),
    datedebut DATE NOT NULL,
    datefin DATE 
);

CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    idadherant INT REFERENCES adherant(id),
    idexemplaire INT REFERENCES exemplaire(id),
    datedebut DATE NOT NULL,
    datefin DATE NOT NULL,
    idetat INT REFERENCES etat(id)
);

CREATE TABLE jourferie (
    id SERIAL PRIMARY KEY,
    dateferie DATE NOT NULL,
    libelle VARCHAR(100) NOT NULL
);

-- nb exemplaire que le profil peut emprunter
CREATE TABLE quota (
    id SERIAL PRIMARY KEY,
    idprofil INT REFERENCES profil(id),
    nbexemplaire INT NOT NULL
);

-- nb de jour de pret par profil
CREATE TABLE pretprofil (
    id SERIAL PRIMARY KEY,
    idprofil INT REFERENCES profil(id),
    nbjourpret INT NOT NULL
);