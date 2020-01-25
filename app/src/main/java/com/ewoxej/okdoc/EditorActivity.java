package com.ewoxej.okdoc;

import android.graphics.Bitmap;
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
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class EditorActivity extends AppCompatActivity {

private DataStorage.DocEntry docEntry;
private BitmapRVAdapter bmpAdapter;
private FieldsRVAdapter fieldsAdapter;
    public class BitmapRVAdapter extends RecyclerView.Adapter<EditorActivity.BitmapRVAdapter.ItemViewHolder>
    {
        List<Bitmap> images;
        public class ItemViewHolder extends RecyclerView.ViewHolder
        {
            ImageView image;
            ImageButton deleteBtn;
            ItemViewHolder(View itemView)
            {
                super(itemView);
                image = itemView.findViewById(R.id.doc_photo);
                deleteBtn = itemView.findViewById(R.id.delete_photo_btn);
            }
        }

        BitmapRVAdapter(List<Bitmap> images)
        {
            this.images = images;
        }

        @NonNull
        @Override
        public EditorActivity.BitmapRVAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.images_list_element, parent, false);
            EditorActivity.BitmapRVAdapter.ItemViewHolder pvh = new EditorActivity.BitmapRVAdapter.ItemViewHolder(v);
            return pvh;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onBindViewHolder(@NonNull final EditorActivity.BitmapRVAdapter.ItemViewHolder holder, int position)
        {
            holder.image.setImageBitmap(images.get(position));
            holder.deleteBtn.setOnClickListener(new View.OnClickListener()
            { public void onClick(View v){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Not yet implemented", Toast.LENGTH_SHORT);
                toast.show(); }
            });
        }

        @Override
        public int getItemCount()
        {
            return images.size();
        }


    }


    public class FieldsRVAdapter extends RecyclerView.Adapter<FieldsRVAdapter.ItemViewHolder>
    {
        List<DataStorage.DocField> fields;
        public class ItemViewHolder extends RecyclerView.ViewHolder
        {
            EditText key;
            EditText value;
            ImageButton deleteBtn;
            ItemViewHolder(View itemView)
            {
                super(itemView);
                key = itemView.findViewById(R.id.edit_name);
                value = itemView.findViewById(R.id.edit_value);
                deleteBtn = itemView.findViewById(R.id.field_delete_btn);
            }
        }

        FieldsRVAdapter()
        {
            fields = new ArrayList<>();
            addField();
        }
    public void addField()
    {
        fields.add(new DataStorage.DocField());
    }
        @NonNull
        @Override
        public FieldsRVAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fields_list_element, parent, false);
            FieldsRVAdapter.ItemViewHolder pvh = new FieldsRVAdapter.ItemViewHolder(v);
            return pvh;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onBindViewHolder(@NonNull final FieldsRVAdapter.ItemViewHolder holder, int position)
        {
            holder.key.setText(fields.get(position).key);
            holder.value.setText(fields.get(position).value);
            holder.deleteBtn.setOnClickListener(new View.OnClickListener()
            { public void onClick(View v){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Not yet implemented", Toast.LENGTH_SHORT);
                toast.show(); }
            });

        }

        @Override
        public int getItemCount()
        {
            return fields.size();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        docEntry = new DataStorage.DocEntry();
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et= findViewById(R.id.editTextName);
                docEntry.name = et.getText().toString();
                RecyclerView rv= (RecyclerView)findViewById(R.id.rv_fields);
                for (int x = rv.getChildCount(), i = 0; i < x; ++i) {
                    FieldsRVAdapter.ItemViewHolder holder = (FieldsRVAdapter.ItemViewHolder)rv.getChildViewHolder(rv.getChildAt(i));
                    DataStorage.DocField field = new DataStorage.DocField();
                    field.key = holder.key.getText().toString();
                    field.value = holder.value.getText().toString();
                    docEntry.fields.add(field);
                }
                DataStorage.get().getDocs().add(docEntry);
                finish();
            }
        });
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv_fields);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        fieldsAdapter = new FieldsRVAdapter();
        rv.setAdapter(fieldsAdapter);

        RecyclerView rv_i = (RecyclerView)findViewById(R.id.rv_images);
        rv_i.setHasFixedSize(true);
        LinearLayoutManager llm_i = new LinearLayoutManager(getApplicationContext());
        rv_i.setLayoutManager(llm_i);
        bmpAdapter = new BitmapRVAdapter(docEntry.images);
        rv_i.setAdapter(bmpAdapter);

    }

    public void addImageClick(View v)
    {
//gallery intent
    }

    public void addFieldClick(View v)
    {
    fieldsAdapter.addField();
    fieldsAdapter.notifyItemInserted(fieldsAdapter.getItemCount());
    }
}
