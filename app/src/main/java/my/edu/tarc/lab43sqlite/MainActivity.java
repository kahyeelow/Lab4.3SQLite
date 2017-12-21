package my.edu.tarc.lab43sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import my.edu.tarc.lab43sqlite.Model.UserRecord;
import my.edu.tarc.lab43sqlite.Model.UserRecordAdapter;
import my.edu.tarc.lab43sqlite.Model.UserSQLHelper;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listViewRecords;
    UserSQLHelper userSQLHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewRecords = (ListView)findViewById(R.id.listViewRecords);
        listViewRecords.setOnItemClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList() {
        //Retrieve records from SQLite
        userSQLHelper = new UserSQLHelper(this);

        final List<UserRecord> values = userSQLHelper.getAllUsers();

        if(values.isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.no_record_message), Toast.LENGTH_SHORT).show();
        }

        UserRecordAdapter adapter = new UserRecordAdapter(this, R.layout.user_record, values);

        //Link adapter to ListView
        listViewRecords.setAdapter(adapter);
    }

}
