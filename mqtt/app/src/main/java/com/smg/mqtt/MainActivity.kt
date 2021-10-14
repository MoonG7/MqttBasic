package com.smg.mqtt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions


class MainActivity : AppCompatActivity() {

    val TAG = "Mqtt"
    val TOPIC = "topic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sendmsgBtn = findViewById<Button>(R.id.send_msg)

        try {
            var mqttclient =
                MqttClient("tcp://192.168.0.252:1883", MqttClient.generateClientId(), null)
            mqttclient.connect()

            mqttclient.subscribe(TOPIC)
            mqttclient.setCallback(object : MqttCallback {
                override fun connectionLost(cause: Throwable?) {


                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    Log.i(TAG, "topic = $topic, message = $message")
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {

                }

            })

            sendmsgBtn.setOnClickListener {
                mqttclient.publish(TOPIC, MqttMessage("ssss".toByteArray()))
            }
        } catch (e: Exception) {
            Log.e(TAG, "e = ${e.printStackTrace()}")
        }

    }

}