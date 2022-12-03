package com.example.ejemplo1_bd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText control, nombre, semestre, carrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control= findViewById(R.id.control);
        nombre = findViewById(R.id.nombre);
        semestre = findViewById(R.id.semestre);
        carrera=findViewById(R.id.carrera);
    }
    public void altas(View view) {
        //construcción ela base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //ALTAS
        String nc = control.getText().toString();
        String nom = nombre.getText().toString();
        String sem = semestre.getText().toString();
        String car = carrera.getText().toString();
//Hacer pila
        ContentValues pila = new ContentValues();
        pila.put("numcontrol", nc);
        pila.put("nombre", nom);
        pila.put("semestre", sem);
        pila.put("carrera", car);

        //insertar datos a la base de datos

        db.insert("alumno", null, pila);
        //se porque se sustituyen los valores
        db.close();

        Toast.makeText(this, "Datos registros con exito", Toast.LENGTH_SHORT).show();
    }
        public void buscar(View view){
            AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            String nc=control.getText().toString();
            Cursor fila = db.rawQuery("select nombre, semestre, carrera from alumno where numcontrol="+nc, null);
            if(fila.moveToFirst()){
                nombre.setText(fila.getString(0));
                semestre.setText(fila.getString(1));
                carrera.setText(fila.getString(2));
            }else{

                Toast.makeText(this, "Datos no encontrados", Toast.LENGTH_SHORT).show();
                db.close();
            }


        }
        public void bajas(View view){
            AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            String nc=control.getText().toString();
            int cant = db.delete("alumno", "numcontrol="+nc, null);
            if(cant ==1){
                Toast.makeText(this, "Alumno eliminado con exito", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Alumno no encontrado", Toast.LENGTH_SHORT).show();

            }
            db.close();



        }
        public void modificar(View view){
            AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String nc = control.getText().toString();
            String nom = nombre.getText().toString();
            String sem = semestre.getText().toString();
            String car = carrera.getText().toString();

            ContentValues pila = new ContentValues();

            pila.put("numcontrol", nc);
            pila.put("nombre", nom);
            pila.put("semestre", sem);
            pila.put("carrera", car);

            int cant=bd.update("alumno",pila, "numcontrol="+nc, null);
            bd.close();
            if(cant == 1){
                Toast.makeText(this, "Alumno eliminado con exito", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Alumno no encontrado", Toast.LENGTH_SHORT).show();

            }

        }
        public void limpiar(View View){
            control.setText("");
            nombre.setText("");
            carrera.setText("");
            semestre.setText("");


    }





}