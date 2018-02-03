package com.example.start.l13;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by start on 2018-02-03.
 */

public class DemoJobCreator implements JobCreator {
    @Override
    public Job create(String tag) {
        switch (tag) {
            case ReportJob.TAG:
                return new ReportJob();
            default:
                return null;
        }

    }
}
