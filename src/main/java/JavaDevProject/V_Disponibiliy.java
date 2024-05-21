package JavaDevProject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class V_Disponibiliy {
    private final StringProperty vehicleId; // Renommé de 'id' à 'vehicleId' pour correspondre au nom de colonne de la base de données
    private final StringProperty commandId; // Propriété ajoutée pour commandId
    private final ObjectProperty<LocalDate> departure;
    private final ObjectProperty<LocalDate> arrival;

    public V_Disponibiliy(String vehicleId, String commandId, LocalDate departure, LocalDate arrival) {
        this.vehicleId = new SimpleStringProperty(vehicleId);
        this.commandId = new SimpleStringProperty(commandId);
        this.departure = new SimpleObjectProperty<>(departure);
        this.arrival = new SimpleObjectProperty<>(arrival);
    }

    // Getter pour obtenir la valeur de vehicleId
    public String getVehicleId() {
        return vehicleId.get();
    }

    // Getter pour obtenir la valeur de commandId
    public String getCommandId() {
        return commandId.get();
    }

    // Getter pour obtenir la valeur de departure
    public LocalDate getDeparture() {
        return departure.get();
    }

    // Getter pour obtenir la valeur de arrival
    public LocalDate getArrival() {
        return arrival.get();
    }

    public StringProperty vehicleIdProperty() {
        return vehicleId;
    }

    public StringProperty commandIdProperty() {
        return commandId;
    }

    public ObjectProperty<LocalDate> departureProperty() {
        return departure;
    }

    public ObjectProperty<LocalDate> arrivalProperty() {
        return arrival;
    }
}
