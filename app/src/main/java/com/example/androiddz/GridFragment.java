package com.example.androiddz;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.androiddz.NumElement.updateView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends Fragment {

    private int maxNumber = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);
        int columnCount = ((GridLayout)rootView.findViewById(R.id.grid_layout)).getColumnCount();
        ArrayList<NumElement> numbers = new ArrayList<>();

        if (savedInstanceState != null) {
            maxNumber = savedInstanceState.getInt("MAX");
            Log.d("ONE", "TWO");
        } else {
            maxNumber = maxNumber != 0 ? maxNumber : 100;
            Log.d("ONE", "3");
        }
        fillList(numbers, maxNumber);
        Log.d("NF", "ON CREATE VIEW");

        final RecyclerView recyclerView = rootView.findViewById(R.id.my_list);
        final LinearLayoutManager layout = new GridLayoutManager(getActivity(), columnCount);
        final MyAdapter adapter = new MyAdapter(numbers);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        Button addBtn = rootView.findViewById(R.id.add_button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addNewElement();
                adapter.notifyItemInserted(adapter.getItemCount());
                maxNumber++;
            }
        });
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            maxNumber = savedInstanceState.getInt("MAX");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("MAX", maxNumber);
        Log.d("TAG", "ON SAVE INSTANCE");
    }

    void fillList(List<NumElement> toFill, int n) {
        for (int i = 1; i < n + 1; i++) {
            toFill.add(new NumElement(i));
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "CLICKED " + ((TextView) v).getText());
                    int i = Integer.parseInt(((TextView) v).getText().toString());
                    ((MainActivity)getActivity()).showOneNumber(i);
                }
            });
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<NumElement> mData;

        public MyAdapter(List<NumElement> data) {
            mData = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View v = inflater.inflate(R.layout.list_element, viewGroup, false);
            Log.d("TAG", "On creating ViewHolder for " + i);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            NumElement num = mData.get(i);
            updateView(num, myViewHolder.mTextView);
            Log.d("TAG", "binding element at position " + i);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void addNewElement() {
            Log.d("TAG", "Now size is " + mData.size());
            mData.add(new NumElement(mData.size() + 1));
        }
    }
}
