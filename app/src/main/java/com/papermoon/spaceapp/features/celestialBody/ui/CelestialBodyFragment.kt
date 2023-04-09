package com.papermoon.spaceapp.features.celestialBody.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentCelestialBodyBinding
import com.papermoon.spaceapp.domain.model.CelestialBody
import com.papermoon.spaceapp.domain.model.Period
import com.papermoon.spaceapp.features.MainActivity
import com.squareup.picasso.Picasso
import java.text.DecimalFormat


class CelestialBodyFragment(
    private val celestialBody: CelestialBody
) : Fragment() {

    private var _binding: FragmentCelestialBodyBinding? = null
    private val binding: FragmentCelestialBodyBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCelestialBodyBinding.inflate(inflater, container, false)

        val formatter = DecimalFormat("#.####")

        binding.tvCelestialBodyName.text = celestialBody.englishName

        if (celestialBody.discoverDate == null) {
            binding.cardViewCelestialBodyDiscovery.visibility = View.GONE
        } else {
            binding.tvCelestialBodyDiscoveryDate.text = celestialBody.discoverDate
            binding.tvCelestialBodyDiscoverer.text = celestialBody.discoveredBy
        }

        binding.tvCelestialBodySatellites.text = celestialBody.satelliteCount.toString()
        binding.tvCelestialBodyArea.text =
            getString(
                R.string.description_area,
                formatter.format(celestialBody.characteristics.area)
            )
        binding.tvCelestialBodyTemperature.text = getString(
            R.string.description_temperatures,
            getTemperatureString(celestialBody.characteristics.minTemperature),
            getTemperatureString(celestialBody.characteristics.maxTemperature)
        )
        binding.tvCelestialBodyOrbitalSpeed.text =
            getString(
                R.string.description_orbital_speed,
                formatter.format(celestialBody.characteristics.avgOrbitalSpeed)
            )
        binding.tvCelestialBodyRotationAxis.text =
            getPeriodString(celestialBody.characteristics.rotationAroundAxis)
        binding.tvCelestialBodyRotationSun.text =
            getPeriodString(celestialBody.characteristics.rotationAroundSun)
        binding.tvCelestialBodyGravity.text = getString(
            R.string.description_gravity,
            formatter.format(celestialBody.characteristics.gravity)
        )
        binding.tvCelestialBodyDensity.text = getString(
            R.string.description_density,
            formatter.format(celestialBody.characteristics.density)
        )
        binding.tvCelestialBodyDescription.text = celestialBody.description

        Picasso.get()
            .load(celestialBody.imageUrls.first())
            .into(binding.imgCelestialBody)

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }

        binding.imgCelestialBody.setOnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(Screens.imageViewerScreen(celestialBody.imageUrls))
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = celestialBody.englishName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getTemperatureString(temperature: Double): String {
        val formatter = DecimalFormat("#.##")
        return if (temperature > 0) {
            "+${formatter.format(temperature)}"
        } else {
            formatter.format(temperature)
        }
    }

    private fun getPeriodString(period: Period): String {
        val stringBuilder = StringBuilder()
        if (period.years != 0) {
            stringBuilder.append(
                resources.getQuantityString(
                    R.plurals.years,
                    period.years,
                    period.years
                )
            )
            stringBuilder.append(" ")
        }
        if (period.days != 0) {
            stringBuilder.append(
                resources.getQuantityString(
                    R.plurals.days,
                    period.days,
                    period.days
                )
            )
            stringBuilder.append(" ")
        }
        if (period.hours != 0) {
            stringBuilder.append(
                resources.getQuantityString(
                    R.plurals.hours,
                    period.hours,
                    period.hours
                )
            )
            stringBuilder.append(" ")
        }
        if (period.minutes != 0) {
            stringBuilder.append(
                resources.getQuantityString(
                    R.plurals.minutes,
                    period.minutes,
                    period.minutes
                )
            )
        }
        return stringBuilder.toString()
    }
}
