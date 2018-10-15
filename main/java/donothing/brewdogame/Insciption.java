package donothing.brewdogame;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;


import java.util.List;

public class Insciption extends AppCompatActivity {

    //List<Couple> GroupeCouple;
    TextInputLayout nomJoueur1Wrapper = (TextInputLayout) findViewById(R.id.joueur1);
    TextInputLayout nomJoueur2Wrapper = (TextInputLayout) findViewById(R.id.joueur2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insciption);

        TextView nextToGame = (TextView) findViewById(R.id.passageJeux);
        nextToGame.setText("On balance la purée !");

        TextView addPlayer = (TextView) findViewById(R.id.addPlayer);
        addPlayer.setText("On aime partager");

        nomJoueur1Wrapper.setHint("Nom Joueur");
        nomJoueur2Wrapper.setHint("Nom Joueur");

        /*nextToGame.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent jeux = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(jeux);
                finish();
            }
        });*/

    }

    public void ajouterJoueur(android.view.View KGB){
        String nomJoueur1 = nomJoueur1Wrapper.getEditText().getText().toString();
        String nomJoueur2 = nomJoueur2Wrapper.getEditText().getText().toString();


    }
}
