package com.springbootfirebase.configuration;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FirabaseConnect {

    public FirabaseConnect() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("testefirebase-86699.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://testefirebase-86699.firebaseio.com")
        .build();

        FirebaseApp.initializeApp(options);

    }

    
    
}