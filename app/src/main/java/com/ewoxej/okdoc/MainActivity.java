package com.ewoxej.okdoc;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same fields.
    private RVAdapter adapter;
    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder>
    {
        List<DataStorage.DocEntry> docs;
        public class ItemViewHolder extends RecyclerView.ViewHolder
        {
            TextView title;
            TextView number;
            View layout;
            ImageButton copyBtn;
            ItemViewHolder(View itemView)
            {
                super(itemView);
                title = itemView.findViewById(R.id.name_str);
                layout = itemView.findViewById(R.id.list_element);
                number = itemView.findViewById(R.id.num_str);
                copyBtn = itemView.findViewById(R.id.copy_btn);
            }
        }

        RVAdapter()
        {
            this.docs = DataStorage.get().getDocs();
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
            ItemViewHolder pvh = new ItemViewHolder(v);
            return pvh;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position)
        {
            DataStorage.DocEntry currentDoc = docs.get(position);
            holder.title.setText(currentDoc.name);
            if(currentDoc.fields.isEmpty())
                holder.number.setText("");
            else
                holder.number.setText(currentDoc.fields.get(0).value);
            holder.layout.setOnClickListener(new View.OnClickListener()
            { public void onClick(View v){
                Toast toast = Toast.makeText(getApplicationContext(),
                    "Not yet implemented", Toast.LENGTH_SHORT);
                toast.show(); }
            });
            holder.copyBtn.setOnClickListener(new View.OnClickListener()
            { public void onClick(View v){
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", holder.number.getText());
                clipboard.setPrimaryClip(clip);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Copied!", Toast.LENGTH_SHORT);
                toast.show(); }
            });
        }

        @Override
        public int getItemCount()
        {
            return docs.size();
        }


    }
final int RESULT_DIAL=1;
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
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivityForResult(intent, RESULT_DIAL);
            }
        });
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        adapter = new RVAdapter();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == RESULT_DIAL)
       {
           adapter.notifyDataSetChanged();
       }
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


