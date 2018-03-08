package com.firesoul.formulario;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;


public class Formulario extends AppCompatActivity {
    EditText fecha, editText, editText2, editText3, editText4, T1;
    RadioButton radioButton1, radioButton2;
    String Pasatiempo, sexo, pass1, pass2, name, email, city;
    CheckBox checkbox1, checkbox2, checkbox3, checkbox4;
    int mYear, mMonth, mDay;
    Spinner Ciudad;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.formulario);


        T1 = findViewById(R.id.EditText5);

        fecha = findViewById(R.id.fecha);

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);


        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        checkbox3 = findViewById(R.id.checkbox3);
        checkbox4 = findViewById(R.id.checkbox4);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ciudades, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Ciudad = findViewById(R.id.spinner);
        Ciudad.setAdapter(adapter);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR); // current year
                mMonth = c.get(Calendar.MONTH); // current month
                mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                // date picker dialog
                datePickerDialog = new DatePickerDialog(Formulario.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mYear = year;
                                mDay = dayOfMonth;
                                mMonth = monthOfYear;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }


    public void onRadioButtonClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.radioButton1:
                if (radioButton1.isChecked()) {
                    sexo = "Mujer";
                }
                break;
            case R.id.radioButton2:
                if (radioButton2.isChecked()) {
                    sexo = "Hombre";
                }
                break;
        }

    }

    public void onCheckboxClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.checkbox1:
                if (checkbox1.isChecked()) {
                    Pasatiempo = "Aikido";
                }
                break;
            case R.id.checkbox2:
                if (checkbox2.isChecked()) {
                    Pasatiempo = "Natación";
                }
                break;
            case R.id.checkbox3:
                if (checkbox3.isChecked()) {
                    Pasatiempo = "Dibujar";
                }
                break;
            case R.id.checkbox4:
                if (checkbox4.isChecked()) {
                    Pasatiempo = "Gimnasia";
                }
                break;

        }

    }


    public void onButtonClicked(View view) {
        pass1 = editText2.getText().toString();
        pass2 = editText3.getText().toString();
        if (!Objects.equals(pass1, pass2)) {
            Toast.makeText(this, "Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            editText2.getText().clear();
            editText3.getText().clear();
        } else if (editText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingresa el nombre",
                    Toast.LENGTH_SHORT).show();
        } else if (editText2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingresa una contraseña",
                    Toast.LENGTH_SHORT).show();
        } else if (Objects.equals(Pasatiempo, "null")) {
            Toast.makeText(this, "Selecciona un pasatiempo",
                    Toast.LENGTH_SHORT).show();
        } else if (editText4.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingresa un correo electronico",
                    Toast.LENGTH_SHORT).show();
        } else if (mDay == 0) {
            Toast.makeText(this, "Selecciona tu fecha de Nacimieto",
                    Toast.LENGTH_SHORT).show();
        } else {
            name = editText.getText().toString();
            city = Ciudad.getSelectedItem().toString();
            email = editText4.getText().toString();

            T1.setText("Nombre: " + name + "\n" + "correo: " + email + "\n" + "Sexo: " + sexo + "\n"
                    + "Fecha de Nacimiento: " + mDay + "/" + mMonth + "/" + mYear + "\n"
                    + "Lugar de Nacimiento: " + city + "\n" + "Pasatiempos: " + Pasatiempo + "\n");
        }
    }
}
