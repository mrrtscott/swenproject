package com.uwi.loanhub



/*
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set
import com.anychart.enums.Orientation
import com.anychart.enums.ScaleStackMode
import com.anychart.scales.Linear

 */



import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.cartesian.series.Column
import com.anychart.data.Set
import com.anychart.enums.LegendLayout
import com.anychart.enums.Orientation
import com.uwi.loanhub.models.InstitutionAssets
import com.uwi.loanhub.models.InstitutionAssetsViewModel
import com.uwi.loanhub.models.LoanLikesViewModel
import java.util.*
import kotlin.collections.ArrayList


class InstitutionActivity : AppCompatActivity() {

    private lateinit var institutionAssets: InstitutionAssetsViewModel
    var inputArrayList: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institution)


        val previousIntent = intent
        val institution = previousIntent.getStringExtra("INSTITUTION")




        val anyChartView = findViewById<AnyChartView>(R.id.chartInstitutionActivity)
        val institutionNameTextView = findViewById<TextView>(R.id.institutionInstitutionActivity)
        val institutionImageView = findViewById<ImageView>(R.id.imageInstitutionActivity)

        institutionAssets = ViewModelProvider(this).get(InstitutionAssetsViewModel::class.java)



        inputArrayList.add(institution)
        institutionAssets.setArray(inputArrayList)




        institutionAssets.specificInstitutionInstitutionAssets.observe(this, androidx.lifecycle.Observer { institution ->
            println("The size".plus(institution.size))





            val column = AnyChart.column()

            val seriesData: MutableList<DataEntry> = ArrayList()

            if(institution.isNotEmpty()){

                institutionNameTextView.text =institution[0].name

                    for (i in institution){
                    println(i.year.toString())
                    seriesData.add(CustomDataEntry(i.year.toString(), i.revenue, i.income, i.totalAssets))
                }
            }

            val set = Set.instantiate()
            set.data(seriesData)
            val series1Data = set.mapAs("{ x: 'x', value: 'value' }")
            val series2Data = set.mapAs("{ x: 'x', value: 'value2' }")
            val series3Data = set.mapAs("{ x: 'x', value: 'value3' }")

            val series1: Column = column.column(series1Data)
            series1.name("Revenue")
                .color("#00ff00")

            val series2: Column = column.column(series2Data)
            series2.name("Net income")
                .color("#0000ff")

            val series3: Column = column.column(series3Data)
            series3.name("Total assets")
                .color("#ffff00")

            column.xAxis(0).orientation(Orientation.TOP)
                .stroke(null)
                .ticks(false)
            column.xGrid(0).enabled(true)

            column.legend(true)
            column.legend()
                .position(Orientation.RIGHT)
                .itemsLayout(LegendLayout.VERTICAL)

            anyChartView.setChart(column)
        })




    }

    private class CustomDataEntry(x:String, value:Number, value2:Number, value3:Number):
        ValueDataEntry(x, value) {
        init{
            setValue("value2", value2)
            setValue("value3", value3)
        }
    }
}