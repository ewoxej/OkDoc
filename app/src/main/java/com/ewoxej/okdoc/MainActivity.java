package com.ewoxej.okdoc;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    class DocumentElement
    {
        private String name;
        private String number;

        public DocumentElement(String name, String num)
        {
            this.name = name;
            this.number = num;
        }
    }
    private List<DocumentElement> persons;
    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new DocumentElement("monobank", "5375 4141 0330 7293"));
        persons.add(new DocumentElement("PrivatBank Универсальная", "4149 6293 8503 2982"));
        persons.add(new DocumentElement("Test", "something"));
    }
    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>
    {
        List<DocumentElement> persons;

        RVAdapter(List<DocumentElement> persons)
        {
            this.persons = persons;
        }

        @NonNull
        @Override
        public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onBindViewHolder(@NonNull final PersonViewHolder holder, int position)
        {
            holder.personName.setText(persons.get(position).name);
            holder.personAge.setText(persons.get(position).number);
            holder.layout.setOnClickListener(new View.OnClickListener()
            { public void onClick(View v){
                Toast toast = Toast.makeText(getApplicationContext(),
                    "Not yet implemented", Toast.LENGTH_SHORT);
                toast.show(); }
            });
            holder.copyBtn.setOnClickListener(new View.OnClickListener()
            { public void onClick(View v){
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", holder.personAge.getText());
                clipboard.setPrimaryClip(clip);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Copied!", Toast.LENGTH_SHORT);
                toast.show(); }
            });
        }

        @Override
        public int getItemCount()
        {
            return persons.size();
        }

        public class PersonViewHolder extends RecyclerView.ViewHolder
        {
            TextView personName;
            TextView personAge;
            View layout;
            ImageButton copyBtn;

            PersonViewHolder(View itemView)
            {
                super(itemView);
                personName = (TextView) itemView.findViewById(R.id.name_str);
                layout = itemView.findViewById(R.id.list_element);
                personAge = (TextView) itemView.findViewById(R.id.num_str);
                copyBtn = (ImageButton) itemView.findViewById(R.id.copy_btn);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        initializeData();
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


