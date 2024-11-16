package com.sanchez.juan.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanchez.juan.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitClient.apiService.getTeachers().enqueue(object : Callback<Map<String, List<Teacher>>> {
            override fun onResponse(
                call: Call<Map<String, List<Teacher>>>,
                response: Response<Map<String, List<Teacher>>>
            ) {
                if (response.isSuccessful) {
                    val teachers = response.body()?.get("teachers") ?: emptyList()
                    binding.recyclerView.adapter = TeacherAdapter(
                        teachers,
                        onClick = { teacher -> callTeacher(teacher.phone) },
                        onLongClick = { teacher -> sendEmail(teacher.email) }
                    )
                }
            }

            override fun onFailure(call: Call<Map<String, List<Teacher>>>, t: Throwable) {
                Toast.makeText(this@Ejercicio01, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun callTeacher(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        startActivity(intent)
    }

    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
        }
        startActivity(intent)
    }
}
