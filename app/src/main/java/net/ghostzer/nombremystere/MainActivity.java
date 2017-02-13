package net.ghostzer.nombremystere;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText txtNombre;
    Button btnOK;
    TextView lblResult;
    ProgressBar progressBar;
    TextView lblHistory;
    Button btnQuitter;

    private int searchedValue;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOK = (Button)findViewById(R.id.btnOK);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        lblResult = (TextView)findViewById(R.id.lblResult);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        lblHistory = (TextView)findViewById(R.id.lblHistory);
        btnQuitter = (Button)findViewById(R.id.btnQuitter);

        btnOK.setOnClickListener(btnOkListener);
        btnQuitter.setOnClickListener(btnQuitterListener);

        init();
    }

    private void init() {
        score = 0;
        searchedValue = 1 + (int) (Math.random() * 100);

        txtNombre.setText("");
        progressBar.setProgress(score);
        lblResult.setText("");
        lblHistory.setText("");

    }


    private View.OnClickListener btnQuitterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener btnOkListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String strNombre = txtNombre.getText().toString();

            if (strNombre.equals("")) return;

            int enteredValue = Integer.parseInt(strNombre);
            lblHistory.append(strNombre + "\r\n");
            progressBar.incrementProgressBy(1);
            score++;

            if (enteredValue == searchedValue) {
                bravo();
            }
            else if (enteredValue < searchedValue) {
                lblResult.setText(R.string.strPlus);
                txtNombre.setText("");
                if (score == 10) {
                    perdu();
                }
            }
            else if (enteredValue > searchedValue) {
                lblResult.setText(R.string.strMoins);
                txtNombre.setText("");
                if (score == 10) {
                    perdu();
                }
            }


        }
    };

    private void bravo() {
        lblResult.setText(R.string.strWin);

        AlertDialog retryAlert = new AlertDialog.Builder(this).create();
        retryAlert.setTitle(R.string.app_name);
        retryAlert.setMessage(getString(R.string.strBravo, score));
        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.strYes), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        } );

        retryAlert.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.strNo), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        } );

        retryAlert.show();

    }

    private void perdu() {
        lblResult.setText(R.string.strTitleLost);

        AlertDialog retryAlert = new AlertDialog.Builder(this).create();
        retryAlert.setTitle(R.string.app_name);
        retryAlert.setMessage(getString(R.string.strLost));
        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.strYes), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        } );

        retryAlert.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.strNo), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        } );

        retryAlert.show();
    }

}
