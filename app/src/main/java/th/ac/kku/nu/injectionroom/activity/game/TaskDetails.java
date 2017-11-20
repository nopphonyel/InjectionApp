package th.ac.kku.nu.injectionroom.activity.game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TaskDetails extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnGlove, btnCotton, btnSyringe, btnCotNoAlc;
    RequestOptions centerCrop, fitCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        initializeComponent();
        Storage.InjectionProcess.resetProcessMarks();
        getIntentData();
        setData();
    }

    TextView taskTitle , taskDesc, htpTitle, htpDetail;
    Button startBtn;
    ImageView backBtn, htpPic;
    private void initializeComponent(){
        taskTitle = (TextView) findViewById(R.id.task_type_title);
        taskDesc = (TextView) findViewById(R.id.task_desc);
        startBtn = (Button) findViewById(R.id.start_task);
        startBtn.setOnClickListener(this);
        backBtn = (ImageView) findViewById(R.id.ic_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnGlove = (ImageButton) findViewById(R.id.btn_glove);
        btnCotton = (ImageButton) findViewById(R.id.btn_cotton);
        btnSyringe = (ImageButton) findViewById(R.id.btn_syringe);
        btnCotNoAlc = (ImageButton) findViewById(R.id.btn_cot_no_alc);

        htpTitle = (TextView) findViewById(R.id.htp_title);
        htpDetail = (TextView) findViewById(R.id.htp_detail);
        htpPic = (ImageView) findViewById(R.id.htp_pic);

        //Load tools image
        Glide.with(this).load(R.drawable.icon_eqp_glove).into(btnGlove);
        Glide.with(this).load(R.drawable.icon_eqp_cotton_with_alc).into(btnCotton);
        Glide.with(this).load(R.drawable.icon_eqp_vaccine).into(btnSyringe);
        Glide.with(this).load(R.drawable.icon_eqp_cotton_no_alc).into(btnCotNoAlc);

    }

    String currentKey;
    Integer currentTaskNumber;
    private void getIntentData(){
        Intent intent = getIntent();
        currentKey = intent.getStringExtra("KEY");
        currentTaskNumber = intent.getIntExtra("TASK_NUM",0);
    }

    private void setData(){
        taskTitle.setText(Storage.Task.taskTypeResource.get(currentKey));
        taskDesc.setText(Storage.Task.taskListResource.get(currentKey).get(currentTaskNumber));
    }

    @Override
    public void onClick(View view) {
        if(view == startBtn){
            setCurrentMode(currentKey , currentTaskNumber);
            startActivity(new Intent(this , SelectEquipment.class));
        }
    }

    private void setCurrentMode(String keys , Integer index){
        Storage.currentTaskTypeKey = keys;
        Storage.currentTaskNumber = index;
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }

    public void glove(View view) {
        htpTitle.setText(getResources().getString(R.string.htp_title_1));
        htpDetail.setText(getResources().getString(R.string.htp_detail_1));
    }

    public void cotton(View view) {
        htpTitle.setText(getResources().getString(R.string.htp_title_2));
        htpDetail.setText(getResources().getString(R.string.htp_detail_2));
        centerCrop = new RequestOptions();
        fitCenter = new RequestOptions();
        centerCrop = centerCrop.centerCrop();
        fitCenter = fitCenter.fitCenter();
        Glide.with(this).load(R.drawable.using_cotton_alc).apply(fitCenter).into(htpPic);
    }

    public void syringe(View view) {
        centerCrop = new RequestOptions();
        fitCenter = new RequestOptions();
        centerCrop = centerCrop.centerCrop();
        fitCenter = fitCenter.fitCenter();
        htpTitle.setText(getResources().getString(R.string.htp_title_3));
        htpDetail.setText(getResources().getString(R.string.htp_detail_3));
        Glide.with(this).load(R.drawable.using_vaccine).apply(fitCenter).into(htpPic);
    }

    public void cotton_no_alc(View view) {
        centerCrop = new RequestOptions();
        fitCenter = new RequestOptions();
        centerCrop = centerCrop.centerCrop();
        fitCenter = fitCenter.fitCenter();
        htpTitle.setText(getResources().getString(R.string.htp_title_4));
        htpDetail.setText(getResources().getString(R.string.htp_detail_4));
        Glide.with(this).load(R.drawable.using_cotton).apply(fitCenter).into(htpPic);
    }
}