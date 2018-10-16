package fr.nothing.brewdogame;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;


//import java.security.acl.Group;
import java.util.ArrayList;

public class inscription extends AppCompatActivity {

    ArrayList<String> GroupeCouple = new ArrayList<>();
    /*final private TextInputLayout nomJoueur1Wrapper = (TextInputLayout) findViewById(R.id.joueur1);
    final private TextInputLayout nomJoueur2Wrapper = (TextInputLayout) findViewById(R.id.joueur2);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        TextView nextToGame = findViewById(R.id.passageJeux);
        nextToGame.setText(R.string.launchparty);

        TextView titleBienvenue = findViewById(R.id.titleApp);
        titleBienvenue.setText(R.string.Bienvenue);

        nextToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jeux = new Intent(getApplicationContext(), MainActivity.class);
                Bundle b = new Bundle();
                b.putStringArrayList("ListeCouple", GroupeCouple);
                jeux.putExtras(b);
                startActivity(jeux);
                finish();
            }
        });

        TextView addPlayer = findViewById(R.id.addPlayer);
        addPlayer.setText(R.string.addplayer);

    }

    public void ajouterJoueur(android.view.View KGB){
        /*try {
            String nomJoueur1 = nomJoueur1Wrapper.getEditText().getText().toString();
            String nomJoueur2 = nomJoueur2Wrapper.getEditText().getText().toString();
            GroupeCouple.add(nomJoueur1);
            GroupeCouple.add(nomJoueur2);
        } catch (NullPointerException e){
            TextView error = findViewById(R.id.error);
            error.setText(R.string.errorNullPointer);
        }
        if(GroupeCouple.size() > 3) {
            nextToGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent jeux = new Intent(getApplicationContext(), MainActivity.class);
                    Bundle b = new Bundle();
                    b.putStringArrayList("ListeCouple", GroupeCouple); //A modifier pour passer la liste des couples
                    jeux.putExtras(b);
                    startActivity(jeux);
                    finish();
                }
            });
        }*/
        GroupeCouple.add("janine");
        GroupeCouple.add("jeanpiere");
        GroupeCouple.add("cassoulet");
        GroupeCouple.add("j'aime le veau");
    }
}
