package fr.nothing.brewdogame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.app.*;
import android.content.DialogInterface;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private Question Q = new Question();
    private String anciennequestion;
    private int joueurid = 1, rank = 0, level = 0, groupeid = 0, nbCouple = 0, tour = 0;
    private ArrayList<Couple> groupeCouple = new ArrayList<>();
    private String joueurName = "";
    private int nbQuestion = Q.Questions.size();
    private ArrayList<Integer> ancienneQuestion = new ArrayList<>();
    private boolean resanciennequestion1 = false, resanciennequestion2 = false, hardcore = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Bundle b = getIntent().getExtras();
            if (b != null) {
                joueurManager( b.getStringArrayList("ListeCouple"));
                hardcore = b.getBoolean("hardcore");
            }
        } else {
            joueurManager( savedInstanceState.getStringArrayList("ListeCouple"));
        }

        TextView info = findViewById(R.id.infoPoint);
        info.setText(R.string.accesPoint);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                point();

            }
        });

        final TextView fin = findViewById(R.id.finPartieButton);
        fin.setText(R.string.finPartie);
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Fin ?");
                builder.setMessage("Voulez vous réelment quitter la partie");
                builder.setPositiveButton("Affirmatif",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                fin();
                            }
                        });
                builder.setNegativeButton("Négatif",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        TextView joueurTitle = findViewById(R.id.joueur);
        String joueurTitleDeSesMorts = "Autour de " + joueurName;
        joueurTitle.setText(joueurTitleDeSesMorts);

        TextView buttonMoi1 = findViewById(R.id.carteMoi);
        buttonMoi1.setText(R.string.cmoi);

        TextView buttonAutre1 = findViewById(R.id.carteAutre);
        buttonAutre1.setText(R.string.cautre);

        changeQuestion();

    }


    public void joueurManager(ArrayList<String> listeJoueur) {
        nbCouple = listeJoueur.size()/3;
        Couple temp;
        String joueur1, joueur2, nomEquipe;
        joueurName = listeJoueur.get(0);
        Iterator<String> it = listeJoueur.iterator();
        while (it.hasNext()) {
            joueur1 = it.next();
            joueur2 = it.next();
            nomEquipe = it.next();
            temp = new Couple(joueur1, joueur2, nomEquipe);
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
        if (joueurid == 1) {
            anciennequestion = buttonJoue;
        }
        if (joueurid == 2) {
            if (!(level < 2)){
                notification("Changement de Couple", "On change de couple donc vous lachez le téléphone et vous le passez au groupe suivant");
            }
            if (!anciennequestion.equals(buttonJoue)) {
                boisFunction(0);
                if (level == 0) {
                    resanciennequestion1 = true;
                    groupeCouple.set(groupeid, groupeCouple.get(groupeid)).add1PointQ1();
                }
                if (level == 1) {
                    resanciennequestion2 = true;
                    groupeCouple.set(groupeid, groupeCouple.get(groupeid)).add1PointQ2();
                }
                if (level == 2) {
                    groupeCouple.set(groupeid, groupeCouple.get(groupeid)).add1PointQ3();
                }
            } else {
                boisManager();
            }
            anciennequestion = "";
            if (level < 2) {
                level = level + 1;
            } else {
                resanciennequestion1 = false;
                resanciennequestion2 = false;
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
        if (level == 0) {
            if (hardcore){
                boisFunction(2);
            } else {
                boisFunction(1);
            }
        }
        if (level == 1) {
            if (hardcore){
                if (resanciennequestion1){
                    boisFunction(3);
                } else {
                    boisFunction(2);
                }
            } else {
                boisFunction(2);
            }
        }
        if (level == 2) {
            if (hardcore) {
                if (resanciennequestion1 & resanciennequestion2) {
                    boisFunction(10);
                } else {
                    boisFunction(6);
                }
            } else {
                boisFunction(3);
            }
        }
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
        String auTour = "Au tour de " + joueurName;
        joueurTitle.setText(auTour);
    }

    public void changeQuestion() {
        if (tour < nbQuestion) {
            int ligne = random();
            String temp = "";
            if (level == 0) {
                temp = "Question Facile :";
            }
            if (level == 1) {
                temp = "Question Moyenne :";
            }
            if (level == 2) {
                temp = "Question Hardu :";
            }
            TextView question = findViewById(R.id.question);
            temp = temp + "\n" + Q.Questions.get(ligne);
            question.setText(temp);
        } else {
            fin();
        }
        tour ++;
    }

    public void boisFunction(int gorge) {
        String res = "", title = "";
        if (gorge > 1) {
            res = "Vous buvez " + gorge + " gorgées";
            title = "Perdu";
        }
        if (gorge == 1) {
            res = "Vous buvez " + gorge + " gorgée";
            title = "Perdu";
        }
        if (gorge == 0) {
            res = "Vous avez bien répondu";
            title = "Bien joué";
        }
        notification(title,res);
    }

    public void notification(String title, String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(text);
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public int random (){
        Random rand = new Random();
        int n = rand.nextInt(nbQuestion/3) + 1;
        int res = n + level * (nbQuestion/3);
        if (ancienneQuestion.contains(res)){
            res = random();
        } else {
            ancienneQuestion.add(res);
        }
        return res;
    }

    public void point(){
        String res ="";
        Couple temp;
        Iterator<Couple> it = groupeCouple.iterator();
        while (it.hasNext()){
            temp = it.next();
            res = res + temp.getJoueur().get(0) + " et " + temp.getJoueur().get(1)+ " ont " + sum(temp) + " points.\n";
        }
        notification("Points", res);
    }

    public int sum(Couple couple){
        return couple.getPointQ1() + couple.getPointQ2() + couple.getPointQ3();
    }

    public void fin() {
        int idmax = 0, maxscore = sum(groupeCouple.get(0)),sumtemp;
        int bq1 = groupeCouple.get(0).getPointQ1(), bq2 = groupeCouple.get(0).getPointQ2(), bq3 = groupeCouple.get(0).getPointQ3();
        int idmaxQ1= 0, idmaxQ2 = 0, idmaxQ3 = 0;
        for (int i = 1 ; i < nbCouple ; i++){
            sumtemp = sum(groupeCouple.get(i));
            if (sumtemp > maxscore){
                idmax = i;
                maxscore = sumtemp;
            }
            if (groupeCouple.get(i).getPointQ1() > bq1){
                bq1 = groupeCouple.get(i).getPointQ1();
                idmaxQ1 = i;
            }
            if (groupeCouple.get(i).getPointQ2() > bq2){
                bq2 = groupeCouple.get(i).getPointQ2();
                idmaxQ2 = i;
            }
            if (groupeCouple.get(i).getPointQ3() > bq3){
                bq3 = groupeCouple.get(i).getPointQ3();
                idmaxQ3 = i;
            }
        }
        String equipeMaxPoint = "Les gagants sont " + groupeCouple.get(idmax).Joueur.get(0)+ " et " + groupeCouple.get(idmax).Joueur.get(1) + " de l'équipe " + groupeCouple.get(idmax).getNomEquipe() + ", ils ont marqués " + maxscore + " points.";
        String minires = "Pour les questions facile l'équipe " + groupeCouple.get(idmaxQ1).getNomEquipe() + " a marqués le plus de points(" + bq1 + " points).\n";
        minires = minires + "Pour les questions moyenne l'équipe " + groupeCouple.get(idmaxQ2).getNomEquipe() + " a marqués le plus de points(" + bq2 + " points).\n";
        minires = minires + "Pour les questions hardu l'équipe " + groupeCouple.get(idmaxQ3).getNomEquipe() + " a marqués le plus de points(" + bq3 + " points).\n";
        Intent fin = new Intent(getApplicationContext(), Fin.class);
        Bundle b = new Bundle();
        b.putString("Gagnant", equipeMaxPoint);
        b.putString("GagnantQ", minires);
        fin.putExtras(b);
        startActivity(fin);
        finish();
    }
}