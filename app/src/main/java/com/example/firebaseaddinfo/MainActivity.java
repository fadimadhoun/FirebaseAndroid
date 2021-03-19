package com.example.firebaseaddinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore firestore ;
    List<User> userList = new ArrayList<>();
    Adapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firestore = FirebaseFirestore.getInstance();

        Button addBtm = findViewById(R.id.addbutton);
        EditText name = findViewById(R.id.editTextTextPersonName);
        EditText number = findViewById(R.id.editTextTextPersonName2);
        EditText address = findViewById(R.id.editTextTextPersonName3);
        listView = findViewById(R.id.listView);

        adapter = new Adapter(getApplicationContext() , userList);
        listView.setAdapter(adapter);

        addBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference collectionReference =  firestore.collection("Users");
                User u1 = new User();
                u1.setName(name.getText().toString());
                u1.setNumber(number.getText().toString());
                u1.setAddress(address.getText().toString());
                collectionReference.add(u1);
               // RetrieveData();


            }
        });



    }

    private void RetrieveData(){
        CollectionReference bookCollection =  firestore.collection("Users");
        Task<QuerySnapshot> t =  bookCollection.get();
        t.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    for (DocumentSnapshot docSnap:queryDocumentSnapshots.getDocuments()){
                        User u =  docSnap.toObject(User.class);
                        userList.add(u);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        t.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext() , e.getMessage()+"" , Toast.LENGTH_LONG).show();
            }
        });

    }

}