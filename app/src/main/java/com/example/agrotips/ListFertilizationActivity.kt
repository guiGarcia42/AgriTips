package com.example.agrotips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.agrotips.databinding.ActivityListFertilizationBinding
import com.example.agrotips.models.Agenda

class ListFertilizationActivity : AppCompatActivity() {

    private lateinit var databind: ActivityListFertilizationBinding
    private lateinit var agenda: Agenda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityListFertilizationBinding.inflate(layoutInflater)
        setContentView(databind.root)

        intent.getParcelableExtra<Agenda>("AGENDA")?.let { agenda = it }

        if (agenda.scheduled.isEmpty()) {
            databind.scheduledList.text = getString(R.string.default_list)
        } else {
            databind.scheduledList.text = getString(R.string.start_list)
            for (crop in agenda.scheduled) {
                databind.scheduledList.text = "${databind.scheduledList.text}-> ${crop}\n"
            }
        }
    }
}