package com.papermoon.spaceapp.features.celestialBody.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
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

        with(binding) {
            tvCelestialBodyName.text = celestialBody.englishName

            if (celestialBody.discoverDate == null) {
                cardViewCelestialBodyDiscovery.visibility = View.GONE
            } else {
                tvCelestialBodyDiscoveryDate.text = celestialBody.discoverDate
                tvCelestialBodyDiscoverer.text = celestialBody.discoveredBy
            }

            tvCelestialBodySatellites.text = celestialBody.satelliteCount.toString()
            tvCelestialBodyArea.text =
                getString(
                    R.string.description_area,
                    formatter.format(celestialBody.characteristics.area)
                )
            tvCelestialBodyTemperature.text = getString(
                R.string.description_temperatures,
                getTemperatureString(celestialBody.characteristics.minTemperature),
                getTemperatureString(celestialBody.characteristics.maxTemperature)
            )
            tvCelestialBodyOrbitalSpeed.text =
                getString(
                    R.string.description_orbital_speed,
                    formatter.format(celestialBody.characteristics.avgOrbitalSpeed)
                )
            tvCelestialBodyRotationAxis.text =
                getPeriodString(celestialBody.characteristics.rotationAroundAxis)
            tvCelestialBodyRotationSun.text =
                getPeriodString(celestialBody.characteristics.rotationAroundSun)
            tvCelestialBodyGravity.text = getString(
                R.string.description_gravity,
                formatter.format(celestialBody.characteristics.gravity)
            )
            tvCelestialBodyDensity.text = getString(
                R.string.description_density,
                formatter.format(celestialBody.characteristics.density)
            )
            tvCelestialBodyDescription.text = celestialBody.description

            Picasso.get()
                .load(celestialBody.imageUrl)
                .into(imgCelestialBody)
        }

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
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
