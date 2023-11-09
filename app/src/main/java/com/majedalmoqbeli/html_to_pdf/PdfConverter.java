package com.majedalmoqbeli.html_to_pdf;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.lang.ref.WeakReference;

public class PdfConverter implements Runnable {


    private static WeakReference<PdfConverter> sInstance;

    private Context mContext;
    private String mHtmlString;
    private boolean mIsCurrentlyConverting;


    private PdfConverter() {
    }

    public static synchronized WeakReference<PdfConverter> getInstance() {
        if (sInstance == null)
            sInstance = new WeakReference<>(new PdfConverter());
        return sInstance;
    }


    @Override
    public void run() {
        WebView mWebView = new WebView(mContext);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {


                // Get a PrintManager instance
                PrintManager printManager = (PrintManager) mContext.getSystemService(Context.PRINT_SERVICE);

                String jobName = mContext.getString(R.string.app_name) + " Document";

                // Get a print adapter instance
                PrintDocumentAdapter printAdapter = mWebView.createPrintDocumentAdapter(jobName);

                // Create a print job with name and adapter instance
                PrintJob printJob = printManager.print(jobName, printAdapter,
                        new PrintAttributes.Builder().build());


            }
        });
        mWebView.loadData(mHtmlString, "text/HTML", "UTF-8");
    }


    public void convert(Context context, String htmlString) {
        if (context == null)
            throw new IllegalArgumentException("context can't be null");
        if (htmlString == null)
            throw new IllegalArgumentException("htmlString can't be null");
        if (mIsCurrentlyConverting)
            return;

        mContext = context;
        mHtmlString = htmlString;
        mIsCurrentlyConverting = true;
        runOnUiThread(this);
    }


    private void runOnUiThread(Runnable runnable) {
        Handler handler = new Handler(new Handler().getLooper());
        handler.post(runnable);
    }


}
