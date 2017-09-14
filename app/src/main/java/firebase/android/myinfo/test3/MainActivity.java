package firebase.android.myinfo.test3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private Person person;


    EditText edt1,edit2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        person=new Person();

        edt1= (EditText) findViewById(R.id.editText);
        edit2= (EditText) findViewById(R.id.editText2);
        button= (Button) findViewById(R.id.button);

        Button btnView= (Button) findViewById(R.id.button2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   String name=person.getName();
//                String name1=edit2.getText().toString();

                databaseReference= FirebaseDatabase.getInstance().getReference();
                person.setName(edt1.getText().toString());
                person.setSurname(edit2.getText().toString());
                databaseReference.child("Person").push().setValue(person);


            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

    }
    }

