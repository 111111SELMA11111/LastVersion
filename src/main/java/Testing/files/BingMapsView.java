package Testing.files;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class BingMapsView extends Application {

    private Label distanceLabel;

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();

        distanceLabel = new Label("Distance: ");

        // HTML content with Bing Maps
        String mapHtml = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
                    <script type='text/javascript' src='https://www.bing.com/api/maps/mapcontrol?key=YOUR_BING_MAPS_API_KEY&callback=loadMapScenario' async defer></script>
                    <script type='text/javascript'>
                    var selectedCities = [];
                    
                    function loadMapScenario() {
                        var map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
                            /* Initialize latitude and longitude for the center of the map */
                            center: new Microsoft.Maps.Location(47.6062, -122.3321),
                            zoom: 12
                        });

                        // Add click event listener to the map
                        map.addListener('click', function(e) {
                            // Add clicked location to selectedCities array
                            selectedCities.push(e.location);
                            
                            // If two cities are selected, calculate distance and update label
                            if(selectedCities.length === 2) {
                                var distance = Microsoft.Maps.Location.calculateDistance(selectedCities[0], selectedCities[1]);
                                javaConnector.onCitiesSelected(selectedCities[0].latitude, selectedCities[0].longitude, selectedCities[1].latitude, selectedCities[1].longitude, distance);
                                
                                // Clear the selectedCities array after two cities are selected
                                selectedCities = [];
                            }
                        });
                    }
                    </script>
                </head>
                <body>
                    <div id='myMap' style='width: 100%; height: 80vh;'></div>
                    <div id='distanceLabel'></div>
                </body>
                </html>
                """;

        // Replace placeholder with your actual API key
        mapHtml = mapHtml.replace("YOUR_BING_MAPS_API_KEY", "FHRvwTRE223IW51stO4H~-uz9drSHQRLamoLLuf0wxw~Am7F8ucUYpbEfGbb3gHuzsN-C7oXxmdhQtHLFIQpz5obPdErlkoBe7gfCooZXtid");

        // Load HTML content into WebView
        webView.getEngine().loadContent(mapHtml);

        // Enable JavaScript communication with JavaFX
        JSObject window = (JSObject) webView.getEngine().executeScript("window");
        window.setMember("javaConnector", new JavaConnector());

        BorderPane layout = new BorderPane();
        layout.setTop(distanceLabel);
        layout.setCenter(webView);

        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("JavaFX Bing Maps Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // JavaConnector class to handle communication from JavaScript to JavaFX
    public class JavaConnector {
        // Method to handle cities selected event from JavaScript
        public void onCitiesSelected(double lat1, double lon1, double lat2, double lon2, double distance) {
            System.out.println("Cities selected: (" + lat1 + ", " + lon1 + ") and (" + lat2 + ", " + lon2 + ")");
            System.out.println("Distance: " + distance + " meters");

            // Update distance label
            distanceLabel.setText("Distance: " + distance + " meters");
        }
    }
}
