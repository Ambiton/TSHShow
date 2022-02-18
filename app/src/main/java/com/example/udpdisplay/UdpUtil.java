package com.example.udpdisplay;

import android.net.wifi.WifiManager;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpUtil  implements Runnable {
    private static final String TAG = "UdpUtil";
    public Boolean IsThreadDisable = false;//指示监听线程是否终止
    private static WifiManager.MulticastLock lock;
    InetAddress mInetAddress;

    public UdpUtil(WifiManager manager) {
        this.lock = manager.createMulticastLock("UDPwifi");
    }

    public void StartListen() {
        // UDP服务器监听的端口
        Integer port = 8906;
        // 接收的字节大小，客户端发送的数据不能超过这个大小
        byte[] message = new byte[100];
        try {
            // 建立Socket连接
            DatagramSocket datagramSocket = new DatagramSocket(port);
            datagramSocket.setBroadcast(true);
            DatagramPacket datagramPacket = new DatagramPacket(message,
                    message.length);
            try {
                while (!IsThreadDisable) {
                    // 准备接收数据
                    Log.d(TAG, "准备接受");
                    this.lock.acquire();

                    datagramSocket.receive(datagramPacket);
                    String strMsg = new String(datagramPacket.getData()).trim();
                    Log.d(TAG, datagramPacket.getAddress()
                            .getHostAddress().toString()
                            + ":" + strMsg);
                    this.lock.release();
                }
            } catch (IOException e) {//IOException
                e.printStackTrace();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void send(String message) {
        message = (message == null ? "Hello IdeasAndroid!" : message);
        int server_port = 8008;
        Log.d(TAG, "UDP发送数据:" + message);
        DatagramSocket udpSocket = null;
        try {
            udpSocket = new DatagramSocket(null);
            udpSocket.setReuseAddress(true);
            //设置本地端口
            udpSocket.bind(new InetSocketAddress(8008));
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress local = null;
        try {
            local = InetAddress.getByName("192.168.101.190");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int msg_length = message.length();
        byte[] messageByte = message.getBytes();
        DatagramPacket p = new DatagramPacket(messageByte, msg_length, new InetSocketAddress("255.255.255.255", server_port));
        try {
            udpSocket.send(p);
            udpSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        StartListen();
    }
}
