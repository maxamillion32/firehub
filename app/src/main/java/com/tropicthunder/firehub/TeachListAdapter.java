package com.tropicthunder.firehub;

import android.annotation.SuppressLint;
import android.content.Context;
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
public class TeachListAdapter extends RecyclerView.Adapter<TeachListAdapter.TeachingViewHolder>{

    private List<PostDetails> postsList;
    private Context context;
    private String timeAgo;

    public TeachListAdapter(List<PostDetails> posts, Context c){
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

    public static class TeachingViewHolder extends RecyclerView.ViewHolder{
        ImageView coursePicture, teacherPicture;
        TextView tvClassTitle, tvCategory, tvTeacherName, tvRating;

        public View view;

        TeachingViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            coursePicture = (ImageView) itemView.findViewById(R.id.img_CoursePicture);
            teacherPicture = (ImageView) itemView.findViewById(R.id.img_teacherPicture);
            tvClassTitle = (TextView) itemView.findViewById(R.id.txt_classTitle);
            tvCategory = (TextView) itemView.findViewById(R.id.txt_Category);
            tvRating = (TextView) itemView.findViewById(R.id.txt_rating);
            tvTeacherName = (TextView) itemView.findViewById(R.id.txt_teacherName);


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
    public TeachingViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        //inflate card layout
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_course, viewGroup, false);
        return new TeachingViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(TeachingViewHolder postsViewHolder, final int i) {


        //set answer card textviews text
        postsViewHolder.tvRating.setText(postsList.get(i).getRating());
        postsViewHolder.tvTeacherName.setText(postsList.get(i).getName());
        postsViewHolder.tvCategory.setText(postsList.get(i).getCategory());
        postsViewHolder.tvClassTitle.setText(postsList.get(i).getTitle());

        Picasso.with(context).load(postsList.get(i).getCoursePicture())
                .centerCrop()
                .fit()
                .into(postsViewHolder.coursePicture);
        Picasso.with(context).load(postsList.get(i).getTeacherPicture())
                .centerCrop()
                .fit()
                .into(postsViewHolder.teacherPicture);

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
