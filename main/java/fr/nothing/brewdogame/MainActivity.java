package fr.nothing.brewdogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.widget.TextView;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private String  anciennequestion;
    private int joueurid = 1, rank = 0, level = 0, groupeid = 1, nbCouple = 0;
    public ArrayList<Couple> groupeCouple;
    public String joueurName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b = getIntent().getExtras();
        if (b != null){
            joueurManager(b.getStringArrayList("ListeCouple"));
        }

        TextView joueur = findViewById(R.id.joueur);
        joueur.setText(joueurid);

        TextView titleBienvenue = findViewById(R.id.title);
        titleBienvenue.setText(R.string.Bienvenue);

        TextView buttonMoi1 = findViewById(R.id.carteMoi);
        buttonMoi1.setText(R.string.cmoi);

        TextView buttonAutre1 = findViewById(R.id.carteAutre);
        buttonAutre1.setText(R.string.cautre);


        TextView question = findViewById(R.id.question);
        question.setText(R.string.Bienvenue);

    }

    public void joueurManager (ArrayList<String> listeJoueur){
        nbCouple = listeJoueur.size();
        Couple temp;
        String joueur1, joueur2;
        Iterator<String> it = listeJoueur.iterator();
        while (it.hasNext()) {
            it.next();
            joueur1 = it.toString();
            it.next();
            joueur2 = it.toString();
            temp = new Couple(joueur1, joueur2);
            groupeCouple.add(temp);
        }
    }

    public void buttonMoi(android.view.View KGB) {
        buttonManager("Moi");
    }

    public void buttonAutre(android.view.View KGB) {
        buttonManager("Autre");
    }

    public void buttonManager(String buttonJoue) {

        // enregistre valeur
        // change nom joueur ou change question ou change groupe
        if (joueurid == 1) {
            anciennequestion = buttonJoue;
        }
        if (joueurid == 2) {
            if (anciennequestion.equals(buttonJoue)) {
                boisFunction(0);
                anciennequestion = "";
                if (level == 0){
                    groupeCouple.get(groupeid).add1PointQ1();
                }
                if (level == 1){
                    groupeCouple.get(groupeid).add1PointQ2();
                }
                if (level == 2){
                    groupeCouple.get(groupeid).add1PointQ3();
                }

            } else {
                boisManager();
            }

            if (level < 2) {
                level = level + 1;
            } else {
                rank = rank + 1;
                level = 0;
            }
            changeQuestion();
        }
        changeJoueur();
    }

    public void boisManager(){
        if (level == 1){
            boisFunction(1);
        }
        if (level == 2 ){
            boisFunction(2);
        }
        if (level ==3 ){
            boisFunction(3);
        }
    }

    public void changeJoueur() {
        TextView joueurTitle = findViewById(R.id.joueur);
        if (joueurid == 1) {
            joueurid = 2;
            joueurName = groupeCouple.get(groupeid).Joueur.get(1);
        } else {
            joueurid = 1;
            if (groupeid < nbCouple){
                groupeid += 1;
            } else {
                groupeid = 0;
            }
            joueurName = groupeCouple.get(groupeid).Joueur.get(0);
        }
        joueurTitle.setText(joueurName);
    }

    public void changeQuestion() {
        int ligne = rank + level * 50;
        //recupere la ligne dans le fichier
        TextView question = findViewById(R.id.question);
        String temp = "Plutot soumis ou dominant" + ligne;
        question.setText(temp);
    }

    public void boisFunction(int gorge) {
        TextView bois = findViewById(R.id.bois);
        //bois.setText("Tu bois ta race connard !"/*stringToBois()*/);
        String res = "";
        if (gorge > 1) {
            res = "Tu bois " + gorge + " gorgés";
        }
        if (gorge == 1) {
            res = "Tu bois " + gorge + " gorgé";
        }
        if (gorge == 0) {
            res = "Vous avez bien répondu";
        }
        bois.setText(res);
    }
    }
