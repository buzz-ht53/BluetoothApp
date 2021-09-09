package com.buzz_ht.bluetoothact;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> paired;
    private TextView textView;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        textView = findViewById(R.id.button5);
        listView = findViewById(R.id.list);




    }

    public void on(View view) {

        if(!bluetoothAdapter.isEnabled()){
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i,0);
            textView.setText("Your Bluetooth is currently on" );
        }
    }

    public void off(View view) {
        bluetoothAdapter.disable();
        textView.setText("Your Bluetooth is currently off" );
    }

    public void devices(View view) {
        paired = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();
        for(BluetoothDevice bt : paired){
            list.add(bt.getName());
        }
        final ArrayAdapter adapter =new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }

    public void visible(View view) {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(i,0);
    }
}