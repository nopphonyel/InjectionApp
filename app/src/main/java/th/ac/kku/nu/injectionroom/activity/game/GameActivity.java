package th.ac.kku.nu.injectionroom.activity.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import th.ac.kku.nu.injectionroom.customComponent.PaintView;
import th.ac.kku.nu.injectionroom.dialog.TaskDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static th.ac.kku.nu.injectionroom.activity.game.GameActivity.SYRINGE_ANGLE.A15;
import static th.ac.kku.nu.injectionroom.activity.game.GameActivity.SYRINGE_ANGLE.A45;
import static th.ac.kku.nu.injectionroom.activity.game.GameActivity.SYRINGE_ANGLE.A90;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    int STATE = 0;
    final String TAG = "GameActivity";
    Paint paint;

    ImageButton btnGlove, btnCotton, btnSyringe, btnCotNoAlc;
    Button btnA15, btnA45, btnA90, btnInject, btnFinishCleaning, btnFinishInject;
    ImageView skin, handStyle, syringe, mask, cottonOnSkin , reviewTask;
    RelativeLayout cottonAlcControl;
    RequestOptions centerCrop, fitCenter;
    RelativeLayout injectionControl;
    TextView dept , reviewTaskText;
    PaintView alcPainter;
    RelativeLayout cottonNoAlcClickable;
    Animation alcFadeOut, injectionControlFade, syringeSlide;

    AppCompatSeekBar adjustDeptSyringe;

    enum CURRENT_EQUIP {
        COTTON_WITH_ALC, COTTON_NO_ALC, SYRINGE, NONE
    }

    enum SYRINGE_ANGLE {
        A15, A45, A90
    }

    SYRINGE_ANGLE currentAngle;
    Storage.SYRINGE_TYPE currentSyringeType;
    CURRENT_EQUIP currentEquip = CURRENT_EQUIP.NONE;

    Bundle eqpBundle;

    private void initComponent() {
        eqpBundle = getIntent().getExtras();
        currentSyringeType = getSyringeType(eqpBundle.getString(Storage.SYRINGE_TYPE_KEY));

        paint = new Paint();
        paint.setAntiAlias(true);

        reviewTask = (ImageView) findViewById(R.id.ic_review_task);
        reviewTaskText = (TextView) findViewById(R.id.textReviewTask);

        injectionControl = (RelativeLayout) findViewById(R.id.injection_control);
        cottonAlcControl = (RelativeLayout) findViewById(R.id.cotton_alc_control);

        btnA15 = (Button) findViewById(R.id.a15);
        btnA45 = (Button) findViewById(R.id.a45);
        btnA90 = (Button) findViewById(R.id.a90);
        btnInject = (Button) findViewById(R.id.btn_inject);
        btnFinishCleaning = (Button) findViewById(R.id.btn_finish_cleaning);
        btnFinishInject = (Button) findViewById(R.id.btn_finish_inject);

        btnGlove = (ImageButton) findViewById(R.id.btn_glove);
        btnCotton = (ImageButton) findViewById(R.id.btn_cotton);
        btnSyringe = (ImageButton) findViewById(R.id.btn_syringe);
        btnCotNoAlc = (ImageButton) findViewById(R.id.btn_cot_no_alc);
        skin = (ImageView) findViewById(R.id.skin);
        handStyle = (ImageView) findViewById(R.id.hand_style);
        syringe = (ImageView) findViewById(R.id.syringe);
        mask = (ImageView) findViewById(R.id.mask);
        cottonOnSkin = (ImageView) findViewById(R.id.cotton_on_skin);
        adjustDeptSyringe = (AppCompatSeekBar) findViewById(R.id.syringe_dept_adj);

        dept = (TextView) findViewById(R.id.dept_value);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        alcPainter = (PaintView) findViewById(R.id.paint_alc);
        alcPainter.init(metrics);
        alcPainter.blur();

        alcFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_alc);
        injectionControlFade = AnimationUtils.loadAnimation(this, R.anim.fade_alc);
        syringeSlide = AnimationUtils.loadAnimation(this, R.anim.fade_syringe_and_move_out);

        cottonNoAlcClickable = (RelativeLayout) findViewById(R.id.cotton_no_control);

        addListener();

        centerCrop = new RequestOptions();
        fitCenter = new RequestOptions();
        centerCrop = centerCrop.centerCrop();
        fitCenter = fitCenter.fitCenter();

        //Load tools image
        Glide.with(this).load(R.drawable.icon_eqp_glove).apply(fitCenter).into(btnGlove);
        Glide.with(this).load(R.drawable.icon_eqp_cotton_with_alc).apply(fitCenter).into(btnCotton);
        Glide.with(this).load(R.drawable.icon_eqp_vaccine).apply(fitCenter).into(btnSyringe);
        Glide.with(this).load(R.drawable.icon_eqp_cotton_no_alc).apply(fitCenter).into(btnCotNoAlc);

        //Load image background and image component
        Glide.with(this).load(R.drawable.small_skin).apply(centerCrop.centerCrop()).into(skin);
        Glide.with(this).load(R.drawable.small_cotton).apply(centerCrop).into(cottonOnSkin);
        Glide.with(this).load(R.drawable.small_subcu).apply(centerCrop.centerCrop()).into(handStyle);
        Glide.with(this).load(R.drawable.small_subcu_skin_mask).apply(centerCrop.centerCrop()).into(mask);

        setSyr(A15, currentSyringeType);
    }

    int prevValue = 0, diffValue = 0;
    float cottonAlcX = 0, cottonAlcY = 0;

    private void addListener() {
        btnGlove.setOnClickListener(this);
        btnCotNoAlc.setOnClickListener(this);
        btnSyringe.setOnClickListener(this);
        btnCotton.setOnClickListener(this);
        Log.d(TAG, "Current max " + adjustDeptSyringe.getMax());
        adjustDeptSyringe.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String deptStr = getString(R.string.dept) + getVaccineDept() + " " + getString(R.string.mm);
                dept.setText(deptStr);
                diffValue = progress - prevValue;
                switch (currentAngle) {
                    case A90:
                        moveSyr90(diffValue);
                        break;
                    case A45:
                        moveSyr45(diffValue);
                        break;
                    case A15:
                        moveSyr15(diffValue);
                        break;
                }
                prevValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        btnA15.setOnClickListener(this);
        btnA45.setOnClickListener(this);
        btnA90.setOnClickListener(this);
        btnInject.setOnClickListener(this);

        btnFinishCleaning.setOnClickListener(this);
        btnFinishInject.setOnClickListener(this);

        alcPainter.setOnFirstTouchPaintListener(new PaintView.OnFirstTouchPaint() {
            @Override
            public void onFirstTouch() {
                Log.d(TAG, "TRIGGERED");
                btnFinishCleaning.setAlpha((float) 1.0);
                btnFinishCleaning.setEnabled(true);
            }
        });
        cottonNoAlcClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "TRIGGERED");
                btnFinishInject.setAlpha((float) 1.0);
                btnFinishInject.setEnabled(true);
                cottonOnSkin.setVisibility(View.VISIBLE);
            }
        });

        reviewTask.setOnClickListener(this);
        reviewTaskText.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponent();
    }

    @Override
    public void onClick(View v) {
        if (v == btnGlove) {
            Storage.InjectionProcess.totalPoint00++;
            Storage.InjectionProcess.totalPoint10++;
            Storage.InjectionProcess.totalPoint20++;
            Storage.InjectionProcess.glove = true;
            updateState(1);
        }
        if (v == btnSyringe) {
            if (currentEquip == CURRENT_EQUIP.NONE) {
                selectBtn(CURRENT_EQUIP.SYRINGE);
            } else {
                selectBtn(CURRENT_EQUIP.NONE);
            }
        }
        if (v == btnCotton) {
            if (currentEquip == CURRENT_EQUIP.NONE) {
                selectBtn(CURRENT_EQUIP.COTTON_WITH_ALC);
            } else {
                selectBtn(CURRENT_EQUIP.NONE);
            }
        }
        if (v == btnCotNoAlc) {
            if (currentEquip == CURRENT_EQUIP.NONE) {
                selectBtn(CURRENT_EQUIP.COTTON_NO_ALC);
            } else {
                selectBtn(CURRENT_EQUIP.NONE);
            }
        }

        if (v == alcPainter) {
            //enable click finish

        }

        if (v == btnFinishCleaning) {
            //Fade out transition
            cottonAlcControl.startAnimation(alcFadeOut);
            selectBtn(CURRENT_EQUIP.NONE);
            Storage.InjectionProcess.cottonAlc = true;
            Storage.InjectionProcess.totalPoint00++;
            Storage.InjectionProcess.totalPoint10++;
            Storage.InjectionProcess.totalPoint20++;

            STATE = 2;
            updateState(STATE);
        }
        if (v == btnFinishInject) {
            Storage.InjectionProcess.useCotton = true;
            Storage.InjectionProcess.totalPoint00++;
            Storage.InjectionProcess.totalPoint10++;
            Storage.InjectionProcess.totalPoint20++;
            startActivity(new Intent(this, ResultActivity.class));
            finish();
        }

        //Adjust syringe angle
        if (v == btnA15) {
            currentAngle = A15;
            setSyr(currentAngle, currentSyringeType);
        }
        if (v == btnA45) {
            currentAngle = A45;
            setSyr(currentAngle, currentSyringeType);
        }
        if (v == btnA90) {
            currentAngle = A90;
            setSyr(currentAngle, currentSyringeType);
        }

        //Inject button
        if (v == btnInject) {
            Storage.InjectionProcess.dept = getVaccineDept();
            checkInject();
            onClickInject();
            injectionControl.startAnimation(injectionControlFade);
            syringe.startAnimation(syringeSlide);
            selectBtn(CURRENT_EQUIP.NONE);
        }

        if(v == reviewTask || v == reviewTaskText){
            TaskDialog taskDialog = TaskDialog.newInstance(Storage.currentTaskTypeKey , Storage.currentTaskNumber);
            taskDialog.show(getFragmentManager() , "Now review task");
        }
    }

    private void checkInject() {
        if (Storage.currentTaskTypeKey.equalsIgnoreCase(Storage.Task.taskTypeKeyList.get(0))) {
            if (currentAngle == A15) {
                Storage.InjectionProcess.totalPoint00++;
                Storage.InjectionProcess.injectCorrect = true;
            }
        }
        if (Storage.currentTaskTypeKey.equalsIgnoreCase(Storage.Task.taskTypeKeyList.get(1))) {
            if (currentAngle == A90) {
                Storage.InjectionProcess.totalPoint10++;
                Storage.InjectionProcess.injectCorrect = true;
            }
        }
        if (Storage.currentTaskTypeKey.equalsIgnoreCase(Storage.Task.taskTypeKeyList.get(2))) {
            if (currentAngle == A45) {
                Storage.InjectionProcess.totalPoint20++;
                Storage.InjectionProcess.injectCorrect = true;
            }
        }
    }

    private float getVaccineDept() {
        return ((float) (adjustDeptSyringe.getProgress() * 26.0)) / adjustDeptSyringe.getMax();
    }

    private void updateState(int newSTATE) {
        STATE = newSTATE;
        switch (STATE) {
            case 4: //place cotton
                btnCotNoAlc.setEnabled(false);
                btnCotNoAlc.setAlpha((float) 0.3);
            case 3: //finished injection
                btnSyringe.setEnabled(false);
                btnSyringe.setAlpha((float) 0.3);
            case 2: //use cotton
                btnCotton.setEnabled(false);
                btnCotton.setAlpha((float) 0.3);
            case 1: //Wear gloves
                btnGlove.setEnabled(false);
                btnGlove.setAlpha((float) 0.3);
            case 0:
                break;
        }
    }

    private Bitmap baseBitMap;
    float currTX = 0, currTY = 0;

    private void setSyr(SYRINGE_ANGLE angle, Storage.SYRINGE_TYPE type) {
        switch (type) {
            case THREE_CC:
                switch (angle) {
                    case A15:
                        currentAngle = A15;
                        currTY = 0;
                        currTX = 0;
                        baseBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.small_cc3_have_drug_a15);
                        Glide.with(this).load(R.drawable.small_cc3_have_drug_a15).apply(centerCrop.centerCrop()).into(syringe);
                        Glide.with(this).load(R.drawable.small_dermal_im).apply(centerCrop).into(handStyle);
                        Glide.with(this).load(R.drawable.small_dermal_im_mask).apply(centerCrop).into(mask);
                        adjustDeptSyringe.setProgress(0);
                        break;
                    case A45:
                        currentAngle = SYRINGE_ANGLE.A45;
                        currTY = 0;
                        currTX = 0;
                        baseBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.small_cc3_have_drug_a45);
                        Glide.with(this).load(R.drawable.small_cc3_have_drug_a45).apply(centerCrop.centerCrop()).into(syringe);
                        Glide.with(this).load(R.drawable.small_subcu).apply(centerCrop).into(handStyle);
                        Glide.with(this).load(R.drawable.small_subcu_skin_mask).apply(centerCrop).into(mask);
                        adjustDeptSyringe.setProgress(0);
                        break;
                    case A90:
                        currentAngle = A90;
                        currTY = 0;
                        currTX = 0;
                        baseBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.small_cc3_have_drug_a90);
                        Glide.with(this).load(R.drawable.small_cc3_have_drug_a90).apply(centerCrop.centerCrop()).into(syringe);
                        Glide.with(this).load(R.drawable.small_dermal_im).apply(centerCrop).into(handStyle);
                        Glide.with(this).load(R.drawable.small_dermal_im_mask).apply(centerCrop).into(mask);
                        adjustDeptSyringe.setProgress(0);
                        break;
                }
                break;
            case INSULIN_SYRINGE:
                switch (angle) {
                    case A15:
                        currentAngle = A15;
                        currTY = 0;
                        currTX = 0;
                        baseBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.small_pvrv_ri_0_1_ml_a15);
                        Glide.with(this).load(R.drawable.small_pvrv_ri_0_1_ml_a15).apply(centerCrop.centerCrop()).into(syringe);
                        Glide.with(this).load(R.drawable.small_dermal_im).apply(centerCrop).into(handStyle);
                        Glide.with(this).load(R.drawable.small_dermal_im_mask).apply(centerCrop).into(mask);
                        adjustDeptSyringe.setProgress(0);
                        break;
                    case A45:
                        currentAngle = SYRINGE_ANGLE.A45;
                        currTY = 0;
                        currTX = 0;
                        baseBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.small_pvrv_ri_0_1_ml_a45);
                        Glide.with(this).load(R.drawable.small_pvrv_ri_0_1_ml_a45).apply(centerCrop.centerCrop()).into(syringe);
                        Glide.with(this).load(R.drawable.small_subcu).apply(centerCrop).into(handStyle);
                        Glide.with(this).load(R.drawable.small_subcu_skin_mask).apply(centerCrop).into(mask);
                        adjustDeptSyringe.setProgress(0);
                        break;
                    case A90:
                        currentAngle = A90;
                        currTY = 0;
                        currTX = 0;
                        baseBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.small_pvrv_0_1_ml_a90);
                        Glide.with(this).load(R.drawable.small_pvrv_0_1_ml_a90).apply(centerCrop.centerCrop()).into(syringe);
                        Glide.with(this).load(R.drawable.small_dermal_im).apply(centerCrop).into(handStyle);
                        Glide.with(this).load(R.drawable.small_dermal_im_mask).apply(centerCrop).into(mask);
                        adjustDeptSyringe.setProgress(0);
                        break;
                }
                break;

            case ONE_CC:
                switch (angle) {
                    case A15:
                        currentAngle = A15;
                        currTY = 0;
                        currTX = 0;
                        baseBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.small_1cc_15);
                        Glide.with(this).load(R.drawable.small_1cc_15).apply(centerCrop.centerCrop()).into(syringe);
                        Glide.with(this).load(R.drawable.small_dermal_im).apply(centerCrop).into(handStyle);
                        Glide.with(this).load(R.drawable.small_dermal_im_mask).apply(centerCrop).into(mask);
                        adjustDeptSyringe.setProgress(0);
                        break;
                    case A45:
                        currentAngle = SYRINGE_ANGLE.A45;
                        currTY = 0;
                        currTX = 0;
                        baseBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.small_1cc_45);
                        Glide.with(this).load(R.drawable.small_1cc_45).apply(centerCrop.centerCrop()).into(syringe);
                        Glide.with(this).load(R.drawable.small_subcu).apply(centerCrop).into(handStyle);
                        Glide.with(this).load(R.drawable.small_subcu_skin_mask).apply(centerCrop).into(mask);
                        adjustDeptSyringe.setProgress(0);
                        break;
                    case A90:
                        currentAngle = A90;
                        currTY = 0;
                        currTX = 0;
                        baseBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.small_1cc_90);
                        Glide.with(this).load(R.drawable.small_1cc_90).apply(centerCrop.centerCrop()).into(syringe);
                        Glide.with(this).load(R.drawable.small_dermal_im).apply(centerCrop).into(handStyle);
                        Glide.with(this).load(R.drawable.small_dermal_im_mask).apply(centerCrop).into(mask);
                        adjustDeptSyringe.setProgress(0);
                        break;
                }
        }
    }

    private void moveSyr15(int diff) {
        currTX += diff * 1.3;
        currTY += (Storage.ANGLE15_RATIO * (diff * 1.3));
        Bitmap afterMoveSyr = Bitmap.createBitmap(baseBitMap.getWidth(), baseBitMap.getHeight(), baseBitMap.getConfig());
        Canvas pencil = new Canvas(afterMoveSyr);
        Matrix mtx = new Matrix();
        //beforeMove = syringe.getImageMatrix();
        //beforeMove.getValues(matrixValue);
        Log.d(TAG, currTX + " , " + currTY);
        mtx.setTranslate(currTX, currTY);
        pencil.drawBitmap(baseBitMap, mtx, paint);
        syringe.setScaleType(ImageView.ScaleType.CENTER_CROP);
        syringe.setImageBitmap(afterMoveSyr);
    }

    private void moveSyr45(int diff) {
        currTX += diff;
        currTY += diff;
        Bitmap afterMoveSyr = Bitmap.createBitmap(baseBitMap.getWidth(), baseBitMap.getHeight(), baseBitMap.getConfig());
        Canvas pencil = new Canvas(afterMoveSyr);
        Matrix mtx = new Matrix();
        //beforeMove = syringe.getImageMatrix();
        //beforeMove.getValues(matrixValue);
        //Log.d(TAG , currTX + " , " + currTY);
        mtx.setTranslate(currTX, currTY);
        pencil.drawBitmap(baseBitMap, mtx, paint);
        syringe.setScaleType(ImageView.ScaleType.CENTER_CROP);
        syringe.setImageBitmap(afterMoveSyr);
        //Glide.with(this).asBitmap().load(afterMoveSyr).apply(centerCrop).into(syringe);
    }

    private void moveSyr90(int diff) {
        currTY += diff * 1.5;
        Bitmap afterMoveSyr = Bitmap.createBitmap(baseBitMap.getWidth(), baseBitMap.getHeight(), baseBitMap.getConfig());
        Canvas pencil = new Canvas(afterMoveSyr);
        Matrix mtx = new Matrix();
        //beforeMove = syringe.getImageMatrix();
        //beforeMove.getValues(matrixValue);
        //Log.d(TAG , currTX + " , " + currTY);
        mtx.setTranslate(currTX, currTY);
        pencil.drawBitmap(baseBitMap, mtx, paint);
        syringe.setScaleType(ImageView.ScaleType.CENTER_CROP);
        syringe.setImageBitmap(afterMoveSyr);
    }

    private Storage.SYRINGE_TYPE getSyringeType(String type) {
        if (type.equalsIgnoreCase(Storage.THREE_CC_STR)) {
            return Storage.SYRINGE_TYPE.THREE_CC;
        } else if (type.equalsIgnoreCase(Storage.ONE_CC_STR)) {
            return Storage.SYRINGE_TYPE.ONE_CC;
        } else {
            return Storage.SYRINGE_TYPE.INSULIN_SYRINGE;
        }
    }

    private void onClickInject() {
        //Start Animation
        switch (currentAngle) {
            case A15:
                switch (currentSyringeType) {
                    case INSULIN_SYRINGE:
                        Glide.with(this).load(R.drawable.small_nullsyringe_ri_pvrv_a15).transition(DrawableTransitionOptions.withCrossFade(250)).apply(centerCrop).into(syringe);
                        break;
                    case THREE_CC:
                        Glide.with(this).load(R.drawable.small_cc3_no_drug_a15).transition(DrawableTransitionOptions.withCrossFade(250)).apply(centerCrop).into(syringe);
                        break;
                    case ONE_CC:
                        Glide.with(this).load(R.drawable.small_null_1cc_15).transition(DrawableTransitionOptions.withCrossFade(250)).apply(centerCrop).into(syringe);
                        break;
                }
                break;
            case A45:
                switch (currentSyringeType) {
                    case INSULIN_SYRINGE:
                        Glide.with(this).load(R.drawable.small_nullsyringe_ri_pvrv_a45).transition(DrawableTransitionOptions.withCrossFade(250)).apply(centerCrop).into(syringe);
                        break;
                    case THREE_CC:
                        Glide.with(this).load(R.drawable.small_cc3_no_drug_a45).transition(DrawableTransitionOptions.withCrossFade(250)).apply(centerCrop).into(syringe);
                        break;
                    case ONE_CC:
                        Glide.with(this).load(R.drawable.small_null_1cc_45).transition(DrawableTransitionOptions.withCrossFade(250)).apply(centerCrop).into(syringe);
                        break;
                }
                break;
            case A90:
                switch (currentSyringeType) {
                    case INSULIN_SYRINGE:
                        Glide.with(this).load(R.drawable.small_nullsyringe_ri_pvrv_a90).transition(DrawableTransitionOptions.withCrossFade(250)).apply(centerCrop).into(syringe);
                        break;
                    case THREE_CC:
                        Glide.with(this).load(R.drawable.small_cc3_no_drug_a90).transition(DrawableTransitionOptions.withCrossFade(250)).apply(centerCrop).into(syringe);
                        break;
                    case ONE_CC:
                        Glide.with(this).load(R.drawable.small_null_1cc_90).transition(DrawableTransitionOptions.withCrossFade(250)).apply(centerCrop).into(syringe);
                        break;
                }
                break;
        }
        STATE = 3;
        updateState(STATE);
    }


    /**
     * This method use to disable button while user select any equipment
     *
     * @param newEquip an equipment that user has been selected
     */
    private void selectBtn(CURRENT_EQUIP newEquip) {
        currentEquip = newEquip;
        switch (currentEquip) {
            case COTTON_WITH_ALC:
                btnCotNoAlc.setEnabled(false);
                btnSyringe.setEnabled(false);
                btnCotton.setEnabled(true);
                btnGlove.setEnabled(false);

                btnGlove.setAlpha((float) 0.3);
                btnCotNoAlc.setAlpha((float) 0.3);
                btnSyringe.setAlpha((float) 0.3);
                btnCotton.setAlpha((float) 1.0);

                cottonAlcControl.setVisibility(View.VISIBLE);
                break;
            case SYRINGE:
                injectionControl.setVisibility(View.VISIBLE);
                handStyle.setVisibility(View.VISIBLE);
                syringe.setVisibility(View.VISIBLE);
                mask.setVisibility(View.VISIBLE);
                btnCotNoAlc.setEnabled(false);
                btnSyringe.setEnabled(true);
                btnCotton.setEnabled(false);
                btnGlove.setEnabled(false);

                btnGlove.setAlpha((float) 0.3);
                btnCotNoAlc.setAlpha((float) 0.3);
                btnSyringe.setAlpha((float) 1.0);
                btnCotton.setAlpha((float) 0.3);
                break;
            case COTTON_NO_ALC:
                cottonNoAlcClickable.setVisibility(View.VISIBLE);
                btnCotNoAlc.setEnabled(true);
                btnSyringe.setEnabled(false);
                btnGlove.setEnabled(false);
                btnCotton.setEnabled(false);

                btnGlove.setAlpha((float) 0.3);
                btnCotNoAlc.setAlpha((float) 1.0);
                btnSyringe.setAlpha((float) 0.3);
                btnCotton.setAlpha((float) 0.3);
                break;
            case NONE:
                injectionControl.setVisibility(View.GONE);
                cottonAlcControl.setVisibility(View.GONE);
                cottonNoAlcClickable.setVisibility(View.GONE);
                handStyle.setVisibility(View.GONE);
                syringe.setVisibility(View.GONE);
                mask.setVisibility(View.GONE);
                btnGlove.setEnabled(true);
                btnCotNoAlc.setEnabled(true);
                btnSyringe.setEnabled(true);
                btnCotton.setEnabled(true);

                btnGlove.setAlpha((float) 1.0);
                btnCotNoAlc.setAlpha((float) 1.0);
                btnSyringe.setAlpha((float) 1.0);
                btnCotton.setAlpha((float) 1.0);
                updateState(STATE);
                break;
        }
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
