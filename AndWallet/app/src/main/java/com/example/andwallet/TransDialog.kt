package com.example.andwallet

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.andwallet.data.Trans
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.trans_dialog.view.*
import java.util.*

class TransDialog : DialogFragment() {

    // Itt szólunk az Activitynek
    interface TransHandler{
        fun transCreated(transItem: Trans)
    }

    lateinit var transHandler: TransHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TransHandler){
            transHandler = context
        } else {
            throw RuntimeException(
                getString(R.string.activity_not_implemented))
        }
    }

    lateinit var etTransDetail: EditText
    lateinit var etTransPrice: EditText
    lateinit var rbtnTransGroup : RadioGroup
    lateinit var rbtnIncome : RadioButton

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setTitle("Trans dialog")
        val dialogView = requireActivity().layoutInflater.inflate(
            R.layout.trans_dialog, null
        )

        etTransDetail = dialogView.etDetail
        etTransPrice = dialogView.etPrice
        rbtnIncome = dialogView.rbtnIncome

        dialogBuilder.setView(dialogView)

        dialogBuilder.setPositiveButton("Ok") {
                dialog, which ->

            if ((etTransDetail.text.length>0) && (etTransPrice.text.length>0)){
                transHandler.transCreated(
                    Trans(
                        null,
                        etTransDetail.text.toString(),
                        etTransPrice.text.toString().toInt(),
                        signValue(rbtnIncome)
                    )
                )
            } else {
                //Toast.makeText(this, "A mezőket ki kell tölteni!", Toast.LENGTH_LONG).show()
            }

        }
        dialogBuilder.setNegativeButton("Cancel") {
                dialog, which ->
        }

        return dialogBuilder.create()
    }

    override fun onResume() {
        super.onResume()

        etTransDetail.requestFocus()

        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            0
        )
    }

    private fun signValue(rbtnIncome: RadioButton): Short{

        var sign : Short
        if (rbtnIncome.isChecked){
            sign = 1
        } else {
            sign = -1
        }
        return sign
    }


}