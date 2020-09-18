package com.example.valute;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Convert extends AppCompatActivity {

    EditText sumRub;
    TextView name;
    TextView result;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_convert);
        sumRub = findViewById(R.id.editText);
        name = findViewById(R.id.textView2);
        result = findViewById(R.id.textView3);
        ok = findViewById(R.id.button);
        Bundle arguments = getIntent().getExtras();
        String nameS = arguments.get("name").toString();
        Double valueS = Double.parseDouble(arguments.get("value").toString());
        name.setText("В "+nameS+" это:");
        sumRub.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    DecimalFormat format = new DecimalFormat("##.00");
                    double rub = Integer.parseInt(sumRub.getText().toString());
                    double r = rub / valueS;
                    result.setText(format.format(r)+"");
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Неправельный ввод числа",Toast.LENGTH_LONG).show();
                }
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}