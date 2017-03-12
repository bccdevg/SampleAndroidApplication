package com.example.iwaki.mysampleapplication.sample.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.iwaki.mysampleapplication.R;

import java.util.List;

public class WiFiSampleActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private TextView textView;
    private Handler handler;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi_sample);

        textView = (TextView) findViewById(R.id.textArea);

        handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // WiFiマネージャーの取得。メモリリークを回避するためにアプリケーションコンテキストから取得する必要がある
                wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

                // 現在接続しているWiFiの情報を取得。パーミッションACCESS_WIFI_STATE(normal)が必要
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();

                StringBuilder stringBuilder = new StringBuilder();
                String ls = System.getProperty("line.separator");
                stringBuilder
                        .append("【現在接続中のアクセスポイント】").append(ls)
                        .append("BSSID      : ").append(wifiInfo.getBSSID()).append(ls)
                        .append("SSID       : ").append(wifiInfo.getSSID()).append(ls)
                        .append("IP Adress  : ").append(wifiInfo.getIpAddress()).append(ls)
                        .append("MAC Adress : ").append(wifiInfo.getMacAddress()).append(ls)
                        .append("NetworkId  : ").append(wifiInfo.getNetworkId()).append(ls)
                        .append("Supplicant : ").append(wifiInfo.getSupplicantState()).append(ls)
                        .append("LinkSpeed  : ").append(wifiInfo.getLinkSpeed()).append(ls)
                        .append("Rssi       : ").append(wifiInfo.getRssi()).append(ls)
                        .append("HiddenSSID : ").append(wifiInfo.getHiddenSSID()).append(ls).append(ls);

                // 現在検知しているWiFiの一覧を取得
                // Android6(Marshmallow)からは位置情報のパーミッションである「ACCESS_FINE_LOCATION」または「ACCESS_COARSE_LOCATION」パーミッション(dengerous)が必要
                // このパーミッションがないと、例外が発生するわけではないが、wifiManager.getScanResults()の結果が0になる
                wifiManager.startScan();

                stringBuilder.append("【検知しているWiFi一覧】").append(ls);
                int nummber = 1;
                for (ScanResult scanResult : wifiManager.getScanResults()) {

                    stringBuilder
                            .append("【" + nummber + "】").append(ls)
                            .append("    SSID         : ").append(scanResult.SSID).append(ls)
                            .append("    BSSID        : ").append(scanResult.BSSID).append(ls)
                            .append("    Frequency    : ").append(scanResult.frequency).append(ls)
                            .append("    capabilities : ").append(scanResult.capabilities).append(ls)
                            .append("    level        : ").append(scanResult.level).append(ls)
                            .append("    timestamp    : ").append(scanResult.timestamp).append(ls);

                    nummber++;
                }

                // 接続実績のあるWiFiの一覧を取得
                stringBuilder.append("【接続実績のあるWiFi一覧】").append(ls);

                // パーミッションACCESS_WIFI_STATE(normal)が必要
                List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
                for (WifiConfiguration nw : configuredNetworks) {
                    stringBuilder
                            .append("【" + nummber + "】").append(ls)
                            .append("    SSID : ").append(nw.SSID).append(ls)
                            .append("    BSSID : ").append(nw.BSSID).append(ls)
                            .append("    allowedAuthAlgorithms : ").append(nw.allowedAuthAlgorithms).append(ls)
                            .append("    allowedGroupCiphers : ").append(nw.allowedGroupCiphers).append(ls)
                            .append("    allowedPairwiseCiphers : ").append(nw.allowedPairwiseCiphers).append(ls)
                            .append("    allowedProtocols : ").append(nw.allowedProtocols).append(ls)
                            .append("    allowedKeyManagement : ").append(nw.allowedKeyManagement).append(ls)
                            .append("    enterpriseConfig : ").append(nw.enterpriseConfig).append(ls)
                            .append("    hiddenSSID : ").append(nw.hiddenSSID).append(ls)
                            .append("    networkId : ").append(nw.networkId).append(ls)
                            .append("    preSharedKey : ").append(nw.preSharedKey).append(ls)
                            .append("    status : ").append(nw.status).append(ls)
                            .append("    wepKeys : ").append(nw.wepKeys[1]).append(ls)
                            .append("    wepTxKeyIndex : ").append(nw.wepTxKeyIndex).append(ls);

                }


                final String ouput = stringBuilder.toString();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(ouput);
                    }
                });


                // WiFi接続を作成する。接続先ネットワークに合った適切な設定を行わないと、
                // ネットワークへの追加、保存(WifiManager#addNetwork())はできても、
                // 実際の接続(WifiManager#enableNetwork())は失敗するので注意
                // ※いったん手動接続して、接続先ネットワークの情報を確かめて、それに合った設定をしておくとよい
                // ※SSID、パスワードは""で囲まないとWifiManager#addNetwork()の結果として-1が返るので注意

                WifiConfiguration wifiConfiguration = new WifiConfiguration();
                wifiConfiguration.SSID = "\"auhome_acuhQa-W\"";
                wifiConfiguration.status = WifiConfiguration.Status.ENABLED;
                wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
                wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                wifiConfiguration.wepKeys[0] = "\"jpeprpfmsdffg\"";
                wifiConfiguration.wepTxKeyIndex = 0;

                // Configured neworksのリストに追加する
                // Wi-Fiの接続状態を変更するには(追加、接続、切断 など)パーミッション CHANGE_WIFI_STATE(normal)が必要
                int networkId = wifiManager.addNetwork(wifiConfiguration);


                // Configured networks のリストをサプリカントに通知する。このメソッド呼び出し後はネットワークIDが変わる可能性がある
                // ※この処理は必要？？この処理がなくても、WifiManager#addNetworkが成功すると「保存済のネットワーク」として保存されるようになるようだが
                //wifiManager.saveConfiguration();


                // 現在接続中のWi-Fiから切断する
                wifiManager.disconnect();
                // 新規作成したネットワークを有効化する
                wifiManager.enableNetwork(networkId, true);
                // ↑へ接続を試みる。↑だけでも↑に自動接続されるが、reconnect()した方がすぐに接続される感じがする
                wifiManager.reconnect();

            }
        }).start();

    }
}
