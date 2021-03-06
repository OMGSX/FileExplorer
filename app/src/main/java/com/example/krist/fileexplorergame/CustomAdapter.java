package com.example.krist.fileexplorergame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<FilePOJO> list_members = new ArrayList<>();
    private final LayoutInflater inflater;
    View view;
    MyViewHolder holder;
    MainActivity activity;
    Stack<String> folderHistory;
    FolderHistory fh;
    Context context;
    public CustomAdapter(Context context, MainActivity activ) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        activity = activ;
        fh = new FolderHistory();
        folderHistory = fh.getHistory();
        Log.i("Steps", "CustomAdapterConstructor");
    }

    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i("Steps", "onCreateViewHolder");
        view = inflater.inflate(R.layout.custom_row, parent, false);

        holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(CustomAdapter.MyViewHolder holder, int position) {
        FilePOJO list_items = list_members.get(position);
        holder.user_name.setText(list_items.getFileName());
        holder.content.setText(list_items.getDetail());
        holder.image.setImageResource(getImageId(list_items.getFileImage()));
        Log.i("Steps","onBindViewHolder");
    }

    public int getImageId(String s)
    {

        return context.getResources().getIdentifier("drawable/" + s, null, context.getPackageName());
    }

    @Override
    public int getItemCount()  {
        Log.i("Steps","getItemCount");
        return list_members.size();
    }


    public void setListContent(ArrayList<FilePOJO> list_members) {
        this.list_members = list_members;
        Log.i("Steps","setListContent");
        notifyItemRangeChanged(0, list_members.size());

    }

    public boolean goBack() {

        if (folderHistory.isEmpty())
            return true;
        folderHistory.pop();
        if (!folderHistory.isEmpty())
            activity.populateRecyclerViewValues(folderHistory.peek());
        return false;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView user_name, content, time;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            content = (TextView) itemView.findViewById(R.id.content);
            time = (TextView) itemView.findViewById(R.id.time);
            image = (ImageView) itemView.findViewById(R.id.picture);
            Log.i("Steps","MyViewHolderConstructor");
        }

        @Override
        public void onClick(View v) {

            Log.i("Steps","onClick");
            TextView a = (TextView) v.findViewById(R.id.user_name);
            String clicked = a.getText().toString();
            Log.i("CustomError2", clicked);

            if (folderHistory.isEmpty())
                folderHistory = fh.getHistory();
            String history = folderHistory.peek();
            File clickedFile = new File(history, clicked);
            Log.i("CustomError2", clickedFile.toString());

            if (clickedFile.isDirectory()) {
                folderHistory.push(clickedFile.toString());
                activity.populateRecyclerViewValues(clickedFile.toString());
                Log.i("CustomError2", "|Directory");
            } else {
                String ext = getExtension(clickedFile.toString());
                activity.playFile(clickedFile.toString(),ext);
            }
        }

        public String getExtension(String fileName)
        {
            int l = fileName.length();

            return fileName.substring(l-3,l);
        }
    }
}
