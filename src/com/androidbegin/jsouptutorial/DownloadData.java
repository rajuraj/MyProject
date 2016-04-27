package com.androidbegin.jsouptutorial;

import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class DownloadData extends AsyncTask<Void, Void, Void> {

    String url = "http://www.tezu.ernet.in/";
    String RemoveNull,linkHref;

    public DownloadListener delegate = null;

    public DownloadData(DownloadListener listener) {
        delegate = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {

            Document doc = Jsoup.connect(url).get();
            Element content = doc.getElementById("content");

            Elements linksMain = content.getElementsByClass("newsitem");
            Elements links = linksMain.select("a[href]");

            for (Element link : links) {

                    linkHref = linkHref+link.text()+"\n" + link.attr("href")+ "\n" + "\n" + "\n";
                    RemoveNull = linkHref.replace("null", "");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Void result) {

        delegate.processFinish(RemoveNull);
    }
}
