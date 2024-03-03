import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Film ismi girin: ");
        String movieTitle = scan.nextLine();
        scan.close();

        movieTitle = movieTitle.replace(" ","%20");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb146.p.rapidapi.com/v1/find/?query="+movieTitle))
                .header("X-RapidAPI-Key", "9f99925037mshc89f901d146a346p1749efjsn262fca174129")
                .header("X-RapidAPI-Host", "imdb146.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            FileWriter file = new FileWriter("response.json");
            file.write(response.body());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
    }
}
