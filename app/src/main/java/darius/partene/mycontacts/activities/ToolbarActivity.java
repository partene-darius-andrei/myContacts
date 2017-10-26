package darius.partene.mycontacts.activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import net.frakbot.jumpingbeans.JumpingBeans;

import darius.partene.mycontacts.R;

public class ToolbarActivity extends BaseActivity {

    private TextView title;
    private ImageView backIcon;
    private JumpingBeans jumpingBeans;
    private TextView loading;

    /**
     * Full control over toolbar with own views or other methods (ex: show or hide loading)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_toolbar);
        title = findViewById(R.id.title);
        backIcon = findViewById(R.id.back_icon);
        loading = findViewById(R.id.loading);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void setContentView(int layoutToInflate) {
        ViewStub stub = findViewById(R.id.view_stub);
        stub.setLayoutResource(layoutToInflate);
        stub.inflate();
    }

    protected void setTitle(String title) {
        this.title.setText(title);
    }

    public void setTitle(int id) {
        this.title.setText(getString(id));
    }

    protected void hideBackIcon() {
        backIcon.setImageResource(0);
    }

    protected void showBackIcon() {
        backIcon.setVisibility(View.VISIBLE);
    }

    protected void showLoading() {
        loading.setVisibility(View.VISIBLE);
        jumpingBeans = JumpingBeans.with(loading)
                .appendJumpingDots()
                .build();
    }

    protected void hideLoading() {
        loading.setVisibility(View.GONE);
        jumpingBeans.stopJumping();
    }
}
