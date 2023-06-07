package com.example.agrotips.ui.fertilization

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.agrotips.ListFertilizationActivity
import com.example.agrotips.R
import com.example.agrotips.databinding.FragmentFertilizationBinding
import com.example.agrotips.models.Agenda

class FertilizationFragment : Fragment() {

    private lateinit var binding: FragmentFertilizationBinding
    private lateinit var agenda: Agenda


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_fertilization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        agenda = Agenda(mutableListOf())
        binding = FragmentFertilizationBinding.bind(view)

        binding.beans.setOnCheckedChangeListener { _,isChecked ->
            updateScheduledCrops(isChecked, getString(R.string.beans_scheduled))
        }
        binding.rice.setOnCheckedChangeListener { _,isChecked ->
            updateScheduledCrops(isChecked, getString(R.string.rice_scheduled))
        }
        binding.wheat.setOnCheckedChangeListener { _,isChecked ->
            updateScheduledCrops(isChecked, getString(R.string.wheat_scheduled))
        }

        binding.button2.setOnClickListener {
            val intent = Intent(requireContext(), ListFertilizationActivity::class.java)
            intent.putExtra("AGENDA", agenda)
            startActivity(intent)
        }
    }

    fun updateScheduledCrops(isChecked: Boolean, crop: String){
        if (isChecked && !agenda.scheduled.contains(crop)) {
            agenda.scheduled.add(crop)
        } else if (!isChecked){
            agenda.scheduled.remove(crop)
        }
    }

    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                val agendaExtra = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    data.getParcelableExtra("AGENDA", Agenda::class.java)
                } else {
                    data.getParcelableExtra<Agenda>("AGENDA")
                }
                }
            }
        }
    }
