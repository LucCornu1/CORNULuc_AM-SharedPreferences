package com.example.cornuluc_persistance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Objets manipulés dans l'interface
    private static TextView textTitle = null;
    private static EditText inputName = null;
    private static EditText inputFirstName = null;
    private static EditText inputMail = null;
    private static EditText inputPhone = null;
    private static Button saveButton = null;

    // Système de persistence
    private SharedPreferences mySharedPreferences = null;
    private SharedPreferences.Editor myEditor = null;

    // Nom des clefs de sauvegarde
    private static final String MyPREFERENCES = "MyPrefs";
    private static final String Names = "SaveName";
    private static final String FirstNames = "SaveFirstName";
    private static final String Mails = "SaveMail";
    private static final String PhoneNumbers = "SavePhoneNumber";
    private static final String NumberVisits = "NumberVisit";


    // A la création de la vue
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation
        initForm();

        // Gestion du nombre de visites
        int i = mySharedPreferences.getInt(NumberVisits, 0) + 1;
        myEditor.putInt(NumberVisits, i);
        myEditor.commit();

        // On affiche les données sauvegardées
        // Si c'est la première connexion, on affiche un message différent
        if (mySharedPreferences.getInt(NumberVisits, 0) > 1) {
            Toast.makeText(getApplicationContext(),  "Hello "+mySharedPreferences.getString(Names, "")+" "+mySharedPreferences.getString(FirstNames, "")+", mail : "+mySharedPreferences.getString(Mails, "")+", phone "+mySharedPreferences.getString(PhoneNumbers, "")+". You have visited this app "+mySharedPreferences.getInt(NumberVisits, 0)+" times", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),  "Hello whoever you are !! You have visited this app "+mySharedPreferences.getInt(NumberVisits, 0)+" times", Toast.LENGTH_LONG).show();
        }

        // Sur le clique du bouton
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ajout des données au SharedPreferences
                myEditor.putString(Names, inputName.getText().toString());
                myEditor.putString(FirstNames, inputFirstName.getText().toString());
                myEditor.putString(Mails, inputMail.getText().toString());
                myEditor.putString(PhoneNumbers, inputPhone.getText().toString());

                myEditor.commit(); // Sauvegarde synchrone
                // Sauvegarde avec apply() asynchrone. Problèmes rencontrés lors du redémarrage.
            }
        });
    }


    // Fonction pour initialiser les objets manipulés
    @SuppressLint("CommitPrefEdits")
    protected void initForm() {
        mySharedPreferences = getPreferences(MODE_PRIVATE);
        myEditor = mySharedPreferences.edit();

        textTitle = (TextView) findViewById(R.id.textview_Title);
        inputName = (EditText) findViewById(R.id.editTextName);
        inputFirstName = (EditText) findViewById(R.id.editTextFirstName);
        inputMail = (EditText) findViewById(R.id.editTextEmailAddress);
        inputPhone = (EditText) findViewById(R.id.editTextPhone);
        saveButton = (Button) findViewById(R.id.button_Save);
    }
}