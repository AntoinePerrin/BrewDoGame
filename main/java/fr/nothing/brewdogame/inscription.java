package fr.nothing.brewdogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;

public class inscription extends AppCompatActivity {

    private ArrayList<String> GroupeCouple = new ArrayList<>();
    private boolean hardcore = false;
    private String res = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        TextView titleBienvenue = findViewById(R.id.titleApp);
        titleBienvenue.setText(R.string.Bienvenue);

        final CheckBox plus = findViewById(R.id.plus);
        plus.setText(R.string.metttre);

        TextView nextToGame = findViewById(R.id.passageJeux);
        nextToGame.setText(R.string.launchparty);
        nextToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajouterJoueur();
                if (GroupeCouple.size() > 3) {
                    if (plus.isChecked()){
                        hardcore = true;
                    }
                    Intent jeux = new Intent(getApplicationContext(), MainActivity.class);
                    Bundle b = new Bundle();
                    b.putStringArrayList("ListeCouple", GroupeCouple);
                    b.putBoolean("hardcore",hardcore);
                    jeux.putExtras(b);
                    startActivity(jeux);
                    finish();
                } else {
                    TextView error = findViewById(R.id.error);
                    error.setText(R.string.errorPasAssezJoueur);
                }
            }
        });

        TextView addPlayer = findViewById(R.id.addPlayer);
        addPlayer.setText(R.string.addplayer);
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajouterJoueur();

            }
        });

    }

    public void ajouterJoueur(){
        final EditText nomJoueur1Wrapper = findViewById(R.id.joueur1);
        final EditText nomJoueur2Wrapper = findViewById(R.id.joueur2);
        final EditText nomEquipeWrapper = findViewById(R.id.nomEquipe);
        TextView error = findViewById(R.id.error);
        try {
            String nomJoueur1 = nomJoueur1Wrapper.getText().toString().replaceAll("\\s+","");
            String nomJoueur2 = nomJoueur2Wrapper.getText().toString().replaceAll("\\s+","");
            String nomEquipe = nomEquipeWrapper.getText().toString().replaceAll("\\s+","");
            if( nomJoueur1.length() > 0 & nomJoueur2.length() > 0 & nomEquipe.length() > 0) {
                if ((!GroupeCouple.contains(nomJoueur1) & !GroupeCouple.contains(nomJoueur2)) & !(nomJoueur1.equals(nomJoueur2))) {
                    GroupeCouple.add(nomJoueur1);
                    GroupeCouple.add(nomJoueur2);
                    GroupeCouple.add(nomEquipe);
                    nomJoueur2Wrapper.getText().clear();
                    nomJoueur1Wrapper.getText().clear();
                    nomEquipeWrapper.getText().clear();
                    res = res + nomJoueur1 + " et " + nomJoueur2 + " de l'équipe " + nomEquipe + " ont bien été ajoutés au jeux.\n";
                    error.setText(res);
                } else {
                    error.setText(R.string.duplicate);
                }
            } else  {
                error.setText(R.string.errorEmptyPlayer);
            }
        } catch (NullPointerException e){
            error.setText(R.string.errorNullPointer);
        }
    }
}
