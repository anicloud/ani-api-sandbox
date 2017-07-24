package com.ani.sunny.api.core.service.domian.Stub.init;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.sunny.api.commons.constants.SunnyConstants;
import com.ani.sunny.api.commons.stub.SunnyStub;
import com.ani.sunny.api.core.service.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

/**
 * Created by lihui on 16-10-28.
 */
@Component("sunnyStubPersistService")
public class SunnyStubPersistServiceImpl implements SunnyStubPersistService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SunnyStubPersistServiceImpl.class);
    private final static String FILE_PATH = "properties/StubMappings";

    @Autowired
    private SpringContextHolder springContextHolder;

    @Override
    public Map<Integer,SunnyStub> fetchSunnyStubMappings() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        Map<Integer,SunnyStub> resultMap = new HashMap<>();
        try {
            String path = getClass().getResource("/").getPath()+FILE_PATH;

            fis = new FileInputStream(new File(path));
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String temp = null;
            while(!StringUtils.isEmpty(temp = br.readLine())) {
                String regex = "\\s+";
                temp = temp.trim();
                String[] strs = temp.split(regex);
                if(strs.length >= 3) {
                    int hashCode = Objects.hash(Long.valueOf(strs[0]), Integer.valueOf(strs[1]));
                    SunnyStub stubInvoker = (SunnyStub) SpringContextHolder.getBean(Class.forName(strs[2]));
                    resultMap.put(hashCode,stubInvoker);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(br != null)
                    br.close();
                if(isr != null)
                    isr.close();
                if(fis != null)
                    fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultMap;
    }

    @Override
    public Map<Long, List<Integer>> getSunnyStubMap() {
        BufferedReader br = null;
        Map<Long, List<Integer>>  stubMap = new HashMap<>();
        try {
            String path = getClass().getResource("/").getPath()+FILE_PATH;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
            String temp = null;
            while(!StringUtils.isEmpty(temp = br.readLine())) {
                String regex = "\\s+";
                temp = temp.trim();
                String[] strs = temp.split(regex);
                if(strs.length >= 3) {
                    Long groupId = Long.valueOf(strs[0]);
                    List<Integer> stubIds = stubMap.get(groupId);
                    if (stubIds == null)
                        stubIds = new ArrayList<>();
                    stubIds.add(Integer.valueOf(strs[1]));
                    stubMap.put(groupId,stubIds);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(br != null)
                    br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return stubMap;
    }

    @Override
    public void update(AniStub aniStub,SunnyStub sunnyStub) throws Exception {
        String path = getClass().getResource("/").getPath()+FILE_PATH;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            fos = new FileOutputStream(new File(path),true);
            pw = new PrintWriter(fos,true);
            if(aniStub != null && sunnyStub != null) {
                String str = aniStub.stubId + "\t"+aniStub.stubGroupId + "\t" + sunnyStub.getClass().getName();
                pw.println();
                pw.print(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(pw != null)
                    pw.close();
                if(fos != null)
                    fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @PostConstruct
    public void initSunnyStubMappings() {
        SunnyConstants.SUNNY_STUB_MAPPINGS = fetchSunnyStubMappings();
        SunnyConstants.SUNNY_STUB_REGIST_MAP = getSunnyStubMap();
    }
}
