package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MyActivity extends Activity {

//    String tag = "Lifecycle";
    CharSequence[] items = {"Google","Apple","Microsoft"};
    boolean[] itemsChecked = new boolean[items.length];

    ProgressDialog progressDialog;

    //从意图返回数据需要一个识别码
    //如果请求码为-1，则没有结果返回
    int request_Code =1;

    @Override
    protected Dialog onCreateDialog(int id) {
//        return super.onCreateDialog(id);
        switch (id){
            case 0 : return new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_launcher)
                    .setTitle("This is a dialog with some simple text...")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(getBaseContext(), "Ok clicked!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Toast.makeText(getBaseContext(), "Cancel clicked!", Toast.LENGTH_SHORT).show();
                                }
                            })
                    .setMultiChoiceItems(items, itemsChecked,
                            new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    Toast.makeText(getBaseContext(), items[which] + (isChecked ? " checked!" : "unchecked"), Toast.LENGTH_SHORT).show();
                                }
                            }).create();
            case 1:
                progressDialog = new ProgressDialog(this);
                progressDialog.setIcon(R.drawable.ic_launcher);
                progressDialog.setTitle("Downloading files...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getBaseContext(),"OK clicked!",Toast.LENGTH_SHORT);
                            }
                        });
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(getBaseContext(), "Cancel clicked!", Toast.LENGTH_SHORT).show();
                        }
                    });
                return progressDialog;
        }
        return null;
    }

    public void onClick(View v){
        showDialog(0);
    }

    public void onClick2(View v){
        //---show the dialog
        final ProgressDialog dialog = ProgressDialog.show(
                this,"Doing something","Please wait...",true
        );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // simulate doing something lengthy
                    Thread.sleep(5000);
                    //dismiss the dialog
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void onClick4(View v){
        /**
         * 使用意图连接活动
         */
        startActivity(new Intent("org.demo.cyp.SecondActivity"));
        //or use
        //startActivity(new Intent(this,SecondActivity.class));
    }

    /**
     * 从意图中返回数据
     * @param v
     */
    public void onClick5(View v){
        startActivityForResult(new Intent("org.demo.cyp.SecondActivity"),request_Code);
    }

    /**
     * 当一个活动返回时，调用此方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode == request_Code){
            if(resultCode == RESULT_OK){
                Toast.makeText(this,data.getData().toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 显示进度对话框
     * @param v
     */
    public void onClick3(View v){
        showDialog(1);
        progressDialog.setProgress(0);

        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i=1;i<=15;i++){
                    try{
                        //simulate doing something lengthy
                        Thread.sleep(1000);
                        System.out.println("是否执行到这里");
                        //update the dialog
                        progressDialog.incrementProgressBy((100/15));
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hides the title bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_my);
//        Log.d(tag,"In the onCreate() event");
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d(tag, "In the onStart() event");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.d(tag,"In the onRestart() event");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d(tag,"In the onResume() event");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d(tag,"In the onDestroy() event");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d(tag,"In the onStop() event");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d(tag,"In the onPause() event");
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
