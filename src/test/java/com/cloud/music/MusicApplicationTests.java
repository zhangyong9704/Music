package com.cloud.music;

import com.cloud.music.common.constParams.UploadLocalPathConfig;
import com.cloud.music.common.upload.UploadToLocalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicApplicationTests {

    @Autowired
    UploadToLocalService uploadToLocalService;
    @Autowired
    UploadLocalPathConfig uploadLocalPathConfig;

    @Test
    void contextLoads() {

    }


}
