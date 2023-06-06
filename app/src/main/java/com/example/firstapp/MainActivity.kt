package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //Tabla del Layout
    private lateinit var tabla: TableLayout
    //Items
    private lateinit var lblItem1: TextView
    private lateinit var txtItem1: TextView
    private lateinit var lblItem2: TextView
    private lateinit var txtItem2: TextView
    private lateinit var lblItem3: TextView
    private lateinit var txtItem3: TextView
    private lateinit var lblItem4: TextView
    private lateinit var txtItem4: TextView
    private lateinit var textoLiteral: TextView

    //Botones
    private lateinit var btnInfo : Button
    private lateinit var btnLiteral : Button
    //Totales
    private lateinit var txtSumaSub: TextView
    private lateinit var txtEnvio: TextView
    private lateinit var txtCupon: TextView
    private lateinit var txtTotal: TextView
    //Datos Nota
    private var fecha = ""
    private var codigoNota = ""
    private var nomUser = ""
    private var totalizadoTotalTotaloso: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cargaDefault()

        this.tabla = findViewById(R.id.table)


        this.btnInfo.setOnClickListener {
            val newRow = TableRow(this)
            tabla.addView(newRow)

            val fechaTextview = TextView(this)
            val codigoNotaTextView = TextView(this)
            val nombreUsuarioTextView = TextView(this)

            this.fecha = "2023-06-05"
            this.codigoNota = "1234"
            this.nomUser = "Rodri"

            fechaTextview.text = fecha
            codigoNotaTextView.text = codigoNota
            nombreUsuarioTextView.text = nomUser

            newRow.addView(fechaTextview)
            newRow.addView(codigoNotaTextView)
            newRow.addView(nombreUsuarioTextView)
        }

        this.btnLiteral.setOnClickListener {
            val newRow = TableRow(this)
            tabla.addView(newRow)

            textoLiteral.text = convertirNumeroEnPalabras(totalizadoTotalTotaloso)
        }
    }

    fun cargaDefault() {
        //Declaracion de variables con la interfaz
        this.lblItem1 = findViewById(R.id.lblItem1)
        this.lblItem2 = findViewById(R.id.lblItem2)
        this.lblItem3 = findViewById(R.id.lblItem3)
        this.lblItem4 = findViewById(R.id.lblItem4)
        this.txtItem1 = findViewById(R.id.txtItem1)
        this.txtItem2 = findViewById(R.id.txtItem2)
        this.txtItem3 = findViewById(R.id.txtItem3)
        this.txtItem4 = findViewById(R.id.txtItem4)

        this.btnInfo = findViewById(R.id.btnInfo)
        this.btnLiteral = findViewById(R.id.btnLiteral)

        this.txtSumaSub = findViewById(R.id.txtSumaSub)
        this.txtEnvio = findViewById(R.id.txtEnvio)
        this.txtCupon = findViewById(R.id.txtCupon)
        this.txtTotal = findViewById(R.id.txtTotal)

        val producto1 = Producto("Coca-colla", 3, 10.0)
        val producto2 = Producto("Doritos", 2, 7.0)
        val producto3 = Producto("Condones Masculan", 1, 13.0)
        val producto4 = Producto("Nutella", 4, 5.0)

        var subProd1 = producto1.getCantidad().toDouble() * producto1.getPrecio()
        var subProd2 = producto2.getCantidad().toDouble() * producto2.getPrecio()
        var subProd3 = producto3.getCantidad().toDouble() * producto3.getPrecio()
        var subProd4 = producto4.getCantidad().toDouble() * producto4.getPrecio()

        var subSuma = subProd1+subProd2+subProd3+subProd4
        var envio = 10.0
        var cupon = 5.0
        var total = subSuma+envio-cupon

        lblItem1.text = producto1.getNombreProducto() + " x " + producto1.getCantidad()
        txtItem1.text = "$ ${subProd1}"
        lblItem2.text = producto2.getNombreProducto() + " x " + producto2.getCantidad()
        txtItem2.text = "$ ${subProd2}"
        lblItem3.text = producto3.getNombreProducto() + " x " + producto3.getCantidad()
        txtItem3.text = "$ ${subProd3}"
        lblItem4.text = producto4.getNombreProducto() + " x " + producto4.getCantidad()
        txtItem4.text = "$ ${subProd4}"

        txtSumaSub.text = "$ ${subSuma}"
        txtEnvio.text = "$ ${envio}"
        txtCupon.text = "- $ ${cupon}"
        txtTotal.text = "$ ${total}"

        totalizadoTotalTotaloso = total
    }

    private class Producto(nombreProducto: String = "", cantidad: Int = 0, precio: Double = 0.0) {
        private val nombrePructo: String = nombreProducto
        private val cantidad: Int = cantidad
        private val precio: Double = precio

        fun getNombreProducto():String {
            return nombrePructo
        }

        fun getCantidad():Int {
            return cantidad
        }

        fun getPrecio():Double {
            return precio
        }
    }

    private fun convertirNumeroEnPalabras(numero: Double): String {
        val unidades = arrayOf(
            "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"
        )
        val especiales = arrayOf(
            "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis",
            "diecisiete", "dieciocho", "diecinueve"
        )
        val decenas = arrayOf(
            "", "", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"
        )
        val centenas = arrayOf(
            "", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos",
            "seiscientos", "setecientos", "ochocientos", "novecientos"
        )

        if (numero == 0.0) {
            return "cero"
        }

        var resultado = ""

        if (numero < 0.0) {
            resultado += "menos "
        }

        val num = Math.abs(numero)

        val parteEntera = num.toInt()
        val parteDecimal = num - parteEntera

        if (parteEntera < 10) {
            resultado += unidades[parteEntera]
        } else if (parteEntera < 20) {
            resultado += especiales[parteEntera - 10]
        } else if (parteEntera < 100) {
            resultado += decenas[parteEntera / 10] + " y " + unidades[parteEntera % 10]
        } else if (parteEntera < 1000) {
            resultado += centenas[parteEntera / 100] + " " + convertirNumeroEnPalabras((parteEntera % 100).toDouble())
        } else if (parteEntera < 1000000) {
            resultado += convertirNumeroEnPalabras((parteEntera / 1000).toDouble()) + " mil " + convertirNumeroEnPalabras(
                (parteEntera % 1000).toDouble()
            )
        } else {
            resultado += convertirNumeroEnPalabras((parteEntera / 1000000).toDouble()) + " millones " + convertirNumeroEnPalabras(
                (parteEntera % 1000000).toDouble()
            )
        }

        if (parteDecimal > 0.0) {
            resultado += " punto " + convertirDecimalEnPalabras(parteDecimal)
        }

        return resultado.trim()
    }

    private fun convertirDecimalEnPalabras(decimal: Double): String {
        val digitos = decimal.toString().substringAfter(".")

        val unidades = arrayOf(
            "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"
        )
        val decenas = arrayOf(
            "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis",
            "diecisiete", "dieciocho", "diecinueve"
        )

        var resultado = ""

        for (i in 0 until digitos.length) {
            val digito = digitos[i].toString().toInt()
            if (digito == 0) continue

            if (i == 0 && digito == 1 && digitos.length == 1) {
                resultado += "uno"
            } else if (i == 0 && digito == 1 && digitos.length > 1) {
                resultado += decenas[digito]
            } else {
                resultado += unidades[digito]
            }
        }

        return resultado
    }
}