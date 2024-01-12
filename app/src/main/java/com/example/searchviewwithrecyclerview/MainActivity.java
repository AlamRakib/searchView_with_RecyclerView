package com.example.searchviewwithrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView courseRV;
    private CourseAdapter adapter;
    private ArrayList<CourseModel> courseModelArrayList;
    androidx.appcompat.widget.SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        courseRV = findViewById(R.id.idRVCourses);
        searchView = findViewById(R.id.searchViewId);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });



        buildRecyclerView();



    }


    //======================RecylerView==============================================================

    public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

        private ArrayList<CourseModel> courseModelArrayList;


        public CourseAdapter(ArrayList<CourseModel> courseModelArrayList, Context context) {
            this.courseModelArrayList = courseModelArrayList;
        }


        public void filterList(ArrayList<CourseModel> filterlist) {

            courseModelArrayList = filterlist;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_view, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {

            CourseModel model = courseModelArrayList.get(position);
            holder.courseNameTV.setText(model.getCourseName());
            holder.courseDescTV.setText(model.getCourseDescription());
        }

        @Override
        public int getItemCount() {
            return courseModelArrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private final TextView courseNameTV;
            private final TextView courseDescTV;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                courseNameTV = itemView.findViewById(R.id.idTVCourseName);
                courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            }
        }
    }


  //=========================================================================================================================



    //=======================================================================================================================
    private void filter(String text) {

        ArrayList<CourseModel> filteredlist = new ArrayList<CourseModel>();

        for (CourseModel item : courseModelArrayList) {
            if (item.getCourseName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredlist);
        }
    }
    //============================================================================================================================



    //============================================================================================================================

    private void buildRecyclerView() {

        courseModelArrayList = new ArrayList<CourseModel>();

        courseModelArrayList.add(new CourseModel("DSA", "DSA Self Paced Course"));
        courseModelArrayList.add(new CourseModel("JAVA", "JAVA Self Paced Course"));
        courseModelArrayList.add(new CourseModel("C++", "C++ Self Paced Course"));
        courseModelArrayList.add(new CourseModel("Python", "Python Self Paced Course"));
        courseModelArrayList.add(new CourseModel("Fork CPP", "Fork CPP Self Paced Course"));

        adapter = new CourseAdapter(courseModelArrayList, MainActivity.this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);
        courseRV.setLayoutManager(manager);
        courseRV.setAdapter(adapter);
    }

    //===============================================================================================================================

}