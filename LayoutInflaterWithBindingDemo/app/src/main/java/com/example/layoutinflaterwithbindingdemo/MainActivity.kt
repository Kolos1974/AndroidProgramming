package com.example.layoutinflaterwithbindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.layoutinflaterwithbindingdemo.databinding.ActivityMainBinding
import com.example.layoutinflaterwithbindingdemo.databinding.NoteRowBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // private lateinit var bindingNoteRow: NoteRowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener{
            // var newNoteView = layoutInflater.inflate(R.layout.note_row, null)

            var bindingNoteRow = NoteRowBinding.inflate(layoutInflater)
            bindingNoteRow.tvData.text = binding.etNote.text.toString()

            bindingNoteRow.btnDel.setOnClickListener{
                binding.layoutMain.removeView(bindingNoteRow.root)
            }

            binding.layoutMain.addView(bindingNoteRow.root)
        }



    }
}