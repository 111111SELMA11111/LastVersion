package JavaDevProject;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ClientBaseHandler {
    private Connection connection(){
        String url = "jdbc:mysql://localhost:3306/projectbase";
        String user = "root";
        String password = "";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }


    public void addToConstrAvailability(String vehicleId, String commandId, LocalDate departureTime, LocalDate arrivalTime) {
        String sql = "INSERT INTO constr_disponibility (ID_Vehicule, Id_commande, temps_de_depart, temps_arrive) VALUES (?, ?, ?, ?)";
        try (Connection conn = connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicleId);
            pstmt.setString(2, commandId);
            pstmt.setDate(3, java.sql.Date.valueOf(departureTime));
            pstmt.setDate(4, java.sql.Date.valueOf(arrivalTime));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding to vehicle availability: " + e.getMessage());
        }
    }

    public List<Vehicule> getAvailableVehicles() {
        List<Vehicule> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicule WHERE status = 'available'";
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Vehicule vehicle = new Vehicule(
                        rs.getString("vehicule_id"),
                        rs.getString("mode_id"),
                        rs.getString("immatriculation"),
                        rs.getString("Type"),
                        rs.getString("capacite"),
                        rs.getString("status")
                );
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public void updateClientStatus(ClientsEnAtt client) {
        Connection connection = connection();
        if (connection != null) {
            try {
                String sql = "UPDATE users SET Status = ? WHERE Id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, client.getProspectStatus());
                statement.setString(2, client.getProspectId());
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Status updated successfully.");
                } else {
                    System.out.println("Failed to update status.");
                }
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to establish database connection.");
        }
    }

    public List<CommandeDetail> getCommandeDetails() {
        List<CommandeDetail> details = new ArrayList<>();
        String query = "SELECT c.client_id, c.commande_id, CONCAT('fer: ', c.fer_column, ', beton: ', c.beton_column, ', caillase: ', c.caillase_column, ', temps projet: ', c.temps_projet_column, ', nbre ouvrier: ', c.nbre_ouvrier_column, ', distance: ', c.distance_column) AS MaterieletMasse, GROUP_CONCAT(CONCAT(v.ID_Vehicule, ' (', v.temps_de_depart, ' - ', v.temps_arrive, ')') SEPARATOR '; ') AS VehiculesLivraison, CONCAT(c.depart_temps_column, ' - ', c.arrivee_temps_column) AS DateDepartArrivee FROM commandealldetails c LEFT JOIN vehicule_disponibility v ON c.commande_id = v.Id_commande LEFT JOIN constr_disponibility d ON c.commande_id = d.Id_commande GROUP BY c.client_id, c.commande_id;";
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CommandeDetail detail = new CommandeDetail(
                        rs.getString("client_id"),
                        rs.getString("commande_id"),
                        rs.getString("MaterieletMasse"),
                        rs.getString("VehiculesLivraison"),
                        rs.getString("DateDepartArrivee")
                );
                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }




    public List<Vehicule> getNotAvailableVehicles() {
        List<Vehicule> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicule WHERE status = 'not available'";
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Vehicule vehicle = new Vehicule(
                        rs.getString("vehicule_id"),
                        rs.getString("mode_id"),
                        rs.getString("immatriculation"),
                        rs.getString("Type"),
                        rs.getString("capacite"),
                        rs.getString("status")
                );
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }
    public List<Vehicule> getVehicles() {
        List<Vehicule> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicule ";
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Vehicule vehicle = new Vehicule(
                        rs.getString("vehicule_id"),
                        rs.getString("mode_id"),
                        rs.getString("immatriculation"),
                        rs.getString("Type"),
                        rs.getString("capacite"),
                        rs.getString("status")
                );
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<V_Construction> getAvailableConstr() {
        List<V_Construction> constructionList = new ArrayList<>();
        String query = "SELECT * FROM v_construction WHERE status = 'available'";
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                V_Construction equipment = new V_Construction(
                        rs.getString("vehicule_id"),
                        rs.getString("commonde_id"),
                        rs.getString("immatriculation"),
                        rs.getString("type"),
                        rs.getString("capacite"),
                        rs.getString("status")
                );
                constructionList.add(equipment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return constructionList;
    }

    public List<V_Construction> getNotAvailableConstr() {
        List<V_Construction> constructionList = new ArrayList<>();
        String query = "SELECT * FROM v_construction WHERE status = 'not available'";
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                V_Construction equipment = new V_Construction(
                        rs.getString("vehicule_id"),
                        rs.getString("commonde_id"),
                        rs.getString("immatriculation"),
                        rs.getString("type"),
                        rs.getString("capacite"),
                        rs.getString("status")
                );
                constructionList.add(equipment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return constructionList;
    }

    public List<V_Construction> getConstr() {
        List<V_Construction> constructionList = new ArrayList<>();
        String query = "SELECT * FROM v_construction";
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                V_Construction equipment = new V_Construction(
                        rs.getString("vehicule_id"),
                        rs.getString("commonde_id"),
                        rs.getString("immatriculation"),
                        rs.getString("type"),
                        rs.getString("capacite"),
                        rs.getString("status")
                );
                constructionList.add(equipment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return constructionList;
    }


    public List<V_Disponibiliy> getC_Disponibiliy() {
        List<V_Disponibiliy> vehicles = new ArrayList<>();
        String query = "SELECT ID_Vehicule , Id_commande ,temps_de_depart ,temps_arrive FROM constr_disponibility ";
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                V_Disponibiliy vehicle = new V_Disponibiliy(
                        rs.getString("ID_Vehicule"),
                        rs.getString("Id_commande"),
                        rs.getDate("temps_de_depart").toLocalDate(),
                        rs.getDate("temps_arrive").toLocalDate()

                );
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }


    public List<Commande> getMateriel() {
        List<Commande> vehicles = new ArrayList<>();
        String query = "SELECT  `commande_id`, `fer_column`, `beton_column`, `caillase_column`, `temps_projet_column`, `nbre_ouvrier_column`, `distance_column` FROM commandealldetails ";
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Commande vehicle = new Commande(
                        rs.getString("commande_id"),
                        rs.getString("fer_column"),
                        rs.getString("beton_column"),
                        rs.getString("caillase_column"),
                        rs.getString("temps_projet_column"),
                        rs.getString("distance_column"),
                        rs.getString("nbre_ouvrier_column")  // Add this if it's intended to be part of the Commande object
                );


                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicule> getFilteredVehicles(String filter) {
        List<Vehicule> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicule WHERE status = ?";
        try (Connection conn = connection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, filter);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Vehicule vehicle = new Vehicule(
                            rs.getString("vehicule_id"),
                            rs.getString("mode_id"),
                            rs.getString("immatriculation"),
                            rs.getString("Type"),
                            rs.getString("capacite"),
                            rs.getString("status")
                    );
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }




    public List<V_Disponibiliy> getV_Disponibiliy() {
        List<V_Disponibiliy> vehicles = new ArrayList<>();
        String query = "SELECT ID_Vehicule , Id_commande ,temps_de_depart ,temps_arrive FROM vehicule_disponibility ";
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                V_Disponibiliy vehicle = new V_Disponibiliy(
                        rs.getString("ID_Vehicule"),
                        rs.getString("Id_commande"),
                        rs.getDate("temps_de_depart").toLocalDate(),
                        rs.getDate("temps_arrive").toLocalDate()

                );
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Order> getOngoingOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM commandes WHERE lanced = 'encours'"; // Filtre pour obtenir uniquement les commandes en cours
        try (Connection conn = connection();  // Assurez-vous que la méthode `connection()` est bien définie pour obtenir une connexion à la base de données
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getString("type_construction"),
                        rs.getString("commande_id"),
                        rs.getString("adresse"),
                        rs.getString("description"),
                        rs.getString("lanced")  // Assurez-vous que l'objet Order peut stocker l'état 'lanced'
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();  // Gestion des erreurs SQL
        }
        return orders;

    }







    public List<ClientsEnAtt> getItems(){
        List<ClientsEnAtt> clientsEnAtts = new ArrayList<>();
        String query = "SELECT Id, Name, Prenam, Sex, Email, Telephone, Address, City, DateInscription, ServiceProposer, DateService, TypeService, Description, Status FROM users WHERE Status = ''" ;
        try (Connection conn = connection()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    ClientsEnAtt clientsEnAtt = new ClientsEnAtt();
                    clientsEnAtt.setProspectId(rs.getString("Id"));
                    clientsEnAtt.setProspectName(rs.getString("Name"));
                    clientsEnAtt.setProspectPrenam(rs.getString("Prenam"));
                    clientsEnAtt.setProspectSex(rs.getString("Sex"));
                    clientsEnAtt.setProspectEmail(rs.getString("Email"));
                    clientsEnAtt.setProspectTelephone(rs.getInt("Telephone"));
                    clientsEnAtt.setProspectAddress(rs.getString("Address"));
                    clientsEnAtt.setProspectCity(rs.getString("City"));
                    clientsEnAtt.setProspectDateInscription(rs.getDate("DateInscription"));
                    clientsEnAtt.setProspectServiceProposer(rs.getString("ServiceProposer"));
                    clientsEnAtt.setProspectDateService(rs.getDate("DateService"));
                    clientsEnAtt.setProspectTypeService(rs.getString("TypeService"));
                    clientsEnAtt.setProspectDescription(rs.getString("Description"));
                    clientsEnAtt.setProspectStatus(rs.getString("Status"));
                    clientsEnAtts.add(clientsEnAtt);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching items: " + e.getMessage());
        }
        return clientsEnAtts;
    }



    public List<V_Construction> getV_ConstructionStatus() {
        List<V_Construction> V_cons = new ArrayList<>();
        String query = "SELECT vehicule_id, commonde_id, immatriculation, Type, capacite, status FROM V_Construction";
        try (Connection conn = connection()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    V_Construction vehicle =  new V_Construction(
                            rs.getString("vehicule_id"),
                            rs.getString("commonde_id"),
                            rs.getString("immatriculation"),
                            rs.getString("type"),
                            rs.getString("capacite"),
                            rs.getString("status")
                    );
                    V_cons.add(vehicle);
                }
            }
        } catch (SQLException e) {
            // Consider logging the error or throwing an exception for better error handling
            System.out.println("Error fetching V_Construction status: " + e.getMessage());
        }
        return V_cons;
    }


    public List<Vehicule> getVehicleStatus() {
        List<Vehicule> vehicles = new ArrayList<>();
        String query = "SELECT vehicule_id, mode_id, immatriculation,Type, capacite, status FROM vehicule";
        try (Connection conn = connection()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Vehicule vehicle = new Vehicule(
                            rs.getString("vehicule_id"),
                            rs.getString("mode_id"),
                            rs.getString("immatriculation"),
                            rs.getString("Type"),
                            rs.getString("capacite"),
                            rs.getString("status")
                    );
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching vehicle status: " + e.getMessage());
        }
        return vehicles;
    }

    public List<Clients> getItemss(){
        List<Clients> clientsEnAtts = new ArrayList<>();
        String query = "SELECT Id, Name, Prenam, Sex, Email, Telephone, Address, City, DateInscription, ServiceProposer, DateService, TypeService, Description, Status FROM users WHERE Status = 'Accepted' OR Status = 'Waited'" ;
        try (Connection conn = connection()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Clients clients = new Clients();
                    clients.setClientId((rs.getString("Id")));
                    clients.setClientName(rs.getString("Name"));
                    clients.setClientPrenam(rs.getString("Prenam"));
                    clients.setClientSex(rs.getString("Sex"));
                    clients.setClientEmail(rs.getString("Email"));
                    clients.setClientTelephone(rs.getInt("Telephone"));
                    clients.setClientAddress(rs.getString("Address"));
                    clients.setClientCity(rs.getString("City"));
                    clients.setClientDateInscription(rs.getDate("DateInscription"));
                    clients.setClientServiceProposer(rs.getString("ServiceProposer"));
                    clients.setClientDateService(rs.getDate("DateService"));
                    clients.setClientTypeService(rs.getString("TypeService"));
                    clients.setClientDescription(rs.getString("Description"));
                    clients.setClientStatus(rs.getString("Status"));
                    clientsEnAtts.add(clients);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching items: " + e.getMessage());
        }
        return clientsEnAtts;
    }
    public List<ClientRefused> getItemsss(){
        List<ClientRefused> clientsRefused = new ArrayList<>();
        String query = "SELECT Id, Name, Prenam, Sex, Email, Telephone, Address, City, DateInscription, ServiceProposer, DateService, TypeService, Description, Status FROM users WHERE Status = 'Not Accepted'" ;
        try (Connection conn = connection()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    ClientRefused Clientrefused = new ClientRefused();
                    Clientrefused.setRefusedId((rs.getString("Id")));
                    Clientrefused.setRefusedName(rs.getString("Name"));
                    Clientrefused.setRefusedPrenam(rs.getString("Prenam"));
                    Clientrefused.setRefusedSex(rs.getString("Sex"));
                    Clientrefused.setRefusedEmail(rs.getString("Email"));
                    Clientrefused.setRefusedTelephone(rs.getInt("Telephone"));
                    Clientrefused.setRefusedAddress(rs.getString("Address"));
                    Clientrefused.setRefusedCity(rs.getString("City"));
                    Clientrefused.setRefusedDateInscription(rs.getDate("DateInscription"));
                    Clientrefused.setRefusedServiceProposer(rs.getString("ServiceProposer"));
                    Clientrefused.setRefusedDateService(rs.getDate("DateService"));
                    Clientrefused.setRefusedTypeService(rs.getString("TypeService"));
                    Clientrefused.setRefusedDescription(rs.getString("Description"));
                    Clientrefused.setRefusedStatus(rs.getString("Status"));
                    clientsRefused.add(Clientrefused);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching items: " + e.getMessage());
        }
        return clientsRefused;
    }

    public void addToVehicleAvailability(String vehicleId, String commandId, LocalDate departureTime, LocalDate arrivalTime) {
        String sql = "INSERT INTO Vehicule_disponibility (ID_Vehicule, Id_commande, temps_de_depart, temps_arrive) VALUES (?, ?, ?, ?)";
        try (Connection conn = connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicleId);
            pstmt.setString(2, commandId);
            pstmt.setDate(3, java.sql.Date.valueOf(departureTime));
            pstmt.setDate(4, java.sql.Date.valueOf(arrivalTime));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding to vehicle availability: " + e.getMessage());
        }
    }



    public void insertDataWithClientAndCommandId(String clientId, String commandId, String fer, String beton, String caillase, String tempsProjet,
                                                 String nbreOuvrier, String distance, LocalDate departTemps, LocalDate arriveeTemps,
                                                 boolean status) {
        String sql = "INSERT INTO commandealldetails (client_id, commande_id, fer_column, beton_column, caillase_column, temps_projet_column, " +
                "nbre_ouvrier_column, distance_column, depart_temps_column, arrivee_temps_column, status_column) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, clientId);
            preparedStatement.setString(2, commandId);
            preparedStatement.setString(3, fer);
            preparedStatement.setString(4, beton);
            preparedStatement.setString(5, caillase);
            preparedStatement.setString(6, tempsProjet);
            preparedStatement.setString(7, nbreOuvrier);
            preparedStatement.setString(8, distance);
            preparedStatement.setDate(9, java.sql.Date.valueOf(departTemps));
            preparedStatement.setDate(10, java.sql.Date.valueOf(arriveeTemps));
            preparedStatement.setBoolean(11, status);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}