package com.lospollos.truthordare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.lospollos.truthordare.R
import com.lospollos.truthordare.ui.activities.MainActivity

class LauncherFragment : Fragment() {

    private lateinit var launcherButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launcher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launcherButton = view.findViewById(R.id.launcher_start_button)
        launcherButton.setOnClickListener {
            (activity as MainActivity).openDefinitionPlayersFragment()
        }
    }

}