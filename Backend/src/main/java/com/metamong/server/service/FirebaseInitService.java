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
import java.util.List;

@Service
public class FirebaseInitService {
    private String firebaseSdkPath = "firebase/favorable-bolt-113915-firebase-adminsdk-bs5he-6fa8592e52.json";

    @PostConstruct
    public void init() throws IOException {

        Resource resource = new ClassPathResource(firebaseSdkPath);
        InputStream inputStream = resource.getInputStream();
        
        FirebaseApp firebaseApp = null;
		List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
		if(firebaseApps != null && !firebaseApps.isEmpty()) {
			for(FirebaseApp app : firebaseApps) {
				if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
					firebaseApp = app;
			}
		} else {

			FirebaseOptions options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(inputStream))
			  .setDatabaseUrl("https://favorable-bolt-113915-default-rtdb.firebaseio.com")
			  .build();
			
			FirebaseApp.initializeApp(options);
		}
        
    }

}
