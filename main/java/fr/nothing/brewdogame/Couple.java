package fr.nothing.brewdogame;
import java.util.ArrayList;
import java.util.List;

public class Couple {
    ArrayList<String> Joueur;
    int pointQ1, pointQ2, pointQ3;

    public Couple(String joueur1, String joueur2) {
        Joueur.add(joueur1);
        Joueur.add(joueur2);
        this.pointQ1 = 0;
        this.pointQ2 = 0;
        this.pointQ3 = 0;
    }

    public List<String> getJoueur() {
        return Joueur;
    }

    public void setJoueur(ArrayList<String> joueur) {
        Joueur = joueur;
    }

    public int getPointQ1() {
        return pointQ1;
    }

    public void setPointQ1(int pointQ1) {
        this.pointQ1 = pointQ1;
    }

    public int getPointQ2() {
        return pointQ2;
    }

    public void setPointQ2(int pointQ2) {
        this.pointQ2 = pointQ2;
    }

    public int getPointQ3() {
        return pointQ3;
    }

    public void setPointQ3(int pointQ3) {
        this.pointQ3 = pointQ3;
    }

    public void add1PointQ1(){
        this.pointQ1 += 1;
    }

    public void add1PointQ2(){
        this.pointQ2 += 1;
    }

    public void add1PointQ3(){
        this.pointQ3 += 1;
    }
}
