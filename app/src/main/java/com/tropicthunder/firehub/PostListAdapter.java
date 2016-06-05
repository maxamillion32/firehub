package com.tropicthunder.firehub;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryan Lee on 4/6/2016.
 */
//public class PostListAdapter extends BaseAdapter {
//
//    SessionManager session;
//
//    static class ViewHolder {
//        protected ImageView coursePicture, teacherPicture;
//        protected TextView tvClassTitle, tvCategory, tvTeacherName, tvRating;
//
//    }
//
//    Context context;
//    final ArrayList<PostDetails> postsList;
//    Activity mainAct;
//
//    public  PostListAdapter(Context context, ArrayList<PostDetails> list, Activity mainAct) {
//
//        this.mainAct = mainAct;
//        this.context = context;
//        postsList = list;
//
//        // Session Manager
//        session = new SessionManager(context);
//    }
//
//    @Override
//    public int getCount() {
//        return postsList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return postsList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup arg2) {
//
//        final PostDetails post = postsList.get(position);
//
//        ViewHolder viewHolder;
//
//
//
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.item_course, null);
//
//            viewHolder.coursePicture = (ImageView) convertView.findViewById(R.id.img_CoursePicture);
//            viewHolder.teacherPicture = (ImageView) convertView.findViewById(R.id.img_teacherPicture);
//            viewHolder.tvClassTitle = (TextView) convertView.findViewById(R.id.txt_classTitle);
//            viewHolder.tvCategory = (TextView) convertView.findViewById(R.id.txt_Category);
//            viewHolder.tvRating = (TextView) convertView.findViewById(R.id.txt_rating);
//            viewHolder.tvTeacherName = (TextView) convertView.findViewById(R.id.txt_teacherName);
//
//            // store the holder with the view.
//            convertView.setTag(viewHolder);
//        }else{
//            // we've just avoided calling findViewById() on resource everytime
//            // just use the viewHolder
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        viewHolder.tvRating.setText(post.getRating());
//        viewHolder.tvTeacherName.setText(post.getName());
//        viewHolder.tvCategory.setText(post.getCategory());
//        viewHolder.tvClassTitle.setText(post.getTitle());
//
//        Picasso.with(context).load(post.getCoursePicture()).into(viewHolder.coursePicture);
//        Picasso.with(context).load(post.getTeacherPicture()).into(viewHolder.teacherPicture);
//
//        return convertView;
//    }
//}


public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostsViewHolder>{

    private static List<PostDetails> postsList;
    private Context context;
    private String timeAgo;
    private TextView tvJoined;

    public PostListAdapter(List<PostDetails> posts, Context c){
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

    public class PostsViewHolder extends RecyclerView.ViewHolder{
        ImageView coursePicture, teacherPicture;
        TextView tvClassTitle, tvCategory, tvTeacherName, tvRating;

        public View view;

        PostsViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            coursePicture = (ImageView) itemView.findViewById(R.id.img_CoursePicture);
            teacherPicture = (ImageView) itemView.findViewById(R.id.img_teacherPicture);
            tvClassTitle = (TextView) itemView.findViewById(R.id.txt_classTitle);
            tvCategory = (TextView) itemView.findViewById(R.id.txt_Category);
            tvRating = (TextView) itemView.findViewById(R.id.txt_rating);
            tvTeacherName = (TextView) itemView.findViewById(R.id.txt_teacherName);
            tvJoined = (TextView) itemView.findViewById(R.id.txt_joined);

            coursePicture.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    Intent intent = new Intent(v.getContext(), ClassDetailsActivity.class);
                    intent.putExtra("title", tvClassTitle.getText());
                    intent.putExtra("category", tvCategory.getText());
                    intent.putExtra("name", tvTeacherName.getText());
                    intent.putExtra("rating", tvRating.getText());
                    intent.putExtra("coursePicture", postsList.get(getAdapterPosition()).getCoursePicture());
                    intent.putExtra("teacherPicture", postsList.get(getAdapterPosition()).getTeacherPicture());
                    intent.putExtra("uID", postsList.get(getAdapterPosition()).getUid());
                    intent.putExtra("description", postsList.get(getAdapterPosition()).getDescription());
                    intent.putExtra("venue", postsList.get(getAdapterPosition()).getVenue());
                    intent.putExtra("time", postsList.get(getAdapterPosition()).getTime());
                    intent.putExtra("date", postsList.get(getAdapterPosition()).getDate());
                    intent.putExtra("key", postsList.get(getAdapterPosition()).getKey());
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
    public PostsViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        //inflate card layout
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_course, viewGroup, false);
        return new PostsViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(PostsViewHolder postsViewHolder, final int i) {
        SessionManager sessionManager = new SessionManager(this.context);
        tvJoined.setVisibility(View.INVISIBLE);
        //set answer card textviews text
        if (postsList.get(i).getParticipants() != null){
            for (int j=0; j<postsList.get(i).getParticipants().length; j++){
                if (postsList.get(i).getParticipants()[j].equals(sessionManager.getUid())){
                    tvJoined.setVisibility(View.VISIBLE);
                    break;
                }
                else{
                    tvJoined.setVisibility(View.INVISIBLE);
                }
            }
        }
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
