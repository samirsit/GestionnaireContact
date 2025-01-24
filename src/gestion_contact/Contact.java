package gestion_contact;

public class Contact {

        private int id;
        private String nom;
        private String email;
        private String telephone;

        // Constructeurs
        public Contact() {}

        public Contact(String nom, String email, String telephone) {
            this.nom = nom;
            this.email = email;
            this.telephone = telephone;
        }

        public Contact(int id, String nom, String email, String telephone) {
            this.id = id;
            this.nom = nom;
            this.email = email;
            this.telephone = telephone;
        }

        // Getters et Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}


