package com.metamong.server.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseInitService {
    private String firebaseSdkPath = "firebase/favorable-bolt-113915-firebase-adminsdk-bs5he-6fa8592e52.json";

    @PostConstruct
    public void init() throws IOException {

        Resource resource = new ClassPathResource(firebaseSdkPath);
        InputStream inputStream = resource.getInputStream();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build();
        if (FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
        }
    }

}
