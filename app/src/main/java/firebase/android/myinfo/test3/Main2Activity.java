package firebase.android.myinfo.test3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private Person person;

    private FirebaseRecyclerAdapter<Person,PersonViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Person");
        adapter=new FirebaseRecyclerAdapter<Person, PersonViewHolder>(Person.class,R.layout.custom,PersonViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(PersonViewHolder viewHolder, Person model, final int position) {
                viewHolder.setSurname(model.getSurname());
                viewHolder.setName(model.getName());
                // viewHolder.setCard();
                viewHolder.Card().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Delete or Update");
                        builder.setMessage("are you sure you want to update ?");
                        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                             //   String key=getRef(position).getKey();
                                String key=getRef(position).getKey();
                                databaseReference= FirebaseDatabase.getInstance().getReference().child("Person").child(key);
                                  Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                                  intent.putExtra("name",key);
                                    startActivity(intent);
                                dialog.dismiss();
                            }
                        });

                        // builder.show();
                        builder.setMessage("do you want to ?");
                        builder.setNegativeButton("delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getRef(position).removeValue();
                                Toast.makeText(Main2Activity.this,"record deleted",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });
                        builder.show();

                    }

                });


            }
        };


        RecyclerView recyclerView;
        recyclerView= (RecyclerView) findViewById(R.id.recy);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }



    //retriving
    private static class PersonViewHolder extends RecyclerView.ViewHolder{
        private View view;
        public PersonViewHolder(View itemView) {


            super(itemView);
            view =itemView;
        }

        public void setName(String name){

            TextView tvName= (TextView) view.findViewById(R.id.txtName);
            tvName.setText(name);
        }

        public void setSurname(String surname){

            TextView tvSurname= (TextView) view.findViewById(R.id.textView2);
            tvSurname.setText(surname);
        }

        public CardView Card(){


            CardView cardView= (CardView) view.findViewById(R.id.cardView);
            return  cardView;
        }
    }
}
