package com.example.start.l11a.tasks;

import android.os.AsyncTask;
import android.util.Base64;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by start on 2018-01-11.
 */

public class SendSettingsTask extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(String... params) {
        try {
            int server_port = 12345;

            DatagramSocket s = new DatagramSocket();
            InetAddress local = InetAddress.getByName(params[0]);
            byte[] message = Base64.decode(params[1], 0);
            int msg_length = message.length;

            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);

            return "success";

        } catch (Exception e) {
            return "error";
        }
    }
}
