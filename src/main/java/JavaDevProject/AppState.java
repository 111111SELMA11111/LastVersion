package JavaDevProject;

public class AppState {
    private static String selectedOrderId;

    public static String getSelectedOrderId() {
        return selectedOrderId;
    }

    public static void setSelectedOrderId(String orderId) {
        selectedOrderId = orderId;
    }
}
