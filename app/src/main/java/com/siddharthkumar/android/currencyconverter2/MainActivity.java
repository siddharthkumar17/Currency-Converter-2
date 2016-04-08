package com.siddharthkumar.android.currencyconverter2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Spinner currencyTag1,currencyTag2;
    TextView currencyLong1, currencyLong2;
    TextView input, results,date;
    public ArrayList<String> currencyTags;
    public ArrayList<String> currencyNames;
    public final static String TAG = "CC2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (TextView)findViewById(R.id.input);
        results = (TextView)findViewById(R.id.results);
        date = (TextView)findViewById(R.id.date);
        currencyTag1 = (Spinner)findViewById(R.id.currencyTag1);
        currencyTag2 = (Spinner)findViewById(R.id.currencyTag2);
        currencyLong1 = (TextView)findViewById(R.id.currencyLong1);
        currencyLong2 = (TextView)findViewById(R.id.currencyLong2);
        currencyTags = new ArrayList<String>(Arrays.asList(new String[]{"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTN", "BWP", "BYR", "BZD", "CAD", "CDF", "CHF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "STD", "SVC", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TVD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VEF", "VND", "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWD"}));
        currencyNames = new ArrayList<String>(Arrays.asList(new String[]{"United Arab Emirates Dirham", "Afghanistan Afghani", "Albania Lek", "Armenia Dram", "Netherlands Antilles Guilder", "Angola Kwanza", "Argentina Peso", "Australia Dollar", "Aruba Guilder", "Azerbaijan New Manat", "Bosnia and Herzegovina Convertible Marka", "Barbados Dollar", "Bangladesh Taka", "Bulgaria Lev", "Bahrain Dinar", "Burundi Franc", "Bermuda Dollar", "Brunei Darussalam Dollar", "Bolivia Boliviano", "Brazil Real", "Bahamas Dollar", "Bhutan Ngultrum", "Botswana Pula", "Belarus Ruble", "Belize Dollar", "Canada Dollar", "Congo/Kinshasa Franc", "Switzerland Franc", "Chile Peso", "China Yuan Renminbi", "Colombia Peso", "Costa Rica Colon", "Cuba Convertible Peso", "Cuba Peso", "Cape Verde Escudo", "Czech Republic Koruna", "Djibouti Franc", "Denmark Krone", "Dominican Republic Peso", "Algeria Dinar", "Egypt Pound", "Eritrea Nakfa", "Ethiopia Birr", "Eurozone Euro", "Fiji Dollar", "Falkland Islands (Malvinas) Pound", "United Kingdom Pound", "Georgia Lari", "Guernsey Pound", "Ghana Cedi", "Gibraltar Pound", "Gambia Dalasi", "Guinea Franc", "Guatemala Quetzal", "Guyana Dollar", "Hong Kong Dollar", "Honduras Lempira", "Croatia Kuna", "Haiti Gourde", "Hungary Forint", "Indonesia Rupiah", "Israel Shekel", "Isle of Man Pound", "India Rupee", "Iraq Dinar", "Iran Rial", "Iceland Krona", "Jersey Pound", "Jamaica Dollar", "Jordan Dinar", "Japan Yen", "Kenya Shilling", "Kyrgyzstan Som", "Cambodia Riel", "Comoros Franc", "Korea (North) Won", "Korea (South) Won", "Kuwait Dinar", "Cayman Islands Dollar", "Kazakhstan Tenge", "Laos Kip", "Lebanon Pound", "Sri Lanka Rupee", "Liberia Dollar", "Lesotho Loti", "Libya Dinar", "Morocco Dirham", "Moldova Leu", "Madagascar Ariary", "Macedonia Denar", "Myanmar (Burma) Kyat", "Mongolia Tughrik", "Macau Pataca", "Mauritania Ouguiya", "Mauritius Rupee", "Maldives (Maldive Islands) Rufiyaa", "Malawi Kwacha", "Mexico Peso", "Malaysia Ringgit", "Mozambique Metical", "Namibia Dollar", "Nigeria Naira", "Nicaragua Cordoba", "Norway Krone", "Nepal Rupee", "New Zealand Dollar", "Oman Rial", "Panama Balboa", "Peru Nuevo Sol", "Papua New Guinea Kina", "Philippines Peso", "Pakistan Rupee", "Poland Zloty", "Paraguay Guarani", "Qatar Riyal", "Romania New Leu", "Serbia Dinar", "Russia Ruble", "Rwanda Franc", "Saudi Arabia Riyal", "Solomon Islands Dollar", "Seychelles Rupee", "Sudan Pound", "Sweden Krona", "Singapore Dollar", "Saint Helena Pound", "Sierra Leone Leone", "Somalia Shilling", "Suriname Dollar", "Sg�o Tom� and Pr�ncipe Dobra", "El Salvador Colon", "Syria Pound", "Swaziland Lilangeni", "Thailand Baht", "Tajikistan Somoni", "Turkmenistan Manat", "Tunisia Dinar", "Tonga Pa'anga", "Turkey Lira", "Trinidad and Tobago Dollar", "Tuvalu Dollar", "Taiwan New Dollar", "Tanzania Shilling", "Ukraine Hryvnia", "Uganda Shilling", "United States Dollar", "Uruguay Peso", "Uzbekistan Som", "Venezuela Bolivar", "Viet Nam Dong", "Vanuatu Vatu", "Samoa Tala", "Communaut� Financi�re Africaine (BEAC) CFA Franc", "East Caribbean Dollar", "International Monetary Fund (IMF) Special Drawing Rights", "Communaut� Financi�re Africaine (BCEAO) Franc", "Comptoirs Fran�ais du Pacifique (CFP) Franc", "Yemen Rial", "South Africa Rand", "Zambia Kwacha", "Zimbabwe Dollar"}));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,currencyTags.toArray(new String[currencyTags.size()]));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencyTag1.setAdapter(adapter);
        currencyTag1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currencyLong1.setText(currencyNames.get(position));
                refresh();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currencyLong1.setText("");
            }
        });
        currencyTag1.setSelection(currencyTags.indexOf("USD"));
        currencyTag2.setAdapter(adapter);
        currencyTag2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currencyLong2.setText(currencyNames.get(position));
                refresh();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currencyLong2.setText("");
            }
        });
        currencyTag2.setSelection(currencyTags.indexOf("EUR"));

    }
    public void refresh() {
        try {
            String[] data = new ConvertTask().execute("http://finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1&s=" + currencyTags.get(currencyTag1.getSelectedItemPosition()) + currencyTags.get(currencyTag2.getSelectedItemPosition()) + "=X").get();
            results.setText(data[1] + " " + currencyNames.get(currencyTag2.getSelectedItemPosition()));
            date.setText("Data retrieved on " + data[2].substring(1, data[2].length() - 1) + " at " + data[3].substring(1, data[3].length() - 1) + " GMT");
            input.setText("1 " + currencyNames.get(currencyTag1.getSelectedItemPosition()) + " =");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private class ConvertTask extends AsyncTask<String,Void,String[]> {
        @Override
        protected String[] doInBackground(String... params) {
            for(String url: params){
                try{
                    InputStream input = new URL(url).openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    String line = reader.readLine();
                    return line.split(",");
                }
                catch (Exception e){
                    Log.e(TAG, e.getMessage());
                }

            }
            return new String[3];
        }
    }
}
