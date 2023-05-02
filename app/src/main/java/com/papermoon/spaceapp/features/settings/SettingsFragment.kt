package com.papermoon.spaceapp.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    lateinit var binding: FragmentSettingsBinding
    lateinit var container: ViewGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        setupUI()

        return binding.root
    }

    private fun setupUI() {
        binding.toolbar.root.title = getString(R.string.settings)
        binding.currentLanguage.text = getString(R.string.current_language)
        binding.switchDarkMode.isChecked = true

        binding.cardViewLanguage.setOnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(Screens.languageSelectorScreen())
        }
    }
}
