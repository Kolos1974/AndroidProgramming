package com.example.layoutinflaterwithbindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.layoutinflaterwithbindingdemo.databinding.ActivityMainBinding
import com.example.layoutinflaterwithbindingdemo.databinding.NoteRowBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingNoteRow: NoteRowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener{
            // var newNoteView = layoutInflater.inflate(R.layout.note_row, null)

            bindingNoteRow = NoteRowBinding.inflate(layoutInflater)
            bindingNoteRow.tvData.text = binding.etNote.toString()

            bindingNoteRow.btnDel.setOnClickListener{
//                A paramétert kell meghatározni!
//                binding.layoutMain.removeView()
            }
//            A paramétert kell meghatározni!
//            binding.layoutMain.addView()

        }



    }
}