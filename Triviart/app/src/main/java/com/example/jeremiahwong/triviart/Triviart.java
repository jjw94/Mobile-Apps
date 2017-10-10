package com.example.jeremiahwong.triviart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class Triviart extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triviart);

        Button articleButton = (Button)findViewById(R.id.articleButton);
        articleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loadFragment("Article");
            }
        });
        Button quizButton = (Button)findViewById(R.id.quizButton);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment("Quiz");
            }
        });
        loadFragment("Article");
    }

    private void loadFragment(String which) {
        if (which.equals("Article")) {
            ArticleList artList = new ArticleList();
            artList.setItemChangedListener(itemChangedListener);
            getFragmentManager().popBackStack(null, getFragmentManager().POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.article_list, artList);
            ft.commit();
        }
        else if (which.equals("Quiz")) {
            TriviaQuiz quizzes = new TriviaQuiz();
            getFragmentManager().popBackStack(null, getFragmentManager().POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.article_list, quizzes);
            ft.commit();
        }
    }

    private ArticleList.ItemChangedListener itemChangedListener = new ArticleList.ItemChangedListener() {
        @Override
        public void onSelectedItemChanged(String itemNameString) {
            ReadArticle details = ReadArticle.newInstance(itemNameString);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.article_list, details);
            ft.addToBackStack(null);
            ft.commit();
        }
    };

    public void submitQuiz(View view){
        Button submitBtn = (Button) findViewById(R.id.submitButton);
        RadioButton polar = (RadioButton) findViewById(R.id.radioButton);
        RadioButton chemistry = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton ionic = (RadioButton) findViewById(R.id.radioButton3);
        boolean isChem = chemistry.isChecked();
        RadioButton cryptr = (RadioButton) findViewById(R.id.radioButton4);
        RadioButton crypt = (RadioButton) findViewById(R.id.radioButton5);
        boolean isCrypt = crypt.isChecked();
        RadioButton data = (RadioButton) findViewById(R.id.radioButton6);
        RadioButton data2 = (RadioButton) findViewById(R.id.radioButton7);
        boolean isData = data.isChecked();
        RadioButton loop = (RadioButton) findViewById(R.id.radioButton8);
        RadioButton loop2 = (RadioButton) findViewById(R.id.radioButton9);
        RadioButton ist = (RadioButton) findViewById(R.id.radioButton10);
        boolean isIst = data.isChecked();
        RadioButton radioactive = (RadioButton) findViewById(R.id.radioButton11);
        RadioButton physics = (RadioButton) findViewById(R.id.radioButton12);
        boolean isPhys = physics.isChecked();

        if(!(polar.isChecked() || chemistry.isChecked() || ionic.isChecked() || cryptr.isChecked()
        || crypt.isChecked() || data.isChecked() || data2.isChecked() || loop.isChecked()
        || loop2.isChecked() || ist.isChecked() || radioactive.isChecked() || physics.isChecked())) {
            Toast.makeText(Triviart.this, "Please complete the quiz!", LENGTH_SHORT).show();
        }
        else {
            AlertDialog alertDialog = new AlertDialog.Builder(Triviart.this).create();
            alertDialog.setTitle("NICE!");
            alertDialog.setMessage("You have successfully submitted the quiz!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

        int score = calculateScore(isChem, isCrypt, isData, isIst, isPhys);
        String message = "Your Score: " + score + "/10";
        TextView textInfo = (TextView) findViewById(R.id.textSum);
        textInfo.setText(message);
    }

    public void radioClick(View view){
        RadioButton polar = (RadioButton) findViewById(R.id.radioButton);
        RadioButton chemistry = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton ionic = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton cryptr = (RadioButton) findViewById(R.id.radioButton4);
        RadioButton crypt = (RadioButton) findViewById(R.id.radioButton5);
        RadioButton data = (RadioButton) findViewById(R.id.radioButton6);
        RadioButton data2 = (RadioButton) findViewById(R.id.radioButton7);
        RadioButton loop = (RadioButton) findViewById(R.id.radioButton8);
        RadioButton loop2 = (RadioButton) findViewById(R.id.radioButton9);
        RadioButton ist = (RadioButton) findViewById(R.id.radioButton10);
        RadioButton radioactive = (RadioButton) findViewById(R.id.radioButton11);
        RadioButton physics = (RadioButton) findViewById(R.id.radioButton12);

        if(chemistry.isChecked()) {
            Toast.makeText(Triviart.this, "Correct!", LENGTH_SHORT).show();
        }
        if(crypt.isChecked()) {
            Toast.makeText(Triviart.this, "Correct!", LENGTH_SHORT).show();
        }
        if(data.isChecked()) {
            Toast.makeText(Triviart.this, "Correct!", LENGTH_SHORT).show();
        }
        if(ist.isChecked()) {
            Toast.makeText(Triviart.this, "Correct!", LENGTH_SHORT).show();
        }
        if(physics.isChecked()) {
            Toast.makeText(Triviart.this, "Correct!", LENGTH_SHORT).show();
        }
        if(polar.isChecked()){
            Toast.makeText(Triviart.this, "Incorrect Answer...", LENGTH_SHORT).show();
        }
        if(ionic.isChecked()){
            Toast.makeText(Triviart.this, "Incorrect Answer...", LENGTH_SHORT).show();
        }
        if(cryptr.isChecked()){
            Toast.makeText(Triviart.this, "Incorrect Answer...", LENGTH_SHORT).show();
        }
        if(data2.isChecked()){
            Toast.makeText(Triviart.this, "Incorrect Answer...", LENGTH_SHORT).show();
        }
        if(loop.isChecked()){
            Toast.makeText(Triviart.this, "Incorrect Answer...", LENGTH_SHORT).show();
        }
        if(loop2.isChecked()){
            Toast.makeText(Triviart.this, "Incorrect Answer...", LENGTH_SHORT).show();
        }
        if(radioactive.isChecked()){
            Toast.makeText(Triviart.this, "Incorrect Answer...", LENGTH_SHORT).show();
        }
    }

    private int calculateScore(boolean addChem, boolean addCrypt, boolean addData, boolean addIst, boolean addPhys) {
        int baseScore = 0;

        if (addChem) {
            baseScore = baseScore + 2;
        }

        if (addCrypt) {
            baseScore = baseScore + 2;
        }

        if (addData) {
            baseScore = baseScore + 2;
        }

        if (addIst) {
            baseScore = baseScore + 2;
        }

        if (addPhys) {
            baseScore = baseScore + 2;
        }
        return baseScore;
    }
}
