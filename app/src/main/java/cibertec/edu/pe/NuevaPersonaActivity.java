package cibertec.edu.pe;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import cibertec.edu.pe.database.PersonaDAO;
import cibertec.edu.pe.model.Persona;
import android.app.AlertDialog;

public class NuevaPersonaActivity extends AppCompatActivity {
    private TextInputEditText etNombreCompleto, etDocumento, etTelefono, etDireccion;
    private RadioGroup rgEstadoCivil;
    private RadioButton rbSoltero, rbCasado, rbDivorciado, rbViudo;
    private Spinner spinnerDistrito;
    private CheckBox cbMasculino, cbFemenino;
    private Button btnGrabar;
    private FrameLayout overlayProgress;
    private TextView progressText;
    private ProgressBar loadingSpinner;
    private ImageView checkImage;
    private PersonaDAO personaDAO;
    private int personId = -1;
    private String[] distritos = {"Seleccionar distrito", "San Isidro", "Miraflores", "Surco", "La Molina", "Barranco", "San Borja"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarWhite();
        setContentView(R.layout.activity_nueva_persona);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initializeViews();
        setupSpinner();
        setupDatabase();
        setupListeners();
        loadPersonData();
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
        etNombreCompleto = findViewById(R.id.etNombreCompleto);
        etDocumento = findViewById(R.id.etDocumento);
        etTelefono = findViewById(R.id.etTelefono);
        etDireccion = findViewById(R.id.etDireccion);
        rgEstadoCivil = findViewById(R.id.rgEstadoCivil);
        rbSoltero = findViewById(R.id.rbSoltero);
        rbCasado = findViewById(R.id.rbCasado);
        rbDivorciado = findViewById(R.id.rbDivorciado);
        rbViudo = findViewById(R.id.rbViudo);
        spinnerDistrito = findViewById(R.id.spinnerDistrito);
        cbMasculino = findViewById(R.id.cbMasculino);
        cbFemenino = findViewById(R.id.cbFemenino);
        btnGrabar = findViewById(R.id.btnGrabar);
        overlayProgress = findViewById(R.id.overlayProgress);
        progressText = findViewById(R.id.progressText);
        loadingSpinner = findViewById(R.id.loadingSpinner);
        checkImage = findViewById(R.id.checkImage);
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, distritos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrito.setAdapter(adapter);
    }

    private void setupDatabase() {
        personaDAO = new PersonaDAO(this);
        personaDAO.open();
    }

    private void setupListeners() {
        btnGrabar.setOnClickListener(v -> validarYGrabar());
        cbMasculino.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbFemenino.setChecked(false);
            }
        });
        cbFemenino.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbMasculino.setChecked(false);
            }
        });
    }

    private void loadPersonData() {
        if (getIntent().hasExtra("personId")) {
            personId = getIntent().getIntExtra("personId", -1);
            etNombreCompleto.setText(getIntent().getStringExtra("nombreCompleto"));
            etDocumento.setText(getIntent().getStringExtra("documentoIdentidad"));
            etTelefono.setText(getIntent().getStringExtra("telefono"));
            etDireccion.setText(getIntent().getStringExtra("direccion"));
            String distrito = getIntent().getStringExtra("distrito");
            if (distrito != null && !distrito.isEmpty()) {
                for (int i = 0; i < distritos.length; i++) {
                    if (distritos[i].equals(distrito)) {
                        spinnerDistrito.setSelection(i);
                        break;
                    }
                }
            }
            String estadoCivil = getIntent().getStringExtra("estadoCivil");
            if (estadoCivil != null) {
                switch (estadoCivil) {
                    case "Soltero(a)": rbSoltero.setChecked(true); break;
                    case "Casado(a)": rbCasado.setChecked(true); break;
                    case "Divorciado(a)": rbDivorciado.setChecked(true); break;
                    case "Viudo(a)": rbViudo.setChecked(true); break;
                }
            }
            String genero = getIntent().getStringExtra("genero");
            if (genero != null) {
                if (genero.equals("Masculino")) cbMasculino.setChecked(true);
                else if (genero.equals("Femenino")) cbFemenino.setChecked(true);
            }
        }
    }

    private void validarYGrabar() {
        String nombre = etNombreCompleto.getText().toString().trim();
        String documento = etDocumento.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        StringBuilder camposFaltantes = new StringBuilder();
        if (nombre.isEmpty()) camposFaltantes.append("- Nombre Completo\n");
        if (documento.isEmpty()) camposFaltantes.append("- Documento de Identidad\n");
        if (telefono.isEmpty()) camposFaltantes.append("- Tel\u00e9fono\n");
        if (direccion.isEmpty()) camposFaltantes.append("- Direcci\u00f3n\n");
        if (camposFaltantes.length() > 0) {
            Toast.makeText(this, "Faltan campos:\n" + camposFaltantes.toString(), Toast.LENGTH_LONG).show();
            return;
        }
        if (personId == -1 && personaDAO.existeDocumento(documento)) {
            mostrarAlertDialog("Error", "El documento de identidad ya existe.");
            return;
        }
        mostrarConfirmacion(nombre, documento, telefono, direccion);
    }

    private void mostrarConfirmacion(String nombre, String documento, String telefono, String direccion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar");
        builder.setMessage("Desea continuar con el registro?");
        builder.setPositiveButton("Si", (dialog, which) -> grabarPersona(nombre, documento, telefono, direccion));
        builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void grabarPersona(String nombre, String documento, String telefono, String direccion) {
        String estadoCivil = obtenerEstadoCivil();
        String distrito = obtenerDistrito();
        String genero = obtenerGenero();
        Persona persona = new Persona(nombre, documento, telefono, direccion, distrito, estadoCivil, genero);
        persona.setId(personId);
        long resultado;
        if (personId == -1) resultado = personaDAO.insertPersona(persona);
        else resultado = personaDAO.updatePersona(persona);
        if (resultado != -1) {
            overlayProgress.setVisibility(View.VISIBLE);
            progressText.setText("Registrando...");
            loadingSpinner.setVisibility(View.VISIBLE);
            checkImage.setVisibility(View.GONE);
            new Handler().postDelayed(() -> {
                loadingSpinner.setVisibility(View.GONE);
                checkImage.setVisibility(View.VISIBLE);
                progressText.setText("Registrado correctamente");
                new Handler().postDelayed(() -> {
                    overlayProgress.setVisibility(View.GONE);
                    finish();
                }, 2000);
            }, 3000);
        } else {
            mostrarAlertDialog("Error", "No se pudo completar la operación.");
        }
    }

    private String obtenerEstadoCivil() {
        int selectedId = rgEstadoCivil.getCheckedRadioButtonId();
        if (selectedId == R.id.rbSoltero) return "Soltero(a)";
        if (selectedId == R.id.rbCasado) return "Casado(a)";
        if (selectedId == R.id.rbDivorciado) return "Divorciado(a)";
        if (selectedId == R.id.rbViudo) return "Viudo(a)";
        return "";
    }

    private String obtenerDistrito() {
        int position = spinnerDistrito.getSelectedItemPosition();
        if (position > 0) return distritos[position];
        return "";
    }

    private String obtenerGenero() {
        if (cbMasculino.isChecked()) return "Masculino";
        if (cbFemenino.isChecked()) return "Femenino";
        return "";
    }

    private void mostrarAlertDialog(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (personaDAO != null) personaDAO.close();
    }
}
