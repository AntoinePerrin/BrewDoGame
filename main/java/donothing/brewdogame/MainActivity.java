package donothing.brewdogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String anciennequestion;
    int joueurid;
    int rank;
    int level;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView titleBienvenue = (TextView) findViewById(R.id.title);
        titleBienvenue.setText(stringFromJNI());

        TextView buttonMoi1 = (TextView) findViewById(R.id.carteMoi);
        buttonMoi1.setText("C'est moi");

        TextView buttonAutre1 = (TextView) findViewById(R.id.carteAutre);
        buttonAutre1.setText("C'est l'autre");

        TextView joueur = (TextView) findViewById(R.id.joueur);
        joueur.setText("Maitre Panda");

        TextView question = (TextView) findViewById(R.id.question);
        question.setText("Plutot soumis ou dominant");

        joueurid = 1;
        level = 0;
        rank = 0;
    }

    public void buttonMoi(android.view.View KGB){
        buttonManager("Moi");
    }
    public void buttonAutre(android.view.View KGB){
        buttonManager("Autre");
    }

    public void buttonManager(String buttonJoué){

        // enregistre valeur
        // change nom joueur ou change question ou change groupe
        if (joueurid == 1 ){
            anciennequestion = buttonJoué;
        }
        if (joueurid == 2){
            if (anciennequestion == buttonJoué){
                boisFunction(0);
                anciennequestion = "";
            } else {
                boisFunction(5);
                //boisManager();
            }

            if (level <2){
                level = level +1;
            } else  {
                rank = rank + 1;
                level = 0;
            }
            changeQuestion();
        }
        changeJoueur();
    }
    public void changeJoueur(){
        TextView joueurTitle = (TextView) findViewById(R.id.joueur);
        if (joueurid == 1){
            joueurid = 2;
            joueurTitle.setText("Maitre Panda2");
        } else{
            joueurid = 1;
            joueurTitle.setText("Maitre Panda1");
        }
    }
    public void changeQuestion(){
        int ligne = rank * 3 + level;
        //recupere la ligne dans le fichier
        TextView question = (TextView) findViewById(R.id.question);
        question.setText("Plutot soumis ou dominant" + ligne);
    }

    public void boisFunction(int gorge){
        TextView bois = (TextView) findViewById(R.id.bois);
        //bois.setText("Tu bois ta race connard !"/*stringToBois()*/);
        String res = "";
        if (gorge > 1 ){ res = "Tu bois " + gorge + " gorgés";}
        if (gorge == 1){ res = "Tu bois " + gorge + " gorgé";}
        if (gorge == 0){ res = "Vous avez bien répondu";}
        bois.setText(res);
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


}
