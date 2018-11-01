package fr.nothing.brewdogame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Fin extends AppCompatActivity {
    private String StringGagnant = "";
    private String StringGagnantQ = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin);

        if (savedInstanceState == null) {
            Bundle b = getIntent().getExtras();
            if (b != null) {
                StringGagnant = ( b.getString("Gagnant"));
                StringGagnantQ = ( b.getString("GagnantQ"));
            }
        } else {
            StringGagnant = ( savedInstanceState.getString("Gagnant"));
            StringGagnantQ = ( savedInstanceState.getString("GagnantQ"));
        }

        TextView messageWinner = findViewById(R.id.mesWinner);
        messageWinner.setText(StringGagnant);

        final TextView fin = findViewById(R.id.buttondetails);
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Fin.this);
                builder.setCancelable(true);
                builder.setTitle("Details points");
                builder.setMessage(StringGagnantQ);
                builder.setPositiveButton("Done",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
}
