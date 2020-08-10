package com.springbootfirebase.application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.JsonObject;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("/")
    public String home() {
        return "Hello Word!";
    }

    @PostMapping("/person")
    public String save(@RequestBody Map<String, Object> obj) throws IOException, InterruptedException, ExecutionException {
                
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("person").document();
        docRef.create(obj);  
        
        JsonObject response = new JsonObject();
        response.addProperty("id", docRef.getId());

        return response.toString();
    }

    @GetMapping("/person")
    public Object getPerson() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("person").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        List<Object> data = new ArrayList<Object>();
       
        for (QueryDocumentSnapshot document : documents) {
            data.add(document.getData());
        }

        return data;
    }

    @DeleteMapping("/person")
    public Object getPerson(@RequestParam String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("person").document(id);
        docRef.delete();
        JsonObject response = new JsonObject();
        response.addProperty("id", docRef.getId());
        response.addProperty("status","deleted");

        return response.toString();
    }


    
}