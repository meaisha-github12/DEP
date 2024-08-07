package com.example.applicationtodo.fragments.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationtodo.R
import com.example.applicationtodo.database.UserViewModel
import com.example.applicationtodo.model.User
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddFragment : Fragment() {

    private lateinit var addTitleEt: EditText
    private lateinit var addDescriptionEt: EditText
    private lateinit var addDueDateEt: EditText
    private lateinit var addCategoryEt: EditText
    private lateinit var addBtn: Button
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        initializeUI(view)
        setupListeners()

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return view
    }

    private fun initializeUI(view: View) {
        addTitleEt = view.findViewById(R.id.addTitle_et)
        addDescriptionEt = view.findViewById(R.id.addDescription_et)
        addDueDateEt = view.findViewById(R.id.addDueDate_et)
        addCategoryEt = view.findViewById(R.id.addCategory_et)
        addBtn = view.findViewById(R.id.add_btn)
    }

    private fun setupListeners() {
        addDueDateEt.setOnClickListener {
            showDatePickerDialog()
        }

        addBtn.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            addDueDateEt.setText(dateFormat.format(calendar.time))
        }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun insertDataToDatabase() {
        val title = addTitleEt.text.toString()
        val description = addDescriptionEt.text.toString()
        val category = addCategoryEt.text.toString()
        val dueDateString = addDueDateEt.text.toString()

        if (inputCheck(title, description, category, dueDateString)) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date: Date? = try {
                dateFormat.parse(dueDateString)
            } catch (e: ParseException) {
                null
            }
            val dueDateLong = date?.time // Convert date to timestamp

            val user = User(
                title = title,
                description = description,
                dueDate = dueDateLong, // Store as timestamp
                category = category
            )
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, description: String, category: String, dueDate: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(category) || TextUtils.isEmpty(dueDate))
    }
}
