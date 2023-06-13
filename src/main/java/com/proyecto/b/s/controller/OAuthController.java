package com.proyecto.b.s.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;


@RestController
@RequestMapping("/oauth")
public class OAuthController {
    private static final String CLIENT_ID = "375061223731-oqiif09lhme5265rss2at3k3ulhlcjc6.apps.googleusercontent.com";
    private static final String REDIRECT_URI = "http://localhost:3000/evento/entrevista";
    private static final String SCOPE = "https://www.googleapis.com/auth/calendar.events";
    private final Calendar client;
    private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String APPLICATION_NAME = "B&S";

    public OAuthController() throws IOException {
        // Crea un objeto Credential utilizando los tokens de acceso almacenados previamente
        Credential credential = new GoogleCredential().setAccessToken("TU_TOKEN_DE_ACCESO");

        // Crea el cliente de la API de Google Calendar utilizando la biblioteca google-api-java-client
        client = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @GetMapping("/authorize")
    public String authorize() {
        String authorizationUrl = "https://accounts.google.com/o/oauth2/v2/auth" +
                "?response_type=token" +
                "&client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URI +
                "&scope=" + SCOPE;

        return "redirect:" + authorizationUrl;
    }

    @GetMapping("/evento/entrevista")
    public String handleAuthorizationResponse(@RequestParam("access_token") String accessToken,
                                              @RequestParam("expires_in") Long expiresIn,
                                              @RequestParam("token_type") String tokenType) {
        // Aquí puedes guardar los tokens de acceso en la base de datos o en una sesión
        // para utilizarlos en futuras interacciones con la API de Google Calendar

        // Realiza las operaciones necesarias utilizando los tokens de acceso

        return "redirect:/ruta-a-continuación"; // Redirige a la siguiente página de tu aplicación
    }

    @PostMapping("/events")
    public String createEvent(@RequestBody Event event) throws IOException {
        // Crea el evento en el calendario utilizando el objeto cliente de la API de Google Calendar
        Event createdEvent = client.events().insert("primary", event).execute();

        // Devuelve la ID del evento creado
        return createdEvent.getId();
    }
}
