package fr.nothing.brewdogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.AndroidException;
import android.widget.TextView;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private String anciennequestion;
    private int joueurid = 1, rank = 0, level = 0, groupeid = 0, nbCouple = 0;
    public ArrayList<Couple> groupeCouple = new ArrayList<>();
    public String joueurName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if (savedInstanceState == null) {
            Bundle b = getIntent().getExtras();
            if (b != null) {
                joueurManager( (ArrayList<String>) b.get("ListeCouple"));
            }
        } else {
            joueurManager((ArrayList<String>) savedInstanceState.getStringArrayList("ListeCouple"));
        }*/

        joueurManager();

        TextView joueur = findViewById(R.id.joueur);
        joueur.setText(joueurName);

        TextView buttonMoi1 = findViewById(R.id.carteMoi);
        buttonMoi1.setText(R.string.cmoi);

        TextView buttonAutre1 = findViewById(R.id.carteAutre);
        buttonAutre1.setText(R.string.cautre);

        changeQuestion();

    }

    public void joueurManager(ArrayList<String> listeJoueur) {
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

    public void joueurManager() {
        String joueur1 = "Panda", joueur2 = "Elise", joueur3 = "Damien", joueur4 = "Maitre Panda";
        joueurName = joueur1;
        Couple temp = new Couple(joueur1, joueur2);
        groupeCouple.add(temp);
        Couple temp2 = new Couple(joueur3, joueur4);
        groupeCouple.add(temp2);
        nbCouple = 2;
    }

    public void buttonMoi(android.view.View KGB) {
        buttonManager("Moi");
    }

    public void buttonAutre(android.view.View KGB) {
        buttonManager("Autre");
    }

    public void buttonManager(String buttonJoue) {

        if (joueurid == 1) {
            anciennequestion = buttonJoue;
        }
        if (joueurid == 2) {
            if (!anciennequestion.equals(buttonJoue)) {
                boisFunction(0);
                if (level == 0) {
                    groupeCouple.get(groupeid).add1PointQ1();
                }
                if (level == 1) {
                    groupeCouple.get(groupeid).add1PointQ2();
                }
                if (level == 2) {
                    groupeCouple.get(groupeid).add1PointQ3();
                }
            } else {
                boisManager();
            }
            anciennequestion = "";

            if (level < 2) {
                level = level + 1;
            } else {
                rank = rank + 1;
                level = 0;
                if (groupeid < nbCouple - 1) {
                    groupeid += 1;
                } else {
                    groupeid = 0;
                }
            }
            changeQuestion();
        }
        changeJoueur();

    }

    public void boisManager() {
        if (level == 0) { boisFunction(1);}
        if (level == 1) { boisFunction(2);}
        if (level == 2) { boisFunction(3);}
    }

    public void changeJoueur() {
        TextView joueurTitle = findViewById(R.id.joueur);
        if (joueurid == 1) {
            joueurid = 2;
            joueurName = groupeCouple.get(groupeid).Joueur.get(1);
        } else {
            joueurid = 1;
            joueurName = groupeCouple.get(groupeid).Joueur.get(0);
        }
        String auTour = "Autour de " + joueurName;
        joueurTitle.setText(auTour);
    }

    public void changeQuestion() {
        int ligne = rank + level * 50;
        //recupere la ligne dans le fichier
        String temp = "";
        if (level == 0) {
            temp = "Question Facile ";
        }
        if (level == 1) {
            temp = "Question Moyenne";
        }
        if (level == 2) {
            temp = "Question Hardu";
        }
        TextView question = findViewById(R.id.question);
        temp = temp + "\n" + ligne;
        question.setText(temp);
    }

    public void boisFunction(int gorge) {
        TextView bois = findViewById(R.id.bois);
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
