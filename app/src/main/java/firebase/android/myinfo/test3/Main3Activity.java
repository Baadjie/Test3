package firebase.android.myinfo.test3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Main3Activity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private DatabaseReference db;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



        Intent intent=getIntent();
        final String key=intent.getStringExtra("name");

        final EditText editTextName,editTextSurname;

        editTextName= (EditText) findViewById(R.id.editText3);
        editTextSurname= (EditText) findViewById(R.id.editText4);
        btnUpdate= (Button) findViewById(R.id.button3);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Person").child(key);
        db=FirebaseDatabase.getInstance().getReference().child("person").child(key);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Person person=dataSnapshot.getValue(Person.class);
                editTextName.setText(person.getName());
                editTextSurname.setText(person.getSurname());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        person.setName(editTextName.getText().toString());
                        person.setSurname(editTextSurname.getText().toString());
                        db.setValue(person);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    }

