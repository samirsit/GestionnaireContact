package gestion_contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GestionnaireContact {


    public void ajouterContact(Contact contact) {

        String sql = "INSERT INTO contacts (nom, email, telephone) VALUES (?, ?, ?)";
        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, contact.getNom());
            statement.setString(2, contact.getEmail());
            statement.setString(3, contact.getTelephone());
            statement.executeUpdate();
            System.out.println("Contact ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    public static void listerContacts() {

        ArrayList<Contact> listContact = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        try (Connection connection = DataBase.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Contact contact = new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone")
                );
                listContact.add(contact);

            }System.out.println(listContact);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des contacts : " + e.getMessage());
        }

    }

    public static void mettreAJourContact(String nomRecherche, Contact nouveauContact) {
        String sqlRecherche = "SELECT * FROM contacts WHERE nom = ?";
        String sqlMiseAJour = "UPDATE contacts SET nom = ?, email = ?, telephone = ? WHERE id = ?";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statementRecherche = connection.prepareStatement(sqlRecherche)) {

            // Étape 1 : Rechercher le contact par nom
            statementRecherche.setString(1, nomRecherche);
            try (ResultSet resultSet = statementRecherche.executeQuery()) {
                if (resultSet.next()) {
                    // Récupérer les informations actuelles du contact
                    int id = resultSet.getInt("id");

                    // Étape 2 : Modifier le contact
                    try (PreparedStatement statementMiseAJour = connection.prepareStatement(sqlMiseAJour)) {
                        statementMiseAJour.setString(1, nouveauContact.getNom());
                        statementMiseAJour.setString(2, nouveauContact.getEmail());
                        statementMiseAJour.setString(3, nouveauContact.getTelephone());
                        statementMiseAJour.setInt(4, id);
                        statementMiseAJour.executeUpdate();
                        System.out.println("Contact mis à jour avec succès !");
                    }
                } else {
                    System.out.println("Aucun contact trouvé avec le nom : " + nomRecherche);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }


    public static void supprimerContact(String nom) {
        // Requête pour récupérer l'ID du contact
        String querySelect = "SELECT id FROM contacts WHERE nom = ?";
        // Requête pour supprimer le contact
        String queryDelete = "DELETE FROM contacts WHERE id = ?";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(querySelect)) {

            // Assigner la valeur du paramètre nom
            selectStatement.setString(1, nom);

            // Exécuter la requête SELECT
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Récupérer l'ID du contact
                int id = resultSet.getInt("id");

                // Supprimer le contact avec l'ID récupéré
                try (PreparedStatement deleteStatement = connection.prepareStatement(queryDelete)) {
                    deleteStatement.setInt(1, id);
                    deleteStatement.executeUpdate();
                    System.out.println("Contact supprimé avec succès !");
                }

            } else {
                // Si aucun contact n'est trouvé avec le nom donné
                System.out.println("Aucun contact trouvé avec le nom : " + nom);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'opération : " + e.getMessage());
        }
    }

    public static void afficherContact(String nomRecherche) throws SQLException {

        String sqlRecherche = "SELECT * FROM contacts WHERE nom = ?";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statementRecherche = connection.prepareStatement(sqlRecherche)) {

            // Étape 1 : Rechercher le contact par nom
            statementRecherche.setString(1, nomRecherche);
            try (ResultSet resultSet = statementRecherche.executeQuery()) {
                // Vérification si un résultat est retourné
                if (resultSet.next()) {
                    // Récupération des colonnes de la table "contacts"
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String email = resultSet.getString("email");
                    String telephone = resultSet.getString("telephone");

                    // Affichage des informations sur la console
                    System.out.println("ID: " + id);
                    System.out.println("Nom: " + nom);
                    System.out.println("Email: " + email);
                    System.out.println("Téléphone: " + telephone);
                } else {
                    System.out.println("Aucun contact trouvé avec le nom: " + nomRecherche);
                }

            } catch (SQLException e) {
                // Gestion des erreurs SQL
                System.err.println("Erreur lors de la récupération du contact: " + e.getMessage());
            }
        }


    }
}