package cibertec.edu.pe;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cibertec.edu.pe.adapter.PersonaAdapter;
import cibertec.edu.pe.database.PersonaDAO;
import cibertec.edu.pe.model.Persona;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPersonas;
    private PersonaAdapter personaAdapter;
    private PersonaDAO personaDAO;
    private EditText etBuscar;
    private Button btnNuevo;
    private TextView tvContador;
    private List<Persona> listaPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarWhite();
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        initializeViews();
        setupRecyclerView();
        setupDatabase();
        setupListeners();
        cargarPersonas();
    }

    private void setStatusBarWhite() {
        Window window = getWindow();
        window.setStatusBarColor(Color.WHITE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            View decorView = window.getDecorView();
            if (decorView != null && window.getInsetsController() != null) {
                window.getInsetsController().setSystemBarsAppearance(
                        android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                        android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                );
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            if (decorView != null) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    private void initializeViews() {
        recyclerViewPersonas = findViewById(R.id.recyclerViewPersonas);
        etBuscar = findViewById(R.id.etBuscar);
        btnNuevo = findViewById(R.id.btnNuevo);
        tvContador = findViewById(R.id.tvContador);
    }

    private void setupRecyclerView() {
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupDatabase() {
        personaDAO = new PersonaDAO(this);
        personaDAO.open();
    }

    private void setupListeners() {
        btnNuevo.setOnClickListener(v -> {
            startActivity(new android.content.Intent(this, NuevaPersonaActivity.class));
        });

        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                String busqueda = s.toString().trim();
                if (busqueda.isEmpty()) {
                    cargarPersonas();
                } else {
                    buscarPersonas(busqueda);
                }
            }
        });
    }

    public void cargarPersonas() {
        listaPersonas = personaDAO.getAllPersonas();
        personaAdapter = new PersonaAdapter(listaPersonas, this);
        recyclerViewPersonas.setAdapter(personaAdapter);

        String mensaje = listaPersonas.size() == 1 ?
                "📊 1 registro encontrado" :
                "📊 " + listaPersonas.size() + " registros encontrados";

        mostrarContador(mensaje, "#4CAF50");
    }

    private void buscarPersonas(String busqueda) {
        listaPersonas = personaDAO.buscarPersonas(busqueda);
        personaAdapter = new PersonaAdapter(listaPersonas, this);
        recyclerViewPersonas.setAdapter(personaAdapter);

        String mensaje;
        String color;

        if (listaPersonas.size() == 0) {
            mensaje = "🔍 No se encontraron registros para: \"" + busqueda + "\"";
            color = "#FF9800";
        } else if (listaPersonas.size() == 1) {
            mensaje = "🔍 1 registro encontrado para: \"" + busqueda + "\"";
            color = "#2196F3";
        } else {
            mensaje = "🔍 " + listaPersonas.size() + " registros encontrados para: \"" + busqueda + "\"";
            color = "#2196F3";
        }

        mostrarContador(mensaje, color);
    }

    private void mostrarContador(String mensaje, String colorHex) {
        tvContador.setText(mensaje);
        tvContador.setBackgroundColor(Color.parseColor(colorHex));
        tvContador.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (personaDAO != null) {
            cargarPersonas();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (personaDAO != null) {
            personaDAO.close();
        }
    }
}
