package com.github.sampathsl.dmanager.model;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.Convert;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DownloadSessionTest {

    private Long id;

    private Long version;

    private LocalDateTime created;

    private List<DownloadTask> downloadTasks;

    @Before
    public void init() {
        id = new Long(0l);
        version = new Long(0l);
        created = LocalDateTime.now();
        downloadTasks = new ArrayList<>();
    }

    @Test
    public void testGetter() {
        DownloadSession downloadSession = new DownloadSession();
    }

}
