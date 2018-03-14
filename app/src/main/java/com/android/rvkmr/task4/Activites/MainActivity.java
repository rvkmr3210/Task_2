package com.android.rvkmr.task4.Activites;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.rvkmr.task4.Adapters.Listadapter;
import com.android.rvkmr.task4.Database.DatabaseHelper;
import com.android.rvkmr.task4.Models.ExecutionTimePOJO;
import com.android.rvkmr.task4.Models.LibraryPOJO;
import com.android.rvkmr.task4.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ExecutionTimePOJO> list = new ArrayList<>();
    Listadapter listadapter;
    long rTotalExecutionTime=0;
    long sTotalExecutionTime=0;
    TextView tvRealExeTime;
    TextView tvSqliteExeTime;

    DatabaseHelper databaseHelper;
    Realm realm;
    String[] pages = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"
            , "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private int i = 0;
    ProgressDialog dialog;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_list);

        tvRealExeTime=findViewById(R.id.tv_realm_exe_time);
        tvSqliteExeTime=findViewById(R.id.tv_sqlite_exe_time);
        realm = Realm.getDefaultInstance();

        recyclerView = findViewById(R.id.rv_list);
        listadapter = new Listadapter(this, list, R.layout.item_list_cell);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(listadapter);


        try {
            databaseHelper = new DatabaseHelper(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        startProgressDialog();
        for (String string : pages) {
            getData(string);

        }


    }

    private void getData(final String string) {
        String URL = "http://unreal3112.16mb.com/wb1913_" + string + ".html";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                Log.d("success", response);
                realm.beginTransaction();
                ExecutionTimePOJO timePOJO = new ExecutionTimePOJO();
                LibraryPOJO pojo = new LibraryPOJO();
                pojo.setLetter(string);
                pojo.setData(response);
                i = i + 1;

                final long lStartTime = System.nanoTime();
                realm.copyToRealm(pojo);
                long lEndTime = System.nanoTime();
                realm.commitTransaction();
                long outputRealm = lEndTime - lStartTime;
                rTotalExecutionTime=rTotalExecutionTime+(outputRealm/1000000);

                long startTime = System.nanoTime();
                databaseHelper.insertDetailsInDatabase(pojo);
                long endTime = System.nanoTime();
                long opSqlite = endTime - startTime;
                sTotalExecutionTime=sTotalExecutionTime+(opSqlite/1000000);

                timePOJO.setLetter(string);
                timePOJO.setRealm(outputRealm/1000000);
                timePOJO.setSqlite(opSqlite/1000000);
                list.add(timePOJO);
                listadapter.notifyDataSetChanged();

                Log.d(string + "-exe time", String.valueOf(outputRealm / 1000000) + "," + String.valueOf(opSqlite / 1000000));

                if (string.equalsIgnoreCase("z")) {
                    stopProgressDialog();
                    tvRealExeTime.setText(String.valueOf(rTotalExecutionTime));
                    tvSqliteExeTime.setText(String.valueOf(sTotalExecutionTime));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());

            }
        });
        queue.add(request);
    }

    public void startProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading....");
        dialog.show();
    }

    public void stopProgressDialog() {
        dialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
