package fr.nothing.brewdogame;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;


import java.util.ArrayList;
import java.util.List;

public class inscription extends AppCompatActivity {

    ArrayList<Couple> GroupeCouple;
    final private TextInputLayout nomJoueur1Wrapper = (TextInputLayout) findViewById(R.id.joueur1);
    final private TextInputLayout nomJoueur2Wrapper = (TextInputLayout) findViewById(R.id.joueur2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        TextView nextToGame = (TextView) findViewById(R.id.passageJeux);
        nextToGame.setText(R.string.launchparty);

        TextView addPlayer = (TextView) findViewById(R.id.addPlayer);
        addPlayer.setText(R.string.addplayer);

        nomJoueur1Wrapper.setHint("Nom Joueur");
        nomJoueur2Wrapper.setHint("Nom Joueur");

        nextToGame.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent jeux = new Intent (getApplicationContext(), MainActivity.class);
                Bundle b = new Bundle();
                b.putInt("ListeCouple", 1); //A modifier pour passer la liste des couples
                jeux.putExtras(b);
                startActivity(jeux);
                finish();
            }
        });

    }

    public void ajouterJoueur(android.view.View KGB){
        ArrayList tempJoueur = new ArrayList() ;
        String nomJoueur1 = nomJoueur1Wrapper.getEditText().getText().toString();
        String nomJoueur2 = nomJoueur2Wrapper.getEditText().getText().toString();
        tempJoueur.add(nomJoueur1);
        tempJoueur.add(nomJoueur2);
        Couple tempCouple = new Couple(tempJoueur);
        GroupeCouple.add(tempCouple);
    }
}
