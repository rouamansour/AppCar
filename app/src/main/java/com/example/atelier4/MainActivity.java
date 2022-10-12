package com.example.atelier4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edM;
    private Spinner spT;
    private SeekBar seekP;
    private RadioGroup rdgC;
    private RadioButton rdE;
    private Button btnA;
    private Button btne;
    private Button btnV;
    private ListView lstA;
    private ArrayAdapter<Automobile> adpA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        edM = findViewById(R.id.edM);
        spT = findViewById(R.id.spT);
        seekP = findViewById(R.id.seekP);
        rdgC = findViewById(R.id.rdgC);
        rdE = findViewById(R.id.rdE);
        btnA = findViewById(R.id.btnA);
        btne = findViewById(R.id.btne);
        btnV = findViewById(R.id.btnV);
        lstA = findViewById(R.id.lstA);
        //adpA=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        adpA = new ArrayAdapter<Automobile>(this, android.R.layout.simple_list_item_1);
        lstA.setAdapter(adpA); //bch torbtha m3a list view
        ajouterEcouter();
        effacer();
    }

    private void ajouterEcouter() {
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajouter();
            }
        });

        btne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                effacer();
            }
        });

        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vider();
            }
        });
        lstA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                afficher(position);
            }
        });
    }

    private void effacer () {
        edM.setText("");
        spT.setSelection(0);
        seekP.setProgress(4);
        rdE.setChecked(true);
        edM.requestFocus();
    }
    private void ajouter () {
        String m= edM.getText().toString();
        if(!m.isEmpty()){
            int t=spT.getSelectedItemPosition();
            int p=seekP.getProgress();
            String c="";
            switch (rdgC.getCheckedRadioButtonId()) {
                case R.id.rdE:
                    c = "Essence";
                    break;
                case R.id.rdD:
                    c = "Diesel";
                    break;
                case R.id.rdG:
                    c = "GPL";
                    break;
            }
            Automobile a = new Automobile(m, t, p, c);
            adpA.add(a);
            effacer();
        } else {
            Toast.makeText(this, "Tapez une matricule SVP!", Toast.LENGTH_LONG).show();
        }
    }


    private void vider() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Vider?");
        b.setMessage("etes vous sur de vider la liste????");
        b.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                adpA.clear();
            }
        });
        b.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = b.create();
        alertDialog.show();
    }


    private void afficher(int position) {
        Automobile a=adpA.getItem(position);
        String m="";
        m="Matricule: "+a.getMatricule()+"\n"+"Prix vignette: "+a.getPrix();
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }


}