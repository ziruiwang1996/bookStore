package com.cse687.zirui.bookstore.order.service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookFetcher {
    private static final String GOOGLE_BOOK_API_URL = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    public static Map<String, String> getBook(String isbn) {
        Map<String, String> result = new LinkedHashMap<>();
        String url = GOOGLE_BOOK_API_URL + isbn;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONArray items = jsonResponse.getJSONArray("items");
                if (items.length() > 0) {
                    JSONObject volumeInfo = items.getJSONObject(0).getJSONObject("volumeInfo");
                    String title = volumeInfo.optString("title", "N/A");
                    JSONArray authorsArray = volumeInfo.optJSONArray("authors");
                    StringBuilder authors = new StringBuilder();
                    if (authorsArray != null) {
                        for (int i = 0; i < authorsArray.length(); i++) {
                            authors.append(authorsArray.getString(i));
                            if (i < authorsArray.length() - 1) {
                                authors.append(", ");
                            }
                        }
                    } else {
                        authors.append("N/A");
                    }
                    String publisher = volumeInfo.optString("publisher", "N/A");
                    result.put("title", title);
                    result.put("authors", authors.toString());
                    result.put("publisher", publisher);
                } else {
                    result.put("error", "No book found for ISBN: " + isbn);
                }
            } else {
                result.put("error", "HTTP error code: " + response.statusCode());
            }
        } catch (Exception e) {
            result.put("error", "Exception: " + e.getMessage());
        }
        return result;
    }

    // For testing
    public static void main(String[] args) {
        Map<String, String> data = BookFetcher.getBook("9780593437810");
        System.out.println(new JSONObject(data).toString(2));
    }
}
