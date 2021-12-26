package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView percentSeekBar;
    private TextView textGorjeta;
    private TextView textTotal;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor = findViewById(R.id.idTextValor);
        percentSeekBar = findViewById(R.id.idTextPercentSeekBar);
        textGorjeta = findViewById(R.id.idTextGorjeta);
        textTotal = findViewById(R.id.idTextTotal);
        seekBar = findViewById(R.id.idSeekBar);

        obeserverSeekBar();
    }

    private void obeserverSeekBar()
    {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                // percent seek bar
                percentSeekBar.setText(i + "%");

                if(emptyField())
                {
                    Toast.makeText(
                            getApplicationContext(),
                            "Nenhum valor digitado",
                            Toast.LENGTH_LONG
                    ).show();
                    return;
                }

                // pegando o valor digitado
                double valor = Double.parseDouble(editValor.getText().toString());

                // formatando 2 casas decimais
                DecimalFormat df = new DecimalFormat("#.##");

                // texto gorjeta
                double gorjeta = valor * (i / 100.0);
                textGorjeta.setText("R$ " + df.format(gorjeta));

                // somando o total
                textTotal.setText("R$ " + df.format(gorjeta + valor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private boolean emptyField()
    {
        return (editValor.getText().toString()).equals("");
    }

}