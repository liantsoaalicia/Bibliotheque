DROP TABLE IF EXISTS reservation CASCADE;
DROP TABLE IF EXISTS prolongement_pret CASCADE;
DROP TABLE IF EXISTS statut CASCADE;
DROP TABLE IF EXISTS bibliothecaire CASCADE;
DROP TABLE IF EXISTS quota_reservation CASCADE;
DROP TABLE IF EXISTS pret_config CASCADE;
DROP TABLE IF EXISTS quota_exemplaire CASCADE;
DROP TABLE IF EXISTS jour_ferie CASCADE;
DROP TABLE IF EXISTS adherant_penalite CASCADE;
DROP TABLE IF EXISTS penalite_config CASCADE;
DROP TABLE IF EXISTS pret CASCADE;
DROP TABLE IF EXISTS type_pret CASCADE;
DROP TABLE IF EXISTS adherant_abonnement CASCADE;
DROP TABLE IF EXISTS adherant CASCADE;
DROP TABLE IF EXISTS profil CASCADE;
DROP TABLE IF EXISTS exemplaire CASCADE;
DROP TABLE IF EXISTS livre CASCADE;
DROP TABLE IF EXISTS genre_litteraire CASCADE;
DROP TABLE IF EXISTS maison_edition CASCADE;
DROP TABLE IF EXISTS auteur CASCADE;

DELETE FROM reservation CASCADE;
DELETE FROM prolongementpret CASCADE;
DELETE FROM statut CASCADE;
DELETE FROM bibliothecaire CASCADE;
DELETE FROM quotareservation CASCADE;
DELETE FROM pretconfig CASCADE;
DELETE FROM quotaexemplaire CASCADE;
DELETE FROM jourferie CASCADE;
DELETE FROM adherantpenalite CASCADE;
DELETE FROM penaliteconfig CASCADE;
DELETE FROM pret CASCADE;
DELETE FROM typePret CASCADE;
DELETE FROM adherantabonnement CASCADE;
DELETE FROM adherant CASCADE;
DELETE FROM profil CASCADE;
DELETE FROM exemplaire CASCADE;
DELETE FROM livre CASCADE;
DELETE FROM genrelitteraire CASCADE;
DELETE FROM maisonedition CASCADE;
DELETE FROM auteur CASCADE;