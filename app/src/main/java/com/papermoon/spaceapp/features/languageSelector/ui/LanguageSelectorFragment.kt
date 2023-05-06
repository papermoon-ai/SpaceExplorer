package com.papermoon.spaceapp.features.languageSelector.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentLanguageSelectorBinding
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.commons.locale.changeLocale

class LanguageSelectorFragment : Fragment() {
    private lateinit var binding: FragmentLanguageSelectorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLanguageSelectorBinding.inflate(inflater, container, false)
        setupToolbar()

        checkCurrentLanguageRadioButton()

        binding.radioGroupLanguages.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.btnEnglishOption.id -> {
                    changeLocale(getString(R.string.english_code))
                }
                binding.btnRussianOption.id -> {
                    changeLocale(getString(R.string.russian_code))
                }
            }
        }

        return binding.root
    }

    private fun checkCurrentLanguageRadioButton() {
        when (getString(R.string.current_language)) {
            getString(R.string.english) -> {
                binding.btnEnglishOption.isChecked = true
            }
            getString(R.string.russian) -> {
                binding.btnRussianOption.isChecked = true
            }
        }
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbar.root
        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar.title = getString(R.string.settings)
        toolbar.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        binding.toolbar.root.title = getString(R.string.language)
        super.onConfigurationChanged(newConfig)
    }
}
