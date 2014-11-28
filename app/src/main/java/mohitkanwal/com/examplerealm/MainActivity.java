package mohitkanwal.com.examplerealm;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    List<Contact> members = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.members = ContactHelper.getAllContacts();
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
    protected void onResume() {
        super.onResume();
        textView = (TextView) findViewById(R.id.txt_view);
        findViewById(R.id.populate_data_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateDB();
            }
        });

        findViewById(R.id.btn_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptCrash();
            }
        });
    }

    private void attemptCrash() {
        List<Contact> contactList = ContactHelper.getAllContacts();

        // This does not crash
//        List<Contact> filtered1 = new ArrayList<>(Collections2.filter(contactList, new Predicate<Contact>() {
//            @Override
//            public boolean apply(Contact input) {
//                return ContactHelper.getDisplayName(input).contains("s");
//            }
//        }));

        // This piece of code crashes
        // Note I am using this.members which I initialize during onCreate() but don't make a copy
        // Perhaps a thread/reference issue?
        List<Contact> filtered = new ArrayList<>(Collections2.filter(this.members, new Predicate<Contact>() {
            @Override
            public boolean apply(Contact input) {
                return ContactHelper.getDisplayName(input).contains("s");
            }
        }));


        // Crashes. during filter
        textView.setText("Did not crash. Wow!");
    }

    private TextView textView;

    private void populateDB() {
        ContactHelper.populate();
        textView.setText("DB Populated");
    }
}
