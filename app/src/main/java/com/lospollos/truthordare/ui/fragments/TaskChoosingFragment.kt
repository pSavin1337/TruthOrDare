package com.lospollos.truthordare.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lospollos.truthordare.Constants.NOT_FOUND_TASK_REQUEST_CODE
import com.lospollos.truthordare.Constants.SUCCESS_TASK_REQUEST_CODE
import com.lospollos.truthordare.Constants.TYPE_ERROR_TASK_REQUEST_CODE
import com.lospollos.truthordare.R
import com.lospollos.truthordare.viewmodels.TaskChoosingViewModel

class TaskChoosingFragment : Fragment() {

    private lateinit var choosingFileButton: Button
    private lateinit var nextFragmentButton: Button
    private lateinit var requestStateTextView: TextView

    private lateinit var taskChoosingViewModel: TaskChoosingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_choosing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskChoosingViewModel = ViewModelProvider(this)[TaskChoosingViewModel::class.java]
        choosingFileButton = view.findViewById(R.id.choose_choosing_file_button)
        nextFragmentButton = view.findViewById(R.id.choose_to_next_fragment_button)
        requestStateTextView = view.findViewById(R.id.choose_request_state_textview)
        choosingFileButton.setOnClickListener {
            val resultLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    taskChoosingViewModel.onChoosingActivityResult(result)
                }
            taskChoosingViewModel.onChoosingFileButtonClick(resultLauncher)
        }
        taskChoosingViewModel.requestLiveData.observe(viewLifecycleOwner) { request ->
            when (request) {
                SUCCESS_TASK_REQUEST_CODE -> {
                    //show in textview success, set choosing button not clickable and do button to next fragment visible
                    requestStateTextView.text =
                        getString(R.string.choose_textview_success_text)
                    choosingFileButton.isClickable = false
                    nextFragmentButton.visibility = VISIBLE
                }
                NOT_FOUND_TASK_REQUEST_CODE -> {
                    //show in textview file had not been founded try again
                    requestStateTextView.text =
                        getString(R.string.choose_textview_not_found_text)
                }
                TYPE_ERROR_TASK_REQUEST_CODE -> {
                    //show in textview file is invalid try again
                    requestStateTextView.text =
                        getString(R.string.choose_textview_invalid_type_text)
                }
            }
        }
    }

}