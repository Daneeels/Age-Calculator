package com.example.agecalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDateTv: TextView? = null
    private var hoursTv: TextView? = null
    private var minutesTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateButton: Button = findViewById(R.id.date_button)
        selectedDateTv = findViewById(R.id.selected_date_tv)
        hoursTv = findViewById(R.id.hours_tv)
        minutesTv = findViewById(R.id.minutes_tv)

        val resetButton: Button = findViewById(R.id.reset_button)

        dateButton.setOnClickListener {

            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val day = myCalendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay->

                /*
                if (selectedDay < 10 && selectedMonth < 10){
                    selectedDate = "0$selectedDay/0${selectedMonth+1}/$selectedYear"
                }else if (selectedDay < 10){
                    selectedDate = "0$selectedDay/${selectedMonth+1}/$selectedYear"
                }else if (selectedMonth+1 < 10){
                    selectedDate = "$selectedDay/0${selectedMonth+1}/$selectedYear"
                }else{
                    selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
                }
                */

                val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"

                selectedDateTv?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                //Date to Hours
                val selectedDateInHours = theDate.time / 3600000
                val currentDateInHours = currentDate.time / 3600000

                val differentHours = currentDateInHours - selectedDateInHours

                //Date to Minutes
                val selectedDateInMinutes = theDate.time / 60000
                val currentDateInMinutes = currentDate.time / 60000

                val differentMinutes = currentDateInMinutes - selectedDateInMinutes

                hoursTv?.text = differentHours.toString()
                minutesTv?.text = differentMinutes.toString()


            },year, month,day

            )

            dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dpd.show()
        }

        resetButton.setOnClickListener {

            selectedDateTv?.text = "0/0/0000"
            hoursTv?.text = 0.toString()
            minutesTv?.text = 0.toString()
        }


    }


}