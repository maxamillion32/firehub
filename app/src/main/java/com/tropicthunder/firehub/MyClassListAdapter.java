package com.tropicthunder.firehub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bryan Lee on 4/6/2016.
 */
public class MyClassListAdapter extends RecyclerView.Adapter<MyClassListAdapter.MyClassViewHolder>{

    private static List<PostDetails> postsList;
    private Context context;
    private String timeAgo;

    public MyClassListAdapter(List<PostDetails> posts, Context c){
        this.postsList = posts;
        this.context = c;
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    /*public void updateData(List<QuestionData> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }*/

    public static class MyClassViewHolder extends RecyclerView.ViewHolder{

        TextView tvDate, tvClassTitle, tvTeacherName;

        public View view;

        MyClassViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            tvClassTitle = (TextView) itemView.findViewById(R.id.txt_className);
            tvDate = (TextView) itemView.findViewById(R.id.txt_Date);
            tvTeacherName = (TextView) itemView.findViewById(R.id.txt_teacher);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), ClassDetailsActivity.class);
                    intent.putExtra("title", tvClassTitle.getText());
                    intent.putExtra("category", postsList.get(getAdapterPosition()).getCategory());
                    intent.putExtra("name", tvTeacherName.getText());
                    intent.putExtra("rating", postsList.get(getAdapterPosition()).getRating());
                    intent.putExtra("coursePicture", postsList.get(getAdapterPosition()).getCoursePicture());
                    intent.putExtra("teacherPicture", postsList.get(getAdapterPosition()).getTeacherPicture());
                    intent.putExtra("uID", postsList.get(getAdapterPosition()).getUid());
                    intent.putExtra("description", postsList.get(getAdapterPosition()).getDescription());
                    intent.putExtra("venue", postsList.get(getAdapterPosition()).getVenue());
                    intent.putExtra("time", postsList.get(getAdapterPosition()).getTime());
                    intent.putExtra("date", postsList.get(getAdapterPosition()).getDate());
                    intent.putExtra("participants", postsList.get(getAdapterPosition()).getParticipants());
                    v.getContext().startActivity(intent);
                }
            });


            /*view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //Toast.makeText(v.getContext(), "complete item clicked", Toast.LENGTH_LONG).show();
                    //System.out.println("Hello.. its item click on recyclerview..");


                }
            });*/
        }


        /*//@Override
        public void onClick(View v) {
            //Log.d(TAG, "onClick " + getPosition() + " " + mItem);

            int pos = getPosition();

            //change activity
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            intent.putExtra("item", pos);
            intent.putExtra("question", question.getText());
            intent.putExtra("username", username.getText());
            intent.putExtra("when", when.getText());
            intent.putExtra("rating", rating.getText());
            intent.putExtra("userid", users.get(pos).getId());

            v.getContext().startActivity(intent);
        }*/
    }

    @Override
    public MyClassViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        //inflate card layout
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_myclass, viewGroup, false);
        return new MyClassViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyClassViewHolder postsViewHolder, final int i) {


        //set answer card textviews text
        postsViewHolder.tvDate.setText(postsList.get(i).getDate());
        postsViewHolder.tvTeacherName.setText(postsList.get(i).getName());

        postsViewHolder.tvClassTitle.setText(postsList.get(i).getTitle());



//        postsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //change activity
//                Intent intent = new Intent(v.getContext(), SelectedQuestionActivity.class);
//
//                intent.putExtra("when", timeAgo);
//                intent.putExtra("questionID", questions.get(i).getId());
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                v.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}