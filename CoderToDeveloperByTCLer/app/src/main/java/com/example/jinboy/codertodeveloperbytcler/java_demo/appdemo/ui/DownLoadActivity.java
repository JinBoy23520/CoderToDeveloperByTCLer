package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;

import java.io.File;
import java.lang.ref.WeakReference;

import javademo.iodemo.DownloadProgressListener;
import javademo.iodemo.FileDownloader;


public class DownLoadActivity extends Activity {
    private static final int PROCESSING = 1;
    private static final int FAILURE = -1;

    private EditText pathText;
    private Button downloadButton;
    private Button stopButton;
    private ProgressBar progressBar;
    private Context context;
    Handler handler = new UIHandler(this);


    private static class UIHandler extends Handler {
        private final WeakReference<DownLoadActivity> mActivity;

        //弱引用，避免内存泄露
        UIHandler(DownLoadActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            DownLoadActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case PROCESSING:
                        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
                        TextView resultView = (TextView) activity.findViewById(R.id.resultView);
                        progressBar.setProgress(msg.getData().getInt("size"));
                        float num = (float) progressBar.getProgress() / (float) progressBar.getMax();
                        int result = (int) (num * 100);
                        resultView.setText(result + "%");
                        if (progressBar.getProgress() == progressBar.getMax()) {
                            Toast.makeText(activity, R.string.success, Toast.LENGTH_LONG).show();
                        }
                        break;
                    case FAILURE:
                        Toast.makeText(activity, R.string.error, Toast.LENGTH_LONG).show();
                        break;
                    default:
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.down_load_activity);
        context = this;
        pathText = (EditText) findViewById(R.id.path);
        downloadButton = (Button) findViewById(R.id.downloadbutton);
        stopButton = (Button) findViewById(R.id.stopbutton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ButtonClickListener listener = new ButtonClickListener();
        downloadButton.setOnClickListener(listener);
        stopButton.setOnClickListener(listener);
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.downloadbutton:
                    String path = pathText.getText().toString();
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        //外部存储
                        //File savDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
                        //File savDir = Environment.getExternalStorageDirectory();
                        //内部存储
                        File savDir = new File(context.getCacheDir(), "/cache/exe");
                        download(path, savDir);
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.sdcarderror, Toast.LENGTH_LONG).show();
                    }
                    downloadButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    break;
                case R.id.stopbutton:
                    exit();
                    Toast.makeText(getApplicationContext(), "Now thread is Stopping!!", Toast.LENGTH_LONG).show();
                    downloadButton.setEnabled(true);
                    stopButton.setEnabled(false);
                    break;
                default:
            }
        }

        private DownloadTask task;

        private void exit() {
            if (task != null) {
                task.exit();
            }
        }

        private void download(String path, File savDir) {
            task = new DownloadTask(path, savDir);
            new Thread(task).start();
        }

        class DownloadTask implements Runnable {
            private String path;
            private File saveDir;
            private FileDownloader loader;

            DownloadTask(String path, File saveDir) {
                this.path = path;
                this.saveDir = saveDir;
            }

            void exit() {
                if (loader != null) {
                    loader.exit();
                }
            }

            //进度监听，通过message机制把传送进度
            DownloadProgressListener downloadProgressListener = new DownloadProgressListener() {
                @Override
                public void onDownloadSize(int size) {
                    Message msg = new Message();
                    msg.what = PROCESSING;
                    msg.getData().putInt("size", size);
                    handler.sendMessage(msg);
                }
            };

            @Override
            public void run() {
                try {
                    //固定三个线程
                    loader = new FileDownloader(getApplicationContext(), path, saveDir, 3);
                    progressBar.setMax(loader.getFileSize());
                    loader.download(downloadProgressListener);
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendMessage(handler.obtainMessage(FAILURE));
                }
            }
        }
    }
}
