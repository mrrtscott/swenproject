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



import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.cartesian.series.Column
import com.anychart.data.Set
import com.anychart.enums.LegendLayout
import com.anychart.enums.Orientation
import com.uwi.loanhub.AppConstants.CITY
import com.uwi.loanhub.AppConstants.HTTP_PREFIX
import com.uwi.loanhub.AppConstants.INSTITUTION
import com.uwi.loanhub.AppConstants.MAIL_PREFIX
import com.uwi.loanhub.AppConstants.NET_ASSETS
import com.uwi.loanhub.AppConstants.NET_INCOME
import com.uwi.loanhub.AppConstants.PARISH
import com.uwi.loanhub.AppConstants.REVENUE
import com.uwi.loanhub.AppConstants.TEL_PREFIX
import com.uwi.loanhub.models.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * This is used in the application to manage information on bank institutions
 */
class InstitutionActivity : AppCompatActivity() {

    private lateinit var institutionAssets: InstitutionAssetsViewModel
    private lateinit var branchesModel: BranchViewModel
    private lateinit var websiteButton:Button
    private lateinit var phoneButton:Button
    private lateinit var emailButton:Button


    var inputArrayList: ArrayList<String> = arrayListOf()
    var inputBranchArrayList: ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institution)


        val previousIntent = intent
        val institution = previousIntent.getStringExtra(INSTITUTION)
        val city = previousIntent.getStringExtra(CITY)
        val parish = previousIntent.getStringExtra(PARISH)


        branchesModel = ViewModelProvider(this).get(BranchViewModel::class.java)

        val recycleView= findViewById<RecyclerView>(R.id.recycleViewBranch)
        val adapter = BranchListAdapter(this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)


        inputBranchArrayList.add(institution)
        inputBranchArrayList.add(city)
        inputBranchArrayList.add(parish)





        websiteButton = findViewById(R.id.websiteInstitutionActivity)
        phoneButton = findViewById(R.id.phoneInstitutionActivity)
        emailButton =  findViewById(R.id.emailInstitutionActivity)





        val anyChartView = findViewById<AnyChartView>(R.id.chartInstitutionActivity)
        val institutionNameTextView = findViewById<TextView>(R.id.institutionInstitutionActivity)
        val institutionImageView = findViewById<ImageView>(R.id.imageInstitutionActivity)
        val institutionSloganTextView = findViewById<TextView>(R.id.sloganInstitutionActivity)
        val institutionAboutTextView = findViewById<TextView>(R.id.aboutInstitutionActivity)

        institutionAssets = ViewModelProvider(this).get(InstitutionAssetsViewModel::class.java)
        inputArrayList.clear()
        inputArrayList.add(institution)
        institutionAssets.setArray(inputArrayList)




        institutionAssets.specificInstitutionInstitutionAssets.observe(this, androidx.lifecycle.Observer { institution ->




            val column = AnyChart.column()

            val seriesData: MutableList<DataEntry> = ArrayList()

            if(institution.isNotEmpty()){

                institutionNameTextView.text =institution[0].name
                institutionImageView.setImageResource(institution[0].logo)
                institutionSloganTextView.text = institution[0].slogan
                institutionAboutTextView.text = institution[0].about


                    for (i in institution){

                    seriesData.add(CustomDataEntry(i.year.toString(), i.revenue, i.income, i.totalAssets))
                }


                websiteButton.setOnClickListener {

                    if(institution[0].website.startsWith(HTTP_PREFIX)){
                        intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(institution[0].website)
                        startActivity(intent)
                    }

                }

                phoneButton.setOnClickListener {
                    if (institution[0].phone.isNotEmpty()){
                        intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.fromParts(TEL_PREFIX, institution[0].phone , null)
                        startActivity(intent)

                    }
                }

                emailButton.setOnClickListener {
                    if(institution[0].email.isNotEmpty()){
                        intent = Intent(Intent.ACTION_SENDTO)
                        intent.data = Uri.fromParts(MAIL_PREFIX, institution[0].email, null)
                        intent.putExtra(Intent.EXTRA_SUBJECT, institution[0].name)
                        startActivity(Intent.createChooser(intent,"Enquire about loan"))


                    }
                }






            }

            val set = Set.instantiate()
            set.data(seriesData)
            val series1Data = set.mapAs("{ x: 'x', value: 'value' }")
            val series2Data = set.mapAs("{ x: 'x', value: 'value2' }")
            val series3Data = set.mapAs("{ x: 'x', value: 'value3' }")

            val series1: Column = column.column(series1Data)
            series1.name(REVENUE)
                .color("#00ff00")

            val series2: Column = column.column(series2Data)
            series2.name(NET_INCOME)
                .color("#0000ff")

            val series3: Column = column.column(series3Data)
            series3.name(NET_ASSETS)
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


        branchesModel.setArray(inputBranchArrayList)

        branchesModel.bankBranches.observe(this, androidx.lifecycle.Observer { branch ->

            branch?.let { adapter.setBranch(it) }

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