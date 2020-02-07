package com.dbsrm.valeohackathonproject

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.InputStream
import java.util.*


class bluetoothReceive : AppCompatActivity() {
    var listen: Button? = null
    var listDevices: Button? = null
    var listView: ListView? = null
    var msg_box: TextView? = null
    var status: TextView? = null
    var bluetoothAdapter: BluetoothAdapter? = null
    lateinit var btArray: Array<BluetoothDevice?>
    var sendReceive: SendReceive? = null
    var REQUEST_ENABLE_BLUETOOTH = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth_receive)
        findViewByIdea1()
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter?.isEnabled()!!) {
            val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableIntent, REQUEST_ENABLE_BLUETOOTH)
        }
        implementListeners()
    }

    private fun implementListeners() {
        listDevices!!.setOnClickListener {
            val bt =
                bluetoothAdapter!!.bondedDevices
            val strings = arrayOfNulls<String>(bt.size)
            btArray = arrayOfNulls(bt.size)
            var index = 0
            if (bt.size > 0) {
                for (device in bt) {
                    btArray[index] = device
                    strings[index] = device.name
                    index++
                }
                val arrayAdapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_list_item_1,
                    strings
                )
                listView!!.adapter = arrayAdapter
            }
        }
        listen!!.setOnClickListener {
            val serverClass = ServerClass()
            serverClass.start()
        }
        listen!!.setOnClickListener {
            val serverClass = ServerClass()
            serverClass.start()
        }
        listView!!.onItemClickListener =
            OnItemClickListener { adapterView, view, i, l ->
                val clientClass = ClientClass(btArray[1]!!)
                clientClass.start()
                status!!.text = "Connecting"
            }
    }

    var handler = Handler(Handler.Callback { msg ->
        when (msg.what) {
            STATE_LISTENING -> status!!.text = "Listening"
            STATE_CONNECTING -> status!!.text = "CONNECTING"
            STATE_CONNECTED -> status!!.text = "Connected"
            STATE_CONNECTION_FAILED -> status!!.text = "Connection Failed"
            STATE_MESSAGE_RECEIVED -> {
                val readBuff = msg.obj as ByteArray
                val tempMsg = String(readBuff, 0, msg.arg1)
                msg_box!!.text = tempMsg
            }
        }
        true
    })

    private fun findViewByIdea1() {
        listen = findViewById<View>(R.id.listen1) as Button
        listView = findViewById<View>(R.id.listview1) as ListView
        msg_box = findViewById<View>(R.id.msg1) as TextView
        status = findViewById<View>(R.id.status1) as TextView
        listDevices = findViewById<View>(R.id.listDevices1) as Button
    }

    private inner class ServerClass : Thread() {
        private var serverSocket: BluetoothServerSocket? = null
        override fun run() {
            var socket: BluetoothSocket? = null
            while (socket == null) {
                try {
                    val message = Message.obtain()
                    message.what = STATE_CONNECTING
                    handler.sendMessage(message)
                    socket = serverSocket!!.accept()
                } catch (e: IOException) {
                    e.printStackTrace()
                    val message = Message.obtain()
                    message.what = STATE_CONNECTION_FAILED
                    handler.sendMessage(message)
                }
                if (socket != null) {
                    val message = Message.obtain()
                    message.what = STATE_CONNECTED
                    handler.sendMessage(message)
                    sendReceive = SendReceive(socket)
                    sendReceive!!.start()
                    break
                }
            }
        }

        init {
            try {
                serverSocket = bluetoothAdapter!!.listenUsingRfcommWithServiceRecord(
                    APP_NAME,
                    MY_UUID
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private inner class ClientClass(private val device: BluetoothDevice) : Thread() {
        private var socket: BluetoothSocket? = null
        override fun run() {
            try {
                socket!!.connect()
                val message = Message.obtain()
                message.what = STATE_CONNECTED
                handler.sendMessage(message)
                sendReceive = SendReceive(socket!!)
                sendReceive!!.start()
            } catch (e: IOException) {
                e.printStackTrace()
                val message = Message.obtain()
                message.what = STATE_CONNECTION_FAILED
                handler.sendMessage(message)
            }
        }

        init {
            try {
                socket = device.createRfcommSocketToServiceRecord(MY_UUID)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    inner class SendReceive(private val bluetoothSocket: BluetoothSocket) : Thread() {
        private val inputStream: InputStream?
        override fun run() {
            val buffer = ByteArray(1024)
            var bytes: Int
            while (true) {
                try {
                    bytes = inputStream!!.read(buffer)
                    handler.obtainMessage(
                        STATE_MESSAGE_RECEIVED,
                        bytes,
                        -1,
                        buffer
                    ).sendToTarget()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }



        init {
            var tempIn: InputStream? = null
            try {
                tempIn = bluetoothSocket.inputStream
            } catch (e: IOException) {
                e.printStackTrace()
            }
            inputStream = tempIn

        }
    }

    companion object {
        const val STATE_LISTENING = 1
        const val STATE_CONNECTING = 2
        const val STATE_CONNECTED = 3
        const val STATE_CONNECTION_FAILED = 4
        const val STATE_MESSAGE_RECEIVED = 5
        private const val APP_NAME = "BIChat"
        private val MY_UUID =
            UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db")
    }
}
