package cibertec.edu.pe.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import cibertec.edu.pe.R
import cibertec.edu.pe.MainActivity
import cibertec.edu.pe.EditarPersonaActivity
import cibertec.edu.pe.database.PersonaDAO
import cibertec.edu.pe.model.Persona

class PersonaAdapter(private val persons: List<Persona>, private val activity: MainActivity) :
    RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvDocumento: TextView = itemView.findViewById(R.id.tvDocumento)
        val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
        val tvDireccion: TextView = itemView.findViewById(R.id.tvDireccion)
        val tvDistrito: TextView = itemView.findViewById(R.id.tvDistrito)
        val btnView: ImageButton = itemView.findViewById(R.id.btnView)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_persona, parent, false)
        return PersonaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val person = persons[position]
        holder.tvNombre.text = person.nombreCompleto
        holder.tvDocumento.text = "${person.documentoIdentidad}"
        holder.tvTelefono.text = "Tel: ${person.telefono}"
        holder.tvDireccion.text = "Dir: ${person.direccion}"
        holder.tvDistrito.text = if (person.distrito.isNotEmpty()) "Distrito: ${person.distrito}" else ""
        holder.tvDistrito.visibility = if (person.distrito.isNotEmpty()) View.VISIBLE else View.GONE


        holder.btnView.setOnClickListener {
            AlertDialog.Builder(activity)
                .setTitle("Detalles de la Persona")
                .setMessage(
                    "Nombre: ${person.nombreCompleto}\n" +
                            "Documento: ${person.documentoIdentidad}\n" +
                            "Teléfono: ${person.telefono}\n" +
                            "Dirección: ${person.direccion}\n" +
                            "Estado Civil: ${person.estadoCivil}\n" +
                            "Distrito: ${person.distrito}\n" +
                            "Género: ${person.genero}"
                )
                .setPositiveButton("OK", null)
                .show()
        }


        holder.btnEdit.setOnClickListener {
            val intent = Intent(activity, EditarPersonaActivity::class.java).apply {
                putExtra("personId", person.id)
                putExtra("nombreCompleto", person.nombreCompleto)
                putExtra("documentoIdentidad", person.documentoIdentidad)
                putExtra("telefono", person.telefono)
                putExtra("direccion", person.direccion)
                putExtra("distrito", person.distrito)
                putExtra("estadoCivil", person.estadoCivil)
                putExtra("genero", person.genero)
            }
            activity.startActivity(intent)
        }


        holder.btnDelete.setOnClickListener {
            AlertDialog.Builder(activity)
                .setTitle("Confirmar Eliminación")
                .setMessage("¿Seguro que desea eliminar a ${person.nombreCompleto}?")
                .setPositiveButton("Sí") { _, _ ->
                    val personaDAO = PersonaDAO(activity)
                    personaDAO.open()
                    personaDAO.deletePersona(person)
                    personaDAO.close()
                    activity.cargarPersonas()
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    override fun getItemCount() = persons.size
}
