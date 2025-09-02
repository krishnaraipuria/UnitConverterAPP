package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier = Modifier) {
     var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Meters") }
    var outputunit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var convertion = remember { mutableStateOf(1.00) }
    var oconvertion = remember { mutableStateOf(1.00) }


    fun coverunits(){
        val inputvalurdouble =input.toDoubleOrNull()?:0.0
        val result= (inputvalurdouble * convertion.value *100.0/oconvertion.value).roundToInt()/100.0
        output=result.toString()
    }
    Column (
        modifier= modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            "UnitConverter"
        )
        Spacer(modifier=Modifier.height(16.dp))
        OutlinedTextField(input, {
            input=it
        }, label ={Text("INPUT VALUE:)")})
        Spacer(modifier=Modifier.height(16.dp))
        Row {
            Box {
                Button({iExpanded=true}) {
                    Text("InputV😒")
                    Icon(Icons.Default.ArrowDropDown, "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded ,{iExpanded=false}) {
                    DropdownMenuItem({ Text("Centimeters")},{
                        iExpanded=false
                        inputunit="Centimeters"
                        convertion.value=0.01
                        coverunits()
                    })
                    DropdownMenuItem({ Text("Meters")},{
                        iExpanded=false
                        inputunit="Meters"
                        convertion.value=1.0
                        coverunits()
                    })
                    DropdownMenuItem({ Text("Feet")},{
                        iExpanded=false
                        inputunit="Feet"
                        convertion.value=0.3048
                        coverunits()
                    })
                    DropdownMenuItem({ Text("Milimeters")},{
                        iExpanded=false
                        inputunit="Milimeters"
                        convertion.value=0.001
                        coverunits()
                    })
                }
            }
            Spacer(modifier=Modifier.width(16.dp))
            Box{
                Button({oExpanded=true}) {
                    Text("OutputV😊")
                    Icon(Icons.Default.ArrowDropDown, "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded=false}) {
                    DropdownMenuItem({ Text("Centimeters")},{
                        oExpanded=false
                        outputunit="Centimeters"
                        oconvertion.value=0.01
                        coverunits()
                    })
                    DropdownMenuItem({ Text("Meters")},{
                        oExpanded=false
                        outputunit="Meters"
                        oconvertion.value=1.00
                        coverunits()
                    })
                    DropdownMenuItem({ Text("Feet")},{
                        oExpanded=false
                        outputunit="Feet"
                        oconvertion.value=0.3048
                        coverunits()
                    })
                    DropdownMenuItem({ Text("Milimeters")},{
                        oExpanded=false
                        outputunit="Milimeters"
                        oconvertion.value=0.001
                        coverunits()
                    })
                }
            }
        }
        Spacer(modifier=Modifier.height(16.dp))
        Text("Result: $output $outputunit")
        Spacer(modifier=Modifier.height(30.dp))
        val context = LocalContext.current
        Button({ Toast.makeText(context, " I tell you Dont touch nig!", Toast.LENGTH_LONG).show() }) {
            Text("PLease dont click!!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}