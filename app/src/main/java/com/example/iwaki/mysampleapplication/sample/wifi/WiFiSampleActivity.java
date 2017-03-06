package com.example.iwaki.mysampleapplication.sample.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwaki.mysampleapplication.R;

public class WiFiSampleActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi_sample);

        textView = (TextView) findViewById(R.id.textArea);

        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        new Thread(new Runnable() {
            @Override
            public void run() {

                // WiFiマネージャーの取得
                wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

                // 現在接続しているWiFiの情報を取得
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();

//                stringBuilder
//                        .append("【現在接続中のアクセスポイント】").append(ls)
//                        .append("BSSID      : ").append(wifiInfo.getBSSID()).append(ls)
//                        .append("SSID       : ").append(wifiInfo.getSSID()).append(ls)
//                        .append("IP Adress  : ").append(wifiInfo.getIpAddress()).append(ls)
//                        .append("MAC Adress : ").append(wifiInfo.getMacAddress()).append(ls)
//                        .append("NetworkId  : ").append(wifiInfo.getNetworkId()).append(ls)
//                        .append("Supplicant : ").append(wifiInfo.getSupplicantState()).append(ls)
//                        .append("LinkSpeed  : ").append(wifiInfo.getLinkSpeed()).append(ls)
//                        .append("Rssi       : ").append(wifiInfo.getRssi()).append(ls)
//                        .append("HiddenSSID : ").append(wifiInfo.getHiddenSSID()).append(ls).append(ls);

                // 現在検知しているWiFiの一覧を取得
                wifiManager.startScan();

//                stringBuilder.append("【検知しているWiFi一覧】").append(ls);
//                int nummber = 1;
//                for (ScanResult scanResult : wifiManager.getScanResults()) {
//
//                    stringBuilder
//                            .append("【" + nummber + "】").append(ls)
//                            .append("    SSID         : ").append(scanResult.SSID).append(ls)
//                            .append("    BSSID        : ").append(scanResult.BSSID).append(ls)
//                            .append("    Frequency    : ").append(scanResult.frequency).append(ls)
//                            .append("    capabilities : ").append(scanResult.capabilities).append(ls)
//                            .append("    level        : ").append(scanResult.level).append(ls)
//                            .append("    timestamp    : ").append(scanResult.timestamp).append(ls);
//
//                    if (scanResult.SSID.equals("auhome_acuhQa-W")) {
//                        Toast.makeText(this, "OK!!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    nummber++;
//                }
//
//                textView.setText(stringBuilder.toString());


                // 検知したWiFiに接続を行う以下がうまくいかない

                WifiConfiguration wifiConfiguration = new WifiConfiguration();
                wifiConfiguration.SSID = "\"auhome_acuhQa-W\"";
                wifiConfiguration.hiddenSSID = true;
                wifiConfiguration.status = WifiConfiguration.Status.ENABLED;
                wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wifiConfiguration.preSharedKey = "\"jpeprpfmsdffg\"";

                int networkId = wifiManager.addNetwork(wifiConfiguration);
                if (networkId == -1) {
                    //Toast.makeText(this, "接続失敗", Toast.LENGTH_SHORT).show();
                    Log.d("★", "run: ");
                    return;
                }


                wifiManager.disableNetwork(wifiInfo.getNetworkId());
                wifiManager.enableNetwork(wifiInfo.getNetworkId(), false);

                wifiManager.saveConfiguration();
                wifiManager.updateNetwork(wifiConfiguration);
                wifiManager.enableNetwork(networkId, true);


            }
        }).start();



    }
}
