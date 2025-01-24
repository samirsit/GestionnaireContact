package gestion_contact;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principale {

    static int choix;

    public static void main(String[] args) throws SQLException {

        GestionnaireContact contact = new GestionnaireContact();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("       ");
            System.out.println("================ MENU ===================");
            System.out.println(" 1 - Ajouter un contact");
            System.out.println(" 2 - Mettre à jour un contact");
            System.out.println(" 3 - Supprimer un contact");
            System.out.println(" 4 - Afficher un  contact");
            System.out.println(" 5 - Lister tous les contacts");
            System.out.println(" 0 - Quitter");

            choix = scanner.nextInt();
            scanner.nextLine(); // Consomme le saut de ligne laissé par nextInt()

            // Ajouter un contact

            switch (choix) {
                case 0:
                    System.out.println("Merci d'avoir utilisé l'application !");
                    break;

                case 1: // Ajouter un contact
                    System.out.println("Merci d'entrer le Nom");
                    String Nom = scanner.nextLine();

                    System.out.println("Merci d'entrer l'Email");
                    String Email = scanner.nextLine();

                    System.out.println("Merci d'entrer le Telephone");
                    String Telephone = scanner.nextLine();

                    Contact contactAjouter = new Contact(Nom, Email, Telephone);
                    contact.ajouterContact(contactAjouter);
                    break;

                case 2:     // Metrre à jour un contact

                    System.out.println("Entrez le nom du contact à mettre à jour : ");
                    String nomModifier = scanner.nextLine();
                    System.out.println("Entrez les nouveau nom");
                    String nom = scanner.nextLine();
                    System.out.println("Entrez les nouveau email");
                    String email = scanner.nextLine();
                    System.out.println("Entrez les nouveau telephone");
                    String telephone = scanner.nextLine();
                    Contact contact3 = new Contact(nom, email, telephone);
                    GestionnaireContact.mettreAJourContact(nomModifier, contact3);

                    break;

                case 3:     // Supprimer un contact
                    System.out.println("Entrez le nom du contact à supprimer : ");
                    String contactSupprimer = scanner.nextLine();

                    GestionnaireContact.supprimerContact(contactSupprimer);
                    break;

                case 4:  // Afficher un contact

                    System.out.println("Entrez le nom du contact à afficher : ");
                    String nomRechercher = scanner.nextLine();
                    GestionnaireContact.afficherContact(nomRechercher);
                    break;

                case 5:    // Afficher la liste de tous les contacts
                    GestionnaireContact.listerContacts();
                    break;

            }

            } while(choix > 0 && choix <= 5);
                scanner.close();
    }
}
