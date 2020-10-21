package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.lang.reflect.Array;

public class Emulate_picture extends AppCompatActivity implements View.OnClickListener {
    ArFragment arFragment;

    ModelRenderable model1_Renderable;



    ImageView img_mmodel1;
    ImageView img_mmodel2;
    ImageView img_mmodel3;
    ImageView img_mmodel4;
    ImageView img_mmodel5;
    ImageView img_mmodel6;
    ImageView img_mmodel7;
    ImageView img_mmodel8;
    ImageView img_mmodel9;
    ImageView img_mmodel10;
    ImageView img_mmodel11;
    ImageView img_mmodel12;
    ImageView img_mmodel13;
    ImageView img_mmodel14;


    ImageView img_fmodel1;
    ImageView img_fmodel2;
    ImageView img_fmodel3;
    ImageView img_fmodel4;
    ImageView img_fmodel5;
    ImageView img_fmodel6;
    ImageView img_fmodel7;
    ImageView img_fmodel8;
    ImageView img_fmodel9;
    ImageView img_fmodel10;
    ImageView img_fmodel11;
    ImageView img_fmodel12;
    ImageView img_fmodel13;
    ImageView img_fmodel14;
    ImageView img_fmodel15;
    ImageView img_fmodel16;
    ImageView img_fmodel17;



    View ArrayView[];

    ViewRenderable Name_Model;
    ViewRenderable viewRenderable_fmodel1;
    ViewRenderable viewRenderable_fmodel2;
    ViewRenderable viewRenderable_fmodel3;
    ViewRenderable viewRenderable_fmodel4;
    ViewRenderable viewRenderable_fmodel5;
    ViewRenderable viewRenderable_fmodel6;
    ViewRenderable viewRenderable_fmodel7;
    ViewRenderable viewRenderable_fmodel8;
    ViewRenderable viewRenderable_fmodel9;
    ViewRenderable viewRenderable_fmodel10;
    ViewRenderable viewRenderable_fmodel11;
    ViewRenderable viewRenderable_fmodel12;
    ViewRenderable viewRenderable_fmodel13;
    ViewRenderable viewRenderable_fmodel14;
    ViewRenderable viewRenderable_fmodel15;
    ViewRenderable viewRenderable_fmodel16;
    ViewRenderable viewRenderable_fmodel17;

    ViewRenderable viewRenderable_mmodel1;
    ViewRenderable viewRenderable_mmodel2;
    ViewRenderable viewRenderable_mmodel3;
    ViewRenderable viewRenderable_mmodel4;
    ViewRenderable viewRenderable_mmodel5;
    ViewRenderable viewRenderable_mmodel6;
    ViewRenderable viewRenderable_mmodel7;
    ViewRenderable viewRenderable_mmodel8;
    ViewRenderable viewRenderable_mmodel9;
    ViewRenderable viewRenderable_mmodel10;
    ViewRenderable viewRenderable_mmodel11;
    ViewRenderable viewRenderable_mmodel12;
    ViewRenderable viewRenderable_mmodel13;
    ViewRenderable viewRenderable_mmodel14;



    int selected = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emulate_picture);

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

        img_fmodel1 = (ImageView)findViewById(R.id.img_fmodel1);
        img_fmodel2 = (ImageView)findViewById(R.id.img_fmodel2);
        img_fmodel3 = (ImageView)findViewById(R.id.img_fmodel3);
        img_fmodel4 = (ImageView)findViewById(R.id.img_fmodel4);
        img_fmodel5 = (ImageView)findViewById(R.id.img_fmodel5);
        img_fmodel6 = (ImageView)findViewById(R.id.img_fmodel6);
        img_fmodel7 = (ImageView)findViewById(R.id.img_fmodel7);
        img_fmodel8 = (ImageView)findViewById(R.id.img_fmodel8);
        img_fmodel9 = (ImageView)findViewById(R.id.img_fmodel9);
        img_fmodel10 = (ImageView)findViewById(R.id.img_fmodel10);
        img_fmodel11 = (ImageView)findViewById(R.id.img_fmodel11);
        img_fmodel12 = (ImageView)findViewById(R.id.img_fmodel12);
        img_fmodel13 = (ImageView)findViewById(R.id.img_fmodel13);
        img_fmodel14 = (ImageView)findViewById(R.id.img_fmodel14);
        img_fmodel15 = (ImageView)findViewById(R.id.img_fmodel15);
        img_fmodel16 = (ImageView)findViewById(R.id.img_fmodel16);
        img_fmodel17 = (ImageView)findViewById(R.id.img_fmodel17);

        img_mmodel1 = (ImageView)findViewById(R.id.img_mmodel1);
        img_mmodel2 = (ImageView)findViewById(R.id.img_mmodel2);
        img_mmodel3 = (ImageView)findViewById(R.id.img_mmodel3);
        img_mmodel4 = (ImageView)findViewById(R.id.img_mmodel4);
        img_mmodel5 = (ImageView)findViewById(R.id.img_mmodel5);
        img_mmodel6 = (ImageView)findViewById(R.id.img_mmodel6);
        img_mmodel7 = (ImageView)findViewById(R.id.img_mmodel7);
        img_mmodel8 = (ImageView)findViewById(R.id.img_mmodel8);
        img_mmodel9 = (ImageView)findViewById(R.id.img_mmodel9);
        img_mmodel10 = (ImageView)findViewById(R.id.img_mmodel10);
        img_mmodel11 = (ImageView)findViewById(R.id.img_mmodel11);
        img_mmodel12 = (ImageView)findViewById(R.id.img_mmodel12);
        img_mmodel13 = (ImageView)findViewById(R.id.img_mmodel13);
        img_mmodel14 = (ImageView)findViewById(R.id.img_mmodel14);


        setArrayView();
        setClickListener();
        setupModel();

        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                createmodel(anchorNode,selected);
            }
        });
    }

    private void createmodel(AnchorNode anchorNode, int selected) {
        if (selected == 1){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel1);
            Transmodel1.select();
        }else if (selected == 2){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel2);
            Transmodel1.select();
        }
        else if (selected == 3){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel3);
            Transmodel1.select();
        }else if (selected == 4){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel4);
            Transmodel1.select();
        }else if (selected == 5){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel5);
            Transmodel1.select();
        }else if (selected == 6){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel6);
            Transmodel1.select();
        }else if (selected == 7){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel7);
            Transmodel1.select();
        }else if (selected == 8){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel8);
            Transmodel1.select();
        }else if (selected == 9){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel9);
            Transmodel1.select();
        }else if (selected == 10){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel10);
            Transmodel1.select();
        }
        else if (selected == 11){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel11);
            Transmodel1.select();
        }else if (selected == 12){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel12);
            Transmodel1.select();
        }else if (selected == 13){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel13);
            Transmodel1.select();
        }else if (selected == 14){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel14);
            Transmodel1.select();
        }
        else if (selected == 15){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel15);
            Transmodel1.select();
        }else if (selected == 16){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel16);
            Transmodel1.select();
        }else if (selected == 17){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_fmodel17);
            Transmodel1.select();
        }
        else if (selected == 18){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel1);
            Transmodel1.select();
        }else if (selected == 19){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel2);
            Transmodel1.select();
        }else if (selected == 20){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel3);
            Transmodel1.select();
        }else if (selected == 21){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel4);
            Transmodel1.select();
        }else if (selected == 22){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel5);
            Transmodel1.select();
        }else if (selected == 23){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel6);
            Transmodel1.select();
        }else if (selected == 24){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel7);
            Transmodel1.select();
        }else if (selected == 25){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel8);
            Transmodel1.select();
        }else if (selected == 26){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel9);
            Transmodel1.select();
        }else if (selected == 27){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel10);
            Transmodel1.select();
        }else if (selected == 28){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel11);
            Transmodel1.select();
        }else if (selected == 29){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel12);
            Transmodel1.select();
        }else if (selected == 30){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel13);
            Transmodel1.select();
        }else if (selected == 31){
            TransformableNode Transmodel1 = new TransformableNode(arFragment.getTransformationSystem());
            Transmodel1.setParent(anchorNode);
            Transmodel1.setRenderable(viewRenderable_mmodel14);
            Transmodel1.select();
        }
    }
    private void setArrayView() {
        ArrayView = new View[]{img_fmodel1,img_fmodel2,img_fmodel3,img_fmodel4,img_fmodel5,
                img_fmodel6,img_fmodel7,img_fmodel8,img_fmodel9,img_fmodel10,
                img_fmodel11,img_fmodel12,img_fmodel13,img_fmodel14,img_fmodel15,
                img_fmodel16,img_fmodel17,img_mmodel1,img_mmodel2,img_mmodel3,
                img_mmodel4,img_mmodel5,img_mmodel6,img_mmodel7,img_mmodel8,
                img_mmodel9,img_mmodel10,img_mmodel11,img_mmodel12,img_mmodel13,
                img_mmodel14};
    }


    private void setClickListener() {
        for (int i=0;i<ArrayView.length;i++){
            ArrayView[i].setOnClickListener(this);
        }
    }




    private void setupModel() {
//        ModelRenderable.builder()
//                .setSource(this, R.raw.modle1)//change model
//                .build()
//                .thenAccept(renderable -> model1_Renderable = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });
        ViewRenderable.builder()
                .setView(this, R.layout.fmodel1)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel1 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel2)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel2 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel3)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel3 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel4)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel4 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel5)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel5 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel6)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel6 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel7)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel7 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel8)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel8 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel9)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel9 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel10)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel10 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel11)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel11 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel12)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel12 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel13)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel13 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel14)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel14 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel15)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel15 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel16)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel16 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.fmodel17)
                .build()
                .thenAccept(renderable -> viewRenderable_fmodel17 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel1)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel1 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel2)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel2 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel3)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel3 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel4)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel4 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel5)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel5 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel6)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel6 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel7)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel7 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel8)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel8 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel9)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel9 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel10)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel10 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel11)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel11 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel12)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel12 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel13)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel13 = renderable);

        ViewRenderable.builder()
                .setView(this, R.layout.mmodel14)
                .build()
                .thenAccept(renderable -> viewRenderable_mmodel14 = renderable);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_fmodel1){
            selected = 1;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel2){
            selected = 2;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel3){
            selected = 3;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel4){
            selected = 4;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel5){
            selected = 5;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel6){
            selected = 6;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel7){
            selected = 7;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel8){
            selected = 8;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel9){
            selected = 9;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel10){
            selected = 10;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.img_fmodel11){
            selected = 11;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel12){
            selected = 12;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel13){
            selected = 13;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.img_fmodel14){
            selected = 14;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.img_fmodel15){
            selected = 15;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel16){
            selected = 16;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_fmodel17){
            selected = 17;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.img_mmodel1){
            selected = 18;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel2){
            selected = 19;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel3){
            selected = 20;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel4){
            selected = 21;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel5){
            selected = 22;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel6){
            selected = 23;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel7){
            selected = 24;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel8){
            selected = 25;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel9){
            selected = 26;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel10){
            selected = 27;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel11){
            selected = 28;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel12){
            selected = 29;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel13){
            selected = 30;
            setBackground(v.getId());
        }else if (v.getId() == R.id.img_mmodel14){
            selected = 31;
            setBackground(v.getId());
        }

    }

    private void setBackground(int id) {
        for (int i=0;i<ArrayView.length;i++){
            if (ArrayView[i].getId() == id){
                ArrayView[i].setBackgroundColor(Color.parseColor("#80333639"));
            }else{
                ArrayView[i].setBackgroundColor(Color.TRANSPARENT);
            }

        }
    }
}